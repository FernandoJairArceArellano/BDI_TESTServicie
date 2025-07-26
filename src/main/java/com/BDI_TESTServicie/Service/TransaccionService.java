package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepcion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BDI_TESTServicie.JpaRepository.NodoRecepcionRepository;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private NodoEntregaRepository nodoEntregaRepository;

    @Autowired
    private NodoRecepcionRepository nodoRecepccionRepository;

    public Result addTransaccion(Transaccion transaccion) {
        Result result = new Result();
        try {
            Contrato contratoBD = contratoRepository.findByCodigoContrato(
                    transaccion.getContrato().getCodigoContrato()
            );
            
            
            transaccion.setContrato(contratoBD);
            NodoEntrega nodoEntrega = nodoEntregaRepository.findByCodigoNodo(
                    transaccion.getNodoEntrega().getCodigoNodo()
            );
            transaccion.setNodoEntrega(nodoEntrega);
            NodoRecepcion nodoRecepccion = nodoRecepccionRepository.findByCodigoNodo(
                    transaccion.getNodoRecepcion().getCodigoNodo()
            );
            transaccion.setNodoRecepcion(nodoRecepccion);
            
            transaccionRepository.save(transaccion);
            result.correct = true;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public List<Transaccion> getAllTransaccion() {
        return transaccionRepository.findAll();
    }

    public List<Transaccion> obtenerTransaccionesPorContrato(Contrato contrato) {
        return transaccionRepository.findByContrato(contrato);
    }
    
//    public List<Transaccion> obtenerTransaccionesPorCodigoNodoEntrega(NodoEntrega nodoEntrega){
//        return transaccionRepository.findByNodoEntrega(nodoEntrega);
//    }
//    
//    public List<Transaccion> obtenerTransaccionesPorCodigoNodoRecepccion(NodoRecepcion nodoRecepcion){
//        return transaccionRepository.findByNodoRecepccion(nodoRecepcion);
//    }
}
