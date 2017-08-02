package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "elementos_ficha")
@XmlRootElement
public class ElementosFicha implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "elem_id")
    private Integer elemId;
    @Column(name = "elem_nombre")
    private String elemNombre;

    public ElementosFicha() {
    }

    public ElementosFicha(Integer elemId) {
        this.elemId = elemId;
    }

    public Integer getElemId() {
        return elemId;
    }

    public void setElemId(Integer elemId) {
        this.elemId = elemId;
    }

    public String getElemNombre() {
        return elemNombre;
    }

    public void setElemNombre(String elemNombre) {
        this.elemNombre = elemNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elemId != null ? elemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ElementosFicha)) {
            return false;
        }
        ElementosFicha other = (ElementosFicha) object;
        if ((this.elemId == null && other.elemId != null) || (this.elemId != null && !this.elemId.equals(other.elemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ElementosFicha[ elemId=" + elemId + " ]";
    }

}
