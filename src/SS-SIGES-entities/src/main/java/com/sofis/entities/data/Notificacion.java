package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "notificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByNotPk", query = "SELECT n FROM Notificacion n WHERE n.notPk = :notPk"),
    @NamedQuery(name = "Notificacion.findByNotDesc", query = "SELECT n FROM Notificacion n WHERE n.notDesc = :notDesc"),
    @NamedQuery(name = "Notificacion.findByNotValor", query = "SELECT n FROM Notificacion n WHERE n.notValor = :notValor"),
    @NamedQuery(name = "Notificacion.findByNotGerenteAdjunto", query = "SELECT n FROM Notificacion n WHERE n.notGerenteAdjunto = :notGerenteAdjunto"),
    @NamedQuery(name = "Notificacion.findByNotPmof", query = "SELECT n FROM Notificacion n WHERE n.notPmof = :notPmof"),
    @NamedQuery(name = "Notificacion.findByNotPmot", query = "SELECT n FROM Notificacion n WHERE n.notPmot = :notPmot"),
    @NamedQuery(name = "Notificacion.findByNotSponsor", query = "SELECT n FROM Notificacion n WHERE n.notSponsor = :notSponsor"),
    @NamedQuery(name = "Notificacion.findByNotMsg", query = "SELECT n FROM Notificacion n WHERE n.notMsg = :notMsg")})
public class Notificacion implements Serializable {
    
    public static final int CODIGO_LENGHT = 45;
    public static final int DESCRIPCION_LENGHT = 245;
    public static final int MENSAJE_LENGHT = 5000;

    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "not_pk")
    private Integer notPk;
    
	@JoinColumn(name = "not_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos notOrgFk;
    
	@Column(name = "not_cod")
    private String notCod;
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "not_asunto")
    private String notAsunto;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "not_desc")
    private String notDesc;
    
	@Column(name = "not_valor")
    private Integer notValor;
    
	@Column(name = "not_gerente_adjunto")
    private Boolean notGerenteAdjunto;
    
	@Column(name = "not_pmof")
    private Boolean notPmof;
    
	@Column(name = "not_pmot")
    private Boolean notPmot;
    
	@Column(name = "not_sponsor")
    private Boolean notSponsor;
    
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "not_msg")
    private String notMsg;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "notinstNotFk")
    private Set<NotificacionInstancia> notificacionInstanciaSet;

    public Notificacion() {
    }

    public Notificacion(Integer notPk) {
        this.notPk = notPk;
    }

    public Notificacion(Integer notPk, String notDesc, String notMsg) {
        this.notPk = notPk;
        this.notDesc = notDesc;
        this.notMsg = notMsg;
    }

    public Notificacion(Organismos notOrgFk, String notCod, String notAsunto, String notDesc, 
			Integer notValor, Boolean notGerenteAdjunto, Boolean notPmof, Boolean notPmot, Boolean notSponsor, String notMsg) {
        
		this.notOrgFk = notOrgFk;
        this.notCod = notCod;
        this.notAsunto = notAsunto;
		this.notDesc = notDesc;
        this.notValor = notValor;
        this.notGerenteAdjunto = notGerenteAdjunto;
        this.notPmof = notPmof;
        this.notPmot = notPmot;
        this.notSponsor = notSponsor;
        this.notMsg = notMsg;
    }
    
    public Integer getNotPk() {
        return notPk;
    }

    public void setNotPk(Integer notPk) {
        this.notPk = notPk;
    }

    public Organismos getNotOrgFk() {
        return notOrgFk;
    }

    public void setNotOrgFk(Organismos notOrgFk) {
        this.notOrgFk = notOrgFk;
    }

    public String getNotCod() {
        return notCod;
    }

    public void setNotCod(String notCod) {
        this.notCod = notCod;
    }

	public String getNotAsunto() {
		return notAsunto;
	}

	public void setNotAsunto(String notAsunto) {
		this.notAsunto = notAsunto;
	}

    public String getNotDesc() {
        return notDesc;
    }

    public void setNotDesc(String notDesc) {
        this.notDesc = notDesc;
    }

    public Integer getNotValor() {
        return notValor;
    }

    public void setNotValor(Integer notValor) {
        this.notValor = notValor;
    }

    public Boolean getNotGerenteAdjunto() {
        return notGerenteAdjunto;
    }

    public void setNotGerenteAdjunto(Boolean notGerenteAdjunto) {
        this.notGerenteAdjunto = notGerenteAdjunto;
    }

    public Boolean getNotPmof() {
        return notPmof;
    }

    public void setNotPmof(Boolean notPmof) {
        this.notPmof = notPmof;
    }

    public Boolean getNotPmot() {
        return notPmot;
    }

    public void setNotPmot(Boolean notPmot) {
        this.notPmot = notPmot;
    }

    public Boolean getNotSponsor() {
        return notSponsor;
    }

    public void setNotSponsor(Boolean notSponsor) {
        this.notSponsor = notSponsor;
    }

    public String getNotMsg() {
        return notMsg;
    }

    public void setNotMsg(String notMsg) {
        this.notMsg = notMsg;
    }

    @XmlTransient
    public Set<NotificacionInstancia> getNotificacionInstanciaSet() {
        return notificacionInstanciaSet;
    }

    public void setNotificacionInstanciaSet(Set<NotificacionInstancia> notificacionInstanciaSet) {
        this.notificacionInstanciaSet = notificacionInstanciaSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notPk != null ? notPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.notPk == null && other.notPk != null) || (this.notPk != null && !this.notPk.equals(other.notPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Notificacion[ notPk=" + notPk + " ]";
    }
}
