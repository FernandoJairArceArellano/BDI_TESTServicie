package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "UGTP_TBL_DetalleTarifa")
public class DetalleTarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indDetalleTarifa;

    @JoinColumn(name = "idTarifa")
    @ManyToOne
    private Tarifa tarifa;

    private BigDecimal gasEnExceso;

    private BigDecimal cargoUso;

    private BigDecimal cargoGasEnExceso;

    private BigDecimal totalAFacturar;

    public int getIndDetalleTarifa() {
        return indDetalleTarifa;
    }

    public void setIndDetalleTarifa(int indDetalleTarifa) {
        this.indDetalleTarifa = indDetalleTarifa;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
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

    public BigDecimal getTotalAFacturar() {
        return totalAFacturar;
    }

    public void setTotalAFacturar(BigDecimal totalAFacturar) {
        this.totalAFacturar = totalAFacturar;
    }

}
