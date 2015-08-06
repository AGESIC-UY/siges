package com.sofis.business.ejbs;

import com.sofis.data.daos.ProyIndicesDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.ProyIndices;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ProyIndicesBean")
@LocalBean
public class ProyIndicesBean {
    
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    
    
    public ProyIndices obtenerIndicePorProyId(Integer proyPk) {
        ProyIndicesDAO dao = new ProyIndicesDAO(em);
        return dao.obtenerIndicePorProyId(proyPk);
    }
}
