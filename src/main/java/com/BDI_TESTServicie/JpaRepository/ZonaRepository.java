package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Integer> {

    Zona findByNombreZona(String nombreZona);
}
