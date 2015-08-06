package com.sofis.business.ejbs;

import com.sofis.data.daos.ProgramasDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroReporteTO;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.sofisform.to.OR_TO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author Usuario
 */
@Named
@Stateless(name = "ReportesBean")
@LocalBean
public class ReportesBean {
    
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private ProyectosBean proyectosBean;
    
    /**
     * Busqueda de Proyectos para los reportes.
     * @param filtro
     * @param orgPk
     * @param usuario
     * @return List
     */
    public List<Proyectos> buscarProyectosPorFiltro(FiltroReporteTO filtro, Integer orgPk, SsUsuario usuario) {
        Set<Proyectos> setProyectos = new HashSet<>();

        List<Programas> programas = buscarProgPorFiltro(orgPk, usuario, filtro);
        if (programas != null) {
            for (Programas prog : programas) {
                setProyectos.addAll(proyectosBean.obtenerProyPorProgId(prog.getProgPk()));
            }
        }

        List<Proyectos> proyectos = buscarProyPorFiltro(orgPk, usuario, filtro);
        if (proyectos != null) {
            setProyectos.addAll(proyectos);
        }

        return new ArrayList<>(setProyectos);
    }

    private List<Programas> buscarProgPorFiltro(Integer orgPk, SsUsuario usuario, FiltroReporteTO filtro) {
        List<CriteriaTO> criterios = new ArrayList<>();

        //Activo
        MatchCriteriaTO activo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "activo", !Boolean.FALSE);
        criterios.add(activo);

        // Organismo
        MatchCriteriaTO orga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progOrgFk.orgPk", orgPk);
        criterios.add(orga);

        // Permisos de Lectura
        if (!usuario.isUsuarioPMOT(orgPk)
                && usuario.getUsuArea(orgPk) != null) {
            CriteriaTO UsuPM = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "progUsrGerenteFk.usuId", usuario.getUsuId());
            CriteriaTO UsuAdjunto = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "progUsrAdjuntoFk.usuId", usuario.getUsuId());
            CriteriaTO UsuSponsor = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "progUsrSponsorFk.usuId", usuario.getUsuId());
            CriteriaTO UsuPMOF = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "progUsrPmofedFk.usuId", usuario.getUsuId());
            CriteriaTO criteriaUsu = CriteriaTOUtils.createORTO(UsuPM, UsuAdjunto, UsuSponsor, UsuPMOF);
            criterios.add(criteriaUsu);

            CriteriaTO proyAreaPermiso1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EMPTY, "areasRestringidasSet", 1);
            CriteriaTO proyAreaPermiso2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "areasRestringidasSet.areaPk", usuario.getUsuArea(orgPk).getAreaPk());
            CriteriaTO orPermiso = CriteriaTOUtils.createORTO(proyAreaPermiso1, proyAreaPermiso2);
            criterios.add(orPermiso);
        }

        // Nombre
        if (!StringsUtils.isEmpty(filtro.getNombre())) {
            MatchCriteriaTO crit = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "progNombre", filtro.getNombre());
            criterios.add(crit);
        }

        // Estados Filtro
        List<Object> estados;
        if (filtro.getEstados() != null && filtro.getEstados().size() > 0) {
            estados = filtro.getEstados();
        } else {
            estados = new ArrayList<>();
            estados.add(Estados.ESTADOS.INICIO.estado_id);
            estados.add(Estados.ESTADOS.PLANIFICACION.estado_id);
            estados.add(Estados.ESTADOS.EJECUCION.estado_id);
            estados.add(Estados.ESTADOS.FINALIZADO.estado_id);
        }

        // Estados
        if (estados != null) {
            List<CriteriaTO> estadosCriteria = new ArrayList<>();
            for (Object estadoId : estados) {
                Integer estadoIdInt = Integer.parseInt(estadoId + "");
                MatchCriteriaTO estado2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progEstFk.estPk", estadoIdInt);
                estadosCriteria.add(estado2);
            }

            if (estadosCriteria.size() > 1) {
                criterios.add(CriteriaTOUtils.createORTO(estadosCriteria.toArray(new CriteriaTO[]{})));
            } else if (!estadosCriteria.isEmpty()) {
                criterios.add(estadosCriteria.get(0));
            }
        }

        // Area
        if (filtro.getArea() != null) {
            MatchCriteriaTO area = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progAreaFk.areaPk", filtro.getArea().getAreaPk());
            criterios.add(area);
        }

        // Gerente o Adjunto
        if (filtro.getGerenteAdjunto() != null) {
            MatchCriteriaTO gerente = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progUsrGerenteFk.usuId", filtro.getGerenteAdjunto().getUsuId());
            MatchCriteriaTO adjunto = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progUsrAdjuntoFk.usuId", filtro.getGerenteAdjunto().getUsuId());
            OR_TO orCriteria = CriteriaTOUtils.createORTO(gerente, adjunto);
            criterios.add(orCriteria);
        }

        // Sponsor
        if (filtro.getSponsor() != null) {
            MatchCriteriaTO crit = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progUsrSponsorFk.usuId", filtro.getSponsor().getUsuId());
            criterios.add(crit);
        }

        // PMO Federada
        if (filtro.getPmof() != null) {
            MatchCriteriaTO crit = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "progUsrPmofedFk.usuId", filtro.getPmof().getUsuId());
            criterios.add(crit);
        }

        //Area tematica
        if (filtro.getAreasTematicas() != null) {
            List<CriteriaTO> areaTemCriteria = new ArrayList<>();
            for (AreasTags areaTem : filtro.getAreasTematicas()) {
                MatchCriteriaTO areaTag = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "areasTematicasSet.arastagPk", areaTem.getArastagPk());
                areaTemCriteria.add(areaTag);
            }

            if (areaTemCriteria.size() > 1) {
                criterios.add(CriteriaTOUtils.createORTO(areaTemCriteria.toArray(new CriteriaTO[]{})));
            } else {
                criterios.add(areaTemCriteria.get(0));
            }
        }

        CriteriaTO criteria;
        if (criterios.size() == 1) {
            criteria = criterios.get(0);
        } else {
            criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
        }

        ProgramasDAO progDao = new ProgramasDAO(em);
        List<Programas> programasResult = null;
        try {
            programasResult = progDao.findEntityByCriteria(Programas.class, criteria, new String[]{}, new boolean[]{}, null, null);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return programasResult;
    }

    private List<Proyectos> buscarProyPorFiltro(Integer orgPk, SsUsuario usuario, FiltroReporteTO filtro) {

        List<CriteriaTO> criterios = new ArrayList<>();

        // Organismo
        MatchCriteriaTO proyOrga = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyOrgFk.orgPk", orgPk);
        criterios.add(proyOrga);

        // Activo
        MatchCriteriaTO activo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "activo", !Boolean.FALSE);
        criterios.add(activo);

        // Permisos de Lectura
        if (!usuario.isUsuarioPMOT(orgPk)) {
            CriteriaTO proyUsuPM = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "proyUsrGerenteFk.usuId", usuario.getUsuId());
            CriteriaTO proyUsuAdjunto = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "proyUsrAdjuntoFk.usuId", usuario.getUsuId());
            CriteriaTO proyUsuSponsor = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "proyUsrSponsorFk.usuId", usuario.getUsuId());
            CriteriaTO proyUsuPMOF = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_EQUALS, "proyUsrPmofedFk.usuId", usuario.getUsuId());
            CriteriaTO criteriaUsu = CriteriaTOUtils.createORTO(proyUsuPM, proyUsuAdjunto, proyUsuSponsor, proyUsuPMOF);

            CriteriaTO proyAreaPermiso1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EMPTY, "areasRestringidasSet", 1);
            CriteriaTO proyAreaPermiso2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_MEMBER_OF, "areasRestringidasSet", usuario.getUsuArea(orgPk));
            CriteriaTO orProyPermiso = CriteriaTOUtils.createORTO(proyAreaPermiso1, proyAreaPermiso2);

            criterios.add(new AND_TO(criteriaUsu, orProyPermiso));
        }

        // Nombre
        if (!StringsUtils.isEmpty(filtro.getNombre())) {
            MatchCriteriaTO nombre1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "proyNombre", filtro.getNombre());
            criterios.add(nombre1);
        }

        // Estados Filtro
        List<Object> estados;
        if (filtro.getEstados() != null && filtro.getEstados().size() > 0) {
            estados = filtro.getEstados();
        } else {
            estados = new ArrayList<>();
            estados.add(Estados.ESTADOS.INICIO.estado_id);
            estados.add(Estados.ESTADOS.PLANIFICACION.estado_id);
            estados.add(Estados.ESTADOS.EJECUCION.estado_id);
            estados.add(Estados.ESTADOS.FINALIZADO.estado_id);
        }

        // Estados
        if (estados != null) {
            List<CriteriaTO> estadosCriteria = new ArrayList<>();
            for (Object estadoId : estados) {
                Integer estadoIdInt = Integer.parseInt(estadoId + "");
                MatchCriteriaTO estado2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyEstFk.estPk", estadoIdInt);
                estadosCriteria.add(estado2);
            }

            if (estadosCriteria.size() > 1) {
                criterios.add(CriteriaTOUtils.createORTO(estadosCriteria.toArray(new CriteriaTO[]{})));
            } else if (!estadosCriteria.isEmpty()) {
                criterios.add(estadosCriteria.get(0));
            }
        }

        //procedimiento; 
        if (!StringsUtils.isEmpty(filtro.getProcedimiento())) {
            MatchCriteriaTO proc = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyPreFk.adquisicionSet.adqProcCompra", filtro.getProcedimiento());
            criterios.add(proc);
        }

        //procedimientoGRP;
        if (!StringsUtils.isEmpty(filtro.getProcedimientoGRP())) {
            MatchCriteriaTO proc = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyPreFk.adquisicionSet.adqProcCompraGrp", filtro.getProcedimientoGRP());
            criterios.add(proc);
        }

        // Area
        if (filtro.getArea() != null) {
            MatchCriteriaTO proyArea = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyAreaFk.areaPk", filtro.getArea().getAreaPk());
            criterios.add(proyArea);
        }

        // Gerente o Adjunto
        if (filtro.getGerenteAdjunto() != null && !filtro.getGerenteAdjunto().equals(-1)) {
            MatchCriteriaTO gerente1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyUsrGerenteFk.usuId", filtro.getGerenteAdjunto().getUsuId());
            MatchCriteriaTO adjunto1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyUsrAdjuntoFk.usuId", filtro.getGerenteAdjunto().getUsuId());
            OR_TO orCriteria1 = new OR_TO(gerente1, adjunto1);
            criterios.add(orCriteria1);
        }

        // Sponsor
        if (filtro.getSponsor() != null && !filtro.getSponsor().equals(-1)) {
            MatchCriteriaTO nombre = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyUsrSponsorFk.usuId", filtro.getSponsor().getUsuId());
            criterios.add(nombre);
        }

        // PMO Federada
        if (filtro.getPmof() != null) {
            MatchCriteriaTO nombre = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyUsrPmofedFk.usuId", filtro.getPmof().getUsuId());
            criterios.add(nombre);
        }

        //Area tematica
        if (filtro.getAreasTematicas() != null && !filtro.getAreasTematicas().isEmpty()) {
            List<CriteriaTO> areaTemCriteria = new ArrayList<>();
            for (AreasTags areaTem : filtro.getAreasTematicas()) {
                MatchCriteriaTO areaTag1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "areasTematicasSet.arastagPk", areaTem.getArastagPk());
                areaTemCriteria.add(areaTag1);
            }

            if (areaTemCriteria.size() > 1) {
                criterios.add(CriteriaTOUtils.createORTO(areaTemCriteria.toArray(new CriteriaTO[]{})));
            } else {
                criterios.add(areaTemCriteria.get(0));
            }
        }

        //Presupuesto Proveedor
        if (filtro.getProveedor() != null) {
            MatchCriteriaTO orgProv = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyPreFk.adquisicionSet.adqProvOrga.orgaPk", filtro.getProveedor().getOrgaPk());
            criterios.add(orgProv);
        }

        //Presupuesto Fuente
        if (filtro.getFuenteFinanc() != null) {
            MatchCriteriaTO fuente1 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyPreFk.fuenteFinanciamiento.fuePk", filtro.getFuenteFinanc().getFuePk());
            MatchCriteriaTO fuente2 = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyPreFk.adquisicionSet.adqFuente.fuePk", filtro.getFuenteFinanc().getFuePk());
            CriteriaTO fuenteA = CriteriaTOUtils.createORTO(fuente1, fuente2);
            criterios.add(fuenteA);
        }

        //Moneda
        if (filtro.getMoneda() != null) {
            MatchCriteriaTO mon = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyPreFk.adquisicionSet.adqMoneda.monPk", filtro.getMoneda().getMonPk());
            criterios.add(mon);
        }

        CriteriaTO criteria;
        if (criterios.size() == 1) {
            criteria = criterios.get(0);
        } else {
            criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
        }

        ProyectosDAO proyDao = new ProyectosDAO(em);
        List<Proyectos> proyectosResult = null;
        try {
            proyectosResult = proyDao.findEntityByCriteria(Proyectos.class, criteria, new String[]{}, new boolean[]{}, null, null);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return proyectosResult;
    }
}
