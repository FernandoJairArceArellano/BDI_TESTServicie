package com.BDI_TESTServicie.DAO;

import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicie {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result<Usuario> procesarArchivoExcel(File archivo) {
//        Result<Usuario> result = new Result<>();
//
//        List<Usuario> usuarios = new ArrayList<>();
//
//        try (FileInputStream fis = new FileInputStream(archivo); Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = (Sheet) workbook.getSheetAt(0);
//
//            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row == null) {
//                    continue;
//                }
//
//                Usuario usuario = new Usuario();
//
//                try {
//                    usuario.setNombre(row.getCell(0).getStringCellValue());
//                    usuario.setCorreo(row.getCell(1).getStringCellValue());
//                    usuario.setEdad((int) row.getCell(2).getNumericCellValue());
//
//                    usuarios.add(usuario);
//
//                } catch (Exception ex) {
//                    result.correct = false;
//                    result.errorMessage = "Error en fila " + (i + 1) + ": " + ex.getMessage();
//                    result.ex = ex;
//                    return result; // Se manda el resultado si salio algun error
//                }
//            }
//
//            usuarioRepository.saveAll(usuarios);
//
//            result.correct = true;
//            result.objects = usuarios;
//
//        } catch (IOException ex) {
//            result.correct = false;
//            result.errorMessage = "No se pudo leer el archivo: " + ex.getMessage();
//            result.ex = ex;
//        }
//
//        return result;
        return null;
    }
}
