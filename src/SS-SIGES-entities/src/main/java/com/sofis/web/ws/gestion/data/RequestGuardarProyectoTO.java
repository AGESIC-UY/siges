/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.ws.gestion.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bruno
 */
@XmlRootElement(name="requestGuardarProyecto")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RequestGuardarProyectoTO {
    
    @XmlElement (name = "token", required = true)
    private String token;
    @XmlElement (name = "proyecto", required = true)
    private ProyectoTO proyecto;
    
    @XmlElement(name="attrs")
    private List<AttrTO> attrs;

    public RequestGuardarProyectoTO() {
    }    
    
    //<editor-fold defaultstate="collapsed" desc="getters-setters">
    
    /**
     * @return the attrs
     */
    public List<AttrTO> getAttrs() {
        return attrs;
    }

    /**
     * @param attrs the attrs to set
     */
    public void setAttrs(List<AttrTO> attrs) {
        this.attrs = attrs;
    }
    
    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the proyecto
     */
    public ProyectoTO getProyecto() {
        return proyecto;
    }

    /**
     * @param proyecto the proyecto to set
     */
    public void setProyecto(ProyectoTO proyecto) {
        this.proyecto = proyecto;
    }
    
}
//</editor-fold>