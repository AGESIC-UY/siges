package com.sofis.entities.data;

import com.sofis.entities.enums.TipoRegistroCompra;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "adquisicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adquisicion.findAll", query = "SELECT a FROM Adquisicion a")
    ,
    @NamedQuery(name = "Adquisicion.findByAdqPk", query = "SELECT a FROM Adquisicion a WHERE a.adqPk = :adqPk")
    ,
    @NamedQuery(name = "Adquisicion.findByAdqNombre", query = "SELECT a FROM Adquisicion a WHERE a.adqNombre = :adqNombre")
    ,
    @NamedQuery(name = "Adquisicion.findByAdqProvOrga", query = "SELECT a FROM Adquisicion a WHERE a.adqProvOrga = :adqProvOrga")
    ,
    @NamedQuery(name = "Adquisicion.findByAdqFuente", query = "SELECT a FROM Adquisicion a WHERE a.adqFuente = :adqFuente")
    ,
    @NamedQuery(name = "Adquisicion.findByAdqMoneda", query = "SELECT a FROM Adquisicion a WHERE a.adqMoneda = :adqMoneda")})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adquisicion implements Serializable {

    public static final int NOMBRE_LENGHT = 300;
    public static final int PROC_COMPRA_LENGHT = 20;
    public static final int PROC_COMPRA_GRP_LENGHT = 20;

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
    @JoinColumn(name = "adq_componente_producto_fk", referencedColumnName = "com_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private ComponenteProducto adqComponenteProducto;
    @JoinColumn(name = "adq_moneda_fk", referencedColumnName = "mon_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Moneda adqMoneda;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pagAdqFk")
    @Fetch(FetchMode.SELECT)
    private Set<Pagos> pagosSet;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adq_id_grp_erp_fk", referencedColumnName = "id_grp_erp_pk")
    private IdentificadorGrpErp adqIdGrpErpFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "devAdqFk", fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Devengado> devengadoList;

    // Agrego estos atributos por la modificación en la tabla de Adquisición
    @JoinColumn(name = "adq_procedimiento_compra_fk", referencedColumnName = "proc_comp_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private ProcedimientoCompra adqProcedimientoCompra;

    @Basic(optional = false)
    @Column(name = "adq_compartida")
    private Boolean adqCompartida;

    @JoinColumn(name = "adq_compartida_usuario_fk", referencedColumnName = "usu_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private SsUsuario ssUsuarioCompartida;

    @Column(name = "adq_tipo_registro")
    @Enumerated(EnumType.STRING)
    private TipoRegistroCompra adqTipoRegistro;

    @Basic(optional = false)
    @Column(name = "adq_arrastre")
    private Boolean adqArrastre;

    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "adq_fecha_estimada_inicio_compra")
    private Date adqFechaEstimadaInicioCompra;

    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "adq_fecha_esperada_inicio_ejecucion")
    private Date adqFechaEsperadaInicioEjecucion;

    @JoinColumn(name = "adq_tipo_adquisicion_fk", referencedColumnName = "tip_adq_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private TipoAdquisicion adqTipoAdquisicion;

    @Column(name = "adq_id_adquisicion")
    private Integer adqIdAdquisicion;

    @JoinColumn(name = "adq_centro_costo_fk", referencedColumnName = "cen_cos_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private CentroCosto adqCentroCosto;

    @JoinColumn(name = "adq_causal_compra_fk", referencedColumnName = "cau_com_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private CausalCompra adqCausalCompra;

    // Agrego estos atributos por la modificación en la tabla de Adquisición
    @Transient
    private List<Devengado> listDevAux;

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

    public ComponenteProducto getAdqComponenteProducto() {
        return adqComponenteProducto;
    }

    public void setAdqComponenteProducto(ComponenteProducto adqComponenteProducto) {
        this.adqComponenteProducto = adqComponenteProducto;
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

    public IdentificadorGrpErp getAdqIdGrpErpFk() {
        return adqIdGrpErpFk;
    }

    public void setAdqIdGrpErpFk(IdentificadorGrpErp adqIdGrpErpFk) {
        this.adqIdGrpErpFk = adqIdGrpErpFk;
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
                if (pagos.getImporteSaldo() != null) {
                    result += pagos.getImporteSaldo();
                } else {
                    result += 0;
                }
            }
            return result;
        }
        return 0d;
    }
    
    /**
     * Calcula el saldo de los pagos realizados en un año.
     *
     * @param anio El año.
     * @return Saldo de los pagos realizados.
     */
    public Double getImporteSaldoAnio(int anio) {
        Double planificado = 0.0;
        Double real = 0.0;
        if (this.getPagosSet() != null && !this.getPagosSet().isEmpty()) {
            for (Pagos pago : this.getPagosSet()) {
                if (new DateTime(pago.getPagFechaPlanificada()).getYear() == anio) {
                    planificado += pago.getPagImportePlanificado() != null ? pago.getPagImportePlanificado() : 0.0;
                }
                if (new DateTime(pago.getPagFechaReal()).getYear() == anio) {
                    real += pago.getPagImporteReal() != null ? pago.getPagImporteReal() : 0.0;
                }
            }
        }
        return planificado - real;
    }

    // Se agrega una operación para obtener el saldo por mes
    public Double getImporteSaldoMes(int mes, boolean acumulado) {
        if (this.getPagosSet() != null && !this.getPagosSet().isEmpty()) {
            Double result = 0D;
            for (Pagos iterPago : this.getPagosSet()) {
                if (iterPago.getImporteSaldo() != null) {
                    Calendar calAuxPlan = Calendar.getInstance();
                    calAuxPlan.setTime(iterPago.getPagFechaPlanificada());

                    if (acumulado) {
                        if (calAuxPlan.get(Calendar.MONTH) <= mes - 1) {
                            if (iterPago.getPagFechaReal() != null) {
                                Calendar calAuxReal = Calendar.getInstance();
                                calAuxReal.setTime(iterPago.getPagFechaReal());
                                if (calAuxReal.get(Calendar.MONTH) <= mes - 1) {
                                    result += iterPago.getImporteSaldo();
                                } else {
                                    result += (iterPago.getPagImportePlanificado() != null ? iterPago.getPagImportePlanificado() : 0);
                                }
                            } else {
                                result += (iterPago.getPagImportePlanificado() != null ? iterPago.getPagImportePlanificado() : 0);
                            }
                        }
                    } else {
                        if (calAuxPlan.get(Calendar.MONTH) == mes - 1) {
                            if (iterPago.getPagFechaReal() != null) {
                                Calendar calAuxReal = Calendar.getInstance();
                                calAuxReal.setTime(iterPago.getPagFechaReal());
                                if (calAuxReal.get(Calendar.MONTH) == mes - 1) {
                                    result += iterPago.getImporteSaldo();
                                } else {
                                    result += (iterPago.getPagImportePlanificado() != null ? iterPago.getPagImportePlanificado() : 0);
                                }
                            } else {
                                result += (iterPago.getPagImportePlanificado() != null ? iterPago.getPagImportePlanificado() : 0);
                            }
                        }
                    }
                }
            }
            return result;
        }
        return 0d;
    }

    //<editor-fold defaultstate="collapsed" desc="getters and sSetters">
    //Agrego los getters y setters faltantes de los atributos puestos después de modificar la tabla
    public ProcedimientoCompra getAdqProcedimientoCompra() {
        return adqProcedimientoCompra;
    }

    public void setAdqProcedimientoCompra(ProcedimientoCompra adqProcedimientoCompra) {
        this.adqProcedimientoCompra = adqProcedimientoCompra;
    }

    public Boolean getAdqCompartida() {
        return adqCompartida;
    }

    public void setAdqCompartida(Boolean adqCompartida) {
        this.adqCompartida = adqCompartida;
    }

    public SsUsuario getSsUsuarioCompartida() {
        return ssUsuarioCompartida;
    }

    public void setSsUsuarioCompartida(SsUsuario ssUsuarioCompartida) {
        this.ssUsuarioCompartida = ssUsuarioCompartida;
    }

    public TipoRegistroCompra getAdqTipoRegistro() {
        return adqTipoRegistro;
    }

    public void setAdqTipoRegistro(TipoRegistroCompra adqTipoRegistro) {
        this.adqTipoRegistro = adqTipoRegistro;
    }

    public Boolean getAdqArrastre() {
        return adqArrastre;
    }

    public void setAdqArrastre(Boolean adqArrastre) {
        this.adqArrastre = adqArrastre;
    }

    public Date getAdqFechaEstimadaInicioCompra() {
        return adqFechaEstimadaInicioCompra;
    }

    public void setAdqFechaEstimadaInicioCompra(Date adqFechaEstimadaInicioCompra) {
        this.adqFechaEstimadaInicioCompra = adqFechaEstimadaInicioCompra;
    }

    public Date getAdqFechaEsperadaInicioEjecucion() {
        return adqFechaEsperadaInicioEjecucion;
    }

    public void setAdqFechaEsperadaInicioEjecucion(Date adqFechaEsperadaInicioEjecucion) {
        this.adqFechaEsperadaInicioEjecucion = adqFechaEsperadaInicioEjecucion;
    }

    public TipoAdquisicion getAdqTipoAdquisicion() {
        return adqTipoAdquisicion;
    }

    public void setAdqTipoAdquisicion(TipoAdquisicion adqTipoAdquisicion) {
        this.adqTipoAdquisicion = adqTipoAdquisicion;
    }

    public Integer getAdqIdAdquisicion() {
        return adqIdAdquisicion;
    }

    public void setAdqIdAdquisicion(Integer adqIdAdquisicion) {
        this.adqIdAdquisicion = adqIdAdquisicion;
    }

    public CentroCosto getAdqCentroCosto() {
        return adqCentroCosto;
    }

    public void setAdqCentroCosto(CentroCosto adqCentroCosto) {
        this.adqCentroCosto = adqCentroCosto;
    }

    public CausalCompra getAdqCausalCompra() {
        return adqCausalCompra;
    }

    public void setAdqCausalCompra(CausalCompra adqCausalCompra) {
        this.adqCausalCompra = adqCausalCompra;
    }
    //</editor-fold>

}
