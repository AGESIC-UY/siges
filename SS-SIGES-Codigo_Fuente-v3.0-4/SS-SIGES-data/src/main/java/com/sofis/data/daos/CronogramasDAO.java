package com.sofis.data.daos;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
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
public class CronogramasDAO extends HibernateJpaDAOImp<Cronogramas, Integer> implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final long serialVersionUID = 1L;

    public CronogramasDAO(EntityManager em) {
        super(em);
    }

    public Cronogramas guardarCronograma(Cronogramas c, String userName, String desc) {
        if (CollectionsUtils.isNotEmpty(c.getEntregablesSet())) {
            for (Entregables e : c.getEntregablesSet()) {
                e.setEntStatus("STATUS_ACTIVE");
                e.setEntCroFk(c);
                if (e.getCoordinadorUsuFk() != null && e.getCoordinadorUsuFk().getUsuId() != null) {
                    SsUsuario usu = super.getEm().find(SsUsuario.class, e.getCoordinadorUsuFk().getUsuId());
                    e.setCoordinadorUsuFk(usu);
                }
            }
        }

        //No se necesita persistido y si es muy largo da error.
        c.setCroEntBorrados(null);

        //salva el cronograma
        try {
            c = this.update(c, userName, desc);
            return c;
        } catch (DAOGeneralException daoGEx) {
            logger.log(Level.SEVERE, daoGEx.getMessage(), daoGEx);
            TechnicalException te = new TechnicalException();
            if (daoGEx.getCause() instanceof javax.persistence.PersistenceException
                    && daoGEx.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                te.addError(MensajesNegocio.ERROR_CRO_CONST_VIOLATION);
            } else {
                te.addError(MensajesNegocio.ERROR_CRO_GUARDAR);
            }
            throw te;
        }
    }

    public Cronogramas obtenerCronogramaProProy(Integer proyId) {

        String quer = "SELECT p.proyCroFk"
                + " FROM Proyectos p"
                + " WHERE p.proyPk = :proyPk";
        Query q = super.getEm().createQuery(quer);
        q.setParameter("proyPk", proyId);

        try {
            List<Cronogramas> result = q.getResultList();
            return (Cronogramas) DAOUtils.obtenerSingleResult(result);
        } catch (Exception w) {
            return null;
        }
    }
}
