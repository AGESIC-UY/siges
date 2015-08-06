package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "estados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e")})
public class Estados implements Serializable {

    public enum ESTADOS {

        NO_EXIGIDO(0),
        PENDIENTE(1), //No se usa
        PENDIENTE_PMOT(11), //Cuando el PM solicita aprobacion al PMOT
        PENDIENTE_PMOF(12), //Cuando el PMOT solicita aprobacion al PMOF
        INICIO(2),
        PLANIFICACION(3),
        EJECUCION(4),
        FINALIZADO(5),
        SOLICITUD_FINALIZADO_PMOF(41), //Cuando el PM solicita al PMOF Finalizar
        SOLICITUD_FINALIZADO_PMOT(42), //Cuando el PMOF solicita al PMOT Finalizar
        SOLICITUD_CANCELAR_PMOT(61); //Cuando el PMOF solicita Cancelar(borrado logico)
        public int estado_id;

        ESTADOS(int estado) {
            this.estado_id = estado;
        }
    }
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "est_pk")
    private Integer estPk;
    @Column(name = "est_codigo")
    private String estCodigo;
    @Column(name = "est_nombre")
    private String estNombre;
    /**
     * Label para el language.
     */
    @Column(name = "est_label")
    private String estLabel;
    @Column(name = "est_orden_proceso")
    private Integer estOrdenProceso;

    public Estados() {
    }

    public Estados(Integer estPk) {
        this.estPk = estPk;
    }

    public Integer getEstPk() {
        return estPk;
    }

    public void setEstPk(Integer estPk) {
        this.estPk = estPk;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public Integer getEstOrdenProceso() {
        return estOrdenProceso;
    }

    public void setEstOrdenProceso(Integer estOrdenProceso) {
        this.estOrdenProceso = estOrdenProceso;
    }

    public String getEstCodigo() {
        return estCodigo;
    }

    public void setEstCodigo(String estCodigo) {
        this.estCodigo = estCodigo;
    }

    public String getEstLabel() {
        return estLabel;
    }

    public void setEstLabel(String estLabel) {
        this.estLabel = estLabel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estPk != null ? estPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.estPk == null && other.estPk != null) || (this.estPk != null && !this.estPk.equals(other.estPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Estados[ estPk=" + estPk + " ]";
    }

    /**
     * Retorna el nombre de la imagen de acuerdo al estado Pk.
     *
     * @return String
     */
    public String getImgEstado() {

        switch (this.estPk) {
            case 1:
            case 11:
            case 12:
                return "fase-1.png";
            case 2:
                return "fase-2.png";
            case 3:
                return "fase-3.png";
            case 4:
                return "fase-4.png";
            case 5:
                return "fase-5.png";
            default:
                return "fase-1.png";
        }
    }

    public boolean isEstado(Integer estPk) {
        return estPk != null && this.estPk.equals(estPk);
    }

    public boolean isPendientes() {
        return this.estPk.equals(Estados.ESTADOS.PENDIENTE.estado_id)
                || this.estPk.equals(Estados.ESTADOS.PENDIENTE_PMOT.estado_id)
                || this.estPk.equals(Estados.ESTADOS.PENDIENTE_PMOF.estado_id);
    }

}
