package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.NotificacionEnvio;
import com.sofis.exceptions.TechnicalException;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.persistence.dao.imp.hibernate.HibernateJpaDAOImp;
import com.sofis.sofisform.to.CriteriaTO;
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
public class NotificacionEnvioDAO extends HibernateJpaDAOImp<NotificacionEnvio, Integer> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public NotificacionEnvioDAO(EntityManager em) {
        super(em);
    }

    public void superarNotEnviada(String cod, Integer proyPk, boolean transaction) {
        String queryStr = "DELETE FROM NotificacionEnvio ne"
                + " WHERE ne.notenvNotCod = :cod"
                + " AND ne.notenvProyFk = :proyPk";
        try {
            if (transaction) {
                super.getEm().getTransaction().begin();
            }

            Query query = super.getEm().createQuery(queryStr);
            query.setParameter("cod", cod);
            query.setParameter("proyPk", proyPk);

            query.executeUpdate();

            if (transaction) {
                super.getEm().getTransaction().commit();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            if (transaction) {
                if (super.getEm().getTransaction().isActive()) {
                    super.getEm().getTransaction().rollback();
                }
            }

        } finally {
            if (transaction) {
                super.getEm().close();
            }
        }
    }

    public NotificacionEnvio guardar(NotificacionEnvio ne, boolean transaction) {
        try {
            if (transaction) {
                super.getEm().getTransaction().begin();
            }

            ne = this.create(ne);
            if (transaction) {
                super.getEm().getTransaction().commit();
            }

        } catch (DAOGeneralException ex) {
            Logger.getLogger(NotificacionEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction) {
                if (super.getEm().getTransaction().isActive()) {
                    super.getEm().getTransaction().rollback();
                }
            }

        } finally {
            if (transaction) {
                super.getEm().close();
            }
        }
        return ne;
    }

    public List<NotificacionEnvio> obtenerEntityByCriteria(Class<NotificacionEnvio> aClass, CriteriaTO criteria, String[] string, boolean[] b, Long startPosition, Long maxResult, boolean transaction) {
        List<NotificacionEnvio> listNE = null;
        if (transaction) {
            super.getEm().getTransaction().begin();
        }

        try {
            listNE = this.findEntityByCriteria(aClass, criteria, string, b, startPosition, maxResult);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(NotificacionEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (transaction) {
            super.getEm().getTransaction().commit();
            super.getEm().close();
        }
        return listNE;
    }
}
