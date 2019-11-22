package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Participantes;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ParticipantesDao extends HibernateJpaDAOImp<Participantes, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ParticipantesDao.class.getName()); 

    public ParticipantesDao(EntityManager em) {
        super(em);
    }

    public boolean tieneDependenciasEnt(Integer entPk) {
        if (entPk != null) {
            String queryStr = "SELECT COUNT(p.partPk) AS cant FROM Participantes p"
                    + " WHERE p.partEntregablesFk.entPk = :entPk";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("entPk", entPk);
            try {
                Long cant = (Long) query.getSingleResult();
                return cant > 0;
            } catch (Exception w) {
                logger.log(Level.SEVERE, null, w);
                return false;
            }
        }
        return false;
    }

}