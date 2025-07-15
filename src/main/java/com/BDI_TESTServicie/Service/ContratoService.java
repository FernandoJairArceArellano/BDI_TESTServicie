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
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    public Result agregarContrato(Contrato contrato) {
        Result result = new Result();
        try {
            ZonaExtraccion zonaExtraccionBD = zonaExtraccionRepository.findByNombreZona(
                    contrato.getZonaExtraccion().getNombreZona()
            );

            ZonaInyeccion zonaInyeccionBD = zonaInyeccionRepository.findByNombreZona(
                    contrato.getZonaInyeccion().getNombreZona()
            );

            Usuario usuarioBD = usuarioRepository.findById(contrato.getUsuario().getIdUsuario()).orElse(null);

            contrato.setZonaExtraccion(zonaExtraccionBD);
            contrato.setZonaInyeccion(zonaInyeccionBD);
            contrato.setUsuario(usuarioBD);

            contratoRepository.save(contrato);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        return result;
    }

    public Result eliminarContrato(int idContrato) {
        Result result = new Result();
        try {
            if (contratoRepository.existsById(idContrato)) {
                contratoRepository.deleteById(idContrato);
                result.correct = true;
            } else {
                result.correct = false;
                result.errorMessage = "Contrato no encontrado con ID: " + idContrato;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        return result;
    }

    public List<Contrato> getAllContratos() {
        return contratoRepository.findAll();
    }

    public Result<Contrato> obtenerContratoPorCodigo(String codigoContrato) {
        Result<Contrato> result = new Result<>();
        try {
            Contrato contrato = contratoRepository.findByCodigoContrato(codigoContrato);
            if (contrato != null) {
                result.correct = true;
                result.object = contrato;
            } else {
                result.correct = false;
                result.errorMessage = "Contrato no encontrado con código: " + codigoContrato;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }
        return result;
    }

    public List<Contrato> obtenerContratosPorIdUsuario(int idUsuario) {
        return contratoRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public List<Contrato> obtenerContratosPorUsuario(Usuario usuario) {
        return contratoRepository.findByUsuario(usuario);
    }
}
