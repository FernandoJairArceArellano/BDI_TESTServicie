package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "UGTP_TBL_Tarifa")
@Schema(description = "Entidad que representa a una tarifa")
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador unico de la tarifa", example = "1")
    private int idTarifa;

    @Schema(description = "Cargo adicional que se aplica en ciertas tarifas de energía eléctrica "
            + "cuando la demanda facturable en exceso supera una cierta proporción de la demanda contratada.", example = "1.333220")
    private double tarifaExcesoFirme;

    @Schema(description = "tarifa eléctrica que se ofrece a grandes consumidores, como clientes"
            + " industriales y residenciales con alto consumo, donde se acuerda un precio más bajo a cambio de la posibilidad de que el suministro eléctrico sea interrumpido temporalmente .", example = "1.333220")
    private double tarifaUsoInterrumpible;

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public double getTarifaExcesoFirme() {
        return tarifaExcesoFirme;
    }

    public void setTarifaExcesoFirme(double tarifaExcesoFirme) {
        this.tarifaExcesoFirme = tarifaExcesoFirme;
    }

    public double getTarifaUsoInterrumpible() {
        return tarifaUsoInterrumpible;
    }

    public void setTarifaUsoInterrumpible(double tarifaUsoInterrumpible) {
        this.tarifaUsoInterrumpible = tarifaUsoInterrumpible;
    }

}
