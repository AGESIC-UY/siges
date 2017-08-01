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
@XmlRootElement(name="responseGuardarProyecto")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ResponseGuardarProyectoTO {
    
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "msg")
    private String msg;
    @XmlElement(name = "proyPk")
    private Integer proyPk;
    @XmlElement(name = "attrs")
    private List<AttrTO> attrs;
    
    //<editor-fold defaultstate="collapsed" desc="getters-setters">

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the proyPk
     */
    public Integer getProyPk() {
        return proyPk;
    }

    /**
     * @param proyPk the proyPk to set
     */
    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

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
    
    
    
}
//</editor-fold>