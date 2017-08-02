package com.sofis.web.mb;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "menuMB")
@SessionScoped
public class MenuMB implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    /**
     * Creates a new instance of MenuMB
     */
    public MenuMB() {
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarPermisos();
    }
    
    
    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }


    public String fireAction(String codigo) {
        return codigo;
    }

    public HashMap<String, Boolean> getPermisos() {
        return inicioMB.getPermisos();
    }

    public void setPermisos(HashMap<String, Boolean> permisos) {
        inicioMB.setPermisos(permisos);
    }
}
