package com.sofis.entities.data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "adquisicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adquisicion.findAll", query = "SELECT a FROM Adquisicion a"),
    @NamedQuery(name = "Adquisicion.findByAdqPk", query = "SELECT a FROM Adquisicion a WHERE a.adqPk = :adqPk"),
    @NamedQuery(name = "Adquisicion.findByAdqNombre", query = "SELECT a FROM Adquisicion a WHERE a.adqNombre = :adqNombre"),
    @NamedQuery(name = "Adquisicion.findByAdqProvOrga", query = "SELECT a FROM Adquisicion a WHERE a.adqProvOrga = :adqProvOrga"),
    @NamedQuery(name = "Adquisicion.findByAdqFuente", query = "SELECT a FROM Adquisicion a WHERE a.adqFuente = :adqFuente"),
    @NamedQuery(name = "Adquisicion.findByAdqMoneda", query = "SELECT a FROM Adquisicion a WHERE a.adqMoneda = :adqMoneda")})
public class Adquisicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "adq_pk")
    private Integer adqPk;
    @JoinColumn(name = "adq_pre_fk", referencedColumnName = "pre_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Presupuesto adqPreFk;
    @Basic(optional = false)
    @Column(name = "adq_nombre")
    private String adqNombre;
    @JoinColumn(name = "adq_prov_orga_fk", referencedColumnName = "orga_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private OrganiIntProve adqProvOrga;
    @JoinColumn(name = "adq_fuente_fk", referencedColumnName = "fue_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private FuenteFinanciamiento adqFuente;
    @JoinColumn(name = "adq_moneda_fk", referencedColumnName = "mon_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Moneda adqMoneda;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pagAdqFk")
    @Fetch(FetchMode.SELECT)
    private Set<Pagos> pagosSet;
    @Column(name = "adq_proc_compra")
    private String adqProcCompra;
    @Column(name = "adq_proc_compra_grp")
    private String adqProcCompraGrp;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "devAdqFk", fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Devengado> devengadoList;
    
    @Transient
    private List<Devengado> listDevAux;

    public Adquisicion() {
    }

    public Adquisicion(Integer adqPk) {
        this.adqPk = adqPk;
    }

    public Adquisicion(Integer adqPk, String adqNombre) {
        this.adqPk = adqPk;
        this.adqNombre = adqNombre;
    }

    public Integer getAdqPk() {
        return adqPk;
    }

    public void setAdqPk(Integer adqPk) {
        this.adqPk = adqPk;
    }

    public Presupuesto getAdqPreFk() {
        return adqPreFk;
    }

    public void setAdqPreFk(Presupuesto adqPreFk) {
        this.adqPreFk = adqPreFk;
    }

    public String getAdqNombre() {
        return adqNombre;
    }

    public void setAdqNombre(String adqNombre) {
        this.adqNombre = adqNombre;
    }

    public OrganiIntProve getAdqProvOrga() {
        return adqProvOrga;
    }

    public void setAdqProvOrga(OrganiIntProve adqProvOrga) {
        this.adqProvOrga = adqProvOrga;
    }

    public FuenteFinanciamiento getAdqFuente() {
        return adqFuente;
    }

    public void setAdqFuente(FuenteFinanciamiento adqFuente) {
        this.adqFuente = adqFuente;
    }

    public Moneda getAdqMoneda() {
        return adqMoneda;
    }

    public void setAdqMoneda(Moneda adqMoneda) {
        this.adqMoneda = adqMoneda;
    }

    @XmlTransient
    public Set<Pagos> getPagosSet() {
        return pagosSet;
    }

    public void setPagosSet(Set<Pagos> pagosSet) {
        this.pagosSet = pagosSet;
    }

    public String getAdqProcCompra() {
        return adqProcCompra;
    }

    public void setAdqProcCompra(String adqProcCompra) {
        this.adqProcCompra = adqProcCompra;
    }

    public String getAdqProcCompraGrp() {
        return adqProcCompraGrp;
    }

    public void setAdqProcCompraGrp(String adqProcCompraGrp) {
        this.adqProcCompraGrp = adqProcCompraGrp;
    }

    public List<Devengado> getDevengadoList() {
        return devengadoList;
    }

    public void setDevengadoList(List<Devengado> devengadoList) {
        this.devengadoList = devengadoList;
    }

    public List<Devengado> getListDevAux() {
        return listDevAux;
    }

    public void setListDevAux(List<Devengado> listDevAux) {
        this.listDevAux = listDevAux;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adqPk != null ? adqPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adquisicion)) {
            return false;
        }
        Adquisicion other = (Adquisicion) object;

        if (adqPk == null || other.adqPk == null) {
            return this == object;
        }

        if ((this.adqPk == null && other.adqPk != null) || (this.adqPk != null && !this.adqPk.equals(other.adqPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Adquisicion[ adqPk=" + adqPk + " ]";
    }

    /**
     * Calcula el importe planificado de los pagos.
     *
     * @return Importe planificado de los pagos.
     */
    public Double getImportePlanificado() {
        if (pagosSet != null && !pagosSet.isEmpty()) {
            Double result = 0D;
            for (Pagos pagos : pagosSet) {
                result += pagos.getPagImportePlanificado();
            }
            return result;
        }
        return null;
    }

    /**
     * Calcula el importe real de los pagos.
     *
     * @return Importe real de los pagos.
     */
    public Double getImporteReal() {
        if (pagosSet != null && !pagosSet.isEmpty()) {
            Double result = 0D;
            for (Pagos pagos : pagosSet) {
                result += pagos.getPagImporteReal();
            }
            return result;
        }
        return null;
    }

    /**
     * Calcula el saldo de los pagos realizados.
     *
     * @return Saldo de los pagos realizados.
     */
    public Double getImporteSaldo() {
        if (pagosSet != null && !pagosSet.isEmpty()) {
            Double result = 0D;
            for (Pagos pagos : pagosSet) {
                result += pagos.getImporteSaldo();
            }
            return result;
        }
        return null;
    }
}
