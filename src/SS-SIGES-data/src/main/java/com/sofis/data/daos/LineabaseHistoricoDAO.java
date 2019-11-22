package com.sofis.data.daos;

import com.sofis.entities.data.LineabaseHistorico;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
@Deprecated
public class LineabaseHistoricoDAO extends HibernateJpaDAOImp<LineabaseHistorico, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LineabaseHistoricoDAO.class.getName());      

    public LineabaseHistoricoDAO(EntityManager em) {
        super(em);
    }
}
