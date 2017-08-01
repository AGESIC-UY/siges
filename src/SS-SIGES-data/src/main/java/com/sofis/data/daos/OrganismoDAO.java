package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.tipos.IdNombreTO;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class OrganismoDAO extends HibernateJpaDAOImp<Organismos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public OrganismoDAO(EntityManager em) {
        super(em);
    }

    /**
     * Retorna una lista de ID y nombre de los organismos donde el usuario
     * aportado tiene permisos.
     *
     * @param usuId
     * @return Lista de IdNombreTO con el ID y nombre de los organismos.
     */
    public List<IdNombreTO> obtenerOrgIdNombre(Integer usuId) {
        if (usuId != null) {
            String queryStr = "SELECT NEW com.sofis.entities.tipos.IdNombreTO(o.orgPk, o.orgNombre)"
                    + " FROM Organismos o"
                    + " WHERE o.orgActivo = :orgActivo"
                    + " AND o.orgPk IN ("
                    + "SELECT uor.usuOfiRolesOficina.ofiId"
                    + " FROM SsUsuOfiRoles uor"
                    + " WHERE uor.usuOfiRolesUsuario.usuId = :usuId"
                    + " AND uor.usuOfiRolesActivo = :ofiRolActivo)"
                    + " ORDER BY o.orgNombre";
            Query query = getEm().createQuery(queryStr);
            query.setParameter("orgActivo", Boolean.TRUE);
            query.setParameter("usuId", usuId);
            query.setParameter("ofiRolActivo", Boolean.TRUE);

            List<IdNombreTO> orgList = query.getResultList();
            return orgList;
        }
        return null;
    }

    public byte[] obtenerLogoOrg(Integer orgPk) {
        String queryStr = "SELECT org_logo FROM organismos where org_pk = :orgPk";
        Query query = super.getEm().createNativeQuery(queryStr);
        query.setParameter("orgPk", orgPk);

        List result = query.getResultList();
        return (byte[]) DAOUtils.obtenerSingleResult(result);
    }

    public int guardarLogoOrg(Integer orgPk, byte[] logo) {
        String queryStr = "UPDATE organismos SET org_logo = :orgLogo WHERE org_pk = :orgPk";
        Query query = super.getEm().createNativeQuery(queryStr);
        query.setParameter("orgLogo", logo);
        query.setParameter("orgPk", orgPk);

        return query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Organismos> obtenerTodos() throws DAOGeneralException {
        return super.getEm().createNamedQuery("Organismos.findAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Organismos> obtenerTodos(Boolean activo) throws DAOGeneralException {
        return super.getEm()
                .createNamedQuery("Organismos.findAllActivos")
                .setParameter("activo", activo)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Organismos> obtenerTodosActivos() throws DAOGeneralException {
        return obtenerTodos(Boolean.TRUE);
    }

    public Organismos obtenerPorToken(String token) throws DAOGeneralException {

        try {
            Query query = this.getEm().createNamedQuery("Organismos.findByToken");
            query.setParameter("token", token);
            List<Organismos> res = query.getResultList();
            return res != null && !res.isEmpty() ? res.get(0) : null;
        } catch (Exception ex) {
            throw new DAOGeneralException(ex);
        }

    }

}
