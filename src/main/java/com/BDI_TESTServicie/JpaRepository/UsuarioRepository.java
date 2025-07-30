package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNombre(String nombre);
    
    Optional<Usuario> findByUsername(String nombre);
    
    boolean existsByUsername(String username);

    @Procedure(name = "busquedaDeUsuariosPorNombreNodoRecepccion")
    List<Usuario> busquedaDeUsuariosPorNombreNodoRecepccion(@Param("pCodigoNodo") String codigoNodo);

    @Procedure(name = "busquedaDeUsuariosPorNombreNodoEntrega")
    List<Usuario> busquedaDeUsuariosPorNombreNodoEntrega(@Param("pCodigoNodo") String codigoNodo);
    
    @Procedure(name = "busquedaUsuarioPorZonaInyeccion")
    List<Usuario> busquedaUsuarioPorZonaInyeccion(@Param("pNombreZona") String nombreZona);
    
    @Procedure(name = "busquedaUsuarioPorZonaExtraccion")
    List<Usuario> busquedaUsuarioPorZonaExtraccion(@Param("pNombreZona") String nombreZona);
}
