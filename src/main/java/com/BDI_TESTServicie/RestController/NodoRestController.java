package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepcion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.Service.NodoService;
import com.BDI_TESTServicie.Service.UsuarioService;
import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nodos/v1")
@CrossOrigin(origins = "http://localhost:8080")
public class NodoRestController {

    @Autowired
    private NodoService nodoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/entrega")
    public Result getAllNodoEntrega() {
        Result result = new Result();
        try {
            List<NodoEntrega> nodoEntrega = nodoService.getAllEntrega();
            List<NodoEntrega> nodosOrdenados = nodoEntrega.stream()
                    .sorted(Comparator.comparing(NodoEntrega::getCodigoNodo))
                    .collect(Collectors.toList());
            result.correct = true;
            result.objects = List.copyOf(nodosOrdenados);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @GetMapping("/recepccion")
    public Result getAllNodoRecepccion() {
        Result result = new Result();
        try {
            List<NodoRecepcion> nodoRecepccion = nodoService.getAllRecepccion();
            List<NodoRecepcion> nodosOrdenados = nodoRecepccion.stream()
                    .sorted(Comparator.comparing(NodoRecepcion::getCodigoNodo))
                    .collect(Collectors.toList());
            result.correct = true;
            result.objects = List.copyOf(nodosOrdenados);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @GetMapping("/detalleNodo")
    public Result detalleNodoComercial(@RequestParam String codigoNodo) {
        return nodoService.getDetalleNodoComercial(codigoNodo);
    }

    @Transactional
    @GetMapping("/buscarPorNodoRecepccion")
    public Result buscarPorNodoRecepccion(@RequestParam String codigoNodo) {
        Result result = new Result();
        try {
            List<Usuario> usuarios = usuarioService.getUsuariosPorNodoRecepccion(codigoNodo);
            if (!usuarios.isEmpty()) {
                List<Usuario> usuariosOrdenadosStream = usuarios.stream()
                        .sorted(Comparator.comparing(Usuario::getIdUsuario))
                        .collect(Collectors.toList());
                result.correct = true;
                result.objects = usuariosOrdenadosStream;
            } else {
                result.correct = false;
                result.errorMessage = "No se encontraron usuarios para el nodo: " + codigoNodo;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @GetMapping("/buscarPorNodoEntrega")
    public Result buscarPorNodoEntrega(@RequestParam String codigoNodo) {
        Result result = new Result();
        try {
            List<Usuario> usuarios = usuarioService.getUsuariosPorNodoEntrega(codigoNodo);
            if (!usuarios.isEmpty()) {
                List<Usuario> usuariosOrdenadosStream = usuarios.stream()
                        .sorted(Comparator.comparing(Usuario::getIdUsuario))
                        .collect(Collectors.toList());
                result.correct = true;
                result.objects = usuariosOrdenadosStream;
            } else {
                result.correct = false;
                result.errorMessage = "No se encontraron usuarios para el nodo: " + codigoNodo;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @GetMapping("/buscarPorZonaInyeccion")
    public Result buscarPorZonaInyeccion(@RequestParam String nombreZona) {
        Result result = new Result();
        try {
            List<Usuario> usuarios = usuarioService.getUsuarioPorZonaInyeccion(nombreZona);
            if (!usuarios.isEmpty()) {
                List<Usuario> usuariosOrdenadosStream = usuarios.stream()
                        .sorted(Comparator.comparing(Usuario::getIdUsuario))
                        .collect(Collectors.toList());
                result.correct = true;
                result.objects = usuariosOrdenadosStream;
            } else {
                result.correct = false;
                result.errorMessage = "No se encontraron usuarios con la zona: " + nombreZona;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @Transactional
    @GetMapping("/buscarPorZonaExtraccion")
    public Result busquedaUsuarioPorZonaExtraccion(@RequestParam String nombreZona) {
        Result result = new Result();
        try {
            List<Usuario> usuarios = usuarioService.getUsuarioPorZonaExtraccion(nombreZona);
            if (!usuarios.isEmpty()) {
                List<Usuario> usuariosOrdenadosStream = usuarios.stream()
                        .sorted(Comparator.comparing(Usuario::getIdUsuario))
                        .collect(Collectors.toList());
                result.correct = true;
                result.objects = usuariosOrdenadosStream;
            } else {
                result.correct = false;
                result.errorMessage = "No se encontraron usuarios con la zona: " + nombreZona;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
