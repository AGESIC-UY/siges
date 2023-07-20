package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;

@Entity
@Table(name = "ss_configuraciones_defecto")
public class ConfiguracionDefecto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int CODIGO_LENGHT = 145;
	public static final int VALOR_LENGHT = 45;
	public static final int DESCRIPCION_LENGHT = 245;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "valor")
	private String valor;

	@Column(name = "es_html")
	private Boolean esHTML;

	@Column(name = "ultima_modificacion")
	@AtributoUltimaModificacion
	@Temporal(javax.persistence.TemporalType.TIMESTAMP)
	private Date ultimaModificacion;

	@Column(name = "usuario_modificacion")
	@AtributoUltimoUsuario
	private String usuarioModificacion;

	@Version
	@Column(name = "version")
	private Integer version;

	public ConfiguracionDefecto() {
	}

	public ConfiguracionDefecto(String cnfCodigo) {
		this.codigo = cnfCodigo;
	}

	public ConfiguracionDefecto(String cnfCodigo, String cnfDescripcion, String cnfValor) {
		this.codigo = cnfCodigo;
		this.descripcion = cnfDescripcion;
		this.valor = cnfValor;
	}

	@PrePersist
	@PreUpdate
	public void asignarUltMod() {
		this.ultimaModificacion = new Date();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getEsHTML() {
		return esHTML;
	}

	public void setEsHTML(Boolean esHTML) {
		this.esHTML = esHTML;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
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
		if (!(object instanceof ConfiguracionDefecto)) {
			return false;
		}
		ConfiguracionDefecto other = (ConfiguracionDefecto) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sofis.generico.entities.Configuracion[ id=" + id + " ]";
	}
}
