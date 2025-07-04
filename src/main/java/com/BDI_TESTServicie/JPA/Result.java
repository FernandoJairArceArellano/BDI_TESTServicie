package com.BDI_TESTServicie.JPA;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Objeto de Respuesta para las operaciones del sistema")
public class Result<T> {

    @Schema(description = "Indica el estado de la operacion", examples = {"true", "false"})
    public boolean correct;

    @Schema(description = "Mensaje de error si la operacion falla", example = "Error al procesar el archivo")
    public String errorMessage;

    @Schema(description = "Excepcion capturada si una operacion fallo", nullable = false)
    public Exception ex;

    @Schema(description = "Objeto individual devuelto por la operación (cuando no es una lista)", nullable = true)
    public T object;

    @Schema(description = "Lista de objetos devueltos por la operación (usualmente una lista de roles)", nullable = true)
    public List<T> objects;
}
