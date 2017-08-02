package com.sofis.data.daos;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.NotificacionEnvio;
import com.sofis.generico.utils.generalutils.StringsUtils;
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

    private boolean useManagedTransaction(boolean transaction) {
        if (super.getEm() != null) {
            String persistenceUnitName = (String) super.getEm().getEntityManagerFactory().getProperties().get("hibernate.ejb.entitymanager_factory_name");
            boolean isNoneJTA = persistenceUnitName.endsWith(ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME_NONE_JTA);

            return (transaction || isNoneJTA) && super.getEm() != null && super.getEm().getTransaction() != null;
        }
        return false;
    }

    public void superarNotEnviada(String cod, Integer proyPk, boolean transaction) {
        if (!StringsUtils.isEmpty(cod) && proyPk != null) {
            String queryStr = "DELETE FROM NotificacionEnvio ne"
                    + " WHERE ne.notenvNotCod = :cod"
                    + " AND ne.notenvProyFk = :proyPk";
            try {
                if (useManagedTransaction(transaction)) {
                    super.getEm().getTransaction().begin();
                }

                Query query = super.getEm().createQuery(queryStr);
                query.setParameter("cod", cod);
                query.setParameter("proyPk", proyPk);

                query.executeUpdate();

                if (useManagedTransaction(transaction)) {
                    super.getEm().getTransaction().commit();
                }

            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
                if (useManagedTransaction(transaction)) {
                    if (super.getEm().getTransaction().isActive()) {
                        super.getEm().getTransaction().rollback();
                    }
                }

            } finally {
                if (useManagedTransaction(transaction)) {
                    super.getEm().close();
                }
            }
        }
    }

    public NotificacionEnvio guardar(NotificacionEnvio ne, boolean transaction) {
        try {
            if (useManagedTransaction(transaction)) {
                super.getEm().getTransaction().begin();
            }

            ne = this.create(ne);
            if (useManagedTransaction(transaction)) {
                super.getEm().getTransaction().commit();
            }

        } catch (DAOGeneralException ex) {
            Logger.getLogger(NotificacionEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
            if (useManagedTransaction(transaction)) {
                if (super.getEm().getTransaction().isActive()) {
                    super.getEm().getTransaction().rollback();
                }
            }

        } finally {
            if (useManagedTransaction(transaction)) {
                super.getEm().close();
            }
        }
        return ne;
    }

    public List<NotificacionEnvio> obtenerEntityByCriteria(Class<NotificacionEnvio> aClass, CriteriaTO criteria, String[] string, boolean[] b, Long startPosition, Long maxResult, boolean transaction) {
        List<NotificacionEnvio> listNE = null;
        if (useManagedTransaction(transaction)) {
            super.getEm().getTransaction().begin();
        }

        try {
            listNE = this.findEntityByCriteria(aClass, criteria, string, b, startPosition, maxResult);
        } catch (DAOGeneralException ex) {
            Logger.getLogger(NotificacionEnvioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (useManagedTransaction(transaction)) {
            super.getEm().getTransaction().commit();
            super.getEm().close();
        }
        return listNE;
    }
}
