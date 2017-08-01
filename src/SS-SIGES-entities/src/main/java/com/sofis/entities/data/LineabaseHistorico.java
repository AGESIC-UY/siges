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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "lineabase_historico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineabaseHistorico.findAll", query = "SELECT l FROM LineabaseHistorico l"),
    @NamedQuery(name = "LineabaseHistorico.findByLineabasePk", query = "SELECT l FROM LineabaseHistorico l WHERE l.lineabasePk = :lineabasePk"),
    @NamedQuery(name = "LineabaseHistorico.findByLineabaseFecha", query = "SELECT l FROM LineabaseHistorico l WHERE l.lineabaseFecha = :lineabaseFecha"),
    @NamedQuery(name = "LineabaseHistorico.findByLineabaseInicio", query = "SELECT l FROM LineabaseHistorico l WHERE l.lineabaseInicio = :lineabaseInicio"),
    @NamedQuery(name = "LineabaseHistorico.findByLineabaseDuracion", query = "SELECT l FROM LineabaseHistorico l WHERE l.lineabaseDuracion = :lineabaseDuracion"),
    @NamedQuery(name = "LineabaseHistorico.findByLineabaseFin", query = "SELECT l FROM LineabaseHistorico l WHERE l.lineabaseFin = :lineabaseFin")})
@Deprecated
public class LineabaseHistorico implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lineabase_pk")
    private Integer lineabasePk;
    @JoinColumn(name = "lineabase_entFk", referencedColumnName = "ent_pk")
    @ManyToOne(fetch = FetchType.LAZY)
    private Entregables entregable;
    @NotNull
    @Column(name = "lineabase_fecha")
    @Temporal(TemporalType.DATE)
    private Date lineabaseFecha;
    @NotNull
    @Column(name = "lineabase_inicio")
    private long lineabaseInicio;
    @Column(name = "lineabase_duracion")
    private Integer lineabaseDuracion;
    @NotNull
    @Column(name = "lineabase_fin")
    private long lineabaseFin;

    public LineabaseHistorico() {
    }

    public LineabaseHistorico(Integer lineabasePk) {
        this.lineabasePk = lineabasePk;
    }

    public LineabaseHistorico(Integer lineabasePk, int lineabaseentFk, Date lineabaseFecha, long lineabaseInicio, long lineabaseFin) {
        this.lineabasePk = lineabasePk;
        this.entregable = new Entregables(lineabaseentFk);
        this.lineabaseFecha = lineabaseFecha;
        this.lineabaseInicio = lineabaseInicio;
        this.lineabaseFin = lineabaseFin;
    }

    public Integer getLineabasePk() {
        return lineabasePk;
    }

    public void setLineabasePk(Integer lineabasePk) {
        this.lineabasePk = lineabasePk;
    }

    public Date getLineabaseFecha() {
        return lineabaseFecha;
    }

    public void setLineabaseFecha(Date lineabaseFecha) {
        this.lineabaseFecha = lineabaseFecha;
    }

    public long getLineabaseInicio() {
        return lineabaseInicio;
    }

    public void setLineabaseInicio(long lineabaseInicio) {
        this.lineabaseInicio = lineabaseInicio;
    }

    public Integer getLineabaseDuracion() {
        return lineabaseDuracion;
    }

    public void setLineabaseDuracion(Integer lineabaseDuracion) {
        this.lineabaseDuracion = lineabaseDuracion;
    }

    public long getLineabaseFin() {
        return lineabaseFin;
    }

    public void setLineabaseFin(long lineabaseFin) {
        this.lineabaseFin = lineabaseFin;
    }

    public Entregables getEntregable() {
        return entregable;
    }

    public void setEntregable(Entregables entregable) {
        this.entregable = entregable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lineabasePk != null ? lineabasePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof LineabaseHistorico)) {
            return false;
        }
        LineabaseHistorico other = (LineabaseHistorico) object;
        if ((this.lineabasePk == null && other.lineabasePk != null) || (this.lineabasePk != null && !this.lineabasePk.equals(other.lineabasePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.LineabaseHistorico[ lineabasePk=" + lineabasePk + " ]";
    }
    
}
