package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.SqlExecuted;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class SqlExecutedDAO extends HibernateJpaDAOImp<SqlExecuted, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public SqlExecutedDAO(EntityManager em) {
        super(em);
    }

    public boolean wasExecuted(int verMayor, int verMenor, int build, int update) {
        String queryStr = "SELECT sqlPk"
                + " from SqlExecuted"
                + " where sqlVerMayor = :verMayor"
                + " AND sqlVerMenor = :verMenor"
                + " AND sqlBuild = :build"
                + " AND sqlUpdate = :update";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("verMayor", verMayor);
        query.setParameter("verMenor", verMenor);
        query.setParameter("build", build);
        query.setParameter("update", update);
        List listResult = query.getResultList();
        Object o = DAOUtils.obtenerSingleResult(listResult);
        return o != null;
    }
}
