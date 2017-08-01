package com.sofis.data.daos;

import com.sofis.entities.data.Noticia;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class NoticiaDAO extends HibernateJpaDAOImp<Noticia, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public NoticiaDAO(EntityManager em) {
        super(em);
    }

    public List<Noticia> obtenerNoticiasVigentes(Date fecha) {
        String consulta = "select s from Noticia s where (s.notDesde is null or s.notDesde>=:desde) and (s.notHasta is null or s.notHasta<=:hasta)";
        Query query = this.getEm().createQuery(consulta);
        query.setParameter("desde", fecha);
        query.setParameter("hasta", fecha);
        return query.getResultList();
    }
}
