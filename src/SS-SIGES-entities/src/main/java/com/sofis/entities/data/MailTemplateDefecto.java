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

@Entity
@Table(name = "mails_template_defecto")
public class MailTemplateDefecto implements Serializable {

	public static final int CODIGO_LENGHT = 45;
	public static final int ASUNTO_LENGHT = 45;
	public static final int MENSAJE_LENGHT = 5000;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "codigo")
	private String codigo;

	@Basic(optional = false)
	@NotNull
	@Column(name = "asunto")
	private String asunto;

	@Basic(optional = false)
	@NotNull
	@Column(name = "mensaje")
	private String mensaje;

	@Version
	@Column(name = "version")
	private Integer version;

	public MailTemplateDefecto() {
	}

	public MailTemplateDefecto(Integer id) {
		this.id = id;
	}

	public MailTemplateDefecto(String codigo, String asunto, String mensaje) {
		this.codigo = codigo;
		this.asunto = asunto;
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

		if (!(object instanceof MailTemplateDefecto)) {
			return false;
		}
		MailTemplateDefecto other = (MailTemplateDefecto) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}
