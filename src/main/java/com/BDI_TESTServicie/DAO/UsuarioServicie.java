package com.BDI_TESTServicie.DAO;

import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicie {

    @Autowired
    private UsuarioRepository usuarioRepository;
}
