package com.sofis.data.daos;

import com.sofis.entities.data.TipoRiesgo;
import com.sofis.entities.data.Areas;
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
public class TipoRiesgoDAO extends HibernateJpaDAOImp<TipoRiesgo, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoRiesgoDAO.class.getName());

    public TipoRiesgoDAO(EntityManager em) {
        super(em);
    }

    public List<TipoRiesgo> busquedaActivos(Integer orgPk) {

        String query = "SELECT procComp"
                + " FROM TipoRiesgo procComp"
                + " WHERE procComp.trsOrgFk.orgPk = :orgPk"
                + " AND procComp.trsHabilitado = 1"
                + " ORDER BY procComp.trsNombre";

        Query q = super.getEm().createQuery(query);
        q.setParameter("orgPk", orgPk);

        List<TipoRiesgo> retorno = (List<TipoRiesgo>) q.getResultList();

        return retorno;

    }

}
