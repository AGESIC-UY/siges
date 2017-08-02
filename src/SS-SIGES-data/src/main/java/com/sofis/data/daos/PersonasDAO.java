package com.sofis.data.daos;

import com.sofis.entities.data.Personas;
import com.sofis.entities.data.RolesInteresados;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class PersonasDAO extends HibernateJpaDAOImp<Personas, Integer> implements Serializable {

    public PersonasDAO(EntityManager em) {
        super(em);
    }

    public List<Personas> buscar(String bNombre, Integer bOrganismo, String orderBy, boolean asc) {
        boolean and = false;
        String select = "SELECT p FROM Personas p ";
        String where = "";
        String order = "";

        if (bNombre != null && !bNombre.trim().isEmpty()) {
            if (and) {
                where = where + " AND ";
            } else {
                where = where + " WHERE ";
                and = true;
            }
            where = where + " p.persNombre LIKE :persNombre";
        }

        if (bOrganismo != null && bOrganismo.intValue() > 0) {
            if (and) {
                where = where + " AND ";
            } else {
                where = where + " WHERE ";
                and = true;
            }
            where = where + " p.persOrgaFk.orgaOrgFk.orgPk = :orgPk";
        }

        if (orderBy != null) {
            order = order + " ORDER BY p." + orderBy + " " + (asc ? "ASC" : "DESC");
        }

        Query query = super.getEm().createQuery(select + where + order);

        if (bNombre != null && !bNombre.trim().isEmpty()) {
            query.setParameter("persNombre", bNombre.trim());
        }
        if (bOrganismo != null && bOrganismo.intValue() > 0) {
            query.setParameter("orgPk", bOrganismo);
        }

        List<Personas> ps = query.getResultList();

        for (Personas p : ps) {
            if (p.getPersRolFk() != null) {
                p.setPersRol(getEm().find(RolesInteresados.class, p.getPersRolFk()));
            }
        }

        return ps;

    }

    public List<Personas> obtenerPersonasPorOrga(Integer orgaPk, Integer orgPk) {
        String queryStr = "SELECT i.intPersonaFk FROM Interesados i "
                + " WHERE i.intOrgaFk = :orgaPk "
                + " AND i.persona.perOrgPk = :orgPk";
        Query query = super.getEm().createQuery(queryStr);
        query.setParameter("orgaPk", orgaPk);
        query.setParameter("orgPk", orgPk);

        List<Personas> result = query.getResultList();
        return result;
    }
}
