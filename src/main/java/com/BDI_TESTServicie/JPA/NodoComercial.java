package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_NodoComercial")
public class NodoComercial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNodo;

    private String codigoNodo;

    private String nombre;

    @JoinColumn(name = "tipo")
    @OneToOne
    private TipoNodoComercial tipoNodoCOmercial;

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public String getCodigoNodo() {
        return codigoNodo;
    }

    public void setCodigoNodo(String codigoNodo) {
        this.codigoNodo = codigoNodo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoNodoComercial getTipoNodoCOmercial() {
        return tipoNodoCOmercial;
    }

    public void setTipoNodoCOmercial(TipoNodoComercial tipoNodoCOmercial) {
        this.tipoNodoCOmercial = tipoNodoCOmercial;
    }

}
