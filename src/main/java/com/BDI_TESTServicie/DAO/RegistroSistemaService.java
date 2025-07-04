package com.BDI_TESTServicie.DAO;

import com.BDI_TESTServicie.JPA.CantidadAsignada;
import com.BDI_TESTServicie.JPA.CantidadNominal;
import com.BDI_TESTServicie.JPA.Contrato;
import com.BDI_TESTServicie.JPA.DetalleCantidad;
import com.BDI_TESTServicie.JPA.DetalleNodoComercial;
import com.BDI_TESTServicie.JPA.DetalleTarifa;
import com.BDI_TESTServicie.JPA.DetalleZona;
import com.BDI_TESTServicie.JPA.NodoComercial;
import com.BDI_TESTServicie.JPA.RegistroSistema;
import com.BDI_TESTServicie.JPA.Result;
import com.BDI_TESTServicie.JPA.ResultFile;
import com.BDI_TESTServicie.JPA.Tarifa;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.Zona;
import com.BDI_TESTServicie.JpaRepository.CantidadAsignadaRepository;
import com.BDI_TESTServicie.JpaRepository.CantidadNominalRepository;
import com.BDI_TESTServicie.JpaRepository.ContratoRepository;
import com.BDI_TESTServicie.JpaRepository.DetalleCantidadRepository;
import com.BDI_TESTServicie.JpaRepository.DetalleNodoComercialRepository;
import com.BDI_TESTServicie.JpaRepository.DetalleTarifaRepository;
import com.BDI_TESTServicie.JpaRepository.DetalleZonaRepository;
import com.BDI_TESTServicie.JpaRepository.NodoComercialRepository;
import com.BDI_TESTServicie.JpaRepository.TarifaRepository;
import com.BDI_TESTServicie.JpaRepository.TransaccionRepository;
import com.BDI_TESTServicie.JpaRepository.UsuarioRepository;
import com.BDI_TESTServicie.JpaRepository.ZonaRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroSistemaService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private NodoComercialRepository nodoComercialRepository;

    @Autowired
    private DetalleNodoComercialRepository detalleNodoRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private DetalleZonaRepository detalleZonaRepository;

    @Autowired
    private TarifaRepository tarifaRepository;

    @Autowired
    private DetalleTarifaRepository detalleTarifaRepository;

    @Autowired
    private CantidadNominalRepository cantidadNominalRepository;

    @Autowired
    private CantidadAsignadaRepository cantidadAsignadaRepository;

    @Autowired
    private DetalleCantidadRepository detalleCantidadRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Result guardarRegistrosEnBD(List<RegistroSistema> registros) {
//        Result result = new Result();
//        List<ResultFile> errores = new ArrayList<>();
//        int fila = 2;
//
//        for (RegistroSistema registro : registros) {
//            try {
//                // 1. Guardar o buscar Usuario
//                Usuario usuario = usuarioRepository.findByNombre(registro.usuario.getNombre());
//                if (usuario == null) {
//                    usuario = usuarioRepository.save(registro.usuario);
//                }
//
//                // 2. Guardar Contrato
//                Contrato contrato = registro.contrato;
//                contrato.setUsuario(usuario);
//                contrato = contratoRepository.save(contrato);
//
//                // 3. Guardar Nodos Comerciales
//                NodoComercial nodoRecepcion = nodoComercialRepository.findByCodigoNodo(registro.detalleNodoComercial.getNodoRecepcion().toString());
//                NodoComercial nodoEntrega = nodoComercialRepository.findByCodigoNodo(registro.detalleNodoComercial.getNodoEntrega().toString());
//
//                if (nodoRecepcion == null || nodoEntrega == null) {
//                    errores.add(new ResultFile(fila, "Nodo comercial no encontrado", "Código nodo inválido"));
//                    continue;
//                }
//
//                DetalleNodoComercial detalleNodo = new DetalleNodoComercial();
//                detalleNodo.setNodoRecepcion(nodoRecepcion);
//                detalleNodo.setNodoEntrega(nodoEntrega);
//                detalleNodo = detalleNodoRepository.save(detalleNodo);
//
//                // 4. Guardar Zonas
//                Zona zonaInyeccion = zonaRepository.findByNombreZona(registro.zona.tipoZona.setTipoZona());
//                Zona zonaExtraccion = zonaRepository.findByNombreZona(registro.zona.getZonaExtraccion());
//
//                if (zonaInyeccion == null || zonaExtraccion == null) {
//                    errores.add(new ResultFile(fila, "Zona no encontrada", "Nombre de zona inválido"));
//                    continue;
//                }
//
//                DetalleZona detalleZona = new DetalleZona();
//                detalleZona.setZonaInyeccion(zonaInyeccion);
//                detalleZona.setZonaExtraccion(zonaExtraccion);
//                detalleZona = detalleZonaRepository.save(detalleZona);
//
//                // 5. Guardar cantidades
//                CantidadNominal nominalRecepcion = cantidadNominalRepository.save(new CantidadNominal(registro.detalleCantidad.getNominadaRecepcion(), 1));
//                CantidadNominal nominalEntrega = cantidadNominalRepository.save(new CantidadNominal(registro.detalleCantidad.getNominadaEntrega(), 2));
//                CantidadAsignada asignadaRecepcion = cantidadAsignadaRepository.save(new CantidadAsignada(registro.detalleCantidad.getAsignadaRecepcion(), 1));
//                CantidadAsignada asignadaEntrega = cantidadAsignadaRepository.save(new CantidadAsignada(registro.detalleCantidad.getAsignadaEntrega(), 2));
//
//                DetalleCantidad detalleCantidad = new DetalleCantidad();
//                detalleCantidad.setCantidadAsignadaEntrega(asignadaEntrega);
//                detalleCantidad.setCantidadAsignadaRecepcion(asignadaRecepcion);
//                detalleCantidad.setCantidadNominalRecepcion(nominalRecepcion);
//                detalleCantidad.setCantidadNominalEntrega(nominalEntrega);
//                detalleCantidad = detalleCantidadRepository.save(detalleCantidad);
//
//                // 6. Guardar tarifa
//                Tarifa tarifa = tarifaRepository.save(new Tarifa(
//                        registro.detalleTarifa.getTarifaFirme(),
//                        registro.detalleTarifa.getTarifaUsoInterrumpible()
//                ));
//
//                DetalleTarifa detalleTarifa = new DetalleTarifa();
//                detalleTarifa.setTarifa(tarifa);
//                detalleTarifa.setGasEnExceso(registro.detalleTarifa.getGasEnExceso());
//                detalleTarifa.setCargoUso(registro.detalleTarifa.getCargoUso());
//                detalleTarifa.setCargoGasEnExceso(registro.detalleTarifa.getCargoExceso());
//                detalleTarifa.setTotalAFacturar(registro.transaccion.getTotalFacturar());
//                detalleTarifa = detalleTarifaRepository.save(detalleTarifa);
//
//                // 7. Guardar Transacción
//                Transaccion transaccion = new Transaccion();
//                transaccion.setContrato(contrato);
//                transaccion.setDetalleNodoComercial(detalleNodo);
//                transaccion.setDetalleZona(detalleZona);
//                transaccion.setDetalleCantidad(detalleCantidad);
//                transaccion.setDetalleTarifa(detalleTarifa);
//                transaccionRepository.save(transaccion);
//
//            } catch (Exception ex) {
//                errores.add(new ResultFile(fila, "Error inesperado", ex.getMessage()));
//                ex.printStackTrace();
//            }
//            fila++;
//        }
//
//        if (errores.isEmpty()) {
//            result.correct = true;
//        } else {
//            result.correct = false;
//            result.objects = errores;
//        }
//
//        return result;
        return null;
    }
}
