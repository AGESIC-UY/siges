/*
 * 
 * 
 */
package com.sofis.certpyme.pgedelegate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CertificadoVigenciaAnual {
    private String estadoRespuesta;
    private String rut;
    private String numeroConstancia;
    private String tipoContribuyente;
    private String estadoCertificado;
    private Date fechaEmision;
    private Date fechaVencimiento;
    
  private List<String> mensajes = new ArrayList();

    public String getEstadoRespuesta() {
        return estadoRespuesta;
    }

    public void setEstadoRespuesta(String estadoRespuesta) {
        this.estadoRespuesta = estadoRespuesta;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNumeroConstancia() {
        return numeroConstancia;
    }

    public void setNumeroConstancia(String numeroConstancia) {
        this.numeroConstancia = numeroConstancia;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(String tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    public String getEstadoCertificado() {
        return estadoCertificado;
    }

    public void setEstadoCertificado(String estadoCertificado) {
        this.estadoCertificado = estadoCertificado;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = mensajes;
    }
  
  
  
    
}
