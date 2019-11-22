package com.sofis.data.daos;

import com.sofis.entities.data.ValorCalidadCodigos;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class ValorCalidadCodigosDAO extends HibernateJpaDAOImp<ValorCalidadCodigos, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ValorCalidadCodigosDAO.class.getName());       

    public ValorCalidadCodigosDAO(EntityManager em) {
        super(em);
    }
}
