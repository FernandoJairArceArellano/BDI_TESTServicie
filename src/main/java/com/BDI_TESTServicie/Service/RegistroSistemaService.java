package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepcion;
import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ResultFile;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaExtraccionRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaInyeccionRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BDI_TESTServicie.JpaRepository.NodoRecepcionRepository;

@Service
public class RegistroSistemaService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private NodoEntregaRepository nodoEntregaRepository;

    @Autowired
    private NodoRecepcionRepository nodoRecepccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ZonaExtraccionRepository zonaExtraccionRepository;

    @Autowired
    private ZonaInyeccionRepository zonaInyeccionRepository;

    public Result procesarRegistroSistema(RegistroSistema registro) {
        Result result = new Result();

        try {
            // Validar o insertar Usuario
            Usuario usuario = usuarioRepository.findByNombre(registro.usuario.getNombre());
            if (usuario == null) {
                usuario = usuarioRepository.save(registro.usuario);
            }

            // Validar o insertar ZonaInyeccion
            ZonaInyeccion zonaInyeccion = zonaInyeccionRepository.findByNombreZona(registro.zonaInyeccion.getNombreZona());
            if (zonaInyeccion == null) {
                zonaInyeccion = zonaInyeccionRepository.save(registro.zonaInyeccion);
            }

            // Validar o insertar ZonaExtraccion
            ZonaExtraccion zonaExtraccion = zonaExtraccionRepository.findByNombreZona(registro.zonaExtraccion.getNombreZona());
            if (zonaExtraccion == null) {
                zonaExtraccion = zonaExtraccionRepository.save(registro.zonaExtraccion);
            }

            // Validar o insertar NodoEntrega
            NodoEntrega nodoEntrega = nodoEntregaRepository.findByCodigoNodo(registro.nodoEntrega.getCodigoNodo());
            if (nodoEntrega == null) {
                nodoEntrega = nodoEntregaRepository.save(registro.nodoEntrega);
            }

            // Validar o insertar NodoRecepcion
            NodoRecepcion nodoRecepccion = nodoRecepccionRepository.findByCodigoNodo(registro.nodoRecepccion.getCodigoNodo());
            if (nodoRecepccion == null) {
                nodoRecepccion = nodoRecepccionRepository.save(registro.nodoRecepccion);
            }

            // Validar o insertar Contrato
            Contrato contrato = contratoRepository.findByCodigoContrato(registro.contrato.getCodigoContrato());
            if (contrato == null) {
                contrato = registro.contrato;
                contrato.setUsuario(usuario);
                contrato.setCodigoContrato(registro.contrato.getCodigoContrato());
                contrato.setFecha(registro.contrato.getFecha());
                contrato.setZonaExtraccion(zonaExtraccion);
                contrato.setZonaInyeccion(zonaInyeccion);
                contrato = contratoRepository.save(contrato);
            }

            // Guardar Transaccion
            Transaccion transaccion = registro.transaccion;
            transaccion.setContrato(contrato);
            transaccion.setNodoEntrega(nodoEntrega);
            transaccion.setNodoRecepcion(nodoRecepccion);

            transaccion.setCantidadAsignadaEntrega(registro.transaccion.getCantidadAsignadaEntrega());
            transaccion.setCantidadAsignadaRecepcion(registro.transaccion.getCantidadAsignadaRecepcion());
            transaccion.setCantidadNominadaEntrega(registro.transaccion.getCantidadNominadaEntrega());
            transaccion.setCantidadNominadaRecepcion(registro.transaccion.getCantidadNominadaRecepcion());
            transaccion.setGasEnExceso(registro.transaccion.getGasEnExceso());
            transaccion.setCargoUso(registro.transaccion.getCargoUso());
            transaccion.setCargoGasEnExceso(registro.transaccion.getCargoGasEnExceso());
            transaccion.setTarifaExcesoFirme(registro.transaccion.getTarifaExcesoFirme());
            transaccion.setTarifaUsoInterrumpible(registro.transaccion.getTarifaUsoInterrumpible());
            transaccion.setTotalAFacturar(registro.transaccion.getTotalAFacturar());
            transaccionRepository.save(transaccion);

            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result procesarListaRegistros(List<RegistroSistema> registros) {
        Result result = new Result();
        List<ResultFile> errores = new ArrayList<>();

        int fila = 1;
        for (RegistroSistema registro : registros) {
            Result resultProcesarRegistroSistema = procesarRegistroSistema(registro);
            if (!resultProcesarRegistroSistema.correct) {
                errores.add(new ResultFile(fila, "", resultProcesarRegistroSistema.errorMessage));
            }
            fila++;
        }

        if (errores.isEmpty()) {
            result.correct = true;
        } else {
            result.correct = false;
            result.objects = new ArrayList<>(errores);
        }

        return result;
    }

}
