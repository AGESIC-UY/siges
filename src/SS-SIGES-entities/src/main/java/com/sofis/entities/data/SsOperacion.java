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
@Table(name = "ss_operacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SsOperacion.findAll", query = "SELECT s FROM SsOperacion s"),
    @NamedQuery(name = "SsOperacion.findByOpeId", query = "SELECT s FROM SsOperacion s WHERE s.opeId = :opeId"),
    @NamedQuery(name = "SsOperacion.findByOpeCodigo", query = "SELECT s FROM SsOperacion s WHERE s.opeCodigo = :opeCodigo"),
    @NamedQuery(name = "SsOperacion.findByOpeNombre", query = "SELECT s FROM SsOperacion s WHERE s.opeNombre = :opeNombre"),
    @NamedQuery(name = "SsOperacion.findByOpeOrigen", query = "SELECT s FROM SsOperacion s WHERE s.opeOrigen = :opeOrigen"),
    @NamedQuery(name = "SsOperacion.findByOpeTipocampo", query = "SELECT s FROM SsOperacion s WHERE s.opeTipocampo = :opeTipocampo"),
    @NamedQuery(name = "SsOperacion.findByOpeUserCode", query = "SELECT s FROM SsOperacion s WHERE s.opeUserCode = :opeUserCode"),
    @NamedQuery(name = "SsOperacion.findByOpeVersion", query = "SELECT s FROM SsOperacion s WHERE s.opeVersion = :opeVersion"),
    @NamedQuery(name = "SsOperacion.findByOpeVigente", query = "SELECT s FROM SsOperacion s WHERE s.opeVigente = :opeVigente")})
public class SsOperacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ope_id", nullable = false)
    private Integer opeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ope_codigo", nullable = false, length = 255)
    private String opeCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "ope_descripcion", nullable = false, length = 2147483647)
    private String opeDescripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ope_nombre", nullable = false, length = 255)
    private String opeNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ope_origen", nullable = false, length = 255)
    private String opeOrigen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ope_tipocampo", nullable = false, length = 255)
    private String opeTipocampo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ope_user_code", nullable = false)
    private int opeUserCode;
    @Column(name = "ope_version")
    private Integer opeVersion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ope_vigente", nullable = false)
    private boolean opeVigente;
    @JoinColumn(name = "ope_categoria_id", referencedColumnName = "cat_id")
    @ManyToOne
    private SsCategoper opeCategoriaId;

    public SsOperacion() {
    }

    public SsOperacion(Integer opeId) {
        this.opeId = opeId;
    }

    public SsOperacion(Integer opeId, String opeCodigo, String opeDescripcion, String opeNombre, String opeOrigen, String opeTipocampo, int opeUserCode, boolean opeVigente) {
        this.opeId = opeId;
        this.opeCodigo = opeCodigo;
        this.opeDescripcion = opeDescripcion;
        this.opeNombre = opeNombre;
        this.opeOrigen = opeOrigen;
        this.opeTipocampo = opeTipocampo;
        this.opeUserCode = opeUserCode;
        this.opeVigente = opeVigente;
    }

    public Integer getOpeId() {
        return opeId;
    }

    public void setOpeId(Integer opeId) {
        this.opeId = opeId;
    }

    public String getOpeCodigo() {
        return opeCodigo;
    }

    public void setOpeCodigo(String opeCodigo) {
        this.opeCodigo = opeCodigo;
    }

    public String getOpeDescripcion() {
        return opeDescripcion;
    }

    public void setOpeDescripcion(String opeDescripcion) {
        this.opeDescripcion = opeDescripcion;
    }

    public String getOpeNombre() {
        return opeNombre;
    }

    public void setOpeNombre(String opeNombre) {
        this.opeNombre = opeNombre;
    }

    public String getOpeOrigen() {
        return opeOrigen;
    }

    public void setOpeOrigen(String opeOrigen) {
        this.opeOrigen = opeOrigen;
    }

    public String getOpeTipocampo() {
        return opeTipocampo;
    }

    public void setOpeTipocampo(String opeTipocampo) {
        this.opeTipocampo = opeTipocampo;
    }

    public int getOpeUserCode() {
        return opeUserCode;
    }

    public void setOpeUserCode(int opeUserCode) {
        this.opeUserCode = opeUserCode;
    }

    public Integer getOpeVersion() {
        return opeVersion;
    }

    public void setOpeVersion(Integer opeVersion) {
        this.opeVersion = opeVersion;
    }

    public boolean getOpeVigente() {
        return opeVigente;
    }

    public void setOpeVigente(boolean opeVigente) {
        this.opeVigente = opeVigente;
    }

    public SsCategoper getOpeCategoriaId() {
        return opeCategoriaId;
    }

    public void setOpeCategoriaId(SsCategoper opeCategoriaId) {
        this.opeCategoriaId = opeCategoriaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opeId != null ? opeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SsOperacion)) {
            return false;
        }
        SsOperacion other = (SsOperacion) object;
        if ((this.opeId == null && other.opeId != null) || (this.opeId != null && !this.opeId.equals(other.opeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.gestemp.entities.SsOperacion[ opeId=" + opeId + " ]";
    }
    
}
