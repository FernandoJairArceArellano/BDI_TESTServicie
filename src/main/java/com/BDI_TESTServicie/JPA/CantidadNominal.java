package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_CantidadNominal")
public class CantidadNominal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCantidadNominal;

    private double valor;

    @JoinColumn(name = "tipo")
    @OneToOne
    private TipoNodoComercial tipo;

    public int getIdCantidadAsignada() {
        return idCantidadNominal;
    }

    public void setIdCantidadAsignada(int idCantidadAsignada) {
        this.idCantidadNominal = idCantidadAsignada;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public TipoNodoComercial getTipo() {
        return tipo;
    }

    public void setTipo(TipoNodoComercial tipo) {
        this.tipo = tipo;
    }
}
