package com.sofis.data.daos;

import com.sofis.entities.data.Interesados;
import com.sofis.entities.enums.TipoFichaEnum;
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
public class InteresadosDao extends HibernateJpaDAOImp<Interesados, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(InteresadosDao.class.getName());          

    public InteresadosDao(EntityManager em) {
        super(em);
    }

    public List<Interesados> obtenerResumenInteresados(Integer fichaPk, Integer tipoFicha, int size) {
        if (fichaPk != null && tipoFicha != null) {
            Query query = null;

            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                String queryStr = "SELECT i FROM Programas p, IN(p.interesadosList) i"
                        + " WHERE p.progPk = :fichaPk"
                        + " ORDER BY i.intPk ASC";
                query = super.getEm().createQuery(queryStr);
                query.setParameter("fichaPk", fichaPk);

            } else {
                String queryStr = "SELECT i FROM Proyectos p, IN(p.interesadosList) i"
                        + " WHERE p.proyPk = :fichaPk"
                        + " ORDER BY i.intPk ASC";
                query = super.getEm().createQuery(queryStr);
                query.setParameter("fichaPk", fichaPk);
            }

            if (size > 0) {
                query.setMaxResults(size);
            }
            List<Interesados> resultList = query.getResultList();
            return resultList;
        }
        return null;
    }

    public List<Interesados> obtenerIntersadosPorFichaPk(Integer fichaPk, Integer tipoFicha) {
        if (fichaPk != null && tipoFicha != null) {
            Query query = null;

            if (tipoFicha.equals(TipoFichaEnum.PROGRAMA.id)) {
                String queryStr = "SELECT i FROM Programas p, IN(p.interesadosList) i"
                        + " WHERE p.progPk = :fichaPk"
                        + " ORDER BY i.intPk ASC";
                query = super.getEm().createQuery(queryStr);
                query.setParameter("fichaPk", fichaPk);

            } else {
                String queryStr = "SELECT i FROM Proyectos p, IN(p.interesadosList) i"
                        + " WHERE p.proyPk = :fichaPk"
                        + " ORDER BY i.intPk ASC";
                query = super.getEm().createQuery(queryStr);
                query.setParameter("fichaPk", fichaPk);
            }

            List<Interesados> resultList = query.getResultList();
            return resultList;
        }
        return null;
    }
}