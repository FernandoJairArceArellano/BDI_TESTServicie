package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.Service.ContratoService;
import com.BDI_TESTServicie.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contratos/v1")
public class ContratoRestController {

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Result getAllContratos() {
        Result result = new Result();

        try {
            List<Contrato> contratos = contratoService.getAllContratos();
            result.correct = true;
            result.objects = List.copyOf(contratos);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    @GetMapping("/por-usuario-id")
    @CrossOrigin(origins = "http://localhost:8080")
    public Result getContratosPorUsuarioId(@RequestParam int id) {
        Result result = new Result();
        try {
            List<Contrato> contratos = contratoService.obtenerContratosPorIdUsuario(id);
            result.correct = true;
            result.objects = contratos;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        return result;
    }

    @GetMapping("/por-usuario-nombre")
    @CrossOrigin(origins = "http://localhost:8080")
    public Result getContratosPorNombreUsuario(@RequestParam String nombre) {
        Result result = new Result();
        try {
            Usuario usuario = usuarioService.getByNombre(nombre);
            if (usuario != null) {
                List<Contrato> contratos = contratoService.obtenerContratosPorUsuario(usuario);
                result.correct = true;
                result.objects = contratos;
            } else {
                result.correct = false;
                result.errorMessage = "Usuario no encontrado con nombre: " + nombre;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        return result;
    }
}
