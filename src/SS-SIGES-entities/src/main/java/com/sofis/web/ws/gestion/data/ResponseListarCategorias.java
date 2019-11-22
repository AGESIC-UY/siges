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
@XmlRootElement(name="responseListarCategorias")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ResponseListarCategorias {

    
    @XmlElement(name = "status")
    private String status;
    @XmlElement(name = "msg")
    private String msg;
    @XmlElement(name = "categorias")
    private List<CategoriaProyectoTO> categorias;
    @XmlElement(name = "attrs")
    private List<AttrTO> attrs;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CategoriaProyectoTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaProyectoTO> categorias) {
        this.categorias = categorias;
    }

    public List<AttrTO> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttrTO> attrs) {
        this.attrs = attrs;
    }

    
}
