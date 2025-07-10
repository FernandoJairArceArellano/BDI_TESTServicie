package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.Service.NodoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nodos/v1")
public class NodoRestController {

    @Autowired
    private NodoService nodoService;

    @GetMapping("/entrega")
    public Result getAllNodoEntrega() {
        Result result = new Result();
        try {
            List<NodoEntrega> nodoEntrega = nodoService.getAllEntrega();
            result.correct = true;
            result.objects = List.copyOf(nodoEntrega);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
    
    @GetMapping("/recepccion")
    public Result getAllNodoRecepccion() {
        Result result = new Result();
        try {
            List<NodoRecepccion> nodoRecepccion = nodoService.getAllRecepccion();
            result.correct = true;
            result.objects = List.copyOf(nodoRecepccion);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
