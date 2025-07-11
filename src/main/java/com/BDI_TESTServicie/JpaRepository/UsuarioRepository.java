package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNombre(String nombre);
}
