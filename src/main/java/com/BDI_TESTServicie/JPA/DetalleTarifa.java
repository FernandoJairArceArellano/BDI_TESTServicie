package com.BDI_TESTServicie.JPA;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_DetalleTarifa")
public class DetalleTarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int indDetalleTarifa;

    @JoinColumn(name = "idTarifa")
    @ManyToOne
    private Tarifa tarifa;

    private double gasEnExceso;

    private double cargoUso;

    private double cargoGasEnExceso;

    private double totalAFacturar;

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

    public double getGasEnExceso() {
        return gasEnExceso;
    }

    public void setGasEnExceso(double gasEnExceso) {
        this.gasEnExceso = gasEnExceso;
    }

    public double getCargoUso() {
        return cargoUso;
    }

    public void setCargoUso(double cargoUso) {
        this.cargoUso = cargoUso;
    }

    public double getCargoGasEnExceso() {
        return cargoGasEnExceso;
    }

    public void setCargoGasEnExceso(double cargoGasEnExceso) {
        this.cargoGasEnExceso = cargoGasEnExceso;
    }

    public double getTotalAFacturar() {
        return totalAFacturar;
    }

    public void setTotalAFacturar(double totalAFacturar) {
        this.totalAFacturar = totalAFacturar;
    }

}
