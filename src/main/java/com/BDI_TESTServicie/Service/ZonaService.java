package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.ZonaExtraccionRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaInyeccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZonaService {

    @Autowired
    private ZonaExtraccionRepository zonaExtraccionRepository;

    @Autowired
    private ZonaInyeccionRepository zonaInyeccionRepository;

    public Result<?> addZonaExtraccion(String nombreZona) {
        Result result = new Result();
        try {
            ZonaExtraccion zonaExtraccion = new ZonaExtraccion();
            zonaExtraccion.setNombreZona(nombreZona);
            zonaExtraccionRepository.save(zonaExtraccion);
            result.correct = true;
            return result;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public Result<?> addZonaInyeccion(String nombreZona) {
        Result result = new Result();
        try {
            ZonaInyeccion zonaInyeccion = new ZonaInyeccion();
            zonaInyeccion.setNombreZona(nombreZona);
            zonaInyeccionRepository.save(zonaInyeccion);
            result.correct = true;
            return result;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public List<ZonaExtraccion> getAllZonasExtraccion() {
        return zonaExtraccionRepository.findAll();
    }

    public List<ZonaInyeccion> getAllZonasInyeccion() {
        return zonaInyeccionRepository.findAll();
    }
}
