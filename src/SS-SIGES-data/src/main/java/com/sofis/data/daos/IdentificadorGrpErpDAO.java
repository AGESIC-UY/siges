package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.IdentificadorGrpErp;
import com.sofis.entities.tipos.FiltroIdentificadorGrpErpTO;
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
public class IdentificadorGrpErpDAO extends HibernateJpaDAOImp<IdentificadorGrpErp, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private final Logger logger = Logger.getLogger(IdentificadorGrpErpDAO.class.getName());

    public IdentificadorGrpErpDAO(EntityManager em) {
        super(em);
    }

    public List<IdentificadorGrpErp> obtenerPorFiltro(FiltroIdentificadorGrpErpTO filtro) throws DAOGeneralException {
        try {

            List<CriteriaTO> criterias = new ArrayList<CriteriaTO>();

            if (filtro.getId() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "idGrpErpPk", filtro.getId()));
            }

            if (!StringsUtils.isEmpty(filtro.getNombre())) {
//                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "cenCosNombre", filtro.getNombre()));
                CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("idGrpErpNombre", filtro.getNombre());
                criterias.add(crit);
            }

            if (!StringsUtils.isEmpty(filtro.getDescripcion())) {
                CriteriaTO crit = DAOUtils.createMatchCriteriaTOString("idGrpErpDescripcion", filtro.getDescripcion());
                criterias.add(crit);
            }

            if (filtro.getHabilitado() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "idGrpErpHabilitado", filtro.getHabilitado()));
            }
            
            if (filtro.getOrganismo() != null) {
                criterias.add(CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.EQUALS, "idGrpErpOrgFk", filtro.getOrganismo()));                
            }

            CriteriaTO criteriaTO;
            if (criterias.size() > 1) {
                criteriaTO = CriteriaTOUtils.createANDTO(criterias.toArray(new CriteriaTO[criterias.size()]));
            } else if (criterias.size() == 1) {
                criteriaTO = criterias.get(0);
            } else {
                criteriaTO = null;
            }

            String[] orderBy = {"idGrpErpNombre"};
            boolean[] ascending = {true};
            return this.findEntityByCriteria(IdentificadorGrpErp.class, criteriaTO, orderBy, ascending, null, null);

        } catch (DAOGeneralException ex) {
            throw ex;
        }
    }

}
