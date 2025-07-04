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
import com.BDI_TESTServicie.JPA.Tarifa;
import com.BDI_TESTServicie.JPA.Transaccion;
import com.BDI_TESTServicie.JPA.Usuario;
import com.BDI_TESTServicie.JPA.Zona;

public class RegistroDAOImplementation {

    public void addJPA(RegistroSistema reg) {
        // Usuario
        Usuario usuario = usuarioRepo.findByNombre(reg.usuario.getNombre());
        if (usuario == null) {
            usuario = usuarioRepo.save(reg.usuario);
        }

        // Contrato
        reg.contrato.setUsuario(usuario);
        Contrato contrato = contratoRepo.findByCodigoContrato(reg.contrato.getCodigoContrato());
        if (contrato == null) {
            contrato = contratoRepo.save(reg.contrato);
        }

        // Nodo comercial
        NodoComercial nodoRecep = nodoRepo.findByCodigoNodo(reg.detalleNodoComercial.getNodoRecepcion().getCodigoNodo());
        if (nodoRecep == null) {
            nodoRecep = nodoRepo.save(reg.detalleNodoComercial.getNodoRecepcion());
        }

        NodoComercial nodoEnt = nodoRepo.findByCodigoNodo(reg.detalleNodoComercial.getNodoEntrega().getCodigoNodo());
        if (nodoEnt == null) {
            nodoEnt = nodoRepo.save(reg.detalleNodoComercial.getNodoEntrega());
        }

        DetalleNodoComercial detalleNodo = new DetalleNodoComercial(nodoRecep, nodoEnt);
        detalleNodo = detalleNodoRepo.save(detalleNodo);

        // Zonas
        Zona zonaIny = zonaRepo.findByNombreZonaAndTipo(reg.detalleZona.getZonaInyeccion().getNombreZona(), 1);
        if (zonaIny == null) {
            zonaIny = zonaRepo.save(reg.detalleZona.getZonaInyeccion());
        }

        Zona zonaExt = zonaRepo.findByNombreZonaAndTipo(reg.detalleZona.getZonaExtraccion().getNombreZona(), 2);
        if (zonaExt == null) {
            zonaExt = zonaRepo.save(reg.detalleZona.getZonaExtraccion());
        }

        DetalleZona detalleZona = new DetalleZona(zonaIny, zonaExt);
        detalleZona = detalleZonaRepo.save(detalleZona);

        // Cantidades
        CantidadAsignada cae = cantidadAsignadaRepo.save(reg.detalleCantidad.getCantidadAsignadaEntrega());
        CantidadAsignada car = cantidadAsignadaRepo.save(reg.detalleCantidad.getCantidadAsignadaRecepcion());
        CantidadNominal cne = cantidadNominalRepo.save(reg.detalleCantidad.getCantidadNominalEntrega());
        CantidadNominal cnr = cantidadNominalRepo.save(reg.detalleCantidad.getCantidadNominalRecepcion());

        DetalleCantidad detalleCantidad = new DetalleCantidad(cae, car, cnr, cne);
        detalleCantidad = detalleCantidadRepo.save(detalleCantidad);

        // Tarifas
        Tarifa tarifa = tarifaRepo.save(reg.tarifa);
        DetalleTarifa detalleTarifa = new DetalleTarifa(tarifa,
                reg.detalleTarifa.getGasEnExceso(),
                reg.detalleTarifa.getCargoUso(),
                reg.detalleTarifa.getCargoGasEnExceso(),
                reg.detalleTarifa.getTotalAFacturar());
        detalleTarifa = detalleTarifaRepo.save(detalleTarifa);

        // Transacción
        Transaccion transaccion = new Transaccion(contrato, detalleNodo, detalleZona, detalleCantidad, detalleTarifa);
        transaccionRepo.save(transaccion);
    }

}
