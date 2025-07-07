package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.TipoZona;
import com.BDI_TESTServicie.Service.TipoZonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tipoZona/v1")
@CrossOrigin(origins = "http://localhost:8080") // Permitir acceso desde el frontend
public class TipoZonaRestController {

    @Autowired
    private TipoZonaService tipoZonaService;
    
    @GetMapping
    public List<TipoZona> obtenerZonas(){
        return tipoZonaService.obtenerZonas();
    }
}
