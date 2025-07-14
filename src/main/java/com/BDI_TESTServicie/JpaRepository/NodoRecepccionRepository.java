package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.NodoRecepccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodoRecepccionRepository extends JpaRepository<NodoRecepccion, Integer>{
    NodoRecepccion findByCodigoNodo (String codigoNodo);
}
