package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
//import com.sofis.business.ejbs.DatosUsuario;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "cambioContraseniaMB")
@ViewScoped
public class CambioContraseniaMB implements Serializable {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @ManagedProperty("#{inicioMB}")
    InicioMB inicioMB;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
//    @Inject
//    private DatosUsuario datUsu;

    private String contraseniaActual;
    private String nuevaContrasenia;
    private String confirmacionContrasenia;

    /**
     * Creates a new instance of CargoMB
     */
    public CambioContraseniaMB() {
        contraseniaActual = "";
        nuevaContrasenia = "";
        confirmacionContrasenia = "";
    }

    @PostConstruct
    public void init() {
//        setFocus("frmCambioPass:guardarCambioPass");
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setFocus(String elementId) {
        JavascriptContext.applicationFocus(FacesContext.getCurrentInstance(), elementId);
    }

    public String guardar() {
        try {
            SsUsuario usuario = ssUsuarioDelegate.cambiarContrasenia(inicioMB.getUsuario().getUsuCorreoElectronico(), contraseniaActual, nuevaContrasenia, confirmacionContrasenia, inicioMB.getOrganismo().getOrgPk());
            if (usuario != null) {
                inicioMB.setUsuario(usuario);
                JSFUtils.agregarMsg("", "info_pass_modificado", null);
                limpiar();
            }
        } catch (BusinessException be) {
            logger.log(Level.WARNING, be.getMessage());
            JSFUtils.agregarMsgs("", be.getErrores());
        }
        return null;
    }

    public void limpiar() {
        contraseniaActual = "";
        nuevaContrasenia = "";
        confirmacionContrasenia = "";
    }

    public String cancelar() {
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String getContraseniaActual() {
        return contraseniaActual;
    }

    public void setContraseniaActual(String contraseniaActual) {
        this.contraseniaActual = contraseniaActual;
    }

    public String getNuevaContrasenia() {
        return nuevaContrasenia;
    }

    public void setNuevaContrasenia(String nuevaContrasenia) {
        this.nuevaContrasenia = nuevaContrasenia;
    }

    public String getConfirmacionContrasenia() {
        return confirmacionContrasenia;
    }

    public void setConfirmacionContrasenia(String confirmacionContrasenia) {
        this.confirmacionContrasenia = confirmacionContrasenia;
    }
}
