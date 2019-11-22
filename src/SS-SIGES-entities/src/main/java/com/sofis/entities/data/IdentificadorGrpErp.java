/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.Serializable;
import java.util.Objects;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "identificadores_grp_erp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IdentificadorGrpErp.findAll", query = "SELECT o FROM IdentificadorGrpErp o")
    ,
    @NamedQuery(name = "IdentificadorGrpErp.findByIdGrpErpPk", query = "SELECT o FROM IdentificadorGrpErp o WHERE o.idGrpErpPk = :idGrpErpPk")
    ,
    @NamedQuery(name = "IdentificadorGrpErp.findByIdGrpErpNombre", query = "SELECT o FROM IdentificadorGrpErp o WHERE o.idGrpErpNombre = :idGrpErpNombre")
    ,
    @NamedQuery(name = "IdentificadorGrpErp.findByIdGrpErpDescripcion", query = "SELECT o FROM IdentificadorGrpErp o WHERE o.idGrpErpDescripcion = :idGrpErpDescripcion")
})
@Getter
@Setter
public class IdentificadorGrpErp implements Serializable {

    public static final int NOMBRE_LENGHT = 100;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_grp_erp_pk")
    private Integer idGrpErpPk;
    @Size(max = 100)
    @Column(name = "id_grp_erp_nombre")
    private String idGrpErpNombre;
    @Size(max = 300)
    @Column(name = "id_grp_erp_descripcion")
    private String idGrpErpDescripcion;
    @Column(name = "id_grp_erp_habilitado")
    private Boolean idGrpErpHabilitado;
    
    @JoinColumn(name = "id_grp_erp_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos idGrpErpOrgFk;

    @PreUpdate
    public void preUpdate() {
        this.idGrpErpNombre = StringsUtils.normalizarString(this.idGrpErpNombre);
        this.idGrpErpDescripcion = StringsUtils.normalizarString(this.idGrpErpDescripcion);
    }

    @PrePersist
    public void prePersist() {
        this.idGrpErpNombre = StringsUtils.normalizarString(this.idGrpErpNombre);
        this.idGrpErpDescripcion = StringsUtils.normalizarString(this.idGrpErpDescripcion);
    }

    public IdentificadorGrpErp() {
    }

    public IdentificadorGrpErp(Integer idGrpErpPk) {
        this.idGrpErpPk = idGrpErpPk;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idGrpErpPk);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdentificadorGrpErp other = (IdentificadorGrpErp) obj;
        if (!Objects.equals(this.idGrpErpPk, other.idGrpErpPk)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IdentificadorGrpErp{" + "idGrpErpPk=" + idGrpErpPk + '}';
    }
}
