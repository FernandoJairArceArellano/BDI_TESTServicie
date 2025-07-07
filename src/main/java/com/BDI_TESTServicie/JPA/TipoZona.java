package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_TIPOZONA")
@Schema(description = "Entidad que representa al tipo de Zona", examples = {"Inyección", "Extracción"})
public class TipoZona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico del Tipo de Zona")
    @Column(name = "IDTIPOZONA")
    private int idTipoZona;

    @Schema(description = "Nombre del tipo de Zona")
    @Column(name = "NOMBRETIPOZONA")
    private String nombreTipoZona;

    public int getIdTipoZona() {
        return idTipoZona;
    }

    public void setIdTipoZona(int idTipoZona) {
        this.idTipoZona = idTipoZona;
    }

    public String getNombreTipoZona() {
        return nombreTipoZona;
    }

    public void setNombreTipoZona(String nombreTipoZona) {
        this.nombreTipoZona = nombreTipoZona;
    }

}
