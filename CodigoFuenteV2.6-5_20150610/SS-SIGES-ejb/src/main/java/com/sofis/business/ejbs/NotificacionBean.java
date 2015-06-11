package com.sofis.business.ejbs;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.business.validations.NotificacionValidacion;
import com.sofis.data.daos.NotificacionDAO;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesNotificaciones;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Notificacion;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.exceptions.DAOGeneralException;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Stateless(name = "notificacionBean")
@LocalBean
public class NotificacionBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    @Inject
    private DatosUsuario du;
    @Inject
    private OrganismoBean organismoBean;

    public Notificacion guardar(Notificacion notif) {
        if (notif != null) {
            NotificacionValidacion.validar(notif);
            validarDuplicado(notif);

            NotificacionDAO dao = new NotificacionDAO(em);
            try {
                return dao.update(notif);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_GUARDAR);
                throw be;
            }
        }
        return null;
    }

    public void controlarNotifFaltantes() {
        List<Organismos> organismos = organismoBean.obtenerTodos();
        for (Organismos org : organismos) {
            List<Notificacion> notifList = obtenerTodosPorOrg(org.getOrgPk());
            Map<String, Notificacion> notifMap = new HashMap<>();
            if (notifList != null) {
                for (Notificacion notif : notifList) {
                    notifMap.put(notif.getNotCod(), notif);
                }
            }

            Notificacion[] notifArr = new Notificacion[]{
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_RIESGOS_1, LabelsEJB.getValue("notif_cod_riesgos_1_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_riesgos_1_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_RIESGOS_2, LabelsEJB.getValue("notif_cod_riesgos_2_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_riesgos_2_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_INICIO, LabelsEJB.getValue("notif_cod_inicio_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_inicio_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_PLANIFICACION, LabelsEJB.getValue("notif_cod_planificacion_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_planificacion_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_ACTUALIZACION_1, LabelsEJB.getValue("notif_cod_actualizacion_1_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_actualizacion_1_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_ACTUALIZACION_2, LabelsEJB.getValue("notif_cod_actualizacion_2_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_actualizacion_2_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_PRESUPUESTO_1, LabelsEJB.getValue("notif_cod_presupuesto_1_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_1_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_PRESUPUESTO_2, LabelsEJB.getValue("notif_cod_presupuesto_2_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_2_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_PRESUPUESTO_3, LabelsEJB.getValue("notif_cod_presupuesto_3_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_3_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_PRESUPUESTO_4, LabelsEJB.getValue("notif_cod_presupuesto_4_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_4_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_PRESUPUESTO_5, LabelsEJB.getValue("notif_cod_presupuesto_5_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_presupuesto_5_mail")),
                new Notificacion(org, ConstantesNotificaciones.NOT_COD_CRONOGRAMA_1, LabelsEJB.getValue("notif_cod_cronograma_1_desc"), null, true, false, false, false, LabelsEJB.getValue("notif_cod_cronograma_1_mail")),};

            for (int i = 0; i < notifArr.length; i++) {
                if (!notifMap.containsKey(notifArr[i].getNotCod())) {
                    notifArr[i].setNotOrgFk(org);
                    try {
                        guardar(notifArr[i]);
                        logger.log(Level.INFO, "Se agregó la notificación '" + notifArr[i].getNotCod() + "' para el org " + org.getOrgPk());
                    } catch (BusinessException e) {
                        logger.log(Level.SEVERE, null, e);
                        logger.log(Level.INFO, "No se pudo guardar la notificacion: " + notifArr[i].getNotCod() + "(org:" + org.getOrgPk() + ")");
                    }
                }
            }
        }
    }

    public List<Notificacion> obtenerTodosPorOrg(Integer orgPk) {
        NotificacionDAO dao = new NotificacionDAO(em);
        try {
            return dao.findByOneProperty(Notificacion.class, "notOrgFk.orgPk", orgPk);

        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.setEx(ex);
            be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
            throw be;
        }
    }

    public Notificacion obtenerNotifPorPk(Integer notifPk) {
        NotificacionDAO dao = new NotificacionDAO(em);

        try {
            return dao.findById(Notificacion.class, notifPk);
        } catch (DAOGeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            TechnicalException te = new TechnicalException();
            te.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
            throw te;
        }
    }

    public List<Notificacion> busquedaNotifFiltro(Integer orgPk, Map<String, Object> mapFiltro, String elementoOrdenacion, int ascendente) {
        if (orgPk != null) {
            List<CriteriaTO> criterios = new ArrayList<>();

            MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.EQUALS, "notOrgFk.orgPk", orgPk);
            criterios.add(criterioOrg);

            String codigo = mapFiltro != null ? (String) mapFiltro.get("notCod") : null;
            if (!StringsUtils.isEmpty(codigo)) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "notCod", codigo);
                criterios.add(criterio);
            }

            String desc = mapFiltro != null ? (String) mapFiltro.get("notDesc") : null;
            if (desc != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "notDesc", desc);
                criterios.add(criterio);
            }

            String msg = mapFiltro != null ? (String) mapFiltro.get("notMsg") : null;
            if (msg != null) {
                MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                        MatchCriteriaTO.types.CONTAINS, "notMsg", msg);
                criterios.add(criterio);
            }

            CriteriaTO condicion;
            if (criterios.size() == 1) {
                condicion = criterios.get(0);
            } else {
                condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
            }

            String[] orderBy = {};
            boolean[] asc = {};
            if (!StringsUtils.isEmpty(elementoOrdenacion)) {
                orderBy = new String[]{elementoOrdenacion};
                asc = new boolean[]{(ascendente == 1 ? true : false)};
            }

            NotificacionDAO dao = new NotificacionDAO(em);

            try {
                return dao.findEntityByCriteria(Notificacion.class, condicion, orderBy, asc, null, null);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_OBTENER);
                throw be;
            }
        }
        return null;
    }

    public void eliminarNotificacion(Integer notifPk) {
        if (notifPk != null) {
            NotificacionDAO dao = new NotificacionDAO(em);
            try {
                Notificacion n = obtenerNotifPorPk(notifPk);
                dao.delete(n);
            } catch (DAOGeneralException ex) {
                logger.log(Level.SEVERE, null, ex);

                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_NOTIFICACION_ELIMINAR);
                if (ex.getCause() instanceof javax.persistence.PersistenceException
                        && ex.getCause().getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
                    be.addError(MensajesNegocio.ERROR_NOTIFICACION_CONST_VIOLATION);
                }
                throw be;
            }
        }
    }

    private void validarDuplicado(Notificacion notif) {
        List<Notificacion> list = obtenerTodosPorOrg(notif.getNotOrgFk().getOrgPk());
        if (CollectionsUtils.isNotEmpty(list)) {
            for (Notificacion n : list) {
                if (!n.getNotPk().equals(notif.getNotPk())
                        && n.getNotCod().equals(notif.getNotCod())) {
                    BusinessException be = new BusinessException();
                    be.addError(MensajesNegocio.ERROR_NOTIFICACION_CODIGO_DUPLICADO);
                    throw be;
                }
            }
        }
    }
}
