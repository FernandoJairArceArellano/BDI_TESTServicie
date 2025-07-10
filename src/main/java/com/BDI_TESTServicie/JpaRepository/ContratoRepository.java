package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    Contrato findByCodigoContrato(String codigoContrato);

    List<Contrato> findByUsuario_IdUsuario(int idUsuario);

    List<Contrato> findByUsuario(Usuario usuario);
}
