package com.sofis.business.ejbs;

import com.sofis.data.daos.EntHistLineaBaseDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.EntHistLineaBase;
import com.sofis.exceptions.BusinessException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "EntHistLineaBaseBean")
@LocalBean
public class EntHistLineaBaseBean {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    @Inject
    private DatosUsuario du;

    public List<EntHistLineaBase> obtenerEntHistPorEntPk(Integer entPk) {
        if (entPk != null) {
            EntHistLineaBaseDAO dao = new EntHistLineaBaseDAO(em);
            try {
                return dao.findByOneProperty(EntHistLineaBase.class, "enthistEntregableFk.entPk", entPk, "enthistFecha");
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ENT_HIST_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public List<EntHistLineaBase> obtenerEntHistPorProyPk(Integer proyPk) {
        if (proyPk != null) {
            EntHistLineaBaseDAO dao = new EntHistLineaBaseDAO(em);
            return dao.obtenerEntHistPorProyPk(proyPk);
        }
        return null;
    }
}
