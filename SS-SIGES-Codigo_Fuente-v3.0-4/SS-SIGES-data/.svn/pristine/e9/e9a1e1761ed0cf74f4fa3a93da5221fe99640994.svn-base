package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyIndices;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProyIndicesDAO extends HibernateJpaDAOImp<ProyIndices, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public ProyIndicesDAO(EntityManager em) {
        super(em);
    }

    public ProyIndices obtenerIndicePorProyId(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT p.proyIndices FROM Proyectos p WHERE p.proyPk = :proyPk";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            try {

                List<ProyIndices> result = query.getResultList();
                return (ProyIndices) DAOUtils.obtenerSingleResult(result);

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                return null;
            }
        }
        return null;
    }
}
