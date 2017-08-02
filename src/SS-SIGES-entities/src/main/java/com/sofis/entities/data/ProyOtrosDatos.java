package com.sofis.entities.data;

import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Datos del proyecto principalmente para usarse en el visualizador.
 *
 * @author Usuario
 */
@Entity
@Table(name = "proy_otros_datos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProyOtrosDatos.findAll", query = "SELECT p FROM ProyOtrosDatos p"),
    @NamedQuery(name = "ProyOtrosDatos.findByProyOtrPk", query = "SELECT p FROM ProyOtrosDatos p WHERE p.proyOtrPk = :proyOtrPk"),
    @NamedQuery(name = "ProyOtrosDatos.findByProyOtrOrigen", query = "SELECT p FROM ProyOtrosDatos p WHERE p.proyOtrOrigen = :proyOtrOrigen"),
    @NamedQuery(name = "ProyOtrosDatos.findByProyOtrPlazo", query = "SELECT p FROM ProyOtrosDatos p WHERE p.proyOtrPlazo = :proyOtrPlazo"),
    @NamedQuery(name = "ProyOtrosDatos.findByProyOtrObservaciones", query = "SELECT p FROM ProyOtrosDatos p WHERE p.proyOtrObservaciones = :proyOtrObservaciones")})
public class ProyOtrosDatos implements Serializable {
    
    public static final int ORIGEN_LENGHT = 1000;
    public static final int OBSERVACIONES_LENGHT = 4000;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proy_otr_pk")
    private Integer proyOtrPk;
    /**
     * Origen Principal de los Recursos.
     */
    @Column(name = "proy_otr_origen")
    private String proyOtrOrigen;
    @Column(name = "proy_otr_plazo")
    private Integer proyOtrPlazo;
    @Column(name = "proy_otr_observaciones")
    private String proyOtrObservaciones;

    /**
     * Proyecto asociado.
     */
    @OneToOne(mappedBy = "proyOtrosDatos", fetch = FetchType.LAZY)
    private Proyectos proyecto;
    
    /**
     * Estado (etapa) para el Visualizador.
     */
    @JoinColumn(name = "proy_otr_eta_fk", referencedColumnName = "eta_pk")
    @ManyToOne(optional = true)
    private Etapa proyOtrEtaFk;

    /**
     * Contratista Principal.
     */
    @JoinColumn(name = "proy_otr_cont_fk", referencedColumnName = "orga_pk")
    @ManyToOne(optional = true)
    private OrganiIntProve proyOtrContFk;

    /**
     * Institución Ejecutora.
     */
    @JoinColumn(name = "proy_otr_ins_eje_fk", referencedColumnName = "orga_pk")
    @ManyToOne(optional = true)
    private OrganiIntProve proyOtrInsEjeFk;

    /**
     * Estado de Publicación.
     */
    @JoinColumn(name = "proy_otr_est_pub_fk", referencedColumnName = "est_pub_pk")
    @ManyToOne(optional = true)
    private EstadosPublicacion proyOtrEstPubFk;

    /**
     * Inicio construcción del Producto. Asociado a un Entregable.
     */
    @JoinColumn(name = "proy_otr_ent_fk", referencedColumnName = "ent_pk")
    @ManyToOne(optional = true)
    private Entregables proyOtrEntFk;

    /**
     * Categoría Principal.
     */
    @JoinColumn(name = "proy_otr_cat_fk", referencedColumnName = "cat_proy_pk")
    @ManyToOne(optional = true)
    private CategoriaProyectos proyOtrCatFk;

    @JoinTable(name = "proy_otros_cat_secundarias", joinColumns = {
        @JoinColumn(name = "proy_cat_proy_otros_fk", referencedColumnName = "proy_otr_pk")}, inverseJoinColumns = {
        @JoinColumn(name = "proy_cat_cat_proy_fk", referencedColumnName = "cat_proy_pk")})
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<CategoriaProyectos> proyOtrosCatSecundarias;

    public ProyOtrosDatos() {
    }

    public ProyOtrosDatos(Integer proyOtrPk) {
        this.proyOtrPk = proyOtrPk;
    }

    public Integer getProyOtrPk() {
        return proyOtrPk;
    }

    public void setProyOtrPk(Integer proyOtrPk) {
        this.proyOtrPk = proyOtrPk;
    }

    public String getProyOtrOrigen() {
        return proyOtrOrigen;
    }

    public void setProyOtrOrigen(String proyOtrOrigen) {
        this.proyOtrOrigen = proyOtrOrigen;
    }

    public Integer getProyOtrPlazo() {
        return proyOtrPlazo;
    }

    public void setProyOtrPlazo(Integer proyOtrPlazo) {
        this.proyOtrPlazo = proyOtrPlazo;
    }

    public String getProyOtrObservaciones() {
        return proyOtrObservaciones;
    }

    public void setProyOtrObservaciones(String proyOtrObservaciones) {
        this.proyOtrObservaciones = proyOtrObservaciones;
    }

    public Proyectos getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyectos proyecto) {
        this.proyecto = proyecto;
    }

    public Etapa getProyOtrEtaFk() {
        return proyOtrEtaFk;
    }

    public void setProyOtrEtaFk(Etapa proyOtrEtaFk) {
        this.proyOtrEtaFk = proyOtrEtaFk;
    }

    public OrganiIntProve getProyOtrContFk() {
        return proyOtrContFk;
    }

    public void setProyOtrContFk(OrganiIntProve proyOtrContFk) {
        this.proyOtrContFk = proyOtrContFk;
    }

    public OrganiIntProve getProyOtrInsEjeFk() {
        return proyOtrInsEjeFk;
    }

    public void setProyOtrInsEjeFk(OrganiIntProve proyOtrInsEjeFk) {
        this.proyOtrInsEjeFk = proyOtrInsEjeFk;
    }

    public EstadosPublicacion getProyOtrEstPubFk() {
        return proyOtrEstPubFk;
    }

    public void setProyOtrEstPubFk(EstadosPublicacion proyOtrEstPubFk) {
        this.proyOtrEstPubFk = proyOtrEstPubFk;
    }

    public Entregables getProyOtrEntFk() {
        return proyOtrEntFk;
    }

    public void setProyOtrEntFk(Entregables proyOtrEntFk) {
        this.proyOtrEntFk = proyOtrEntFk;
    }

    public CategoriaProyectos getProyOtrCatFk() {
        return proyOtrCatFk;
    }

    public void setProyOtrCatFk(CategoriaProyectos proyOtrCatFk) {
        this.proyOtrCatFk = proyOtrCatFk;
    }

    public List<CategoriaProyectos> getProyOtrosCatSecundarias() {
        return proyOtrosCatSecundarias;
    }

    public void setProyOtrosCatSecundarias(List<CategoriaProyectos> proyOtrosCatSecundarias) {
        this.proyOtrosCatSecundarias = proyOtrosCatSecundarias;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyOtrPk != null ? proyOtrPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ProyOtrosDatos)) {
            return false;
        }
        ProyOtrosDatos other = (ProyOtrosDatos) object;
        if ((this.proyOtrPk == null && other.proyOtrPk != null) || (this.proyOtrPk != null && !this.proyOtrPk.equals(other.proyOtrPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProyOtrosDatos[ proyOtrPk=" + proyOtrPk + " ]";
    }

    public String getCategoriasSecundariasStr() {
        if (CollectionsUtils.isNotEmpty(proyOtrosCatSecundarias)) {
            boolean primero = true;
            StringBuilder sb = new StringBuilder();
            for (CategoriaProyectos cp : proyOtrosCatSecundarias) {
                sb.append((primero ? "" : ", "))
                        .append(cp.getCatProyNombre());
                primero = false;
            }
            return sb.toString();
        }
        return null;
    }
}
