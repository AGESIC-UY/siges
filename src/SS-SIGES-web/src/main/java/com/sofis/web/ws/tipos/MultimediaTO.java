package com.sofis.web.ws.tipos;

import java.io.Serializable;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
//@XmlRootElement
public class MultimediaTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String mail;
    private String pass;
    private String token;
    private Integer orgPk;
    private Integer proyPk;
    private String comentario;
    private String msg;

    public MultimediaTO() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getOrgPk() {
        return orgPk;
    }

    public void setOrgPk(Integer orgPk) {
        this.orgPk = orgPk;
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
