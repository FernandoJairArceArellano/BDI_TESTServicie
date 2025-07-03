package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_DetalleZona")
public class DetalleZona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleZona;

    @JoinColumn(name = "idZonaInyeccion")
    @OneToOne
    private Zona zonaInyeccion;

    @JoinColumn(name = "idZonaExtraccion")
    @OneToOne
    private Zona zonaExtraccion;

    public int getIdDetalleZona() {
        return idDetalleZona;
    }

    public void setIdDetalleZona(int idDetalleZona) {
        this.idDetalleZona = idDetalleZona;
    }

    public Zona getZonaInyeccion() {
        return zonaInyeccion;
    }

    public void setZonaInyeccion(Zona zonaInyeccion) {
        this.zonaInyeccion = zonaInyeccion;
    }

    public Zona getZonaExtraccion() {
        return zonaExtraccion;
    }

    public void setZonaExtraccion(Zona zonaExtraccion) {
        this.zonaExtraccion = zonaExtraccion;
    }

}
