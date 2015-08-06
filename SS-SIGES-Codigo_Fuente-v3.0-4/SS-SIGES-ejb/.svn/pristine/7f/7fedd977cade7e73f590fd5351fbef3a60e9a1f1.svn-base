package com.sofis.business.ejbs;

import com.sofis.data.daos.CategoriaProyectosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.CategoriaProyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.List;
import java.util.logging.Level;
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
@Stateless(name = "CategoriaProyectosBean")
@LocalBean
public class CategoriaProyectosBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public List<CategoriaProyectos> obtenerTodas() {
        CategoriaProyectosDAO dao = new CategoriaProyectosDAO(em);
        try {
            return dao.findAll(CategoriaProyectos.class);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_CAT_PROY_OBTENER);
            throw be;
        }
    }
}
