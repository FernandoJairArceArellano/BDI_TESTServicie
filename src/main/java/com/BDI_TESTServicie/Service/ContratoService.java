package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaExtraccionRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaInyeccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;
    
    @Autowired
    private TransaccionRepository transaccionRepository;

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
                List<Transaccion> transacciones = transaccionRepository.findByContrato_IdContrato(idContrato);
                
                for(Transaccion transaccion : transacciones){
                    transaccionRepository.delete(transaccion);
                }
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

    public Contrato getById (int idContrato){
        return contratoRepository.findByIdContrato(idContrato);
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

    public Result<Contrato> obtenerContratoPorId(int idContrato) {
        Result<Contrato> result = new Result<>();
        try {
            Contrato contrato = contratoRepository.findByIdContrato(idContrato);
            if (contrato != null) {
                result.correct = true;
                result.object = contrato;
            } else {
                result.correct = false;
                result.errorMessage = "Contrato no encontrado con el ID: " + idContrato;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
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
