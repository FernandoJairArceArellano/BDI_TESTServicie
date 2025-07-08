package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import com.BDI_TESTServicie.JpaRepository.NodoRecepccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodoService {

    @Autowired
    private NodoEntregaRepository nodoEntregaRepository;

    @Autowired
    private NodoRecepccionRepository nodoRecepccionRepository;

    public Result<?> addNodoRecepcion(String codigoNodo) {
        Result result = new Result();
        try {
            NodoRecepccion nodoRecepccion = new NodoRecepccion();
            nodoRecepccion.setCodigoNodo(codigoNodo);
            nodoRecepccionRepository.save(nodoRecepccion);
            result.correct = true;
            return result;
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }

    public Result<?> addNodoEntrega(String codigoNodo) {
        Result result = new Result();
        try {
            NodoEntrega nodoEntrega = new NodoEntrega();
            nodoEntrega.setCodigoNodo(codigoNodo);
            nodoEntregaRepository.save(nodoEntrega);
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
