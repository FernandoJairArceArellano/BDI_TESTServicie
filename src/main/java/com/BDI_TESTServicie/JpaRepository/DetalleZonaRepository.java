package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.DetalleZona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleZonaRepository extends JpaRepository<DetalleZona, Integer>{
    
}
