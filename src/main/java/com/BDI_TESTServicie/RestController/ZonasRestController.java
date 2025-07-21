package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.Service.ContratoService;
import com.BDI_TESTServicie.Service.UsuarioService;
import com.BDI_TESTServicie.Service.ZonaService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zonas/v1")
@CrossOrigin(origins = "http://localhost:8080")
public class ZonasRestController {

    @Autowired
    private ZonaService zonaService;

    @Autowired
    private ContratoService contratoService;

    @Autowired
    private UsuarioService usuarioServicie;

    @GetMapping("/zonaextraccion")
    public Result getAllZonasExtraccion() {
        Result result = new Result();
        try {
            List<ZonaExtraccion> zonaExtraccion = zonaService.getAllZonasExtraccion();
            List<ZonaExtraccion> zonasOrdenadas = zonaExtraccion.stream()
                    .sorted(Comparator.comparing(ZonaExtraccion::getNombreZona))
                    .collect(Collectors.toList());
            result.correct = true;
            result.objects = List.copyOf(zonasOrdenadas);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    @GetMapping("/zonainyeccion")
    public Result getAllZonasInyeccion() {
        Result result = new Result();
        try {
            List<ZonaInyeccion> zonaInyeccion = zonaService.getAllZonasInyeccion();
            List<ZonaInyeccion> zonasOrdenadas = zonaInyeccion.stream()
                    .sorted(Comparator.comparing(ZonaInyeccion::getNombreZona))
                    .collect(Collectors.toList());
            result.correct = true;
            result.objects = List.copyOf(zonasOrdenadas);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

}
