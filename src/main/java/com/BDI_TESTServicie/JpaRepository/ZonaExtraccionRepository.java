package com.BDI_TESTServicie.JpaRepository;

import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaExtraccionRepository extends JpaRepository<ZonaExtraccion, Integer> {

    ZonaExtraccion findByNombreZona(String nombreZona);

}
