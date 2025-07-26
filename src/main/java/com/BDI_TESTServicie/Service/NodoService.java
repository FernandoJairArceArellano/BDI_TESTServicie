package com.BDI_TESTServicie.Service;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepcion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.BDI_TESTServicie.JpaRepository.NodoRecepcionRepository;

@Service
public class NodoService {

    @Autowired
    private NodoEntregaRepository nodoEntregaRepository;

    @Autowired
    private NodoRecepcionRepository nodoRecepccionRepository;

    public Result<?> addNodoRecepcion(String codigoNodo) {
        Result result = new Result();
        try {
            NodoRecepcion nodoRecepccion = new NodoRecepcion();
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

    public List<NodoEntrega> getAllEntrega() {
        return nodoEntregaRepository.findAll();
    }

    public List<NodoRecepcion> getAllRecepccion() {
        return nodoRecepccionRepository.findAll();
    }

    public Result<?> getDetalleNodoComercial(String codigoNodo) {
        Result<?> result = new Result();
        char evaluarCodigoNodo = codigoNodo.charAt(0);
        try {
            if (evaluarCodigoNodo == 'V') {
                result.correct = true;
                System.out.println("Se entro en los nodos de Recepcción\nLetra evaluada: " + evaluarCodigoNodo);
                NodoRecepcion nodoRecepccion = nodoRecepccionRepository.findByCodigoNodo(codigoNodo);
                
                
            } else if (evaluarCodigoNodo == 'E') {
                result.correct = true;
                System.out.println("Se entro en los nodos de Entrega\nLetra evaluada: " + evaluarCodigoNodo);
                NodoEntrega nodoEntrega = nodoEntregaRepository.findByCodigoNodo(codigoNodo);
                
                
            } else {
                result.correct = false;
                result.errorMessage = "No se contro ningun nodo con el codigo: " + codigoNodo;
            }
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
