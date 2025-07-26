package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.NodoRecepcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodoRecepcionRepository extends JpaRepository<NodoRecepcion, Integer> {

    NodoRecepcion findByCodigoNodo(String codigoNodo);

}
