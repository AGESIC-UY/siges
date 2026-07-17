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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grupo_trabajo_operacion_detalle")
@XmlRootElement
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrupoTrabajoOperacionDetalle implements Serializable {

    public static final int NOMBRE_LENGHT = 300;
    public static final int PROC_COMPRA_LENGHT = 20;
    public static final int PROC_COMPRA_GRP_LENGHT = 20;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gto_pk")
    private Integer gto_pk;

    @JoinColumn(name = "gto_grupo_fk", referencedColumnName = "gtb_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private GrupoTrabajo gtoGrupoFk;

    @JoinColumn(name = "gto_operacion_fk", referencedColumnName = "opd_pk")
    @ManyToOne(optional = false)
    private OperacionDetalle gtoOperacionFk;

    public int hashCode() {
        int hash = 0;
        hash += (gto_pk != null ? gto_pk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof GrupoTrabajoOperacionDetalle)) {
            return false;
        }
        GrupoTrabajoOperacionDetalle other = (GrupoTrabajoOperacionDetalle) object;
        if (gto_pk == null || other.gto_pk == null) {
            return this == object;
        }

        if ((this.gto_pk == null && other.gto_pk != null) || (this.gto_pk != null && !this.gto_pk.equals(other.gto_pk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Adquisicion[ adqPk=" + gto_pk + " ]";
    }

    public Integer getGto_pk() {
        return gto_pk;
    }

    public void setGto_pk(Integer gto_pk) {
        this.gto_pk = gto_pk;
    }

    public GrupoTrabajo getGtoGrupoFk() {
        return gtoGrupoFk;
    }

    public void setGtoGrupoFk(GrupoTrabajo gtoGrupoFk) {
        this.gtoGrupoFk = gtoGrupoFk;
    }

    public OperacionDetalle getGtoOperacionFk() {
        return gtoOperacionFk;
    }

    public void setGtoOperacionFk(OperacionDetalle gtoOperacionFk) {
        this.gtoOperacionFk = gtoOperacionFk;
    }

  

}
