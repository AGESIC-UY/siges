package com.sofis.entities.data;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "media_proyectos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "MediaProyectos.findAll", query = "SELECT m FROM MediaProyectos m")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaPk", query = "SELECT m FROM MediaProyectos m WHERE m.mediaPk = :mediaPk")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaLink", query = "SELECT m FROM MediaProyectos m WHERE m.mediaLink = :mediaLink")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaEstado", query = "SELECT m FROM MediaProyectos m WHERE m.mediaEstado = :mediaEstado")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaPrincipal", query = "SELECT m FROM MediaProyectos m WHERE m.mediaPrincipal = :mediaPrincipal")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaOrden", query = "SELECT m FROM MediaProyectos m WHERE m.mediaOrden = :mediaOrden")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaPubFecha", query = "SELECT m FROM MediaProyectos m WHERE m.mediaPubFecha = :mediaPubFecha")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaModFecha", query = "SELECT m FROM MediaProyectos m WHERE m.mediaModFecha = :mediaModFecha")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaRechFecha", query = "SELECT m FROM MediaProyectos m WHERE m.mediaRechFecha = :mediaRechFecha")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaComentario", query = "SELECT m FROM MediaProyectos m WHERE m.mediaComentario = :mediaComentario")
	,
    @NamedQuery(name = "MediaProyectos.findByMediaContenttype", query = "SELECT m FROM MediaProyectos m WHERE m.mediaContenttype = :mediaContenttype")})
public class MediaProyectos implements Serializable {

	public static final int LINK_LENGHT = 545;
	public static final int COMENTARIO_LENGHT = 2000;
	public static final int CONTENTTYPE_LENGHT = 2000;

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "media_pk")
	private Integer mediaPk;
	@Column(name = "media_link")
	private String mediaLink;
	@Column(name = "media_estado")
	private Integer mediaEstado;
	@Column(name = "media_principal")
	private Boolean mediaPrincipal;
	@Column(name = "media_orden")
	private Integer mediaOrden;
	@JoinColumn(name = "media_usr_pub_fk", referencedColumnName = "usu_id")
	@ManyToOne(optional = true)
	private SsUsuario mediaUsrPubFk;

	//la fecha de creacion
	@Column(name = "media_pub_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mediaPubFecha;

	@JoinColumn(name = "media_usr_mod_fk", referencedColumnName = "usu_id")
	@ManyToOne(optional = true)
	private SsUsuario mediaUsrModFk;

	//la fecha de modificacion
	@Column(name = "media_mod_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mediaModFecha;

	@JoinColumn(name = "media_usr_rech_fk", referencedColumnName = "usu_id")
	@ManyToOne(optional = true)
	private SsUsuario mediaUsrRechFk;

	@Column(name = "media_rech_fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mediaRechFecha;
	@Column(name = "media_comentario")
	private String mediaComentario;
	@Column(name = "media_contenttype")
	private String mediaContenttype;
	@Column(name = "media_publicable")
	private Boolean mediaPublicable;

	@JoinColumn(name = "media_tipo_fk", referencedColumnName = "tip_pk", nullable = false)
	@ManyToOne
	private TiposMedia mediaTipoFk;

	@JoinColumn(name = "media_proy_fk", referencedColumnName = "proy_pk")
	@ManyToOne
	private Proyectos mediaProyFk;

	@Transient
	private byte[] mediaBytes;

	@PrePersist
	public void prePersist() {
		mediaModFecha = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		mediaModFecha = new Date();
	}

	public MediaProyectos() {
	}

	public MediaProyectos(Integer mediaPk) {
		this.mediaPk = mediaPk;
	}

	public Integer getMediaPk() {
		return mediaPk;
	}

	public void setMediaPk(Integer mediaPk) {
		this.mediaPk = mediaPk;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public Integer getMediaEstado() {
		return mediaEstado;
	}

	public void setMediaEstado(Integer mediaEstado) {
		this.mediaEstado = mediaEstado;
	}

	public Boolean getMediaPrincipal() {
		return mediaPrincipal;
	}

	public void setMediaPrincipal(Boolean mediaPrincipal) {
		this.mediaPrincipal = mediaPrincipal;
	}

	public Integer getMediaOrden() {
		return mediaOrden;
	}

	public void setMediaOrden(Integer mediaOrden) {
		this.mediaOrden = mediaOrden;
	}

	public SsUsuario getMediaUsrPubFk() {
		return mediaUsrPubFk;
	}

	public void setMediaUsrPubFk(SsUsuario mediaUsrPubFk) {
		this.mediaUsrPubFk = mediaUsrPubFk;
	}

	public Date getMediaPubFecha() {
		return mediaPubFecha;
	}

	public void setMediaPubFecha(Date mediaPubFecha) {
		this.mediaPubFecha = mediaPubFecha;
	}

	public SsUsuario getMediaUsrModFk() {
		return mediaUsrModFk;
	}

	public void setMediaUsrModFk(SsUsuario mediaUsrModFk) {
		this.mediaUsrModFk = mediaUsrModFk;
	}

	public Date getMediaModFecha() {
		return mediaModFecha;
	}

	public void setMediaModFecha(Date mediaModFecha) {
		this.mediaModFecha = mediaModFecha;
	}

	public SsUsuario getMediaUsrRechFk() {
		return mediaUsrRechFk;
	}

	public void setMediaUsrRechFk(SsUsuario mediaUsrRechFk) {
		this.mediaUsrRechFk = mediaUsrRechFk;
	}

	public Date getMediaRechFecha() {
		return mediaRechFecha;
	}

	public void setMediaRechFecha(Date mediaRechFecha) {
		this.mediaRechFecha = mediaRechFecha;
	}

	public String getMediaComentario() {
		return mediaComentario;
	}

	public void setMediaComentario(String mediaComentario) {
		this.mediaComentario = mediaComentario;
	}

	public String getMediaContenttype() {
		return mediaContenttype;
	}

	public void setMediaContenttype(String mediaContenttype) {
		this.mediaContenttype = mediaContenttype;
	}

	public Boolean getMediaPublicable() {
		return mediaPublicable;
	}

	public boolean esMediaPublicable() {
		return mediaPublicable != null && mediaPublicable.booleanValue();
	}

	public void setMediaPublicable(Boolean mediaPublicable) {
		this.mediaPublicable = mediaPublicable;
	}

	public TiposMedia getMediaTipoFk() {
		return mediaTipoFk;
	}

	public void setMediaTipoFk(TiposMedia mediaTipoFk) {
		this.mediaTipoFk = mediaTipoFk;
	}

	public Proyectos getMediaProyFk() {
		return mediaProyFk;
	}

	public void setMediaProyFk(Proyectos mediaProyFk) {
		this.mediaProyFk = mediaProyFk;
	}

	public byte[] getMediaBytes() {
		return mediaBytes;
	}

	public void setMediaBytes(byte[] mediaBytes) {
		this.mediaBytes = mediaBytes;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mediaPk != null ? mediaPk.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof MediaProyectos)) {
			return false;
		}
		MediaProyectos other = (MediaProyectos) object;
		if ((this.mediaPk == null && other.mediaPk != null) || (this.mediaPk != null && !this.mediaPk.equals(other.mediaPk))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sofis.entities.data.MediaProyectos[ mediaPk=" + mediaPk + " ]";
	}

}
