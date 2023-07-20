package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.AreasTags;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.FiltroAreaTematicaTO;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AreasTagsDAO extends HibernateJpaDAOImp<AreasTags, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public AreasTagsDAO(EntityManager em) {
		super(em);
	}

	public List<AreasTags> obtenerAreasTematicasPorFichaPk(int fichaFk, Integer tipoFicha) throws DAOGeneralException {
		String queryStr = null;
		if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
			queryStr = "SELECT d FROM Programas p, IN(p.areasTematicasSet) d"
					+ " WHERE p.progPk = :fichaFk"
					+ " ORDER BY d.areatagNombre";

		} else if (tipoFicha.equals(TipoFichaEnum.PROYECTO.id)) {
			queryStr = "SELECT d FROM Proyectos p, IN(p.areasTematicasSet) d"
					+ " WHERE p.proyPk = :fichaFk"
					+ " ORDER BY d.areatagNombre";
		}

		Query query = super.getEm().createQuery(queryStr);
		query.setParameter("fichaFk", fichaFk);

		return query.getResultList();
	}

	public List<AreasTags> buscar(FiltroAreaTematicaTO filtro, String elementoOrdenacion, boolean ascendente) throws DAOGeneralException {

		List<CriteriaTO> criterios = new ArrayList<>();

		if (filtro.getId() != null) {

			criterios.add(CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.EQUALS, "arastagPk", filtro.getId()));
		}

		if (filtro.getIdOrganismo() != null) {

			criterios.add(CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.EQUALS, "areatagOrgFk.orgPk", filtro.getIdOrganismo()));
		}

		if (!StringsUtils.isEmpty(filtro.getNombre())) {
			criterios.add(DAOUtils.createMatchCriteriaTOString("areatagNombre", filtro.getNombre()));
		}

		if (filtro.getHabilitada() != null) {

			criterios.add(CriteriaTOUtils.createMatchCriteriaTO(
					MatchCriteriaTO.types.EQUALS, "habilitada", filtro.getHabilitada()));
		}

		CriteriaTO condicion;
		if (criterios.size() == 1) {
			condicion = criterios.get(0);
		} else {
			condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
		}

		String[] orderBy = {};
		boolean[] asc = {};
		if (!StringsUtils.isEmpty(elementoOrdenacion)) {
			orderBy = new String[]{elementoOrdenacion};
			asc = new boolean[]{ascendente};
		}

		return findEntityByCriteria(AreasTags.class, condicion, orderBy, asc, null, null);
	}

	public List<AreasTags> obtenerHijas(Integer arastagPk) {
		
		return em.createNamedQuery("AreasTags.findHijas", AreasTags.class)
				.setParameter("idPadre", arastagPk)
				.getResultList();
	}
}
