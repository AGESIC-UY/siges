package com.sofis.data.daos;

import com.sofis.entities.data.AreaConocimiento;
import com.sofis.entities.data.LeccionesAprendidas;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class AreaConocimientoDAO extends HibernateJpaDAOImp<AreaConocimiento, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public AreaConocimientoDAO(EntityManager em) {
        super(em);
    }

    public List<AreaConocimiento> obtenerAreasConPorLeccPk(Integer leccAprPk) {
        LeccionesAprendidas lec = super.getEm().find(LeccionesAprendidas.class, leccAprPk);
        return new ArrayList(lec.getAreaConocimientosSet());
    }

    public List<AreaConocimiento> obtenerSoloPadres(Integer orgPk) {
        String query = "SELECT ac.areaConPadreFk"
                + " FROM AreaConocimiento ac"
                + " WHERE ac.conOrganismo.orgPk = :orgPk"
                + " ORDER BY ac.areaConPadreFk.conNombre";

        Query q = super.getEm().createQuery(query);
        q.setParameter("orgPk", orgPk);

        return q.getResultList();
    }
}
