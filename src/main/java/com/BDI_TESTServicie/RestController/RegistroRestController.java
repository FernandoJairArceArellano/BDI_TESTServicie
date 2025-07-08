package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ResultFile;
import com.BDI_TESTServicie.JPA.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/registro/v1")
public class RegistroRestController {

//    @PostMapping("cargaMasiva")
//    public ResponseEntity CargaMasiva(@RequestParam("archivo") MultipartFile archivo) {
//        Result result = new Result();
//        if (!archivo.isEmpty() || archivo != null) {
//
//            try {
//                String tipoArchivo = archivo.getOriginalFilename().split("\\.")[1];
//
//                String root = System.getProperty("user.dir"); //Obtener direccion del proyecto en el equipo
//                String path = "src/main/resources/static/archivos"; //Path relativo dentro del proyecto
//                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmSS"));
//                String absolutePath = root + "/" + path + "/" + fecha + archivo.getOriginalFilename();
//                archivo.transferTo(new File(absolutePath));
//                //Leer el archivo
//                List<RegistroSistema> listaRegistro = new ArrayList();
//
//                if (tipoArchivo.equals("xlsx")) {
//                    listaRegistro = LecturaArchivoExcel(new File(absolutePath));
//                } else {
//                    // Regresar mensaje de error de archivo no soportado por el sistema
//                }
//
//                //Validar el archivo
//                //List<ResultFile> listaErrores = new ArrayList<>();
//                List<ResultFile> listaErrores = ValidarArchivo(listaRegistro);
//
//                if (listaErrores.isEmpty()) {
//                    //Proceso mi archivo
//                    result.correct = true;
//                    result.object = absolutePath;
//                    return ResponseEntity.ok(result); //ResultFile o Result?
//                } else {
//                    result.correct = false;
//                    result.objects = new ArrayList();
//
//                    for (ResultFile error : listaErrores) {
//                        result.objects.add(error);
//                    }
//
//                    return ResponseEntity.status(400).body(result);
//                }
//
//            } catch (Exception ex) {
//                return ResponseEntity.status(500).body("Algo fallo al procesar el archivo");
//            }
//
//        } else {
//            result.correct = false;
//            return ResponseEntity.status(400).body(result);
//
//        }
//
//    }
//
//    public List<RegistroSistema> LecturaArchivoExcel(File archivo) {
//        List<RegistroSistema> listaRegistro = new ArrayList<>();
//        try (XSSFWorkbook workbook = new XSSFWorkbook(archivo);) {
//            for (Sheet sheet : workbook) {
//                for (Row row : sheet) {
//                    RegistroSistema registroSistema = new RegistroSistema();
//                    usuarioDireccion.Usuario = new Usuario();
//                    usuarioDireccion.Usuario.setNombre(row.getCell(0).toString());
//                    usuarioDireccion.Usuario.setApellidoPaterno(row.getCell(1).toString());
//                    usuarioDireccion.Usuario.setApellidoMaterno(row.getCell(2).toString());
//                    usuarioDireccion.Usuario.setImagen(null);
//
//                    if (DateUtil.isCellDateFormatted(row.getCell(4))) {
//                        usuarioDireccion.Usuario.setFNacimiento(row.getCell(4).getDateCellValue());
//                    } else {
//                        double fechaTexto = row.getCell(4).getNumericCellValue();
//                        Date formato = DateUtil.getJavaDate(fechaTexto);
//                        usuarioDireccion.Usuario.setFNacimiento(formato);
//                    }
//
//                    usuarioDireccion.Usuario.setNCelular(row.getCell(5).toString());
//
//                    usuarioDireccion.Usuario.Rol = new Rol();
//                    usuarioDireccion.Usuario.Rol.setIdRol(Integer.parseInt(row.getCell(6).toString()));
//                    //usuarioDireccion.Usuario.Rol.setIdRol(Integer.parseInt(row.getCell(6).getStringCellValue()));
//                    usuarioDireccion.Usuario.setCURP(row.getCell(7).toString());
//                    usuarioDireccion.Usuario.setUsername(row.getCell(8).toString());
//                    usuarioDireccion.Usuario.setEmail(row.getCell(9).toString());
//                    usuarioDireccion.Usuario.setPassword(row.getCell(10).toString());
//                    usuarioDireccion.Usuario.setSexo(row.getCell(11).toString().charAt(0));
//                    usuarioDireccion.Usuario.setTelefono(row.getCell(12).toString());
//                    usuarioDireccion.Direccion.setCalle(row.getCell(13).toString());
//                    usuarioDireccion.Direccion.setNumeroExterior(row.getCell(14).toString());
//                    usuarioDireccion.Direccion.setNumeroInterior(row.getCell(15).toString());
//                    usuarioDireccion.Direccion.Colonia = new Colonia();
//                    usuarioDireccion.Direccion.Colonia.setIdColonia(Integer.parseInt(row.getCell(16).toString()));
//                    usuarioDireccion.Usuario.setStatus(Integer.parseInt(row.getCell(17).toString()));
//                    listaRegistro.add(usuarioDireccion);
//                }
//            }
//        } catch (Exception ex) {
//            System.out.println("Error al abrir el archivo");
//        }
//
//        return listaRegistro;
//    }
//
//    public List<ResultFile> ValidarArchivo(List<RegistroSistema> listaUsuarios) {
//        List<ResultFile> listaErrores = new ArrayList<>();
//
//        if (listaUsuarios == null) {
//            listaErrores.add(new ResultFile(0, "La lista es nula", "La lista es nula"));
//        } else if (listaUsuarios.isEmpty()) {
//            listaErrores.add(new ResultFile(0, "La lista está vacía", "La lista está vacía"));
//        } else {
//            int fila = 1;
//            for (UsuarioDireccion usuarioDireccion : listaUsuarios) {
//                String nombre = usuarioDireccion.Usuario.getNombre();
//                String apellidoPaterno = usuarioDireccion.Usuario.getApellidoPaterno();
//                String apellidoMaterno = usuarioDireccion.Usuario.getApellidoMaterno();
//                Date fNacimiento = usuarioDireccion.Usuario.getFNacimiento();
//                String nCelular = usuarioDireccion.Usuario.getNCelular();
//                int idRol = usuarioDireccion.Usuario.Rol.getIdRol();
//                String curp = usuarioDireccion.Usuario.getCURP();
//                String userName = usuarioDireccion.Usuario.getUsername();
//                String email = usuarioDireccion.Usuario.getEmail();
//                String password = usuarioDireccion.Usuario.getPassword();
//                char sexo = usuarioDireccion.Usuario.getSexo();
//                String telefono = usuarioDireccion.Usuario.getTelefono();
//                int status = usuarioDireccion.Usuario.getStatus();
//                //String imagen = usuarioDireccion.Usuario.getImagen();
//
//                if (nombre == null || nombre.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, nombre, "El nombre es obligatorio"));
//                }
//
//                if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, apellidoPaterno, "El apellido paterno es obligatorio"));
//                }
//
//                if (apellidoMaterno == null || apellidoMaterno.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, apellidoMaterno, "El apellido materno es obligatorio"));
//                }
//
//                if (fNacimiento == null) {
//                    listaErrores.add(new ResultFile(fila, "", "La fecha de nacimiento es obligatoria"));
//                }
//
//                if (nCelular == null || nCelular.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, nCelular, "El número de celular es obligatorio"));
//                } else if (!nCelular.matches("^\\d{10}$")) {
//                    listaErrores.add(new ResultFile(fila, nCelular, "El número de celular debe tener 10 dígitos"));
//                }
//
//                if (curp == null || curp.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, curp, "El CURP es obligatorio"));
//                }
//
//                if (userName == null || userName.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, userName, "El nombre de usuario es obligatorio"));
//                }
//
//                if (email == null || email.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, email, "El correo electrónico es obligatorio"));
//                }
//
//                if (password == null || password.trim().isEmpty()) {
//                    listaErrores.add(new ResultFile(fila, password, "La contraseña es obligatoria"));
//                }
//
//                if (sexo != 'M' && sexo != 'F') {
//                    listaErrores.add(new ResultFile(fila, String.valueOf(sexo), "El sexo debe ser 'M' o 'F'"));
//                }
//
//                if (telefono != null && !telefono.trim().isEmpty() && !telefono.matches("^\\d{10}$")) {
//                    listaErrores.add(new ResultFile(fila, telefono, "El teléfono debe tener 10 dígitos si se proporciona"));
//                }
//
//                if (idRol <= 0) {
//                    listaErrores.add(new ResultFile(fila, String.valueOf(idRol), "El rol debe ser un ID válido"));
//                }
//
//                if (status != 0 && status != 1) {
//                    listaErrores.add(new ResultFile(fila, String.valueOf(status), "El status debe ser 0 (inactivo) o 1 (activo)"));
//                }
//
//                fila++;
//            }
//        }
//        return listaErrores;
//    }
//
//    // Procesar archivo
//    @PostMapping("/CargaMasiva/Procesar")
//    public ResponseEntity<Result> ProcesarArchivo(@RequestBody String absolutePath) {
//        Result result = new Result();
//
//        try {
//            String tipoArchivo = absolutePath.split("\\.")[1];
//            List<UsuarioDireccion> listaUsuarios = new ArrayList();
//
//            if (tipoArchivo.equals("txt")) {
//                // Metodo para leer una lista
//                listaUsuarios = LecturaArchivoTXT(new File(absolutePath));
//            } else {
//                listaUsuarios = LecturaArchivoExcel(new File(absolutePath));
//            }
//
//            for (UsuarioDireccion usuarioDireccion : listaUsuarios) {
//                System.out.println("Estoy agregando un nuevo usuario y direccion");
//                usuarioDAOImplementation.AddJPA(usuarioDireccion);
//            }
//            result.correct = true;
//
//        } catch (Exception ex) {
//            result.correct = false;
//        }
//
//        return ResponseEntity.ok(result);
//    }
}
