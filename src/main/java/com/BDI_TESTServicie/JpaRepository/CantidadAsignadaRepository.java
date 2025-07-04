package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.CantidadAsignada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CantidadAsignadaRepository extends JpaRepository<CantidadAsignada, Integer>{
    
}
