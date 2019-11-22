package com.sofis.data.daos;

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
 * @author Usuario
 */
public class CodigueraDAO<T> extends HibernateJpaDAOImp<T, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CodigueraDAO.class.getName());    

    public CodigueraDAO(EntityManager em) {
        super(em);
    }

    public List<T> obtenerHabilitados(Class c, String campoHabilitado, String campoDescripcion) throws DAOGeneralException {
        List<CriteriaTO> criterios = new ArrayList<CriteriaTO>();
        MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.EQUALS, campoHabilitado,
                Boolean.TRUE);
        criterios.add(criterio);

        CriteriaTO condicion = criterios.get(0);
        String[] orderBy = {campoDescripcion};
        boolean[] asc = {true};
        return findEntityByCriteria(c, condicion, orderBy, asc, 0L, 1000L);
    }
}
