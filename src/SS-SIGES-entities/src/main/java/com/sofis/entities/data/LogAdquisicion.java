package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "log_adquisiciones")
public class LogAdquisicion implements Serializable {

    public static final int NOMBRE_LENGHT = 300;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "lad_pk")
    private Integer ladPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lad_detalle")
    private String ladDetalle;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lad_usuario")
    private String ladUsuario;
    
    @Column(name = "lad_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date ladFecha;
    
    @JoinColumn(name = "lad_adquisicion_fk", referencedColumnName = "adq_pk")
    @ManyToOne(optional = false)
    private Adquisicion ladAquisicionFk;
    

    public LogAdquisicion() {
    }

   
    @Override
    public int hashCode() {
            int hash = 0;
            hash += (ladPk != null ? ladPk.hashCode() : 0);
            return hash;
    }

    @Override
    public boolean equals(Object object) {

            if (!(object instanceof LogAdquisicion)) {
                    return false;
            }
            LogAdquisicion other = (LogAdquisicion) object;
            if ((this.ladPk == null && other.ladPk != null) || (this.ladPk != null && !this.ladPk.equals(other.ladPk))) {
                    return false;
            }
            return true;
    }

    @Override
    public String toString() {
            return "com.sofis.entities.data.ProcedimientoCompra[ procCompPk=" + ladPk + " ]";
    }

    public Integer getLadPk() {
        return ladPk;
    }

    public void setLadPk(Integer ladPk) {
        this.ladPk = ladPk;
    }

    public String getLadDetalle() {
        return ladDetalle;
    }

    public void setLadDetalle(String ladDetalle) {
        this.ladDetalle = ladDetalle;
    }

    public String getLadUsuario() {
        return ladUsuario;
    }

    public void setLadUsuario(String ladUsuario) {
        this.ladUsuario = ladUsuario;
    }

    public Date getLadFecha() {
        return ladFecha;
    }

    public void setLadFecha(Date ladFecha) {
        this.ladFecha = ladFecha;
    }

    public Adquisicion getLadAquisicionFk() {
        return ladAquisicionFk;
    }

    public void setLadAquisicionFk(Adquisicion ladAquisicionFk) {
        this.ladAquisicionFk = ladAquisicionFk;
    }
    
    


}
