package com.sofis.data.daos;

import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ValorCalidadCodigosDAO extends HibernateJpaDAOImp<ValorCalidadCodigos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValorCalidadCodigosDAO(EntityManager em) {
        super(em);
    }
}
