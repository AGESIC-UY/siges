package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProyectoTO implements Serializable {

	private Integer id;
	private String nombre;

	private EstadoTO estado;

	private Integer idPrograma;
	private String nombrePrograma;

	private UsuarioTO gerente;
	private UsuarioTO adjunto;
	private UsuarioTO pmof;
	private UsuarioTO sponsor;

	private AreaTO area;

	private Boolean activo;

	private Date fechaActualizacion;
	private Date fechaCambioActivacion;
	private String usuarioCambioActivacion;

	private List<SaldoTO> saldos;
	private List<AdquisicionTO> adquisiciones;

	private Integer idPresupuesto;
	private Long cantidadAdquisiciones;

	public ProyectoTO() {
		saldos = new ArrayList<>();
		adquisiciones = new ArrayList<>();
	}

	public ProyectoTO(Integer id, String nombre, Integer idEstado, String labelEstado,
			Integer idPrograma, String nombrePrograma,
			Integer idGerente, String nombreGerente, String apellidoGerente,
			Integer idAdjunto, String nombreAdjunto, String apellidoAdjunto,
			Integer idPMOF, String nombrePMOF, String apellidoPMOF,
			Integer idSponsor, String nombreSponsor, String apellidoSponsor,
			Integer idArea, String nombreArea,
			Integer idPresupuesto, Long cantidadAdquisiciones) {

		this();

		this.id = id;
		this.nombre = nombre;

		this.estado = new EstadoTO(idEstado, labelEstado);

		this.idPrograma = idPrograma;
		this.nombrePrograma = nombrePrograma;

		if (idGerente != null) {
			this.gerente = new UsuarioTO(idGerente, nombreGerente, apellidoGerente);
		}

		if (idAdjunto != null) {
			this.adjunto = new UsuarioTO(idAdjunto, nombreAdjunto, apellidoAdjunto);
		}

		if (idPMOF != null) {
			this.pmof = new UsuarioTO(idPMOF, nombrePMOF, apellidoPMOF);
		}

		if (idSponsor != null) {
			this.sponsor = new UsuarioTO(idSponsor, nombreSponsor, apellidoSponsor);
		}

		if (idArea != null) {
			this.area = new AreaTO(idArea, nombreArea);
		}

		this.idPresupuesto = idPresupuesto;
		this.cantidadAdquisiciones = cantidadAdquisiciones;
	}

	public ProyectoTO(Integer id, String nombre, Integer idEstado, String labelEstado,
			Integer idGerente, String nombreGerente, String apellidoGerente,
			Integer idAdjunto, String nombreAdjunto, String apellidoAdjunto,
			Date fechaActualizacion, Boolean activo,
			Date fechaCambioActivacion, String usuarioCambioActivacion,
			Integer idArea, String nombreArea) {

		this();

		this.id = id;
		this.nombre = nombre;

		this.estado = new EstadoTO(idEstado, labelEstado);

		if (idGerente != null) {
			this.gerente = new UsuarioTO(idGerente, nombreGerente, apellidoGerente);
		}

		if (idAdjunto != null) {
			this.adjunto = new UsuarioTO(idAdjunto, nombreAdjunto, apellidoAdjunto);
		}

		if (idArea != null) {
			this.area = new AreaTO(idArea, nombreArea);
		}

		this.activo = activo;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaCambioActivacion = fechaCambioActivacion;
		this.usuarioCambioActivacion = usuarioCambioActivacion;
	}
        
        
        
        public ProyectoTO(Integer id, String nombre, Integer idEstado, String labelEstado,
			Integer idGerente, String nombreGerente, String apellidoGerente,
			Integer idAdjunto, String nombreAdjunto, String apellidoAdjunto,
			Date fechaActualizacion, Boolean activo,
			Date fechaCambioActivacion, String usuarioCambioActivacion,
			Integer idArea, String nombreArea,Integer idPmof, String nombrePmof, String apellidoPmof,
			Integer idSponsor, String nombreSponsor, String apellidoSponsor) {

		this();

		this.id = id;
		this.nombre = nombre;

		this.estado = new EstadoTO(idEstado, labelEstado);

		if (idGerente != null) {
			this.gerente = new UsuarioTO(idGerente, nombreGerente, apellidoGerente);
		}

		if (idAdjunto != null) {
			this.adjunto = new UsuarioTO(idAdjunto, nombreAdjunto, apellidoAdjunto);
		}
                
                if (idPmof != null) {
			this.pmof = new UsuarioTO(idPmof, nombrePmof, apellidoPmof);
		}
                
                if (idSponsor != null) {
			this.sponsor = new UsuarioTO(idSponsor, nombreSponsor, apellidoSponsor);
		}

		if (idArea != null) {
			this.area = new AreaTO(idArea, nombreArea);
		}

		this.activo = activo;
		this.fechaActualizacion = fechaActualizacion;
		this.fechaCambioActivacion = fechaCambioActivacion;
		this.usuarioCambioActivacion = usuarioCambioActivacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(Integer idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getNombrePrograma() {
		return nombrePrograma;
	}

	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}

	public EstadoTO getEstado() {
		return estado;
	}

	public void setEstado(EstadoTO estado) {
		this.estado = estado;
	}

	public UsuarioTO getGerente() {
		return gerente;
	}

	public void setGerente(UsuarioTO gerente) {
		this.gerente = gerente;
	}

	public UsuarioTO getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(UsuarioTO adjunto) {
		this.adjunto = adjunto;
	}

	public UsuarioTO getPmof() {
		return pmof;
	}

	public void setPmof(UsuarioTO pmof) {
		this.pmof = pmof;
	}

	public UsuarioTO getSponsor() {
		return sponsor;
	}

	public void setSponsor(UsuarioTO sponsor) {
		this.sponsor = sponsor;
	}

	public AreaTO getArea() {
		return area;
	}

	public void setArea(AreaTO area) {
		this.area = area;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<SaldoTO> getSaldos() {
		return saldos;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioCambioActivacion() {
		return usuarioCambioActivacion;
	}

	public void setUsuarioCambioActivacion(String usuarioCambioActivacion) {
		this.usuarioCambioActivacion = usuarioCambioActivacion;
	}

	public Date getFechaCambioActivacion() {
		return fechaCambioActivacion;
	}

	public void setFechaCambioActivacion(Date fechaCambioActivacion) {
		this.fechaCambioActivacion = fechaCambioActivacion;
	}

	public void setSaldos(List<SaldoTO> saldos) {
		this.saldos = saldos;
	}

	public List<AdquisicionTO> getAdquisiciones() {
		return adquisiciones;
	}

	public void setAdquisiciones(List<AdquisicionTO> adquisiciones) {
		this.adquisiciones = adquisiciones;
	}

	public Integer getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(Integer idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	public Long getCantidadAdquisiciones() {
		return cantidadAdquisiciones;
	}

	public void setCantidadAdquisiciones(Long cantidadAdquisiciones) {
		this.cantidadAdquisiciones = cantidadAdquisiciones;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 43 * hash + Objects.hashCode(this.id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ProyectoTO other = (ProyectoTO) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
