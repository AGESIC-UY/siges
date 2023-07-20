package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notificacion_defecto")
public class NotificacionDefecto implements Serializable {
    
    public static final int CODIGO_LENGHT = 45;
    public static final int DESCRIPCION_LENGHT = 245;
    public static final int MENSAJE_LENGHT = 5000;

    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
        
	@Column(name = "codigo")
    private String codigo;
    
	@Basic(optional = false)
    @NotNull
    @Column(name = "asunto")
    private String asunto;
	
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5000)
    @Column(name = "mensaje")
    private String mensaje;
	
	@Basic(optional = false)
    @NotNull
    @Column(name = "descripcion")
    private String descripcion;
    
	@Column(name = "gerente_adjunto")
    private Boolean gerenteAdjunto;
    
	@Column(name = "pmof")
    private Boolean pmof;
    
	@Column(name = "pmot")
    private Boolean pmot;
    
	@Column(name = "sponsor")
    private Boolean sponsor;
    
	@Version
	@Column(name = "version")
	private Integer version;

    public NotificacionDefecto() {
    }

    public NotificacionDefecto(Integer id) {
        this.id = id;
    }

    public NotificacionDefecto(Integer id, String descripcion, String mensaje) {
        this.id = id;
        this.descripcion = descripcion;
        this.mensaje = mensaje;
    }

    public NotificacionDefecto(String codigo, String asunto, String descripcion, 
			Boolean gerenteAdjunto, Boolean pmof, Boolean pmot, Boolean sponsor, String mensaje) {
        
        this.codigo = codigo;
        this.asunto = asunto;
		this.descripcion = descripcion;
        this.gerenteAdjunto = gerenteAdjunto;
        this.pmof = pmof;
        this.pmot = pmot;
        this.sponsor = sponsor;
        this.mensaje = mensaje;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getGerenteAdjunto() {
        return gerenteAdjunto;
    }

    public void setGerenteAdjunto(Boolean gerenteAdjunto) {
        this.gerenteAdjunto = gerenteAdjunto;
    }

    public Boolean getPmof() {
        return pmof;
    }

    public void setPmof(Boolean pmof) {
        this.pmof = pmof;
    }

    public Boolean getPmot() {
        return pmot;
    }

    public void setPmot(Boolean pmot) {
        this.pmot = pmot;
    }

    public Boolean getSponsor() {
        return sponsor;
    }

    public void setSponsor(Boolean sponsor) {
        this.sponsor = sponsor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof NotificacionDefecto)) {
            return false;
        }
        NotificacionDefecto other = (NotificacionDefecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Notificacion[ notPk=" + id + " ]";
    }
}
