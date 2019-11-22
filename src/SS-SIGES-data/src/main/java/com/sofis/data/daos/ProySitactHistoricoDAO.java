package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
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
    private static final Logger logger = Logger.getLogger(ProySitactHistoricoDAO.class.getName()); 

    public ProySitactHistoricoDAO(EntityManager em) {
        super(em);
    }

    public List<ProySitactHistorico> obtenerSitActHistoricoPorFecha(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h"
                    + " FROM ProySitactHistorico h"
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
            String queryStr = "SELECT h.proySitactFecha"
                    + " FROM ProySitactHistorico h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proySitactFecha DESC, h.proySitactHistPk DESC";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setMaxResults(1);
            try {
                List<Date> result = query.getResultList();
                return (Date) DAOUtils.obtenerSingleResult(result);
            } catch (Exception e) {
                logger.log(Level.SEVERE, null, e);
            }
        }
        return null;
    }

    public ProySitactHistorico obtenerUltimaSitAct(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h"
                    + " FROM ProySitactHistorico h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proySitactFecha DESC, h.proySitactHistPk DESC";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setMaxResults(1);
            try {
                List<ProySitactHistorico> result = query.getResultList();
                return (ProySitactHistorico) DAOUtils.obtenerSingleResult(result);
            } catch (Exception e) {
                logger.log(Level.SEVERE, null, e);
            }
        }
        return null;
    }
}
