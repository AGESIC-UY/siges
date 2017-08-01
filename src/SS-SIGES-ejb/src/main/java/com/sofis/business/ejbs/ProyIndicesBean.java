package com.sofis.business.ejbs;

import com.sofis.data.daos.ProyIndicesDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.Date;
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
    
    public ProyIndices guardar(ProyIndices ind, Integer proyPk) {
        ind.setProyindFechaAct(new Date());

        ProyIndicesDAO dao = new ProyIndicesDAO(em);
        try {
            boolean isNew = ind != null && ind.getProyindPk() == null;
            ind = dao.update(ind);

            if (isNew && proyPk != null) {
                ProyectosDAO pDao = new ProyectosDAO(em);
                Proyectos proy = pDao.findById(Proyectos.class, proyPk);
                proy.setProyIndices(ind);
                pDao.update(proy);
            }

        } catch (DAOGeneralException ex) {
//            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException(ex);
            be.addError(MensajesNegocio.ERROR_PROY_IND_GUARDAR + ":"+proyPk);
            throw be;
        }
        return ind;
    }

    public ProyIndices obtenerIndicePorProyId(Integer proyPk) {
        ProyIndicesDAO dao = new ProyIndicesDAO(em);
        return dao.obtenerIndicePorProyId(proyPk);
    }

    public Date ultimaActualizacion(Integer proyPk) {
        ProyIndicesDAO dao = new ProyIndicesDAO(em);
        return dao.ultimaActualizacion(proyPk);
    }
}
