package com.sofis.business.ejbs;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.MailsTemplateUtils;
import com.sofis.business.utils.ProgProyUtils;
import com.sofis.business.utils.Utils;
import com.sofis.business.validations.ProyectosValidacion;
import com.sofis.data.daos.DocFileDao;
import com.sofis.data.daos.DocumentosDao;
import com.sofis.data.daos.EntHistLineaBaseDAO;
import com.sofis.data.daos.LatlngProyectosDAO;
import com.sofis.data.daos.NotificacionEnvioDAO;
import com.sofis.data.daos.ProySitactHistoricoDAO;
import com.sofis.data.daos.ProyectoExpVisualizadorDAO;
import com.sofis.data.daos.ProyectosConCronogramaDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.EstadoPublicacionCodigos;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.ConstantesNotificaciones;
import com.sofis.entities.constantes.MailVariables;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Devengado;
import com.sofis.entities.data.DocFile;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.EntHistLineaBase;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.EstadosPublicacion;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.NotificacionEnvio;
import com.sofis.entities.data.NotificacionInstancia;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.ProdMes;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.ProyIndicesPre;
import com.sofis.entities.data.ProyOtrosDatos;
import com.sofis.entities.data.ProyPublicaHist;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.data.ProySitactHistorico;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.entities.data.TipoDocumentoInstancia;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroExpVisuaTO;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.entities.utils.JAXBUtils;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.MailException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import com.sofis.web.ws.gestion.data.ProyReplanificacionTO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.agesic.siges.visualizador.web.ws.ProyectoImportado;
import org.apache.commons.io.FileUtils;
import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProyectosBean")
@LocalBean
public class ProyectosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    private static final Logger logger = Logger.getLogger(ProyectosBean.class.getName());

    private static final Semaphore MUTEX_MOVER_ARCHIVOS = new Semaphore(1, true);

    @Inject
    private DatosUsuario du;
    @EJB
    private ProgramasBean programasBean;
    @EJB
    private RiesgosBean riesgosBean;
    @EJB
    private DocumentosBean documentosBean;
    @EJB
    private MailBean mailBean;
    @EJB
    private SsUsuarioBean ssUsuarioBean;
    @EJB
    private ProgramasProyectosBean programasProyectosBean;
    @EJB
    private EntregablesBean entregablesBean;
    @EJB
    private ProyReplanificacionBean proyReplanificacionBean;
    @EJB
    private PresupuestoBean presupuestoBean;
    @EJB
    private AdquisicionBean adquisicionBean;
    @EJB
    private CronogramasBean cronogramasBean;
    @EJB
    private InteresadosBean interesadosBean;
    @EJB
    private TipoDocumentoInstanciaBean tipoDocumentoInstanciaBean;
    @EJB
    private NotificacionInstanciaBean notificacionInstanciaBean;
    @EJB
    private DevengadoBean devengadoBean;
    @EJB
    private RegistrosHorasBean registrosHorasBean;
    @EJB
    private GastosBean gastosBean;
    @EJB
    private AreaTematicaBean areaTematicaBean;
    @EJB
    private AreasBean areasBean;
    @EJB
    private ProductosBean productosBean;
    @EJB
    private ProdMesBean prodMesBean;
    @EJB
    private ProyOtrosDatosBean proyOtrosDatosBean;
    @EJB
    private MediaProyectosBean mediaProyectosBean;
    @EJB
    private ParticipantesBean participantesBean;
    @Inject
    private CalidadBean calidadBean;
    @Inject
    private ProyIndicesBean proyIndicesBean;
    @Inject
    private EstadosBean estadosBean;
    @Inject
    private ConfiguracionBean configuracionBean;
    @Inject
    private ProgIndicesBean progIndicesBean;
    @Inject
    private EstadosPublicacionBean estadosPublicacionBean;
    @Inject
    private ExportarVisualizadorBean exportarVisualizadorBean;
    @Inject
    private ProyPublicaHistBean proyPublicaHistBean;
//	@Inject
//	private NotificacionEnvioBean notificacionEnvioBean;
    @Inject
    private OrganismoBean organismoBean;
    
    //private String usuario;
    //private String origen;
    public void flush() {
        this.em.flush();
    }

    @PostConstruct
    public void init() {
        //usuario = du.getCodigoUsuario();
        //origen = du.getOrigen();
    }

    private Proyectos guardar(Proyectos proy, SsUsuario usuario, Integer orgPk, boolean indicadores) throws GeneralException {
        //logger.info("Guardar Proyecto. ");
        ProyectosValidacion.validar(proy, usuario, orgPk);
        ProyectosDAO dao = new ProyectosDAO(em);

        try {
            //TODO para evitar el unsaved transient object, verificar porque no ocurre con estado y si con usuario
            SsUsuario usu;
            if (proy.getProyUsrAdjuntoFk() != null) {
                usu = em.find(SsUsuario.class, proy.getProyUsrAdjuntoFk().getUsuId());
                proy.setProyUsrAdjuntoFk(usu);
            }

            if (proy.getProyUsrGerenteFk() != null) {
                usu = em.find(SsUsuario.class, proy.getProyUsrGerenteFk().getUsuId());
                proy.setProyUsrGerenteFk(usu);
            }

            if (proy.getProyUsrPmofedFk() != null) {
                usu = em.find(SsUsuario.class, proy.getProyUsrPmofedFk().getUsuId());
                proy.setProyUsrPmofedFk(usu);
            }

            if (proy.getProyUsrSponsorFk() != null) {
                usu = em.find(SsUsuario.class, proy.getProyUsrSponsorFk().getUsuId());
                proy.setProyUsrSponsorFk(usu);
            }

            if (proy.getProyCroFk() != null && proy.getProyCroFk().getEntregablesSet() != null) {
                for (Entregables e : proy.getProyCroFk().getEntregablesSet()) {
                    if (e.getCoordinadorUsuFk() != null) {
                        usu = em.find(SsUsuario.class, e.getCoordinadorUsuFk().getUsuId());
                        e.setCoordinadorUsuFk(usu);
                    }
                }
            }

            proy = dao.update(proy, this.du.getCodigoUsuario(), du.getOrigen());

            if (proy != null) {
                if (proy.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                    proyReplanificacionBean.inactivarSolicitud(proy.getProyPk());
                }
                if (indicadores) {
                    actualizarIndProyProg(proy, usuario, orgPk, true);
                }

            }
            return proy;

        } catch (BusinessException be) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public void actualizarFecha(Integer proyPk, SsUsuario usuario) {
        if (proyPk != null && usuario != null) {
            Proyectos proy = obtenerProyPorId(proyPk);
            if (proy != null && isUsuarioGerenteOAdjuntoProy(proy, usuario)) {
                proy.setProyFechaAct(new Date());
                ProyectosDAO dao = new ProyectosDAO(em);
                dao.actualizarFecha(proyPk);
            }
        }
    }

    public void actualizarIndProyProg(Integer proyPk, SsUsuario usuario, Integer orgPk, Boolean actualizarPrograma) {
        Proyectos proy = obtenerProyPorId(proyPk);
        actualizarIndProyProg(proy, usuario, orgPk, actualizarPrograma);
    }

    /**
     * Actializa los indicadores del proyecto aportado y del programa si es que
     * tiene.
     *
     * @param proy
     * @param usuario
     * @param orgPk
     */
    public void actualizarIndProyProg(Proyectos proy, SsUsuario usuario, Integer orgPk, Boolean actualizarPrograma) {
        guardarIndicadores(proy, false, true, orgPk, null, false, false);
        if (actualizarPrograma) {
            programasBean.actualizarProgramaPorProyectos(proy, usuario, orgPk);
        }
    }

    public Proyectos obtenerProyPorId(Integer id, Boolean loadAreasTem, Boolean loadAreasRestr, Boolean loadDocs, Boolean loadInteresados, Boolean loadRiesgos, Boolean loadCalidad) throws GeneralException {
        Proyectos proy = obtenerProyPorId(id);

        if (loadAreasTem != null && loadAreasTem) {
            proy.getAreasTematicasSet().isEmpty();
        }
        if (loadAreasRestr != null && loadAreasRestr) {
            proy.getAreasRestringidasSet().isEmpty();
        }
        if (loadDocs != null && loadDocs) {
            proy.getDocumentosSet().isEmpty();
        }
        if (loadInteresados != null && loadInteresados) {
            proy.getInteresadosList().isEmpty();
        }
        if (loadRiesgos != null && loadRiesgos) {
            proy.getRiesgosList().isEmpty();
        }
        if (loadCalidad != null && loadCalidad) {
            proy.getCalidadList().isEmpty();
        }

        return proy;
    }

    public Proyectos obtenerProyPorId(Integer id) throws GeneralException {
        return obtenerProyPorId(id, null);
    }

    public Proyectos obtenerProyPorId(Integer id, Boolean loadProyPublicados) throws GeneralException {
        if (id != null) {
            ProyectosDAO proyDao = new ProyectosDAO(em);
            try {
                Proyectos proy = proyDao.findById(Proyectos.class, id);

                if (loadProyPublicados != null && loadProyPublicados) {
                    proy.getProyPublicaHist().isEmpty();
                }

                return proy;
            } catch (NoResultException e) {
                logger.log(Level.SEVERE, null, e);
                return null;
            } catch (Exception ex) {
                //Las demÃƒÆ’Ã‚Â¡s excepciones se consideran tÃƒÆ’Ã‚Â©cnicas
                logger.log(Level.SEVERE, null, ex);
                TechnicalException ge = new TechnicalException(ex);
                ge.addError(ex.getMessage());
                throw ge;
            }
        }
        return null;
    }

    public Proyectos guardarProyecto(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Proyectos proy = (Proyectos) ProgProyUtils.fichaTOToProgProy(fichaTO);
        return guardarProyecto(proy, usuario, orgPk);
    }

    public Proyectos guardarProyecto(Proyectos proy, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (proy != null) {
            guardarHistoricoSitAct(proy, usuario);
            guardarLineaBaseYHistorico(proy);
            boolean cambioEstado = false;
            
            if (proy.getProyPk() == null) {
                /*
                *       22-03-18 Nico: Agrego este chequeo de si el usuario es distinto de null, porque cuando es null
                *                   este método es incovocado del servicio web y ya esta setteado el estado.
                */
                if(usuario != null){
                    proy.setProyFechaCrea(new Date());
                    proy.setProyFechaAct(new Date());

                    if (proy.getProyUsrPmofedFk() != null
                            && proy.getProyUsrPmofedFk().equals(usuario)) {
                        proy.setProyEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
                        proy.setProyEstPendienteFk(null);

                    } else if (usuario != null && usuario.isUsuarioPMOT(orgPk)) {
                        proy.setProyEstFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOF.estado_id));
                        proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOF.estado_id));
                    } else {
                        proy.setProyEstFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
                        proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
                    }
                }
                
            /*
            *    Este else es del if que se fija si (proy.getProyPk == null), es decir, se fija si el proyecto es nuevo.
            */
                
            }else {
                Proyectos proyPersistido = obtenerProyPorId(proy.getProyPk());
                
                proy.setProyFechaAct(new Date());
                
                if (!proyPersistido.getProyEstFk().equals(proy.getProyEstFk())) {
                    proy.setProyFechaEstadoAct(new Date());
                    cambioEstado = true;
                    //proy.setProyFechaAct(new Date());
                }

                /*
                *       El if de abajo solo deja actualizar la fecha cuando el usuario es Gerente o Adjunto del proyecto,
                *   y si hay cambios del PMOT, sino no hace nada. Por ejemplo, si un PMOF hace un cambio, no se actualiza
                *   la fecha.
                
                */
                
//                if (isUsuarioGerenteOAdjuntoProy(proy, usuario)) {
//                    if (!tieneCambiosConfPMOT(proy, proyPersistido)) {
//                        proy.setProyFechaAct(new Date());
//                    }
//                }

                /**
                 * Agregado para verificar que hay cambios sobre los datos
                 * publicables.
                 */
                try {
                    if(usuario != null){
                        if (tieneCambiosPublicable(proy, proyPersistido)) {
                            proy.setProyFechaActPub(new Date());
                        }
                    }
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "No es posible verficiar los cambios sobre los campos publicables: " + ex.getMessage());
                    logger.log(Level.FINEST, "No es posible verficiar los cambios sobre los campos publicables: " + ex.getMessage(), ex);
                }
            }

            if (proy.getActivo() == null) {
                proy.setActivo(true);
            }
            if (proy.getProyPublicable() == null) {
                proy.setProyPublicable(false);
            }

            boolean hasDoc = false;
            try {
                hasDoc = CollectionsUtils.isNotEmpty(proy.getDocumentosSet());
            } catch (Exception e) {
                logger.log(Level.FINEST, e.getMessage(), e);
            }
            if (hasDoc) {
                List<Documentos> docsRemove = new ArrayList<>();
                for (Documentos doc : proy.getDocumentosSet()) {
                    if (doc.getDocsNombre().equalsIgnoreCase(LabelsEJB.getValue("ficha_doc_pendiente", orgPk))) {
                        docsRemove.add(doc);
                    } else if (doc.getDocsFecha() == null) {
                        doc.setDocsFecha(new Date());
                    }
                }
                proy.getDocumentosSet().removeAll(docsRemove);
            }

            if (proy.getActivo() == null) {
                proy.setActivo(true);
            }

            controlarEstadoPublicacion(proy);

            proy = guardar(proy, usuario, orgPk, true);

            /*
            *       22-03-18 Nico: Agrego este chequeo de si el usuario es distinto de null, porque cuando es null
            *                   este método es incovocado del servicio web y ya esta setteado el estado.
            */            
            if ((proy != null) && (usuario != null)) {
                mailPostGuardar(proy, cambioEstado, orgPk);
            }
        }
        return proy;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void mailPostGuardar(Proyectos proy, boolean cambioEstado, Integer orgPk) {
        if (proy != null) {
            if (cambioEstado) {
                List<SsUsuario> usuariosDest = new ArrayList<>();
                usuariosDest.add(proy.getProyUsrGerenteFk());
                usuariosDest.add(proy.getProyUsrAdjuntoFk());
                String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosDest);
                try {
                    mailBean.comunicarCambioEstado(orgPk, proy, destinatarios);
                } catch (MailException me) {
                    logger.log(Level.SEVERE, me.getMessage(), me);
                }
            }

            if (proy.getProyEstFk().isPendientes()) {
                List<SsUsuario> usuariosDest = new ArrayList<>();
                if (proy.getProyEstFk().isEstado(Estados.ESTADOS.PENDIENTE_PMOT.estado_id)) {
                    String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
                    boolean[] ascUsuarios = new boolean[]{true, true, true, true};
                    List<SsUsuario> listUsu = ssUsuarioBean.obtenerUsuariosPorRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk, ordenUsuarios, ascUsuarios);
                    if (listUsu != null) {
                        usuariosDest.addAll(listUsu);
                    }
                } else if (proy.getProyEstFk().isEstado(Estados.ESTADOS.PENDIENTE_PMOF.estado_id)
                        && proy.getProyUsrPmofedFk() != null) {
                    usuariosDest.add(proy.getProyUsrPmofedFk());
                }
                String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosDest);
                try {
                    mailBean.comunicarProgProyPendiente(orgPk, proy, destinatarios);
                } catch (MailException me) {
                    logger.log(Level.SEVERE, me.getMessage(), me);
                }
            }
        }
    }

    public void guardarHistoricoSitAct(Proyectos proy, SsUsuario usuario) {
        if (proy != null
                && proy.getProySituacionActual() != null
                && proy.getProyectoOriginal() != null) {

            int eq = proy.getProyectoOriginal().getProySituacionActual() == null ? -1 : proy.getProySituacionActual().trim().toLowerCase().compareTo(proy.getProyectoOriginal().getProySituacionActual().trim().toLowerCase());
            if (eq != 0) {
                if (!StringsUtils.isEmpty(proy.getProySituacionActual())
                        && proy.getProySituacionActual().length() > 20000) {
                    BusinessException be = new BusinessException();
                    be.addError(Utils.mensajeLargoCampo("ficha_situacionActualFicha", proy.getProyOrgFk().getOrgPk(), 20000));
                    throw be;
                }

                ProySitactHistorico proySitActH = new ProySitactHistorico();
                proySitActH.setProySitactDesc(proy.getProySituacionActual());
                proySitActH.setProySitactFecha(new Date());
                proySitActH.setProySitactUsuario(usuario);
                proySitActH.setProyectoFk(new Proyectos(proy.getProyPk()));

                ProySitactHistoricoDAO dao = new ProySitactHistoricoDAO(em);
                try {
                    dao.persist(proySitActH, this.du.getCodigoUsuario(), du.getOrigen());
                } catch (DAOGeneralException ex) {
                    Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_FICHA_HIST_SIT_ACT_GUARDAR);
                    throw be;
                }
            }
        }
    }

    public void guardarLineaBaseYHistorico(Proyectos proy) {
        if (proy != null && proy.getProyectoOriginal() != null
                && proy.getProyCroFk() != null
                && (!proy.getProyectoOriginal().getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id)
                && proy.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id))) {

//            ProyReplanificacion replan = proyReplanificacionBean.obtenerSolicitudActiva(proy.getProyPk());
            ProyReplanificacion replan = proyReplanificacionBean.obtenerUltimaSolicitud(proy.getProyPk());

            if (replan != null && replan.getProyreplanHistorial()) {

                EntHistLineaBaseDAO dao = new EntHistLineaBaseDAO(em);
                Date date = new Date();
                EntHistLineaBase entHistLineaBase;
                for (Entregables ent : proy.getProyCroFk().getEntregablesSet()) {
                    /**
                     * Solo guarda un histórico
                     */
                    
                    /*
                    *       06-11-2018 Nico: Cuando se replanifica y se agregan entregables, estos tienen la línea base en null,
                    *   por lo que al guardar el histórico devolvía una excepción. El siguiente if evita ese caso.     
                    */
                    if (replan != null && replan.getProyreplanHistorial() && replan.getProyreplanActivo()) {
                        
                        if(ent.getEntInicioLineaBase() != null && ent.getEntFinLineaBase() != null){
                            entHistLineaBase = new EntHistLineaBase();
                            entHistLineaBase.setEnthistFecha(date);
                            entHistLineaBase.setEnthistEntregableFk(ent);
                            entHistLineaBase.setEnthistInicioLineaBase(ent.getEntInicioLineaBase());
                            entHistLineaBase.setEnthistFinLineaBase(ent.getEntFinLineaBase());
                            entHistLineaBase.setEnthistDuracion(ent.getEntDuracionLineaBase());
                        
                            if (replan.getProyreplanActivo()) {
                                entHistLineaBase.setEnthistReplanFk(replan);
                            }
                            try {
                                dao.update(entHistLineaBase, du.getCodigoUsuario(), du.getOrigen());
                            } catch (DAOGeneralException ex) {
                                Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }

                }

            }

            /**
             * 13-12-2016 Se debe setear la línea base del cronograma luego de
             * guardar el histórico. El histórico debe ser una foto de la línea
             * base actual antes de generarse una nueva.
             */
            setearLineaBaseCronograma(proy, replan);
            setearLineaBasePagos(proy, replan);
            setearLineaBaseProductos(proy, replan);
        }
    }
    
    /*
    *   21-03-18 Nico: Creo este método para poder generar la aprobación del proyecto
    *           desde el web service.
    */

    public Proyectos guardarProyectoAprobacionServicio(FichaTO fichaTO, Integer orgPk) throws GeneralException {
        Proyectos proy = (Proyectos) ProgProyUtils.fichaTOToProgProy(fichaTO);
        proy.setProyectoOriginal((Proyectos) proy.clone());
        programasProyectosBean.solAprobacionCambioEstadoServicio(proy, orgPk);

        proy = guardarProyecto(proy, null, orgPk);
        return proy;
    }

    public Proyectos guardarProyectoAprobacion(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Proyectos proy = (Proyectos) ProgProyUtils.fichaTOToProgProy(fichaTO);
        return guardarProyectoAprobacion(proy, usuario, orgPk);
    }

    public Proyectos guardarProyectoAprobacion(Proyectos proy, SsUsuario usuario, Integer orgPk) throws GeneralException {
        proy.setProyectoOriginal((Proyectos) proy.clone());
        programasProyectosBean.solAprobacionCambioEstado(proy, usuario, orgPk);

        proy = guardarProyecto(proy, usuario, orgPk);

        if (proy != null) {
            if (proy.getProyEstPendienteFk() != null && !proy.getProyEstFk().isPendientes()) {
                String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
                boolean[] ascUsuarios = new boolean[]{true, true, true, true};
                
                /*
                *   Se chequea la configuración para conocer si el PMOF es el encargado de aprobar las solicitudes de cambio de fase.
                */
                
                Configuracion cnfAprobPMOF = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.APROBACION_PMOF, orgPk);

                String[] destinatarios = null;

                List<SsUsuario> usuarios = ssUsuarioBean.obtenerUsuariosPorRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk, ordenUsuarios, ascUsuarios);
                
                if(cnfAprobPMOF != null && cnfAprobPMOF.getCnfValor().equals("true")){
                    usuarios.add(proy.getProyUsrPmofedFk());   
                }
                destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuarios);
                
                /**
                 * 26-09-2017 Requerimiento RQF-25: Ficha de proyecto -
                 * notificar solicitud de cambio de fase
                 */
                this.enviarNotificacion(ConstantesNotificaciones.NOT_COD_CAMBIO_FASE_PROY_1, proy, orgPk);

                try {
                    mailBean.comunicarSolicitudAprobacion(orgPk, proy, destinatarios);
                } catch (MailException me) {
                    logger.log(Level.SEVERE, me.getMessage(), me);
                }
            }
        }
        return proy;
    }

    private void setearLineaBaseCronograma(Proyectos proy, ProyReplanificacion replan) {
        if (proy.getProyCroFk() != null && proy.getProyCroFk().getEntregablesSet() != null) {
            Cronogramas cro = proy.getProyCroFk();
            if (replan == null || replan.isProyreplanGenerarLineaBase()) {
                for (Entregables ent : cro.getEntregablesSet()) {
                    ent.setEntInicioLineaBase(ent.getEntInicio());
                    ent.setEntFinLineaBase(ent.getEntFin());
                    ent.setEntDuracionLineaBase(ent.getEntDuracion());
                }
            } else {
                for (Entregables ent : cro.getEntregablesSet()) {
                    // si es nueva la tarea
                    if (ent.getEntInicioLineaBase() == null || ent.getEntInicioLineaBase() == 0) {
                        ent.setEntInicioLineaBase(ent.getEntInicio());
                        ent.setEntFinLineaBase(ent.getEntFin());
                        ent.setEntDuracionLineaBase(ent.getEntDuracion());
                    }
                }
            }
        

            /*
            *   07-06-2018 Nico: Se debe analizar que la línea base de los entregables padres se corresponda
            *        a la de los hijos, es decir, el fin sea el último fin de los hijos, y el inicio el primer
            *        inicio de los hijos.
            */

            List<Entregables> entSetAux = EntregablesUtils.ajustarFechas(cro.getEntregablesSet());
            
            cro.getEntregablesSet().clear();
            cro.getEntregablesSet().addAll(entSetAux);
        }
    }

    /*
        *   20-03-18 Nico: Agrego esta operación para poder mantener los pagos reales cuando se pase de 
        *               Planificación a Ejecución y se quiera mantener la línea base.
     */
    private void setearLineaBasePagos(Proyectos proy, ProyReplanificacion replan) {

        List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorProy(proy.getProyPk());

        if (replan == null || replan.isProyreplanGenerarPresupuesto()) {
            for (Adquisicion iterAdq : adqList) {
                for (Pagos iterPago : iterAdq.getPagosSet()) {
                    if (!iterPago.isPagConfirmado()) {
                        iterPago.setPagFechaReal(iterPago.getPagFechaPlanificada());
                        iterPago.setPagImporteReal(iterPago.getPagImportePlanificado());
                    } 
                }
                if (CollectionsUtils.isNotEmpty(iterAdq.getDevengadoList())) {
                        for (Devengado dev : iterAdq.getDevengadoList()) {
                                dev.setDevReal(dev.getDevPlan());
                        }
                }  
            }
        }else {
            for (Adquisicion iterAdq : adqList) {
                for (Pagos iterPago : iterAdq.getPagosSet()) {
                    // si es nueva la tarea

                    if (iterPago.getPagFechaReal() == null && iterPago.getPagImporteReal() == null) {
                        iterPago.setPagFechaReal(iterPago.getPagFechaPlanificada());
                        iterPago.setPagImporteReal(iterPago.getPagImportePlanificado());                        
                    }
                }
                if (CollectionsUtils.isNotEmpty(iterAdq.getDevengadoList())) {
                    for (Devengado dev : iterAdq.getDevengadoList()) {
                        if(dev.getDevReal() == null){
                            dev.setDevReal(dev.getDevPlan());
                        }
                    }
                }
            }
        }
        

        /*
        *   03-08-2018 Nico: Debido a un bug reportado que al momento de crear adquisiciones en Inicio no se actualizado el valor
        *       de "Fecha Real" e "Importe Real", por lo que se guarda toda la colección de adquisiciones nuevamente.
        */
                
        
        if (proy.getProyPreFk() != null) {
            proy.getProyPreFk().setAdquisicionSet(new HashSet<>(adqList));
        }
    }

    /*
        *   20-03-18 Nico: Agrego este método para poder mantener los productos reales cuando se pase de
        *           Planificación a Ejecución y se quiera mantener la línea base.
     */
    private void setearLineaBaseProductos(Proyectos proy, ProyReplanificacion replan) {
        List<Productos> prodList = productosBean.obtenerProdPorProyPk(proy.getProyPk());
        if (replan == null) {
            for (Productos iterProd : prodList) {
                for (ProdMes iterProdMes : iterProd.getProdMesList()) {
                    iterProdMes.setProdmesAcuReal(0d);
                    iterProdMes.setProdmesReal(0d);
                }
            }
        }else {
            for (Productos iterProd : prodList) {
                for (ProdMes iterProdMes : iterProd.getProdMesList()) {
                    // si es nueva la tarea
                    if (iterProdMes.getProdmesAcuReal() == null && iterProdMes.getProdmesReal() == null) {
                        iterProdMes.setProdmesAcuReal(0d);
                        iterProdMes.setProdmesReal(0d);                   
                    }
                }
            }
        }
    }

    public Set<Proyectos> obtenerProyPorProgId(Integer progPk) {
        return obtenerProyPorProgId(progPk, null);
    }

    /**
     * Dado un id de un programa retorna todos los proyectos que contenga.
     *
     * @param progPk Id del programa.
     * @return Lista de proyectos.
     */
    public Set<Proyectos> obtenerProyPorProgId(Integer progPk, Boolean activos) {
        ProyectosDAO proyDao = new ProyectosDAO(em);
        List<Proyectos> resultado;

        CriteriaTO condicion;
        MatchCriteriaTO criterioProg = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.EQUALS, "proyProgFk.progPk", progPk);

        if (activos != null) {
            MatchCriteriaTO criterioActivo = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "activo", activos);
            condicion = CriteriaTOUtils.createANDTO(criterioProg, criterioActivo);
        } else {
            condicion = criterioProg;
        }

        try {
            String[] orderBy = new String[]{"proyNombre"};
            boolean[] ascending = new boolean[]{true};
            resultado = proyDao.findEntityByCriteria(Proyectos.class, condicion, orderBy, ascending, null, null);
            return new HashSet<>(resultado);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(ex.getMessage());
            throw be;
        }
    }

    /**
     * Dado un id de un programa retorna todos los proyectos que contenga. Solo
     * retorna el id, estado y si es activo o no
     *
     * @param progPk Id del programa.
     * @return Lista de proyectos.
     */
    public Set<Proyectos> obtenerProySimplePorProgId(Integer progPk) {
        ProyectosDAO proyDao = new ProyectosDAO(em);
        List<Proyectos> resultado = null;
        try {
            resultado = proyDao.obtenerProySimplePorProgId(progPk);
        } catch (BusinessException be) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerProyPorProgId", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerProyPorProgId", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return new HashSet<>(resultado);
    }

    /**
     * Retorna los proyectos cuyo gerente sea el del id aportado.
     *
     * @param usuPk
     * @param orgPk
     * @return List
     */
    public List<Proyectos> obtenerProyPorGerente(Integer usuPk, Integer orgPk) {
        if (usuPk != null && orgPk != null) {
            ProyectosDAO proyDao = new ProyectosDAO(em);

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);

            MatchCriteriaTO criterioGerente = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "proyUsrGerenteFk.usuId", usuPk);

            CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterioOrg, criterioGerente);

            try {
                String[] orderBy = new String[]{"proyNombre"};
                boolean[] ascending = new boolean[]{true};
                return proyDao.findEntityByCriteria(Proyectos.class, condicion, orderBy, ascending, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
        return null;
    }

    public Set<ProyectosConCronograma> obtenerProyectosConCronogramaPorProgId(Integer proyPk) {
        ProyectosConCronogramaDAO proyDao = new ProyectosConCronogramaDAO(em);

        List<ProyectosConCronograma> resultado = null;

        try {
            resultado = proyDao.findByOneProperty(ProyectosConCronograma.class, "proyProgFk", proyPk);

        } catch (BusinessException be) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerProyectosConCronogramaPorProgId", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerProyectosConCronogramaPorProgId", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return new HashSet<>(resultado);
    }

    public List<Proyectos> obtenerProyHermanos(Integer proyPk) {
        ProyectosDAO dao = new ProyectosDAO(em);
        List<Proyectos> list = dao.obtenerProyHermanos(proyPk);
        return list;
    }

    public Interesados guardarInteresado(Interesados i, Integer proyId) {
        Proyectos p = em.find(Proyectos.class, proyId);
        p.getInteresadosList().add(i);
        em.merge(p);

        return i;
    }

    public Proyectos darBajaProyecto(Integer proyId, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Proyectos proy = em.find(Proyectos.class, proyId);
        if (ProgProyUtils.isUsuarioPMOF(proy, usuario, orgPk) && !ProgProyUtils.isUsuarioPMOT(usuario, orgPk)) {
            proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.SOLICITUD_CANCELAR_PMOT.estado_id));
        } else if (usuario.isUsuarioPMOT(orgPk)) {
            proy.setProyEstPendienteFk(null);
            proy.setActivo(false);
        } else {
            throw new BusinessException(MensajesNegocio.ERROR_USUARIO_NO_PERMISO_ACCION);
        }

        try {
            proy = guardar(proy, usuario, orgPk, true);
            //TODO: Notificar al PM, Adjunto y PMOF
            /**
             * 26-09-2017 Requerimiento RQF-18: Ficha de proyecto - notificar
             * eliminación de proyecto
             */
            this.enviarNotificacion(ConstantesNotificaciones.NOT_COD_ELIMINACION_PROY_1, proy, orgPk);

            return proy;
        } catch (GeneralException ge) {
            throw new BusinessException(MensajesNegocio.ERROR_ELIMINAR_PROYECTO);
        }
    }

    public ProyIndices obtenerIndicadores(Integer proyPk, Integer orgPk) {
        ProyIndices p = proyIndicesBean.obtenerIndicePorProyId(proyPk);
        if (p == null) {
            p = guardarIndicadores(proyPk, orgPk);
        }
        return p;
    }

    public ProyIndices guardarIndicadores(Integer proyPk, Integer orgPk) {
        return guardarIndicadores(proyPk, true, true, orgPk, null, false, true);
    }

    /**
     * Guarda los indicadores para el proyecto aportado y todos sus hermanos.
     * Retorna los indicadores del proyecto aportado.
     *
     * @param proyPk
     * @param orgPk
     * @return ProyIndices
     */
    public ProyIndices guardarIndicadoresYHermanos(Integer proyPk, Integer orgPk) {
        List<Proyectos> proyHermanos = obtenerProyHermanos(proyPk);
        ProyIndices pi = null;
        if (proyHermanos != null) {
            for (Proyectos proy : proyHermanos) {
                guardarIndicadores(proy.getProyPk(), orgPk);
            }
        }

        pi = guardarIndicadores(proyPk, orgPk);

        return pi;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    @TransactionTimeout(unit = TimeUnit.SECONDS, value = 120) //BRUNO 11-04-17
    public void guardarIndicadoresSimple(Integer proyPk, boolean programas, boolean soloFaltantes, Integer orgPk, Map<String, Configuracion> confs, boolean procesarPrograma) {
        guardarIndicadores(proyPk, programas, soloFaltantes, orgPk, confs, true, procesarPrograma);
    }

    /**
     * Calcula los indicadores y los guarda.
     *
     * @param proyPk
     * @param programas
     * @param recalcular
     * @param orgPk
     * @param confs
     * @return ProyIndices
     */
    public ProyIndices guardarIndicadores(Integer proyPk, boolean programas, boolean recalcular, Integer orgPk, Map<String, Configuracion> confs,
            boolean indicadoresProgramasNuevaTransaccion, boolean procesarPrograma) {
        logger.fine("guardar Indicadores Proyectos.");
        if (proyPk != null) {
            Proyectos p = obtenerProyPorId(proyPk);
            return guardarIndicadores(p, programas, recalcular, orgPk, confs, indicadoresProgramasNuevaTransaccion, procesarPrograma);
        }
        return null;
    }

    public ProyIndices guardarIndicadores(Proyectos p, boolean programas, boolean recalcular, Integer orgPk, Map<String, Configuracion> confs,
            boolean indicadoresProgramasNuevaTransaccion, boolean procesarPrograma) {
        logger.fine("guardar Indicadores Proyectos.");

        if (p != null) {
            Integer proyPk = p.getProyPk();

            /**
             * Bruno 10-04-17: obtener marcas de inicio y fin de proyecto a
             * partir de los entregables.
             */
            Entregables entInicioProyecto = null;
            Entregables entFinProyecto = null;

            Configuracion confGanttPeriodo = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PROYECTO_GANTT_PERIODO, orgPk);
            Boolean confGanttPeriodoValor = confGanttPeriodo != null && "true".equals(confGanttPeriodo.getCnfValor()) ? true : false;

            if (p.getProyCroFk() != null && confGanttPeriodoValor) {
                for (Entregables e : p.getProyCroFk().getEntregablesSet()) {
                    if (e.getEntInicioProyecto()) {
                        entInicioProyecto = e;
                    }
                    if (e.getEntFinProyecto()) {
                        entFinProyecto = e;
                    }
                }
            }

            ProyIndices ind = proyIndicesBean.obtenerIndicePorProyId(proyPk);

            if (ind == null) {
                ind = new ProyIndices();
            }

            //Riesgo
            if (ind.getProyindRiesgoAlto() == null || recalcular) {
                ind.setProyindRiesgoAlto(riesgosBean.obtenerCantRiesgosAltos(proyPk, orgPk));
            }

            if (ind.getProyindRiesgoExpo() == null || recalcular) {
                ind.setProyindRiesgoExpo(riesgosBean.obtenerExposicionRiesgo(proyPk));
            }

            if (ind.getProyindRiesgoUltact() == null || recalcular) {
                ind.setProyindRiesgoUltact(riesgosBean.obtenerDateUltimaActualizacion(proyPk));
            }

            //Metodologia
            if (ind.getProyindMetodologiaEstado() == null || recalcular) {
                ind.setProyindMetodologiaEstado(documentosBean.calcularIndiceEstadoMetodologiaProyecto(proyPk, p.getProyEstFk(), orgPk));
            }

            if (ind.getProyindMetodologiaSinAprobar() == null || recalcular) {
                ind.setProyindMetodologiaSinAprobar(documentosBean.calcularIndiceMetodologiaSinAprobar(proyPk));
            }

            //Periodo
            if (ind.getProyindPeriodoInicio() == null || recalcular) {
                /**
                 * Bruno 10-04-17: Toma el inicio del entregable marcado
                 */
                if (entInicioProyecto != null && confGanttPeriodoValor) {
                    ind.setProyindPeriodoInicio(entInicioProyecto.getEntInicioDate());
                } else {
                    ind.setProyindPeriodoInicio(obtenerPrimeraFecha(p));
                }
            }

            if (ind.getProyindPeriodoFin() == null || recalcular) {
                /**
                 * Bruno 10-04-17: Toma el fin del entregable marcado
                 */
                if (entFinProyecto != null && confGanttPeriodoValor) {
                    ind.setProyindPeriodoFin(entFinProyecto.getEntFinDate());
                } else {
                    ind.setProyindPeriodoFin(obtenerUltimaFecha(p));
                }
            }

            if (ind.getProyindPorcPesoTotal() == null || recalcular) {
                ind.setProyindPorcPesoTotal(this.porcentajePesoTotalProyecto(proyPk));
            }
            //Calidad
            if (ind.getProyindCalInd() == null || recalcular) {
                ind.setProyindCalInd(calidadBean.calcularIndiceCalidad(proyPk, orgPk));
            }

            if (ind.getProyindCalPend() == null || recalcular) {
                ind.setProyindCalPend(calidadBean.obtenerPendCalidad(proyPk));
            }

            //Fase
            String[] codigos = new String[]{
                ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO,
                ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO,
                ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO,
                ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO};
            confs = configuracionBean.obtenerCnfPorCodigoYOrg(orgPk, confs, codigos);

            Integer estIniAmarillo = Integer.valueOf(confs.get(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO).getCnfValor());
            Integer estIniRojo = Integer.valueOf(confs.get(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO).getCnfValor());
            Integer estPlanAmarillo = Integer.valueOf(confs.get(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO).getCnfValor());
            Integer estPlanRojo = Integer.valueOf(confs.get(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO).getCnfValor());

            String faseColorStr = estadosBean.obtenerEstadoColor(p.getProyEstFk(), ind.getProyindPeriodoInicio(), ind.getProyindPeriodoFin(), estIniAmarillo, estIniRojo, estPlanAmarillo, estPlanRojo, orgPk);

            int faseColorCod = programasProyectosBean.obtenerCodigoColorEstadoAc(faseColorStr);
            ind.setProyindFaseColor(faseColorCod);

            //Avance
            Cronogramas cro = cronogramasBean.obtenerCronogramaPorProy(p.getProyPk());
            int[] iaf = cronogramasBean.calcularAvanceCronoFinalizado(cro);
            int[] iap = cronogramasBean.calcularAvanceCronoParcial(cro);
            ind.setProyindAvanceParAzul(iap[0]);
            ind.setProyindAvanceParVerde(iap[1]);
            ind.setProyindAvanceParRojo(iap[2]);
            ind.setProyindAvanceFinAzul(iaf[0]);
            ind.setProyindAvanceFinVerde(iaf[1]);
            ind.setProyindAvanceFinRojo(iaf[2]);

            //Presupuesto
            Integer prePk = p.getProyPreFk() != null ? p.getProyPreFk().getPrePk() : null;
            cargarIndPresupuesto(prePk, ind, orgPk);

            //Actualizacion Color
            ind.setProyindFechaActColor(obtenerUltimaActualizacionColorNumero(p.getProyEstFk(),
                    p.getProyFechaAct(), p.getProySemaforoAmarillo(), p.getProySemaforoRojo()));

            try {
                ind = proyIndicesBean.guardar(ind, proyPk);
                if (p.getProyProgFk() != null && programas && procesarPrograma) {
                    progIndicesBean.guardarIndicadores(p.getProyProgFk().getProgPk(), orgPk);
                }
                return ind;
            } catch (BusinessException ex) {
                logger.log(Level.SEVERE, null, ex);
                if (!indicadoresProgramasNuevaTransaccion) {
                    throw ex;
                }
            }

        }
        return null;
    }
   
    
    /*
    *   20-03-18 Nico: Agrego este método para poder mantener los productos reales cuando se pase de
    *           Planificación a Ejecución y se quiera mantener la línea base.
    */
    
    public Proyectos guardarProyectoRetrocederEstadoServicio(FichaTO fichaTO, Integer orgPk, ProyReplanificacionTO replanificacionTO) throws GeneralException {
        Proyectos p = obtenerProyPorId(fichaTO.getFichaFk());
        Estados est = p.getProyEstFk();
        
        ProyReplanificacion replanificacion = ProgProyUtils.replanifTOaReplanif(p, replanificacionTO);
        
        ProgProyUtils.retrocederEstado(p, orgPk, replanificacion);
        if (!est.equals(p.getProyEstFk())) {
            p.setProyFechaEstadoAct(new Date());
        }

        //Ver bien este guardar capaz que hay que cambiarlo un poco
        return guardar(p, null, orgPk, true);
    }

    
    
    
    public Proyectos guardarProyectoRetrocederEstado(Integer proyPk, SsUsuario usuario, Integer orgPk, ProyReplanificacion replanificacion) throws GeneralException {
        Proyectos p = obtenerProyPorId(proyPk);
        Estados est = p.getProyEstFk();
        
        // Se analiza si esta activada la configuración para que el PMOF acepte
        
        Configuracion cnfAprobPMOF = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.APROBACION_PMOF, orgPk);
        boolean aprobPMOF = cnfAprobPMOF.getCnfValor().equals("true") ? true : false;
        
        Configuracion cnfAprobReplanPMOF = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.APROBACION_REPLANIFICACION_PMOF, orgPk);
        boolean aprobReplanPMOF = cnfAprobReplanPMOF.getCnfValor().equals("true") ? true : false;
        
        ProgProyUtils.retrocederEstado(p, usuario, orgPk, replanificacion, aprobPMOF, aprobReplanPMOF);
        if (!est.equals(p.getProyEstFk())) {
            p.setProyFechaEstadoAct(new Date());
            
            /*
            *   11-06-2018  Nico: Se settea la fecha de actualización del proyecto cuando se retrocede un proyecto, dado que para el frontend se fija en el valor a partir
            *           de la entidad de Proyecto y no de IndicesProyecto, que ahí si se actualiza.
            */
            
            p.setProyFechaAct(new Date());

            List<SsUsuario> usuariosDest = new ArrayList<>();
            usuariosDest.add(p.getProyUsrGerenteFk());
            usuariosDest.add(p.getProyUsrAdjuntoFk());
            String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosDest);
            try {
                mailBean.comunicarCambioEstado(orgPk, p, destinatarios);
            } catch (MailException me) {
                logger.log(Level.SEVERE, me.getMessage(), me);
            }
        }

        return guardar(p, usuario, orgPk, true);
    }

    public Proyectos guardarProyectoRechazarCambioEstado(Integer proyPk, SsUsuario usuario, Integer orgPk, ProyReplanificacion replanificacion) throws GeneralException {

        Proyectos p = obtenerProyPorId(proyPk);
        Estados est = p.getProyEstFk();
        ProgProyUtils.rechazarCambioEstado(p, usuario, orgPk, replanificacion);
        if (!est.equals(p.getProyEstFk())) {
            p.setProyFechaEstadoAct(new Date());
            List<SsUsuario> usuariosDest = new ArrayList<>();
            usuariosDest.add(p.getProyUsrGerenteFk());
            usuariosDest.add(p.getProyUsrAdjuntoFk());
            String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosDest);
            try {
                mailBean.comunicarCambioEstado(orgPk, p, destinatarios);
            } catch (MailException me) {
                logger.log(Level.SEVERE, me.getMessage(), me);
            }
        }
        return guardar(p, usuario, orgPk, true);

    }

    public Integer obtenerUltimaActualizacionColorNumero(Estados estado, Date fechaAct, Integer semaforoAmarillo, Integer semaforoRojo) {
        String val = obtenerUltimaActualizacionColor(estado, fechaAct, semaforoAmarillo, semaforoRojo);
        switch (val) {
            case ConstantesEstandares.SEMAFORO_VERDE:
                return 1;
            case ConstantesEstandares.SEMAFORO_AMARILLO:
                return 2;
            case ConstantesEstandares.SEMAFORO_ROJO:
                return 3;
            case ConstantesEstandares.SEMAFORO_AZUL:
                return 4;
            default:
                return 0;
        }
    }

    public String obtenerUltimaActualizacionColorFromNumero(Integer val) {
        switch (val) {
            case 1:
                return ConstantesEstandares.SEMAFORO_VERDE;
            case 2:
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            case 3:
                return ConstantesEstandares.SEMAFORO_ROJO;
            case 4:
                return ConstantesEstandares.SEMAFORO_AZUL;
            default:
                return ConstantesEstandares.COLOR_TRANSPARENT;
        }
    }

    public String obtenerUltimaActualizacionColor(Estados estado, Date fechaAct, Integer semaforoAmarillo, Integer semaforoRojo) {

        if (estado.isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) {
            return ConstantesEstandares.SEMAFORO_AZUL;
        }

        if (fechaAct != null && semaforoAmarillo != null && semaforoRojo != null) {
            Calendar calFechaAct = Calendar.getInstance();
            calFechaAct.setTime(fechaAct);

            Calendar calAmarillo = Calendar.getInstance();
            calAmarillo.setTimeInMillis(calFechaAct.getTimeInMillis());
            calAmarillo.add(Calendar.DATE, semaforoAmarillo);

            Calendar calRojo = Calendar.getInstance();
            calRojo.setTimeInMillis(calFechaAct.getTimeInMillis());
            calRojo.add(Calendar.DATE, semaforoRojo);

            Calendar calNow = Calendar.getInstance();
            if (calNow.before(calAmarillo)) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            }
            if (calNow.equals(calAmarillo) || (calNow.after(calAmarillo) && calNow.before(calRojo))) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            }
            if (calNow.equals(calRojo) || calNow.after(calRojo)) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }
        }

        return ConstantesEstandares.COLOR_TRANSPARENT;
    }

    /*
    *       VER SI NO HAY QUE SACAR EL MÉTODO DE ABAJO,  PORQUE YA HAY UNO EN ProgramasBean QUE HACE LO
        MISMO DESDE LA DAO
    */
    public Date obtenerUltimaActualizacionPrograma(Set<Proyectos> proyectosSet) {
        if (proyectosSet != null && !proyectosSet.isEmpty()) {
            Date result = null;
            boolean tieneProy = false;
            for (Proyectos p : proyectosSet) {
                if ((p.getProyEstFk().isEstado(Estados.ESTADOS.INICIO.estado_id)
                        || p.getProyEstFk().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)
                        || p.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id))
                        && (result == null || DatesUtils.esMayor(result, p.getProyFechaAct()))) {
                    result = p.getProyFechaAct();
                    tieneProy = true;
                }
            }
            return tieneProy ? result : null;
        }
        return null;
    }

    public boolean isUsuarioGerenteOAdjuntoProy(Proyectos p, SsUsuario u) {
        return p != null && u != null
                && (p.getProyUsrGerenteFk().equals(u)
                || p.getProyUsrAdjuntoFk().equals(u));
    }

    /**
     * @see ProyectosDAO#obtenerPrimeraFecha()
     * @return Date
     */
    public Date obtenerPrimeraFecha() {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.obtenerPrimeraFecha();
    }

    /**
     * @see ProyectosDAO#obtenerUltimaFecha()
     * @return Date
     */
    public Date obtenerUltimaFecha() {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.obtenerUltimaFecha();
    }

    /**
     * Retorna la primer fecha de un proyecto dado.
     *
     * @param proy
     * @return Date
     */
    private Date obtenerPrimeraFecha(Proyectos proy) {
        Date result = null;
        if (proy != null) {
            result = proy.getProyFechaCrea();
            Date dateAct = proy.getProyFechaAct();
            result = result == null || dateAct != null && DatesUtils.esMayor(result, dateAct) ? dateAct : result;

            //Pagos
            Date prePrimera = presupuestoBean.obtenerPrimeraFechaPre(proy.getProyPreFk());
            result = result == null || prePrimera != null && DatesUtils.esMayor(result, prePrimera) ? prePrimera : result;

            //Riesgos
            Date riesgoPrimera = riesgosBean.obtenerPrimeraFecha(proy.getRiesgosList());
            result = result == null || riesgoPrimera != null && DatesUtils.esMayor(result, riesgoPrimera) ? riesgoPrimera : result;

            //Gantt
            Set<Entregables> entSet = proy.getProyCroFk() != null && proy.getProyCroFk().getEntregablesSet() != null ? proy.getProyCroFk().getEntregablesSet() : null;
            Date entPrimera = entregablesBean.obtenerPrimeraFecha(entSet, false);
            result = result == null || entPrimera != null && DatesUtils.esMayor(result, entPrimera) ? entPrimera : result;

            //Devengados
            List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorProy(proy.getProyPk());
            Date dateDev = devengadoBean.obtenerPrimeraFechaPorAdq(adqList, true);
            result = result == null || dateDev != null && DatesUtils.esMayor(result, dateDev) ? dateDev : result;

            //Carga de horas
            Date dateHoras = registrosHorasBean.obtenerPrimeraFecha(proy.getProyPk(), true);
            result = result == null || dateHoras != null && DatesUtils.esMayor(result, dateHoras) ? dateHoras : result;

            //Gastos
            Date dateGastos = gastosBean.obtenerPrimeraFecha(proy.getProyPk(), true);
            result = result == null || dateGastos != null && DatesUtils.esMayor(result, dateGastos) ? dateGastos : result;
        }
        return result;
    }

    /**
     * Retorna la ultima fecha de un proyecto dado.
     *
     * @param proy
     * @return Date
     */
    private Date obtenerUltimaFecha(Proyectos proy) {
        Date result = null;
        if (proy != null) {
            //Creacion y actualizacion
            result = proy.getProyFechaCrea();
            Date dateAct = proy.getProyFechaAct();
            result = result == null || dateAct != null && DatesUtils.esMayor(dateAct, result) ? dateAct : result;

            //Pagos
            Date preUltima = presupuestoBean.obtenerUltimaFechaPre(proy.getProyPreFk());
            result = result == null || (preUltima != null && DatesUtils.esMayor(preUltima, result)) ? preUltima : result;

            //Riesgos
            Date riesgoUltima = riesgosBean.obtenerUltimaFecha(proy.getRiesgosList());
            result = result == null || (riesgoUltima != null && DatesUtils.esMayor(riesgoUltima, result)) ? riesgoUltima : result;

            //Gantt
            Set<Entregables> entSet = proy.getProyCroFk() != null && proy.getProyCroFk().getEntregablesSet() != null ? proy.getProyCroFk().getEntregablesSet() : null;
            Date entUltima = entregablesBean.obtenerUltimaFecha(entSet, false);
            result = result == null || (entUltima != null && DatesUtils.esMayor(entUltima, result)) ? entUltima : result;

            //Devengados
            List<Adquisicion> adqList = adquisicionBean.obtenerAdquisicionPorProy(proy.getProyPk());
            Date dateDev = devengadoBean.obtenerPrimeraFechaPorAdq(adqList, false);
            result = result == null || dateDev != null && DatesUtils.esMayor(dateDev, result) ? dateDev : result;

            //Carga de horas
            Date dateHoras = registrosHorasBean.obtenerPrimeraFecha(proy.getProyPk(), false);
            result = result == null || dateHoras != null && DatesUtils.esMayor(dateHoras, result) ? dateHoras : result;

            //Gastos
            Date dateGastos = gastosBean.obtenerPrimeraFecha(proy.getProyPk(), false);
            result = result == null || dateGastos != null && DatesUtils.esMayor(dateGastos, result) ? dateGastos : result;
        }

        return result;
    }

    public List<Proyectos> obtenerTodos() {
        ProyectosDAO proyDao = new ProyectosDAO(em);
        try {
            return proyDao.findAll(Proyectos.class);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
    *   17-05-2018 Nico: Se saca este método para poner uno que consulte sobre
    *           la base de datos
    */
    
//    public Double porcentajePesoTotalProyecto(Integer proyPk) {
//        if (proyPk != null) {
//            Proyectos proyecto = this.obtenerProyPorId(proyPk);
//
//            if (proyecto != null && proyecto.isActivo()
//                    && (proyecto.getProyEstFk().isEstado(Estados.ESTADOS.INICIO.estado_id)
//                    || proyecto.getProyEstFk().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)
//                    || proyecto.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id))) {
//                List<Proyectos> setProyectos = obtenerProyHermanos(proyPk);
//                if (setProyectos != null && !setProyectos.isEmpty()) {
//                    double pesoTotal = 0;
//                    for (Proyectos proy : setProyectos) {
//                        if (proy != null && proy.isActivo()
//                                && proy.getProyPeso() != null
//                                && (proy.getProyEstFk().isEstado(Estados.ESTADOS.INICIO.estado_id)
//                                || proy.getProyEstFk().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)
//                                || proy.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id))) {
//                            pesoTotal += proy.getProyPeso();
//                        }
//                    }
//
//                    return pesoTotal > 0 ? (proyecto.getProyPeso().floatValue() * 100 / pesoTotal) : 0d;
//                }
//            } else {
//                return 0d;
//            }
//        }
//        return null;
//    }
    
    /*
    *   17-05-2018 Nico: Se crea el método para consultar a la base de datos sobre los pesos
    *           de los proyectos.
    */
    public Double porcentajePesoTotalProyecto(Integer proyPk){
        if(proyPk != null){
            ProyectosDAO proyDAO = new ProyectosDAO(em);
            
            Double retorno = 0d;
            try {
                retorno = proyDAO.obtenerPorcPesoTotalProy(proyPk);
            } catch (DAOGeneralException ex) {
                Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return retorno;
        }
        return null;
    }
    

    public List<Integer> obtenerIdsProyPorOrg(Integer orgPk) throws GeneralException {
        return obtenerIdsProyPorOrg(orgPk, null);
    }

    public List<Integer> obtenerIdsProyPorOrg(Integer orgPk, Boolean activos) throws GeneralException {
        ProyectosDAO proyDao = new ProyectosDAO(em);
        List<Integer> proyIds = new ArrayList<>();
        try {
            CriteriaTO criteria;
            if (activos == null) {
                criteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);
            } else {
                MatchCriteriaTO criterioAct1 = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "activo", activos);
                MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);
                criteria = CriteriaTOUtils.createANDTO(criterioOrg, criterioAct1);
            }

            List<EntityReference<Integer>> proyectosResult = proyDao.findEntityReferenceByCriteria(Proyectos.class, criteria, new String[]{}, new boolean[]{}, null, null, "proyPk");
            for (EntityReference<Integer> proy : proyectosResult) {
                if (proy.getPropertyMap().get("proyPk") != null) {
                    proyIds.add((Integer) proy.getPropertyMap().get("proyPk"));
                }
            }
        } catch (Exception ex) {

            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerProyPorId", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return proyIds;
    }

    public List<Integer> obtenerIdsProyPorOrgNoFinalizado(Integer orgPk) throws GeneralException {
        ProyectosDAO proyDao = new ProyectosDAO(em);
        List<Integer> proyIds = new ArrayList<>();
        try {
            CriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);
            CriteriaTO criteriaEstado = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "proyEstFk.estPk", Estados.ESTADOS.FINALIZADO.estado_id);
            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaOrg, criteriaEstado);

            List<EntityReference<Integer>> proyectosResult = proyDao.findEntityReferenceByCriteria(Proyectos.class, criteria, new String[]{}, new boolean[]{}, null, null, "proyPk");
            for (EntityReference<Integer> proy : proyectosResult) {
                if (proy.getPropertyMap().get("proyPk") != null) {
                    proyIds.add((Integer) proy.getPropertyMap().get("proyPk"));
                }
            }
        } catch (Exception ex) {

            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerProyPorId", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }

        return proyIds;
    }

    public List<Proyectos> obtenerActivosPorOrg(Integer orgPk) {
        ProyectosDAO proyDao = new ProyectosDAO(em);

        MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);

        MatchCriteriaTO criterioAct1 = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.EQUALS, "activo", Boolean.TRUE);

        MatchCriteriaTO criterioAct2 = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.NULL, "activo", 1);

        CriteriaTO criterioAct = CriteriaTOUtils.createORTO(criterioAct1, criterioAct2);

        CriteriaTO condicion = CriteriaTOUtils.createANDTO(criterioOrg, criterioAct);

        try {
            String[] orderBy = new String[]{"proyNombre"};
            boolean[] ascending = new boolean[]{true};
            return proyDao.findEntityByCriteria(Proyectos.class, condicion, orderBy, ascending, null, null);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public List<Proyectos> obtenerProyComboPorOrg(Integer orgPk) {
        ProyectosDAO proyDao = new ProyectosDAO(em);
        return proyDao.obtenerProyComboPorOrg(orgPk);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void controlarEntregables(Integer proyPk, boolean resetLineaBase) {
        Proyectos proy = obtenerProyPorId(proyPk);
        if (proy != null && proy.getProyCroFk() != null && CollectionsUtils.isNotEmpty(proy.getProyCroFk().getEntregablesSet())) {
            List<Entregables> entResult = new ArrayList<>(proy.getProyCroFk().getEntregablesSet());
            entResult = EntregablesUtils.marcarPadres(entResult);

            for (Entregables ent : entResult) {
                if (proy.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id)
                        || proy.getProyEstFk().isEstado(Estados.ESTADOS.FINALIZADO.estado_id)) {
                    if (ent.getEntInicioLineaBase() == null || ent.getEntInicioLineaBase() <= 0) {
                        ent.setEntInicioLineaBase(ent.getEntInicio());
                    }
                    if (ent.getEntFinLineaBase() == null || ent.getEntFinLineaBase() <= 0) {
                        ent.setEntFinLineaBase(ent.getEntFin());
                    }
                    if (ent.getEntFinLineaBase().equals(ent.getEntInicioLineaBase())) {
                        ent.setEntDuracionLineaBase(1);
                    } else if (ent.getEntFinLineaBase() > ent.getEntInicioLineaBase()) {
                        //La duracion no la toca, es lo que viene del gannt
                        //int duraLineaBase = Math.round((ent.getEntFinLineaBase() - ent.getEntInicioLineaBase()) / 86400000);
                        //ent.setEntDuracionLineaBase(duraLineaBase);
                    }

                    if (resetLineaBase) {
                        ent.setEntInicioLineaBase(ent.getEntInicio());
                        ent.setEntFinLineaBase(ent.getEntFin());
                        ent.setEntDuracionLineaBase(ent.getEntDuracion());
                    }
                } else {
                    //si esta en otros estado entonces la linea base se limpia
                    ent.setEntInicioLineaBase(0l);
                    ent.setEntFinLineaBase(0l);
                    ent.setEntDuracionLineaBase(0);
                }

                if (productosBean.tieneProdPorEnt(ent.getEntPk())) {
                    Double avance = entregablesBean.calcularAvanceEntPorProd(ent.getEntPk());
                    if (avance != null) {
                        Long progreso = Math.round(avance);
                        progreso = progreso > 100 ? 100 : progreso;
                        ent.setEntProgreso(progreso.intValue());
                    }
                }

                if (ent.esEntParent()) {
                    //Los padres no llevan esfuerzo ni progreso.
                    ent.setEntEsfuerzo(0);
                    ent.setEntProgreso(0);
                }

                entregablesBean.guardar(ent);
            }
        }
    }

    @Deprecated
    public List<Entregables> recalcularEntregablesPadres(Set<Entregables> entSet) {
        List<Entregables> entResult = new ArrayList<>();

        if (CollectionsUtils.isNotEmpty(entSet)) {
            List<Entregables> entList = new ArrayList<>(entSet);
            Entregables[] entArr = entSet.toArray(new Entregables[entSet.size()]);

            int nivelMax = 0;
            for (Entregables ent : entList) {
                if (ent.getEntNivel() > nivelMax) {
                    nivelMax = ent.getEntNivel();
                }
            }

            //Se ordena por id.
            for (int i = 0; i < entArr.length; i++) {
                for (int j = i + 1; j < entArr.length; j++) {
                    if (entArr[i].getEntId() > entArr[j].getEntId()) {
                        Entregables ent = entArr[j];
                        entArr[j] = entArr[i];
                        entArr[i] = ent;
                    }
                }
            }

            for (int a = nivelMax; a >= 0; a--) {
                for (int b = 0; b < entArr.length; b++) {
                    if (entArr[b].getEntNivel().equals(a - 1)) {
                        boolean seguir = true;
                        boolean padre = false;
                        long fechaMenor = 0;
                        long fechaMayor = 0;
                        for (int c = b + 1; seguir && c < entArr.length; c++) {
                            if (entArr[c].getEntNivel().equals(entArr[b].getEntNivel() + 1)
                                    || entArr[c].getEntNivel() > entArr[b].getEntNivel()) {
                                if (fechaMenor == 0 || entArr[c].getEntInicio() < fechaMenor) {
                                    fechaMenor = entArr[c].getEntInicio();
                                }
                                if (entArr[c].getEntFin() > fechaMayor) {
                                    fechaMayor = entArr[c].getEntFin();
                                }
                                padre = true;
                            } else {
                                seguir = false;
                            }
                        }

                        if (padre) {
                            if (fechaMenor > 0) {
                                entArr[b].setEntInicio(fechaMenor);
                            }
                            if (fechaMayor > 0) {
                                entArr[b].setEntFin(fechaMayor);
                            }
                            entArr[b].setEntEsfuerzo(0);
                            entArr[b].setEntProgreso(0);
                        }
                    }
                }
            }

            for (Entregables ent : entArr) {
                Integer difDias = DatesUtils.diasEntreFechas(ent.getEntInicioDate(), ent.getEntFinDate());
                if (difDias != null) {
                    ent.setEntDuracion(difDias);
                }
            }
            Collections.addAll(entResult, entArr);
        }
        return entResult;
    }

    public Proyectos guardarCopiaProyecto(Integer proyPk, String nombre, Date fechaComienzoProyCopia, SsUsuario usu, Integer orgPk) {
        if (StringsUtils.isEmpty(nombre)) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROY_NOMBRE);
            throw be;
        }
        if (fechaComienzoProyCopia == null) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROY_FECHA);
            throw be;
        }
        if (orgPk == null) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROY_ORG);
            throw be;
        }

        Proyectos p = obtenerProyPorId(proyPk);
        if (p == null) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROY_NULL);
            throw be;
        }

        Proyectos copiaProy = copiarProyecto(p, nombre, fechaComienzoProyCopia);
        Set<LatlngProyectos> localizaciones = p.getLatLngProyList();
        try {
            copiaProy = guardar(copiaProy, usu, orgPk, true);
            if (copiaProy.getLatLngProyList() == null) {
                copiaProy.setLatLngProyList(new HashSet<LatlngProyectos>());
            }
            if (localizaciones != null) {
                LatlngProyectosDAO locDAO = new LatlngProyectosDAO(em);
                LatlngProyectos laux;
                for (LatlngProyectos l : localizaciones) {
                    laux = new LatlngProyectos();
                    laux.setLatlangBarrio(l.getLatlangBarrio());
                    laux.setLatlangCodigopostal(l.getLatlangCodigopostal());
                    laux.setLatlangDepFk(l.getLatlangDepFk());
                    laux.setLatlangLoc(l.getLatlangLoc());
                    laux.setLatlangLocFk(l.getLatlangLocFk());
                    laux.setLatlngLat(l.getLatlngLat());
                    laux.setLatlngLng(l.getLatlngLng());
                    laux.setProyecto(copiaProy);
                    laux = (LatlngProyectos) locDAO.persist(laux, du.getCodigoUsuario(), du.getOrigen());
                    copiaProy.getLatLngProyList().add(laux);
                }
                copiaProy = guardar(copiaProy, usu, orgPk, true);
            }

        } catch (GeneralException ge) {
            BusinessException be = new BusinessException(ge);
            be.addError(MensajesNegocio.ERROR_COPIA_PROY_GUARDAR);
            throw be;
        } catch (DAOGeneralException ex) {
            Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(MensajesNegocio.ERROR_COPIA_PROY_GUARDAR);
            throw te;
        }

        tipoDocumentoInstanciaBean.guardarCopiaTDIProyecto(proyPk, copiaProy.getProyPk(), orgPk);

        //Notificaciones
        notificacionInstanciaBean.guardarCopiaNIProyecto(proyPk, copiaProy.getProyPk(), orgPk);

        //Participantes
        return copiaProy;
    }

    public Proyectos moverProyecto(Proyectos p, SsUsuario usuario, boolean mismoOrganismo) throws GeneralException {

        //por especificacion estos datos no se mueven
        p.getAreasTematicasSet().clear();
        p.getAreasRestringidasSet().clear();
        if (p.getProyOtrosDatos() != null) {
            p.getProyOtrosDatos().setProyOtrCatFk(null);
            p.getProyOtrosDatos().getProyOtrosCatSecundarias().clear();
            p.getProyOtrosDatos().setProyOtrInsEjeFk(null);
            p.getProyOtrosDatos().setProyOtrContFk(null);
        }

        List<TipoDocumentoInstancia> tdiOriginales = tipoDocumentoInstanciaBean.obtenerTiposDocumentoInstanciaPorProyecto(p.getProyPk());
        List<TipoDocumentoInstancia> tdiNuevos = new ArrayList<>();
        if (p.getDocumentosSet() != null) {
            TipoDocumentoInstancia tdi;
            TipoDocumento td;

            for (Documentos doc : p.getDocumentosSet()) {
                tdi = doc.getDocsTipo();
                td = doc.getDocsTipo().getTipodocInstTipoDocFk();
                tdi.setTipodocExigidoDesde(td.getTipodocExigidoDesde());
                tdi.setTipodocExigidoDesdeString(td.getExigidoDesdeStr());
                tdi.setTipodocInstPeso(td.getTipodocPeso());
                tdi.setTipodocInstResumenEjecutivo(td.getTipodocResumenEjecutivo());
                tdi.setTipodocInstProyFk(p.getProyPk());
                tdi.setTipodocInstProgFk(p.getProyProgFk() != null ? p.getProyProgFk().getProgPk() : null);
                tdiOriginales.remove(tdi);
                tdiNuevos.add(tdi);
            }
        }

        tipoDocumentoInstanciaBean.guardarTiposDocsIntancia(tdiNuevos);
        // Eliminar los TipoDocsInst para este Proy
        tipoDocumentoInstanciaBean.eliminar(tdiOriginales);
        //se hereda las notificaciones
        notificacionInstanciaBean.eliminarNotificacionesPorProyId(p.getProyPk());

        //Eliminar la relación con Interesados
        p.setInteresadosList(null);

        //Eliminar la relación con los Participantes(Colaboradores)
        if (p.getParticipantesList() != null) {
            for (Participantes part : p.getParticipantesList()) {
                participantesBean.eliminarNewTrans(part.getPartPk());
            }
        }

        //Eliminar la relación con Calidad
        if (p.getCalidadList() != null) {
            for (Calidad cal : p.getCalidadList()) {
                calidadBean.eliminarNewTrans(cal.getCalPk());
            }
        }

        //El historico se pone el jefe de proyecto
        for (ProyPublicaHist hist : p.getProyPublicaHist()) {
            hist.setUsuarioFk(p.getProyUsrGerenteFk());
        }

        // Cargar como coordinador de cada entregable al que fue elegido como gerente del proy.
        if (p.getProyCroFk() != null && p.getProyCroFk().getEntregablesSet() != null) {
            SsUsuario usuCoord = p.getProyUsrGerenteFk();
            for (Entregables ent : p.getProyCroFk().getEntregablesSet()) {
                ent.setCoordinadorUsuFk(usuCoord);
            }
        }

        if (p.getProyPreFk() != null) {
            if (p.getProyPreFk().getFuenteFinanciamiento() != null && p.getProyPreFk().getFuenteFinanciamiento().getFuePk() == -1) {
                p.getProyPreFk().setFuenteFinanciamiento(null);
            }
            if (p.getProyPreFk().getPreMoneda() != null && p.getProyPreFk().getPreMoneda().getMonPk() == -1) {
                p.getProyPreFk().setPreMoneda(null);
            }
            
            if (p.getProyPreFk().getAdquisicionSet() != null) {
                for (Adquisicion adq : p.getProyPreFk().getAdquisicionSet()) {
                    if (adq.getAdqProvOrga().getOrgaPk() == -1) {
                        adq.setAdqProvOrga(null);
                    }
                    
                    if (adq.getAdqProcedimientoCompra().getProcCompPk() == -1) {
                        adq.setAdqProcedimientoCompra(null);
                    }
                   
                    if (adq.getAdqComponenteProducto().getComPk() == -1) {
                        adq.setAdqComponenteProducto(null);
                    }

                    if((adq.getAdqCompartida() != null) && (adq.getAdqCompartida())){
                        if (adq.getSsUsuarioCompartida().getUsuId() == -1) {
                            adq.setSsUsuarioCompartida(null);
                        }                        
                    }
                    
                    for(Pagos iterPago : adq.getPagosSet()){
                        if(iterPago.getPagContrOrganizacionFk().getOrgaPk() == -1){
                            iterPago.setPagContrOrganizacionFk(null);
                        }
                    }
                    
//                    if (!mismoOrganismo) {
//                        adq.setAdqComponenteProducto(null);
//                    }
                    
                }
            }
        }

        //AL MOVER PROYECTOS DE UNA CARPETA A OTRA, que se muevan los documentos.
        try {
            /**
             * BRUNO 24-03-2017 Agrego un mutex para el guardado de los
             * archivos. Esto se debe a que hay que controlar la copia de los
             * archivos multiexcluyendo.
             */
            MUTEX_MOVER_ARCHIVOS.acquire();
            List<String[]> copyFiles = null;
            DocumentosDao documentosDao = new DocumentosDao(em);
            if (p.getDocumentosSet() != null) {
                Integer orgPkOld = this.obtenerProyPorId(p.getProyPk()).getProyOrgFk().getOrgPk();
                String dir = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.DOCUMENTOS_DIR, null);
                copyFiles = new ArrayList<>();
                String srcFile;
                String desFile;
                Documentos d;
                String[] cpAux = null;
                DocFileDao docFileDAO = new DocFileDao(em);
                for (Documentos doc : p.getDocumentosSet()) {
                    try {
                        /**
                         * BRUNO 06-03-2017 muevo el archivo desde el directorio
                         * del organismo orgPkOld hacia el nuevo organismo.
                         */
                        d = documentosDao.findById(Documentos.class, doc.getDocsPk());
                        
                        srcFile = dir + "/" + orgPkOld + "/" + d.getDocFile().getDocfilePath();
                        
                        /*
                        *   26-04-2018 Nico: Se actualiza el atributo "dockfile_path" de cada documento, para evitar
                        *           repetidos.
                        */
                        
                        String nuevoPath = UUID.randomUUID().toString();
                        
                        desFile = dir + "/" + p.getProyOrgFk().getOrgPk() + "/" + nuevoPath;
                        
                        documentosDao.actualizarNombrePath(d.getDocsPk(), d.getDocFile().getDocfilePk(), nuevoPath, false, null);                        
                        
                        cpAux = new String[2];
                        cpAux[0] = srcFile;
                        cpAux[1] = desFile;
                        copyFiles.add(cpAux);
                        /**
                         * BRUNO 06-03-2017 muevo el archivo del histórico del
                         * documento desde el directorio del organismo orgPkOld
                         * hacia el nuevo organismo.
                         */
                        for (DocFile dfHist : docFileDAO.obtenerHistoricoDocFile(doc)) {
                            srcFile = dir + "/" + orgPkOld + "/" + dfHist.getDocfilePath();
                            
                            /*
                            *   26-04-2018 Nico: También se debe actualizar los documentos en el histórico
                            */
                            
                            nuevoPath = UUID.randomUUID().toString();
                            
                            desFile = dir + "/" + p.getProyOrgFk().getOrgPk() + "/" + nuevoPath;
                            
                            documentosDao.actualizarNombrePath(d.getDocsPk(), dfHist.getDocfilePk(), nuevoPath, true, dfHist.getRev());

                            cpAux = new String[2];
                            cpAux[0] = srcFile;
                            cpAux[1] = desFile;
                            copyFiles.add(cpAux);
                        }

                    } catch (DAOGeneralException ex) {
                        throw new TechnicalException(ex);
                    }
                }
            }
            
            /*
            *   09-05-2018 Nico: Se agrega código para poder copiar los archivos de media de los proyetos.
            */
            
            List<MediaProyectos> listMedia = mediaProyectosBean.obtenerPorProyId(p.getProyPk());
            
            if((listMedia != null) && !(listMedia.isEmpty())){
                Integer orgPkOld = this.obtenerProyPorId(p.getProyPk()).getProyOrgFk().getOrgPk();
                String dirOrigen = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.FOLDER_MEDIA, orgPkOld);
                String dirDestino = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.FOLDER_MEDIA, p.getProyOrgFk().getOrgPk());
                String srcFileMedia;
                String desFileMedia;
                String[] cpAuxMedia = null;
                
                
                
                for(MediaProyectos iterMedia : listMedia){
                    /*
                    *       Se debe saber si el tipo de media que estoy moviendo tiene como link una URL o un archivo, para
                    *   eso se utilizado la operación "esURL(iterMedia.getMediaLink())"   
                    */

                    if(!(esURL(iterMedia.getMediaLink()))){                
                        srcFileMedia = dirOrigen + "/" + iterMedia.getMediaLink();
                        String nuevoPath = UUID.randomUUID().toString();
                        desFileMedia = dirDestino + "/" + nuevoPath;

                        //Acá le asigno el nuvo nombre al archivo media

                        iterMedia.setMediaLink(nuevoPath);

                        cpAuxMedia = new String[2];
                        cpAuxMedia[0] = srcFileMedia;
                        cpAuxMedia[1] = desFileMedia;
                        copyFiles.add(cpAuxMedia);
                    }
                }
            }
            
            p = guardar(p, usuario, p.getProyOrgFk().getOrgPk(), true);

            if (p.getDocumentosSet() != null) {
                try {
                    for (String[] copy : copyFiles) {

                        System.out.println("MOVE FILE '" + copy[0] + "' to '" + copy[1] + "'");
//                        File archOrigen = new File(copy[0]);
//                        File archDestino = new File(copy[1]);
                        
//                        if(archDestino.isFile() && !(archDestino.exists())){
//                            FileUtils.copyFile(
//                                archOrigen,
//                                archDestino
//                            );
//                        }
                        FileUtils.copyFile(
                                new File(copy[0]),
                                new File(copy[1])
                        );
                    }
                } catch (IOException ex) {
                    throw new TechnicalException(ex);
                }
            }

        } catch (BusinessException ex) {
            throw ex;
        } catch (TechnicalException ex) {
            throw ex;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new TechnicalException(ex);
        } finally {
            MUTEX_MOVER_ARCHIVOS.release();
        }

        return p;

    }

    public Proyectos obtenerProyPorIdEager(Integer proyPk) {
        Proyectos p = obtenerProyPorId(proyPk, true, true, true, true, true, true);
        p.getProyPreFk().getAdquisicionSet().isEmpty();
        p.getParticipantesList().isEmpty();
        p.getCalidadList().isEmpty();
        p.getProyPublicaHist().isEmpty();

        return p;

    }

    public Proyectos copiarProyecto(Proyectos proyOrigen, String nombre, Date fechaComienzoProyCopia) {
        if (proyOrigen != null) {
            Date iniPeriodo = proyOrigen.getProyIndices().getProyindPeriodoInicio();
            Integer difFechas = DatesUtils.diasEntreFechas(iniPeriodo, fechaComienzoProyCopia);
            int desfasajeDias = difFechas != null ? difFechas : 0;

            Proyectos nvoProy = (Proyectos) proyOrigen.clone();

            nvoProy.setProyPk(null);
            nvoProy.setProyNombre(nombre);
            nvoProy.setProyEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
            nvoProy.setProyEstPendienteFk(null);
            Date date = new Date();
            nvoProy.setProyFechaCrea(date);
            nvoProy.setProyFechaAct(date);
            nvoProy.setProyFechaEstadoAct(null);
            nvoProy.setActivo(Boolean.TRUE);
            nvoProy.setProySituacionActual(null);
            nvoProy.setProyGrp(null);
            nvoProy.setProyIndices(null);

            nvoProy.setProyOtrosDatos(proyOtrosDatosBean.copiarProyOtrosDatos(proyOrigen.getProyOtrosDatos()));

            //Audit
            nvoProy.setProyUltUsuario(null);
            nvoProy.setProyUltMod(null);
            nvoProy.setProyUltOrigen(null);
            nvoProy.setProyVersion(0);

            //Colecciones
            nvoProy.setAreasTematicasSet(proyOrigen.getAreasTematicasSet());
            nvoProy.setAreasRestringidasSet(proyOrigen.getAreasRestringidasSet());

            nvoProy.setDocumentosSet(null);

            nvoProy.setInteresadosList(interesadosBean.copiarProyInteresados(proyOrigen.getInteresadosList()));
            nvoProy.setRiesgosList(riesgosBean.copiarProyRiesgos(proyOrigen.getRiesgosList(), nvoProy, desfasajeDias));

            Cronogramas cro = proyOrigen.getProyCroFk();
            nvoProy.setProyCroFk(cronogramasBean.copiarProyCronograma(cro, desfasajeDias));

            Cronogramas croNvo = nvoProy.getProyCroFk();
            nvoProy.setProyPreFk(presupuestoBean.copiarProyPresupuesto(proyOrigen.getProyPreFk(), (croNvo != null ? croNvo.getEntregablesSet() : null), desfasajeDias));

            return nvoProy;
        }
        return null;
    }

    /**
     * Retorna true si el proyecto tiene solicitud de cambio de estado.
     *
     * @param progPk
     * @return Boolean
     */
    public boolean cambioEstadoPorProg(Integer progPk) {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.cambioEstadoPorProg(progPk);
    }

    private boolean tieneCambiosConfPMOT(Proyectos proy, Proyectos proyPersistido) {
        boolean tieneCambios = false;

        if (proy != null && proyPersistido != null) {
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyAreaFk() != null && proy.getProyAreaFk() != null && !proyPersistido.getProyAreaFk().equals(proy.getProyAreaFk());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyProgFk() != null && proy.getProyProgFk() != null && !proyPersistido.getProyProgFk().equals(proy.getProyProgFk());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyPeso() != null && proy.getProyPeso() != null && !proyPersistido.getProyPeso().equals(proy.getProyPeso());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyUsrGerenteFk() != null && proy.getProyUsrGerenteFk() != null && !proyPersistido.getProyUsrGerenteFk().equals(proy.getProyUsrGerenteFk());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyUsrAdjuntoFk() != null && proy.getProyUsrAdjuntoFk() != null && !proyPersistido.getProyUsrAdjuntoFk().equals(proy.getProyUsrAdjuntoFk());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyUsrSponsorFk() != null && proy.getProyUsrSponsorFk() != null && !proyPersistido.getProyUsrSponsorFk().equals(proy.getProyUsrSponsorFk());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProyUsrPmofedFk() != null && proy.getProyUsrPmofedFk() != null && !proyPersistido.getProyUsrPmofedFk().equals(proy.getProyUsrPmofedFk());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProySemaforoAmarillo() != null && proy.getProySemaforoAmarillo() != null && !proyPersistido.getProySemaforoAmarillo().equals(proy.getProySemaforoAmarillo());
            tieneCambios = tieneCambios ? tieneCambios : proyPersistido.getProySemaforoRojo() != null && proy.getProySemaforoRojo() != null && !proyPersistido.getProySemaforoRojo().equals(proy.getProySemaforoRojo());

            //Area tematica
            List<AreasTags> listATproy = areaTematicaBean.obtenerAreasTematicasPorFichaPk(proy.getProyPk(), TipoFichaEnum.PROYECTO.id);
            List<AreasTags> listATproyPersit = areaTematicaBean.obtenerAreasTematicasPorFichaPk(proyPersistido.getProyPk(), TipoFichaEnum.PROYECTO.id);
            if (listATproyPersit != null
                    && listATproy != null
                    && (listATproyPersit.size() != listATproy.size()
                    || !listATproyPersit.containsAll(listATproy))) {
                return true;
            }

            //Lectura
            List<Areas> listAreaProy = areasBean.obtenerAreasRestringidasPorFichaPk(proy.getProyPk(), TipoFichaEnum.PROYECTO.id);
            List<Areas> listAreaProyPrestist = areasBean.obtenerAreasRestringidasPorFichaPk(proyPersistido.getProyPk(), TipoFichaEnum.PROYECTO.id);
            if (listAreaProyPrestist != null
                    && listAreaProy != null
                    && (listAreaProyPrestist.size() != listAreaProy.size()
                    || !listAreaProyPrestist.containsAll(listAreaProy))) {
                return true;
            }
            //MetodologÃƒÆ’Ã†â€™Ãƒâ€šÃ‚Â­a y Notificaciones no es necesario porque se guardan independientemente del proyecto.
        }
        return tieneCambios;
    }

    private boolean tieneCambiosPublicable(Proyectos proy, Proyectos proyPersistido) {

        if (proy != null && proyPersistido != null) {
            try {
                ProyectoImportado proyImp = exportarVisualizadorBean.proyToProyImp(proy, Boolean.FALSE, false);
                if (proyImp == null) {
                    logger.log(Level.SEVERE, "No es posible verficiar los cambios sobre los campos publicables");
                    return false;
                }
                ProyectoImportado proyPersistidoImp = exportarVisualizadorBean.proyToProyImp(proyPersistido, Boolean.FALSE, false);
                if (proyPersistidoImp == null) {
                    logger.log(Level.SEVERE, "No es posible verficiar los cambios sobre los campos publicables");
                    return false;
                }

                /**
                 * Eliminar elementos que no se quieren comparar
                 */
                proyImp.setProyDeptoAreaGerente(null);
                proyImp.setProyGerenteEmail(null);
                proyImp.setProyGerenteTel(null);
                proyImp.setProyImpAvanceFecha(null);
                proyImp.setProyImpFechaPub(null);

                proyPersistidoImp.setProyDeptoAreaGerente(null);
                proyPersistidoImp.setProyGerenteEmail(null);
                proyPersistidoImp.setProyGerenteTel(null);
                proyPersistidoImp.setProyImpAvanceFecha(null);
                proyPersistidoImp.setProyImpFechaPub(null);

                String proyImpString = JAXBUtils.marshal(proyImp, ProyectoImportado.class);
                String proyPersistidoImpString = JAXBUtils.marshal(proyPersistidoImp, ProyectoImportado.class);
                return !proyImpString.equals(proyPersistidoImpString);
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "No es posible verficiar los cambios sobre los campos publicables: " + ex.getMessage());
            }
        }
        return false;
    }

    public Integer porcentajeAvanceEnTiempo(Integer proyPk) {
        ProyIndices proyInd = proyIndicesBean.obtenerIndicePorProyId(proyPk);

        return porcentajeAvanceEnTiempo(proyInd.getProyindPeriodoInicio(), proyInd.getProyindPeriodoFin());
    }

    public Integer porcentajeAvanceEnTiempoLBase(Set<Entregables> entregables) {
        if (entregables != null) {
            Date inicio = null;
            Date fin = null;
            for (Entregables ent : entregables) {
                if (ent.getEntInicioLineaBase() != null && ent.getEntFinLineaBase() != null) {
                    if (inicio == null || DatesUtils.esMayor(inicio, ent.getEntInicioLineaBaseDate())) {
                        inicio = ent.getEntInicioLineaBaseDate();
                    }
                    if (fin == null || DatesUtils.esMayor(ent.getEntFinLineaBaseDate(), fin)) {
                        fin = ent.getEntFinLineaBaseDate();
                    }
                }
            }
            return porcentajeAvanceEnTiempo(inicio, fin);
        }
        return null;
    }

    public Integer porcentajeAvanceEnTiempo(Date inicio, Date fin) {
        if (inicio != null && fin != null) {
            Calendar inicioCal = new GregorianCalendar();
            inicioCal.setTime(inicio);
            Calendar finCal = new GregorianCalendar();
            finCal.setTime(fin);
            Calendar calNow = new GregorianCalendar();

            if (DatesUtils.esMayor(inicioCal.getTime(), finCal.getTime())) {
                return null;
            }
            if (calNow.before(inicioCal)) {
                return 0;
            } else if (calNow.after(finCal)) {
                return 100;
            }

            if (DatesUtils.fechasIguales(inicioCal.getTime(), finCal.getTime()) && DatesUtils.fechasIguales(calNow.getTime(), inicioCal.getTime())) {
                return 50;
            }

            Integer diffProy = DatesUtils.diasEntreFechas(inicioCal.getTime(), finCal.getTime());
            Integer diffNow = DatesUtils.diasEntreFechas(inicioCal.getTime(), calNow.getTime());
            return diffNow * 100 / diffProy;

        }
        return null;
    }

    public List<Proyectos> obtenerPorUsuarioParticipanteActivo(Integer usuId, Integer orgPk) {
        ProyectosDAO dao = new ProyectosDAO(em);

        MatchCriteriaTO criteriaOrg = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);
        MatchCriteriaTO criteriaUsu = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "participantesList.partUsuarioFk.usuId", usuId);
        MatchCriteriaTO criteriaActivo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "participantesList.partActivo", Boolean.TRUE);
        MatchCriteriaTO criteriaProyActivo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "activo", Boolean.TRUE);
        CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaOrg, criteriaUsu, criteriaActivo, criteriaProyActivo);

        String[] orderBy = {};
        boolean[] asc = {};

        try {
            List<Proyectos> listPart = dao.findEntityByCriteria(Proyectos.class, criteria, orderBy, asc, null, null);
            return listPart;
        } catch (BusinessException be) {
            throw be;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            BusinessException be = new BusinessException();
            be.addError(ex.getMessage());
            throw be;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void controlarProdAcumulados(Integer proyPk, Boolean actualizarPrograma) {
        List<Productos> listProd = productosBean.obtenerProdPorProyPk(proyPk);
        if (listProd != null) {
            for (Productos prod : listProd) {
                if (prod.isProdAcu()) {
                    if (prod.getProdMesList() != null) {
                        boolean primero = true;
                        Double plan = 0D;
                        Double real = 0D;
                        double planAnterior = 0;
                        double realAnterior = 0;
                        List<ProdMes> listProdMes = prodMesBean.obtenerOrdenadoPorFecha(prod.getProdPk());
                        for (ProdMes prodMes : listProdMes) {
                            if (primero) {
                                planAnterior = prodMes.getProdmesAcuPlan();
                                realAnterior = prodMes.getProdmesAcuReal();
                                prodMes.setProdmesPlan(planAnterior);
                                prodMes.setProdmesReal(realAnterior);
                                primero = false;
                            } else {
                                plan = prodMes.getProdmesAcuPlan() - planAnterior;
                                real = prodMes.getProdmesAcuReal() - realAnterior;
                                plan = plan < 0 ? 0 : plan;
                                real = real < 0 ? 0 : real;
                                prodMes.setProdmesPlan(plan);
                                prodMes.setProdmesReal(real);
                                planAnterior += plan;
                                realAnterior += real;
                            }
                        }
                    }
                } else {
                    prod = productosBean.calcularAcumulados(prod);
                }
                productosBean.guardarProducto(prod, actualizarPrograma);
            }
        }
    }

    private void cargarIndPresupuesto(Integer prePk, ProyIndices ind, Integer orgPk) {
        if (ind != null) {
            List<Moneda> monedas = presupuestoBean.obtenerMonedasPresupuesto(prePk, null);

            String[] codigos = new String[]{
                ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO,
                ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO};
            Map<String, Configuracion> confs = configuracionBean.obtenerCnfPorCodigoYOrg(orgPk, codigos);
            Integer limiteAmarillo;
            Integer limiteRojo;
            try {
                limiteAmarillo = Integer.valueOf(confs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO).getCnfValor());
                limiteRojo = Integer.valueOf(confs.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO).getCnfValor());
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Hay valores de la configuracion que no se pueden convertir a Integer.");
                limiteAmarillo = null;
                limiteRojo = null;
            }

            //0=color, 1=PV, 2=AC, 3=Anio
            HashMap<Integer, Object[]> indiceMonedaColor = presupuestoBean.obtenerColorAC(prePk, monedas, orgPk, limiteAmarillo, limiteRojo);

            if (ind.getProyIndPreSet() != null) {
                Set<ProyIndicesPre> preToRemove = new HashSet<>();
                for (ProyIndicesPre proyIndPre : ind.getProyIndPreSet()) {
                    if (!monedas.contains(new Moneda(proyIndPre.getProyindpreMonFk()))) {
                        preToRemove.add(proyIndPre);
                    }
                }
                ind.getProyIndPreSet().removeAll(preToRemove);
            }

            for (Moneda moneda : monedas) {
                ProyIndicesPre proyIndicesPre = null;
                if (ind.getProyIndPreSet() != null) {
                    for (ProyIndicesPre proyIndPre : ind.getProyIndPreSet()) {
                        if (moneda.getMonPk().equals(proyIndPre.getProyindpreMonFk())) {
                            proyIndicesPre = proyIndPre;
                            break;
                        }
                    }
                }

                if (proyIndicesPre == null) {
                    proyIndicesPre = new ProyIndicesPre();
                    if (ind.getProyindPk() != null) {
                        proyIndicesPre.setProyindpreProyindFk(ind.getProyindPk());
                    }
                    proyIndicesPre.setProyindpreMonFk(moneda.getMonPk());
                }

                proyIndicesPre.setProyindpreEstPre(programasProyectosBean.obtenerCodigoColorEstadoAc((String) (indiceMonedaColor.get(moneda.getMonPk())[0])));
                proyIndicesPre.setProyindpreTotal(presupuestoBean.obtenerTotalPorMoneda(prePk, moneda));
                proyIndicesPre.setProyindpreAnio((Double) (indiceMonedaColor.get(moneda.getMonPk())[3]));
                proyIndicesPre.setProyindprePV((Double) (indiceMonedaColor.get(moneda.getMonPk())[1]));
                proyIndicesPre.setProyindpreAC((Double) (indiceMonedaColor.get(moneda.getMonPk())[2]));
                if (proyIndicesPre.getProyindprePk() == null) {
                    if (ind.getProyIndPreSet() == null) {
                        ind.setProyIndPreSet(new HashSet<ProyIndicesPre>());
                    }
                    ind.getProyIndPreSet().add(proyIndicesPre);
                }
            }
        }
    }

    /**
     * Carga el estado de publicacion en el Visualizador si no tiene ninguno, o
     * lo actualiza.
     *
     * @param proy
     */
    private void controlarEstadoPublicacion(Proyectos proy) {
        if (proy != null) {
            if (proy.getProyOtrosDatos() == null) {
                proy.setProyOtrosDatos(new ProyOtrosDatos());
            }
            if (proy.getProyOtrosDatos().getProyOtrEstPubFk() == null
                    || !proy.getProyOtrosDatos().getProyOtrEstPubFk().getEstPubCodigo().equalsIgnoreCase(EstadoPublicacionCodigos.PUBLICADO)) {
                EstadosPublicacion ep;
                if (proy.isProyPublicable()) {
                    if (validarCamposPublicacion(proy)) {
                        ep = estadosPublicacionBean.obtenerPorCodigo(EstadoPublicacionCodigos.PENDIENTE_PUBLICAR);
                    } else {
                        ep = estadosPublicacionBean.obtenerPorCodigo(EstadoPublicacionCodigos.PENDIENTE_CARGAR);
                    }
                } else {
                    ep = estadosPublicacionBean.obtenerPorCodigo(EstadoPublicacionCodigos.NO_ES_PARA_PUBLICAR);
                }
                proy.getProyOtrosDatos().setProyOtrEstPubFk(ep);
            }
        }
    }

    /**
     * Valida si los campos obligatorios para publicar estÃƒÆ’Ã‚Â¡n cargados.
     *
     * @param proy
     * @return boolean true si los campos estan cargados.
     */
    private boolean validarCamposPublicacion(Proyectos proy) {
        if (proy != null) {
            ProyOtrosDatos proyOtros = proy.getProyOtrosDatos();
            return (proyOtros != null && proyOtros.getProyOtrEtaFk() != null)
                    && !StringsUtils.isEmpty(proy.getProyNombre())
                    && !StringsUtils.isEmpty(proy.getProyDescripcion())
                    && !StringsUtils.isEmpty(proy.getProyObjetivo())
                    && !StringsUtils.isEmpty(proy.getProyObjPublico())
                    //					&& (proy.getProyLatlngFk() != null && proy.getProyLatlngFk().getLatlangDepFk() != null)
                    && (proy.getLatLngProyList() != null && proy.getLatLngProyList().size() > 0)
                    && proyOtros.getProyOtrCatFk() != null;
        }
        return false;
    }

    private boolean validarCamposPublicacion2(Proyectos proy) {
        BusinessException be = new BusinessException();

        if (proy == null) {
            be.addError(MensajesNegocio.ERROR_PROYECTO);
        } else {
            ProyOtrosDatos proyOtros = proy.getProyOtrosDatos();

            if (proyOtros == null) {
                be.addError("No se ha ingresado Otros Datos.");

            } else {
                if (proyOtros.getProyOtrEtaFk() == null) {
                    be.addError("No se Ingresó la Etapa.");
                }
                if (proyOtros.getProyOtrCatFk() == null) {
                    be.addError("No se Ingresó la Categoría.");
                }
            }

            if (StringsUtils.isEmpty(proy.getProyNombre())) {
                be.addError("Debe ingresar el nombre del Proyecto");
            }
            if (StringsUtils.isEmpty(proy.getProyDescripcion())) {
                be.addError("Debe ingresar la Descripción");
            }
            if (StringsUtils.isEmpty(proy.getProyObjetivo())) {
                be.addError("Debe ingresar el Objetivo");
            }
            if (StringsUtils.isEmpty(proy.getProyObjPublico())) {
                be.addError("Debe ingresar el Objetivo Publico");
            }
//			if (proy.getProyLatlngFk() != null && proy.getProyLatlngFk().getLatlangDepFk() == null) {
            if (proy.getLatLngProyList() != null && proy.getLatLngProyList().size() > 0) {
                be.addError("Debe ingresar la ubicación en el Departamento.");
            }

        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }

    public List<FichaTO> buscarPorFiltro(FiltroExpVisuaTO filtro) {
        if (filtro != null) {
            ProyectoExpVisualizadorDAO dao = new ProyectoExpVisualizadorDAO(em);
            return dao.buscarPorFiltroExpVisua(filtro);
        }
        return null;
    }

    /**
     * Exporta el proyecto indicado al Visualizador. Si todo es correcto
     * actualiza el estado y registra la fecha de publicaciÃƒÂ³n.
     *
     * @param fichaFk
     * @param usuario
     * @param todosLosMedia
     * @return Proyectos actualizado.
     */
    public Proyectos exportarVisualizador(Integer fichaFk, SsUsuario usuario, Boolean todosLosMedia) {

        try {

            Proyectos proy = obtenerProyPorId(fichaFk, true);
            if (proy != null) {
                List<MediaProyectos> listMP = mediaProyectosBean.obtenerPorProyId(proy.getProyPk());
                proy.setProyMediaProyectos(new HashSet<MediaProyectos>(listMP));

                isExportableVisualizador(proy);
                String result = exportarVisualizadorBean.exportar(proy, todosLosMedia);
                logger.log(Level.INFO, "Resultado exportar proy '" + proy.getProyPk() + "':{0}", result);
                boolean exportado = false;
                if (result != null && result.equalsIgnoreCase("0")) {
                    exportado = true;
                }

                proy = obtenerProyPorId(fichaFk, true);
                if (exportado) {
                    proy.getProyPublicaHist().isEmpty();
                    proy.setProyPublicaHist(new HashSet<>(proyPublicaHistBean.obtenerHistoricoTodos(proy.getProyPk())));
                    if (proy.getProyPublicaHist() == null) {
                        proy.setProyPublicaHist(new HashSet<ProyPublicaHist>());
                    }
                    ProyPublicaHist pph = new ProyPublicaHist(null, new Date(), proy, usuario);
                    proy.getProyPublicaHist().add(pph);

                    EstadosPublicacion estPub = estadosPublicacionBean.obtenerPorCodigo(EstadoPublicacionCodigos.PUBLICADO);
                    if (proy.getProyOtrosDatos() == null) {
                        proy.setProyOtrosDatos(new ProyOtrosDatos());
                    }
                    proy.getProyOtrosDatos().setProyOtrEstPubFk(estPub);
                    //no actualiza los indicadores, no es necesario.
                    proy = guardar(proy, usuario, proy.getProyOrgFk().getOrgPk(), false);
                    proy.getProyPublicaHist().isEmpty();

                    return proy;

                } else {
                    BusinessException ex = new BusinessException();
                    ex.addError(result);
                    throw ex;
                }
            }
        } catch (BusinessException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
        return null;
    }

    /**
     * Valida si el proyecto es valido para exportar al Visualizador, de lo
     * contrario lanza una exepcion.
     *
     * @param proy
     */
    private void isExportableVisualizador(Proyectos proy) {
        if (proy != null) {
            if (!proy.isProyPublicable()) {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.WARN_EXP_VISUA_EST_PUBLICA);
                throw be;
            }
//            validarCamposPublicacion2(proy);

            if (!validarCamposPublicacion(proy)) {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.WARN_EXP_VISUA_CAMPOS_REQUERIDOS);
                throw be;
            }

            if (proy.getProyOtrosDatos() != null) {
                //Estado de Publicacion
                if (proy.getProyOtrosDatos().getProyOtrEstPubFk() == null
                        || (proy.getProyOtrosDatos().getProyOtrEstPubFk() != null
                        && !(proy.getProyOtrosDatos().getProyOtrEstPubFk().getEstPubCodigo().equalsIgnoreCase(EstadoPublicacionCodigos.PENDIENTE_PUBLICAR)
                        || proy.getProyOtrosDatos().getProyOtrEstPubFk().getEstPubCodigo().equalsIgnoreCase(EstadoPublicacionCodigos.PUBLICADO)))) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.WARN_EXP_VISUA_EST_PUBLICA);
                    throw be;
                }
                //Proyecto no puede pesar mas de 30MB(parametrizable)

                //Las fotos no pueden ser mayores a 500KB(parametrizable)
                Integer orgPk = proy.getProyOrgFk().getOrgPk();

                Set<MediaProyectos> setMP = proy.getProyMediaProyectos();
                if (CollectionsUtils.isNotEmpty(setMP)) {
                    List<MediaProyectos> listMP = mediaProyectosBean.cargarArchivos(new ArrayList<>(setMP), orgPk);
                    setMP = new HashSet<>(listMP);

                    for (MediaProyectos mp : setMP) {
                        mediaProyectosBean.validarImageSize(mp.getMediaBytes(), orgPk);
                    }
                }
                //El tamaÃƒÆ’Ã‚Â±o de los archivos en documentos no puede ser mayor a 5MB(parametrizable). Esto queda pendiente.
            }
        }
    }

    /**
     * @param usuId
     * @param orgPk
     * @return lista de IdNombreTO.
     * @see ProyectosDAO#obtenerProyIdNombre(java.lang.Integer,
     * java.lang.Integer)
     */
    public List<IdNombreTO> obtenerProyIdNombre(Integer usuId, Integer orgPk) {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.obtenerProyIdNombre(usuId, orgPk);
    }

    public boolean esColaboradorProy(Integer usuId, Integer proyPk) {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.esColaboradorProy(usuId, proyPk);
    }

    public Integer obtenerOrgPkPorProyPk(Integer proyPk) {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.obtenerOrgPkPorProyPk(proyPk);
    }

    public ProyectoTO obtenerProyTO(Integer proyPk) {
        ProyectosDAO dao = new ProyectosDAO(em);
        return dao.obtenerProyTO(proyPk);
    }

    /**
     * Envía la notificación según el código si es que aún no ha sido enviada.
     *
     * @param codNot
     * @param proy
     * @param orgPk
     */
    public void enviarNotificacion(String codNot, Proyectos proy, Integer orgPk) {
        Configuracion conCorreConf = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.CON_CORREO, orgPk);
        if (conCorreConf != null && conCorreConf.getCnfValor() != null && "true".equals(conCorreConf.getCnfValor().toLowerCase())) {
            if (!fueNotificado(codNot, proy.getProyPk())) {
                NotificacionInstancia ni = notificacionInstanciaBean.obtenerNotificacionInstPorCod(codNot, proy.getProyPk(), orgPk);

                if (ni != null) {
                    String subject = LabelsEJB.getValue("notif_envio_subjet", orgPk);

                    List<SsUsuario> usuariosDest = new ArrayList<>();
                    if (ni.getNotinstGerenteAdjunto()) {
                        usuariosDest.add(proy.getProyUsrGerenteFk());
                        usuariosDest.add(proy.getProyUsrAdjuntoFk());
                    }
                    if (ni.getNotinstSponsor()) {
                        usuariosDest.add(proy.getProyUsrSponsorFk());
                    }
                    if (ni.getNotinstPmof()) {
                        usuariosDest.add(proy.getProyUsrPmofedFk());
                    }
                    if (ni.getNotinstPmot()) {
                        String[] orden = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
                        boolean[] asc = new boolean[]{true, true, true, true};
                        usuariosDest.addAll(ssUsuarioBean.obtenerUsuariosPorRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk, orden, asc));
                    }
                    String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosDest);

                    Map<String, String> valores = new HashMap<>();
                    valores.put(MailVariables.NOMBRE_PROYECTO, proy.getProyPk() + " - '" + proy.getProyNombre() + "'");
                    
                    Organismos org = organismoBean.obtenerOrgPorId(orgPk, false);
                    if (org != null) {
                        valores.put(MailVariables.ORGANISMO_NOMBRE, org.getOrgNombre());
                    }
                    
                    String urlSistema = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.URL_SISTEMA, null);
                    valores.put(MailVariables.URL_SISTEMA, urlSistema);

                    String urlProyecto = ProgProyUtils.obtenerURL(urlSistema, proy);
                    valores.put(MailVariables.URL_PROYECTO, urlProyecto);
                    
                    String mensaje = MailsTemplateUtils.instanciarConHashMap(ni.getNotinstNotFk().getNotMsg(), valores);

                    final String subjectThread = subject;
                    final String[] destinatariosThread = destinatarios;
                    final String mensajeThread = mensaje;
                    final Integer orgPkThread = orgPk;
                    final String codNotThread = codNot;
                    final Proyectos proyThread = proy;

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mailBean.enviarMail(subjectThread, null, null, null, destinatariosThread, mensajeThread, orgPkThread);
                                agregarNotificacionEnvio(codNotThread, proyThread.getProyPk());
                            } catch (MailException me) {
                                for (String error : me.getErrores()) {
                                    logger.log(Level.WARNING, error);
                                }
                            }
                        }
                    });
                    t.start();
                } else {
                    logger.log(Level.WARNING, "No se envía notificación porque no existe la instancia '" + codNot + "' para el proyecto " + proy.getProyPk());
                }
            }
        }
    }

    /**
     * Consulta si la notificación para dicho código y proyecto ya fue enviada.
     *
     * @param codNot
     * @param proyPk
     * @return boolean true si fue enviada.
     */
    public boolean fueNotificado(String codNot, Integer proyPk) {
        NotificacionEnvioDAO dao = new NotificacionEnvioDAO(em);
        CriteriaTO criteriaCod = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "notenvNotCod", codNot);
        CriteriaTO criteriaProy = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "notenvProyFk", proyPk);
        CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaCod, criteriaProy);
        /*
        *   26-03-18 Nico: Se cambia en la siguiente línea el true por un false.
        */
        
        List<NotificacionEnvio> neList = dao.obtenerEntityByCriteria(NotificacionEnvio.class, criteria, new String[]{}, new boolean[]{}, null, null, false);

        return CollectionsUtils.isNotEmpty(neList);
    }

    public NotificacionEnvio agregarNotificacionEnvio(String cod, Integer proyPk) {
        NotificacionEnvio ne = new NotificacionEnvio(null, new Date(), proyPk, cod);
        NotificacionEnvioDAO dao = new NotificacionEnvioDAO(em);
        try {
            ne = dao.guardar(ne, true);
            return ne;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    boolean esURL (String dir){
        /*
        *     Este código se usa para ver si lo que se le pasa por parámetro es una URL.
        *   En caso de ser una dirección se cumplirá la sentencia del "try", sino se entra
        *   al catch.
        */
        try {
            new URL(dir).toURI();
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }
    

}
