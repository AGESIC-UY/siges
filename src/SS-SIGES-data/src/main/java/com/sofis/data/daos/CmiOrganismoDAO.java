package com.sofis.data.daos;

import com.sofis.entities.data.CmiOrganismo;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CmiOrganismoDAO extends HibernateJpaDAOImp<CmiOrganismo, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public CmiOrganismoDAO(EntityManager em) {
        super(em);
    }

    /**
     * Guarda o actualiza un CmiOrganismo.
     */
    public CmiOrganismo guardar(CmiOrganismo cmiOrg) throws DAOGeneralException {
        try {
            return super.update(cmiOrg);
        } catch (Exception ex) {
            throw new DAOGeneralException("Error al guardar CmiOrganismo", ex.getCause());
        }
    }

    /**
     * Obtiene un CmiOrganismo por su campo orgOrganismos.
     */
    public CmiOrganismo obtenerPorOrgOrganismos(String orgOrganismos) throws DAOGeneralException {
        try {

            String sql = "SELECT c "
                       + "FROM CmiOrganismo c "
                       + "WHERE c.orgOrganismos = :orgOrganismos";

            Query query = super.getEm().createQuery(sql);
            query.setParameter("orgOrganismos", orgOrganismos);

            List<CmiOrganismo> lista = query.getResultList();

            if (lista != null && !lista.isEmpty()) {
                return lista.get(0);
            }

            return null;

        } catch (Exception ex) {
            throw new DAOGeneralException("Error obteniendo CmiOrganismo por orgOrganismos", ex);
        }
    }
}
