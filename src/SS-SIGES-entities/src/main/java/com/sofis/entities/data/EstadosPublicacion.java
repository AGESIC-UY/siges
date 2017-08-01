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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Estados de la publicación de un proyecto en el visualizador.
 * @author Usuario
 */
@Entity
@Table(name = "estados_publicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosPublicacion.findAll", query = "SELECT e FROM EstadosPublicacion e"),
    @NamedQuery(name = "EstadosPublicacion.findByEstPubPk", query = "SELECT e FROM EstadosPublicacion e WHERE e.estPubPk = :estPubPk"),
    @NamedQuery(name = "EstadosPublicacion.findByEstPubCodigo", query = "SELECT e FROM EstadosPublicacion e WHERE e.estPubCodigo = :estPubCodigo"),
    @NamedQuery(name = "EstadosPublicacion.findByEstPubNombre", query = "SELECT e FROM EstadosPublicacion e WHERE e.estPubNombre = :estPubNombre")})
public class EstadosPublicacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "est_pub_pk")
    private Integer estPubPk;
    @Size(max = 45)
    @Column(name = "est_pub_codigo")
    private String estPubCodigo;
    @Size(max = 145)
    @Column(name = "est_pub_nombre")
    private String estPubNombre;
    
    public EstadosPublicacion() {
    }

    public EstadosPublicacion(Integer estPubPk, String estPubCodigo, String estPubNombre) {
        this.estPubPk = estPubPk;
        this.estPubCodigo = estPubCodigo;
        this.estPubNombre = estPubNombre;
    }

    public EstadosPublicacion(Integer estPubPk) {
        this.estPubPk = estPubPk;
    }

    public Integer getEstPubPk() {
        return estPubPk;
    }

    public void setEstPubPk(Integer estPubPk) {
        this.estPubPk = estPubPk;
    }

    public String getEstPubCodigo() {
        return estPubCodigo;
    }

    public void setEstPubCodigo(String estPubCodigo) {
        this.estPubCodigo = estPubCodigo;
    }

    public String getEstPubNombre() {
        return estPubNombre;
    }

    public void setEstPubNombre(String estPubNombre) {
        this.estPubNombre = estPubNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estPubPk != null ? estPubPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof EstadosPublicacion)) {
            return false;
        }
        EstadosPublicacion other = (EstadosPublicacion) object;
        if ((this.estPubPk == null && other.estPubPk != null) || (this.estPubPk != null && !this.estPubPk.equals(other.estPubPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.EstadosPublicacion[ estPubPk=" + estPubPk + " ]";
    }
}
