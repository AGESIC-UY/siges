package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.EntHistLineaBase;
import com.sofis.entities.data.Entregables;
import com.sofis.exceptions.TechnicalException;
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
public class EntHistLineaBaseDAO extends HibernateJpaDAOImp<EntHistLineaBase, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public EntHistLineaBaseDAO(EntityManager em) {
        super(em);
    }

    public List<EntHistLineaBase> obtenerEntHistPorProyPk(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT e FROM Proyectos p, Cronogramas c, Entregables e, EntHistLineaBase h"
                    + " WHERE p.proyPk = :proyPk"
                    + " AND p.proyCroFk.croPk = c.croPk"
                    + " AND c.croPk = e.entCroFk.croPk"
                    + " AND h.enthistEntregableFk.entPk = e.entPk";
            Query q = super.getEm().createQuery(queryStr);
            q.setParameter("proyPk", proyPk);
            try {
                return q.getResultList();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, "obtenerEntHistPorProyPk", ex.getMessage());
                TechnicalException te = new TechnicalException(ex);
                te.addError(ex.getMessage());
                throw te;
            }
        }
        return null;
    }
    
}
