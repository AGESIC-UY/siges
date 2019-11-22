package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyPublicaHist;
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
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class ProyPublicaHistDAO extends HibernateJpaDAOImp<ProyPublicaHist, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProyPublicaHistDAO.class.getName());   

    public ProyPublicaHistDAO(EntityManager em) {
        super(em);
    }

    public List<ProyPublicaHist> obtenerHistoricoPorFecha(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h"
                    + " FROM ProyPublicaHist h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proyPublicaFecha DESC, h.proyPublicaPk DESC";
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

    public Date obtenerUltimaFecha(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h.proyPublicaFecha"
                    + " FROM ProyPublicaHist h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proyPublicaFecha DESC, h.proyPublicaPk DESC";
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

    public ProyPublicaHist obtenerUltimo(Integer proyPk) {
        if (proyPk != null) {
            String queryStr = "SELECT h"
                    + " FROM ProyPublicaHist h"
                    + " WHERE h.proyectoFk.proyPk = :proyPk"
                    + " ORDER BY h.proyPublicaFecha DESC, h.proyPublicaPk DESC";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("proyPk", proyPk);
            query.setMaxResults(1);
            try {
                List<ProyPublicaHist> result = query.getResultList();
                return (ProyPublicaHist) DAOUtils.obtenerSingleResult(result);
            } catch (Exception e) {
                logger.logp(Level.SEVERE, ProySitactHistoricoDAO.class.getName(), "obtenerUltimaSitAct", e.getMessage());
            }
        }
        return null;
    }
}
