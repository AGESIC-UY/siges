package com.sofis.business.ejbs;

import com.sofis.business.ejbs.wekan.TarjetaBean;
import com.sofis.business.ejbs.wekan.VinculacionBean;
import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.business.interceptors.annotations.WekanCronograma;
import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.utils.CronogramaUtils;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.ProyectosUtils;
import com.sofis.business.validations.CronogramaValidaciones;
import com.sofis.data.daos.CronogramasDAO;
import com.sofis.data.daos.EntregablesDAO;
import com.sofis.data.daos.OrganismoDAO;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.data.daos.wekan.TarjetaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.ProyectosConCronograma;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.Tarjeta;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.CronogramaTO;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.UsuarioTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.entities.tipos.ActivityInfoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.wekanapiclient.model.Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Named
@Stateless(name = "CronogramasBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class CronogramasBean {

    @Inject
    private ProgramasBean programasBean;

    @Inject
    private ProyectosBean proyectosBean;

    @Inject
    private ProductosBean productosBean;

    @Inject
    private EntregablesBean entregablesBean;

    @Inject
    private DocumentosBean documentosBean;

    @Inject
    private ParticipantesBean participantesBean;

    @Inject
    private RegistrosHorasBean registrosHorasBean;

    @Inject
    private DatosUsuario datosUsuario;

    @Inject
    private SsUsuarioBean usuarioBean;

    @Inject
    private VinculacionBean vinculacionBean;

    @Inject
    private ConfiguracionBean configuracionBean;

    @Inject
    private ProyectosBean proyectoBean;

    @Inject
    private TarjetaBean tarjetaBean;

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(CronogramasBean.class.getName());

    public Cronogramas guardar(Cronogramas cro) {
        LOGGER.fine("CronogramasBean.guardar");

        CronogramaValidaciones.validar(cro);

        CronogramasDAO cdao = new CronogramasDAO(em);
        ProyectosDAO proyectoDAO = new ProyectosDAO(em);

        try {
            cro = cdao.guardarCronograma(cro, datosUsuario.getCodigoUsuario(), datosUsuario.getOrigen());
            productosBean.actualizarProdPorEnt(cro.getEntregablesSet(), true);

            try {
                proyectosBean.actualizarFechaUltimaModificacion(proyectoDAO.obtenerIdPorIdCronograma(cro.getCroPk()));
            } catch (NoResultException nre) {

                LOGGER.log(Level.FINE, "Cronograma sin proyecto: cro.getCroPk()");
            }

        } catch (BusinessException | TechnicalException be) {
            LOGGER.log(Level.SEVERE, null, be);
            throw be;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            throw be;
        }

        return cro;
    }

    // Se crea esta operación para que el cronograma sea guardado cuando se carga la Ficha
    public Cronogramas guardarDespuesDeCheck(Cronogramas cro) {
        CronogramasDAO cdao = new CronogramasDAO(em);

        try {
            cro = cdao.guardarCronograma(cro, datosUsuario.getCodigoUsuario(), datosUsuario.getOrigen());
            productosBean.actualizarProdPorEnt(cro.getEntregablesSet(), true);

        } catch (BusinessException | TechnicalException be) {
            LOGGER.log(Level.SEVERE, null, be);
            throw be;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            throw be;
        }

        return cro;
    }

    @WekanCronograma
    public Object guardarCronograma(Cronogramas cro, Integer fichaFk, Integer tipoFicha, SsUsuario usuario, Integer orgPk) {
        LOGGER.info("CronogramasBean.guardarCronograma");
        try {

            boolean error = false;
            BusinessException be = new BusinessException();
            Integer cantInicioPeriodo = 0;
            Integer cantFinPeriodo = 0;
            Date inicioProyecto = null;
            Date finProyecto = null;
            String entregablesLargos = "";
            for (Entregables e : cro.getEntregablesSet()) {
        //        e.setEntDuracion(7000);
                if (e.getEntInicioProyecto()) {
                    cantInicioPeriodo++;
                    inicioProyecto = e.getEntInicioDate();
                }
                if (e.getEntFinProyecto()) {
                    cantFinPeriodo++;
                    finProyecto = e.getEntFinDate();
                }

                if (e.getEntDuracion() != null && e.getEntDuracion() > 5475) {
                    entregablesLargos = entregablesLargos + e.getEntNombre()+" ("+e.getEntId()+") " + ",";
                    error = true;
                }
            }

            if (error) {
                entregablesLargos = entregablesLargos.substring(0, entregablesLargos.length() - 2);
                be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_DURACION_MAS_15, orgPk) + entregablesLargos);
            }

            if (cantInicioPeriodo > 1) {
                be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR_MULTIPLE_INI_PERIODO, orgPk));
                error = true;
            }
            if (cantFinPeriodo > 1) {
                be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR_MULTIPLE_FIN_PERIODO, orgPk));
                error = true;
            }

            if (inicioProyecto != null && finProyecto != null) {
                if (inicioProyecto.after(finProyecto)) {
                    be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR_INI_FIN_PERIODO_INCONSISTENTE, orgPk));
                    error = true;
                }
            }

            if (error) {
                throw be;
            }

            eliminarEntregablesBorrados(cro);
            preProcesarEntregables(cro, orgPk);

            EntregablesUtils.asignarProgresoEsfuerzoPadres(cro.getEntregablesSet());

            cro = guardar(cro);

            // Se disparan acciones sobre los entregables relacionados desde otros proyectos
            proyectosBean.postProcesarEntregablesRelacionados(cro, null);

            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                Programas prog = programasBean.obtenerProgPorId(fichaFk);
                prog.setProgCroFk(cro);
                prog = programasBean.guardarPrograma(prog, usuario, orgPk, false);
                return prog;

            } else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
                Proyectos proy = proyectosBean.obtenerProyPorId(fichaFk);
                proy.setProyCroFk(cro);
                proy = proyectosBean.guardarProyecto(proy, usuario, orgPk);
                return proy;
            }
        } catch (GeneralException ex) {
            BusinessException be = new BusinessException(ex);
            be.addError(LabelsEJB.getValue(MensajesNegocio.ERROR_CRO_GUARDAR, orgPk));
            be.addErrores(ex.getErrores());
            throw be;
        }

        return null;
    }

    public void procesarActividad(ActivityInfoTO activityInfo) {
        TarjetaDAO tarjetaDAO = new TarjetaDAO(em);

        Tarjeta tarjeta = tarjetaDAO.obtenerPorIdTarjeta(activityInfo.getCardId());

        switch (activityInfo.getDescription()) {
            case Activity.SET_CUSTOM_FIELD:
                tarjetaBean.actualizarEntregableSegunCampoPersonalizado(tarjeta, activityInfo);
                break;
            case Activity.UPDATE_START_AT:
                tarjetaBean.actualizarFechaInicioEntregable(tarjeta, activityInfo);
                break;
            case Activity.UPDATE_END_AT:
                tarjetaBean.actualizarFechaFinEntregable(tarjeta, activityInfo);
                break;
            case Activity.MOVE_CARD:
                tarjetaBean.registrarCambioListaTajeta(tarjeta, activityInfo);
                break;
        }

        // actualizar id lista si esta presente, en caso de que haya cambiado
        if (activityInfo.getListId() != null
                && !tarjeta.getIdLista().equals(activityInfo.getListId())) {

            tarjeta.setIdLista(activityInfo.getListId());
        }

        EntregablesUtils.asignarProgresoEsfuerzoPadres(tarjeta.getVinculacion().getCronograma().getEntregablesSet());

        EntregablesUtils.asignarFechaInicioFinPadres(tarjeta.getVinculacion().getCronograma().getEntregablesSet());

        Proyectos proy = proyectoBean.obtenerProyPorId(tarjeta.getEntregable().getEntCroFk().getProyecto().getProyPk());

        Cronogramas croActual = proy.getProyCroFk();
        if (croActual != null) {
            proyectoBean.postProcesarEntregablesRelacionados(croActual, null);
        }

        guardar(tarjeta.getVinculacion().getCronograma());

    }

    public int[] calcularAvanceCronoFinalizadoProg(Set<Proyectos> proyectos) {
        //Toma como hipotesis que ya existe el indicador a nivel del proyecto.
        if (proyectos != null) {
            int[] calculo = {0, 0, 0};
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;
            for (Proyectos proy : proyectos) {
                if (proy.getProyIndices() != null && proy.isActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo[0] = proy.getProyIndices().getProyindAvanceFinAzul();
                    calculo[1] = proy.getProyIndices().getProyindAvanceFinVerde();
                    calculo[2] = proy.getProyIndices().getProyindAvanceFinRojo();
                    //calculo = calcularAvanceCronoFinalizado(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoFinalizadoProg(Integer progPk) {
        if (progPk != null) {
            int[] calculo = {0, 0, 0};
            int[] result = {0, 0, 0};

            ProgramasDAO programasDAO = new ProgramasDAO(em);

            try {
                calculo = programasDAO.obtenerProcentajeIndiceFinalizado(progPk);
            } catch (DAOGeneralException ex) {
                Logger.getLogger(CronogramasBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            result = cargarYAjustarResultado(calculo[0], calculo[2], result);
            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoFinalizadoProgConCronograma(Set<ProyectosConCronograma> proyectos) {
        if (proyectos != null) {
            int[] calculo;
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (ProyectosConCronograma proy : proyectos) {
                if (proy.getProyCroFk() != null && proy.getActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo = calcularAvanceCronoFinalizado(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public Cronogramas obtenerCronogramaPorProy(Integer proyPk) {
        CronogramasDAO dao = new CronogramasDAO(em);
        Cronogramas cro = dao.obtenerCronogramaProProy(proyPk);
        return cro;
    }

    public CronogramaTO obtenerCronogramaCompletoPorIdProyecto(Integer proyPk) {

        SsUsuario usuario = usuarioBean.obtenerSsUsuarioPorMail(datosUsuario.getCodigoUsuario());

        if (usuario == null) {

            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_USUARIO_OBTENER);

            throw be;
        }

        Proyectos proyecto = proyectosBean.obtenerProyPorId(proyPk);

        if (proyecto == null) {

            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_PROYECTO_NO_ENCONTRADO);

            throw be;
        }

        CronogramasDAO cronogramaDAO = new CronogramasDAO(em);
        Cronogramas cro = cronogramaDAO.obtenerCronogramaProProy(proyPk);

        if (cro == null) {

            cro = new Cronogramas();

            cro.setCroEntSeleccionado(0);
            cro.setCroPermisoEscritura(true);
            cro.setCroPermisoEscrituraPadre(true);
            cro.setEntregablesSet(new HashSet<Entregables>());

            cro.setProyecto(proyecto);

            proyecto.setProyCroFk(cro);
        }

        OrganismoDAO organismoDAO = new OrganismoDAO(em);
        Organismos organismo = organismoDAO.obtenerOrganismoPorIdProyecto(proyPk);

        Configuracion cnfSetPeriodoGantt = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PROYECTO_GANTT_PERIODO, organismo.getOrgPk());
        Configuracion cnfPmofEditor = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PMOF_GESTIONA_SUS_PROYECTOS, organismo.getOrgPk());
        
        boolean pmofEdita= Boolean.valueOf(cnfPmofEditor.getCnfValor());
        
        boolean periodoConfigurable = Boolean.valueOf(cnfSetPeriodoGantt.getCnfValor());

        boolean isPM = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(proyecto, usuario);

        boolean isPmoFederadaFicha = usuario.getUsuId().equals(proyecto.getProyUsrPmofedFk().getUsuId());
        boolean isPMO = SsUsuariosUtils.isUsuarioPMO(proyecto, usuario, organismo.getOrgPk()) || isPmoFederadaFicha;

        boolean isUsuPermitido = SsUsuariosUtils.isUsuarioGerenteOAdjuntoFicha(proyecto, usuario) || pmofEdita;

        boolean admiteNuevosEntregables = ((proyecto.getProyEstFk().isEstado(Estados.ESTADOS.INICIO.estado_id)
                || proyecto.getProyEstFk().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id))
                && isUsuPermitido);

        CronogramaTO cronograma = CronogramaUtils.convert(cro);
        cronograma.setIdOrganismo(organismo.getOrgPk());
        cronograma.setIdFicha(proyecto.getProyPk());
        cronograma.setEstadoCronograma(proyecto.getProyEstFk().getEstadoCronograma());
        cronograma.setEstadoFicha(proyecto.getProyEstFk().getEstPk());
        cronograma.setEditable(cro.getCroPermisoEscritura() && isUsuPermitido);
        cronograma.setPeriodoConfigurable(periodoConfigurable);
        cronograma.setEsPM(isPM);
        cronograma.setEsPMO(isPMO);
        cronograma.setAdmiteNuevosEntregables(admiteNuevosEntregables);

        cronograma.setEsfuerzoTotal(obtenerEsfuerzoTotal(cro));
        cronograma.setHorasTotales(obtenerHorasTotales(cro));

        List<Entregables> entregables = new ArrayList<>(cro.getEntregablesSet());
        EntregablesUtils.sortById(entregables);

        for (Entregables e : entregables) {
            EntregableTO entregable = EntregablesUtils.convert(e);

            Integer duracion = (e.getEntDuracion() != null)
                    ? e.getEntDuracion() : DatesUtils.diasEntreFechas(e.getEntInicioDate(), e.getEntFinDate());

            entregable.setDuracion(duracion);

            Integer duracionLineaBase = (e.getEntDuracionLineaBase() != null)
                    ? e.getEntDuracionLineaBase()
                    : (e.getEntInicioLineaBase() != null && e.getEntFinLineaBase() != null)
                    ? DatesUtils.diasEntreFechas(e.getEntInicioLineaBaseDate(), e.getEntFinLineaBaseDate())
                    : 0;

            entregable.setDuracionLineaBase(duracionLineaBase);

            boolean tieneProductos = productosBean.tieneProdPorEnt(e.getEntPk());
            entregable.setTieneProductos(tieneProductos);

            boolean tieneDependencias = entregablesBean.tieneDependencias(e.getEntPk());
            entregable.setEliminable(!tieneDependencias && !tieneProductos);

            SsUsuario coordinador = e.getCoordinadorUsuFk();
            UsuarioTO coordinadorTO = null;

            if (coordinador != null) {
                coordinadorTO = new UsuarioTO(coordinador.getUsuId(), coordinador.getUsuPrimerNombre(), coordinador.getUsuPrimerApellido());
            }

            entregable.setCoordinador(coordinadorTO);

            if (e.getEsReferencia() != null && e.getEsReferencia() && e.getReferido() != null) {

                EntregableTO referido = EntregablesUtils.convert(e.getReferido());

                entregable.setCoordinador(coordinadorTO);

                entregable.setReferido(referido);

                entregable.setProyectoReferido(ProyectosUtils.convert(e.getReferido().getEntCroFk().getProyecto()));
            }

            entregable.setTieneVinculacionWekan(entregablesBean.tieneVinculacionWekan(e.getEntPk()));

            EntregablesDAO entregablesDAO = new EntregablesDAO(em);
            List<Entregables> referencias = entregablesDAO.obtenerEntregablesReferenciando(e.getEntPk());
            entregable.setEsReferenciaDesdeOtroProyecto(!referencias.isEmpty());

            cronograma.getEntregables().add(entregable);
        }

        return cronograma;
    }

    public Cronogramas obtenerCronograma(Integer croPk) {

        if (croPk == null) {
            return null;
        }

        CronogramasDAO dao = new CronogramasDAO(em);

        try {
            Cronogramas cro = dao.findById(Cronogramas.class, croPk);
            return cro;
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            throw be;
        }
    }

    public int[] calcularAvanceCronoFinalizado(Cronogramas cro) {
        int[] result = {0, 0, 0};
        if (cro != null) {
            result = calcularAvanceCronoFinalizado(cro.getEntregablesSet());
        }
        return result;
    }

    public int[] calcularAvanceCronoParcial(Cronogramas cro) {
        int[] result = {0, 0, 0};
        if (cro != null) {
            result = calcularAvanceCronoParcial(cro.getEntregablesSet());
        }
        return result;
    }

    public int[] calcularAvanceCronoFinalizado(Set<Entregables> entregables) {
        int[] result = {0, 0, 0};
        double azul = 0;
        double rojo = 0;
        int esfuerzoTotal = 0;

        for (Entregables ent : entregables) {

            if (ent.esEntParent()) {
                continue;
            }

            if (ent.getEntProgreso() >= 100) {
                azul += ent.getEntEsfuerzo();

            } else if (DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                rojo += ent.getEntEsfuerzo();
            }

            esfuerzoTotal += ent.getEntEsfuerzo();
        }

        if (esfuerzoTotal > 0) {
            double azulD = azul * 100 / esfuerzoTotal;
            double rojoD = rojo * 100 / esfuerzoTotal;
            result = cargarYAjustarResultado(azulD, rojoD, result);
        }

        return result;
    }

    public int[] calcularAvanceCronoParcialProg(Set<Proyectos> proyectos) {
        if (proyectos != null) {
            int[] calculo = {0, 0, 0};
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (Proyectos proy : proyectos) {
                if (proy.getProyIndices() != null && proy.isActivo()) {
                    Integer peso = proy.getProyPeso();
                    //calculo = calcularAvanceCronoParcial(proy.getProyCroFk().getEntregablesSet());
                    calculo[0] = proy.getProyIndices().getProyindAvanceParAzul();
                    calculo[1] = proy.getProyIndices().getProyindAvanceParVerde();
                    calculo[2] = proy.getProyIndices().getProyindAvanceParRojo();
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    /*
        *   15-05-2018 Nico: Esta operación se hace para evitar hacer un loop entre los proyectos
        *           y los ProyIndices asociados.
     */
    public int[] calcularAvanceCronoParcialProg(Integer progPk) {
        if (progPk != null) {
            int[] calculo = {0, 0, 0};
            int[] result = {0, 0, 0};

            ProgramasDAO programasDAO = new ProgramasDAO(em);

            try {
                calculo = programasDAO.obtenerProcentajeIndiceParcial(progPk);
            } catch (DAOGeneralException ex) {
                Logger.getLogger(CronogramasBean.class.getName()).log(Level.SEVERE, null, ex);
            }

            result = cargarYAjustarResultado(calculo[0], calculo[2], result);
            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoParcialProgConCronograma(Set<ProyectosConCronograma> proyectos) {
        if (proyectos != null) {
            int[] calculo;
            int[] result = {0, 0, 0};
            int azul = 0;
            int rojo = 0;
            int cont = 0;

            for (ProyectosConCronograma proy : proyectos) {
                if (proy.getProyCroFk() != null && proy.getActivo()) {
                    Integer peso = proy.getProyPeso();
                    calculo = calcularAvanceCronoParcial(proy.getProyCroFk().getEntregablesSet());
                    if (calculo[0] > 0 || calculo[1] > 0 || calculo[2] > 0) {
                        azul += calculo[0] * peso;
                        rojo += calculo[2] * peso;
                        cont += peso;
                    }
                }
            }

            if (cont != 0) {
                double azulD = azul / cont;
                double rojoD = rojo / cont;
                result = cargarYAjustarResultado(azulD, rojoD, result);
            }

            return result;
        }
        return null;
    }

    public int[] calcularAvanceCronoParcial(Set<Entregables> entregables) {
        if (entregables != null) {
            int[] result = {0, 0, 0};
            double azul = 0;
            double rojo = 0;
            double esfuerzoTotal = 0;

            for (Entregables ent : entregables) {

                if (ent.esEntParent()) {
                    continue;
                }

                esfuerzoTotal += ent.getEntEsfuerzo();
            }

            if (esfuerzoTotal != 0) {
                for (Entregables ent : entregables) {

                    if (ent.esEntParent()) {
                        continue;
                    }

                    double pesoAzul = ent.getEntProgreso().doubleValue() * ent.getEntEsfuerzo().doubleValue() / 100;
                    double porcentajeAzulDelTotal = pesoAzul * 100 / esfuerzoTotal;
                    azul += porcentajeAzulDelTotal;

                    if (DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                        rojo += (ent.getEntEsfuerzo().doubleValue() - pesoAzul) * 100 / esfuerzoTotal;
                    }
                }

                result = cargarYAjustarResultado(azul, rojo, result);
            }
            return result;
        }
        return null;
    }

    public List<Entregables> obtenerResumenCronograma(Integer proyPk, int size) {
        if (proyPk != null) {
            EntregablesDAO eDao = new EntregablesDAO(em);
            List<Entregables> entregables = eDao.obtenerEntregablesPorProy(proyPk);
            List<Entregables> result = new ArrayList<>();
            for (Entregables ent : entregables) {
                if (ent.getEntEsfuerzo() > 0 && ent.getEntProgreso() < 100
                        && DatesUtils.esMayor(new Date(), ent.getEntFinDate())) {
                    result.add(ent);
                }
            }
            return EntregablesUtils.primerosEntregables(EntregablesUtils.sortByEsfuerzo(result, true), size);
        }
        return null;
    }

    public Integer obtenerEsfuerzoTotal(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            Integer result = 0;
            for (Entregables ent : cro.getEntregablesSet()) {

                if (!ent.esEntParent() && ent.getEntEsfuerzo() != null) {
                    result += ent.getEntEsfuerzo();
                }
            }
            return result;
        }
        return null;
    }

    public String obtenerHorasTotales(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            String result = "";
            for (Entregables ent : cro.getEntregablesSet()) {
                if (!StringsUtils.isEmpty(ent.getEntHorasEstimadas())) {
                    result = DatesUtils.sumarHora(result, ent.getEntHorasEstimadas());
                }
            }
            return result;
        }
        return null;
    }

    public Integer esfuerzoTotal(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            Integer result = 0;
            for (Entregables ent : cro.getEntregablesSet()) {

                if (!ent.esEntParent() && ent.getEntEsfuerzo() != null) {
                    result += ent.getEntEsfuerzo();
                }
            }
            return result;
        }
        return null;
    }

    /**
     * Calcula el esfurzo total solo considerando los hitos
     *
     * @param cro
     * @return
     */
    public Integer esfuerzoTotalHitos(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            Integer result = 0;
            for (Entregables ent : cro.getEntregablesSet()) {
                if (ent.getEntFinEsHito() != null && ent.getEntFinEsHito().booleanValue() && ent.getEntEsfuerzo() != null) {
                    result += ent.getEntEsfuerzo();
                }
            }
            return result;
        }
        return null;
    }

    public String horasTotales(Cronogramas cro) {
        if (cro != null && CollectionsUtils.isNotEmpty(cro.getEntregablesSet())) {
            String result = "";
            for (Entregables ent : cro.getEntregablesSet()) {
                if (!StringsUtils.isEmpty(ent.getEntHorasEstimadas())) {
                    result = DatesUtils.sumarHora(result, ent.getEntHorasEstimadas());
                }
            }
            return result;
        }
        return null;
    }

    public Cronogramas copiarProyCronograma(Cronogramas proyCroFk, int desfasajeDias) {
        if (proyCroFk != null) {
            Cronogramas nvoCro = new Cronogramas();
            nvoCro.setCroEntBorrados(proyCroFk.getCroEntBorrados());
            nvoCro.setCroEntSeleccionado(proyCroFk.getCroEntSeleccionado());
            nvoCro.setCroPermisoEscritura(proyCroFk.getCroPermisoEscritura());
            nvoCro.setCroPermisoEscrituraPadre(proyCroFk.getCroPermisoEscrituraPadre());
            nvoCro.setCroResources(proyCroFk.getCroResources());
            return nvoCro;
        }
        return null;
    }

    private void eliminarEntregablesBorrados(Cronogramas cro) {

          LOGGER.info("CronogramasBean.eliminarEntregablesBorrados");
        
        if (cro == null || StringsUtils.isEmpty(cro.getCroEntBorrados())) {
            return;
        }

        Set<Entregables> entSet = cro.getEntregablesSet();
        cro = obtenerCronograma(cro.getCroPk());

        Integer org = cro.getProyecto().getProyOrgFk().getOrgPk();
        Estados est = cro.getProyecto().getProyEstFk();

        if (!(est.isEstado(Estados.ESTADOS.INICIO.estado_id) || est.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id))) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_ESTADO);
            throw be;
        }

        Set<Entregables> entSetPerist = cro.getEntregablesSet();
        List<Entregables> borradosList = new ArrayList<>();

        for (Entregables ent : entSetPerist) {
            if (!entSet.contains(ent)) {
                borradosList.add(ent);
            }
        }

        if (borradosList.isEmpty()) {
            return;
        }

        EntregablesDAO entregablesDAO = new EntregablesDAO(em);

        for (Entregables borrado : borradosList) {
            boolean avance = borrado.getEntProgreso() != null && borrado.getEntProgreso() > 0;
            boolean horasAsociadas = registrosHorasBean.tieneDependenciasEnt(borrado.getEntPk());
            boolean partAsociados = participantesBean.tieneDependenciasEnt(borrado.getEntPk());
            boolean tieneDependencias = entregablesBean.tieneDependencias(borrado.getEntPk());

            if (tieneDependencias) {
                BusinessException be = new BusinessException();
                be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_DEPEDENCIAS, org), borrado.getEntNombre()));
                throw be;
            } else if (avance && !borrado.getEsReferencia()) {
                BusinessException be = new BusinessException();
                be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_AVANCE, org), borrado.getEntNombre()));
                throw be;
            } else if (horasAsociadas) {
                BusinessException be = new BusinessException();
                be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_HORAS, org), borrado.getEntNombre()));
                throw be;
            } else if (partAsociados) {
                BusinessException be = new BusinessException();
                be.addError(String.format(LabelsEJB.getValue(MensajesNegocio.ERROR_ENTREGABLE_BORRAR_PART, org), borrado.getEntNombre()));
                throw be;
            }

            List<Entregables> referencias = entregablesDAO.obtenerEntregablesReferenciando(borrado.getEntPk());

            for (Entregables referencia : referencias) {
                referencia.setReferido(null);
                referencia.setEntProgreso(0);
                referencia.setEntEsfuerzo(0);

                EntregablesUtils.asignarProgresoEsfuerzoPadres(referencia.getEntCroFk().getEntregablesSet());
            }

            documentosBean.quitarEntregable(borrado.getEntPk());
        }
    }

    //redondea a x decimales
    public static double round(double value, int places) {
        if (places < 0) {
            return 0;
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Dado los valores de azul y rojo, los redondea y luego calcula el valor
     * del verde.
     *
     * @param azul
     * @param rojo
     * @param result
     * @return Array de int con los valores del azul, verde y rojo.
     */
    private int[] cargarYAjustarResultado(double azul, double rojo, int[] result) {
        int azulInt;
        int rojoInt;

        azul = azul > 100 ? 100 : azul;
        rojo = rojo > 100 ? 100 : rojo;

        azul = round(azul, 2);
        rojo = round(rojo, 2);

        double mod = azul % 1;
        double modRojo = rojo % 1;

        mod = round(mod, 2);
        modRojo = round(modRojo, 2);

        //casos del algorimto 
        //caso 1: que ya la suma da mayor que 100 el más dificl de arreglar. Se tiene que restar algun numero para que de 100, no es clara a cual. Se resta al que el mod sea neor a 0.5
        //        No queda claro que esto pueda pasar, pero este algoritmo es defensivo y lo arregla.   
        //caso 2: que da 100  pero los dos dan 0,5 entonces hace mal el tail
        //caso 3: que da menor a 100 (este caso nunca se comporta mal)
        if ((azul + rojo) > 100d) {
            //caso 1
            //se tiene que arreglar y hacer que sume 100
            double cantidadRestar = (100.0d - (azul + rojo));

            if (mod < 0.5) {
                azul = azul + cantidadRestar;
            } else if (modRojo < 0.5) {
                rojo = rojo + cantidadRestar;
            } else {
                azul = azul + cantidadRestar;
            }

            mod = azul % 1;
            modRojo = rojo % 1;

            mod = round(mod, 2);
            modRojo = round(modRojo, 2);

        }

        if (mod == 0.5 && modRojo == 0.5) {
            //caso 2
            azulInt = (int) Math.abs(Math.ceil(azul));
            rojoInt = (int) Math.abs(Math.floor(rojo));

        } else {
            if (mod >= 0.5) {
                azulInt = (int) Math.abs(Math.ceil(azul));
            } else {
                azulInt = (int) Math.abs(Math.floor(azul));
            }

            if (modRojo >= 0.5) {
                rojoInt = (int) Math.abs(Math.ceil(rojo));
            } else {
                rojoInt = (int) Math.abs(Math.floor(rojo));
            }
        }

        result[0] = azulInt;
        result[2] = rojoInt;
        int verde = 100 - result[0] - result[2];
        result[1] = verde;

        // prorrateo
        int suma = result[0] + result[1] + result[2];
        if (suma > 100) {
            result[1] = (result[0] / suma) * 100;
            result[2] = (result[0] / suma) * 100;
            result[3] = (result[0] / suma) * 100;
        }
        return result;
    }

    /**
     * Agrega los nombres a los entregables, setea valores si es hito, y
     * modifica línea base si pasó un entregable a hito.
     *
     * @param cro
     */
    private void preProcesarEntregables(Cronogramas cro, Integer orgPk) {
        
          LOGGER.log(Level.INFO, "preProcesarEntregables");
        if (cro != null && cro.getEntregablesSet() != null) {

            for (Entregables ent : cro.getEntregablesSet()) {

                if (StringsUtils.isEmpty(ent.getEntNombre())) {
                    ent.setEntNombre(LabelsEJB.getValue("entregable", orgPk) + " " + (ent.getEntId() != null ? ent.getEntId() : ""));
                }

                if (ent.getCoordinadorUsuFk() == null || ent.getCoordinadorUsuFk().getUsuId() < 1) {
                    ent.setCoordinadorUsuFk(usuarioBean.obtenerSsUsuarioPorMail(datosUsuario.getCodigoUsuario()));
                }

                if (ent.getEntFinEsHito() != null && ent.getEntFinEsHito()) {
                    ent.setEntInicio(ent.getEntFin());
                    ent.setEntDuracion(1);
                    if (ent.getEntDuracionLineaBase() != null && ent.getEntDuracionLineaBase() > 1) {
                        ent.setEntInicioLineaBase(ent.getEntInicio());
                        ent.setEntFinLineaBase(ent.getEntFin());
                        ent.setEntDuracionLineaBase(ent.getEntDuracion());
                    }
                }
            }
        }
    }

    public void calcularAvancePadresProyectos() {

        LOGGER.log(Level.INFO, "[Calculo avance] Comienzo");
        CronogramasDAO cronogramaDAO = new CronogramasDAO(em);

        long count = 100;
        long pos = 0;

        List<Cronogramas> cronogramas;

        try {
            do {
                cronogramas = cronogramaDAO.findEntityByCriteria(Cronogramas.class, null, new String[]{"croPk"}, new boolean[]{true}, pos, count);

                LOGGER.log(Level.INFO, "[Calculo avance] cronogramas obtenidos: {0}", cronogramas.size());
                pos += cronogramas.size();

                for (Cronogramas cronograma : cronogramas) {

                    LOGGER.log(Level.INFO, "[Calculo avance] procesando cronograma {0} entregables {1}", new Object[]{cronograma.getCroPk(), cronograma.getEntregablesSet().size()});

                    EntregablesUtils.asignarProgresoEsfuerzoPadres(cronograma.getEntregablesSet());
                }
            } while (!cronogramas.isEmpty());

        } catch (DAOGeneralException ex) {
            Logger.getLogger(CronogramasBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        LOGGER.log(Level.INFO, "[Calculo avance] Fin. {0} cronogramas procesados", pos);
    }
}
