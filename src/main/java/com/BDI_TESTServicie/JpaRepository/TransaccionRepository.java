package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Integer>{
    void deleteByContrato_IdContrato(int idContrato);
}
