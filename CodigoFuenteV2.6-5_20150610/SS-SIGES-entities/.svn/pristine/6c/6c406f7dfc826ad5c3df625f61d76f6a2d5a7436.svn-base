package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "registros_horas")
@XmlRootElement
public class RegistrosHoras implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rh_pk")
    private Integer rhPk;
    @Temporal(TemporalType.DATE)
    @Column(name = "rh_fecha")
    private Date rhFecha;
    @Column(name = "rh_horas")
    private Float rhHoras;
    @Column(name = "rh_comentario")
    private String rhComentario;
    @Column(name = "rh_aprobado")
    private Boolean rhAprobado;
    @JoinColumn(name = "rh_usu_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario rhUsuarioFk;
    @JoinColumn(name = "rh_proy_fk", referencedColumnName = "proy_pk")
    @ManyToOne(optional = false)
    private Proyectos rhProyectoFk;
    @JoinColumn(name = "rh_ent_fk", referencedColumnName = "ent_pk")
    @ManyToOne(optional = false)
    private Entregables rhEntregableFk;

    public RegistrosHoras() {
    }

    public Integer getRhPk() {
        return rhPk;
    }

    public void setRhPk(Integer rhPk) {
        this.rhPk = rhPk;
    }

    public Boolean getRhAprobado() {
        return rhAprobado;
    }

    public void setRhAprobado(Boolean rhAprobado) {
        this.rhAprobado = rhAprobado;
    }

    public Date getRhFecha() {
        return rhFecha;
    }

    public void setRhFecha(Date rhFecha) {
        this.rhFecha = rhFecha;
    }

    public Float getRhHoras() {
        return rhHoras;
    }

    public void setRhHoras(Float rhHoras) {
        this.rhHoras = rhHoras;
    }

    public String getRhComentario() {
        return rhComentario;
    }

    public void setRhComentario(String rhComentario) {
        this.rhComentario = rhComentario;
    }

    public Entregables getRhEntregableFk() {
        return rhEntregableFk;
    }

    public void setRhEntregableFk(Entregables rhEntregableFk) {
        this.rhEntregableFk = rhEntregableFk;
    }

    public SsUsuario getRhUsuarioFk() {
        return rhUsuarioFk;
    }

    public void setRhUsuarioFk(SsUsuario rhUsuarioFk) {
        this.rhUsuarioFk = rhUsuarioFk;
    }

    public Proyectos getRhProyectoFk() {
        return rhProyectoFk;
    }

    public void setRhProyectoFk(Proyectos rhProyectoFk) {
        this.rhProyectoFk = rhProyectoFk;
    }
    
    public String getRhComentario(int largo) {
        return StringsUtils.recortarTexto(rhComentario, largo);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rhPk != null ? rhPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof RegistrosHoras)) {
            return false;
        }
        RegistrosHoras other = (RegistrosHoras) object;
        if ((this.rhPk == null && other.rhPk != null) || (this.rhPk != null && !this.rhPk.equals(other.rhPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.RegistrosHoras[ partPk=" + rhPk + " ]";
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof RegistrosHoras)) {
            return 1;
        }
        RegistrosHoras otro = (RegistrosHoras) o;
        if (this.rhFecha.equals(otro.rhFecha)) {
            if (this.rhProyectoFk.getProyNombre().equals(otro.rhProyectoFk.getProyNombre())) {
                return this.rhEntregableFk.getEntNombre().compareTo(otro.rhEntregableFk.getEntNombre());
            }
            return this.rhProyectoFk.getProyNombre().compareTo(otro.rhProyectoFk.getProyNombre());
        }
        return this.rhFecha.compareTo(otro.rhFecha);
    }
}
