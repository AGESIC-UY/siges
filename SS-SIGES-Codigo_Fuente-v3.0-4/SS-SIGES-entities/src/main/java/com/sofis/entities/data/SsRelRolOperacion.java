/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "ss_rel_rol_operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsRelRolOperacion.findAll", query = "SELECT s FROM SsRelRolOperacion s"),
    @NamedQuery(name = "SsRelRolOperacion.findByRelRolOperacionId", query = "SELECT s FROM SsRelRolOperacion s WHERE s.relRolOperacionId = :relRolOperacionId"),
    @NamedQuery(name = "SsRelRolOperacion.findByRelRolOperacionEditable", query = "SELECT s FROM SsRelRolOperacion s WHERE s.relRolOperacionEditable = :relRolOperacionEditable"),
    @NamedQuery(name = "SsRelRolOperacion.findByRelRolOperacionOrigen", query = "SELECT s FROM SsRelRolOperacion s WHERE s.relRolOperacionOrigen = :relRolOperacionOrigen"),
    @NamedQuery(name = "SsRelRolOperacion.findByRelRolOperacionUserCode", query = "SELECT s FROM SsRelRolOperacion s WHERE s.relRolOperacionUserCode = :relRolOperacionUserCode"),
    @NamedQuery(name = "SsRelRolOperacion.findByRelRolOperacionVisible", query = "SELECT s FROM SsRelRolOperacion s WHERE s.relRolOperacionVisible = :relRolOperacionVisible")})
public class SsRelRolOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rel_rol_operacion_id", nullable = false)
    private Integer relRolOperacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rel_rol_operacion_editable", nullable = false)
    private boolean relRolOperacionEditable;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "rel_rol_operacion_origen", nullable = false, length = 255)
    private String relRolOperacionOrigen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rel_rol_operacion_user_code", nullable = false)
    private int relRolOperacionUserCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rel_rol_operacion_visible", nullable = false)
    private boolean relRolOperacionVisible;
     @JoinColumn(name = "rel_rol_operacion_operacion_id", referencedColumnName = "ope_id", nullable = false)
    @ManyToOne(optional = false)
    private SsOperacion relRolOperacionOperacionId;
      @JoinColumn(name = "rel_rol_operacion_rol_id", referencedColumnName = "rol_id", nullable = false)
    @ManyToOne(optional = false)
    private SsRol relRolOperacionRolId;

    public SsRelRolOperacion() {
    }

    public SsRelRolOperacion(Integer relRolOperacionId) {
        this.relRolOperacionId = relRolOperacionId;
    }

    public SsRelRolOperacion(Integer relRolOperacionId, boolean relRolOperacionEditable, String relRolOperacionOrigen, int relRolOperacionUserCode, boolean relRolOperacionVisible) {
        this.relRolOperacionId = relRolOperacionId;
        this.relRolOperacionEditable = relRolOperacionEditable;
        this.relRolOperacionOrigen = relRolOperacionOrigen;
        this.relRolOperacionUserCode = relRolOperacionUserCode;
        this.relRolOperacionVisible = relRolOperacionVisible;
    }

    public Integer getRelRolOperacionId() {
        return relRolOperacionId;
    }

    public void setRelRolOperacionId(Integer relRolOperacionId) {
        this.relRolOperacionId = relRolOperacionId;
    }

    public boolean getRelRolOperacionEditable() {
        return relRolOperacionEditable;
    }

    public void setRelRolOperacionEditable(boolean relRolOperacionEditable) {
        this.relRolOperacionEditable = relRolOperacionEditable;
    }

    public String getRelRolOperacionOrigen() {
        return relRolOperacionOrigen;
    }

    public void setRelRolOperacionOrigen(String relRolOperacionOrigen) {
        this.relRolOperacionOrigen = relRolOperacionOrigen;
    }

    public int getRelRolOperacionUserCode() {
        return relRolOperacionUserCode;
    }

    public void setRelRolOperacionUserCode(int relRolOperacionUserCode) {
        this.relRolOperacionUserCode = relRolOperacionUserCode;
    }

    public boolean getRelRolOperacionVisible() {
        return relRolOperacionVisible;
    }

    public void setRelRolOperacionVisible(boolean relRolOperacionVisible) {
        this.relRolOperacionVisible = relRolOperacionVisible;
    }

    public SsOperacion getRelRolOperacionOperacionId() {
        return relRolOperacionOperacionId;
    }

    public void setRelRolOperacionOperacionId(SsOperacion relRoloperacionOperacionId) {
        this.relRolOperacionOperacionId = relRoloperacionOperacionId;
    }

    public SsRol getRelRolOperacionRolId() {
        return relRolOperacionRolId;
    }

    public void setRelRolOperacionRolId(SsRol relRolOperacionRolId) {
        this.relRolOperacionRolId = relRolOperacionRolId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relRolOperacionId != null ? relRolOperacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SsRelRolOperacion)) {
            return false;
        }
        SsRelRolOperacion other = (SsRelRolOperacion) object;
        if ((this.relRolOperacionId == null && other.relRolOperacionId != null) || (this.relRolOperacionId != null && !this.relRolOperacionId.equals(other.relRolOperacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.SsRelRolOperacion[ relRolOperacionId=" + relRolOperacionId + " ]";
    }
    
}
