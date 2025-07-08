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
import java.math.BigDecimal;

@Entity
@Table(name = "UGTP_TBL_Transaccion")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDOPERACION")
    private int idOperacion;

    @ManyToOne
    @JoinColumn(name = "IDCONTRATO")
    private Contrato contrato;

    @OneToOne
    @JoinColumn(name = "NODORECEPCION")
    private NodoRecepccion nodoRecepcion;

    @OneToOne
    @JoinColumn(name = "NODOENTREGA")
    private NodoEntrega nodoEntrega;

    @Column(name = "CANTIDADASIGNADAENTREGA")
    private BigDecimal cantidadAsignadaEntregada;

    @Column(name = "CANTIDADASIGNADARECEPCION")
    private BigDecimal cantidadAsignadaRecepcion;

    @Column(name = "CANTIDADNOMINALENTREGA")
    private BigDecimal cantidadNominalEntregada;

    @Column(name = "CANTIDADNOMINALRECEPCION")
    private BigDecimal cantidadNominalRecepcion;

    @Column(name = "GASENEXCESO")
    private BigDecimal gasEnExceso;

    @Column(name = "CARGOUSO")
    private BigDecimal cargoUso;

    @Column(name = "CARGOGASENEXCESO")
    private BigDecimal cargoGasEnExceso;

    @Column(name = "TARIFAEXCESO")
    private BigDecimal tarifaExceso;

    @Column(name = "TARIFAUSOITERRUMPIBLE")
    private BigDecimal tarifaUsoIterrumpible;

    @Column(name = "TOTALAFACTURAR")
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
