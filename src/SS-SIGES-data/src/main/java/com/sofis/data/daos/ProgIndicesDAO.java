package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProgIndices;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProgIndicesDAO extends HibernateJpaDAOImp<ProgIndices, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public ProgIndicesDAO(EntityManager em) {
        super(em);
    }

    public ProgIndices obtenerIndicePorProgId(Integer progPk) {
        if (progPk != null) {
            String queryStr = "SELECT p.progIndices FROM Programas p WHERE p.progPk = :progPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("progPk", progPk);
            try {
                List<ProgIndices> result = query.getResultList();
                return (ProgIndices) DAOUtils.obtenerSingleResult(result);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
