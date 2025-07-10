package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.Service.RegistroSistemaService;
import com.BDI_TESTServicie.Service.TransaccionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacciones/v1")
public class TransaccionRestController {

    @Autowired
    private RegistroSistemaService registroSistemaService;

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/procesar")
    public ResponseEntity<Result> procesarRegistro(@RequestBody RegistroSistema registro) {
        Result result = registroSistemaService.procesarRegistroSistema(registro);
        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @PostMapping("/procesar-masivo")
    public ResponseEntity<Result> procesarLista(@RequestBody List<RegistroSistema> registros) {
        Result result = registroSistemaService.procesarListaRegistros(registros);
        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }

    @GetMapping("/transacciones")
    public Result getAllTransacciones() {
        Result result = new Result();
        try {
            List<Transaccion> transacciones = transaccionService.getAllTransaccion();
            result.correct = true;
            result.objects = List.copyOf(transacciones);
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }
        return result;
    }
}
