package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TarifaRepository extends JpaRepository<Tarifa, Integer>{
    
}
