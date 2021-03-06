package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "busq_filtro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusquedaFiltro.findAll", query = "SELECT b FROM BusquedaFiltro b"),
    @NamedQuery(name = "BusquedaFiltro.findByBusqFiltroPk", query = "SELECT b FROM BusquedaFiltro b WHERE b.busqFiltroPk = :busqFiltroPk"),
    @NamedQuery(name = "BusquedaFiltro.findByBusqFiltroXml", query = "SELECT b FROM BusquedaFiltro b WHERE b.busqFiltroXml = :busqFiltroXml")})
public class BusquedaFiltro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "busq_filtro_pk")
    private Integer busqFiltroPk;
    @JoinColumn(name = "busq_filtro_usu_fk", referencedColumnName = "usu_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SsUsuario busqFiltroUsuarioFk;
    @JoinColumn(name = "busq_filtro_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(fetch = FetchType.LAZY)
    private Organismos busqFiltroOrgaFk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "busq_filtro_xml")
    private String busqFiltroXml;

    public String getVersion() {
        return "1";
    }
    
    public BusquedaFiltro() {
    }

    public BusquedaFiltro(Integer busqFiltroPk) {
        this.busqFiltroPk = busqFiltroPk;
    }

    public BusquedaFiltro(Integer busqFiltroPk, String busqFiltroXml) {
        this.busqFiltroPk = busqFiltroPk;
        this.busqFiltroXml = busqFiltroXml;
    }

    public Integer getBusqFiltroPk() {
        return busqFiltroPk;
    }

    public void setBusqFiltroPk(Integer busqFiltroPk) {
        this.busqFiltroPk = busqFiltroPk;
    }

    public SsUsuario getBusqFiltroUsuarioFk() {
        return busqFiltroUsuarioFk;
    }

    public void setBusqFiltroUsuarioFk(SsUsuario busqFiltroUsuarioFk) {
        this.busqFiltroUsuarioFk = busqFiltroUsuarioFk;
    }

    public Organismos getBusqFiltroOrgaFk() {
        return busqFiltroOrgaFk;
    }

    public void setBusqFiltroOrgaFk(Organismos busqFiltroOrgaFk) {
        this.busqFiltroOrgaFk = busqFiltroOrgaFk;
    }

    public String getBusqFiltroXml() {
        return busqFiltroXml;
    }

    public void setBusqFiltroXml(String busqFiltroXml) {
        this.busqFiltroXml = busqFiltroXml;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (busqFiltroPk != null ? busqFiltroPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof BusquedaFiltro)) {
            return false;
        }
        BusquedaFiltro other = (BusquedaFiltro) object;
        if ((this.busqFiltroPk == null && other.busqFiltroPk != null) || (this.busqFiltroPk != null && !this.busqFiltroPk.equals(other.busqFiltroPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.BusquedaFiltro[ busqFiltroPk=" + busqFiltroPk + " ]";
    }
}
