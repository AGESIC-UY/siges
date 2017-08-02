package com.sofis.entities.data;

import com.sofis.entities.codigueras.EstadoPublicacionCodigos;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
public class ProyectoExpVisualizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proy_pk")
    private Integer proyPk;

    @Column(name = "proy_nombre")
    private String proyNombre;

    @JoinColumn(name = "proy_usr_gerente_fk", referencedColumnName = "usu_id")
    @ManyToOne(optional = false)
    private SsUsuario proyUsrGerenteFk;

    @JoinColumn(name = "proy_prog_fk", referencedColumnName = "prog_pk")
    @ManyToOne(optional = true)
    private Programas proyProgFk;

    @JoinColumn(name = "proy_area_fk", referencedColumnName = "area_pk")
    @ManyToOne(optional = false)
    private Areas proyAreaFk;

    @JoinColumn(name = "proy_org_fk", referencedColumnName = "org_pk")
    @ManyToOne(optional = false)
    private Organismos proyOrgFk;

    @JoinColumn(name = "proy_est_fk", referencedColumnName = "est_pk")
    @ManyToOne(optional = false)
    private Estados proyEstFk;

    /**
     * Historico de publicaciones en el Visualizador
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "proyectoFk")
    @Fetch(FetchMode.SELECT)
    private Set<ProyPublicaHist> proyPublicaHist;

    @JoinColumn(name = "proy_otr_dat_fk", referencedColumnName = "proy_otr_pk")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ProyOtrosDatos proyOtrosDatos;

    /**
     * Marca al Proyecto como publicable para el Visualizador.
     */
    @Column(name = "proy_publicable")
    private Boolean proyPublicable;

    /**
     * Indica si el Programa está activo o eliminado logicamente.
     */
    @Column(name = "proy_activo")
    private Boolean activo;

    @Column(name = "proy_fecha_act")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date proyFechaAct;

    /**
     * 14-12-2016
     * @return 
     */
    public Boolean puedePublicarse() {
	return (this.proyOtrosDatos != null
		&& this.proyOtrosDatos.getProyOtrEstPubFk() != null
		&& (this.proyOtrosDatos.getProyOtrEstPubFk().getEstPubCodigo().equals(EstadoPublicacionCodigos.PENDIENTE_PUBLICAR)
		|| this.proyOtrosDatos.getProyOtrEstPubFk().getEstPubCodigo().equals(EstadoPublicacionCodigos.PUBLICADO)));

    }

    public Integer getProyPk() {
	return proyPk;
    }

    public void setProyPk(Integer proyPk) {
	this.proyPk = proyPk;
    }

    public String getProyNombre() {
	return proyNombre;
    }

    public void setProyNombre(String proyNombre) {
	this.proyNombre = proyNombre;
    }

    public SsUsuario getProyUsrGerenteFk() {
	return proyUsrGerenteFk;
    }

    public void setProyUsrGerenteFk(SsUsuario proyUsrGerenteFk) {
	this.proyUsrGerenteFk = proyUsrGerenteFk;
    }

    public Programas getProyProgFk() {
	return proyProgFk;
    }

    public void setProyProgFk(Programas proyProgFk) {
	this.proyProgFk = proyProgFk;
    }

    public Areas getProyAreaFk() {
	return proyAreaFk;
    }

    public void setProyAreaFk(Areas proyAreaFk) {
	this.proyAreaFk = proyAreaFk;
    }

    public Organismos getProyOrgFk() {
	return proyOrgFk;
    }

    public void setProyOrgFk(Organismos proyOrgFk) {
	this.proyOrgFk = proyOrgFk;
    }

    public Estados getProyEstFk() {
	return proyEstFk;
    }

    public void setProyEstFk(Estados proyEstFk) {
	this.proyEstFk = proyEstFk;
    }

    public ProyOtrosDatos getProyOtrosDatos() {
	return proyOtrosDatos;
    }

    public void setProyOtrosDatos(ProyOtrosDatos proyOtrosDatos) {
	this.proyOtrosDatos = proyOtrosDatos;
    }

    public Boolean getProyPublicable() {
	return proyPublicable;
    }

    public void setProyPublicable(Boolean proyPublicable) {
	this.proyPublicable = proyPublicable;
    }

    public Boolean getActivo() {
	return activo;
    }

    public void setActivo(Boolean activo) {
	this.activo = activo;
    }

    public Date getProyFechaAct() {
	return proyFechaAct;
    }

    public void setProyFechaAct(Date proyFechaAct) {
	this.proyFechaAct = proyFechaAct;
    }

    public Date getUltimaPublicacion() {
	if (CollectionsUtils.isNotEmpty(proyPublicaHist)) {
	    ProyPublicaHist pph = null;
	    Calendar dateLast = new GregorianCalendar();
	    for (ProyPublicaHist proyPub : proyPublicaHist) {
		Calendar datePub = new GregorianCalendar();
		datePub.setTime(proyPub.getProyPublicaFecha());
		if (pph == null || (datePub.after(dateLast))) {
		    pph = proyPub;
		    dateLast.setTime(pph.getProyPublicaFecha());
		}
	    }
	    return pph.getProyPublicaFecha();
	}
	return null;
    }
}
