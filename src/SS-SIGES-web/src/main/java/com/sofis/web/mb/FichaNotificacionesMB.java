package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.NotificacionInstancia;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.web.delegates.NotificacionInstanciaDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "fichaNotificacionesMB")
@ViewScoped
public class FichaNotificacionesMB implements Serializable{

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @ManagedProperty("#{fichaMB}")
    private FichaMB fichaMB;
    @Inject
    private NotificacionInstanciaDelegate notificacionInstanciaDelegate;

    // Atributos
    private int cantElementosPorPagina = 25;
    private List<NotificacionInstancia> notifInstList;

    public FichaNotificacionesMB() {
    }

    @PostConstruct
    public void init() {
        notifInstList = notificacionInstanciaDelegate.obtenerNotificacionInstPorProyId(fichaMB.getFichaTO().getFichaFk(), inicioMB.getOrganismo().getOrgPk());
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setFichaMB(FichaMB fichaMB) {
        this.fichaMB = fichaMB;
    }

    public int getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(int cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public List<NotificacionInstancia> getNotifInstList() {
        return notifInstList;
    }

    public void setNotifInstList(List<NotificacionInstancia> notifInstList) {
        this.notifInstList = notifInstList;
    }

    public String guardarNotifInst() {
        if (CollectionsUtils.isNotEmpty(notifInstList)) {
            try {
                notifInstList = notificacionInstanciaDelegate.guardarNotificacionInstancia(notifInstList);
                if (notifInstList != null) {
                    JSFUtils.agregarMsg("", Labels.getValue("info_notif_inst_guardado"), null);
                    fichaMB.cerrarPopupNotificacion();
                }
            } catch (BusinessException be) {
                logger.log(Level.SEVERE, be.getMessage());
                JSFUtils.agregarMsg("", Labels.getValue("error_notif_inst_guardar"), null);
            }
        }
        return null;
    }

}
