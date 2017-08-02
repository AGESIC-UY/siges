/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.mb;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.web.delegates.DocumentosDelegate;
import com.sofis.web.genericos.constantes.ConstantesNavegacion;
import com.sofis.web.utils.JSFUtils;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@ManagedBean(name = "moverDocFsMB")
@ViewScoped
public class MoverDocFsMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstantesEstandares.LOGGER);

    private Boolean renderProcesar = true;

//    @ManagedProperty("#{inicioMB}")
//    private InicioMB inicioMB;
    @Inject
    private DocumentosDelegate documentosDelegate;

    /**
     * Creates a new instance of MoverDocFsMB
     */
    public MoverDocFsMB() {
    }

    @PostConstruct
    public void init() {
        renderProcesar = documentosDelegate.procesarMoverDocFS();
    }

    public String salir() {
        return ConstantesNavegacion.IR_A_INICIO;
    }

    public String procesar() {
        try {
            documentosDelegate.moverArchivosDocToFS();
            JSFUtils.agregarMsgInfo("Migración de documentos realizada con éxito");
        } catch (Exception ex) {
            JSFUtils.agregarMsgError("Ocurrió un error durante la migración de documentos");
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return null;
    }

    public Boolean getRenderProcesar() {
        return renderProcesar;
    }

    public void setRenderProcesar(Boolean renderProcesar) {
        this.renderProcesar = renderProcesar;
    }
}
