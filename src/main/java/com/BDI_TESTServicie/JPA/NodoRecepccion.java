package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_NODORECEPCCION")
@Schema(description = "Entidad que representa al nodo de Recepccion", examples = {"V045", "V025"})
public class NodoRecepccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Identificador unico del nodo de Recepccion")
    private int idNodoRecepccion;

    private String codigoNodo;

    public int getIdNodoRecepccion() {
        return idNodoRecepccion;
    }

    public void setIdNodoRecepccion(int idNodoRecepccion) {
        this.idNodoRecepccion = idNodoRecepccion;
    }

    public String getCodigoNodo() {
        return codigoNodo;
    }

    public void setCodigoNodo(String codigoNodo) {
        this.codigoNodo = codigoNodo;
    }

}
