package com.sofis.web.mb;

import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.TokenSegundoFactor;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.seguridad.SigesPrincipal;
import com.sofis.web.utils.EJBUtils;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.joda.time.LocalDateTime;

@ManagedBean(name = "SegundoFactorMB")
@ViewScoped
public class SegundoFactorMB implements Serializable {

    private static final Logger logger
            = Logger.getLogger(SegundoFactorMB.class.getName());
    @Inject
    private SsUsuarioDelegate usuDelegate;

    private TokenSegundoFactor tokenSegundoFactor;
    private int contador;
    private String codigo;
    private int intentosFallidos;
    private boolean tiempoVencido;
    private SsUsuario usuario;

    @PostConstruct
    public void init() {
        if (devolverSigesPrincipal().getToken().getTiempoVencimiento() <= 0) {
            logout();
        }
    }

    public String validarCodigo() {
        SigesPrincipal principal = devolverSigesPrincipal();

        if (principal.getToken().getTiempoVencimiento() == 0) {
            JSFUtils.agregarMsgWarn("El código ya venció.");
            return null;
        }

        if (principal.getToken().getIntentosFallidos() >= 3) {
            String email = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getUserPrincipal().getName();
            usuario = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorMail(email);

            usuDelegate.bloquearUsuario(usuario.getUsuId());

            irErrorContainer();
            return null;

        }

        if (principal.getToken().getCodigo().equals(codigo)) {

            LocalDateTime timeNow = LocalDateTime.now();
            principal.getToken().setFechaConfirmado(timeNow);
            String email = FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getUserPrincipal().getName();
            usuario = EJBUtils.getUsuarioLocal().obtenerSsUsuarioPorMail(email);
            usuDelegate.actualizarFechaLogin(usuario.getUsuId());

            // 👉 REDIRECCIÓN
            return "/paginasPrivadas/paginaInicioCliente.xhtml?faces-redirect=true";

        } else {

            principal.getToken().setIntentosFallidos(principal.getToken().getIntentosFallidos() + 1);
            JSFUtils.agregarMsgError("Código incorrecto.");
            return null;
        }
    }

    public void irErrorContainer() {

        try {
            FacesContext fc = FacesContext.getCurrentInstance();

            fc.getExternalContext().invalidateSession();

            fc.getExternalContext().redirect(
                    fc.getExternalContext().getRequestContextPath()
                    + "/paginasPublicas/error.jsf"
            );

        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    public String logout() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

        logger.severe("en el logout antes del redirect");
        return "/paginasPrivadas/paginaInicioCliente.xhtml?faces-redirect=true";
    }

    public SigesPrincipal devolverSigesPrincipal() {
        Object principal
                = FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getUserPrincipal();
        return (SigesPrincipal) principal;
    }


    /* ================= GETTERS / SETTERS ================= */
    public int getContador() {
        return contador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isTiempoVencido() {
        return tiempoVencido;
    }
}
