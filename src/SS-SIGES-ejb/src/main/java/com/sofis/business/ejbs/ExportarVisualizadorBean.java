package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.VisualizadorWSUtils;
import com.sofis.data.daos.DocumentosDao;
import com.sofis.data.daos.MediaProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.LatlngProyectos;
import com.sofis.entities.data.MediaProyectos;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.PGEProxyException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.visualizador.ws.client.PGEProxyBean;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;
import org.agesic.siges.visualizador.web.ws.CategoriaProyectosResponse;
import org.agesic.siges.visualizador.web.ws.DocumentosImp;
import org.agesic.siges.visualizador.web.ws.EntregablesImp;
import org.agesic.siges.visualizador.web.ws.EstadoProyectos;
import org.agesic.siges.visualizador.web.ws.MediaImpProyectos;
import org.agesic.siges.visualizador.web.ws.ObtenerCategoriasXml;
import org.agesic.siges.visualizador.web.ws.ProyectoImportado;
import org.agesic.siges.visualizador.web.ws.PublicarProyecto;
import org.agesic.siges.visualizador.web.ws.PublicarProyecto_Service;
import org.apache.commons.lang.BooleanUtils;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Named
@Stateless(name = "ExportarVisualizadorBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class ExportarVisualizadorBean {

    private static final Logger logger = Logger.getLogger(ExportarVisualizadorBean.class.getName());
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

//    @WebServiceRef(wsdlLocation = "META-INF/wsdl/localhost_16456/SigesVisualizadorPrivado/PublicarProyecto.wsdl")
    private PublicarProyecto_Service service;

    @Inject
    private ConfiguracionBean configuracionBean;
//    @Inject
//    private DatosUsuario du;
    @EJB
    private CronogramasBean cronogramasBean;
    @EJB
    private EntregablesBean entregablesBean;
    @EJB
    private MediaProyectosBean mediaProyectosBean;

    @EJB
    private DocumentosBean documentosBean;

    @EJB
    private ProyPublicaHistBean proyPublicaHistBean;
    @EJB
    private ConfiguracionBean conf;

    @Inject
    private LatlngBean latlngBean;
    @Inject
    private PGEProxyBean proxyBean;
    @Inject
    private ProySitActHistoricoBean sitActHistoricoBean;
    @Inject
    private OrganismoBean organismoBean;

    public String exportar(Proyectos proySiges, Boolean todosLosMedia) {
        //invoca al ws de publicacion del visualizador
        try { // Call Web Service Operation

            /*
                        *   11-04-2018 Nico: Busca en esta configuración para saber con que versión exportar los proyectos,
                        *           es decir, si es versión 1 se envia una geoloxalización y no se envian documentos,
                        *           si es versión 2, se envia todo.
             */
            Configuracion cnfVersion = conf.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_VERSION, proySiges.getProyOrgFk().getOrgPk());

            String versionParaExportar = "2";

            if (cnfVersion != null) {
                versionParaExportar = cnfVersion.getCnfValor();
            }

            Integer proySigesOrgPk = proySiges.getProyOrgFk().getOrgPk();

            ProyectoImportado proyVisualizador = proyToProyImp(proySiges, todosLosMedia);

            /**
             * 05-11-17 Bruno: marcar media y docs con la fecha de publicación
             */
            if (proyVisualizador.getMediaImpProyectosList() != null && !proyVisualizador.getMediaImpProyectosList().isEmpty()) {
                MediaProyectosDAO mediaProyectosDAO = new MediaProyectosDAO(em);
                MediaProyectos mAux;
                for (MediaImpProyectos m : proyVisualizador.getMediaImpProyectosList()) {
                    mAux = mediaProyectosDAO.findById(MediaProyectos.class, m.getMediaOrigenPk());
                    mAux.setMediaPubFecha(new Date());
                    mediaProyectosDAO.update(mAux);
                }
            }
            /*
                        *   11-04-2018 Nico: En la condición del if agrego que se fije si estoy en version "2" con la configuración para poder
                        *               saber si tengo documentos.
             */
            if (proyVisualizador.getDocsImpList() != null && !proyVisualizador.getDocsImpList().isEmpty() && versionParaExportar.equals("2")) {
                DocumentosDao documentosDao = new DocumentosDao(em);
                Documentos dAux;
                for (DocumentosImp d : proyVisualizador.getDocsImpList()) {
                    dAux = documentosDao.findById(Documentos.class, d.getDocsImpOrigenPk());
                    dAux.setDocsPubFecha(new Date());
                    documentosDao.update(dAux);
                }
            }

            //Resultado de la exportación
            String result = null;

            String expPorPGE = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_EXPORTACION_POR_PGE, proySigesOrgPk);

            if (StringsUtils.isEmpty(expPorPGE) || expPorPGE.equalsIgnoreCase("true")) {
                //Por PGE
                try {
                    result = proxyBean.proxyPublicarProyecto(ConstanteApp.APP_NAME, proyVisualizador, proySigesOrgPk);
                } catch (PGEProxyException pGEProxyException) {
                    logger.log(Level.SEVERE, null, pGEProxyException);
                    BusinessException be = new BusinessException(pGEProxyException);
                    be.addError(MensajesNegocio.ERROR_EXP_VISUA_PGE_PROXY);
                    throw be;
                }
            } else {
                //Sin PGE
                try {
                    PublicarProyecto port = publicarProyectoSinPGE(proySigesOrgPk);
                    result = port.publicarProyecto(proyVisualizador);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Error Exportación Proyecto Sin PGE.", ex);
                    BusinessException be = new BusinessException(ex);
                    be.addError(MensajesNegocio.ERROR_EXP_VISUA_WS_PROXY);
                    throw be;
                }
            }

            return result;
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, null, be);
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException(ex);
            be.addError(MensajesNegocio.ERROR_EXP_VISUA_DATOS);
            throw be;
        }
    }

    public List<CategoriaProyectos> obtenerCategorias(Integer orgPk) {
		
		Organismos org = organismoBean.obtenerOrgPorId(orgPk, false);
		
        CategoriaProyectosResponse response = null;

        String expPorPGE = configuracionBean.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_EXPORTACION_POR_PGE, orgPk);

        if (StringsUtils.isEmpty(expPorPGE) || expPorPGE.equalsIgnoreCase("true")) {
            //Por PGE
            try {
                response = proxyBean.proxyObtenerCategorias(ConstanteApp.APP_NAME, orgPk, org.getOrgToken());
            
			} catch (PGEProxyException pGEProxyException) {
                logger.log(Level.SEVERE, null, pGEProxyException);
                BusinessException be = new BusinessException();
                be.addError("Error al conectar con el visualizador");
                throw be;
            }
        } else {
            //Sin PGE
            try {
                PublicarProyecto port = publicarProyectoSinPGE(orgPk);
                response = port.obtenerCategoriasXml(org.getOrgToken());
				
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "Error Obtener Categorias Sin PGE.", ex);
                BusinessException be = new BusinessException();
                be.addError("Error al conectar con el visualizador");
                throw be;
            }
        }

		if (response.getError() != null && !response.getError().equals("0")) {

			BusinessException be = new BusinessException();
			be.addError(response.getError());
			
			throw be;
		}

		List<CategoriaProyectos> listCP = new ArrayList<>();
		for (org.agesic.siges.visualizador.web.ws.CategoriaProyectos categoriaProyectos : response.getListCatProy()) {
			CategoriaProyectos cp = VisualizadorWSUtils.convertCategoriaProyectos(categoriaProyectos, orgPk);
			listCP.add(cp);
		}
		
		return listCP;
    }

    private PublicarProyecto publicarProyectoSinPGE(Integer orgPk) {
        String paramURLWSDL = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_WSDL, orgPk);
        String paramendpointURL = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_SOAPACTION, orgPk);

        try {
            URL url = new URL(paramURLWSDL);
            QName qname = new QName("http://ws.web.visualizador.siges.agesic.org/", "PublicarProyecto");
            service = new PublicarProyecto_Service(url, qname);
            PublicarProyecto port = service.getPublicarProyectoPort();
            BindingProvider bp = (BindingProvider) port;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, paramendpointURL);

            if (bp.getBinding() instanceof SOAPBinding) {
                SOAPBinding binding = (SOAPBinding) bp.getBinding();
                binding.setMTOMEnabled(true);
            }

            return port;
        } catch (MalformedURLException malformedURLException) {
            logger.log(Level.SEVERE, "Error WS Sin PGE:" + paramendpointURL, malformedURLException);
            BusinessException be = new BusinessException();
            be.addError("Error al conectar con visualizador");
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error WS Sin PGE:" + paramendpointURL, ex);
            BusinessException be = new BusinessException();
            be.addError("Error al conectar con visualizador");
            throw be;
        }
    }

    /**
     * Pasar del proySiges al proyVisualizador
     *
     * @param proySiges
     * @return ProyectoImportado
     */
    private ProyectoImportado proyToProyImp(Proyectos proySiges, Boolean todosLosMedia) {
        /*
                *   11-04-2018 Nico: Pido la configuración para saber si agrego una localización y si tengo que agregar documentos
         */

        Configuracion cnfVersion = conf.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_VERSION, proySiges.getProyOrgFk().getOrgPk());

        String versionParaExportar = "2";

        if (cnfVersion != null) {
            versionParaExportar = cnfVersion.getCnfValor();
        }

        Integer proySigesOrgPk = proySiges.getProyOrgFk().getOrgPk();

        ProyectoImportado proyVisualizador = new ProyectoImportado();

        proyVisualizador.setProyImpNombre(proySiges.getProyNombre());
        proyVisualizador.setProyImpAvanceFecha(VisualizadorWSUtils.dateToXMLGregorianCalendar(new Date()));
        proyVisualizador.setProyImpCompartido(0L);
        proyVisualizador.setProyImpContacto("");
        proyVisualizador.setProyImpDescripcion(proySiges.getProyDescripcion());
        proyVisualizador.setProyImpDescripcionStrip(StringsUtils.stripHtml(proySiges.getProyDescripcion(), ""));
        proyVisualizador.setProyImpFechaPub(VisualizadorWSUtils.dateToXMLGregorianCalendar(proySiges.getProyFechaAct()));

        if (proySiges.getProyAreaFk() != null) {
            proyVisualizador.setProyImpAreaPk(proySiges.getProyAreaFk().getAreaPk());
            proyVisualizador.setProyImpAreaNombre(proySiges.getProyAreaFk().getAreaNombre());
            proyVisualizador.setProyImpareaAbreviacion(proySiges.getProyAreaFk().getAreaAbreviacion());
        }

//        int[] arrParcial = cronogramasBean.calcularAvanceCronoParcial(proySiges.getProyCroFk());
//        proyVisualizador.setProyImpAvancePorc(arrParcial[0]);

        Long proyFechaInicio = null;

           if (proySiges.getProyOtrosDatos() != null) {
                if (proySiges.getProyOtrosDatos().getProyOtrCatFk() != null) {
                    org.agesic.siges.visualizador.web.ws.CategoriaProyectos cp = VisualizadorWSUtils.convertCategoriaProyectos(proySiges.getProyOtrosDatos().getProyOtrCatFk());
                    proyVisualizador.setProyImpTemaFk(cp);

                    if (proySiges.getProyOtrosDatos().getProyOtrosCatSecundarias() != null) {
                        List<CategoriaProyectos> listCatSec = proySiges.getProyOtrosDatos().getProyOtrosCatSecundarias();
                        List<org.agesic.siges.visualizador.web.ws.CategoriaProyectos> listCatSecImp = VisualizadorWSUtils.convertCategoriaProyectos(listCatSec);
                        if (listCatSecImp != null) {
                            proyVisualizador.getProyCategsList().addAll(listCatSecImp);
                        }
                    }
                }

                if (proySiges.getProyOtrosDatos().getProyOtrInsEjeFk() != null) {
                    proyVisualizador.setProyImpEjecOrgPk(VisualizadorWSUtils.convertOrgaIntProve(proySiges.getProyOtrosDatos().getProyOtrInsEjeFk()));
                }

                if (proySiges.getProyUsrGerenteFk() != null) {
                    SsUsuario gerente = proySiges.getProyUsrGerenteFk();
                    proyVisualizador.setProyGerente(gerente.getUsuNombreApellido());
                    proyVisualizador.setProyGerenteEmail(gerente.getUsuCorreoElectronico());
                    proyVisualizador.setProyGerenteTel(gerente.getUsuTelefono());
                    Areas areaGerente = gerente.getUsuArea(proySigesOrgPk);
                    proyVisualizador.setProyDeptoAreaGerente(areaGerente != null ? areaGerente.getAreaNombre() : null);
                }

                if (proySiges.getProyOtrosDatos().getProyOtrEtaFk() != null) {
                    EstadoProyectos est = new EstadoProyectos();
                    est.setEstPk(proySiges.getProyOtrosDatos().getProyOtrEtaFk().getEtaPk());
                    est.setEstCodigo(proySiges.getProyOtrosDatos().getProyOtrEtaFk().getEtaCodigo());
                    proyVisualizador.setProyImpEstadoFk(est);
                }

                if (proySiges.getProyOtrosDatos().getProyOtrEntFk() != null) {
                    proyFechaInicio = proySiges.getProyOtrosDatos().getProyOtrEntFk().getEntInicio();
                }

                proyVisualizador.setProyImpObservaciones(proySiges.getProyOtrosDatos().getProyOtrObservaciones());
                proyVisualizador.setProyImpOrigenrecOrg(proySiges.getProyOtrosDatos().getProyOtrOrigen());
                proyVisualizador.setProyImpOrigenrecOrgPk(null);
                proyVisualizador.setProyImpProvOrgFk(VisualizadorWSUtils.convertOrgaIntProve(proySiges.getProyOtrosDatos().getProyOtrContFk()));
                proyVisualizador.setProyImpPlazoEstimado(proySiges.getProyOtrosDatos().getProyOtrPlazo());
            }

            if (proySiges.getProyOtrosDatos() != null && proySiges.getProyOtrosDatos().getProyOtrEntFk() != null) {
                proyFechaInicio = proySiges.getProyOtrosDatos().getProyOtrEntFk().getEntInicio();
                proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(proyFechaInicio));
                if (proySiges.getProyOtrosDatos().getProyOtrPlazo() != null && proySiges.getProyOtrosDatos().getProyOtrPlazo() > 0) {
                    GregorianCalendar calFin = new GregorianCalendar();
                    calFin.setTime(new Date(proyFechaInicio));

                    /*
                    *   27-06-2018 Nico: Se agrega el "-1" para que no exporte con un día de más
                    */                                        
                    
                    calFin.add(Calendar.DAY_OF_YEAR, proySiges.getProyOtrosDatos().getProyOtrPlazo()-1);
                    proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(calFin.getTime()));
                    proyVisualizador.setProyImpPlazoEstimado(proySiges.getProyOtrosDatos().getProyOtrPlazo());
                } else {
                    proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(new Date(proySiges.getProyOtrosDatos().getProyOtrEntFk().getEntFin())));
                    proyVisualizador.setProyImpPlazoEstimado(DatesUtils.diasEntreFechas(proyVisualizador.getProyImpFechaInicio().toGregorianCalendar().getTime(), proyVisualizador.getProyImpFechaFin().toGregorianCalendar().getTime()));
                }
                List<Entregables> entHijos = EntregablesUtils.obtenerHijos(new ArrayList<>(proySiges.getProyCroFk().getEntregablesSet()), proySiges.getProyOtrosDatos().getProyOtrEntFk(), Boolean.TRUE);
                Set<Entregables> setEntAvance = new HashSet<>(entHijos);
                int[] arrParcial = cronogramasBean.calcularAvanceCronoParcial(setEntAvance);
                proyVisualizador.setProyImpAvancePorc(arrParcial[0]);

            } else if (proySiges.getProyCroFk() != null && proySiges.getProyCroFk().getEntregablesSet() != null) {

                int[] arrParcial = cronogramasBean.calcularAvanceCronoParcial(proySiges.getProyCroFk());
                proyVisualizador.setProyImpAvancePorc(arrParcial[0]);

                Configuracion cnfPeriodoCnf = configuracionBean.obtenerCnfPorCodigoYOrg("PROYECTO_GANTT_PERIODO", proySiges.getProyOrgFk().getOrgPk());
                Boolean cnfPeriodo = cnfPeriodoCnf != null && cnfPeriodoCnf.getCnfValor() != null && "true".equalsIgnoreCase(cnfPeriodoCnf.getCnfValor());

                /**
                 * Ticket #897850 - 2018-02-17 Bruno: Modificado para tomar la misma
                 * fecha de inicio y fin que aparece en la pantalla de inicio.
                 */
                proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(proySiges.getProyIndices().getProyindPeriodoInicio()));
                proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(proySiges.getProyIndices().getProyindPeriodoFin()));

                /**
                 * 02-06-17: Se agrega para que tome en cuenta si la
                 * configuración de seleccionar el período en el gantt está
                 * activa.
                 */
                Date entIni = entregablesBean.obtenerPrimeraFecha(proySiges.getProyCroFk().getEntregablesSet(), cnfPeriodo);
                Date entFin = entregablesBean.obtenerUltimaFecha(proySiges.getProyCroFk().getEntregablesSet(), cnfPeriodo);
                proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(entIni.getTime()));
                proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(entFin.getTime()));
                proyVisualizador.setProyImpPlazoEstimado(DatesUtils.diasEntreFechas(proyVisualizador.getProyImpFechaInicio().toGregorianCalendar().getTime(), proyVisualizador.getProyImpFechaFin().toGregorianCalendar().getTime()));
            }


//		if (proySiges.getProyOtrosDatos() != null
//			&& proyFechaInicio != null
//			&& proySiges.getProyOtrosDatos().getProyOtrPlazo() > 0) {
//
//			GregorianCalendar cal = new GregorianCalendar();
//			cal.setTime(new Date(proyFechaInicio));
//			cal.add(Calendar.DAY_OF_YEAR, proySiges.getProyOtrosDatos().getProyOtrPlazo());
//
//			proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(cal.getTime()));
//
//		} else if (proySiges.getProyCroFk() != null && proySiges.getProyCroFk().getEntregablesSet() != null) {
//
//			Configuracion cnfPeriodoCnf = configuracionBean.obtenerCnfPorCodigoYOrg("PROYECTO_GANTT_PERIODO", proySiges.getProyOrgFk().getOrgPk());
//			Boolean cnfPeriodo = cnfPeriodoCnf != null && cnfPeriodoCnf.getCnfValor() != null && "true".equalsIgnoreCase(cnfPeriodoCnf.getCnfValor());
        /**
         * 02-06-17: Se agrega para que tome en cuenta si la configuración de
         * seleccionar el período en el gantt está activa.
         */
//			Date entIni = entregablesBean.obtenerPrimeraFecha(proySiges.getProyCroFk().getEntregablesSet(), cnfPeriodo);
//			Date entFin = entregablesBean.obtenerUltimaFecha(proySiges.getProyCroFk().getEntregablesSet(), cnfPeriodo);
//			proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(entIni.getTime()));
//			proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(entFin.getTime()));
//		}
        if (proySiges.getProyPreFk() != null) {

            proyVisualizador.setProyImpInvMonedaMonFk(VisualizadorWSUtils.convertMoneda(proySiges.getProyPreFk().getPreMoneda()));
            proyVisualizador.setProyImpFinanciamientoFueFk(VisualizadorWSUtils.convertFuenteFinanciamiento(proySiges.getProyPreFk().getFuenteFinanciamiento()));
            proyVisualizador.setProyImpInvTotal(proySiges.getProyPreFk().getPreBase());
        }

        proyVisualizador.setProyImpModificado(null);
        proyVisualizador.setProyImpObjPublico(proySiges.getProyObjPublico());
        proyVisualizador.setProyImpObjetivo(proySiges.getProyObjetivo());
        proyVisualizador.setProyImpOrigenPk(proySiges.getProyPk());
        proyVisualizador.setProyImpOrgFk(VisualizadorWSUtils.convertOrganismos(organismoBean.obtenerOrgPorId(proySiges.getProyOrgFk().getOrgPk(), true)));
        proyVisualizador.setProyImpUsrMod(null);
        proyVisualizador.setProyImpUsrPub(null);
        proyVisualizador.setProyImpSitActual(proySiges.getProySituacionActual());
        Date fechaSitAct = sitActHistoricoBean.obtenerUltimaFechaSitAct(proySiges.getProyPk());
        proyVisualizador.setProyImpSitActualDate(VisualizadorWSUtils.dateToXMLGregorianCalendar(fechaSitAct));

        //Entregables
        if (proySiges.getProyCroFk() != null && proySiges.getProyCroFk().getEntregablesSet() != null) {
            List<Entregables> entList = EntregablesUtils.entregablesSinPadres(new ArrayList<>(proySiges.getProyCroFk().getEntregablesSet()));
            List<EntregablesImp> impEntregables = VisualizadorWSUtils.convertEntregables(entList);
            if (impEntregables != null) {
                proyVisualizador.getEntregablesProyectoImpList().addAll(impEntregables);
            }
        }

        //Media
        Date ultimaExportacion = proyPublicaHistBean.obtenerUltimaFecha(proySiges.getProyPk());
        StringBuilder sbMediasIds = new StringBuilder("");
        //solo los media que fueron modificados se vuelven a importar
        Set<MediaProyectos> mediaProyList = proySiges.getProyMediaProyectos();
        List<MediaProyectos> toImportMedia = new ArrayList<>();
        boolean mediaModMayorMediaPub = false;
        for (MediaProyectos mediaProy : mediaProyList) {
            if (mediaProy.esMediaPublicable()) {
                //Añadirlo a la lista de multimedia del proyecto
                if (sbMediasIds.length() > 0) {
                    sbMediasIds.append(",");
                }
                sbMediasIds.append(mediaProy.getMediaPk().toString());

                mediaModMayorMediaPub = false;
                // la fecha de modificacion
                Date toCompare = mediaProy.getMediaModFecha();
                if (toCompare == null) {
                    // la fecha de creacion
                    toCompare = mediaProy.getMediaPubFecha();
                }

                /**
                 * Bruno 14-08-17: puede pasar que la imágenes no se exporte
                 * porque el tamaño del archivo supera al configurado al momento
                 * de exportar. Luego se cambia por configuración el tamaño del
                 * archivo que se puede exportar y esta imágenes que quedaron
                 * sin exportar, tienen que exportarse. En este caso, estas
                 * imágenes quedan con fecha de creación anterior a la de la
                 * última publicación, pero nunca fueron exportadas. Por eso se
                 * agrega esta condición.
                 *
                 */
                if (mediaProy.getMediaModFecha() != null) {
                    mediaModMayorMediaPub = mediaProy.getMediaPubFecha() == null || mediaProy.getMediaModFecha().after(mediaProy.getMediaPubFecha());
                }

                /**
                 * Bruno 25-07-17: se cambia par que compare los timestamp de la
                 * fecha de modificación y de publicación. Antes comparaba en el
                 * orden de días y por eso quedaban imágenes sin exportar.
                 */
                if (todosLosMedia
                        || mediaModMayorMediaPub
                        || toCompare == null
                        || ultimaExportacion == null
                        || toCompare.after(ultimaExportacion)) {
                    toImportMedia.add(mediaProy);
                }
            }
        }

        proyVisualizador.setMediaImpProyectosIds(sbMediasIds.toString());

        List<MediaProyectos> listMP = mediaProyectosBean.cargarArchivos(toImportMedia, proySigesOrgPk);
        List<MediaImpProyectos> listImpMP = mediaProyectosBean.crearMediaProyFromImp(listMP);

        if (listImpMP != null) {
			
            proyVisualizador.getMediaImpProyectosList().addAll(listImpMP);
        }

        if (versionParaExportar.equals("1")) {

            // No pongo media
            proyVisualizador.setMediaImpProyectosIds(null);

            // No pongo los documentos y solo agrego una localización
            Set<LatlngProyectos> latLngInsert = proySiges.getLatLngProyList();

            proyVisualizador.getLatlngProyectoImpList().add(latlngBean.crearLatlngProyecto(latLngInsert.iterator().next()));

            proyVisualizador.setDocsImpList(null);

        } else {

            //Latlng
            for (LatlngProyectos l : proySiges.getLatLngProyList()) {
                proyVisualizador.getLatlngProyectoImpList().add(latlngBean.crearLatlngProyecto(l));
            }

            //Documentos
            DocumentosDao dao = new DocumentosDao(em);
            List<Documentos> docsdocProy = dao.obtenerDocumentosPorFicha(proySiges.getProyPk(), TipoFichaEnum.PROYECTO.id, 0);
            List<Documentos> toImportDocs = new ArrayList<>();
            if (docsdocProy != null) {
                boolean docsModMayorDocsPub = false;
                for (Documentos docProy : docsdocProy) {
                    if (BooleanUtils.isTrue(docProy.getDocsPublicable())) {
                        docsModMayorDocsPub = false;
                        // la fecha de modificacion
                        Date toCompare = docProy.getDocsFecha();
                        if (toCompare == null) {
                            toCompare = docProy.getDocsPubFecha();
                        }
                        if (docProy.getDocsFecha() != null) {
                            docsModMayorDocsPub = docProy.getDocsPubFecha() == null || docProy.getDocsFecha().after(docProy.getDocsPubFecha());
                        }
                        if (todosLosMedia
                                || docsModMayorDocsPub
                                || toCompare == null
                                || ultimaExportacion == null
                                || toCompare.after(ultimaExportacion)) {
                            toImportDocs.add(docProy);
                        }
                    }
                }
                List<Documentos> listDocs = documentosBean.cargarArchivosDocumentos(toImportDocs);
                List<DocumentosImp> listDocsImp = documentosBean.crearDocsImpFromDocs(listDocs, proySigesOrgPk);

				if (listDocsImp != null) {

                    proyVisualizador.setDocsImpList(new ArrayList<DocumentosImp>());
                    proyVisualizador.getDocsImpList().addAll(listDocsImp);
                }

            }
        }
        return proyVisualizador;
    }

    /**
     * Pasar del proySiges al proyVisualizador
     *
     * @param proySiges
     * @return ProyectoImportado
     */
    public ProyectoImportado proyToProyImp(Proyectos proySiges, Boolean todosLosMedia, boolean throwExceptionOnError) {

        try {

            Integer proySigesOrgPk = proySiges.getProyOrgFk().getOrgPk();

            ProyectoImportado proyVisualizador = new ProyectoImportado();

            proyVisualizador.setProyImpNombre(proySiges.getProyNombre());
            proyVisualizador.setProyImpAvanceFecha(VisualizadorWSUtils.dateToXMLGregorianCalendar(new Date()));
            proyVisualizador.setProyImpCompartido(0L);
            proyVisualizador.setProyImpContacto("");
            proyVisualizador.setProyImpDescripcion(proySiges.getProyDescripcion());
            proyVisualizador.setProyImpDescripcionStrip(StringsUtils.stripHtml(proySiges.getProyDescripcion(), ""));
            proyVisualizador.setProyImpFechaPub(VisualizadorWSUtils.dateToXMLGregorianCalendar(proySiges.getProyFechaAct()));

            if (proySiges.getProyAreaFk() != null) {
                proyVisualizador.setProyImpAreaPk(proySiges.getProyAreaFk().getAreaPk());
                proyVisualizador.setProyImpAreaNombre(proySiges.getProyAreaFk().getAreaNombre());
                proyVisualizador.setProyImpareaAbreviacion(proySiges.getProyAreaFk().getAreaAbreviacion());
            }

//		int[] arrParcial = cronogramasBean.calcularAvanceCronoParcial(proySiges.getProyCroFk());
           // int[] arrParcial = proySiges.getProyIndices().getProyindAvanceParcial();

            //proyVisualizador.setProyImpAvancePorc(arrParcial[0]);

            Long proyFechaInicio = null;

            if (proySiges.getProyOtrosDatos() != null) {
                if (proySiges.getProyOtrosDatos().getProyOtrCatFk() != null) {
                    org.agesic.siges.visualizador.web.ws.CategoriaProyectos cp = VisualizadorWSUtils.convertCategoriaProyectos(proySiges.getProyOtrosDatos().getProyOtrCatFk());
                    proyVisualizador.setProyImpTemaFk(cp);

                    if (proySiges.getProyOtrosDatos().getProyOtrosCatSecundarias() != null) {
                        List<CategoriaProyectos> listCatSec = proySiges.getProyOtrosDatos().getProyOtrosCatSecundarias();
                        List<org.agesic.siges.visualizador.web.ws.CategoriaProyectos> listCatSecImp = VisualizadorWSUtils.convertCategoriaProyectos(listCatSec);
                        if (listCatSecImp != null) {
                            proyVisualizador.getProyCategsList().addAll(listCatSecImp);
                        }
                    }
                }

                if (proySiges.getProyOtrosDatos().getProyOtrInsEjeFk() != null) {
                    proyVisualizador.setProyImpEjecOrgPk(VisualizadorWSUtils.convertOrgaIntProve(proySiges.getProyOtrosDatos().getProyOtrInsEjeFk()));
                }

                if (proySiges.getProyUsrGerenteFk() != null) {
                    SsUsuario gerente = proySiges.getProyUsrGerenteFk();
                    proyVisualizador.setProyGerente(gerente.getUsuNombreApellido());
                    proyVisualizador.setProyGerenteEmail(gerente.getUsuCorreoElectronico());
                    proyVisualizador.setProyGerenteTel(gerente.getUsuTelefono());
                    Areas areaGerente = gerente.getUsuArea(proySigesOrgPk);
                    proyVisualizador.setProyDeptoAreaGerente(areaGerente != null ? areaGerente.getAreaNombre() : null);
                }

                if (proySiges.getProyOtrosDatos().getProyOtrEtaFk() != null) {
                    EstadoProyectos est = new EstadoProyectos();
                    est.setEstPk(proySiges.getProyOtrosDatos().getProyOtrEtaFk().getEtaPk());
                    est.setEstCodigo(proySiges.getProyOtrosDatos().getProyOtrEtaFk().getEtaCodigo());
                    proyVisualizador.setProyImpEstadoFk(est);
                }

                if (proySiges.getProyOtrosDatos().getProyOtrEntFk() != null) {
                    proyFechaInicio = proySiges.getProyOtrosDatos().getProyOtrEntFk().getEntInicio();
                }

                proyVisualizador.setProyImpObservaciones(proySiges.getProyOtrosDatos().getProyOtrObservaciones());
                proyVisualizador.setProyImpOrigenrecOrg(proySiges.getProyOtrosDatos().getProyOtrOrigen());
                proyVisualizador.setProyImpOrigenrecOrgPk(null);
                proyVisualizador.setProyImpProvOrgFk(VisualizadorWSUtils.convertOrgaIntProve(proySiges.getProyOtrosDatos().getProyOtrContFk()));
                proyVisualizador.setProyImpPlazoEstimado(proySiges.getProyOtrosDatos().getProyOtrPlazo());
            }

            if (proySiges.getProyOtrosDatos() != null && proySiges.getProyOtrosDatos().getProyOtrEntFk() != null) {
                proyFechaInicio = proySiges.getProyOtrosDatos().getProyOtrEntFk().getEntInicio();
                proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(proyFechaInicio));
                if (proySiges.getProyOtrosDatos().getProyOtrPlazo() != null && proySiges.getProyOtrosDatos().getProyOtrPlazo() > 0) {
                    GregorianCalendar calFin = new GregorianCalendar();
                    calFin.setTime(new Date(proyFechaInicio));
                    
                    /*
                    *   27-06-2018 Nico: Se agrega el "-1" para que no exporte con un día de más
                    */
                    
                    calFin.add(Calendar.DAY_OF_YEAR, proySiges.getProyOtrosDatos().getProyOtrPlazo()-1);
                    proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(calFin.getTime()));
                    proyVisualizador.setProyImpPlazoEstimado(proySiges.getProyOtrosDatos().getProyOtrPlazo());
                } else {
                    proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(new Date(proySiges.getProyOtrosDatos().getProyOtrEntFk().getEntFin())));
                    proyVisualizador.setProyImpPlazoEstimado(DatesUtils.diasEntreFechas(proyVisualizador.getProyImpFechaInicio().toGregorianCalendar().getTime(), proyVisualizador.getProyImpFechaFin().toGregorianCalendar().getTime()));
                }
                List<Entregables> entHijos = EntregablesUtils.obtenerHijos(new ArrayList<>(proySiges.getProyCroFk().getEntregablesSet()), proySiges.getProyOtrosDatos().getProyOtrEntFk(), Boolean.TRUE);
                Set<Entregables> setEntAvance = new HashSet<>(entHijos);
                int[] arrParcial = cronogramasBean.calcularAvanceCronoParcial(setEntAvance);
                proyVisualizador.setProyImpAvancePorc(arrParcial[0]);

            } else if (proySiges.getProyCroFk() != null && proySiges.getProyCroFk().getEntregablesSet() != null) {

                int[] arrParcial = cronogramasBean.calcularAvanceCronoParcial(proySiges.getProyCroFk());
                proyVisualizador.setProyImpAvancePorc(arrParcial[0]);

                /**
                 * Ticket #897850 - 2018-02-17 Bruno: Modificado para tomar la misma
                 * fecha de inicio y fin que aparece en la pantalla de inicio.
                 */
                proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(proySiges.getProyIndices().getProyindPeriodoInicio()));
                proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(proySiges.getProyIndices().getProyindPeriodoFin()));                                
                
                Configuracion cnfPeriodoCnf = configuracionBean.obtenerCnfPorCodigoYOrg("PROYECTO_GANTT_PERIODO", proySiges.getProyOrgFk().getOrgPk());
                Boolean cnfPeriodo = cnfPeriodoCnf != null && cnfPeriodoCnf.getCnfValor() != null && "true".equalsIgnoreCase(cnfPeriodoCnf.getCnfValor());

                /**
                 * 02-06-17: Se agrega para que tome en cuenta si la
                 * configuración de seleccionar el período en el gantt está
                 * activa.
                 */
                Date entIni = entregablesBean.obtenerPrimeraFecha(proySiges.getProyCroFk().getEntregablesSet(), cnfPeriodo);
                Date entFin = entregablesBean.obtenerUltimaFecha(proySiges.getProyCroFk().getEntregablesSet(), cnfPeriodo);
                proyVisualizador.setProyImpFechaInicio(VisualizadorWSUtils.dateToXMLGregorianCalendar(entIni.getTime()));
                proyVisualizador.setProyImpFechaFin(VisualizadorWSUtils.dateToXMLGregorianCalendar(entFin.getTime()));
                proyVisualizador.setProyImpPlazoEstimado(DatesUtils.diasEntreFechas(proyVisualizador.getProyImpFechaInicio().toGregorianCalendar().getTime(), proyVisualizador.getProyImpFechaFin().toGregorianCalendar().getTime()));
            }

            if (proySiges.getProyPreFk() != null) {

                proyVisualizador.setProyImpInvMonedaMonFk(VisualizadorWSUtils.convertMoneda(proySiges.getProyPreFk().getPreMoneda()));
                proyVisualizador.setProyImpFinanciamientoFueFk(VisualizadorWSUtils.convertFuenteFinanciamiento(proySiges.getProyPreFk().getFuenteFinanciamiento()));
                proyVisualizador.setProyImpInvTotal(proySiges.getProyPreFk().getPreBase());
            }

            proyVisualizador.setProyImpModificado(null);
            proyVisualizador.setProyImpObjPublico(proySiges.getProyObjPublico());
            proyVisualizador.setProyImpObjetivo(proySiges.getProyObjetivo());
            proyVisualizador.setProyImpOrigenPk(proySiges.getProyPk());
            proyVisualizador.setProyImpOrgFk(VisualizadorWSUtils.convertOrganismos(organismoBean.obtenerOrgPorId(proySiges.getProyOrgFk().getOrgPk(), true)));
            proyVisualizador.setProyImpUsrMod(null);
            proyVisualizador.setProyImpUsrPub(null);
            proyVisualizador.setProyImpSitActual(proySiges.getProySituacionActual());
            Date fechaSitAct = sitActHistoricoBean.obtenerUltimaFechaSitAct(proySiges.getProyPk());
            proyVisualizador.setProyImpSitActualDate(VisualizadorWSUtils.dateToXMLGregorianCalendar(fechaSitAct));

            //Entregables
            if (proySiges.getProyCroFk() != null && proySiges.getProyCroFk().getEntregablesSet() != null) {
                List<Entregables> entList = EntregablesUtils.entregablesSinPadres(new ArrayList<>(proySiges.getProyCroFk().getEntregablesSet()));
                List<EntregablesImp> impEntregables = VisualizadorWSUtils.convertEntregables(entList);
                if (impEntregables != null) {
                    proyVisualizador.getEntregablesProyectoImpList().addAll(impEntregables);
                }
            }

            //Media
            Date ultimaExportacion = proyPublicaHistBean.obtenerUltimaFecha(proySiges.getProyPk());
            //solo los media que fueron modificados se vuelven a importar
            Set<MediaProyectos> mediaProyList = proySiges.getProyMediaProyectos();
            List<MediaProyectos> toImportMedia = new ArrayList<>();
            boolean mediaModMayorMediaPub = false;
            if (mediaProyList != null) {
                for (MediaProyectos mediaProy : mediaProyList) {
                    if (mediaProy.esMediaPublicable()) {
                        mediaModMayorMediaPub = false;
                        // la fecha de modificacion
                        Date toCompare = mediaProy.getMediaModFecha();
                        if (toCompare == null) {
                            // la fecha de creacion
                            toCompare = mediaProy.getMediaPubFecha();
                        }

                        /**
                         * Bruno 14-08-17: puede pasar que la imágenes no se
                         * exporte porque el tamaño del archivo supera al
                         * configurado al momento de exportar. Luego se cambia
                         * por configuración el tamaño del archivo que se puede
                         * exportar y esta imágenes que quedaron sin exportar,
                         * tienen que exportarse. En este caso, estas imágenes
                         * quedan con fecha de creación anterior a la de la
                         * última publicación, pero nunca fueron exportadas. Por
                         * eso se agrega esta condición.
                         *
                         */
                        if (mediaProy.getMediaModFecha() != null) {
                            mediaModMayorMediaPub = mediaProy.getMediaPubFecha() == null && mediaProy.getMediaModFecha().after(mediaProy.getMediaPubFecha());
                        }

                        /**
                         * Bruno 25-07-17: se cambia par que compare los
                         * timestamp de la fecha de modificación y de
                         * publicación. Antes comparaba en el orden de días y
                         * por eso quedaban imágenes sin exportar.
                         */
                        if (todosLosMedia
                                || mediaModMayorMediaPub
                                || toCompare == null
                                || ultimaExportacion == null
                                || toCompare.after(ultimaExportacion)) {
                            toImportMedia.add(mediaProy);
                        }
                    }
                }
            }

            List<MediaProyectos> listMP = mediaProyectosBean.cargarArchivos(toImportMedia, proySigesOrgPk);
            List<MediaImpProyectos> listImpMP = mediaProyectosBean.crearMediaProyFromImp(listMP);

            /**
             * TODO (bruno) exportación de archivos 30-11-2017
             */
            documentosBean.cargarArchivosDocumentos(new ArrayList<Documentos>(proySiges.getDocumentosSet()));

            if (listImpMP != null) {

                proyVisualizador.getMediaImpProyectosList().addAll(listImpMP);
            }

            //Latlng
            for (LatlngProyectos l : proySiges.getLatLngProyList()) {
                proyVisualizador.getLatlngProyectoImpList().add(latlngBean.crearLatlngProyecto(l));
            }

            return proyVisualizador;

        } catch (Exception ex) {
            if (throwExceptionOnError) {
                throw ex;
            }
        }
        return null;
    }

}
