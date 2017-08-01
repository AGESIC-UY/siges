/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.validations.CronogramaValidaciones;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Personas;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.validations.FichaValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.ws.gestion.data.AdquisicionTO;
import com.sofis.web.ws.gestion.data.CronogramaTO;
import com.sofis.web.ws.gestion.data.EntregableTO;
import com.sofis.web.ws.gestion.data.PagoTO;
import com.sofis.web.ws.gestion.data.PresupuestoTO;
import com.sofis.web.ws.gestion.data.ProyectoTO;
import com.sofis.web.ws.gestion.data.RequestGuardarProyectoTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author bruno
 */
@Named
@Stateless(name = "ServiciosBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ServiciosBean {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private AreasBean areasBean;
    @Inject
    private ProyectosBean proyectosBean;
    @Inject
    private ProgramasProyectosBean progProyBean;
    @Inject
    private SsUsuarioBean ssUsuarioBean;
    @Inject
    private AreaTematicaBean areaTematicaBean;
    @Inject
    private OrganismoBean organismoBean;
    @Inject
    private CronogramasBean cronogramaBean;
    @Inject
    private PresupuestoBean presupuestoBean;
    @Inject
    private MonedaBean monedaBean;
    @Inject
    private FuenteFinanciamientoBean fuenteFinanciamientoBean;
    @Inject
    private AdquisicionBean adquisicionBean;
    @Inject
    private PagosBean pagosBean;
    @Inject
    private DocumentosBean documentosBean;
    @Inject
    private ProductosBean productosBean;
    @Inject
    private CalidadBean calidadBean;
    @Inject
    private InteresadosBean interesadosBean;
    @Inject
    private ParticipantesBean participantesBean;
    @Inject
    private RiesgosBean riesgosBean;

//    @Inject
//    private 
    public Integer guardarFicha(RequestGuardarProyectoTO request) throws GeneralException {

        boolean error = false;
        List<String> errores = new LinkedList<String>();

        ProyectoTO proyectoTO = request.getProyecto();

        /**
         * Obtener organismo a partir del token. Controlar de que exista un
         * organismo con ese token
         */
        Organismos organismo = organismoBean.obtenerOrgPorToken(request.getToken());
        Integer orgPk = organismo.getOrgPk();

        //Se cargan los valores necesarios al fichaTO.
        FichaTO fichaTO = null;

        //Inicializa el objeto para el alta de interesados
        Interesados interesado = new Interesados();
        interesado.setIntPersonaFk(new Personas());
        interesado.setIntRolintFk(new RolesInteresados());

        fichaTO = new FichaTO();

        if (proyectoTO.getProyPk() != null) {
            Proyectos proy = proyectosBean.obtenerProyPorId(proyectoTO.getProyPk());
            //TODO si el proyecto está inactivo lanzar una excepción.
            desasociarCronogramaYPresupuesto(proy);
            proyectosBean.obtenerProyPorId(proyectoTO.getProyPk());
            fichaTO = this.proyectoToFichaTO(proy, fichaTO);
        } else {
            fichaTO.setActivo(true);
            fichaTO.setEstado(new Estados(Estados.ESTADOS.INICIO.estado_id)); //los proyectos se crean en inicio y activos
        }

        fichaTO.setUltimaModificacion(new Date());
        fichaTO.setTipoFicha(TipoFichaEnum.PROYECTO.getValue());
        fichaTO.setPeso(1);
        LatlngProyectos latLng = new LatlngProyectos();
        fichaTO.setLatlngProy(latLng);
        Areas usuArea = areasBean.obtenerAreasPorPk(proyectoTO.getAreaFk());
        if (usuArea == null || (usuArea != null && !usuArea.getAreaHabilitada())) {
            error = true;
            errores.add("No se encuentra el área especificada");
        } else {
            fichaTO.setAreaFk(usuArea);
        }

        Configuracion confRiesgoTiempoLimiteAmarillos = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, orgPk);
        if (confRiesgoTiempoLimiteAmarillos != null) {
            fichaTO.setSemaforoAmarillo(Integer.valueOf(confRiesgoTiempoLimiteAmarillos.getCnfValor()));
        }

        Configuracion confRiesgoTiempoLimiteRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, orgPk);
        if (confRiesgoTiempoLimiteRojo != null) {
            fichaTO.setSemaforoRojo(Integer.valueOf(confRiesgoTiempoLimiteRojo.getCnfValor()));
        }

        fichaTO.setNombre(proyectoTO.getProyNombre());
        fichaTO.setDescripcion(proyectoTO.getProyDescripcion());
        fichaTO.setObjPublico(proyectoTO.getProyObjPublico());
        fichaTO.setObjetivo(proyectoTO.getProyObjetivo());
        fichaTO.setSituacionActual(proyectoTO.getProySituacionActual());

        SsUsuario proyUsrGerente = ssUsuarioBean.obtenerSsUsuarioPorId(proyectoTO.getProyUsrGerenteFk());
        SsUsuario proyUsrAdjunto = ssUsuarioBean.obtenerSsUsuarioPorId(proyectoTO.getProyUsrAdjuntoFk());
        SsUsuario proyUsrPmofed = ssUsuarioBean.obtenerSsUsuarioPorId(proyectoTO.getProyUsrPmofedFk());
        SsUsuario proyUsrSponsor = ssUsuarioBean.obtenerSsUsuarioPorId(proyectoTO.getProyUsrSponsorFk());

//        if (proyUsrGerente == null || !proyUsrGerente.getUsuVigente()) {
//            error = true;
//            errores.add("No se encuentra el gerente especificado");
//        } else if (proyUsrGerente.isUsuarioDirector(orgPk)) {
//            error = true;
//            errores.add("El usuario especificado como generte no tiene el rol correspondiente");
//        }
//        if (proyUsrAdjunto == null || !proyUsrGerente.getUsuVigente()) {
//            error = true;
//            errores.add("No se encuentra el adjunto especificado");
//        } else if (proyUsrAdjunto.isUsuarioDirector(orgPk)) {
//            error = true;
//            errores.add("El usuario especificado como adjunto no tiene el rol correspondiente");
//        }
//        if (proyUsrPmofed == null || !proyUsrGerente.getUsuVigente()) {
//            error = true;
//            errores.add("No se encuentra el PMOF especificado");
//        } else if (proyUsrGerente.isUsuarioPMO(orgPk)) {
//            error = true;
//            errores.add("El usuario especificado como PMOF no tiene el rol correspondiente");
//        }
//        if (proyUsrSponsor == null || !proyUsrGerente.getUsuVigente()) {
//            error = true;
//            errores.add("No se encuentra el sponsor especificado");
//        } else if (proyUsrGerente.isUsuarioDirector(orgPk)) {
//            error = true;
//            errores.add("El usuario especificado como sponsor no tiene el rol correspondiente");
//        }
        fichaTO.setUsrAdjuntoFk(proyUsrAdjunto);
        fichaTO.setUsrGerenteFk(proyUsrGerente);
        fichaTO.setUsrPmofedFk(proyUsrPmofed);
        fichaTO.setUsrSponsorFk(proyUsrSponsor);

        fichaTO.setOrgFk(organismo);

        if (error) {
            BusinessException ex = new BusinessException();
            ex.setErrores(errores);
            throw ex;
        }

        FichaValidacion.validar(fichaTO, orgPk);
        if (fichaTO.getCroFk() == null) {
            fichaTO.setCroFk(new Cronogramas());
        }
        if (fichaTO.getPreFk() != null) {
            limpiarPresupuesto(fichaTO.getPreFk(), proyectoTO.getProyPk(), orgPk);
        }
        fichaTO.setPreFk(new Presupuesto());
        Proyectos obj = proyectosBean.guardarProyecto(fichaTO, null, orgPk);
        fichaTO = proyectoToFichaTO(obj, fichaTO);
        Cronogramas cro = null;
        Presupuesto pre = null;

        if (proyectoTO.getCronograma() != null) {
            cro = obj.getProyCroFk();
            if (cro == null) {
                return obj.getProyPk();
            }
            cro = cronogramaBean.guardar(cro);
            obj.setProyCroFk(cro);
            obj = proyectosBean.obtenerProyPorId(obj.getProyPk());
            cro = cronogramaFromCronogramaTO(orgPk, obj, proyectoTO.getCronograma());
        }

        if (proyectoTO.getPresupuesto() != null) {
            pre = obj.getProyPreFk();
            if (pre == null) {
                return obj.getProyPk();
            }

            pre.getAdquisicionSet().clear();
//            pre = obj.getProyPreFk();
            pre = presupuestoFromPresupuestoTO(orgPk, fichaTO, obj, proyectoTO.getPresupuesto(), obtenerEntregableNivel0(cro));
            obj = (Proyectos) presupuestoBean.guardarPresupuesto(pre, obj.getProyPk(), TipoFichaEnum.PROYECTO.id, null, orgPk);
        }
        Proyectos proy = proyectosBean.obtenerProyPorId(proyectoTO.getProyPk());
        fichaTO = this.proyectoToFichaTO(proy, fichaTO);
        FichaValidacion.validar(fichaTO, orgPk);

        return obj.getProyPk();

    }

    private FichaTO proyectoToFichaTO(Proyectos proy, FichaTO fichaTO) {
        if (proy != null) {
            fichaTO = new FichaTO();
            fichaTO.setFichaFk(proy.getProyPk());
            fichaTO.setTipoFicha(TipoFichaEnum.PROYECTO.id);
            fichaTO.setOrgFk(proy.getProyOrgFk());
            fichaTO.setActivo(proy.getActivo());
            fichaTO.setFechaCrea(proy.getProyFechaCrea());
            fichaTO.setFechaAct(proy.getProyFechaAct());
            fichaTO.setFechaActColor(proyectosBean.obtenerUltimaActualizacionColor(proy.getProyEstFk(), proy.getProyFechaAct(), proy.getProySemaforoAmarillo(), proy.getProySemaforoRojo()));
            fichaTO.setAreaFk(proy.getProyAreaFk());

            fichaTO.setNombre(proy.getProyNombre());
            fichaTO.setDescripcion(proy.getProyDescripcion());
            fichaTO.setObjetivo(proy.getProyObjetivo());
            fichaTO.setObjPublico(proy.getProyObjPublico());
            fichaTO.setFactorImpacto(proy.getProyFactorImpacto());
            fichaTO.setProgFk(proy.getProyProgFk());
            fichaTO.setPeso(proy.getProyPeso());
            fichaTO.setSituacionActual(proy.getProySituacionActual());
            fichaTO.setSemaforoAmarillo(proy.getProySemaforoAmarillo());
            fichaTO.setSemaforoRojo(proy.getProySemaforoRojo());
            fichaTO.setGrp(proy.getProyGrp());
            fichaTO.setEstado(proy.getProyEstFk());
            fichaTO.setEstadoPendiente(proy.getProyEstPendienteFk());
            fichaTO.setUsrAdjuntoFk(proy.getProyUsrAdjuntoFk());
            fichaTO.setUsrGerenteFk(proy.getProyUsrGerenteFk());
            fichaTO.setUsrPmofedFk(proy.getProyUsrPmofedFk());
            fichaTO.setUsrSponsorFk(proy.getProyUsrSponsorFk());

            fichaTO.setCroFk(proy.getProyCroFk());
            fichaTO.setPreFk(proy.getProyPreFk());

            fichaTO.setProyIndices(proy.getProyIndices());
            fichaTO.setProyProgVersion(proy.getProyVersion());

            if (fichaTO.hasPk()) {
                List<Areas> areasRestList = areasBean.obtenerAreasRestringidasPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (areasRestList != null) {
                    fichaTO.setAreasRestringidas(new HashSet<Areas>(areasRestList));
                }
            }

            if (fichaTO.hasPk()) {
                List<AreasTags> areasTemaList = areaTematicaBean.obtenerAreasTematicasPorFichaPk(fichaTO.getFichaFk(), fichaTO.getTipoFicha());
                if (areasTemaList != null) {
                    fichaTO.setAreasTematicas(new HashSet<AreasTags>(areasTemaList));
                }
            }

            //Otros Datos
            fichaTO.setPublicable(proy.getProyPublicable());
            fichaTO.setOtrosDatos(proy.getProyOtrosDatos());
            fichaTO.setLatlngProy(proy.getProyLatlngFk() != null ? proy.getProyLatlngFk() : new LatlngProyectos());

            // Documentos
            fichaTO.setDocumentos(new ArrayList<>(proy.getDocumentosSet()));
            fichaTO.setInteresados(new ArrayList<>(proy.getInteresadosList()));
            fichaTO.setParticipantes(new ArrayList<>(proy.getParticipantesList()));
            fichaTO.setRiesgos(new ArrayList<>(proy.getRiesgosList()));

            fichaTO = fichaTOOriginal(proy, fichaTO);
        }

        return fichaTO;

    }

    private Cronogramas cronogramaFromCronogramaTO(Integer orgPk, Proyectos proy, CronogramaTO cronogramaTO) {

        Cronogramas cro = proy.getProyCroFk();
        cro.setCroPermisoEscritura(true);
        cro.setCroPermisoEscrituraPadre(false);
        cro.setEntregablesSet(entregablesFromCronogramaTO(proy.getProyUsrGerenteFk(), cro, cronogramaTO));
        CronogramaValidaciones.validar(cro);
        proy = (Proyectos) cronogramaBean.guardarCronograma(cro, proy.getProyPk(), TipoFichaEnum.PROYECTO.id, null, orgPk);
        cro = proy.getProyCroFk();
        return cro;
    }

    private Set<Entregables> entregablesFromCronogramaTO(SsUsuario coordinador, Cronogramas cro, CronogramaTO cronogramaTO) {

        Set<Entregables> entregablesSet = cro.getEntregablesSet();
        List<Entregables> entregablesList = new ArrayList<>();
        entregablesSet.clear();
        Entregables e;
        if (cronogramaTO.getEntregableSet() != null) {
            for (EntregableTO eTO : cronogramaTO.getEntregableSet()) {
                e = new Entregables();
                e.setEntCroFk(cro);
                e.setEntId(eTO.getEntId());
                e.setEntInicio(eTO.getEntInicio());
                e.setEntInicioLineaBase(eTO.getEntInicioLineaBase());
                e.setEntEsfuerzo(eTO.getEntEsfuerzo());
                e.setEntFin(eTO.getEntFin());
                e.setEntFinLineaBase(eTO.getEntFinLineaBase());
                e.setEntNivel(eTO.getEntNivel());
                e.setEntNombre(eTO.getEntNombre());
                e.setEntFinEsHito(eTO.getEsHito());
                e.setEntInicioProyecto(false);
                e.setEntFinProyecto(false);
                e.setCoordinadorUsuFk(coordinador);
                if (eTO.getEsHito()) {
                    e.setEntDuracion(1);
                    e.setEntDuracionLineaBase(1);
                } else {
                    e.setEntDuracion(DatesUtils.diasEntreFechas(e.getEntInicioDate(), e.getEntFinDate()));
                    e.setEntDuracionLineaBase(DatesUtils.diasEntreFechas(e.getEntInicioLineaBaseDate(), e.getEntFinLineaBaseDate()));
                }

                e.setEntPredecesorFk(eTO.getEntPredecesorFk());
                if (eTO.getEntPredecesorFk() != null
                        && StringsUtils.isEmpty(eTO.getEntPredecesorFk())) {
                    e.setEntPredecesorDias(e.getEntPredecesorDias());
                }
                e.setEntProgreso(eTO.getEntProgreso());
                entregablesList.add(e);
            }
            entregablesList = EntregablesUtils.corregirFechasPadres(entregablesList, true, true);
            entregablesSet.addAll(entregablesList);
            return entregablesSet;
        }
        return new HashSet<Entregables>();
    }

    private FichaTO fichaTOOriginal(Object p, FichaTO fichaTO) {
        if (p != null) {
            FichaTO fichaOrigTO = new FichaTO();

            if (p instanceof Programas) {
                Programas prog = (Programas) p;

                fichaOrigTO.setFichaFk(prog.getProgPk());
                fichaOrigTO.setTipoFicha(TipoFichaEnum.PROGRAMA.id);
                fichaOrigTO.setEstado(prog.getProgEstFk());

            } else if (p instanceof Proyectos) {
                Proyectos proy = (Proyectos) p;

                fichaOrigTO.setFichaFk(proy.getProyPk());
                fichaOrigTO.setTipoFicha(TipoFichaEnum.PROYECTO.id);
                fichaOrigTO.setSituacionActual(proy.getProySituacionActual());
                fichaOrigTO.setEstado(proy.getProyEstFk());
            }

            fichaTO.setFichaOriginal(fichaOrigTO);
            return fichaTO;
        }
        return null;
    }

    private Presupuesto presupuestoFromPresupuestoTO(Integer orgPk, FichaTO fichaTO, Proyectos proy, PresupuestoTO presupuestoTO, Entregables e) {
        Presupuesto pre = proy.getProyPreFk();
        Set<Adquisicion> adquisicionesSet = pre.getAdquisicionSet();
        adquisicionesSet.clear();
        pre.setAdquisicionSet(adquisicionesFromPresupuestoTO(orgPk, fichaTO, pre, presupuestoTO, e));
        return pre;
    }

    private Set<Adquisicion> adquisicionesFromPresupuestoTO(Integer orgPk, FichaTO fichaTO, Presupuesto pre, PresupuestoTO presupuestoTO, Entregables e) {
        Adquisicion a;
        FuenteFinanciamiento ff;
        Moneda m;
        BusinessException be;
        if (presupuestoTO.getAdquisicionSet() != null) {

            for (AdquisicionTO aTO : presupuestoTO.getAdquisicionSet()) {
                a = new Adquisicion();
                ff = fuenteFinanciamientoBean.obtenerFuentePorPk(aTO.getAdqFuente());
                if (ff != null && ff.getFueOrgFk().getOrgPk().equals(orgPk)) {
                    ff = fuenteFinanciamientoBean.obtenerFuentePorPk(aTO.getAdqFuente());
                    a.setAdqFuente(ff);
                } else {
                    be = new BusinessException();
                    be.getErrores().add("No se encuentra una fuente de financiamento válida para la adquisición");
                }
                m = monedaBean.obtenerMonedaPorId(aTO.getAdqMoneda());
                if (m != null) {
                    a.setAdqMoneda(m);
                } else {
                    be = new BusinessException();
                    be.getErrores().add("Moneda ingresada para la adquisición es inválida");
                }
                a.setAdqNombre(aTO.getAdqNombre());
                a.setAdqProcCompra(aTO.getAdqProcCompra());
                a.setAdqProcCompraGrp(aTO.getAdqProcCompraGrp());
                a.setAdqPreFk(pre);
                a = adquisicionBean.guardarAdquisicion(a, fichaTO.getFichaFk(), TipoFichaEnum.PROYECTO.id, null, orgPk);
                pre.getAdquisicionSet().add(a);
                a.setPagosSet(pagosFromAdquisicionTO(a, aTO, fichaTO.getFichaFk(), orgPk, e));

            }

            return pre.getAdquisicionSet();
        }
        return new HashSet<>();
    }

    private Set<Pagos> pagosFromAdquisicionTO(Adquisicion a, AdquisicionTO aTO, Integer proyPk, Integer orgPk, Entregables e) {
        Pagos p;
        a.setPagosSet(new HashSet<Pagos>());
        for (PagoTO pTO : aTO.getPagosSet()) {
            try {
                p = new Pagos();
                p.setPagAdqFk(a);
                p.setPagObservacion(pTO.getPagObservacion());
                p.setPagConfirmar(pTO.getPagConfirmar() != null && pTO.getPagConfirmar());
                p.setPagFechaPlanificada(SDF.parse(pTO.getPagFechaPanificada()));
                p.setPagFechaReal(SDF.parse(pTO.getPagFechaReal()));
                p.setPagImportePlanificado(pTO.getPagImportePlanificado());
                p.setPagImporteReal(pTO.getPagImporteReal());
                p.setPagTxtReferencia(pTO.getPagTxtReferencia());
                p.setEntregables(e);
                p = pagosBean.guardarPago(p, proyPk, null, orgPk);
                a.getPagosSet().add(p);

            } catch (ParseException ex) {
                Logger.getLogger(ServiciosBean.class.getName()).log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException(ex);
                be.getErrores().add("Formato de fecha de un pago es inválido.");
                throw be;
            }
        }
        return a.getPagosSet();
    }

    private void limpiarPresupuesto(Presupuesto pre, Integer proyPk, Integer orgPk) {

        for (Adquisicion a : pre.getAdquisicionSet()) {
            if (a.getPagosSet() != null) {
                for (Pagos p : a.getPagosSet()) {
                    p.setEntregables(null);
                    em.merge(p);
                    em.flush();
                    em.remove(p);
                }
            }
            em.remove(a);
        }
        em.flush();
    }

    private Entregables obtenerEntregableNivel0(Cronogramas cro) {
        if (cro != null) {
            for (Entregables e : cro.getEntregablesSet()) {
                if (e.getEntNivel() == 0) {
                    return e;
                }
            }
        }
        BusinessException be = new BusinessException();
        be.addError("Para ingresar un pago se requiere un entregable de nivel 0 en el cronograma");
        throw be;
    }

    private void desasociarCronogramaYPresupuesto(Proyectos proy) {

        /**
         * Poner referencias de los documentos a los entregables en null
         */
        for (Documentos d : proy.getDocumentosSet()) {
            d.setDocsEntregable(null);
            documentosBean.guardar(d);
        }

        /**
         * Poner referencias de los interesados a los entregables en null
         */
        for (Interesados i : proy.getInteresadosList()) {
            i.setIntEntregable(null);
            interesadosBean.guardar(i);
        }

        /**
         * Borrar calidad
         */
        for (Calidad c : calidadBean.obtenerPorProyId(proy.getProyPk())) {
            if (c.getCalEntFk() != null) {
                calidadBean.eliminar(c.getCalPk());
            }
        }

        /**
         * Borrar productos
         */
        for (Productos p : productosBean.obtenerProdPorProyPk(proy.getProyPk())) {
            productosBean.eliminarProducto(p.getProdPk());
        }

        /**
         * Borrar colaboradores (participantes
         */
        for (Participantes col : participantesBean.obtenerParticipantesPorProyPk(proy.getProyPk())) {
            participantesBean.eliminar(col.getPartPk());
        }

        /**
         * Poner referencia de los riesgos a los entregables en null
         */
        for (Riesgos r : proy.getRiesgosList()) {
            r.setRiskEntregable(null);
            riesgosBean.guardar(r, proy.getProyOrgFk().getOrgPk());
        }

    }

}
