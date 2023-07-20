package com.sofis.business.ejbs;

import com.sofis.business.utils.ProgProyUtils;
import com.sofis.business.validations.ProgramaValidacion;
import com.sofis.data.daos.ProgramasDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Interesados;
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
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

@Named
@Stateless(name = "ProgramasBean")
@LocalBean
public class ProgramasBean {

    private static final Logger LOGGER = Logger.getLogger(ProgramasBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;

    @Inject
    private DatosUsuario du;

    @EJB
    private ProyectosBean proyectoBean;
    @EJB
    private MailBean mailBean;
    @EJB
    private SsUsuarioBean ssUsuarioBean;
    @EJB
    private ProgramasProyectosBean programasProyectosBean;
    @Inject
    private ProgIndicesBean progIndicesBean;
    @Inject
    private EstadosBean estadosBean;

    @Inject
    private CalculoIndicadoresAgendadoBean calculoIndicadoresAgendadoBean;
    
    @Inject
    private ConfiguracionBean configuracionBean;

    public void flush() {
        this.em.flush();
    }

    private Programas guardar(Programas prog, SsUsuario usuario, Integer orgPk) throws GeneralException {
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

            prog = fpDao.update(prog, this.du.getCodigoUsuario(), du.getOrigen());

            return prog;
        } catch (BusinessException be) {
            LOGGER.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            LOGGER.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
            ge.addError(ex.getMessage());
            throw ge;
        }
    }

    public Programas guardarPrograma(Programas prog, SsUsuario usuario, Integer orgPk, Boolean isCopia) throws GeneralException {
        return guardarPrograma(prog, usuario, orgPk, null, isCopia);
    }

    public Programas guardarPrograma(Programas prog, SsUsuario usuario, Integer orgPk, Boolean indicadores, Boolean isCopia) throws GeneralException {
        if (prog.getProgPk() == null || prog.getProgFechaAct() == null) {
            prog.setProgFechaAct(new Date());
        } else {
            if (obtenerFechaActualización(prog.getProgPk()) != null) {
                prog.setProgFechaAct(this.obtenerFechaActualización(prog.getProgPk()));
            } else {
                prog.setProgFechaAct(new Date());
            }
        }

        Configuracion cnfAprobPMOF = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PMOT_OMITIR_APROBACION_INICIAL, orgPk);

        boolean omiteAprobacion = cnfAprobPMOF.getCnfValor().equals("true");

        if (prog.getProgPk() == null) {
            prog.setProgFechaCrea(new Date());

            if (usuario.isUsuarioPMOT(orgPk) || isCopia) {
                prog.setProgEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
                prog.setProgEstPendienteFk(null);

            } else if (omiteAprobacion && prog.getProgUsrPmofedFk() != null) {
                prog.setProgEstFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOF.estado_id));
                prog.setProgEstPendienteFk(new Estados(Estados.ESTADOS.PENDIENTE_PMOF.estado_id));
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
        boolean isNew = prog.getProgPk() == null;

        prog = guardar(prog, usuario, orgPk);

        if (isNew || (indicadores != null && indicadores)) {
            progIndicesBean.guardarIndicadores(prog.getProgPk(), orgPk);
        }

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
                LOGGER.log(Level.SEVERE, me.getMessage(), me);
            }
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Programas guardarSinValidar(Programas prog, SsUsuario usuario, String origen) throws GeneralException {
        //"usuario" en guardar entregables con referencia desde Wekan viene vacio
        ProgramasDAO fpDao = new ProgramasDAO(em);
        try {
            prog = fpDao.update(prog, usuario != null ? usuario.getUsuCod() : null, origen);

        } catch (BusinessException be) {
            LOGGER.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", be.getMessage(), be);
            throw be;
        } catch (Exception ex) {
            LOGGER.logp(Level.SEVERE, ProgramasBean.class.getName(), "guardar", ex.getMessage(), ex);
            TechnicalException ge = new TechnicalException(ex);
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
            LOGGER.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException(ex);
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
            LOGGER.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<Programas> obtenerProgComboPorOrg(int orgId, Boolean habilitado) throws GeneralException {
        ProgramasDAO programasDao = new ProgramasDAO(em);
        return programasDao.obtenerProgComboPorOrg(orgId, habilitado);
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
                LOGGER.logp(Level.SEVERE, ProgramasBean.class.getName(), "obtenerProgPorId", ex.getMessage(), ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }
        return null;
    }

    public Programas guardarPrograma(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws GeneralException {
        Programas prog = (Programas) ProgProyUtils.fichaTOToProgProy(fichaTO);

        try {
//            return guardarPrograma(prog, usuario, orgPk);
            return guardarPrograma(prog, usuario, orgPk, Boolean.FALSE);
        } catch (GeneralException ex) {
            LOGGER.log(Level.SEVERE, ProgramasBean.class.getName(), ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ProgramasBean.class.getName(), ex);
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
        prog = guardarPrograma(prog, usuario, orgPk, false);

        if (prog != null && prog.isEstPendienteFk()) {
            String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
            boolean[] ascUsuarios = new boolean[]{true, true, true, true};
            List<SsUsuario> usuariosPMOT = ssUsuarioBean.obtenerUsuariosPorRol(SsRolCodigos.PMO_TRANSVERSAL, orgPk, ordenUsuarios, ascUsuarios);
            String[] destinatarios = SsUsuariosUtils.arrayMailsUsuarios(usuariosPMOT);
            try {
                mailBean.comunicarSolicitudAprobacion(orgPk, prog, destinatarios);
            } catch (MailException me) {
                LOGGER.log(Level.SEVERE, me.getMessage(), me);
            }
        }
        return prog;
    }

    public void actualizarProgramaPorProyectos(Proyectos proy, SsUsuario usuario, Integer orgPk) {
        //"usuario" en guardar entregables con referencia desde Wekan viene vacio
        if (proy.getProyProgFk() == null) {
            return;
        }
        actualizarProgramaPorProyectosSimple(proy.getProyProgFk().getProgPk(), usuario, du.getOrigen());

        calculoIndicadoresAgendadoBean.agendarCalculoIndicadores(proy.getProyProgFk());
    }

    /**
     * Dado un proyecto actualiza el estado del programa al que pertenece y la
     * menor fecha de actualización de sus proyectos.
     *
     * @param progPk
     * @param usuario
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void actualizarProgramaPorProyectos(Integer progPk, SsUsuario usuario, String origen) {
        this.actualizarProgramaPorProyectosSimple(progPk, usuario, du.getOrigen());
    }

    public void actualizarProgramaPorProyectosSimple(Integer progPk, SsUsuario usuario, String origen) {
        //"usuario" en guardar entregables con referencia desde Wekan viene vacio
        if (progPk == null) {
            return;
        }

        Programas prog = this.obtenerProgPorId(progPk);

        ProgramasDAO dao = new ProgramasDAO(em);
        Estados estado = dao.obtenerMenorEstadoProy(progPk);

        if (estado == null) {
            estado = estadosBean.obtenerEstadosPorId(Estados.ESTADOS.INICIO.estado_id);
        }
        prog.setProgEstFk(estado);

        prog.setProgEstPendienteFk(null);

        Date menorActualizacion = dao.obtenerMenorFechaActProy(progPk);
        if (menorActualizacion != null) {
            prog.setProgFechaAct(menorActualizacion);
        }

        this.guardarSinValidar(prog, usuario, du.getOrigen());
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
                proyectoBean.desReferenciarEntregablesRelacionados(proyectos.getProyCroFk(), usuario);
            }

            if (activos) {
                throw new BusinessException(MensajesNegocio.ERROR_ELIMINAR_PROG_PROY_ACTIVOS);
            }
            Programas p = em.find(Programas.class, progPk);
            p.setActivo(false);

            try {
                p = guardarPrograma(p, usuario, orgPk, false);
                //TODO: Notificar al PM, Adjunto y PMOF
                return p;
            } catch (GeneralException ge) {
                throw new BusinessException(MensajesNegocio.ERROR_ELIMINAR_PROGRAMA);
            }
        } else {
            throw new BusinessException(MensajesNegocio.ERROR_USUARIO_NO_PERMISO_ACCION);
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void guardarIndicadoresSimple(Integer progPk, Integer orgPk) {
        progIndicesBean.guardarIndicadoresNewTrans(progPk, orgPk);
    }

    /**
     * Retorna la primer fecha para un progrma dependiendo de las fechas de sus
     * proyectos.
     *
     * @param setProyectos
     * @return Date
     */
    public Date obtenerPrimeraFecha(Set<Proyectos> setProyectos) {
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

    public Date obtenerPrimeraFecha(Integer progPk) {
        // Para hacerlo mas performante se toma como valido que antes de calcular 
        // estos valores ya se guardaron los mismos para los proyectos en el indice del proyecto.
        try {
            ProgramasDAO dao = new ProgramasDAO(em);
            return dao.obtenerPrimeraFecha(progPk);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    /**
     * Retorna la última fecha para un progrma dependiendo de las fechas de sus
     * proyectos.
     *
     * @param setProyectos
     * @return Date
     */
    public Date obtenerUltimaFecha(Integer progPk) {
        // Para hacerlo mas performante se toma como valido que antes de calcular 
        // estos valores ya se guardaron los mismos para los proyectos en el indice del proyecto.
        try {
            ProgramasDAO dao = new ProgramasDAO(em);
            return dao.obtenerUltimaFecha(progPk);
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }

    }

    /**
     * Retorna la última fecha para un progrma dependiendo de las fechas de sus
     * proyectos.
     *
     * @param setProyectos
     * @return Date
     */
    public Date obtenerUltimaFecha(Set<Proyectos> setProyectos) {
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

    /*
        *   17-04-2018  Nico: Esta operación revisa todas las Fechas de Actualización de los proyectos en los programas
        *          para quedarse con la menor, que sería la correspondiente.
     */
    public Date obtenerFechaActualización(Integer progPk) {

        try {
            ProgramasDAO dao = new ProgramasDAO(em);
            Date ret = dao.obtenerFechaActualización(progPk);
            return ret;
        } catch (DAOGeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public List<Programas> obtenerTodosPorOrg(Integer orgPk) {
        ProgramasDAO dao = new ProgramasDAO(em);
        try {
            if (orgPk != null) {
                return dao.findByOneProperty(Programas.class, "progOrgFk.orgPk", orgPk);
            } else {
                return dao.findAll(Programas.class);
            }
        } catch (DAOGeneralException ex) {
            Logger.getLogger(ProgramasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Integer> obtenerTodosIdsPorOrg(Integer orgPk) {
        ProgramasDAO dao = new ProgramasDAO(em);
        try {
            return dao.obtenerTodosIdsOrgFk(orgPk);
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

    public List<Programas> obtenerProgramas(String codigo, String nombre, Integer orgPk) throws GeneralException {
        try {
            return new ProgramasDAO(em).obtenerProgramas(codigo, nombre, orgPk);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    public void habilitarDeshabilitarPrograma(Integer progPk, Boolean habilitado, SsUsuario u, Integer orgPk) throws GeneralException {
        try {
            Programas p = this.obtenerProgPorId(progPk);
            p.setProgHabilitado(habilitado);
            p.setProgFechaAct(new Date());
            ProgramasDAO fpDao = new ProgramasDAO(em);
            fpDao.update(p);
            mailPostGuardar(p, orgPk);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    public Programas guardarCopiaPrograma(Integer progPk, String nombre, SsUsuario usu, Integer orgPk) {
        if (StringsUtils.isEmpty(nombre)) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROG_NOMBRE);
            throw be;
        }

        if (orgPk == null) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROG_ORG);
            throw be;
        }

        Programas p = obtenerProgPorId(progPk);
        if (p == null) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_COPIA_PROG_NULL);
            throw be;
        }

        Programas copiaProg = copiarPrograma(p, nombre);
        try {
            copiaProg = guardarPrograma(copiaProg, usu, orgPk, true);

        } catch (GeneralException ge) {
            BusinessException be = new BusinessException(ge);
            be.addError(MensajesNegocio.ERROR_COPIA_PROG_GUARDAR);
            throw be;
        }

//		tipoDocumentoInstanciaBean.guardarCopiaTDIProyecto(proyPk, copiaProy.getProyPk(), orgPk);
        //Notificaciones
//		notificacionInstanciaBean.guardarCopiaNIProyecto(proyPk, copiaProy.getProyPk(), orgPk);
        return copiaProg;
    }

    public Programas copiarPrograma(Programas progOrigen, String nombre) {

        Programas nvoProg = (Programas) progOrigen.clone();

        nvoProg.setActivo(Boolean.TRUE);

        Set<AreasTags> areasTags = new HashSet();
        for (AreasTags at : progOrigen.getAreasTematicasSet()) {
            areasTags.add(at);
        }
        nvoProg.setAreasTematicasSet(areasTags);

        Set<Areas> areas = new HashSet();
        for (Areas a : progOrigen.getAreasRestringidasSet()) {
            areas.add(a);
        }
        nvoProg.setAreasRestringidasSet(areas);

        nvoProg.setDocumentosSet(null);    // QUE HACEN LOS DOCUMENTOS
        nvoProg.setInteresadosList(null);  // QUE HACEN LOS INTERESADOS
//                nvoProg.setObjetivoEstrategico(); 
//                nvoProg.setProgAreaFk(progAreaFk);
        nvoProg.setProgCroFk(null);
//                nvoProg.setProgDescripcion();
        nvoProg.setProgEstFk(new Estados(Estados.ESTADOS.INICIO.estado_id));
        nvoProg.setProgEstPendienteFk(null);
//                nvoProg.setProgFactorImpacto();  QUE ES FACTOR IMPACTO
        Date date = new Date();
        nvoProg.setProgFechaAct(date);
        nvoProg.setProgFechaCrea(date);
        nvoProg.setProgGrp(null);
        nvoProg.setProgHabilitado(Boolean.TRUE);
        nvoProg.setProgIndices(null);
        nvoProg.setProgNombre(nombre);
//                nvoProg.setProgObjPublico();
//                nvoProg.setProgObjetivo();
//                nvoProg.setProgOrgFk();
        nvoProg.setProgPk(null);
        nvoProg.setProgPreFk(null);
//                nvoProg.setProgSemaforoAmarillo();
//                nvoProg.setProgSemaforoRojo();
        nvoProg.setProgUltMod(null);
        nvoProg.setProgUltOrigen(null);
        nvoProg.setProgUltUsuario(null);
//                nvoProg.setProgUsrAdjuntoFk();
//                nvoProg.setProgUsrGerenteFk();
//                nvoProg.setProgUsrPmofedFk();
//                nvoProg.setProgUsrSponsorFk();
        nvoProg.setProgVersion(0);
        nvoProg.setProyectosSet(null);

        return nvoProg;
    }
}
