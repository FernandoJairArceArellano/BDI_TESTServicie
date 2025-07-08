package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {
    
    @Autowired
    private ContratoRepository contratoRepository;
    
    public Result<?> agregarContrato(String codigoContrato, Date fecha, ZonaExtraccion zonaExtraccion, ZonaInyeccion zonaInyeccion, Usuario usuario) {
        Result result = new Result();
        try {
            Contrato contrato = new Contrato();
            contrato.setCodigoContrato(codigoContrato);
            contrato.setFecha(fecha);
            zonaExtraccion.setIdZonaExtraccion(zonaExtraccion.getIdZonaExtraccion());
            zonaInyeccion.setIdZonaInyeccion(zonaInyeccion.getIdZonaInyeccion());
            usuario.setIdUsuario(usuario.getIdUsuario());
            contratoRepository.save(contrato);
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
