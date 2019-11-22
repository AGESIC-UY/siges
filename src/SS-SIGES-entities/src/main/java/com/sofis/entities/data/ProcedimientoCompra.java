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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "procedimiento_compra")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "ProcedimientoCompra.findAll", query = "SELECT c FROM ProcedimientoCompra c"),
    @NamedQuery(name = "ProcedimientoCompra.findByComPk", query = "SELECT c FROM ProcedimientoCompra c WHERE c.procCompPk = :procCompPk"),
    @NamedQuery(name = "ProcedimientoCompra.findByComNombre", query = "SELECT c FROM ProcedimientoCompra c WHERE c.procCompNombre = :procCompNombre"),
    @NamedQuery(name = "ProcedimientoCompra.findByOrgPk", query = "SELECT c FROM ProcedimientoCompra c WHERE c.procCompOrgFk.orgPk = :orgPk ORDER BY c.procCompNombre")})
public class ProcedimientoCompra implements Serializable {

    public static final int NOMBRE_LENGHT = 300;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proc_comp_pk")
    private Integer procCompPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proc_comp_nombre")
    private String procCompNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proc_comp_descripcion")
    private String procCompDescripcion;
    @JoinColumn(name = "proc_comp_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos procCompOrgFk;
    @Column(name = "proc_comp_habilitado")
    private Boolean procCompHabilitado;

    public ProcedimientoCompra() {
    }

    public ProcedimientoCompra(Integer comPk) {
            this.procCompPk = comPk;
    }

    public ProcedimientoCompra(Integer comPk, String comNombre) {
            this.procCompPk = comPk;
            this.procCompNombre = comNombre;
    }

    public Integer getProcCompPk() {
            return procCompPk;
    }

    public void setProcCompPk(Integer procCompPk) {
            this.procCompPk = procCompPk;
    }

    public String getProcCompNombre() {
            return procCompNombre;
    }

    public void setProcCompNombre(String procCompNombre) {
            this.procCompNombre = procCompNombre;
    }

    public Organismos getProcCompOrgFk() {
            return procCompOrgFk;
    }

    public void setProcCompOrgFk(Organismos procCompOrgFk) {
            this.procCompOrgFk = procCompOrgFk;
    }

    @Override
    public int hashCode() {
            int hash = 0;
            hash += (procCompPk != null ? procCompPk.hashCode() : 0);
            return hash;
    }

    @Override
    public boolean equals(Object object) {

            if (!(object instanceof ProcedimientoCompra)) {
                    return false;
            }
            ProcedimientoCompra other = (ProcedimientoCompra) object;
            if ((this.procCompPk == null && other.procCompPk != null) || (this.procCompPk != null && !this.procCompPk.equals(other.procCompPk))) {
                    return false;
            }
            return true;
    }

    @Override
    public String toString() {
            return "com.sofis.entities.data.ProcedimientoCompra[ procCompPk=" + procCompPk + " ]";
    }

    public String getProcCompDescripcion() {
            return procCompDescripcion;
    }

    public void setProcCompDescripcion(String procCompDescripcion) {
            this.procCompDescripcion = procCompDescripcion;
    }

    public Boolean getProcCompHabilitado() {
        return procCompHabilitado;
    }

    public void setProcCompHabilitado(Boolean procCompHabilitado) {
        this.procCompHabilitado = procCompHabilitado;
    }

}
