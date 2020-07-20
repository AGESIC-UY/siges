package com.sofis.data.daos;

import com.sofis.entities.data.Etiqueta;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class EtiquetaDAO extends HibernateJpaDAOImp<Etiqueta, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public EtiquetaDAO(EntityManager em) {
        super(em);
    }
    
    public List<Etiqueta> obtenerPorOrganismo(Integer orgPk) throws DAOGeneralException {
    
        return super.getEm()
                .createNamedQuery("Etiqueta.findByOrgPk", Etiqueta.class)
                .setParameter("orgPk", orgPk)
                .getResultList();
    }
}
