package com.sofis.data.daos;

 
import com.sofis.entities.data.TipoDocumentoPersona;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoPersonaDAO extends HibernateJpaDAOImp<TipoDocumentoPersona, Integer> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public TipoDocumentoPersonaDAO(EntityManager em) {
        super(em);
    }
    
    public List<TipoDocumentoPersona> obtenerHabilitados() throws DAOGeneralException {
       CodigueraDAO cdao = new CodigueraDAO(super.getEm());
       return cdao.obtenerHabilitados(TipoDocumentoPersona.class, "tipdocperHabilitado", "tipdocperDescripcion");
    }
}
