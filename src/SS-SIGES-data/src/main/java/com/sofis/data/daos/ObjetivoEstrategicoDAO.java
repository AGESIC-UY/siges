/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.ObjetivoEstrategico;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.tipos.FiltroObjectivoEstategicoTO;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ObjetivoEstrategicoDAO extends HibernateJpaDAOImp<ObjetivoEstrategico, Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(ObjetivoEstrategicoDAO.class.getName());

	public ObjetivoEstrategicoDAO(EntityManager em) {
		super(em);
	}

	public List<ObjetivoEstrategico> obtenerPorOrganismo(Organismos org) throws DAOGeneralException {
		try {
			return this.findByOneProperty(ObjetivoEstrategico.class, "objEstOrgFk", org, "objEstNombre");
		} catch (DAOGeneralException ex) {
			throw ex;
		}
	}

	public List<ObjetivoEstrategico> obtenerPorFiltro(FiltroObjectivoEstategicoTO filtro) throws DAOGeneralException {
		try {

			List<CriteriaTO> criterias = new ArrayList<CriteriaTO>();
			if (!StringsUtils.isEmpty(filtro.getNombre())) {
//                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "objEstNombre", filtro.getNombre()));
				CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("objEstNombre", filtro.getNombre());
				criterias.add(crit);
			}
			if (filtro.getOrgPk() != null) {
				criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "objEstOrgFk.orgPk", filtro.getOrgPk()));
			}
			if (filtro.getOrganismo() != null) {
				criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "objEstOrgFk", filtro.getOrganismo()));
			}
			if (filtro.getHabilitado() != null) {
				criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "objEstHabilitado", filtro.getHabilitado()));
			}

			CriteriaTO criteriaTO;
			if (criterias.size() > 1) {
				criteriaTO = CriteriaTOUtils.createANDTO(criterias.toArray(new CriteriaTO[criterias.size()]));
			} else {
				criteriaTO = criterias.get(0);
			}

			String[] orderBy = {"objEstNombre"};
			boolean[] ascending = {true};
			return this.findEntityByCriteria(ObjetivoEstrategico.class, criteriaTO, orderBy, ascending, null, null);

		} catch (DAOGeneralException ex) {
			throw ex;
		}
	}

}
