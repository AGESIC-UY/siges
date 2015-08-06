/*
 * 
 * 
 */
package com.sofis.web.presentacion.tipos;

import javax.faces.application.FacesMessage;

/**
 *
 * @author Usuario
 */
public class MensajePresentacion {
    
    private String para;
    private String descripcion;
    private FacesMessage fMensaje;
    
    public MensajePresentacion() {
        
    }
    
    public MensajePresentacion(String para, String descripcion) {
        this.para=para;
        this.descripcion=descripcion;
        fMensaje = new FacesMessage( FacesMessage.SEVERITY_ERROR,descripcion,descripcion);
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FacesMessage getfMensaje() {
        return fMensaje;
    }

    public void setfMensaje(FacesMessage fMensaje) {
        this.fMensaje = fMensaje;
    }
    
    
}
