package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.tipos.FiltroCausalCompraTO;
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
public class CausalCompraDAO extends HibernateJpaDAOImp<CausalCompra, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(CausalCompraDAO.class.getName());

    public CausalCompraDAO(EntityManager em) {
        super(em);
    }

    public List<CausalCompra> obtenerPorFiltro(FiltroCausalCompraTO filtro) throws DAOGeneralException {
        try {

            List<CriteriaTO> criterias = new ArrayList<CriteriaTO>();

            if (filtro.getId() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "cauComPk", filtro.getId()));
            }

            if (!StringsUtils.isEmpty(filtro.getNombre())) {
                CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("cauComNombre", filtro.getNombre());
                criterias.add(crit);
            }

            if (!StringsUtils.isEmpty(filtro.getDescripcion())) {
                CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("cauComDescripcion", filtro.getDescripcion());
                criterias.add(crit);
            }

            if (filtro.getHabilitado() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "cauComHabilitado", filtro.getHabilitado()));
            }

            if (filtro.getOrganismo() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "cauComOrgFk", filtro.getOrganismo()));
            }

            CriteriaTO criteriaTO;
            if (criterias.size() > 1) {
                criteriaTO = CriteriaTOUtils.createANDTO(criterias.toArray(new CriteriaTO[criterias.size()]));
            } else if (criterias.size() == 1) {
                criteriaTO = criterias.get(0);
            } else {
                criteriaTO = null;
            }

            String[] orderBy = {"cauComNombre"};
            boolean[] ascending = {true};
            return this.findEntityByCriteria(CausalCompra.class, criteriaTO, orderBy, ascending, null, null);

        } catch (DAOGeneralException ex) {
            throw ex;
        }
    }

}
