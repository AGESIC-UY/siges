/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.validations.CronogramaValidaciones;
import com.sofis.data.daos.EtapaDAO;
import com.sofis.data.daos.FuenteFinanciamientoDAO;
import com.sofis.data.daos.MonedaDAO;
import com.sofis.data.daos.ObjetivoEstrategicoDAO;
import com.sofis.data.daos.OrganiIntProveDAO;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Etapa;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Personas;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyOtrosDatos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.validations.FichaValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.web.ws.gestion.data.AdquisicionTO;
import com.sofis.web.ws.gestion.data.CategoriaProyectoTO;
import com.sofis.web.ws.gestion.data.CronogramaTO;
import com.sofis.web.ws.gestion.data.EntregableTO;
import com.sofis.web.ws.gestion.data.LocalizacionTO;
import com.sofis.web.ws.gestion.data.PagoTO;
import com.sofis.web.ws.gestion.data.PresupuestoTO;
import com.sofis.web.ws.gestion.data.ProyectoTO;
import com.sofis.web.ws.gestion.data.RequestGuardarProyectoTO;
import com.sofis.web.ws.gestion.data.RequestListarCategorias;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sofis.entities.data.Departamentos;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
import com.sofis.web.ws.gestion.data.ProyReplanificacionTO;

/**
 *
 * @author bruno
 */
@Named
@Stateless(name = "ServiciosBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ServiciosBean {

    private static final Logger logger = Logger.getLogger(ServiciosBean.class.getName());
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
    @Inject
    private CategoriaProyectosBean categoriasProyectoBean;
    @Inject
    private DepartamentosBean departamentosBean;
    @Inject
    private ProcedimientoCompraBean procedimientoCompraBean;
    @Inject
    private IdentificadorGrpErpBean identificadorGrpErpBean;
    @Inject
    private LatlngBean latlngBean;

//    @Inject
//    private 
    //Ver lo de la excepcion "CloneNotSupportedException"
    public Integer guardarProyecto(RequestGuardarProyectoTO request) throws GeneralException, Exception {
        Organismos organismo = organismoBean.obtenerOrgPorToken(request.getToken());
        Integer orgPk = organismo.getOrgPk();

        ProyectoTO proyTo = request.getProyecto();
        FichaTO fichaTo = null;
        
        if(proyTo.getProyPk() == null){
            fichaTo = guardarFicha(request);
            proyTo.setProyPk(fichaTo.getFichaFk());
        }        

        if (proyTo.getFaseDestinoProy() == null) {
            fichaTo = guardarFicha(request);
            return fichaTo.getFichaFk();
        } else {
            Integer proyPk = null;
            Proyectos proy = proyectosBean.obtenerProyPorId(proyTo.getProyPk());
            
            int estActual = Estados.ESTADOS.valueOf(proy.getProyEstFk().getEstCodigo()).estado_id;
            int estDest = Estados.ESTADOS.valueOf(proyTo.getFaseDestinoProy()).estado_id;

            if (estActual == estDest) {
                fichaTo = guardarFicha(request);
                return fichaTo.getFichaFk();
            } else {

                if (estActual != Estados.ESTADOS.FINALIZADO.estado_id) {
                    while (estActual != estDest) {
                        //En este if estoy viendo que pasa cuando estoy avanzando en un estado.
                        if (estActual < estDest) {
                            //Primero guardo la Ficha.
                            fichaTo = guardarFicha(request);

                            //Me muevo al siguiente estado
                            proy = proyectosBean.guardarProyectoAprobacionServicio(fichaTo, orgPk);
                            proyPk = proy.getProyPk();

                        } //En este else me fijo que pasa cuando estoy retrocediendo de estado.
                        else {
                            ProyReplanificacionTO auxReplanTO = proyTo.getReplanifProy();
                            if ((estActual == Estados.ESTADOS.EJECUCION.estado_id) && (auxReplanTO == null)) {
                                // Creo una "Replanificación Dummy"
                                auxReplanTO = new ProyReplanificacionTO();
                                auxReplanTO.setProyreplanDesc("");
                                auxReplanTO.setProyreplanGenerarLineaBase(false);
                                auxReplanTO.setProyreplanHistorial(false);
                                
                                auxReplanTO.setProyreplanGenerarPresupuesto(false);
                                //auxReplanTO.setProyreplanGenerarProducto(false);
                                auxReplanTO.setProyreplanPermitEditar(false);
                            }
                            //Primero guardo la Ficha.
                            fichaTo = guardarFicha(request);

                            //Me muevo al estado anterior
                            proy = proyectosBean.guardarProyectoRetrocederEstadoServicio(fichaTo, orgPk, auxReplanTO);
                            proyPk = proy.getProyPk();
                        }
                        estActual = Estados.ESTADOS.valueOf(proy.getProyEstFk().getEstCodigo()).estado_id;
                    }

                    if (proyPk != null) {
                        return proyPk;
                    } else {
                        BusinessException be = new BusinessException();
                        be.getErrores().add("Hubo un error inesperado al realizar la operación.");
                        throw be;
                    }

                } else {
                    BusinessException be = new BusinessException();
                    be.getErrores().add("No se puede cambiar de estado cuando el proyecto se encuentra en estado \"Finalizado\".");
                    throw be;
                }
            }
        }
    }

    public FichaTO guardarFicha(RequestGuardarProyectoTO request) throws GeneralException {

        try {
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

            // Me fijo si el proyecto ya existe
            if (proyectoTO.getProyPk() != null) {
                Proyectos proy = proyectosBean.obtenerProyPorId(proyectoTO.getProyPk());
                //TODO si el proyecto está inactivo lanzar una excepción.
                desasociarCronogramaYPresupuesto(proy);
                proyectosBean.obtenerProyPorId(proyectoTO.getProyPk());
                
                // Como ya existe un proyecto, seteo la ficha con esos valores
                fichaTO = this.proyectoToFichaTO(proy, fichaTO);
            } else {
                fichaTO.setActivo(true);
                fichaTO.setEstado(new Estados(Estados.ESTADOS.INICIO.estado_id)); //los proyectos se crean en inicio y activos
            }

            fichaTO.setUltimaModificacion(new Date());
            fichaTO.setTipoFicha(TipoFichaEnum.PROYECTO.getValue());
            fichaTO.setPeso(1);
            
            /**
             * 12-10-17 Bruno: agregadas las localizaciones
             */
            //fichaTO.setLatLngProyList(new ArrayList<LatlngProyectos>());

            Areas usuArea = areasBean.obtenerAreasPorPk(proyectoTO.getAreaFk());
            if (usuArea == null || (usuArea != null && !usuArea.getAreaHabilitada())) {
                error = true;
                errores.add("No se encuentra el área especificada");
            } else {
                fichaTO.setAreaFk(usuArea);
            }

            if (proyectoTO.getSemaforoAmarillo() == null) {
                Configuracion confRiesgoTiempoLimiteAmarillos = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, orgPk);
                if (confRiesgoTiempoLimiteAmarillos != null) {
                    fichaTO.setSemaforoAmarillo(Integer.valueOf(confRiesgoTiempoLimiteAmarillos.getCnfValor()));
                }
            } else {
                fichaTO.setSemaforoAmarillo(proyectoTO.getSemaforoAmarillo());
            }

            if (proyectoTO.getSemaforoRojo() == null) {
                Configuracion confRiesgoTiempoLimiteRojo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, orgPk);
                if (confRiesgoTiempoLimiteRojo != null) {
                    fichaTO.setSemaforoRojo(Integer.valueOf(confRiesgoTiempoLimiteRojo.getCnfValor()));
                }
            } else {
                fichaTO.setSemaforoRojo(proyectoTO.getSemaforoRojo());
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
            fichaTO.setUsrAdjuntoFk(proyUsrAdjunto);
            fichaTO.setUsrGerenteFk(proyUsrGerente);
            fichaTO.setUsrPmofedFk(proyUsrPmofed);
            fichaTO.setUsrSponsorFk(proyUsrSponsor);

            /**
             * Agregados 05-11-17
             */
            ObjetivoEstrategicoDAO objetivoEstrategicoDAO = new ObjetivoEstrategicoDAO(em);
            OrganiIntProveDAO organiIntProveDAO = new OrganiIntProveDAO(em);
            ProgramasDAO programasDAO = new ProgramasDAO(em);
            EtapaDAO etapaDAO = new EtapaDAO(em);

            fichaTO.setObjetivoEstrategico(proyectoTO.getObjetivoEstrategico() != null ? objetivoEstrategicoDAO.findById(ObjetivoEstrategico.class, proyectoTO.getObjetivoEstrategico()) : null);
            if (fichaTO.getOtrosDatos() == null) {
                fichaTO.setOtrosDatos(new ProyOtrosDatos());
            }
            fichaTO.getOtrosDatos().setProyOtrInsEjeFk(proyectoTO.getProyOtrInsEjeFk() != null ? organiIntProveDAO.findById(OrganiIntProve.class, proyectoTO.getProyOtrInsEjeFk()) : null);
            fichaTO.getOtrosDatos().setProyOtrContFk(proyectoTO.getProyOtrContFk() != null ? organiIntProveDAO.findById(OrganiIntProve.class, proyectoTO.getProyOtrContFk()) : null);
            fichaTO.setPublicable(proyectoTO.getPublicable() != null && proyectoTO.getPublicable());
            fichaTO.setProgFk(proyectoTO.getProyPk() != null ? programasDAO.findById(Programas.class, proyectoTO.getProyPk()) : null);
            fichaTO.getOtrosDatos().setProyOtrEtaFk(proyectoTO.getProyOtrEtaFk() != null ? etapaDAO.findById(Etapa.class, proyectoTO.getProyOtrEtaFk()) : null);

            /*
            * 12-03-18 Nico: Después de obtener los OtrosDatos del Proyecto, le setteo la categoría
            */
            
            if (proyectoTO.getProyOtrCatFk() != null) {
                CategoriaProyectos auxCatProyInsert = categoriasProyectoBean.obtenerPorId(proyectoTO.getProyOtrCatFk());
                fichaTO.getOtrosDatos().setProyOtrCatFk(auxCatProyInsert);
            }

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
            
            /*
            *   12-03-18 Nico: Les paso las localizaciones por el WebServices.
            */
            
            if(proyectoTO.getLatLngProyList() != null){
                if(obj.getLatLngProyList() != null){
                    for(LatlngProyectos iterLoc : obj.getLatLngProyList()){
                        latlngBean.eliminarFromWS(iterLoc);
                    }
                    obj.setLatLngProyList(new HashSet<LatlngProyectos>());
                }
            
                this.agregarLocalizaciones(proyectoTO.getLatLngProyList(), obj);
                //obj.setLatLngProyList(listaLocalizaciones);
                //fichaTO.setLatLngProyList(listaLocalizaciones);

            }

            
            if (proyectoTO.getCronograma() != null) {
                cro = obj.getProyCroFk();
                if (cro == null) {
                    /*
                    *   22-03-18 Nico: Se cambia el tipo de retorno para reciclar la operación
                    */

                    return fichaTO;
                }
                cro = cronogramaBean.guardar(cro);
                obj.setProyCroFk(cro);
                obj = proyectosBean.obtenerProyPorId(obj.getProyPk());
                cro = cronogramaFromCronogramaTO(orgPk, obj, proyectoTO.getCronograma());
            }else{
                fichaTO.getCroFk().getEntregablesSet().clear();
                obj.getProyCroFk().getEntregablesSet().clear();
            }

            if (proyectoTO.getPresupuesto() != null) {
                try {
                    pre = obj.getProyPreFk();
                    if (pre == null) {
                        /*
                        *   22-03-18 Nico: Se cambia el tipo de retorno para reciclar la operación
                        */

                        return fichaTO;
                    }
                    pre.getAdquisicionSet().clear();
                    pre = presupuestoFromPresupuestoTO(orgPk, fichaTO, obj, proyectoTO.getPresupuesto(), obtenerEntregableNivel0(cro));
                    obj = (Proyectos) presupuestoBean.guardarPresupuesto(pre, obj.getProyPk(), TipoFichaEnum.PROYECTO.id, null, orgPk);
                } catch (GeneralException ex) {
                    throw ex;
                } catch (DAOGeneralException ex) {
                    throw new TechnicalException(ex);
                } catch (Exception ex) {
                    throw new TechnicalException(ex);
                }
            }else{
                fichaTO.getPreFk().getAdquisicionSet().clear();
                obj.getProyPreFk().getAdquisicionSet().clear();
            }
            Proyectos proy = proyectosBean.obtenerProyPorId(proyectoTO.getProyPk());
            fichaTO = this.proyectoToFichaTO(proy, fichaTO);
            FichaValidacion.validar(fichaTO, orgPk);

            /*
            *   Se cambia el tipo de retorno para reciclar la operación
            */
            return fichaTO;

        } catch (DAOGeneralException ex) {
            throw new TechnicalException(ex);
        }

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
//          fichaTO.setLatlngProy(proy.getProyLatlngFk() != null ? proy.getProyLatlngFk() : new LatlngProyectos());
            /**
             * 12-10-17 Bruno: agregadas las localizaciones
             */

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
            entregablesList = EntregablesUtils.ajustarFechas(entregablesList);
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

    private Presupuesto presupuestoFromPresupuestoTO(Integer orgPk, FichaTO fichaTO, Proyectos proy, PresupuestoTO presupuestoTO, Entregables e) throws Exception {
        Presupuesto pre = proy.getProyPreFk();
        Set<Adquisicion> adquisicionesSet = pre.getAdquisicionSet();
        adquisicionesSet.clear();
        pre.setAdquisicionSet(adquisicionesFromPresupuestoTO(orgPk, fichaTO, pre, presupuestoTO, e));
        pre.setPreBase(presupuestoTO.getPreBase());

        MonedaDAO mDAO = new MonedaDAO(em);
        FuenteFinanciamientoDAO ffDAO = new FuenteFinanciamientoDAO(em);
        Moneda m = null;
        if (presupuestoTO.getPreMoneda() != null) {
            m = mDAO.findById(Moneda.class, presupuestoTO.getPreMoneda());
        }
        FuenteFinanciamiento ff = null;
        if (presupuestoTO.getFuenteFinanciamiento() != null) {
            ff = ffDAO.findById(FuenteFinanciamiento.class, presupuestoTO.getFuenteFinanciamiento());
            
            if(ff == null){
                BusinessException be = new BusinessException();
                be.getErrores().add("No existe una Fuente de Financiamiento con el Identificador ingresado.");
                throw be;
            }
        }
        
        if ((ff != null) && (ff.getFueOrgFk() != null) && !(ff.getFueOrgFk().getOrgPk().equals(orgPk))) {
            BusinessException be = new BusinessException();
            be.getErrores().add("Fuente de financiamiento del presupuesto aprobado no pertenece al Organismo del proyecto.");
            throw be;
        }
       
        pre.setPreMoneda(m);
        pre.setFuenteFinanciamiento(ff);
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
                
                
                /*
                *   24-05-2018 Nico: Se comenta la parte de manejo de String de los Procedimientos de Compra ya que ahora
                *           se maneja como una entidad nueva. Para este caso del servicio web, se deja que se agregue un 
                *           un string con el nombre del procedimiento de compra, y en el caso de que el procedimiento de 
                *           compra no exista para ese organismo, se devuelve un error.
                */                 
//                a.setAdqProcCompra(aTO.getAdqProcCompra());

                if(aTO.getAdqProcCompra() != null){
                    ProcedimientoCompra proc = procedimientoCompraBean.obtenerProcedimientoCompraPorNombre(aTO.getAdqProcCompra(), orgPk);
                    if(proc != null){
                        a.setAdqProcedimientoCompra(proc);
                    }else{
                        be = new BusinessException();
                        be.getErrores().add("No se encontró el procedimiento de compra ingresado en el organismo");                        
                    }
                }else{
                    a.setAdqProcedimientoCompra(null);
                }                


                final FiltroIdentificadorGrpErpTO filtroIdentificadorGrpErpTO = new FiltroIdentificadorGrpErpTO();
                filtroIdentificadorGrpErpTO.setNombre(aTO.getAdqProcCompraGrp());
                filtroIdentificadorGrpErpTO.setOrganismo(this.organismoBean.obtenerOrgPorId(orgPk, false));
                a.setAdqIdGrpErpFk(this.identificadorGrpErpBean.obtenerPorFiltro(filtroIdentificadorGrpErpTO).get(0));
                a.setAdqPreFk(pre);
                /*
                                * 12-03-18 Nico: Se agregan esas dos lineas para que no de problemas
                                *               por los nuevos atributos de la entidad.
                 */
                a.setAdqCompartida(false);
                a.setSsUsuarioCompartida(null);

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
                /*
                                * 12-03-18 Nico: Se agregan esas dos lineas para que no de problemas
                                *               por los nuevos atributos de la entidad.
                 */
                p.setPagGasto(0);
                p.setPagInversion(0);

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
            documentosBean.guardar(d, proy.getProyOrgFk().getOrgPk());
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

    public List<CategoriaProyectoTO> obtenerCategoriasProyecto(RequestListarCategorias request) throws GeneralException {
        List<CategoriaProyectoTO> retorno = new ArrayList<CategoriaProyectoTO>();
        /**
         * Obtener organismo a partir del token. Controlar de que exista un
         * organismo con ese token
         */
        Organismos organismo = organismoBean.obtenerOrgPorToken(request.getToken());

        if (organismo.getOrgPk().equals(request.getIdOrg())) {
            List<CategoriaProyectos> auxCatProy = categoriasProyectoBean.obtenerPrimarias(request.getIdOrg());

            for (CategoriaProyectos iterCatProy : auxCatProy) {
                CategoriaProyectoTO auxInsert = new CategoriaProyectoTO(iterCatProy);
                retorno.add(auxInsert);
            }
        }

        return retorno;
    }

    public void agregarLocalizaciones(List<LocalizacionTO> latLngProyList, Proyectos proy) {
        
        if (latLngProyList != null) {
            for (LocalizacionTO iterLoc : latLngProyList) {
                Departamentos auxDep = departamentosBean.obtenerDepPorPk(iterLoc.getLatlangDepFk());
                LatlngProyectos auxInsert = new LatlngProyectos(iterLoc, auxDep);
                
                auxInsert.setProyecto(proy);

                latlngBean.guardar(auxInsert);
                
            }
        }
        
        List<LatlngProyectos> auxListLoc = latlngBean.obtenerPorProyecto(proy.getProyPk());
        Set<LatlngProyectos> auxSetPersist = new HashSet<LatlngProyectos>();
        
        for(LatlngProyectos iterLoc : auxListLoc){
            auxSetPersist.add(iterLoc);            
        }
        proy.setLatLngProyList(auxSetPersist);

    }
}
