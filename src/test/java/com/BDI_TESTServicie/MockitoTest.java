package com.BDI_TESTServicie;

import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.NodoEntrega;
import com.BDI_TESTServicie.JPA.NodoRecepccion;
import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.ZonaExtraccion;
import com.BDI_TESTServicie.JPA.ZonaInyeccion;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.NodoEntregaRepository;
import com.BDI_TESTServicie.JpaRepository.NodoRecepccionRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaExtraccionRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaInyeccionRepository;
import com.BDI_TESTServicie.Service.ContratoService;
import com.BDI_TESTServicie.Service.NodoService;
import com.BDI_TESTServicie.Service.RegistroSistemaService;
import com.BDI_TESTServicie.Service.TransaccionService;
import com.BDI_TESTServicie.Service.UsuarioService;
import com.BDI_TESTServicie.Service.ZonaService;
import java.math.BigDecimal;
import java.util.Date;
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

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private ContratoService contratoService;

    @InjectMocks
    private UsuarioService usuarioService;

    @InjectMocks
    private NodoService nodoService;

    @InjectMocks
    private ZonaService zonaService;

    @InjectMocks
    private TransaccionService transaccionService;

    @InjectMocks
    private RegistroSistemaService registroSistemaService;

    @Test
    public void testAgregarUsuario() {
        // Arrange
        String nombre = "BP Energía México";
        Usuario usuarioMock = new Usuario();
        usuarioMock.setNombre(nombre);

        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        // Act
        Result result = usuarioService.agregarUsuario(usuarioMock);

        // Assert
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    public void testAgregarContrato() {
        String codigoContrato = "CENAGAS/A/100/17";
        Date fechaContrato = new Date(2021, 0, 1);

        int idZonaInyeccion = 1;
        String zonaInyeccion = "Zona 8";
        int idZonaExtraccion = 1;
        String zonaExtraccion = "Zona 8";
        int idUsuario = 1;
        String nombreUsurio = "Pemex Transformación Industrial";

        ZonaInyeccion zonaInyeccionMock = new ZonaInyeccion();
        zonaInyeccionMock.setIdZonaInyeccion(idZonaInyeccion);
        zonaInyeccionMock.setNombreZona(zonaInyeccion);

        ZonaExtraccion zonaExtraccionMock = new ZonaExtraccion();
        zonaExtraccionMock.setIdZonaExtraccion(idZonaExtraccion);
        zonaExtraccionMock.setNombreZona(zonaExtraccion);

        Usuario usuarioMock = new Usuario();
        usuarioMock.setIdUsuario(idUsuario);
        usuarioMock.setNombre(nombreUsurio);

        Contrato contratoMock = new Contrato();
        contratoMock.setCodigoContrato(codigoContrato);
        contratoMock.setFecha(fechaContrato);
        Mockito.when(zonaInyeccionRepository.findByNombreZona(zonaInyeccion)).thenReturn(zonaInyeccionMock);
        Mockito.when(zonaExtraccionRepository.findByNombreZona(zonaInyeccion)).thenReturn(zonaExtraccionMock);
        Mockito.when(usuarioRepository.findByNombre(nombreUsurio)).thenReturn(usuarioMock);
        Mockito.when(contratoRepository.save(any(Contrato.class))).thenReturn(contratoMock);

//        Result<?> result = contratoService.agregarContrato(
//                codigoContrato,
//                fechaContrato,
//                zonaExtraccionMock,
//                zonaInyeccionMock,
//                usuarioMock
//        );

//        Assertions.assertTrue(result.correct);
//        Assertions.assertNull(result.errorMessage);
//        Assertions.assertNull(result.ex);
        Mockito.verify(zonaInyeccionRepository).findByNombreZona(zonaInyeccion);
        Mockito.verify(zonaExtraccionRepository).findByNombreZona(zonaExtraccion);
        Mockito.verify(usuarioRepository).findByNombre(nombreUsurio);
        Mockito.verify(contratoRepository).save(any(Contrato.class));

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

    @Test
    public void testAddTransaccion() {
        String codigoContrato = "C001";
        String codigoNodoEntrega = "N037";
        String codigoNodoRecepcion = "R011";

        Contrato contratoMock = new Contrato();
        contratoMock.setCodigoContrato(codigoContrato);

        NodoEntrega nodoEntregaMock = new NodoEntrega();
        nodoEntregaMock.setCodigoNodo(codigoNodoEntrega);

        NodoRecepccion nodoRecepccionMock = new NodoRecepccion();
        nodoRecepccionMock.setCodigoNodo(codigoNodoRecepcion);

        Transaccion transaccion = new Transaccion();

        // Simulaciones
        Mockito.when(contratoRepository.findByCodigoContrato(codigoContrato)).thenReturn(contratoMock);
        Mockito.when(nodoEntregaRepository.findByCodigoNodo(codigoNodoEntrega)).thenReturn(nodoEntregaMock);
        Mockito.when(nodoRecepccionRepository.findByCodigoNodo(codigoNodoRecepcion)).thenReturn(nodoRecepccionMock);
        Mockito.when(transaccionRepository.save(any(Transaccion.class))).thenReturn(transaccion);

        Result<?> result = transaccionService.addTransaccion(
                codigoContrato,
                codigoNodoEntrega,
                codigoNodoRecepcion,
                transaccion
        );

        // Assert
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
        Mockito.verify(contratoRepository).findByCodigoContrato(codigoContrato);
        Mockito.verify(nodoEntregaRepository).findByCodigoNodo(codigoNodoEntrega);
        Mockito.verify(nodoRecepccionRepository).findByCodigoNodo(codigoNodoRecepcion);
        Mockito.verify(transaccionRepository).save(any(Transaccion.class));
    }

    @Test
    public void testProcesarRegistroSistema() {
        RegistroSistema registro = new RegistroSistema();

        // Crear mocks de entidades
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombre("Pemex Transformación Industrial");
        registro.usuario = usuario;

        ZonaInyeccion zonaInyeccion = new ZonaInyeccion();
        zonaInyeccion.setIdZonaInyeccion(1);
        zonaInyeccion.setNombreZona("Zona 8");
        registro.zonaInyeccion = zonaInyeccion;

        ZonaExtraccion zonaExtraccion = new ZonaExtraccion();
        zonaExtraccion.setIdZonaExtraccion(1);
        zonaExtraccion.setNombreZona("Zona 8");
        registro.zonaExtraccion = zonaExtraccion;

        NodoEntrega nodoEntrega = new NodoEntrega();
        nodoEntrega.setIdNodoEntrega(1);
        nodoEntrega.setCodigoNodo("E095");
        nodoEntrega.setNombreNodoComercial("CACTUSNVOPMX");
        registro.nodoEntrega = nodoEntrega;

        NodoRecepccion nodoRecepccion = new NodoRecepccion();
        nodoRecepccion.setIdNodoRecepccion(1);
        nodoRecepccion.setCodigoNodo("V025");
        nodoRecepccion.setNombreNodoComercial("AGUADULCE");
        registro.nodoRecepccion = nodoRecepccion;

        Contrato contrato = new Contrato();
        contrato.setIdContrato(1);
        contrato.setCodigoContrato("CENAGAS/A/100/17");
        contrato.setFecha(new Date());
        registro.contrato = contrato;

        Transaccion transaccion = new Transaccion();
        transaccion.setContrato(contrato);
        transaccion.setNodoRecepcion(nodoRecepccion);
        transaccion.setNodoEntrega(nodoEntrega);
        transaccion.setCantidadAsignadaEntregada(BigDecimal.TEN);
        transaccion.setCantidadAsignadaRecepcion(BigDecimal.TEN);
        transaccion.setCantidadNominadaEntregada(BigDecimal.TEN);
        transaccion.setCantidadNominadaRecepcion(BigDecimal.TEN);
        transaccion.setGasEnExceso(BigDecimal.TEN);
        transaccion.setCargoUso(BigDecimal.TEN);
        transaccion.setCargoGasEnExceso(BigDecimal.TEN);
        transaccion.setTarifaExcesoFirme(BigDecimal.TEN);
        transaccion.setTarifaUsoInterrumpible(BigDecimal.TEN);
        transaccion.setTotalAFacturar(BigDecimal.TEN);

        registro.transaccion = transaccion;

        // Configurar mocks de repositorios
        Mockito.when(usuarioRepository.findByNombre("Pemex Transformación Industrial")).thenReturn(null);
        Mockito.when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Mockito.when(zonaInyeccionRepository.findByNombreZona("Zona 8")).thenReturn(null);
        Mockito.when(zonaInyeccionRepository.save(any(ZonaInyeccion.class))).thenReturn(zonaInyeccion);

        Mockito.when(zonaExtraccionRepository.findByNombreZona("Zona 8")).thenReturn(null);
        Mockito.when(zonaExtraccionRepository.save(any(ZonaExtraccion.class))).thenReturn(zonaExtraccion);

        Mockito.when(nodoEntregaRepository.findByCodigoNodo("E095")).thenReturn(null);
        Mockito.when(nodoEntregaRepository.save(any(NodoEntrega.class))).thenReturn(nodoEntrega);

        Mockito.when(nodoRecepccionRepository.findByCodigoNodo("V025")).thenReturn(null);
        Mockito.when(nodoRecepccionRepository.save(any(NodoRecepccion.class))).thenReturn(nodoRecepccion);

        Mockito.when(contratoRepository.findByCodigoContrato("CENAGAS/A/100/17")).thenReturn(null);
        Mockito.when(contratoRepository.save(any(Contrato.class))).thenReturn(contrato);

        Mockito.when(transaccionRepository.save(any(Transaccion.class))).thenReturn(transaccion);

        // Ejecutar método
        Result result = registroSistemaService.procesarRegistroSistema(registro);

        // Verificar resultados
        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNull(result.ex);
    }

}
