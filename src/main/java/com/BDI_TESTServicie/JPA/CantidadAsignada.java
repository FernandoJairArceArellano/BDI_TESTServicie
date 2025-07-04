package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "UGTP_TBL_CantidadAsignada")
@Schema(description = "Entidad que representa al registro de la cantidad Asignada")
public class CantidadAsignada {

    public CantidadAsignada(BigDecimal bigDecimal, int par) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico de la cantidad Asignada")
    private int idCantidadAsignada;

    @Schema(description = "Cantidad de energía, medida en Gigajoules por día (GJ/día), que se ha asignado para la entrega de gas natural a un usuario específico en un día determinado."
            + "Cantidad de gas que se ha acordado y efectivamente entregado a un receptor específico, expresada en términos de energía por día")
    private double valor;

    @JoinColumn(name = "tipo")
    @OneToOne
    private TipoNodoComercial tipo;

    public int getIdCantidadAsignada() {
        return idCantidadAsignada;
    }

    public void setIdCantidadAsignada(int idCantidadAsignada) {
        this.idCantidadAsignada = idCantidadAsignada;
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
