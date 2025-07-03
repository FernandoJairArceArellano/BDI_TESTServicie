package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_Transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperacion;

    @JoinColumn(name = "idContrato")
    @OneToOne
    private Contrato contrato;

    @JoinColumn(name = "idDetalleNodoComercial")
    @OneToOne
    private DetalleNodoComercial detalleNodoComercial;

    @JoinColumn(name = "idDetalleZona")
    @OneToOne
    private DetalleZona detalleZona;

    @JoinColumn(name = "idDetalleCantidad")
    @OneToOne
    private DetalleCantidad detalleCantidad;

    @JoinColumn(name = "idDetalleTarifa")
    @OneToOne
    private DetalleTarifa detalleTarifa;

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public DetalleNodoComercial getDetalleNodoComercial() {
        return detalleNodoComercial;
    }

    public void setDetalleNodoComercial(DetalleNodoComercial detalleNodoComercial) {
        this.detalleNodoComercial = detalleNodoComercial;
    }

    public DetalleZona getDetalleZona() {
        return detalleZona;
    }

    public void setDetalleZona(DetalleZona detalleZona) {
        this.detalleZona = detalleZona;
    }

    public DetalleCantidad getDetalleCantidad() {
        return detalleCantidad;
    }

    public void setDetalleCantidad(DetalleCantidad detalleCantidad) {
        this.detalleCantidad = detalleCantidad;
    }

    public DetalleTarifa getDetalleTarifa() {
        return detalleTarifa;
    }

    public void setDetalleTarifa(DetalleTarifa detalleTarifa) {
        this.detalleTarifa = detalleTarifa;
    }

}
