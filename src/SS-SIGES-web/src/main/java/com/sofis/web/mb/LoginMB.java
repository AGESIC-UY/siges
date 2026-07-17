package com.sofis.web.mb;

import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "LoginMB")
@ViewScoped
public class LoginMB implements Serializable {

    private static final Logger logger = Logger.getLogger(LoginMB.class.getName());

    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;

    private String username;
    private String password;
    private boolean ingresarMailParaResetearContrasenia = false;
    private String mailParaResetearContrasenia = "";

    private Boolean authLdapEnable = false;
    private Boolean bloquearFormularioLogin = false;
    private String mensajeErroneo = "";

    @PostConstruct
    public void init() {
        String authLdapEnableString = configuracionDelegate.obtenerCnfValorPorCodigo("AUTH_LDAP_ENABLE", null);
        authLdapEnable = authLdapEnableString != null && "true".equals(authLdapEnableString.toLowerCase());
        Configuracion configMensajeLogeo = configuracionDelegate.obtenerCnfPorCodigoPrimerOrganismo("MENSAJE_ERROR_LOGEO");
        mensajeErroneo = (configMensajeLogeo != null && configMensajeLogeo.getCnfValor() != null)
                ? configMensajeLogeo.getCnfValor()
                : "";
        bloquearFormularioLogin = mensajeErroneo.contains("bloqueado");
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            String normalizedUsername = this.username != null
                ? this.username.trim().toLowerCase()
                : null;
            request.login(normalizedUsername, this.password);
            logger.log(Level.INFO, "CALL LOGIN");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public boolean isIngresarMailParaResetearContrasenia() {
        return ingresarMailParaResetearContrasenia;
    }

    public void setIngresarMailParaResetearContrasenia(boolean ingresarMailParaResetearContrasenia) {
        this.ingresarMailParaResetearContrasenia = ingresarMailParaResetearContrasenia;
    }

    public String getMailParaResetearContrasenia() {
        return mailParaResetearContrasenia;
    }

    public void setMailParaResetearContrasenia(String mailParaResetearContrasenia) {
        this.mailParaResetearContrasenia = mailParaResetearContrasenia;
    }

    public String recordarContrasenia() {
        ingresarMailParaResetearContrasenia = true;
        return null;
    }

    public String aceptarCambiarContrasenia() {
        if (!mailParaResetearContrasenia.trim().isEmpty()) {
            try {

                SsUsuario usuario = ssUsuarioDelegate.obtenerUsuarioPorMail(mailParaResetearContrasenia);

                if (usuario != null) {

                    boolean bloqueado = ssUsuarioDelegate.usuarioBloqueado(usuario.getUsuId());
                    if (bloqueado) {
                        String msg = "Usuario se encuentra bloqueado temporalmente,reintente en unos minutos. ";
                        JSFUtils.agregarMsgWarn(msg);
                    } else {
                        ssUsuarioDelegate.resetearContrasenia(mailParaResetearContrasenia, null);
                  String msg = "Si el correo\n"
                                + "electrónico se encuentra registrado en la aplicación, se le enviará un correo.";
                        JSFUtils.agregarMsgInfo(msg);
                        mailParaResetearContrasenia = "";
                        ingresarMailParaResetearContrasenia = false;      
                    }

                }else{
                      String msg = "Si el correo\n"
                                + "electrónico se encuentra registrado en la aplicación, se le enviará un correo.";
                        JSFUtils.agregarMsgInfo(msg);
                        mailParaResetearContrasenia = "";
                        ingresarMailParaResetearContrasenia = false;      
                }

            } catch (BusinessException ex) {
                logger.log(Level.SEVERE, ex.getMessage(), ex);
                JSFUtils.agregarMensajes(FacesMessage.SEVERITY_ERROR, ex.getErrores());
            }
        }
        return null;
    }

    public String cancelarCambiarContrasenia() {
        mailParaResetearContrasenia = "";
        ingresarMailParaResetearContrasenia = false;
        return null;
    }

    /**
     * @return the authLdapEnable
     */
    public Boolean getAuthLdapEnable() {
        return authLdapEnable;
    }

    /**
     * @param authLdapEnable the authLdapEnable to set
     */
    public void setAuthLdapEnable(Boolean authLdapEnable) {
        this.authLdapEnable = authLdapEnable;
    }

    public String getMensajeErroneo() {
        return mensajeErroneo;
    }

    public void setMensajeErroneo(String mensajeErroneo) {
        this.mensajeErroneo = mensajeErroneo;
    }

    public Boolean getBloquearFormularioLogin() {
        return bloquearFormularioLogin;
    }

    public void setBloquearFormularioLogin(Boolean bloquearFormularioLogin) {
        this.bloquearFormularioLogin = bloquearFormularioLogin;
    }

}
