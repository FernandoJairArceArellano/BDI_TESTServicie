package com.BDI_TESTServicie.JPA;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "UGTP_TBL_Usuario")
@NamedStoredProcedureQuery(
        name = "busquedaDeUsuariosPorNombreNodoRecepccion",
        procedureName = "busquedaDeUsuariosPorNombreNodoRecepccion",
        resultClasses = Usuario.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "pCodigoNodo", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "pCursor", type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "busquedaDeUsuariosPorNombreNodoEntrega",
        procedureName = "busquedaDeUsuariosPorNombreNodoEntrega",
        resultClasses = Usuario.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "pCodigoNodo", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "pCursor", type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "busquedaUsuarioPorZonaInyeccion",
        procedureName = "busquedaUsuarioPorZonaInyeccion",
        resultClasses = Usuario.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "pNombreZona", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "pCursor", type = void.class)
        }
)
@NamedStoredProcedureQuery(
        name = "busquedaUsuarioPorZonaExtraccion",
        procedureName = "busquedaUsuarioPorZonaExtraccion",
        resultClasses = Usuario.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "pNombreZona", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "pCursor", type = void.class)
        }
)
public class Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSUARIO")
    private int idUsuario;

    @Column(name = "NOMBRE")
    private String nombre;

    private String username;
    
    private String password;

    @OneToOne
    @JoinColumn(name = "Rol")
    private Rol rol;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    @JsonIgnore
    private List<Contrato> contratos;
}
