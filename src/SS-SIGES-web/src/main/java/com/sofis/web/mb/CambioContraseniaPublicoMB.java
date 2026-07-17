package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.entities.constantes.MensajesNegocio;
//import com.sofis.business.ejbs.DatosUsuario;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "cambioContraseniaPublicoMB")
@ViewScoped
public class CambioContraseniaPublicoMB implements Serializable {

    private static final Logger logger = Logger.getLogger(CambioContraseniaPublicoMB.class.getName());

    @ManagedProperty("#{inicioMB}")
    InicioMB inicioMB;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
//    @Inject
//    private DatosUsuario datUsu;

    private String contraseniaActual;
    private String nuevaContrasenia;
    private String confirmacionContrasenia;
    private Long idExpediente;
    private String hashToken;

    /**
     * Creates a new instance of CargoMB
     */
    public CambioContraseniaPublicoMB() {
        contraseniaActual = "";
        nuevaContrasenia = "";
        confirmacionContrasenia = "";
    }

    @PostConstruct
    public void init() {
        try {
            //String hash = (String) getFlashContext(ConstantesPresentacion.USUARIO_HASH);
            HttpServletRequest request = (HttpServletRequest) FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .getRequest();
            String hashParam = request.getQueryString();
            if (hashParam != null) {
                String[] arrOfStr = hashParam.split("=", 2);
                String hash = arrOfStr[1];
                hashToken = hash;
            }

            SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorHashToken(hashToken);

            if (usuario != null) {
                if (usuario.getUsuFinHashToen() == null) {

                     ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect("/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.jsf");
                    return;
                } else {

                    if (usuario.getUsuFinHashToen().before(new Date())) {

                     ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect("/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.jsf");
                    return;
                    } 

                }
            } else {

                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    ec.redirect("/SS-SIGES-web/paginasPrivadas/paginaInicioCliente.jsf");
                    return;
            }

        } catch (Exception e) {
        }

    }

    private Object getFlashContext(String attName) {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash().get(attName);
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setFocus(String elementId) {
        JavascriptContext.applicationFocus(FacesContext.getCurrentInstance(), elementId);
    }

    public String guardar() {
        try {
            BusinessException ge = new BusinessException();
            SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorHashToken(hashToken);

            if (usuario != null) {
                if (usuario.getUsuFinHashToen() == null) {

                    ge.addError(MensajesNegocio.ERROR_USUARIO_HASH_INVALIDO);
                    throw ge;
                } else {

                    if (usuario.getUsuFinHashToen().before(new Date())) {

                        ge.addError(MensajesNegocio.ERROR_USUARIO_HASH_INVALIDO);
                        throw ge;
                    } else {
                        SsUsuario usuarioa = ssUsuarioDelegate.cambiarContraseniaNuevoProcedimiento(usuario.getUsuCorreoElectronico(), nuevaContrasenia, confirmacionContrasenia);
                        JSFUtils.agregarMsg("", "info_pass_modificado", null);
                        return ConstantesNavegacion.IR_A_INICIO;
                    }

                }
            } else {

                ge.addError(MensajesNegocio.ERROR_USUARIO_HASH_INVALIDO);
                throw ge;
            }

        } catch (BusinessException be) {
            logger.log(Level.WARNING, be.getMessage());

            /*
            *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsgs("", be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
            }

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
