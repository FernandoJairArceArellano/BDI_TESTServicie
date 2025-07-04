package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.DetalleCantidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCantidadRepository extends JpaRepository<DetalleCantidad, Integer>{
    
}
