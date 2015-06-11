package com.sofis.business.ejbs;

import com.sofis.business.utils.ProgProyUtils;
import com.sofis.business.validations.ProgramaValidacion;
import com.sofis.data.daos.ProgIndicesDAO;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.ProgIndices;
import com.sofis.entities.data.ProgIndicesPre;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.MailException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProgramasBean")
@LocalBean
public class ProgramasBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private ConsultaHistorico<Programas> ch;
    @Inject
    private DatosUsuario du;

    @EJB
    private ProyectosBean proyectoBean;
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
    private PresupuestoBean presupuestoBean;
    @Inject
    private CalidadBean calidadBean;

    private Programas guardar(Programas prog, SsUsuario usuario, Integer orgPk) throws GeneralException {
        logger.info("Guardar Programa.");

        ProgramaValidacion.validar(prog, usuario, orgPk);
        ProgramasDAO fpDao = new ProgramasDAO(em);

        try {
            SsUsuario usu;
            if (prog.getProgUsrAdjuntoFk() != null) {
                usu = em.find(SsUsuario.class, prog.getProgUsrAdjuntoFk().getUsuId());
                prog.setProgUsrAdjuntoFk(usu);
            }

            if (prog.getProgUsrGerenteFk() != null) {
                usu = em.find(SsUsuario.class, prog.getProgUsrGerenteFk().getUsuId());
                prog.setProgUsrGerenteFk(usu);
            }

            if (prog.getProgUsrPmofedFk() != null) {
                usu = em.find(SsUsuario.class, prog.getProgUsrPmofedFk().getUsuId());
                prog.setProgUsrPmofedFk(usu);
            }

            if (prog.getProgUsrSponsorFk() != null) {
                usu = em.find(SsUsuario.class, prog.getProgUsrSponsorFk().getUsuId());
                prog.setProgUsrSponsorFk(usu);
            }

            prog = fpDao.update(prog, du.getCodigoUsuario(), du.getOrigen());

            return prog;
        } catch (BusinessException be) {
            logger.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public Programas guardarPrograma(Programas prog, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (prog.getProgPk() == null || prog.getProgFechaAct() == null) {
            prog.setProgFechaAct(new Date());
        }

        if (prog.getProgPk() == null) {
            prog.setProgFechaCrea(new Date());

            if (usuario.isUsuarioPMOT(orgPk)) {
                prog.setProgEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
            } else {
                prog.setProgEstFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
                prog.setProgEstPendienteFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOT.estado_id));
            }
        }

        if (prog.getActivo() == null) {
            prog.setActivo(true);
        }

        if (CollectionsUtils.isNotEmpty(prog.getDocumentosSet())) {
            for (Documentos doc : prog.getDocumentosSet()) {
                if (doc.getDocsFecha() == null) {
                    doc.setDocsFecha(new Date());
                }
            }
        }

        prog = guardar(prog, usuario, orgPk);

        mailPostGuardar(prog, orgPk);

        return prog;
    }

    private void mailPostGuardar(Programas prog, Integer orgPk) {
        if (prog.getProgEstFk().isPendientes()) {
            List<SsUsuario> usuariosDest = new ArrayList<>();
            if (prog.getProgEstFk().isEstado(Estados.ESTADOS.PENDIENTE_PMOT.estado_id)) {
                String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
                boolean[] ascUsuarios = new boolean[]{true, true, true, true};
                usuariosDest.addAll(ssUsuarioBean.obtenerUsuariosPorRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk, ordenUsuarios, ascUsuarios));
            } else if (prog.getProgEstFk().isEstado(Estados.ESTADOS.PENDIENTE_PMOF.estado_id)) {
                usuariosDest.add(prog.getProgUsrPmofedFk());
            }

            String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosDest);
            try {
                mailBean.comunicarProgProyPendiente(orgPk, prog, destinatarios);
            } catch (MailException me) {
                logger.log(Level.SEVERE, me.getMessage(), me);
            }
        }
    }

    public Programas guardarSinValidar(Programas prog, SsUsuario usuario) throws GeneralException {
        ProgramasDAO fpDao = new ProgramasDAO(em);
        try {
            prog = fpDao.update(prog, du.getCodigoUsuario(), du.getOrigen());

        } catch (BusinessException be) {
            logger.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
            ge.addError(ex.getMessage());
            throw ge;
        }
        return prog;
    }

    /**
     * Retorna los Programas relacionados con el Organismo aportado.
     *
     * @param id
     * @return Programas
     */
    public List<Programas> obtenerProgPorOrganismo(int orgId) throws GeneralException {
        ProgramasDAO programasDao = new ProgramasDAO(em);

        try {
            List<Programas> resultado = programasDao.findByOneProperty(Programas.class, "progOrgFk.orgPk", orgId);
            return resultado;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Retorna los Programas de un Organismos cuyo estado no sea pendiente.
     *
     * @param orgId
     * @return
     * @throws GeneralException
     */
    public List<Programas> obtenerProgIniciadoPorOrg(int orgId) throws GeneralException {
        ProgramasDAO programasDao = new ProgramasDAO(em);

        try {
            MatchCriteriaTO criteriaOrganismo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progOrgFk.orgPk", orgId);
            MatchCriteriaTO criteriaIniciado = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.GREATER, "progEstFk.estOrdenProceso", 1);
            MatchCriteriaTO criteriaActivo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "activo", Boolean.FALSE);
            CriteriaTO criteria = CriteriaTOUtils.createANDTO(criteriaOrganismo, criteriaIniciado, criteriaActivo);

            List<Programas> resultado = programasDao.findEntityByCriteria(Programas.class, criteria, new String[]{"progNombre"}, new boolean[]{true}, null, null);
            return resultado;

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException();
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<Programas> obtenerProgComboPorOrg(int orgId) throws GeneralException {
        ProgramasDAO programasDao = new ProgramasDAO(em);
        return programasDao.obtenerProgComboPorOrg(orgId);
    }

    public Programas obtenerProgPorId(Integer id) throws GeneralException {
        ProgramasDAO progDao = new ProgramasDAO(em);
        if (id != null) {
            try {
                return progDao.findById(Programas.class, id);
            } catch (DAOGeneralException ex) {
                logger.logp(Level.SEVERE, ProgramasBean.class.getName(), "obtenerProgPorId", ex.getMessage(), ex);
                TechnicalException te = new TechnicalException();
                te.addError(ex.getMessage());
                throw te;
            }
        }
        return null;
    }

    public Programas guardarPrograma(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Programas prog = (Programas) ProgProyUtils.fichaTOToProgProy(fichaTO);

        try {
            return guardarPrograma(prog, usuario, orgPk);
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, ProgramasBean.class.getName(), ex);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ProgramasBean.class.getName(), ex);
        }
        return null;
    }

    public Programas guardarProgramaAprobacion(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Programas prog = (Programas) ProgProyUtils.fichaTOToProgProy(fichaTO);

        return guardarProgramaAprobacion(prog, usuario, orgPk);
    }

    public Programas guardarProgramaAprobacion(Programas prog, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Set<Proyectos> setProyectos = proyectoBean.obtenerProyPorProgId(prog.getProgPk());
        prog.setProyectosSet(setProyectos);
        programasProyectosBean.solAprobacionCambioEstado(prog, usuario, orgPk);
        prog = guardarPrograma(prog, usuario, orgPk);

        if (prog != null && prog.isEstPendienteFk()) {
            String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
            boolean[] ascUsuarios = new boolean[]{true, true, true, true};
            List<SsUsuario> usuariosPMOT = ssUsuarioBean.obtenerUsuariosPorRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk, ordenUsuarios, ascUsuarios);
            String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosPMOT);
            try {
                mailBean.comunicarSolicitudAprobacion(orgPk, prog, destinatarios);
            } catch (MailException me) {
                logger.log(Level.SEVERE, me.getMessage(), me);
            }
        }
        return prog;
    }

    public void actualizarProgramaPorProyectos(Proyectos proy, SsUsuario usuario, Integer orgPk) {
        if (proy.getProyProgFk() == null) {
            return;
        }

        actualizarProgramaPorProyectos(proy.getProyProgFk().getProgPk(), usuario);
        guardarIndicadores(proy.getProyProgFk().getProgPk(), orgPk);
    }

    /**
     * Dado un proyecto actualiza el estado del programa al que pertenece y la
     * menor fecha de actualización de sus proyectos.
     *
     * @param progPk
     * @param usuario
     */
    public void actualizarProgramaPorProyectos(Integer progPk, SsUsuario usuario) {
        if (progPk == null) {
            return;
        }

        Programas prog = this.obtenerProgPorId(progPk);

        if (!prog.isEstadoPendientes()) {
            Set<Proyectos> setProyectos = proyectoBean.obtenerProyPorProgId(prog.getProgPk());

            if (setProyectos != null && !setProyectos.isEmpty()) {
                Estados estado = null;
                Date menorActualizacion = new Date();
                for (Proyectos proyecto : setProyectos) {
                    if (!proyecto.isEstadoPendientes()
                            && proyecto.isActivo()) {
                        if (estado != null) {
                            if (estado.getEstOrdenProceso() != null
                                    && proyecto.getProyEstFk().getEstOrdenProceso() < estado.getEstOrdenProceso()) {
                                estado = proyecto.getProyEstFk();
                            }
                        } else {
                            estado = proyecto.getProyEstFk();
                        }

                        if (DatesUtils.esMayor(menorActualizacion, proyecto.getProyFechaAct())) {
                            menorActualizacion = proyecto.getProyFechaAct();
                        }
                    }
                }

                if (estado != null) {
                    prog.setProgEstFk(estado);
                    prog.setProgFechaAct(menorActualizacion);
                    this.guardarSinValidar(prog, usuario);
                }
            }
        }
    }

    public Interesados guardarInteresado(Interesados i, Integer progId) {
        Programas p = em.find(Programas.class, progId);
        p.getInteresadosList().add(i);
        em.merge(p);

        return i;
    }

    public Programas darBajaPrograma(Integer progPk, SsUsuario usuario, Integer orgPk) throws GeneralException {
        if (usuario.isUsuarioPMOT(orgPk)) {
            Set<Proyectos> setProyectos = proyectoBean.obtenerProyPorProgId(progPk);
            boolean activos = false;
            for (Proyectos proyectos : setProyectos) {
                if (proyectos.getActivo()) {
                    activos = true;
                }
            }

            if (activos) {
                throw new BusinessException(MensajesNegocio.ERROR_ELIMINAR_PROG_PROY_ACTIVOS);
            }
            Programas p = em.find(Programas.class, progPk);
            p.setActivo(false);

            try {
                p = guardarPrograma(p, usuario, orgPk);
                //TODO: Notificar al PM, Adjunto y PMOF
                return p;
            } catch (GeneralException ge) {
                throw new BusinessException(MensajesNegocio.ERROR_ELIMINAR_PROGRAMA);
            }
        } else {
            throw new BusinessException(MensajesNegocio.ERROR_USUARIO_NO_PERMISO_ACCION);
        }

    }

    public ProgIndices obtenerIndicadores(Integer progPk, Integer orgPk) {
        ProgIndicesDAO progDao = new ProgIndicesDAO(em);
        ProgIndices p = null;
        try {
            p = progDao.obtenerIndicePorProgId(progPk);
            if (p == null) {
                p = guardarIndicadores(progPk, orgPk);
            }
            return p;
        } catch (Exception ex) {
            logger.logp(Level.SEVERE, ProyectosBean.class.getName(), "obtenerIndices", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException();
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void guardarIndicadoresSimple(Integer progPk, Integer orgPk) {
        guardarIndicadores(progPk, orgPk);
    }

    /**
     * Calcula los inices y los guarda.
     *
     * @param progPk
     * @return
     */
    public ProgIndices guardarIndicadores(Integer progPk, Integer orgPk) {
        logger.log(Level.FINE, "GUARDAR INDICE PROG {0}", progPk);

        if (progPk != null) {
            ProgIndicesDAO dao = new ProgIndicesDAO(em);
            ProgIndices ind = dao.obtenerIndicePorProgId(progPk);

            if (ind == null) {
                ind = new ProgIndices();
            }

            Set<Proyectos> setProyectos = proyectoBean.obtenerProyPorProgId(progPk);

            ind.setProgindRiesgoAlto(riesgosBean.obtenerCantRiesgosAltosPrograma(setProyectos, orgPk));
            ind.setProgindRiesgoExpo(riesgosBean.obtenerExposicionRiesgoPrograma(setProyectos));
            ind.setProgindRiesgoUltact(riesgosBean.obtenerDateUltimaActualizacionPrograma(setProyectos));
            ind.setProgindMetodologiaEstado(documentosBean.calcularIndiceEstadoMetodologiaPrograma(setProyectos));
            ind.setProgindMetodologiaSinAprobar(documentosBean.calcularIndiceMetodologiaSinAprobarProgramas(progPk));
            ind.setProgindPeriodoInicio(obtenerPrimeraFecha(setProyectos));
            ind.setProgindPeriodoFin(obtenerUltimaFecha(setProyectos));

            ind.setProgindCalInd(calidadBean.obtenerIndiceCalidadPrograma(setProyectos, orgPk));
            ind.setProgindCalPend(calidadBean.obtenerPendCalidadPrograma(setProyectos));

            ind.setProyActualizacion(proyectoBean.obtenerUltimaActualizacionPrograma(setProyectos));

            try {
                ind = dao.update(ind);

                ProgramasDAO pDao = new ProgramasDAO(em);
                pDao.updateIndices(progPk, ind);

                cargarIndPresupuesto(progPk, ind, setProyectos, orgPk);
                ind = dao.update(ind);

                return ind;
            } catch (DAOGeneralException ex) {
                Logger.getLogger(ProyectosBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    /**
     * Retorna la primer fecha para un progrma dependiendo de las fechas de sus
     * proyectos.
     *
     * @param setProyectos
     * @return Date
     */
    private Date obtenerPrimeraFecha(Set<Proyectos> setProyectos) {
        Date result = null;
        // Para hacerlo mas performante se toma como valido que antes de calcular 
        // estos valores ya se guardaron los mismos para los proyectos.
        for (Proyectos proy : setProyectos) {
            Date proyInicio = proy.getProyIndices() != null ? proy.getProyIndices().getProyindPeriodoInicio() : null;
            if (proy.isActivo()
                    && proyInicio != null
                    && (result == null || DatesUtils.esMayor(result, proyInicio))) {
                result = proyInicio;
            }
        }

        return result;
    }

    /**
     * Retorna la última fecha para un progrma dependiendo de las fechas de sus
     * proyectos.
     *
     * @param setProyectos
     * @return Date
     */
    private Date obtenerUltimaFecha(Set<Proyectos> setProyectos) {
        Date result = null;

        // Para hacerlo mas performante se toma como valido que antes de calcular 
        // estos valores ya se guardaron los mismos para los proyectos.
        for (Proyectos proy : setProyectos) {
            Date proyFin = proy.getProyIndices() != null ? proy.getProyIndices().getProyindPeriodoFin() : null;
            if (proy.isActivo()
                    && proyFin != null
                    && (result == null || DatesUtils.esMayor(proyFin, result))) {
                result = proyFin;
            }
        }

        return result;
    }

    public List<Programas> obtenerTodosPorOrg(Integer orgPk) {
        ProgramasDAO dao = new ProgramasDAO(em);
        try {
//            return dao.findAll(Programas.class);
            return dao.findByOneProperty(Programas.class, "progOrgFk.orgPk", orgPk);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(ProgramasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer pesoTotalPrograma(Programas prog) {
        if (prog != null) {
            Set<Proyectos> setProyectos = proyectoBean.obtenerProyPorProgId(prog.getProgPk());
            if (setProyectos != null && !setProyectos.isEmpty()) {
                Integer result = 0;
                for (Proyectos proy : setProyectos) {
                    if (proy.isActivo()
                            && (proy.getProyEstFk().isEstado(Estados.ESTADOS.INICIO.estado_id)
                            || proy.getProyEstFk().isEstado(Estados.ESTADOS.PLANIFICACION.estado_id)
                            || proy.getProyEstFk().isEstado(Estados.ESTADOS.EJECUCION.estado_id))
                            && proy.getProyPeso() != null) {
                        result += proy.getProyPeso();
                    }
                }
                return result;
            }
        }
        return null;
    }

    private void cargarIndPresupuesto(Integer progPk, ProgIndices ind, Set<Proyectos> setProyectos, Integer orgPk) {
        if (ind != null) {
            Map<Integer, Double> totalMoneda = new HashMap<>();
            List<Moneda> monedas = presupuestoBean.obtenerMonedasPresupuestoPrograma(progPk);
            for (Proyectos proy : setProyectos) {
                if (proy.isActivo() && proy.getProyPreFk() != null) {
                    for (Moneda moneda : monedas) {
                        Double total = presupuestoBean.obtenerTotalPorMoneda(proy.getProyPreFk().getPrePk(), moneda);
                        if (totalMoneda.containsKey(moneda.getMonPk())) {
                            total += totalMoneda.get(moneda.getMonPk());
                        }
                        totalMoneda.put(moneda.getMonPk(), total);
                    }
                }
            }

            for (Moneda moneda : monedas) {
                int estado = presupuestoBean.obtenerEstadoAC(setProyectos, moneda.getMonPk(), orgPk, null, null);

                ProgIndicesPre progIndicesPre = null;
                if (ind.getProgIndPreSet() != null) {
                    for (ProgIndicesPre progIndPre : ind.getProgIndPreSet()) {
                        if (moneda.getMonPk().equals(progIndPre.getProgindpreMonFk())) {
                            progIndicesPre = progIndPre;
                            break;
                        }
                    }
                }

                if (progIndicesPre == null) {
                    progIndicesPre = new ProgIndicesPre();
                    progIndicesPre.setProgindpreMonFk(moneda.getMonPk());
                    progIndicesPre.setProgindpreProgindFk(ind.getProgindPk());
                }
                progIndicesPre.setProgindpreEstPre(estado);
                progIndicesPre.setProgindpreTotal(totalMoneda.get(moneda.getMonPk()));
                if (progIndicesPre.getProgindprePk() == null) {
                    ind.getProgIndPreSet().add(progIndicesPre);
                }
            }
        }
    }
}
