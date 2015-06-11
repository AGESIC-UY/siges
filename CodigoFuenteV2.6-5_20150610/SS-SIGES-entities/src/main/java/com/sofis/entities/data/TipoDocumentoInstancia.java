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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipo_documento_instancia")
@XmlRootElement
public class TipoDocumentoInstancia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tipodoc_inst_pk")
    private Integer tipodocInstPk;
    @Column(name = "tipodoc_inst_exigido_desde")
    private Integer tipodocExigidoDesde;
    @Column(name = "tipodoc_inst_peso")
    private Integer tipodocInstPeso;
    @JoinColumn(name = "tipodoc_inst_tipodoc_fk", referencedColumnName = "tipdoc_pk")
    @ManyToOne(optional = false)
    private TipoDocumento tipodocInstTipoDocFk;
    @Column(name = "tipodoc_inst_proy_fk")
    private Integer tipodocInstProyFk;
    @Column(name = "tipodoc_inst_prog_fk")
    private Integer tipodocInstProgFk;
    @Column(name = "tipodoc_inst_resum_ejecutivo")
    private Boolean tipodocInstResumenEjecutivo;
    
    @Transient
    private String docsEstadoColor;
    @Transient
    private Double docsEstado;

    public TipoDocumentoInstancia() {
    }

    public TipoDocumentoInstancia(Integer tipodocInstPk) {
        this.tipodocInstPk = tipodocInstPk;
    }

    public Integer getTipodocInstPk() {
        return tipodocInstPk;
    }

    public void setTipodocInstPk(Integer tipodocInstPk) {
        this.tipodocInstPk = tipodocInstPk;
    }

    public Integer getTipodocExigidoDesde() {
        return tipodocExigidoDesde;
    }

    public void setTipodocExigidoDesde(Integer tipodocExigidoDesde) {
        this.tipodocExigidoDesde = tipodocExigidoDesde;
    }

    public TipoDocumento getTipodocInstTipoDocFk() {
        return tipodocInstTipoDocFk;
    }

    public void setTipodocInstTipoDocFk(TipoDocumento tipodocInstTipoDocFk) {
        this.tipodocInstTipoDocFk = tipodocInstTipoDocFk;
    }

    public Integer getTipodocInstPeso() {
        return tipodocInstPeso;
    }

    public void setTipodocInstPeso(Integer tipodocInstPeso) {
        this.tipodocInstPeso = tipodocInstPeso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipodocInstPk != null ? tipodocInstPk.hashCode() : 0);
        return hash;
    }

    public Integer getTipodocInstProyFk() {
        return tipodocInstProyFk;
    }

    public void setTipodocInstProyFk(Integer tipodocInstProyFk) {
        this.tipodocInstProyFk = tipodocInstProyFk;
    }

    public Integer getTipodocInstProgFk() {
        return tipodocInstProgFk;
    }

    public void setTipodocInstProgFk(Integer tipodocInstProgFk) {
        this.tipodocInstProgFk = tipodocInstProgFk;
    }

    public String getDocsEstadoColor() {
        return docsEstadoColor;
    }

    public void setDocsEstadoColor(String docsEstadoColor) {
        this.docsEstadoColor = docsEstadoColor;
    }

    public Double getDocsEstado() {
        return docsEstado;
    }

    public void setDocsEstado(Double docsEstado) {
        this.docsEstado = docsEstado;
    }

    public Boolean getTipodocInstResumenEjecutivo() {
        return tipodocInstResumenEjecutivo;
    }

    public void setTipodocInstResumenEjecutivo(Boolean tipodocInstResumenEjecutivo) {
        this.tipodocInstResumenEjecutivo = tipodocInstResumenEjecutivo;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumentoInstancia)) {
            return false;
        }
        TipoDocumentoInstancia other = (TipoDocumentoInstancia) object;
        if ((this.tipodocInstPk == null && other.tipodocInstPk != null) 
                || (this.tipodocInstPk != null && !this.tipodocInstPk.equals(other.tipodocInstPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TipoDocumentoInstancia[ tipodocInstPk=" + tipodocInstPk + " ]";
    }
}
