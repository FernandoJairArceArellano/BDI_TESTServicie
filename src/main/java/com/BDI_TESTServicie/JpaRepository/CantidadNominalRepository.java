package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.CantidadNominal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CantidadNominalRepository extends JpaRepository<CantidadNominal, Integer>{
    
}
