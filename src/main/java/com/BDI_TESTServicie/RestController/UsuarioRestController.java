package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.Service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario/v1")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Result getAllUsuarios() {
        Result result = new Result();
        try {
            List<Usuario> usuarios = usuarioService.getAll();
            result.correct = true;
            result.objects = List.copyOf(usuarios);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/updateUsuario")
    public Result actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(usuario);
    }

    @GetMapping("/por-nombre")
    public Result getUsuario(@RequestParam String nombre) {
        return usuarioService.obtenerUsuarioPorNombre(nombre);
    }
}
