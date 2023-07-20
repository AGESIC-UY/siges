package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "procedimiento_compra_info")
@XmlRootElement
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimientoCompraInfo implements Serializable {

    public static final int NOMBRE_LENGHT = 300;
    public static final int PROC_COMPRA_LENGHT = 20;
    public static final int PROC_COMPRA_GRP_LENGHT = 20;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pci_pk")
    private Integer pciPk;

    @Basic(optional = false)
    @Column(name = "pci_nombre")
    private String pciNombre;

    @Basic(optional = false)
    @Column(name = "pci_descripcion")
    private String pciDescripcion;

    @Column(name = "pci_adquisicionId")
    private Integer pciAdquisicionId;

    
    @Column(name = "pci_proyecto_fk")
    private Integer pciProyectoFK;
    
    
    @JoinColumn(name = "pci_adquisicion_fk", referencedColumnName = "adq_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    private Adquisicion pciAdquisicionFK;

    @JoinColumn(name = "pci_organismo_fk", referencedColumnName = "org_pk")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Organismos pciOrgFk;

    @Column(name = "pci_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date pciFecha;

    public int hashCode() {
        int hash = 0;
        hash += (pciPk != null ? pciPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof ProcedimientoCompraInfo)) {
            return false;
        }
        ProcedimientoCompraInfo other = (ProcedimientoCompraInfo) object;
        if (pciPk == null || other.pciPk == null) {
            return this == object;
        }

        if ((this.pciPk == null && other.pciPk != null) || (this.pciPk != null && !this.pciPk.equals(other.pciPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Adquisicion[ adqPk=" + pciPk + " ]";
    }

    public Integer getPciPk() {
        return pciPk;
    }

    public void setPciPk(Integer pciPk) {
        this.pciPk = pciPk;
    }

    public String getPciNombre() {
        return pciNombre;
    }

    public void setPciNombre(String pciNombre) {
        this.pciNombre = pciNombre;
    }

    public String getPciDescripcion() {
        return pciDescripcion;
    }

    public void setPciDescripcion(String pciDescripcion) {
        this.pciDescripcion = pciDescripcion;
    }

    public Integer getPciAdquisicionId() {
        return pciAdquisicionId;
    }

    public void setPciAdquisicionId(Integer pciAdquisicionId) {
        this.pciAdquisicionId = pciAdquisicionId;
    }

    public Adquisicion getPciAdquisicionFK() {
        return pciAdquisicionFK;
    }

    public void setPciAdquisicionFK(Adquisicion pciAdquisicionFK) {
        this.pciAdquisicionFK = pciAdquisicionFK;
    }

    public Organismos getPciOrgFk() {
        return pciOrgFk;
    }

    public void setPciOrgFk(Organismos pciOrgFk) {
        this.pciOrgFk = pciOrgFk;
    }

    public Date getPciFecha() {
        return pciFecha;
    }

    public void setPciFecha(Date pciFecha) {
        this.pciFecha = pciFecha;
    }

    public Integer getPciProyectoFK() {
        return pciProyectoFK;
    }

    public void setPciProyectoFK(Integer pciProyectoFK) {
        this.pciProyectoFK = pciProyectoFK;
    }
    
}
