package com.BDI_TESTServicie.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "UGPT_TBL_ZONA_INYECCION")
@Schema(description = "Entidad que representa la Zona de inyeccion", examples = {"Zona 8", "Zona 1"})
public class ZonaInyeccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico de la Zona de inyeccion")
    @Column(name = "IDZONAINYECCION")
    private int idZonaInyeccion;

    @Column(name = "NOMBREZONA")
    private String nombreZona;

    public int getIdZonaInyeccion() {
        return idZonaInyeccion;
    }

    public void setIdZonaInyeccion(int idZonaInyeccion) {
        this.idZonaInyeccion = idZonaInyeccion;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

}
