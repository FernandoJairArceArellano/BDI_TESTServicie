package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "UGTP_TBL_Transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperacion;

    @JoinColumn(name = "idContrato")
    @OneToOne
    private Contrato contrato;

    @OneToOne
    private NodoRecepccion nodoRecepcion;

    @OneToOne
    private NodoEntrega nodoEntrega;

    private BigDecimal cantidadAsignadaEntregada;

    private BigDecimal cantidadAsignadaRecepcion;

    private BigDecimal cantidadNominalEntregada;

    private BigDecimal cantidadNominalRecepcion;

    private BigDecimal gasEnExceso;

    private BigDecimal cargoUso;

    private BigDecimal cargoGasEnExceso;

    private BigDecimal tarifaExceso;

    private BigDecimal tarifaUsoIterrumpible;

    private BigDecimal totalAFacturar;

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

    public NodoRecepccion getNodoRecepcion() {
        return nodoRecepcion;
    }

    public void setNodoRecepcion(NodoRecepccion nodoRecepcion) {
        this.nodoRecepcion = nodoRecepcion;
    }

    public NodoEntrega getNodoEntrega() {
        return nodoEntrega;
    }

    public void setNodoEntrega(NodoEntrega nodoEntrega) {
        this.nodoEntrega = nodoEntrega;
    }

    public BigDecimal getCantidadAsignadaEntregada() {
        return cantidadAsignadaEntregada;
    }

    public void setCantidadAsignadaEntregada(BigDecimal cantidadAsignadaEntregada) {
        this.cantidadAsignadaEntregada = cantidadAsignadaEntregada;
    }

    public BigDecimal getCantidadAsignadaRecepcion() {
        return cantidadAsignadaRecepcion;
    }

    public void setCantidadAsignadaRecepcion(BigDecimal cantidadAsignadaRecepcion) {
        this.cantidadAsignadaRecepcion = cantidadAsignadaRecepcion;
    }

    public BigDecimal getCantidadNominalEntregada() {
        return cantidadNominalEntregada;
    }

    public void setCantidadNominalEntregada(BigDecimal cantidadNominalEntregada) {
        this.cantidadNominalEntregada = cantidadNominalEntregada;
    }

    public BigDecimal getCantidadNominalRecepcion() {
        return cantidadNominalRecepcion;
    }

    public void setCantidadNominalRecepcion(BigDecimal cantidadNominalRecepcion) {
        this.cantidadNominalRecepcion = cantidadNominalRecepcion;
    }

    public BigDecimal getGasEnExceso() {
        return gasEnExceso;
    }

    public void setGasEnExceso(BigDecimal gasEnExceso) {
        this.gasEnExceso = gasEnExceso;
    }

    public BigDecimal getCargoUso() {
        return cargoUso;
    }

    public void setCargoUso(BigDecimal cargoUso) {
        this.cargoUso = cargoUso;
    }

    public BigDecimal getCargoGasEnExceso() {
        return cargoGasEnExceso;
    }

    public void setCargoGasEnExceso(BigDecimal cargoGasEnExceso) {
        this.cargoGasEnExceso = cargoGasEnExceso;
    }

    public BigDecimal getTarifaExceso() {
        return tarifaExceso;
    }

    public void setTarifaExceso(BigDecimal tarifaExceso) {
        this.tarifaExceso = tarifaExceso;
    }

    public BigDecimal getTarifaUsoIterrumpible() {
        return tarifaUsoIterrumpible;
    }

    public void setTarifaUsoIterrumpible(BigDecimal tarifaUsoIterrumpible) {
        this.tarifaUsoIterrumpible = tarifaUsoIterrumpible;
    }

    public BigDecimal getTotalAFacturar() {
        return totalAFacturar;
    }

    public void setTotalAFacturar(BigDecimal totalAFacturar) {
        this.totalAFacturar = totalAFacturar;
    }

}
