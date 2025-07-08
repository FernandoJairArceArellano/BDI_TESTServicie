package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodoEntregaRepository extends JpaRepository<NodoEntrega, Integer>{
    NodoEntrega findByCodigoNodo (String codigoNodo);
}
