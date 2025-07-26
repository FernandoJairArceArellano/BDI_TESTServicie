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
    private NodoRecepcion nodoRecepcion;

    @OneToOne
    @JoinColumn(name = "NODOENTREGA")
    private NodoEntrega nodoEntrega;

    @Column(name = "CANTIDADASIGNADAENTREGA")
    private BigDecimal cantidadAsignadaEntrega;

    @Column(name = "CANTIDADASIGNADARECEPCION")
    private BigDecimal cantidadAsignadaRecepcion;

    @Column(name = "CANTIDADNOMINADAENTREGA")
    private BigDecimal cantidadNominadaEntrega;

    @Column(name = "CANTIDADNOMINADARECEPCION")
    private BigDecimal cantidadNominadaRecepcion;

    @Column(name = "GASENEXCESO")
    private BigDecimal gasEnExceso;

    @Column(name = "CARGOUSO")
    private BigDecimal cargoUso;

    @Column(name = "CARGOGASENEXCESO")
    private BigDecimal cargoGasEnExceso;

    @Column(name = "TARIFAEXCESOFIRME")
    private BigDecimal tarifaExcesoFirme;

    @Column(name = "TARIFAUSOINTERRUMPIBLE")
    private BigDecimal tarifaUsoInterrumpible;

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

    public NodoRecepcion getNodoRecepcion() {
        return nodoRecepcion;
    }

    public void setNodoRecepcion(NodoRecepcion nodoRecepcion) {
        this.nodoRecepcion = nodoRecepcion;
    }

    public NodoEntrega getNodoEntrega() {
        return nodoEntrega;
    }

    public void setNodoEntrega(NodoEntrega nodoEntrega) {
        this.nodoEntrega = nodoEntrega;
    }

    public BigDecimal getCantidadAsignadaEntrega() {
        return cantidadAsignadaEntrega;
    }

    public void setCantidadAsignadaEntrega(BigDecimal cantidadAsignadaEntrega) {
        this.cantidadAsignadaEntrega = cantidadAsignadaEntrega;
    }

    public BigDecimal getCantidadAsignadaRecepcion() {
        return cantidadAsignadaRecepcion;
    }

    public void setCantidadAsignadaRecepcion(BigDecimal cantidadAsignadaRecepcion) {
        this.cantidadAsignadaRecepcion = cantidadAsignadaRecepcion;
    }

    public BigDecimal getCantidadNominadaEntrega() {
        return cantidadNominadaEntrega;
    }

    public void setCantidadNominadaEntrega(BigDecimal cantidadNominadaEntrega) {
        this.cantidadNominadaEntrega = cantidadNominadaEntrega;
    }

    public BigDecimal getCantidadNominadaRecepcion() {
        return cantidadNominadaRecepcion;
    }

    public void setCantidadNominadaRecepcion(BigDecimal cantidadNominadaRecepcion) {
        this.cantidadNominadaRecepcion = cantidadNominadaRecepcion;
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

    public BigDecimal getTarifaExcesoFirme() {
        return tarifaExcesoFirme;
    }

    public void setTarifaExcesoFirme(BigDecimal tarifaExcesoFirme) {
        this.tarifaExcesoFirme = tarifaExcesoFirme;
    }

    public BigDecimal getTarifaUsoInterrumpible() {
        return tarifaUsoInterrumpible;
    }

    public void setTarifaUsoInterrumpible(BigDecimal tarifaUsoInterrumpible) {
        this.tarifaUsoInterrumpible = tarifaUsoInterrumpible;
    }

    public BigDecimal getTotalAFacturar() {
        return totalAFacturar;
    }

    public void setTotalAFacturar(BigDecimal totalAFacturar) {
        this.totalAFacturar = totalAFacturar;
    }

}
