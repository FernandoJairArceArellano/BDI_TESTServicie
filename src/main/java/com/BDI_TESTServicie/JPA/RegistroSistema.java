package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto que contiene fila a procesar")
public class RegistroSistema {

    public Contrato contrato;

    public Usuario usuario;

    public DetalleZona detalleZona;

    public Zona zona;

    public TipoZona tipoZona;

    public DetalleTarifa detalleTarifa;

    public Tarifa tarifa;

    public DetalleCantidad detalleCantidad;

    public CantidadNominal cantidadNominal;

    public CantidadAsignada cantidadAsignada;

    public TipoNodoComercial tipoNodoComercial;

    public NodoComercial nodoComercial;

    public DetalleNodoComercial detalleNodoComercial;

    public Transaccion transaccion;

}
