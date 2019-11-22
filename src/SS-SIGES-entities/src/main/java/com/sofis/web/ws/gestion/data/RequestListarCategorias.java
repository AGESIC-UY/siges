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
 * @author usuario
 */
@XmlRootElement(name="requestListarCategorias")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class RequestListarCategorias {
    
    @XmlElement (name = "token", required = true)
    private String token;
    @XmlElement (name = "idOrg", required = true)
    private Integer idOrg;
    
    @XmlElement(name="attrs")
    private List<AttrTO> attrs;
    
    //<editor-fold defaultstate="collapsed" desc="getters-setters">

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdOrg() {
        return idOrg;
    }

    public void setIdOrg(Integer idOrg) {
        this.idOrg = idOrg;
    }

    public List<AttrTO> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrTO> attrs) {
        this.attrs = attrs;
    }
    
  
}
