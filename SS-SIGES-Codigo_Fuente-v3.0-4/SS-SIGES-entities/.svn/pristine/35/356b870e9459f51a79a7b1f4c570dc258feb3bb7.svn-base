package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tipos_media")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposMedia.findAll", query = "SELECT t FROM TiposMedia t"),
    @NamedQuery(name = "TiposMedia.findByTipPk", query = "SELECT t FROM TiposMedia t WHERE t.tipPk = :tipPk"),
    @NamedQuery(name = "TiposMedia.findByTipCod", query = "SELECT t FROM TiposMedia t WHERE t.tipCod = :tipCod"),
    @NamedQuery(name = "TiposMedia.findByTipNombre", query = "SELECT t FROM TiposMedia t WHERE t.tipNombre = :tipNombre")})
public class TiposMedia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tip_pk")
    private Integer tipPk;
    @Column(name = "tip_cod")
    private String tipCod;
    @Column(name = "tip_nombre")
    private String tipNombre;
    
//    @OneToMany(mappedBy = "mediaTipoFk")
//    private List<MediaProyectos> mediaProyectosList;

    public TiposMedia() {
    }

    public TiposMedia(Integer tipPk, String tipCod, String tipNombre) {
        this.tipPk = tipPk;
        this.tipCod = tipCod;
        this.tipNombre = tipNombre;
    }

    public TiposMedia(Integer tipPk) {
        this.tipPk = tipPk;
    }

    public Integer getTipPk() {
        return tipPk;
    }

    public void setTipPk(Integer tipPk) {
        this.tipPk = tipPk;
    }

    public String getTipCod() {
        return tipCod;
    }

    public void setTipCod(String tipCod) {
        this.tipCod = tipCod;
    }

    public String getTipNombre() {
        return tipNombre;
    }

    public void setTipNombre(String tipNombre) {
        this.tipNombre = tipNombre;
    }

//    @XmlTransient
//    public List<MediaProyectos> getMediaProyectosList() {
//        return mediaProyectosList;
//    }
//
//    public void setMediaProyectosList(List<MediaProyectos> mediaProyectosList) {
//        this.mediaProyectosList = mediaProyectosList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipPk != null ? tipPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiposMedia)) {
            return false;
        }
        TiposMedia other = (TiposMedia) object;
        if ((this.tipPk == null && other.tipPk != null) || (this.tipPk != null && !this.tipPk.equals(other.tipPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.TiposMedia[ tipPk=" + tipPk + " ]";
    }

    public boolean isTipoMediaCod(String cod) {
        return StringsUtils.isEmpty(cod) ? false : this.tipCod.equals(cod);
    }
}
