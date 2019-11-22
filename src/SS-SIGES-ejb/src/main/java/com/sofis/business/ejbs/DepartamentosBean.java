package com.sofis.business.ejbs;

import com.sofis.data.daos.DepartamentosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.sofis.entities.data.Departamentos;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "DepartamentosBean")
@LocalBean
public class DepartamentosBean {
    
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(DepartamentosBean.class.getName());
    
    public Departamentos obtenerDepPorPk(Integer depPk){
        if(depPk != null) {
            DepartamentosDAO depDao = new DepartamentosDAO(em);
            try {
                Departamentos dep = depDao.findById(Departamentos.class, depPk);
                return dep;
            } catch (DAOGeneralException ex) {
                    logger.log(Level.SEVERE, ex.getMessage(), ex);
                    TechnicalException te = new TechnicalException(ex);
                    te.addError(ex.getMessage());
                    throw te;
            }
        }
        return null;
    }
}
