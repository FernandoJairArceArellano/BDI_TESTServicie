package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.DetalleTarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleTarifaRepository extends JpaRepository<DetalleTarifa, Integer>{
    
}
