package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ResultFile;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.Service.RegistroSistemaService;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/registro/v1")
public class RegistroRestController {

    @Autowired
    private RegistroSistemaService registroSistemaService;

    @PostMapping("/CargaMasiva")
    public ResponseEntity CargaMasiva(@RequestParam("archivo") MultipartFile archivo) {
        Result result = new Result();
        if (!archivo.isEmpty() || archivo != null) {

            try {
                String tipoArchivo = archivo.getOriginalFilename().split("\\.")[1];

                String root = System.getProperty("user.dir"); //Obtener direccion del proyecto en el equipo
                String path = "src/main/resources/static/archivos"; //Path relativo dentro del proyecto
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
                String absolutePath = root + "/" + path + "/" + fecha + archivo.getOriginalFilename();
                archivo.transferTo(new File(absolutePath));
                //Leer el archivo
                List<RegistroSistema> listaRegistro = new ArrayList();

                if (tipoArchivo.equals("xlsx")) {
                    listaRegistro = LecturaArchivoExcel(new File(absolutePath));
                } else {
                    return ResponseEntity.status(500).body("Error fromato de archivo no soportado");
                }

                //Validar el archivo
                List<ResultFile> listaErrores = ValidarArchivo(listaRegistro);

                if (listaErrores.isEmpty()) {
                    //Proceso mi archivo
                    result.correct = true;
                    result.object = absolutePath;
                    return ResponseEntity.ok(result); //ResultFile o Result?
                } else {
                    result.correct = false;
                    result.objects = new ArrayList();

                    for (ResultFile error : listaErrores) {
                        result.objects.add(error);
                    }

                    return ResponseEntity.status(400).body(result);
                }

            } catch (Exception ex) {
                return ResponseEntity.status(500).body("Algo fallo al procesar el archivo");
            }

        } else {
            result.correct = false;
            return ResponseEntity.status(400).body(result);

        }

    }

    public List<RegistroSistema> LecturaArchivoExcel(File archivo) {
        List<RegistroSistema> listaRegistro = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo);) {
            for (Sheet sheet : workbook) {
                for (Row row : sheet) {
                    RegistroSistema registroSistema = new RegistroSistema();
                    registroSistema.contrato = new Contrato();

                    if (DateUtil.isCellDateFormatted(row.getCell(0))) {
                        registroSistema.contrato.setFecha(row.getCell(0).getDateCellValue());
                    } else {
                        double fechaTexto = row.getCell(0).getNumericCellValue();
                        Date formato = DateUtil.getJavaDate(fechaTexto);
                        registroSistema.contrato.setFecha(formato);
                    }
                    registroSistema.contrato.setCodigoContrato(row.getCell(1).toString());

                    registroSistema.usuario = new Usuario();
                    registroSistema.usuario.setNombre(row.getCell(2).toString());

                    // Registrar el contrato y zonas de inyeccion y extraccion a contrato
                    registroSistema.nodoRecepccion = new NodoRecepccion();
                    registroSistema.nodoRecepccion.setCodigoNodo(row.getCell(3).toString());
                    registroSistema.nodoRecepccion.setNombreNodoComercial(row.getCell(4).toString());

                    registroSistema.nodoEntrega = new NodoEntrega();
                    registroSistema.nodoEntrega.setCodigoNodo(row.getCell(5).toString());
                    registroSistema.nodoEntrega.setNombreNodoComercial(row.getCell(6).toString());

                    registroSistema.zonaInyeccion = new ZonaInyeccion();
                    registroSistema.zonaInyeccion.setNombreZona(row.getCell(7).toString());

                    registroSistema.zonaExtraccion = new ZonaExtraccion();
                    registroSistema.zonaExtraccion.setNombreZona(row.getCell(8).toString());

                    registroSistema.transaccion = new Transaccion();
                    registroSistema.transaccion.setCantidadNominadaRecepcion(new BigDecimal(row.getCell(9).getNumericCellValue()));
                    registroSistema.transaccion.setCantidadAsignadaRecepcion(new BigDecimal(row.getCell(10).getNumericCellValue()));
                    registroSistema.transaccion.setCantidadNominadaEntrega(new BigDecimal(row.getCell(11).getNumericCellValue()));
                    registroSistema.transaccion.setCantidadAsignadaEntrega(new BigDecimal(row.getCell(12).getNumericCellValue()));
                    registroSistema.transaccion.setGasEnExceso(new BigDecimal(row.getCell(13).getNumericCellValue()));
                    registroSistema.transaccion.setTarifaExcesoFirme(new BigDecimal(row.getCell(14).getNumericCellValue()));
                    registroSistema.transaccion.setTarifaUsoInterrumpible(new BigDecimal(row.getCell(15).getNumericCellValue()));
                    registroSistema.transaccion.setCargoUso(new BigDecimal(row.getCell(16).getNumericCellValue()));    // la propiedade en JPA esta en Bigdecimal y  la celda esta en Number
                    registroSistema.transaccion.setCargoGasEnExceso(new BigDecimal(row.getCell(17).getNumericCellValue()));    // la propiedade en JPA esta en Bigdecimal y  la celda esta en Number
                    registroSistema.transaccion.setTotalAFacturar(new BigDecimal(row.getCell(18).getNumericCellValue()));    // la propiedade en JPA esta en Bigdecimal y  la celda esta en Number
                    listaRegistro.add(registroSistema);
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al abrir el archivo");
        }

        return listaRegistro;
    }

    public List<ResultFile> ValidarArchivo(List<RegistroSistema> listaRegistro) {
        List<ResultFile> listaErrores = new ArrayList<>();

        if (listaRegistro == null) {
            listaErrores.add(new ResultFile(0, "La lista es nula", "La lista es nula"));
        } else if (listaRegistro.isEmpty()) {
            listaErrores.add(new ResultFile(0, "La lista está vacía", "La lista está vacía"));
        } else {
            int fila = 1;
            for (RegistroSistema registro : listaRegistro) {

                // Contrato
                if (registro.contrato == null || registro.contrato.getCodigoContrato() == null || registro.contrato.getCodigoContrato().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "Contrato", "El código de contrato es obligatorio"));
                }

                if (registro.contrato == null || registro.contrato.getFecha() == null) {
                    listaErrores.add(new ResultFile(fila, "Fecha", "La fecha de contrato es obligatoria"));
                }

                // Usuario
                if (registro.usuario == null || registro.usuario.getNombre() == null || registro.usuario.getNombre().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "Usuario", "El nombre del usuario es obligatorio"));
                }

                // Nodo Recepción
                if (registro.nodoRecepccion == null || registro.nodoRecepccion.getCodigoNodo() == null || registro.nodoRecepccion.getCodigoNodo().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "NodoRecepcion", "El código del nodo de recepción es obligatorio"));
                }

                if (registro.nodoRecepccion == null || registro.nodoRecepccion.getNombreNodoComercial() == null || registro.nodoRecepccion.getNombreNodoComercial().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "NombreNodoRecepcion", "El nombre del nodo de recepción es obligatorio"));
                }

                // Nodo Entrega
                if (registro.nodoEntrega == null || registro.nodoEntrega.getCodigoNodo() == null || registro.nodoEntrega.getCodigoNodo().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "NodoEntrega", "El código del nodo de entrega es obligatorio"));
                }

                if (registro.nodoEntrega == null || registro.nodoEntrega.getNombreNodoComercial() == null || registro.nodoEntrega.getNombreNodoComercial().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "NombreNodoEntrega", "El nombre del nodo de entrega es obligatorio"));
                }

                // Zonas
                if (registro.zonaInyeccion == null || registro.zonaInyeccion.getNombreZona() == null || registro.zonaInyeccion.getNombreZona().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "ZonaInyeccion", "La zona de inyección es obligatoria"));
                }

                if (registro.zonaExtraccion == null || registro.zonaExtraccion.getNombreZona() == null || registro.zonaExtraccion.getNombreZona().trim().isEmpty()) {
                    listaErrores.add(new ResultFile(fila, "ZonaExtraccion", "La zona de extracción es obligatoria"));
                }

                // Transacción
                if (registro.transaccion == null) {
                    listaErrores.add(new ResultFile(fila, "Transaccion", "Los datos de transacción son obligatorios"));
                } else {
                    if (registro.transaccion.getCantidadNominadaRecepcion() == null) {
                        listaErrores.add(new ResultFile(fila, "CantidadNominalRecepcion", "Debe especificar la cantidad nominal de recepción"));
                    }

                    if (registro.transaccion.getCantidadAsignadaRecepcion() == null) {
                        listaErrores.add(new ResultFile(fila, "CantidadAsignadaRecepcion", "Debe especificar la cantidad asignada de recepción"));
                    }

                    if (registro.transaccion.getCantidadNominadaEntrega() == null) {
                        listaErrores.add(new ResultFile(fila, "CantidadNominalEntregada", "Debe especificar la cantidad nominal de entrega"));
                    }

                    if (registro.transaccion.getCantidadAsignadaEntrega() == null) {
                        listaErrores.add(new ResultFile(fila, "CantidadAsignadaEntregada", "Debe especificar la cantidad asignada de entrega"));
                    }

                    if (registro.transaccion.getGasEnExceso() == null) {
                        listaErrores.add(new ResultFile(fila, "GasEnExceso", "Debe especificar el gas en exceso"));
                    }

                    if (registro.transaccion.getTarifaExcesoFirme() == null) {
                        listaErrores.add(new ResultFile(fila, "TarifaExcesoFirme", "Debe especificar la tarifa de exceso firme"));
                    }

                    if (registro.transaccion.getTarifaUsoInterrumpible() == null) {
                        listaErrores.add(new ResultFile(fila, "TarifaUsoIterrumpible", "Debe especificar la tarifa de uso interrumpible"));
                    }

                    if (registro.transaccion.getCargoUso() == null) {
                        listaErrores.add(new ResultFile(fila, "CargoUso", "Debe especificar el cargo por uso"));
                    }

                    if (registro.transaccion.getCargoGasEnExceso() == null) {
                        listaErrores.add(new ResultFile(fila, "CargoGasEnExceso", "Debe especificar el cargo por gas en exceso"));
                    }

                    if (registro.transaccion.getTotalAFacturar() == null) {
                        listaErrores.add(new ResultFile(fila, "TotalAFacturar", "Debe especificar el total a facturar"));
                    }
                }

                fila++;
            }
        }
        return listaErrores;
    }

    // Procesar archivo
    @PostMapping("/CargaMasiva/Procesar")
    public ResponseEntity<Result> ProcesarArchivo(@RequestBody String absolutePath) {
        Result result = new Result();
        List<ResultFile> erroresValidacion = new ArrayList<>();

        try {
            if (absolutePath == null || absolutePath.isBlank()) {
                result.correct = false;
                result.errorMessage = "Ruta del archivo vacía";
                return ResponseEntity.badRequest().body(result);
            }

            String tipoArchivo = absolutePath.split("\\.")[1].toLowerCase();
            List<RegistroSistema> listaRegistro = new ArrayList<>();

            if (tipoArchivo.equals("xlsx")) {
                listaRegistro = LecturaArchivoExcel(new File(absolutePath));
            } else {
                result.correct = false;
                result.errorMessage = "Tipo de archivo no soportado: " + tipoArchivo;
                return ResponseEntity.badRequest().body(result);
            }

            if (listaRegistro == null || listaRegistro.isEmpty()) {
                result.correct = false;
                result.errorMessage = "El archivo no contiene datos válidos.";
                return ResponseEntity.badRequest().body(result);
            }

            int fila = 1;
            for (RegistroSistema registro : listaRegistro) {
                System.out.println("Procesando registro fila: " + fila);
                Result res = registroSistemaService.procesarRegistroSistema(registro);

                if (!res.correct) {
                    erroresValidacion.add(new ResultFile(fila, registro.contrato.getCodigoContrato(), res.errorMessage));
                }

                fila++;
            }

            result.correct = erroresValidacion.isEmpty();
            result.errorMessage = erroresValidacion.isEmpty() ? null : "Se encontraron errores en algunas filas.";
            result.objects = erroresValidacion.isEmpty() ? null : erroresValidacion;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping
    public Result getAllRegistros() {
        Result result = new Result();
        try {

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
