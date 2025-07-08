package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaInyeccionRepository extends JpaRepository<ZonaInyeccion, Integer>{
    
}
