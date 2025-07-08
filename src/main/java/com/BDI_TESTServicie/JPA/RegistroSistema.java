package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto que contiene fila a procesar")
public class RegistroSistema {

    public Contrato contrato;

    public Usuario usuario;

    public Transaccion transaccion;

}
