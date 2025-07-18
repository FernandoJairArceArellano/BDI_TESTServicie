package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.Service.NodoService;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nodos/v1")
@CrossOrigin(origins = "http://localhost:8080")
public class NodoRestController {

    @Autowired
    private NodoService nodoService;

    @GetMapping("/entrega")
    public Result getAllNodoEntrega() {
        Result result = new Result();
        try {
            List<NodoEntrega> nodoEntrega = nodoService.getAllEntrega();
            List<NodoEntrega> nodosOrdenados = nodoEntrega.stream()
                    .sorted(Comparator.comparing(NodoEntrega::getCodigoNodo))
                    .collect(Collectors.toList());
            result.correct = true;
            result.objects = List.copyOf(nodosOrdenados);
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
            List<NodoRecepccion> nodosOrdenados = nodoRecepccion.stream()
                    .sorted(Comparator.comparing(NodoRecepccion::getCodigoNodo))
                    .collect(Collectors.toList());
            result.correct = true;
            result.objects = List.copyOf(nodosOrdenados);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
