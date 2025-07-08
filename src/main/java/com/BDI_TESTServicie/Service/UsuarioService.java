package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result agregarUsuario(String nombre) {
        Result result = new Result();

        try {
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuarioRepository.save(usuario);
            result.correct = true;
            return result;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
