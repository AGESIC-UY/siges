package com.sofis.entities.data;

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
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "riesgos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Riesgos.findIdProyectoByIdRiesgo", query = "SELECT r.riskProyFk.proyPk FROM Riesgos r WHERE r.riskPk = :idRiesgo")
})
public class Riesgos implements Serializable {

    public static final int NOMBRE_LENGHT = 3000;
    public static final int ESTRATEGIA_LENGHT = 3000;
    public static final int EFECTO_LENGHT = 3000;
    public static final int DISPARADOR_LENGHT = 3000;
    public static final int CONTINGENCIA_LENGHT = 3000;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "risk_pk")
    private Integer riskPk;

    @JoinColumn(name = "risk_proy_fk", referencedColumnName = "proy_pk", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Proyectos riskProyFk;

    @Column(name = "risk_nombre")
    private String riskNombre;

    @Column(name = "risk_fecha_actu")
    @Temporal(TemporalType.DATE)
    private Date riskFechaActualizacion;

    @Column(name = "risk_probabilidad")
    private Integer riskProbabilidad;

    @Column(name = "risk_impacto")
    private Integer riskImpacto;

    @JoinColumn(name = "risk_ent_fk", referencedColumnName = "ent_pk", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Entregables riskEntregable;

    @Column(name = "risk_fecha_limite")
    @Temporal(TemporalType.DATE)
    private Date riskFechaLimite;

    @Column(name = "risk_efecto")
    private String riskEfecto;

    @Column(name = "risk_estategia")
    private String riskEstrategia;

    @Column(name = "risk_disparador")
    private String riskDisparador;

    @Column(name = "risk_contingencia")
    private String riskContingencia;

    @Column(name = "risk_superado")
    private Boolean riskSuperado;

    @Column(name = "risk_fecha_superado")
    @Temporal(TemporalType.DATE)
    private Date riskFechaSuperado;

    @Column(name = "risk_usuario_superado_fk")
    private Integer riskUsuarioSuperadoFk;

    @Column(name = "risk_exposicion")
    private Double exposicion;

    @Column(name = "risk_observaciones")
    private String riskObservaciones;

    //Campos no persistidos
    @Transient
    private String exposicionColor;

    @Transient
    private String fechaLimiteColor;

    @JoinColumn(name = "risk_tipo_riesgo_fk", referencedColumnName = "trs_pk")
    @ManyToOne
    private TipoRiesgo riskTipoRiesgoFk;

    public Riesgos() {
    }

    public Riesgos(Integer riskPk) {
        this.riskPk = riskPk;
    }

    public Integer getRiskPk() {
        return riskPk;
    }

    public Proyectos getRiskProyFk() {
        return riskProyFk;
    }

    public void setRiskProyFk(Proyectos riskProyFk) {
        this.riskProyFk = riskProyFk;
    }

    public void setRiskPk(Integer riskPk) {
        this.riskPk = riskPk;
    }

    public String getRiskNombre() {
        return riskNombre;
    }

    public void setRiskNombre(String riskNombre) {
        this.riskNombre = riskNombre;
    }

    public Date getRiskFechaActualizacion() {
        return riskFechaActualizacion;
    }

    public void setRiskFechaActualizacion(Date riskFechaActualizacion) {
        this.riskFechaActualizacion = riskFechaActualizacion;
    }

    public Integer getRiskProbabilidad() {
        return riskProbabilidad;
    }

    public String getRiskProbabilidadStr() {
        return riskProbabilidad != null ? riskProbabilidad + "%" : "";
    }

    public void setRiskProbabilidad(Integer riskProbabilidad) {
        this.riskProbabilidad = riskProbabilidad;
    }

    public Integer getRiskImpacto() {
        return riskImpacto;
    }

    public void setRiskImpacto(Integer riskImpacto) {
        this.riskImpacto = riskImpacto;
    }

    public Entregables getRiskEntregable() {
        return riskEntregable;
    }

    public void setRiskEntregable(Entregables riskEntregable) {
        this.riskEntregable = riskEntregable;
    }

    public Date getRiskFechaLimite() {
        return riskFechaLimite;
    }

    public Integer getRiskUsuarioSuperadoFk() {
        return riskUsuarioSuperadoFk;
    }

    public void setRiskUsuarioSuperadoFk(Integer riskUsuarioSuperadoFk) {
        this.riskUsuarioSuperadoFk = riskUsuarioSuperadoFk;
    }

    public void setRiskFechaLimite(Date riskFechaLimite) {
        this.riskFechaLimite = riskFechaLimite;
    }

    public String getRiskEfecto() {
        return riskEfecto;
    }

    public void setRiskEfecto(String riskEfecto) {
        this.riskEfecto = riskEfecto;
    }

    public String getRiskEstrategia() {
        return riskEstrategia;
    }

    public void setRiskEstrategia(String riskEstrategia) {
        this.riskEstrategia = riskEstrategia;
    }

    public String getRiskDisparador() {
        return riskDisparador;
    }

    public void setRiskDisparador(String riskDisparador) {
        this.riskDisparador = riskDisparador;
    }

    public String getRiskContingencia() {
        return riskContingencia;
    }

    public void setRiskContingencia(String riskContingencia) {
        this.riskContingencia = riskContingencia;
    }

    public Double getExposicion() {
        return exposicion;
    }

    public void setExposicion(Double exposicion) {
        this.exposicion = exposicion;
    }

    public Boolean getRiskSuperado() {
        return riskSuperado;
    }

    public void setRiskSuperado(Boolean riskSuperado) {
        this.riskSuperado = riskSuperado;
    }

    public Date getRiskFechaSuperado() {
        return riskFechaSuperado;
    }

    public void setRiskFechaSuperado(Date riskFechaSuperado) {
        this.riskFechaSuperado = riskFechaSuperado;
    }

    public String getExposicionColor() {
        return exposicionColor;
    }

    public void setExposicionColor(String exposicionColor) {
        this.exposicionColor = exposicionColor;
    }

    public String getRiskObservaciones() {
        return riskObservaciones;
    }

    public void setRiskObservaciones(String observaciones) {
        this.riskObservaciones = observaciones;
    }

    public TipoRiesgo getRiskTipoRiesgoFk() {
        return riskTipoRiesgoFk;
    }

    public void setRiskTipoRiesgoFk(TipoRiesgo riskTipoRiesgoFk) {
        this.riskTipoRiesgoFk = riskTipoRiesgoFk;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (riskPk != null ? riskPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Riesgos)) {
            return false;
        }
        Riesgos other = (Riesgos) object;
        if ((this.riskPk == null && other.riskPk != null) || (this.riskPk != null && !this.riskPk.equals(other.riskPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Riesgos[ riskPk=" + riskPk + " ]";
    }

    public String getFechaLimiteColor() {
        return fechaLimiteColor;
    }

    public void setFechaLimiteColor(String fechaLimiteColor) {
        this.fechaLimiteColor = fechaLimiteColor;
    }

    public boolean isSuperado() {
        return this.riskSuperado != null && this.riskSuperado;
    }

    /**
     * Carga la Exposición calculando la Probabilidad x Impacto.
     */
    public void loadExposicion() {
        if (this.getRiskImpacto() != null && this.getRiskProbabilidad() != null) {
            double d = this.getRiskProbabilidad() / 100d;
            double exp = this.getRiskImpacto() * d;
            this.setExposicion(exp);
        }
    }
}
