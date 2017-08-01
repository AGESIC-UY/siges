package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "valor_calidad_codigos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorCalidadCodigos.findAll", query = "SELECT v FROM ValorCalidadCodigos v"),
    @NamedQuery(name = "ValorCalidadCodigos.findByVcaPk", query = "SELECT v FROM ValorCalidadCodigos v WHERE v.vcaPk = :vcaPk"),
    @NamedQuery(name = "ValorCalidadCodigos.findByVcaCodigo", query = "SELECT v FROM ValorCalidadCodigos v WHERE v.vcaCodigo = :vcaCodigo"),
    @NamedQuery(name = "ValorCalidadCodigos.findByVcaNombre", query = "SELECT v FROM ValorCalidadCodigos v WHERE v.vcaNombre = :vcaNombre"),
    @NamedQuery(name = "ValorCalidadCodigos.findByVcaHabilitado", query = "SELECT v FROM ValorCalidadCodigos v WHERE v.vcaHabilitado = :vcaHabilitado")})
public class ValorCalidadCodigos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vca_pk")
    private Integer vcaPk;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "vca_codigo")
    private String vcaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "vca_nombre")
    private String vcaNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vca_habilitado")
    private boolean vcaHabilitado;

    public ValorCalidadCodigos() {
    }

    public ValorCalidadCodigos(Integer vcaPk) {
        this.vcaPk = vcaPk;
    }

    public ValorCalidadCodigos(Integer vcaPk, String vcaCodigo, String vcaNombre, boolean vcaHabilitado) {
        this.vcaPk = vcaPk;
        this.vcaCodigo = vcaCodigo;
        this.vcaNombre = vcaNombre;
        this.vcaHabilitado = vcaHabilitado;
    }

    public Integer getVcaPk() {
        return vcaPk;
    }

    public void setVcaPk(Integer vcaPk) {
        this.vcaPk = vcaPk;
    }

    public String getVcaCodigo() {
        return vcaCodigo;
    }

    public void setVcaCodigo(String vcaCodigo) {
        this.vcaCodigo = vcaCodigo;
    }

    public String getVcaNombre() {
        return vcaNombre;
    }

    public void setVcaNombre(String vcaNombre) {
        this.vcaNombre = vcaNombre;
    }

    public boolean getVcaHabilitado() {
        return vcaHabilitado;
    }

    public void setVcaHabilitado(boolean vcaHabilitado) {
        this.vcaHabilitado = vcaHabilitado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vcaPk != null ? vcaPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ValorCalidadCodigos)) {
            return false;
        }
        ValorCalidadCodigos other = (ValorCalidadCodigos) object;
        if ((this.vcaPk == null && other.vcaPk != null) || (this.vcaPk != null && !this.vcaPk.equals(other.vcaPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ValorCalidadCodigos[ vcaPk=" + vcaPk + " ]";
    }

}
