package com.BDI_TESTServicie.RestController;

import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario/v1")
public class UsuarioRestController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    
    
}
