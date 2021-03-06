package com.sofis.web.mb;

import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ErrorHandler {

    public String getMensajeError() {
        try {
            Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
            String codigo = String.valueOf((Integer) map.get("javax.servlet.error.status_code"));
            String mensaje = (String) map.get("javax.servlet.error.message");
            return "Error " + codigo + ": " + mensaje;
        } catch (Exception ex) {
            return "***";
        }
    }

    public String getUrlRedirect() {
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
        String contexto = (String) map.get("javax.servlet.forward.context_path");

        for (String k : map.keySet()) {
            System.out.println("--" + k + " => " + map.get(k));
        }

        return contexto;

    }

    /*
     public String getStatusCode() {
     try {
     String val = String.valueOf((Integer) FacesContext.getCurrentInstance().getExternalContext().
     getRequestMap().get("javax.servlet.error.status_code"));
     return val;
     } catch (Exception ex) {
     return "***";
     }
     }

     public String getMessage() {
     try {
     String val = (String) FacesContext.getCurrentInstance().getExternalContext().
     getRequestMap().get("javax.servlet.error.message");
     return val;
     } catch (Exception ex) {
     return "***";
     }
     }

     public String getExceptionType() {
     try {
     String val = FacesContext.getCurrentInstance().getExternalContext().
     getRequestMap().get("javax.servlet.error.exception_type").toString();
     return val;
     } catch (Exception ex) {
     return "***";
     }
     }

     public String getException() {
     try {
     String val = (String) ((Exception) FacesContext.getCurrentInstance().getExternalContext().
     getRequestMap().get("javax.servlet.error.exception")).toString();
     return val;
     } catch (Exception ex) {
     return "***";
     }
     }

     public String getRequestURI() {
     try {
     return (String) FacesContext.getCurrentInstance().getExternalContext().
     getRequestMap().get("javax.servlet.error.request_uri");
     } catch (Exception ex) {
     return "***";
     }
     }

     public String getServletName() {
     try {
     return (String) FacesContext.getCurrentInstance().getExternalContext().
     getRequestMap().get("javax.servlet.error.servlet_name");
     } catch (Exception ex) {
     return "***";
     }
     }
     * */
}
