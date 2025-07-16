package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Result agregarUsuario(Usuario usuario) {
        Result result = new Result();

        try {
            usuarioRepository.save(usuario);
            result.correct = true;
            result.object = usuario;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Result<Usuario> obtenerUsuarioPorNombre(String nombre) {
        Result<Usuario> result = new Result<>();
        try {
            Usuario usuario = usuarioRepository.findByNombre(nombre);
            if (usuario != null) {
                result.correct = true;
                result.object = usuario;
            } else {
                result.correct = false;
                result.errorMessage = "Usuario no encontrado con el nombre de: " + nombre;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public Result actualizarUsuario(Usuario usuario) {
        Result result = new Result();
        try {
            Usuario usuarioBuscado = usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
            if (usuarioBuscado != null) {
                usuarioBuscado.setNombre(usuario.getNombre());
                usuarioRepository.save(usuarioBuscado);
                result.correct = true;
                result.object = usuarioBuscado;
            } else {
                result.correct = false;
                result.errorMessage = "Usuario no encontrado";
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public Result borrarUsuario(int idUsuario) {
        Result result = new Result();
        try {
            if (usuarioRepository.existsById(idUsuario)) {
                //System.out.println("Usuario encontrado con el ID:" + idUsuario);
                List<Contrato> contratos = contratoRepository.findByUsuario_IdUsuario(idUsuario);

                for (Contrato contrato : contratos) {
                    //System.out.println("Borrando: " + contrato);
                    transaccionRepository.deleteByContrato_IdContrato(contrato.getIdContrato());

                    contratoRepository.deleteById(contrato.getIdContrato());
                }
                //System.out.println("Usuario borrado con el ID:" + idUsuario);
                usuarioRepository.deleteById(idUsuario);
                result.correct = true;
            } else {
                result.correct = false;
                result.errorMessage = "Usuario no encontrado con ID: " + idUsuario;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
