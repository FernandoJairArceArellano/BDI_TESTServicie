package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.Service.ZonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zonas/v1")
public class ZonasRestController {

    @Autowired
    private ZonaService zonaService;

    @GetMapping("/zonaextraccion")
    public Result getAllZonasExtraccion() {
        Result result = new Result();
        try {
            List<ZonaExtraccion> zonaExtraccion = zonaService.getAllZonasExtraccion();
            result.correct = true;
            result.objects = List.copyOf(zonaExtraccion);
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
            result.correct = true;
            result.objects = List.copyOf(zonaInyeccion);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
