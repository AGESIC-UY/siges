package com.sofis.business.ejbs;

import com.sofis.data.daos.NotificacionDAO;
import com.sofis.data.daos.NotificacionInstanciaDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Notificacion;
import com.sofis.entities.data.NotificacionInstancia;
import com.sofis.entities.data.Proyectos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "NotificacionInstanciaBean")
@LocalBean
public class NotificacionInstanciaBean {

//    @Inject
//    private DatosUsuario du;
    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(NotificacionInstanciaBean.class.getName());

    public List<NotificacionInstancia> obtenerNotificacionInstPorProyId(Integer proyPk, Integer orgPk) {
        NotificacionInstanciaDAO notificacionInstanciaDao = new NotificacionInstanciaDAO(em);
        try {
            copiarNotInstFaltantes(proyPk, orgPk);

            List<NotificacionInstancia> notificacionInstanciaList = notificacionInstanciaDao.obtenerNotificacionInstPorProyId(proyPk, orgPk);
            return notificacionInstanciaList;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    public NotificacionInstancia obtenerNotificacionInstPorCod(String cod, Integer proyPk, Integer orgPk) {
        if (!StringsUtils.isEmpty(cod) && proyPk != null && orgPk != null) {
            NotificacionInstanciaDAO dao = new NotificacionInstanciaDAO(em);
            try {
                copiarNotInstFaltantes(proyPk, orgPk);
                NotificacionInstancia notificacionInst = dao.obtenerNotificacionInstPorCod(cod, proyPk);
                return notificacionInst;
            } catch (TechnicalException te) {
                logger.log(Level.SEVERE, null, te);
                BusinessException be = new BusinessException();
                te.addError(te.getMessage());
                throw be;
            }
        }
        return null;
    }

    /**
     * Genera la NotificacionInstancia si no existe para dicho proyecto. En caso
     * de que exista, controla que no falte algún tipo de documento.
     *
     * @param proyPk
     * @param tipoFicha
     * @param orgPk
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void copiarNotInstFaltantes(Integer proyPk, Integer orgPk) {
        NotificacionDAO notifDao = new NotificacionDAO(em);
        NotificacionInstanciaDAO notifInstDao = new NotificacionInstanciaDAO(em);

        try {
            List<NotificacionInstancia> notifInstList = notifInstDao.obtenerNotificacionInstPorProyId(proyPk, orgPk);

            if (notifInstList == null) {
                List<Notificacion> notifList = notifDao.findByOneProperty(Notificacion.class, "notOrgFk.orgPk", orgPk);
                for (Notificacion notif : notifList) {
                    guardarNotifInstDesdeNotif(notif, proyPk);
                }
            } else {
                List<Notificacion> notifList = notifInstDao.obtenerNotificacionesSinInstanciaPorProyId(proyPk, orgPk);

                for (Notificacion notif : notifList) {
                    guardarNotifInstDesdeNotif(notif, proyPk);
                }
            }
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }

    private NotificacionInstancia guardarNotifInstDesdeNotif(Notificacion notif, Integer proyPk) {
        NotificacionInstanciaDAO notifInstDao = new NotificacionInstanciaDAO(em);

        NotificacionInstancia nvaNotifInst = new NotificacionInstancia();
        nvaNotifInst.setNotinstGerenteAdjunto(notif.getNotGerenteAdjunto());
        nvaNotifInst.setNotinstNotFk(notif);
        nvaNotifInst.setNotinstPmof(notif.getNotPmof());
        nvaNotifInst.setNotinstPmot(notif.getNotPmot());
        nvaNotifInst.setNotinstProyFk(new Proyectos(proyPk));
        nvaNotifInst.setNotinstSponsor(notif.getNotSponsor());
        try {
            return notifInstDao.update(nvaNotifInst);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_NOTIFICACION_INST_GUARDAR);
            throw be;
        }
    }

    public List<NotificacionInstancia> guardarCopiaNIProyecto(Integer proyPk, Integer nvoProyPk, Integer orgPk) {
        List<NotificacionInstancia> niList = obtenerNotificacionInstPorProyId(proyPk, orgPk);

        List<NotificacionInstancia> tdiListResult = new ArrayList<>();
        for (NotificacionInstancia ni : niList) {
            NotificacionInstancia nvaNI = new NotificacionInstancia();
            nvaNI.setNotinstGerenteAdjunto(ni.getNotinstGerenteAdjunto());
            nvaNI.setNotinstNotFk(ni.getNotinstNotFk());
            nvaNI.setNotinstPmof(ni.getNotinstPmof());
            nvaNI.setNotinstPmot(ni.getNotinstPmot());
            nvaNI.setNotinstProyFk(new Proyectos(nvoProyPk));
            nvaNI.setNotinstSponsor(ni.getNotinstSponsor());

            tdiListResult.add(nvaNI);
        }

        return guardarNotificacionInstancia(tdiListResult);
    }

    public void eliminarNotificacionesPorProyId(Integer proyPk) {
        NotificacionInstanciaDAO notifInstDao = new NotificacionInstanciaDAO(em);
        notifInstDao.eliminarNotificacionesPorProyId(proyPk);
    }

    public List<NotificacionInstancia> guardarNotificacionInstancia(List<NotificacionInstancia> notifInstList) throws GeneralException {

        NotificacionInstanciaDAO NotifInstDao = new NotificacionInstanciaDAO(em);
        List<NotificacionInstancia> result = new ArrayList<>();
        try {
            for (NotificacionInstancia ni : notifInstList) {
                ni = NotifInstDao.update(ni);
                result.add(ni);
            }

            return result;
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException(ex);
            te.addError(ex.getMessage());
            throw te;
        }
    }
    
    
    public void guardarListaNotificacionInstancia(List<NotificacionInstancia> notifInstList) throws GeneralException {

        NotificacionInstanciaDAO nDao = new NotificacionInstanciaDAO(em);
       
        for (NotificacionInstancia ni : notifInstList) {
            
            em.merge(ni);
        }
    }
    
    
}
