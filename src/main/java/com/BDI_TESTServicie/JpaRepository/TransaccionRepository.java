package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepcion;
import com.BDI_TESTServicie.JPA.Transaccion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    void deleteByContrato_IdContrato(int idContrato);

    List<Transaccion> findByContrato_IdContrato(int idContrato);

    List<Transaccion> findByContrato(Contrato contrato);
//
//    List<Transaccion> findByNodoRecepccion(NodoRecepcion nodoRecepcion);
//
//    List<Transaccion> findByNodoEntrega(NodoEntrega nodoEntrega);
}
