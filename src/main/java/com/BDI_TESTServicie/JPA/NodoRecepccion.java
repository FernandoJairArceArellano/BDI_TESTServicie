package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_NODO_RECEPCCION")
@Schema(description = "Entidad que representa al nodo de Recepccion", examples = {"V045", "V025"})
public class NodoRecepccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "Identificador unico del nodo de Recepccion")
    @Column(name = "IDNODORECEPCCION")
    private int idNodoRecepccion;

    @Column(name = "CODIGONODO")
    private String codigoNodo;

    @Column(name = "NOMBRENODOCOMERCIAL")
    private String nombreNodoComercial;

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

    public String getNombreNodoComercial() {
        return nombreNodoComercial;
    }

    public void setNombreNodoComercial(String nombreNodoComercial) {
        this.nombreNodoComercial = nombreNodoComercial;
    }

}
