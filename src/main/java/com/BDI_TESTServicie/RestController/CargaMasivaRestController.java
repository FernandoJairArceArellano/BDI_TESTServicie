package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.DAO.RegistroDAOImplementation;
import com.BDI_TESTServicie.DAO.UsuarioServicie;
import com.BDI_TESTServicie.JPA.CantidadAsignada;
import com.BDI_TESTServicie.JPA.CantidadNominal;
import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.DetalleCantidad;
import com.BDI_TESTServicie.JPA.DetalleNodoComercial;
import com.BDI_TESTServicie.JPA.DetalleTarifa;
import com.BDI_TESTServicie.JPA.DetalleZona;
import com.BDI_TESTServicie.JPA.NodoComercial;
import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ResultFile;
import com.BDI_TESTServicie.JPA.Tarifa;
import com.BDI_TESTServicie.JPA.TipoNodoComercial;
import com.BDI_TESTServicie.JPA.TipoZona;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.Zona;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/cargaMasiva/v1")
public class CargaMasivaRestController {

    @Autowired
    private UsuarioServicie usuarioServicie;

    @Autowired
    private RegistroDAOImplementation registroDAOImplementation

    @PostMapping("/registros")
    @Operation(
            summary = "Guardar un archivo",
            description = "Permite guardar un documento xls en el servidor para procesar los datos."
    )
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Archivo procesado correctamente",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Error en los datos enviados o en el proceso de lectura del archivo",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content
        )
    }) ;

    public ResponseEntity<?> subirArchivo(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        if (!file.isEmpty() || file != null) {
            try {

                // Saber el tipo de archivo de ser neserario para validarlo antes de procesarse
                String tipoArchivo = file.getOriginalFilename().split("\\.")[1];

                // Obtener la ruta absoluta y darle un nuevo nombre con un formato en especifico
                String root = System.getProperty("user.dir");
                String path = "src/main/resuources/static/archivos";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHmmSS"));
                String aboslutePath = root + "/" + path + "/" + fecha + file.getOriginalFilename();
                file.transferTo(new File(aboslutePath));

                List<RegistroSistema> listaRegistros = new ArrayList();

                if (tipoArchivo.equals("xlsx")) {
                    listaRegistros = LecturaArchivoExcelTransacciones(new File(aboslutePath));
                } else {
                    result.correct = false;
                    result.errorMessage = "Error al guardar el archivo.";
                }

                List<ResultFile> listaErrores = ValidarArchivo(listaRegistros);

                if (listaErrores.isEmpty()) {
                    result.correct = true;
                    result.object = aboslutePath;
                    return ResponseEntity.ok(result);
                } else {
                    result.correct = false;
                    result.objects = new ArrayList();

                    for (ResultFile error : listaErrores) {
                        result.objects.add(error);
                    }
                    return ResponseEntity.status(404).body(result);
                }

//                return ResponseEntity.ok("Archivo procesado correctamente");
            } catch (Exception ex) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Error al procesar el archivo: " + ex.getMessage());
            }
        } else {
            result.correct = false;
            return ResponseEntity.status(400).body(result);

        }
    }

    public List<RegistroSistema> LecturaArchivoExcelTransacciones(File archivo) {
        List<RegistroSistema> registros = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo)) {
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) {
                        continue; // Saltar encabezados
                    }
                    RegistroSistema registro = new RegistroSistema();
                    registro.usuario = new Usuario();
                    registro.contrato = new Contrato();
                    registro.nodoComercial = new NodoComercial();
                    registro.detalleNodoComercial = new DetalleNodoComercial();
                    registro.zona = new Zona(row.getCell(7).toString(), 1);
                    registro.tipoZona = new TipoZona();
                    registro.detalleZona = new DetalleZona();
                    registro.tarifa = new Tarifa(new BigDecimal(row.getCell(14).toString()), new BigDecimal(row.getCell(15).toString()));
                    registro.detalleTarifa = new DetalleTarifa();
                    registro.cantidadNominal = new CantidadNominal(new BigDecimal(row.getCell(9).toString()), 1);
                    registro.cantidadAsignada = new CantidadAsignada(new BigDecimal(row.getCell(10).toString()), 1);
                    registro.detalleCantidad = new DetalleCantidad();
                    registro.transaccion = new Transaccion();

                    // 0 Fecha, 1 Contrato, 2 Usuario
                    registro.usuario.setNombre(row.getCell(2).toString());
                    registro.contrato.setCodigoContrato(row.getCell(1).toString());
                    registro.transaccion.setFecha(DateUtil.getJavaDate(row.getCell(0).getNumericCellValue()));
                    registro.contrato.setUsuario(registro.usuario);

                    // 3 Nodo Recepción
                    NodoComercial nodoRecepcion = new NodoComercial();
                    nodoRecepcion.setCodigoNodo(row.getCell(3).toString());
                    nodoRecepcion.setNombre(row.getCell(4).toString());
                    TipoNodoComercial tipoNodoComercialRecepcion = new TipoNodoComercial();
                    tipoNodoComercialRecepcion.setIdTipoNodo(1);
                    nodoRecepcion.setTipoNodoCOmercial(tipoNodoComercialRecepcion);

                    // 5 Nodo Entrega
                    NodoComercial nodoEntrega = new NodoComercial();
                    nodoEntrega.setCodigoNodo(row.getCell(5).toString());
                    nodoEntrega.setNombre(row.getCell(6).toString());
                    TipoNodoComercial tipoNodoComercialEntrega = new TipoNodoComercial();
                    tipoNodoComercialEntrega.setIdTipoNodo(2);
                    nodoEntrega.setTipoNodoCOmercial(tipoNodoComercialEntrega);

                    registro.detalleNodoComercial.setNodoRecepcion(nodoRecepcion);
                    registro.detalleNodoComercial.setNodoEntrega(nodoEntrega);

                    // 7-8 Zona
                    Zona zonaInyeccion = new Zona(row.getCell(7).toString(), 1);
                    Zona zonaExtraccion = new Zona(row.getCell(8).toString(), 2);
                    registro.detalleZona.setZonaInyeccion(zonaInyeccion);
                    registro.detalleZona.setZonaExtraccion(zonaExtraccion);

                    // Cantidades: 9-12
                    CantidadNominal cantidadNominalRecibida = new CantidadNominal(new BigDecimal(row.getCell(9).toString()), 1);
                    CantidadAsignada cantidadAsignadaRecibida = new CantidadAsignada(new BigDecimal(row.getCell(10).toString()), 1);
                    CantidadNominal cantidadNominalEntregada = new CantidadNominal(new BigDecimal(row.getCell(11).toString()), 2);
                    CantidadAsignada cantidadAsignadaEntregada = new CantidadAsignada(new BigDecimal(row.getCell(12).toString()), 2);
                    registro.detalleCantidad.setCantidadAsignadaEntregada(cantidadAsignadaEntregada);
                    registro.detalleCantidad.setCantidadAsignadaRecepcion(cantidadAsignadaRecibida);
                    registro.detalleCantidad.setCantidadNominalRecepcion(cantidadNominalRecibida);
                    registro.detalleCantidad.setCantidadNominalEntregada(cantidadNominalEntregada);

                    // Tarifas
                    registro.tarifa = new Tarifa(new BigDecimal(row.getCell(14).toString()), new BigDecimal(row.getCell(15).toString()));
                    registro.detalleTarifa.setTarifa(registro.tarifa);
                    registro.detalleTarifa.setGasEnExceso(new BigDecimal(row.getCell(13).toString()));
                    registro.detalleTarifa.setCargoUso(new BigDecimal(row.getCell(16).toString()));
                    registro.detalleTarifa.setCargoGasEnExceso(new BigDecimal(row.getCell(17).toString()));
                    registro.detalleTarifa.setTotalAFacturar(new BigDecimal(row.getCell(18).toString()));
                    registros.add(registro);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return registros;
    }

    private List<ResultFile> ValidarArchivo(List<RegistroSistema> listaRegistros) {
        List<ResultFile> listaErrores = new ArrayList<>();
        if (listaRegistros == null) {
            listaErrores.add(new ResultFile(0, "La lista es nula", "La lista es nula"));
        } else if (listaRegistros.isEmpty()) {
            listaErrores.add(new ResultFile(0, "La lista está vacía", "La lista está vacía"));
        } else {

        }
        return listaErrores;
    }

    @PostMapping("/CargaMasivaTransaccion/Procesar")
    public ResponseEntity<Result> ProcesarArchivoTransaccion(@RequestBody String absolutePath) {
        Result result = new Result();
        try {
            List<RegistroSistema> registros = LecturaArchivoExcelTransacciones(new File(absolutePath));

            for (RegistroSistema registro : registros) {
                System.out.println("Agregando transacción de contrato: " + registro.contrato.getCodigoContrato());
                registroDAOImplementation.addJPA(registro); // Método que debes crear para procesar cada RegistroSistema
            }
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            ex.printStackTrace();
        }

        return ResponseEntity.ok(result);
    }

}
