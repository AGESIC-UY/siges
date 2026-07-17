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
@Table(name = "grupos_trabajo_usuarios")
@XmlRootElement
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrupoTrabajoUsuario implements Serializable {

    public static final int NOMBRE_LENGHT = 300;
    public static final int PROC_COMPRA_LENGHT = 20;
    public static final int PROC_COMPRA_GRP_LENGHT = 20;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gtu_pk")
    private Integer gtuPk;

    @JoinColumn(name = "gtu_grupo_fk", referencedColumnName = "gtb_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private GrupoTrabajo gtuGrupoFk;

    @JoinColumn(name = "gtu_usuario_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario gtuUsuarioFk;

    public int hashCode() {
        int hash = 0;
        hash += (gtuPk != null ? gtuPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof GrupoTrabajoUsuario)) {
            return false;
        }
        GrupoTrabajoUsuario other = (GrupoTrabajoUsuario) object;
        if (gtuPk == null || other.gtuPk == null) {
            return this == object;
        }

        if ((this.gtuPk == null && other.gtuPk != null) || (this.gtuPk != null && !this.gtuPk.equals(other.gtuPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Adquisicion[ adqPk=" + gtuPk + " ]";
    }

    public Integer getGtuPk() {
        return gtuPk;
    }

    public void setGtuPk(Integer gtuPk) {
        this.gtuPk = gtuPk;
    }

    public GrupoTrabajo getGtuGrupoFk() {
        return gtuGrupoFk;
    }

    public void setGtuGrupoFk(GrupoTrabajo gtuGrupoFk) {
        this.gtuGrupoFk = gtuGrupoFk;
    }

    public SsUsuario getGtuUsuarioFk() {
        return gtuUsuarioFk;
    }

    public void setGtuUsuarioFk(SsUsuario gtuUsuarioFk) {
        this.gtuUsuarioFk = gtuUsuarioFk;
    }

}
