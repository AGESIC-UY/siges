package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProySitactHistorico;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class ProySitactHistoricoDAO extends HibernateJpaDAOImp<ProySitactHistorico, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public ProySitactHistoricoDAO(EntityManager em) {
        super(em);
    }

    public List<ProySitactHistorico> obtenerSitActHistoricoPorFecha(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h FROM ProySitactHistorico h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proySitactFecha DESC, h.proySitactHistPk DESC";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            try {
                return query.getResultList();
            } catch (Exception e) {
                logger.log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public Date obtenerUltimaFechaSitAct(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h.proySitactFecha FROM ProySitactHistorico h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proySitactFecha DESC, h.proySitactHistPk DESC";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setMaxResults(1);
            try {
                return (Date) query.getSingleResult();
            } catch (Exception e) {
                logger.logp(Level.SEVERE, ProySitactHistoricoDAO.class.getName(), "obtenerUltimaFechaSitAct", e.getMessage());
            }
        }
        return null;
    }

    public ProySitactHistorico obtenerUltimaSitAct(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h FROM ProySitactHistorico h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proySitactFecha DESC";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setMaxResults(1);
            try {
                return (ProySitactHistorico) query.getSingleResult();
            } catch (Exception e) {
                logger.logp(Level.SEVERE, ProySitactHistoricoDAO.class.getName(), "obtenerUltimaSitAct", e.getMessage());
            }
        }
        return null;
    }
}
