package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Devengado;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
public class DevengadoDAO extends HibernateJpaDAOImp<Devengado, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(DevengadoDAO.class.getName());

    public DevengadoDAO(EntityManager em) {
        super(em);
    }

    public void eliminarDevPorAdqPk(Integer adqPk) {
        String queryStr = "DELETE FROM Devengado d"
                + " WHERE d.devAdqFk.adqPk = :adqPk";

        try {
            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("adqPk", adqPk);

            query.executeUpdate();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(MensajesNegocio.ERROR_DEVENGADO_ELIMINAR);
            throw te;
        }
    }

    public Devengado obtenerDevengado(Integer adqPk, short mesDev, short anioDev) {
        if (adqPk != null && mesDev > 0 && anioDev > 0) {
            String queryStr = "SELECT d FROM Devengado d"
                    + " WHERE d.devAdqFk.adqPk = :adqPk"
                    + " AND d.devMes = :mesDev"
                    + " AND d.devAnio = :anioDev";

            try {
                Query query = super.getEm().createQuery(queryStr);
                query.setParameter("adqPk", adqPk);
                query.setParameter("mesDev", mesDev);
                query.setParameter("anioDev", anioDev);

                List<Devengado> result = query.getResultList();
                return (Devengado) DAOUtils.obtenerSingleResult(result);
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage(), be);
                return null;
            } catch (Exception ex) {
                logger.log(Level.SEVERE, null, ex);
                TechnicalException te = new TechnicalException(ex);
                te.addError(MensajesNegocio.ERROR_DEVENGADO_OBTENER);
                throw te;
            }
        }
        return null;
    }
}
