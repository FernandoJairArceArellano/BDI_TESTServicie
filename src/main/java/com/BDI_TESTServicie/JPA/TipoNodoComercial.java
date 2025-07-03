package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_TipoNodoComercial")
@Schema(description = "Entidad que representa el tipo de Comercio", examples = {"Recepcion", "Entreg"})
public class TipoNodoComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico del Tipo de nodo", examples = {"1", "2"})
    private int idTipoNodo;

    @Schema(description = "Nombre del tipo de Comercio", example = "Recepcion")
    private String nombre;

    public int getIdTipoNodo() {
        return idTipoNodo;
    }

    public void setIdTipoNodo(int idTipoNodo) {
        this.idTipoNodo = idTipoNodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
