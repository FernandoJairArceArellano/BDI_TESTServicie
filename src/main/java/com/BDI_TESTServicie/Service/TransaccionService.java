package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import com.BDI_TESTServicie.JpaRepository.NodoRecepccionRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private NodoEntregaRepository nodoEntregaRepository;

    @Autowired
    private NodoRecepccionRepository nodoRecepccionRepository;

    public Result<?> addTransaccion(String codigoContrato, String codigoNodoEntrega, String codigoNodoRecepcion, Transaccion transaccion) {
        Result result = new Result();

        try {

            Contrato contrato = contratoRepository.findByCodigoContrato(codigoContrato);
            transaccion.setContrato(contrato);
            NodoEntrega nodoEntrega = nodoEntregaRepository.findByCodigoNodo(codigoNodoEntrega);
            transaccion.setNodoEntrega(nodoEntrega);
            NodoRecepccion nodoRecepccion = nodoRecepccionRepository.findByCodigoNodo(codigoNodoRecepcion);
            transaccion.setNodoRecepcion(nodoRecepccion);
            transaccion.setCantidadAsignadaEntregada(BigDecimal.TEN);
            transaccion.setCantidadAsignadaRecepcion(BigDecimal.TEN);
            transaccion.setCantidadNominalEntregada(BigDecimal.TEN);
            transaccion.setCantidadNominalRecepcion(BigDecimal.TEN);
            transaccion.setGasEnExceso(BigDecimal.TEN);
            transaccion.setCargoUso(BigDecimal.TEN);
            transaccion.setCargoGasEnExceso(BigDecimal.TEN);
            transaccion.setTarifaExceso(BigDecimal.TEN);
            transaccion.setTarifaUsoIterrumpible(BigDecimal.TEN);
            transaccion.setTotalAFacturar(BigDecimal.TEN);
            transaccionRepository.save(transaccion);
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
