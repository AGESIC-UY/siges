/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data.test;

import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.SsOficina;
import com.sofis.entities.data.SsRol;
import com.sofis.entities.data.SsUsuOfiRoles;
import com.sofis.entities.data.SsUsuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

/**
 * Constructores de datos de prueba.
 * 
 * @author pablo
 */
public final class TestDataBuilders {
    /**
     * Identificador del último usuario creado.
     */
    private static Integer USUARIO = -1;
    
    public synchronized static final SsUsuario.SsUsuarioBuilder ssUsuario() {
        USUARIO = USUARIO + 1;
        return SsUsuario.builder().usuPassword("pass")
                .usuFechaPassword(new Date()).usuUserCode(USUARIO)
                .usuVigente(true);
    }
    
    public static final List<Estados.EstadosBuilder> estadosReales() {
        final List<Estados.EstadosBuilder> builders = new ArrayList<>();
        builders.add(
                Estados.builder().estPk(Estados.ESTADOS.INICIO.estado_id)
                        .estNombre("Inicio")
                        .estLabel("Inicio")
        );
        builders.add(
                Estados.builder().estPk(Estados.ESTADOS.PLANIFICACION.estado_id)
                        .estNombre("Planificación").estLabel("Planificación")
        );
        builders.add(
                Estados.builder().estPk(Estados.ESTADOS.EJECUCION.estado_id)
                        .estNombre("Ejecución").estLabel("Ejecución")
        );
        builders.add(
                Estados.builder().estPk(Estados.ESTADOS.FINALIZADO.estado_id)
                        .estNombre("Finalizado").estLabel("Finalizado")
        );
        return builders;
    }
    
    public static final SsRol.SsRolBuilder ssRol() {
        return SsRol.builder().rolOrigen("SIGES_WEB").rolLabel("a")
                .rolUserCode(0);
    }
    
    public static final SsUsuOfiRoles.SsUsuOfiRolesBuilder ssUsuOfiRoles() {
        return SsUsuOfiRoles.builder().usuOfiRolesOrigen("Testing")
                .usuOfiRolesActivo(true);
    }
    
    public static final SsOficina.SsOficinaBuilder ssOficina() {
        return SsOficina.builder().ofiFechaCreacion(new Date())
                .ofiOrigen("origen");
    }
    
    public static final Entregables.EntregablesBuilder entregable() {
        return Entregables.builder().entId(1);
    }
    
    public static final Adquisicion.AdquisicionBuilder adquisicion() {
        return Adquisicion.builder().adqNombre("Adquisición").adqCompartida(false)
                .adqArrastre(false).adqFechaEstimadaInicioCompra(new Date())
                .adqFechaEsperadaInicioEjecucion(new DateTime().plusDays(7).toDate())
                .adqPreFk(Presupuesto.builder().build());
    }
    
    public static final Pagos.PagosBuilder pago() {
        return Pagos.builder().pagGasto(0).pagInversion(0)
                .pagAdqFk(adquisicion().build());
    }
}
