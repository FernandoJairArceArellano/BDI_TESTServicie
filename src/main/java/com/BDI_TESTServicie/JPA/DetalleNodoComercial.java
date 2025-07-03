package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_DetalleNodoComercial")
public class DetalleNodoComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleNodoComercial;

    @JoinColumn(name = "idNodoRecepcion")
    @OneToOne
    private NodoComercial nodoRecepcion;

    @JoinColumn(name = "idNodoEntrega")
    @OneToOne
    private NodoComercial nodoEntrega;

    public int getIdDetalleNodoComercial() {
        return idDetalleNodoComercial;
    }

    public void setIdDetalleNodoComercial(int idDetalleNodoComercial) {
        this.idDetalleNodoComercial = idDetalleNodoComercial;
    }

    public NodoComercial getNodoRecepcion() {
        return nodoRecepcion;
    }

    public void setNodoRecepcion(NodoComercial nodoRecepcion) {
        this.nodoRecepcion = nodoRecepcion;
    }

    public NodoComercial getNodoEntrega() {
        return nodoEntrega;
    }

    public void setNodoEntrega(NodoComercial nodoEntrega) {
        this.nodoEntrega = nodoEntrega;
    }

}
