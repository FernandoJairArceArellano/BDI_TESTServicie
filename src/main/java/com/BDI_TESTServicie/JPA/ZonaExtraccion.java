package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGPT_TBL_ZONAEXTRACCION")
@Schema(description = "Entidad que representa la Zona de extraccion", examples = {"Zona 2", "Zona 3"})
public class ZonaExtraccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico de la Zona de Extraccion")
    private int idZonaExtraccion;

    private String nombreZona;

    public int getIdZonaExtraccion() {
        return idZonaExtraccion;
    }

    public void setIdZonaExtraccion(int idZonaExtraccion) {
        this.idZonaExtraccion = idZonaExtraccion;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }
}
