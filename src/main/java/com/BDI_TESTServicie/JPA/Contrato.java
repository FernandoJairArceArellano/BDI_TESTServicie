package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "UGTP_TBL_Contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContrato;

    @Column(name = "CODIGOCONTRATO")
    private String codigoContrato;

    private Date fecha;

    @JoinColumn(name = "zonaExtraccion")
    @OneToOne
    private ZonaExtraccion zonaExtraccion;

    @JoinColumn(name = "zonaInyeccion")
    @OneToOne
    private ZonaInyeccion zonaInyeccion;

    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario usuario;

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public String getCodigoContrato() {
        return codigoContrato;
    }

    public void setCodigoContrato(String codigoContrato) {
        this.codigoContrato = codigoContrato;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ZonaExtraccion getZonaExtraccion() {
        return zonaExtraccion;
    }

    public void setZonaExtraccion(ZonaExtraccion zonaExtraccion) {
        this.zonaExtraccion = zonaExtraccion;
    }

    public ZonaInyeccion getZonaInyeccion() {
        return zonaInyeccion;
    }

    public void setZonaInyeccion(ZonaInyeccion zonaInyeccion) {
        this.zonaInyeccion = zonaInyeccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
