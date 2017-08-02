package com.sofis.entities.data;

import com.sofis.entities.enums.TipoCalidadEnum;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "calidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calidad.findAll", query = "SELECT c FROM Calidad c"),
    @NamedQuery(name = "Calidad.findByCalPk", query = "SELECT c FROM Calidad c WHERE c.calPk = :calPk"),
    @NamedQuery(name = "Calidad.findByCalPeso", query = "SELECT c FROM Calidad c WHERE c.calPeso = :calPeso"),
    @NamedQuery(name = "Calidad.findByCalActualizacion", query = "SELECT c FROM Calidad c WHERE c.calActualizacion = :calActualizacion")})
public class Calidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cal_pk", nullable = false)
    private Integer calPk;
    @Basic(optional = false)
    @Column(name = "cal_peso", nullable = false)
    private int calPeso;
    
    @Basic(optional = false)
    @Column(name = "cal_actualizacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date calActualizacion;

    @Basic(optional = false)
    @Column(name = "cal_tipo", nullable = false)
    private int calTipo;

    @JoinColumn(name = "cal_vca_fk", referencedColumnName = "vca_pk")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private ValorCalidadCodigos calVcaFk;

    @JoinColumn(name = "cal_ent_fk", referencedColumnName = "ent_pk")
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Entregables calEntFk;

    @JoinColumn(name = "cal_prod_fk", referencedColumnName = "prod_pk")
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Productos calProdFk;

    @JoinColumn(name = "cal_tca_fk", referencedColumnName = "tca_pk")
    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private TemasCalidad calTcaFk;

    @JoinColumn(name = "cal_proy_fk", referencedColumnName = "proy_pk", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Proyectos calProyFk;

    public Calidad() {
    }

    public Calidad(Integer calPk) {
        this.calPk = calPk;
    }

    public Integer getCalPk() {
        return calPk;
    }

    public void setCalPk(Integer calPk) {
        this.calPk = calPk;
    }

    public int getCalPeso() {
        return calPeso;
    }

    public void setCalPeso(int calPeso) {
        this.calPeso = calPeso;
    }

    public Date getCalActualizacion() {
        return calActualizacion;
    }

    public void setCalActualizacion(Date calActualizacion) {
        this.calActualizacion = calActualizacion;
    }

    public int getCalTipo() {
        return calTipo;
    }

    public void setCalTipo(int calTipo) {
        this.calTipo = calTipo;
    }

    public Entregables getCalEntFk() {
        return calEntFk;
    }

    public void setCalEntFk(Entregables calEntFk) {
        this.calEntFk = calEntFk;
    }

    public ValorCalidadCodigos getCalVcaFk() {
        return calVcaFk;
    }

    public void setCalVcaFk(ValorCalidadCodigos calVcaFk) {
        this.calVcaFk = calVcaFk;
    }

    public Productos getCalProdFk() {
        return calProdFk;
    }

    public void setCalProdFk(Productos calProdFk) {
        this.calProdFk = calProdFk;
    }

    public TemasCalidad getCalTcaFk() {
        return calTcaFk;
    }

    public void setCalTcaFk(TemasCalidad calTcaFk) {
        this.calTcaFk = calTcaFk;
    }

    public Proyectos getCalProyFk() {
        return calProyFk;
    }

    public void setCalProyFk(Proyectos calProyFk) {
        this.calProyFk = calProyFk;
    }

    public String getItemStr() {
        if (this.calTipo == TipoCalidadEnum.GENERAL.ordinal()) {
            return this.calTcaFk.getTcaNombre();
        } else if (this.calTipo == TipoCalidadEnum.ENTREGABLE.ordinal()) {
            return this.calEntFk.getEntNombre();
        } else if (this.calTipo == TipoCalidadEnum.PRODUCTO.ordinal()) {
            return this.calProdFk.getProdDesc();
        }
        return "";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calPk != null ? calPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Calidad)) {
            return false;
        }
        Calidad other = (Calidad) object;
        if ((this.calPk == null && other.calPk != null) || (this.calPk != null && !this.calPk.equals(other.calPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Calidad[ calPk=" + calPk + " ]";
    }

}
