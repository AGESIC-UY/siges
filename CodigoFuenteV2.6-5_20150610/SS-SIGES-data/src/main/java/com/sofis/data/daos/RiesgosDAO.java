package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Riesgos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class RiesgosDAO extends HibernateJpaDAOImp<Riesgos, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public RiesgosDAO(EntityManager em) {
        super(em);
    }

    public Date obtenerDateUltimaActualizacion(Integer fichaFk) {
        logger.fine("Obtener la fecha de la ultima actualizacion de Riesgos. ".concat(new Date().toString()));

        String queryStr = "SELECT r.riskFechaActualizacion"
                + " FROM com.sofis.entities.data.Riesgos r"
                + " WHERE r.riskProyFk.proyPk = :proyFk"
                + " AND (r.riskSuperado IS NULL OR r.riskSuperado = :superado)"
                + " ORDER BY r.riskFechaActualizacion DESC, r.riskPk DESC";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyFk", fichaFk);
        query.setParameter("superado", false);
        query.setMaxResults(1);

        Date toReturn = null;
        List resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            toReturn = ((Date) resultList.get(0));
        }

        return toReturn;
    }

    public List<Riesgos> obtenerRiesgosNoSuperados(Integer proyPk) {
        logger.fine("Obtener Riesgos No superados(".concat(proyPk.toString()).concat("). ").concat(new Date().toString()));

        String queryStr = "SELECT r"
                + " FROM com.sofis.entities.data.Riesgos r"
                + " WHERE ( r.riskSuperado is null OR r.riskSuperado <> true )"
                + " AND r.riskProyFk.proyPk = :proyPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);
        List<Riesgos> resultList = query.getResultList();
//        List<Riesgos> setRiesgos = new HashSet<>();
//        setRiesgos.addAll(resultList);

        return resultList;
    }

    public List<Riesgos> obtenerRiesgosPorProyecto(Integer proyPk) {
        String queryStr = "SELECT r"
                + " FROM com.sofis.entities.data.Riesgos r"
                + " WHERE r.riskProyFk.proyPk = :proyPk"
                + " ORDER BY r.exposicion DESC, r.riskPk DESC";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("proyPk", proyPk);
        List<Riesgos> resultList = query.getResultList();
        return resultList;
    }

    public List<Riesgos> obtenerResumenRiesgos(Integer proyPk, int size) {
        if (proyPk != null) {
            String queryStr = "SELECT r"
                    + " FROM com.sofis.entities.data.Riesgos r"
                    + " WHERE (r.riskSuperado IS NULL OR r.riskSuperado = :superado)"
                    + " AND r.riskProyFk.proyPk = :proyPk"
                    + " ORDER BY r.exposicion DESC, r.riskPk DESC";
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("superado", false);
            query.setParameter("proyPk", proyPk);
            if (size > 0) {
                query.setMaxResults(size);
            }
            List<Riesgos> resultList = query.getResultList();
            return resultList;
        }
        return null;
    }
}