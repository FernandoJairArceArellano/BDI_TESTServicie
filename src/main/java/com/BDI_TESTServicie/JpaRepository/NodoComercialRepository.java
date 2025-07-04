package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.NodoComercial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodoComercialRepository extends JpaRepository<NodoComercial, Integer> {

    NodoComercial findByCodigoNodo(String codigoNodo);
}
