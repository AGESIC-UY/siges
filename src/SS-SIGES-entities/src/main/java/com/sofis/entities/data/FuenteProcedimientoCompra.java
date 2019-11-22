package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "fuentes_procedimiento_compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuenteProcedimientoCompra.findAll", query = "SELECT o FROM FuenteProcedimientoCompra o")
    ,
    @NamedQuery(name = "FuenteProcedimientoCompra.findByFueProComPk", query = "SELECT o FROM FuenteProcedimientoCompra o WHERE o.fueProComPk = :fueProComPk")
    ,
    @NamedQuery(name = "FuenteProcedimientoCompra.findByFueProComNombre", query = "SELECT o FROM FuenteProcedimientoCompra o WHERE o.fueProComFuente = :fueProComFuente")
    ,
    @NamedQuery(name = "FuenteProcedimientoCompra.findByFueProComDescripcion", query = "SELECT o FROM FuenteProcedimientoCompra o WHERE o.fueProComProcedimientoCompra = :fueProComProcedimientoCompra")
})
public class FuenteProcedimientoCompra implements Serializable {

    public static final int NOMBRE_LENGHT = 100;
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fue_pro_com_pk")
    private Integer fueProComPk;

    @Size(max = 100)
    @Column(name = "fue_pro_com_fuente")
    private String fueProComFuente;

    @Size(max = 100)
    @Column(name = "fue_pro_com_procedimiento_compra")
    private String fueProComProcedimientoCompra;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "fue_pro_com_cau_com", joinColumns = {
        @JoinColumn(name = "fue_pro_com_pk", referencedColumnName = "fue_pro_com_pk")},
            inverseJoinColumns = {
                @JoinColumn(name = "cau_com_pk", referencedColumnName = "cau_com_pk")})
    private List<CausalCompra> fueProComCausalesCompra;

    @Column(name = "fue_pro_com_habilitado")
    private Boolean fueProComHabilitado;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "fue_pro_com_org_fk", referencedColumnName = "org_pk")
    private Organismos fueProComOrgFk;

    public FuenteProcedimientoCompra() {
    }

    public FuenteProcedimientoCompra(Integer fueProComPk) {
        this.fueProComPk = fueProComPk;
    }

    public Integer getFueProComPk() {
        return fueProComPk;
    }

    public void setFueProComPk(Integer fueProComPk) {
        this.fueProComPk = fueProComPk;
    }

    public String getFueProComFuente() {
        return fueProComFuente;
    }

    public void setFueProComFuente(String fueProComFuente) {
        this.fueProComFuente = fueProComFuente;
    }

    public String getFueProComProcedimientoCompra() {
        return fueProComProcedimientoCompra;
    }

    public void setFueProComProcedimientoCompra(String fueProComProcedimientoCompra) {
        this.fueProComProcedimientoCompra = fueProComProcedimientoCompra;
    }

    public Boolean getFueProComHabilitado() {
        return fueProComHabilitado;
    }

    public void setFueProComHabilitado(Boolean fueProComHabilitado) {
        this.fueProComHabilitado = fueProComHabilitado;
    }

    public List<CausalCompra> getFueProComCausalesCompra() {
        return fueProComCausalesCompra;
    }

    public void setFueProComCausalesCompra(List<CausalCompra> fueProComCausalesCompra) {
        this.fueProComCausalesCompra = fueProComCausalesCompra;
    }

    public Organismos getFueProComOrgFk() {
        return fueProComOrgFk;
    }

    public void setFueProComOrgFk(Organismos fueProComOrgFk) {
        this.fueProComOrgFk = fueProComOrgFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fueProComPk != null ? fueProComPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuenteProcedimientoCompra)) {
            return false;
        }
        FuenteProcedimientoCompra other = (FuenteProcedimientoCompra) object;
        if ((this.fueProComPk == null && other.fueProComPk != null) || (this.fueProComPk != null && !this.fueProComPk.equals(other.fueProComPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.FuenteProcedimientoCompra[ fueProComPk=" + fueProComPk + " ]";
    }

}
