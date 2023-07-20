package com.sofis.business.ejbs;

import com.sofis.data.daos.EntHistLineaBaseDAO;
import com.sofis.data.daos.ProyReplanificacionDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.EntHistLineaBase;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.ProyReplanificacion;
import com.sofis.entities.tipos.ReplanificacionTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.AND_TO;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.Date;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Named
@Stateless(name = "ProyReplanificacionBean")
@LocalBean
public class ProyReplanificacionBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;
	
	@Inject
	private DatosUsuario du;
	
	private static final Logger logger = Logger.getLogger(ProyReplanificacionBean.class.getName());

	public ProyReplanificacion guardar(ProyReplanificacion proyReplan) throws GeneralException {
		if (proyReplan.getProyreplanFecha() == null) {
			proyReplan.setProyreplanFecha(new Date());
		}

		ProyReplanificacionDAO dao = new ProyReplanificacionDAO(em);
		try {
			return dao.update(proyReplan, du.getCodigoUsuario(), du.getOrigen());
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage());
			throw new TechnicalException(ex.getMessage());
		}
	}

	public ProyReplanificacion obtenerUltimaSolicitud(Integer fichaFk) {
		ProyReplanificacionDAO dao = new ProyReplanificacionDAO(em);
		CriteriaTO criteria = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyectoFk.proyPk", fichaFk);
		try {
			List<ProyReplanificacion> result = dao.findEntityByCriteria(ProyReplanificacion.class, criteria, new String[]{"proyreplanFecha", "proyreplanPk"}, new boolean[]{false, false}, null, 1L);
			if (result != null && !result.isEmpty()) {
				return result.get(0);
			} else {
				return null;
			}
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage());
			throw new BusinessException(ex.getMessage());
		}
	}

	public ReplanificacionTO obtenerUltimaReplanificacionPorIdProyecto(Integer idProyecto) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<ReplanificacionTO> cq = cb.createQuery(ReplanificacionTO.class);

		Root<ProyReplanificacion> root = cq.from(ProyReplanificacion.class);

		cq.select(cb.construct(ReplanificacionTO.class,
				root.get("proyreplanPk"),
				root.get("proyreplanDesc"),
				root.get("proyreplanFecha"),
				root.get("proyectoFk").get("proyPk"),
				root.get("proyreplanHistorial"),
				root.get("proyreplanActivo"),
				root.get("proyreplanPermitEditar"),
				root.get("proyreplanGenerarLineaBase"),
				root.get("proyreplanGenerarPresupuesto"),
				root.get("proyreplanGenerarProducto")));

		cq.where(cb.equal(root.get("proyectoFk").get("proyPk"), idProyecto));

		cq.orderBy(cb.desc(root.get("proyreplanFecha")), cb.desc(root.get("proyreplanPk")));

		List<ReplanificacionTO> result = em.createQuery(cq)
				.setMaxResults(1)
				.getResultList();

		return result.isEmpty() ? null : result.get(0);
	}

	public ProyReplanificacion obtenerSolicitudActiva(Integer fichaFk) {
		ProyReplanificacionDAO dao = new ProyReplanificacionDAO(em);
		CriteriaTO criteriaProy = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyectoFk.proyPk", fichaFk);
		CriteriaTO criteriaActivo = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyreplanActivo", Boolean.TRUE);
		CriteriaTO criteria = new AND_TO(criteriaProy, criteriaActivo);
		try {
			List<ProyReplanificacion> result = dao.findEntityByCriteria(ProyReplanificacion.class, criteria, new String[]{"proyreplanFecha", "proyreplanPk"}, new boolean[]{false, false}, null, 1L);
			return result != null && !result.isEmpty() ? result.get(0) : null;
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, ex.getMessage());
			throw new BusinessException(ex.getMessage());
		}
	}

	public void inactivarSolicitud(Integer proyPk) {
		ProyReplanificacion replan = obtenerSolicitudActiva(proyPk);
		if (replan != null) {
			replan.setProyreplanActivo(false);
			guardar(replan);
		}
	}

	public EntHistLineaBase guardarHistLineaBase(EntHistLineaBase entHist) {
		EntHistLineaBaseDAO dao = new EntHistLineaBaseDAO(em);
		try {
			dao.update(entHist);
		} catch (DAOGeneralException ex) {
			logger.log(Level.SEVERE, null, ex);
			TechnicalException te = new TechnicalException(ex);
			te.addError(ex.getMessage());
			throw te;
		}
		return entHist;
	}

	public void guardarHistLineaBase(Set<Entregables> entSet) {
		Date date = new Date();
		for (Entregables ent : entSet) {
			EntHistLineaBase entHist = new EntHistLineaBase();
			entHist.setEnthistEntregableFk(ent);
			entHist.setEnthistInicioLineaBase(ent.getEntInicioLineaBase());
			entHist.setEnthistFinLineaBase(ent.getEntFinLineaBase());
			entHist.setEnthistDuracion(DatesUtils.diasEntreFechas(entHist.getEnthistInicioLineaBaseDate(), entHist.getEnthistFinLineaBaseDate()));
			entHist.setEnthistFecha(date);

			guardarHistLineaBase(entHist);
		}
	}

	public List<ProyReplanificacion> obtenerReplanPorProyPk(Integer proyPk) {
		ProyReplanificacionDAO dao = new ProyReplanificacionDAO(em);
		try {
			return dao.findByOneProperty(ProyReplanificacion.class, "proyectoFk.proyPk", proyPk, "proyreplanFecha");
		} catch (DAOGeneralException ex) {
			Logger.getLogger(ProyReplanificacionBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public List<ProyReplanificacion> obtenerReplanHistPorProyPk(Integer proyPk) {
		ProyReplanificacionDAO dao = new ProyReplanificacionDAO(em);

		MatchCriteriaTO critProy = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyectoFk.proyPk", proyPk);
		MatchCriteriaTO critHist = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "proyreplanHistorial", Boolean.TRUE);
		CriteriaTO criteria = CriteriaTOUtils.createANDTO(critProy, critHist);
		try {
			return dao.findEntityByCriteria(ProyReplanificacion.class, criteria, new String[]{"proyreplanFecha", "proyreplanPk"}, new boolean[]{false, false}, null, null);
		} catch (DAOGeneralException ex) {
			Logger.getLogger(ProyReplanificacionBean.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
