package com.sofis.data.daos;

import com.sofis.entities.data.Pais;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class PaisDAO extends HibernateJpaDAOImp<Pais, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(PaisDAO.class.getName()); 

    public PaisDAO(EntityManager em) {
        super(em);
    }

    public List<Pais> obtenerHabilitados() throws DAOGeneralException {
        CodigueraDAO cdao = new CodigueraDAO(super.getEm());
        return cdao.obtenerHabilitados(Pais.class, "paiHabilitado", "paiNombre");
    }
}
