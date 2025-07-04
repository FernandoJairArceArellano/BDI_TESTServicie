package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {
    
}
