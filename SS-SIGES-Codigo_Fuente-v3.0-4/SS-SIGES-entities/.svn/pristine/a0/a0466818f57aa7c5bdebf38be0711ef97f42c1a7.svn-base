package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.StringsUtils;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByGasPk", query = "SELECT g FROM Gastos g WHERE g.gasPk = :gasPk"),
    @NamedQuery(name = "Gastos.findByGasImporte", query = "SELECT g FROM Gastos g WHERE g.gasImporte = :gasImporte"),
    @NamedQuery(name = "Gastos.findByGasObs", query = "SELECT g FROM Gastos g WHERE g.gasObs = :gasObs")})
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gas_pk")
    private Integer gasPk;
    @JoinColumn(name = "gas_tipo_fk", referencedColumnName = "tipogas_pk")
    @ManyToOne(optional = false)
    private TipoGasto gasTipoFk;
    @JoinColumn(name = "gas_proy_fk", referencedColumnName = "proy_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proyectos gasProyFk;
    @JoinColumn(name = "gas_usu_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario gasUsuarioFk;
    @JoinColumn(name = "gas_mon_fk", referencedColumnName = "mon_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Moneda gasMonFk;
    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "gas_fecha")
    private Date gasFecha;
    @Basic(optional = false)
    @Column(name = "gas_importe")
    private Double gasImporte;
    @Column(name = "gas_obs")
    private String gasObs;
    @Column(name = "gas_aprobado")
    private Boolean gasAprobado;

    public Gastos() {
    }

    public Gastos(Integer gasPk) {
        this.gasPk = gasPk;
    }

    public Gastos(Integer gasPk, Double gasImporte) {
        this.gasPk = gasPk;
        this.gasImporte = gasImporte;
    }

    public Integer getGasPk() {
        return gasPk;
    }

    public void setGasPk(Integer gasPk) {
        this.gasPk = gasPk;
    }

    public Double getGasImporte() {
        return gasImporte;
    }

    public void getGasImporte(Double gasImporte) {
        this.gasImporte = gasImporte;
    }

    public void setGasImporte(Double gasImporte) {
        this.gasImporte = gasImporte;
    }
    
    public String getGasObs() {
        return gasObs;
    }
    
    public String getGasObs(int largo) {
        return StringsUtils.recortarTexto(gasObs, largo);
    }

    public void setGasObs(String gasObs) {
        this.gasObs = gasObs;
    }

    public Boolean getGasAprobado() {
        return gasAprobado;
    }

    public void setGasAprobado(Boolean gasAprobado) {
        this.gasAprobado = gasAprobado;
    }

    public TipoGasto getGasTipoFk() {
        return gasTipoFk;
    }

    public void setGasTipoFk(TipoGasto gasTipoFk) {
        this.gasTipoFk = gasTipoFk;
    }

    public Proyectos getGasProyFk() {
        return gasProyFk;
    }

    public void setGasProyFk(Proyectos gasProyFk) {
        this.gasProyFk = gasProyFk;
    }

    public SsUsuario getGasUsuarioFk() {
        return gasUsuarioFk;
    }

    public void setGasUsuarioFk(SsUsuario gasUsuarioFk) {
        this.gasUsuarioFk = gasUsuarioFk;
    }

    public Moneda getGasMonFk() {
        return gasMonFk;
    }

    public void setGasMonFk(Moneda gasMonFk) {
        this.gasMonFk = gasMonFk;
    }

    public Date getGasFecha() {
        return gasFecha;
    }

    public void setGasFecha(Date gasFecha) {
        this.gasFecha = gasFecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gasPk != null ? gasPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.gasPk == null && other.gasPk != null) || (this.gasPk != null && !this.gasPk.equals(other.gasPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Gastos[ gasPk=" + gasPk + " ]";
    }
}
