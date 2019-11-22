/*
 * 
 * 
 */
package com.sofis.data.daos;

 
 
import com.sofis.entities.data.TipoTelefono;
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
public class TipoTelefonoDAO extends HibernateJpaDAOImp<TipoTelefono, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(TipoTelefonoDAO.class.getName());       

    public TipoTelefonoDAO(EntityManager em) {
        super(em);
    }
    
     public List<TipoTelefono> obtenerHabilitados() throws DAOGeneralException {
       CodigueraDAO cdao = new CodigueraDAO(super.getEm());
       return cdao.obtenerHabilitados(TipoTelefono.class, "tipTelHabilitado", "tipTelDescripcion");
    }
}
