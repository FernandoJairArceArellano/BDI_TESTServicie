package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaExtraccionRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaInyeccionRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ZonaExtraccionRepository zonaExtraccionRepository;

    @Autowired
    private ZonaInyeccionRepository zonaInyeccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Result<?> agregarContrato(String codigoContrato, Date fecha, ZonaExtraccion zonaExtraccion, ZonaInyeccion zonaInyeccion, Usuario usuario) {
        Result result = new Result();
        try {
            Contrato contrato = new Contrato();
            contrato.setCodigoContrato(codigoContrato);
            contrato.setFecha(fecha);

            ZonaExtraccion zonaExtraccionBD = zonaExtraccionRepository.findByNombreZona(zonaExtraccion.getNombreZona());
            ZonaInyeccion zonaInyeccionBD = zonaInyeccionRepository.findByNombreZona(zonaInyeccion.getNombreZona());
            Usuario usuarioBD = usuarioRepository.findByNombre(usuario.getNombre());

            contrato.setZonaExtraccion(zonaExtraccionBD);
            contrato.setZonaInyeccion(zonaInyeccionBD);
            contrato.setUsuario(usuarioBD);

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

    public List<Contrato> getAllContratos() {
        return contratoRepository.findAll();
    }

    public List<Contrato> obtenerContratosPorIdUsuario(int idUsuario) {
        return contratoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public List<Contrato> obtenerContratosPorUsuario(Usuario usuario) {
        return contratoRepository.findByUsuario(usuario);
    }
}
