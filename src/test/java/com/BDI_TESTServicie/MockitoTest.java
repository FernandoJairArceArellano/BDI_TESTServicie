package com.BDI_TESTServicie;

import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import com.BDI_TESTServicie.JpaRepository.NodoRecepccionRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaExtraccionRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaInyeccionRepository;
import com.BDI_TESTServicie.Service.NodoService;
import com.BDI_TESTServicie.Service.UsuarioService;
import com.BDI_TESTServicie.Service.ZonaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private NodoEntregaRepository nodoEntregaRepository;

    @Mock
    private NodoRecepccionRepository nodoRecepccionRepository;

    @Mock
    private ZonaExtraccionRepository zonaExtraccionRepository;

    @Mock
    private ZonaInyeccionRepository zonaInyeccionRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private NodoService nodoService;

    @InjectMocks
    private ZonaService zonaService;

    @Test
    public void testAgregarUsuario() {
        // Arrange
        String nombre = "BP Energía México";
        Usuario usuarioMock = new Usuario();
        usuarioMock.setNombre(nombre);

        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        // Act
        Result result = usuarioService.agregarUsuario(nombre);

        // Assert
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testAgregarNodoEntrega() {
        String nombreNodoEntrega = "N037";
        NodoEntrega nodoEntregaMock = new NodoEntrega();
        nodoEntregaMock.setCodigoNodo(nombreNodoEntrega);

        Mockito.when(nodoEntregaRepository.save(any(NodoEntrega.class))).thenReturn(nodoEntregaMock);

        Result result = nodoService.addNodoEntrega(nombreNodoEntrega);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(nodoEntregaRepository, times(1)).save(any(NodoEntrega.class));
    }

    @Test
    public void testAgregarNodoRecepccion() {
        String nombreNodoRecepcion = "V067";
        NodoRecepccion nodoRecepccionMock = new NodoRecepccion();
        nodoRecepccionMock.setCodigoNodo(nombreNodoRecepcion);

        Mockito.when(nodoRecepccionRepository.save(any(NodoRecepccion.class))).thenReturn(nodoRecepccionMock);

        Result result = nodoService.addNodoRecepcion(nombreNodoRecepcion);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(nodoRecepccionRepository, times(1)).save(any(NodoRecepccion.class));
    }

    @Test
    public void testAgregarZonaInyeccion() {
        String nombreZonaInyeccion = "Zona 3";
        ZonaInyeccion zonaInyeccionMock = new ZonaInyeccion();
        zonaInyeccionMock.setNombreZona(nombreZonaInyeccion);

        Mockito.when(zonaInyeccionRepository.save(any(ZonaInyeccion.class))).thenReturn(zonaInyeccionMock);

        Result result = zonaService.addZonaInyeccion(nombreZonaInyeccion);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(zonaInyeccionRepository, times(1)).save(any(ZonaInyeccion.class));
    }

    @Test
    public void testAgregarZonaExtraccion() {
        String nombreZonaExtraccion = "Zona 3";
        ZonaExtraccion zonaExtraccionMock = new ZonaExtraccion();
        zonaExtraccionMock.setNombreZona(nombreZonaExtraccion);

        Mockito.when(zonaExtraccionRepository.save(any(ZonaExtraccion.class))).thenReturn(zonaExtraccionMock);

        Result result = zonaService.addZonaExtraccion(nombreZonaExtraccion);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(zonaExtraccionRepository, times(1)).save(any(ZonaExtraccion.class));
    }
    
    
}
