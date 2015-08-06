package com.sofis.business.ejbs;

import com.sofis.business.utils.ProgProyUtils;
import static com.sofis.business.utils.ProgProyUtils.cambioEstado;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.data.daos.ProgramasProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.FiltroInicioItem;
import com.sofis.entities.tipos.FiltroInicioResultadoTO;
import com.sofis.entities.tipos.FiltroInicioTO;
import com.sofis.entities.utils.FichaUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProgramasProyectosBean")
@LocalBean
public class ProgramasProyectosBean {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    @EJB
    private ProyectosBean proyectosBean;
    @EJB
    private ProgramasBean programasBean;
    @EJB
    private RiesgosBean riesgosBean;
    @EJB
    private CalidadBean calidadBean;
    @EJB
    private DocumentosBean documentosBean;
    @EJB
    private CronogramasBean cronogramasBean;
    @EJB
    private PresupuestoBean presupuestoBean;
    @EJB
    private EstadosBean estadosBean;
    @EJB
    private MonedaBean monedaBean;

    public List<FichaTO> obtenerProyProgPendientes(SsUsuario usuario, Integer orgPk) throws GeneralException {
        ProgramasProyectosDAO proyProgDao = new ProgramasProyectosDAO(em);
        try {
            List<FichaTO> resultado = proyProgDao.obtenerProyProgPendientes(usuario, orgPk);
            if (resultado == null) {
                return new ArrayList<>();
            } else {
                for (FichaTO fichaTO : resultado) {
                    cargarTipoSolicitud(fichaTO);
                }
            }
            return resultado;

        } catch (DAOGeneralException ex) {
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
        }
    }

    private void cargarTipoSolicitud(FichaTO fichaTO) {
        if (fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id)
                || fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id)) {
            fichaTO.setTipoSolicitud("ficha_tipo_sol_finalizar");
        } else if (fichaTO.isEstadoPendiente(Estados.ESTADOS.SOLICITUD_CANCELAR_PMOT.estado_id)) {
            fichaTO.setTipoSolicitud("ficha_tipo_sol_cierre");
        } else if (fichaTO.isEstadoPendiente(Estados.ESTADOS.PLANIFICACION.estado_id)) {
            fichaTO.setTipoSolicitud("ficha_tipo_sol_planificacion");
        } else if (fichaTO.isEstadoPendiente(Estados.ESTADOS.EJECUCION.estado_id)) {
            fichaTO.setTipoSolicitud("ficha_tipo_sol_ejecucion");
        } else if (fichaTO.isEstado(Estados.ESTADOS.PENDIENTE_PMOF.estado_id)
                || fichaTO.isEstado(Estados.ESTADOS.PENDIENTE_PMOT.estado_id)) {
            fichaTO.setTipoSolicitud("ficha_tipo_sol_inicio");
        }
    }

    /**
     * Carga en FiltroInicioItem los indicadores que están persistidos.
     *
     * @param item
     * @param orgPk
     * @param ficha
     * @return FiltroInicioItem
     */
    public FiltroInicioItem cargarIndicadoresMaterializados(FiltroInicioItem item, Integer orgPk, Object ficha, Map<String, String> config) {

        if (FichaUtils.isPrograma(item)) {

            ProgIndices progIndices = ficha != null ? ((Programas) ficha).getProgIndices() : null;
            if (progIndices == null) {
                if (item.getIndicesProg() != null) {
                    progIndices = item.getIndicesProg();
                } else {
                    progIndices = programasBean.obtenerIndicadores(item.getFichaFk(), orgPk);
                }
            }

            if (progIndices != null) {
                //Metodologia
                if (progIndices.getProgindMetodologiaEstado() != null) {
                    Double metodologiaIndiceEstado = progIndices.getProgindMetodologiaEstado();
                    item.setMetodologiaIndice(metodologiaIndiceEstado);
                    String metodologiaIndiceColor = documentosBean.calcularIndiceEstadoMetodologiaColor(metodologiaIndiceEstado, orgPk, config);
                    item.setMetodologiaIndiceColor(metodologiaIndiceColor);
                } else {
                    item.setMetodologiaIndiceColor(ConstantesEstandares.COLOR_TRANSPARENT);
                }

                if (progIndices.getProgindMetodologiaSinAprobar() != null) {
                    item.setMetodologiaSinAprobar(progIndices.getProgindMetodologiaSinAprobar());
                }

                //Periodo
                if (progIndices.getProgindPeriodoInicio() != null) {
                    item.setPeriodoDesde(progIndices.getProgindPeriodoInicio());
                }
                if (progIndices.getProgindPeriodoFin() != null) {
                    item.setPeriodoHasta(progIndices.getProgindPeriodoFin());
                }

                //Riesgos
                if (progIndices.getProgindRiesgoExpo() != null) {
                    item.setRiesgoExposicion(progIndices.getProgindRiesgoExpo());
                    String riesgoColor = riesgosBean.obtenerExposicionRiesgoColor(progIndices.getProgindRiesgoExpo(), orgPk, config);
                    item.setRiesgoExposicionColor(riesgoColor);
                } else {
                    item.setRiesgoExposicionColor(ConstantesEstandares.COLOR_TRANSPARENT);
                }

                if (progIndices.getProgindRiesgoAlto() != null) {
                    item.setRiesgosAltosCantidad(progIndices.getProgindRiesgoAlto());
                }

                //Calidad
                if (progIndices.getProgindCalInd() != null) {
                    item.setCalidadIndice(progIndices.getProgindCalInd());
                    String calIndColor = calidadBean.calcularIndiceCalidadColor(progIndices.getProgindCalInd(), orgPk);
                    item.setCalidadIndiceColor(calIndColor);
                }
                if (progIndices.getProgindCalPend() != null) {
                    item.setCalidadPendientes(progIndices.getProgindCalPend());
                }
            }

        } else if (FichaUtils.isProyecto(item)) {

            ProyIndices proyIndices = ficha != null ? ((Proyectos) ficha).getProyIndices() : null;
            if (proyIndices == null) {
                if (item.getIndicesProg() != null) {
                    proyIndices = item.getIndicesProy();
                } else {
                    proyIndices = proyectosBean.obtenerIndicadores(item.getFichaFk(), orgPk);
                }
            }

            if (proyIndices != null) {
                //Metodologia
                if (proyIndices.getProyindMetodologiaEstado() != null) {
                    Double metodologiaIndiceEstado = proyIndices.getProyindMetodologiaEstado();
                    item.setMetodologiaIndice(metodologiaIndiceEstado);
                    String metodologiaIndiceColor = documentosBean.calcularIndiceEstadoMetodologiaColor(metodologiaIndiceEstado, orgPk, config);
                    item.setMetodologiaIndiceColor(metodologiaIndiceColor);
                } else {
                    item.setMetodologiaIndiceColor(ConstantesEstandares.COLOR_TRANSPARENT);
                }
                if (proyIndices.getProyindMetodologiaSinAprobar()) {
                    item.setMetodologiaSinAprobar(proyIndices.getProyindMetodologiaSinAprobar());
                }

                //Periodo
                if (proyIndices.getProyindPeriodoInicio() != null) {
                    item.setPeriodoDesde(proyIndices.getProyindPeriodoInicio());
                }
                if (proyIndices.getProyindPeriodoFin() != null) {
                    item.setPeriodoHasta(proyIndices.getProyindPeriodoFin());
                }

                //Riesgos
                if (proyIndices.getProyindRiesgoExpo() != null) {
                    item.setRiesgoExposicion(proyIndices.getProyindRiesgoExpo());
                    String riesgoColor = riesgosBean.obtenerExposicionRiesgoColor(proyIndices.getProyindRiesgoExpo(), orgPk, config);
                    item.setRiesgoExposicionColor(riesgoColor);
                } else {
                    item.setRiesgoExposicionColor(ConstantesEstandares.COLOR_TRANSPARENT);
                }
                if (proyIndices.getProyindRiesgoUltact() != null) {
                    String riesgoUltimaActColor = riesgosBean.obtenerColorUltimaActualizacion(proyIndices.getProyindRiesgoUltact(), orgPk, config);
                    item.setRiesgosActualizacionColor(riesgoUltimaActColor);
                }
                if (proyIndices.getProyindRiesgoAlto() != null) {
                    item.setRiesgosAltosCantidad(proyIndices.getProyindRiesgoAlto());
                }

                //Calidad
                if (proyIndices.getProyindCalInd() != null) {
                    item.setCalidadIndice(proyIndices.getProyindCalInd());
                    String calIndColor = calidadBean.calcularIndiceCalidadColor(proyIndices.getProyindCalInd(), orgPk);
                    item.setCalidadIndiceColor(calIndColor);
                }
                if (proyIndices.getProyindCalPend() != null) {
                    item.setCalidadPendientes(proyIndices.getProyindCalPend());
                }
            }
        }

        return item;
    }

    /**
     * Calcula los indicadores para un item
     *
     * @param item
     * @param orgPk
     * @param config
     * @return FiltroInicioItem
     */
    public FiltroInicioItem calcularIndicadores(FiltroInicioItem item, Integer orgPk, Map<String, String> config) {
        if (FichaUtils.isPrograma(item)) {
            Set<ProyectosConCronograma> setProyectos = proyectosBean.obtenerProyectosConCronogramaPorProgId(item.getFichaFk());
            
            //Avance
            int[] iaf = cronogramasBean.calcularAvanceCronoFinalizadoProgConCronograma(setProyectos);
            item.setIndiceAvanceFinalizado(iaf);
            int[] iap = cronogramasBean.calcularAvanceCronoParcialProgConCronograma(setProyectos);
            item.setIndiceAvanceParcial(iap);
            
            //Presupuesto
            item.setIndiceMonedas(monedaBean.obtenerMonedas());

            String sConflimiteAmarillo = config.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO);
            String sConflimiteRojo = config.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO);
            Integer limiteAmarillo = null;
            Integer limiteRojo = null;
            try {
                limiteAmarillo = Integer.valueOf(sConflimiteAmarillo);
                limiteRojo = Integer.valueOf(sConflimiteRojo);
            } catch (Exception ex) {
                limiteAmarillo = null;
                limiteRojo = null;
            }

            HashMap<Integer, String> indiceMonedaColor = presupuestoBean.obtenerColorAC(setProyectos, item.getIndiceMonedas(), orgPk, limiteAmarillo, limiteRojo);
            item.setIndiceMonedaColor(indiceMonedaColor);

        } else if (FichaUtils.isProyecto(item)) {
            //Fase
            Integer estIniAmarillo = Integer.valueOf(config.get(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_AMARILLO));
            Integer estIniRojo = Integer.valueOf(config.get(ConfiguracionCodigos.ESTADO_INICIO_LIMITE_ROJO));
            Integer estPlanAmarillo = Integer.valueOf(config.get(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_AMARILLO));
            Integer estPlanRojo = Integer.valueOf(config.get(ConfiguracionCodigos.ESTADO_PLANIFICACION_LIMITE_ROJO));
            item.setFaseColor(estadosBean.obtenerEstadoColor(item.getEstado(), item.getPeriodoDesde(), item.getPeriodoHasta(), estIniAmarillo, estIniRojo, estPlanAmarillo, estPlanRojo, orgPk));
            
            //Avance
            Cronogramas cro = cronogramasBean.obtenerCronogramaPorProy(item.getFichaFk());
            int[] iaf = cronogramasBean.calcularAvanceCronoFinalizado(cro);
            item.setIndiceAvanceFinalizado(iaf);
            int[] iap = cronogramasBean.calcularAvanceCronoParcial(cro);
            item.setIndiceAvanceParcial(iap);
            
            //Presupuesto
            Integer prePk = item.getPresupuesto() != null ? item.getPresupuesto().getPrePk() : null;
            item.setIndiceMonedas(monedaBean.obtenerMonedas());
            String sConflimiteAmarillo = config.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_AMARILLO);
            String sConflimiteRojo = config.get(ConfiguracionCodigos.COSTO_ACTUAL_LIMITE_ROJO);
            Integer limiteAmarillo = null;
            Integer limiteRojo = null;
            try {
                limiteAmarillo = Integer.valueOf(sConflimiteAmarillo);
                limiteRojo = Integer.valueOf(sConflimiteRojo);
            } catch (Exception ex) {
                limiteAmarillo = null;
                limiteRojo = null;
            }

            HashMap<Integer, String> indiceMonedaColor = presupuestoBean.obtenerColorAC(prePk, item.getIndiceMonedas(), orgPk, limiteAmarillo, limiteRojo);
            item.setIndiceMonedaColor(indiceMonedaColor);
        }
        return item;
    }

    /**
     * Crea el Item a partir del programa o proyecto
     *
     * @param o
     * @return FiltroInicioItem
     */
    public FiltroInicioItem crearFiltroInicioItem(Object o) {
        FiltroInicioItem item = null;
        if (o instanceof Programas) {
            item = new FiltroInicioItem();
            Programas p = (Programas) o;

            item.setIndicesProg(p.getProgIndices());
            //Indicadores
            if (p.getProgIndices() != null) {
                ProgIndices progInd = p.getProgIndices();
                item.setMetodologiaIndice(progInd.getProgindMetodologiaEstado());
                item.setMetodologiaSinAprobar(progInd.getProgindMetodologiaSinAprobar());
                item.setPeriodoDesde(progInd.getProgindPeriodoInicio());
                item.setPeriodoHasta(progInd.getProgindPeriodoFin());
                item.setRiesgoExposicion(progInd.getProgindRiesgoExpo());
                item.setRiesgosAltosCantidad(progInd.getProgindRiesgoAlto());
                item.setActualizacion(progInd.getProyActualizacion());
            }

            //el area
            item.setArea(p.getProgAreaFk().getAreaNombre());
            item.setAreaAbreviacion(p.getProgAreaFk().getAreaAbreviacion());
            item.setAreaId(p.getProgAreaFk().getAreaPk());
            //los datos de la ficha
            item.setFichaFk(p.getProgPk());
            item.setActivo(p.getActivo());

            item.setTipoFicha(TipoFichaEnum.PROGRAMA.id);
            item.setNombre(p.getProgNombre());
            item.setEstado(p.getProgEstFk());
            item.setEstadoPendiente(p.getProgEstPendienteFk());

            item.setActualizacionColor(proyectosBean.obtenerUltimaActualizacionColor(item.getEstado(), item.getActualizacion(), p.getProgSemaforoAmarillo(), p.getProgSemaforoRojo()));
            item.setSemaforoAmarillo(p.getProgSemaforoAmarillo());
            item.setSemaforoRojo(p.getProgSemaforoRojo());
            item.setFechaAct(p.getProgFechaAct());
            item.setResponsable(p.getProgUsrGerenteFk().getUsuNombreApellido());
            item.setResponsableId(p.getProgUsrGerenteFk().getUsuId());
            item.setAdjunto(p.getProgUsrAdjuntoFk().getUsuNombreApellido());
            item.setAdjuntoId(p.getProgUsrAdjuntoFk() != null ? p.getProgUsrAdjuntoFk().getUsuId() : null);
            item.setSponsorId(p.getProgUsrSponsorFk() != null ? p.getProgUsrSponsorFk().getUsuId() : null);
            item.setPmofId(p.getProgUsrPmofedFk() != null ? p.getProgUsrPmofedFk().getUsuId() : null);
            item.setSolCambioFase(proyectosBean.cambioEstadoPorProg(p.getProgPk()));

            //item.setPeso(pesoTotalPrograma(p));
            ProgramasDAO progDao = new ProgramasDAO(em);
            item.setPeso(progDao.calcularPesoPrograma(p.getProgPk()));

        } else if (o instanceof Proyectos) {
            item = new FiltroInicioItem();
            Proyectos p = (Proyectos) o;

            //el area
            item.setArea(p.getProyAreaFk().getAreaNombre());
            item.setAreaAbreviacion(p.getProyAreaFk().getAreaAbreviacion());
            item.setAreaId(p.getProyAreaFk().getAreaPk());

            //los datos de la ficha
            item.setFichaFk(p.getProyPk());
            item.setTipoFicha(TipoFichaEnum.PROYECTO.id);
            item.setNombre(p.getProyNombre());
            item.setEstado(p.getProyEstFk());
            item.setEstadoPendiente(p.getProyEstPendienteFk());
            item.setActualizacion(p.getProyFechaAct());
            item.setActualizacionColor(proyectosBean.obtenerUltimaActualizacionColor(p.getProyEstFk(), p.getProyFechaAct(), p.getProySemaforoAmarillo(), p.getProySemaforoRojo()));
            item.setSemaforoAmarillo(p.getProySemaforoAmarillo());
            item.setSemaforoRojo(p.getProySemaforoRojo());
            item.setFechaAct(p.getProyFechaAct());
            item.setFechaEstadoAct(p.getProyFechaEstadoAct());
            item.setSolCambioFase(p.isEstPendienteFk());
            item.setPeso(p.getProyPeso());
            item.setActivo(p.getActivo());

            //el responsable PM del proyecto
            item.setResponsable(p.getProyUsrGerenteFk().getUsuNombreApellido());
            item.setResponsableId(p.getProyUsrGerenteFk().getUsuId());
            item.setAdjunto(p.getProyUsrAdjuntoFk().getUsuNombreApellido());
            item.setAdjuntoId(p.getProyUsrAdjuntoFk() != null ? p.getProyUsrAdjuntoFk().getUsuId() : null);
            item.setSponsorId(p.getProyUsrSponsorFk() != null ? p.getProyUsrSponsorFk().getUsuId() : null);
            item.setPmofId(p.getProyUsrPmofedFk() != null ? p.getProyUsrPmofedFk().getUsuId() : null);

            item.setIndicesProy(p.getProyIndices());
            //Indicadores
            if (p.getProyIndices() != null) {
                ProyIndices proyInd = p.getProyIndices();
                item.setMetodologiaIndice(proyInd.getProyindMetodologiaEstado());
                item.setMetodologiaSinAprobar(proyInd.getProyindMetodologiaSinAprobar());
                item.setPeriodoDesde(proyInd.getProyindPeriodoInicio());
                item.setPeriodoHasta(proyInd.getProyindPeriodoFin());
                item.setRiesgoExposicion(proyInd.getProyindRiesgoExpo());
                item.setRiesgosAltosCantidad(proyInd.getProyindRiesgoAlto());
            }
            if (p.getProyPreFk() != null) {
                item.setPresupuesto(p.getProyPreFk());
            }

            item.setPrograma(p.getProyProgFk());
        }
        return item;
    }

    /**
     * Obtiene el primer nivel de la busqueda(Programas y Proyectos huerfanos).
     *
     * @param organismoId
     * @param area
     * @param usuario
     * @param confAmarillo
     * @param confRojo
     * @param filtro
     * @return FiltroInicioResultadoTO
     * @throws GeneralException
     */
    public FiltroInicioResultadoTO obtenerPrimerNivel(Integer organismoId, Areas area, SsUsuario usuario, FiltroInicioTO filtro, Configuracion confAmarillo, Configuracion confRojo) throws GeneralException {
        Integer areaId = area != null ? area.getAreaPk() : null;
        FiltroInicioResultadoTO resultado = new FiltroInicioResultadoTO();
        resultado.setArea(area);
        resultado.setPrimerNivel(new ArrayList());

        if (CollectionsUtils.isNotEmpty(filtro.getGradoRiesgo())) {

            if (filtro.getConfiguracion() == null) {
                filtro.setConfiguracion(new HashMap<String, Configuracion>());
            }
            filtro.getConfiguracion().put(confAmarillo.getCnfCodigo(), confAmarillo);
            filtro.getConfiguracion().put(confRojo.getCnfCodigo(), confRojo);
        }

        ProgramasProyectosDAO dao = new ProgramasProyectosDAO(em);
        try {
            //Busca los proyectos que coincidan con el filtro, retornando los programas que los contienen.
            List<Programas> programas = dao.buscarProgPorFiltro(organismoId, areaId, usuario, filtro, true);
            cargarProgramasToFiltroResultrado(resultado, programas);

            //Busca los proyectos dentro de programas y carga los programas.
//            List<Proyectos> proyectos = dao.buscarProyPorFiltro(organismoId, areaId, usuario, filtro);
//            cargarProgramasFromProyToFiltroResultrado(resultado, proyectos);
            //Busca los proyectos que no dependen de un programa.
            List<Proyectos> proyectosHuerf = dao.buscarProyHuerfanosPorFiltro(organismoId, areaId, usuario, filtro);
            cargarProyHuerfanosToFiltroResultrado(resultado, proyectosHuerf);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return resultado;
    }

    /**
     * Obtiene el segundo nivel de la busqueda(Proyectos dentro de un Programa
     * dado).
     *
     * @param organismoId
     * @param areaId
     * @param usuario
     * @param filtro
     * @return FiltroInicioResultadoTO
     * @throws GeneralException
     */
    public FiltroInicioResultadoTO obtenerSegundoNivel(Integer organismoId, Integer areaId, SsUsuario usuario, FiltroInicioTO filtro) throws GeneralException {
        FiltroInicioResultadoTO resultado = new FiltroInicioResultadoTO();
        resultado.setPrimerNivel(new ArrayList());

        ProgramasProyectosDAO dao = new ProgramasProyectosDAO(em);

        try {
            cargarProyHuerfanosToFiltroResultrado(resultado, dao.buscarProyPorFiltroYProg(organismoId, areaId, usuario, filtro));
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return resultado;
    }

    /**
     * Dado una lista de Proyectos carga en el resultado del filtro los
     * Programas a los que pertenecen.
     *
     * @param resultado
     * @param proyectos
     */
    private void cargarProgramasFromProyToFiltroResultrado(FiltroInicioResultadoTO resultado, List<Proyectos> proyectos) {
        if (resultado != null && proyectos != null && !proyectos.isEmpty()) {
            Map<Integer, Programas> mapProg = new HashMap();
            for (Proyectos proy : proyectos) {
                if (proy.getProyProgFk() != null && !mapProg.containsKey(proy.getProyProgFk().getProgPk())) {
                    mapProg.put(proy.getProyProgFk().getProgPk(), proy.getProyProgFk());
                }
            }

            List<Programas> listProgramas = new ArrayList<>(mapProg.values());
            cargarProgramasToFiltroResultrado(resultado, listProgramas);
        }
    }

    /**
     * Dado una lista de Programas los carga en el resultado del filtro.
     *
     * @param resultado
     * @param programas
     */
    private void cargarProgramasToFiltroResultrado(FiltroInicioResultadoTO resultado, List<Programas> programas) {
        if (resultado != null && programas != null && !programas.isEmpty()) {
            FiltroInicioItem item;
            ArrayList<Map.Entry<FiltroInicioItem, List>> detailData;
            boolean agregar;
            for (Programas prog : programas) {
                item = crearFiltroInicioItem(prog);
                item.setInicioResultado(resultado);
                agregar = true;
                for (Map.Entry<FiltroInicioItem, List> entry : resultado.getPrimerNivel()) {
                    if (entry.getKey().equals(item)) {
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    detailData = new ArrayList<Map.Entry<FiltroInicioItem, List>>();
                    detailData.add(new SimpleEntry(new FiltroInicioItem(), null));
                    resultado.getPrimerNivel().add(new SimpleEntry(item, detailData));
                }
            }
        }
    }

    /**
     * Dado una lista de Proyectos huerfanos los carga en el resultado del
     * filtro.
     *
     * @param resultado
     * @param proyectos
     */
    private void cargarProyHuerfanosToFiltroResultrado(FiltroInicioResultadoTO resultado, List<Proyectos> proyectos) {
        if (resultado != null && proyectos != null && !proyectos.isEmpty()) {
            for (Proyectos proy : proyectos) {
                FiltroInicioItem item = this.crearFiltroInicioItem(proy);
                item.setInicioResultado(resultado);
                ArrayList<Map.Entry<FiltroInicioItem, List>> detailData = new ArrayList<Map.Entry<FiltroInicioItem, List>>();
                resultado.getPrimerNivel().add(new SimpleEntry(item, detailData));
            }
        }
    }

    public void solAprobacionCambioEstado(Object progProy, SsUsuario usuario, Integer orgPk) throws BusinessException {

        boolean isPM = ProgProyUtils.isUsuarioGerenteOAdjunto(progProy, usuario);
        boolean isPMOT = ProgProyUtils.isUsuarioPMOT(usuario, orgPk);
        boolean isPMOF = ProgProyUtils.isUsuarioPMOF(progProy, usuario, orgPk);

        if (progProy instanceof Programas) {
            Programas prog = (Programas) progProy;
            boolean hasEstado = prog.getProgEstFk() != null;
            boolean isAlta = !hasEstado;

            if (isAlta) {
                cambioEstado(prog, new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));

            } else if (prog.isEstado(Estados.ESTADOS.PENDIENTE_PMOT.estado_id) && isPMOT) {
                cambioEstado(prog, new Estados(Estados.ESTADOS.INICIO.estado_id));

            } else if (prog.isEstado(Estados.ESTADOS.EJECUCION.estado_id)) {
                if (isPMOT) {
                    boolean modificar = true;
                    Set<Proyectos> setProyectos = proyectosBean.obtenerProyPorProgId(prog.getProgPk());
                    if (setProyectos != null && !setProyectos.isEmpty()) {
                        for (Proyectos proy : setProyectos) {
                            if (!(proy.getProyEstFk().getEstPk().equals(Estados.ESTADOS.FINALIZADO.estado_id))) {
                                modificar = false;
                                break;
                            }
                        }
                    }
                    if (modificar) {
                        cambioEstado(prog, new Estados(Estados.ESTADOS.FINALIZADO.estado_id));
                    } else {
                        throw new BusinessException(MensajesNegocio.ERROR_MODIFICAR_ESTADO);
                    }
                }
            }

        } else if (progProy instanceof Proyectos) {
            Proyectos proy = (Proyectos) progProy;
            boolean hasEstado = proy.getProyEstFk() != null;
            boolean isAlta = !hasEstado;

            boolean isPendientePMOT = !isAlta
                    && proy.isEstado(Estados.ESTADOS.PENDIENTE_PMOT.estado_id);
            boolean isPendientePMOF = !isAlta
                    && proy.isEstado(Estados.ESTADOS.PENDIENTE_PMOF.estado_id);
            boolean isInicio = hasEstado
                    && proy.isEstado(Estados.ESTADOS.INICIO.estado_id);
            boolean isPlanificacion = hasEstado
                    && proy.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
            boolean isEjecucion = hasEstado
                    && proy.isEstado(Estados.ESTADOS.EJECUCION.estado_id);

            boolean isEstPendiente = proy.isEstPendienteFk();

            if (isAlta) {
                if (proy.getProyUsrPmofedFk().equals(usuario)) {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.INICIO.estado_id));
                } else {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
                    proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
                }

            } else if (isPendientePMOT) {
                if (proy.getProyUsrPmofedFk().equals(usuario)) {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.INICIO.estado_id));
                } else {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.PENDIENTE_PMOF.estado_id));
                    proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOF.estado_id));
                }

            } else if (isPendientePMOF) {
                cambioEstado(proy, new Estados(Estados.ESTADOS.INICIO.estado_id));

            } else if (isInicio) {
                if ((isEstPendiente && isPMOT) || isPMOT) {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
                } else {
                    proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.PLANIFICACION.estado_id));
                }
            } else if (isPlanificacion) {
                if ((isEstPendiente && isPMOT) || isPMOT) {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.EJECUCION.estado_id));
                } else {
                    proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.EJECUCION.estado_id));
                }

            } else if (isEjecucion) {
                if (isPM && !isPMOT) {
                    proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id));
                } else if (isPMOF && !isPMOT) {
                    proy.setProyEstPendienteFk(new Estados(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id));
                } else if (isPMOT) {
                    cambioEstado(proy, new Estados(Estados.ESTADOS.FINALIZADO.estado_id));
                }
            } else if (proy.getProyEstPendienteFk().isEstado(Estados.ESTADOS.SOLICITUD_CANCELAR_PMOT.estado_id)
                    && isPMOT) {
                proy.setProyEstPendienteFk(null);
                proy.setActivo(false);
            }
        }
    }
}
