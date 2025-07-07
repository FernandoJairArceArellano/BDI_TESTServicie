package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.TipoZona;
import com.BDI_TESTServicie.JpaRepository.TipoZonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoZonaService {

    @Autowired
    private TipoZonaRepository tipoZonaRepository;

    public List<TipoZona> obtenerZonas() {
        return tipoZonaRepository.findAll();
    }
}
