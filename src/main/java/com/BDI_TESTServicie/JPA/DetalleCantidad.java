package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_DetalleCantidad")
public class DetalleCantidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleCantidad;

    @JoinColumn(name = "idCantidadAsignadaEntrega")
    @OneToOne
    private CantidadAsignada cantidadAsignadaEntregada;

    @JoinColumn(name = "idCantidadAsignadaRecepcion")
    @OneToOne
    private CantidadAsignada cantidadAsignadaRecepcion;

    @JoinColumn(name = "idCantidadNominalRecepcion")
    @OneToOne
    private CantidadNominal cantidadNominalRecepcion;

    @JoinColumn(name = "idCantidadNominalEntrega")
    @OneToOne
    private CantidadNominal cantidadNominalEntregada;

    public int getIdDetalleCantidad() {
        return idDetalleCantidad;
    }

    public void setIdDetalleCantidad(int idDetalleCantidad) {
        this.idDetalleCantidad = idDetalleCantidad;
    }

    public CantidadAsignada getCantidadAsignadaEntregada() {
        return cantidadAsignadaEntregada;
    }

    public void setCantidadAsignadaEntregada(CantidadAsignada cantidadAsignadaEntregada) {
        this.cantidadAsignadaEntregada = cantidadAsignadaEntregada;
    }

    public CantidadAsignada getCantidadAsignadaRecepcion() {
        return cantidadAsignadaRecepcion;
    }

    public void setCantidadAsignadaRecepcion(CantidadAsignada cantidadAsignadaRecepcion) {
        this.cantidadAsignadaRecepcion = cantidadAsignadaRecepcion;
    }

    public CantidadNominal getCantidadNominalRecepcion() {
        return cantidadNominalRecepcion;
    }

    public void setCantidadNominalRecepcion(CantidadNominal cantidadNominalRecepcion) {
        this.cantidadNominalRecepcion = cantidadNominalRecepcion;
    }

    public CantidadNominal getCantidadNominalEntregada() {
        return cantidadNominalEntregada;
    }

    public void setCantidadNominalEntregada(CantidadNominal cantidadNominalEntregada) {
        this.cantidadNominalEntregada = cantidadNominalEntregada;
    }

}
