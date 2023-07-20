package com.sofis.web.mb;

import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "menuMB")
@SessionScoped
public class MenuMB implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(MenuMB.class.getName());
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    
    @PostConstruct
    public void init() {
        inicioMB.cargarPermisos();
    }
    
    
    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }


    public String fireAction(String codigo) {
        if(codigo.equals("IR_A_INICIO")){
            inicioMB.cargarCombosFiltro();
            inicioMB.inicializarFiltro();
        }
        
        return codigo;
    }

    public HashMap<String, Boolean> getPermisos() {
        return inicioMB.getPermisos();
    }

    public void setPermisos(HashMap<String, Boolean> permisos) {
        inicioMB.setPermisos(permisos);
    }
}
