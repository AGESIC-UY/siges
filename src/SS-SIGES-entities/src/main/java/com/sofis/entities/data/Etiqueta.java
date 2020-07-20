package com.sofis.entities.data;

import java.io.Serializable;
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
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "etiqueta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etiqueta.findByOrgPk", query = "SELECT e FROM Etiqueta e WHERE e.organismo.orgPk = :orgPk")
})
public class Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "eti_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos organismo;

    @Column(name = "eti_codigo")
    private String codigo;

    @Column(name = "eti_valor")
    private String valor;
    
    @Version
    @Column(name="eti_version")
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Organismos getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismos organismo) {
        this.organismo = organismo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((organismo == null) ? 0 : organismo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Etiqueta other = (Etiqueta) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (organismo == null) {
            if (other.organismo != null)
                return false;
        } else if (!organismo.equals(other.organismo))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Etiqueta[ organismo=" + organismo +
                ", codigo=" + codigo + " ]";
    }

}
