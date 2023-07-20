package com.sofis.entities.data;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "mails_template")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MailsTemplate.findAll", query = "SELECT m FROM MailsTemplate m"),
    @NamedQuery(name = "MailsTemplate.findByMailTmpPk", query = "SELECT m FROM MailsTemplate m WHERE m.mailTmpPk = :mailTmpPk"),
    @NamedQuery(name = "MailsTemplate.findByMailTmpCod", query = "SELECT m FROM MailsTemplate m WHERE m.mailTmpCod = :mailTmpCod"),
    @NamedQuery(name = "MailsTemplate.findByMailTmpAsunto", query = "SELECT m FROM MailsTemplate m WHERE m.mailTmpAsunto = :mailTmpAsunto"),
    @NamedQuery(name = "MailsTemplate.findByMailTmpMensaje", query = "SELECT m FROM MailsTemplate m WHERE m.mailTmpMensaje = :mailTmpMensaje")
})
public class MailsTemplate implements Serializable {

	public static final int CODIGO_LENGHT = 45;
	public static final int ASUNTO_LENGHT = 45;
	public static final int MENSAJE_LENGHT = 5000;

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "mail_tmp_pk")
	private Integer mailTmpPk;

	@Basic(optional = false)
	@NotNull
	@Column(name = "mail_tmp_cod")
	private String mailTmpCod;

	@Basic(optional = false)
	@NotNull
	@Column(name = "mail_tmp_asunto")
	private String mailTmpAsunto;

	@Basic(optional = false)
	@NotNull
	@Column(name = "mail_tmp_mensaje")
	private String mailTmpMensaje;

	@JoinColumn(name = "mail_tmp_org_fk", referencedColumnName = "org_pk")
	@ManyToOne(optional = false)
	private Organismos mailTmpOrgFk;

	public MailsTemplate() {
	}

	public MailsTemplate(Integer mailTmpPk) {
		this.mailTmpPk = mailTmpPk;
	}

	public MailsTemplate(String mailTmpCod, String mailTmpAsunto, String mailTmpMensaje, Organismos org) {
		this.mailTmpCod = mailTmpCod;
		this.mailTmpAsunto = mailTmpAsunto;
		this.mailTmpMensaje = mailTmpMensaje;
		this.mailTmpOrgFk = org;
	}

	public Integer getMailTmpPk() {
		return mailTmpPk;
	}

	public void setMailTmpPk(Integer mailTmpPk) {
		this.mailTmpPk = mailTmpPk;
	}

	public String getMailTmpCod() {
		return mailTmpCod;
	}

	public void setMailTmpCod(String mailTmpCod) {
		this.mailTmpCod = mailTmpCod;
	}

	public String getMailTmpAsunto() {
		return mailTmpAsunto;
	}

	public void setMailTmpAsunto(String mailTmpAsunto) {
		this.mailTmpAsunto = mailTmpAsunto;
	}

	public String getMailTmpMensaje() {
		return mailTmpMensaje;
	}

	public void setMailTmpMensaje(String mailTmpMensaje) {
		this.mailTmpMensaje = mailTmpMensaje;
	}

	public Organismos getMailTmpOrgFk() {
		return mailTmpOrgFk;
	}

	public void setMailTmpOrgFk(Organismos mailTmpOrgFk) {
		this.mailTmpOrgFk = mailTmpOrgFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (mailTmpPk != null ? mailTmpPk.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {

		if (!(object instanceof MailsTemplate)) {
			return false;
		}
		MailsTemplate other = (MailsTemplate) object;
		if ((this.mailTmpPk == null && other.mailTmpPk != null)
				|| (this.mailTmpPk != null && !this.mailTmpPk.equals(other.mailTmpPk))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.sofis.entities.data.MailsTemplate[ mailTmpPk=" + mailTmpPk + " ]";
	}
}
