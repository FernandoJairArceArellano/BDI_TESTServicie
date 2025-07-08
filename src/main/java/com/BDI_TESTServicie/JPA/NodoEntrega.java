package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_NodoEntrega")
@Schema(description = "Entidad que representa al nodo de Entrega", examples = {"E168", "N119"})
public class NodoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Identificador unico del nodo de Entrega")
    private int idNodoEntrega;

    private String codigoNodo;

    public int getIdNodoEntrega() {
        return idNodoEntrega;
    }

    public void setIdNodoEntrega(int idNodoEntrega) {
        this.idNodoEntrega = idNodoEntrega;
    }

    public String getCodigoNodo() {
        return codigoNodo;
    }

    public void setCodigoNodo(String codigoNodo) {
        this.codigoNodo = codigoNodo;
    }

}
