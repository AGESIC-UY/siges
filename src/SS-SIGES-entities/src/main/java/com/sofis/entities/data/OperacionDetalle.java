package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "operacion_detalle")
@XmlRootElement
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperacionDetalle implements Serializable {

    public static final int NOMBRE_LENGHT = 300;
    public static final int PROC_COMPRA_LENGHT = 20;
    public static final int PROC_COMPRA_GRP_LENGHT = 20;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "opd_pk")
    private Integer opdPk;

    @Basic(optional = false)
    @Column(name = "opd_codigo")
    private String opdCodigo;

    
    @Basic(optional = false)
    @Column(name = "opd_descripcion")
    private String opdDescripcion;

    public int hashCode() {
        int hash = 0;
        hash += (opdPk != null ? opdPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof OperacionDetalle)) {
            return false;
        }
        OperacionDetalle other = (OperacionDetalle) object;
        if (opdPk == null || other.opdPk == null) {
            return this == object;
        }

        if ((this.opdPk == null && other.opdPk != null) || (this.opdPk != null && !this.opdPk.equals(other.opdPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Adquisicion[ adqPk=" + opdPk + " ]";
    }

    public Integer getOpdPk() {
        return opdPk;
    }

    public void setOpdPk(Integer opdPk) {
        this.opdPk = opdPk;
    }

    public String getOpdCodigo() {
        return opdCodigo;
    }

    public void setOpdCodigo(String opdCodigo) {
        this.opdCodigo = opdCodigo;
    }

    public String getOpdDescripcion() {
        return opdDescripcion;
    }

    public void setOpdDescripcion(String opdDescripcion) {
        this.opdDescripcion = opdDescripcion;
    }

   
    
}
