package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CronogramaTO implements Serializable {

	private Integer id;
	private Integer idOrganismo;
        
        private Integer idFicha;

	private Integer estadoCronograma;
	private Integer estadoFicha;

	private boolean editable;
	private boolean periodoConfigurable;

	private boolean esPM;
	private boolean esPMO;
        private boolean isGerenteOAdjunto;

	private boolean admiteNuevosEntregables;

	private Integer esfuerzoTotal;
	private String horasTotales;

	private Integer entregableSeleccionado;

	private List<EntregableTO> entregables;

	private String entregablesEliminados;


    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public Integer getIdOrganismo() {
            return idOrganismo;
    }

    public void setIdOrganismo(Integer idOrganismo) {
            this.idOrganismo = idOrganismo;
    }

    public Integer getIdFicha() {
        return idFicha;
    }

    public void setIdFicha(Integer idFicha) {
        this.idFicha = idFicha;
    }

    public Integer getEstadoCronograma() {
        return estadoCronograma;
    }

    public void setEstadoCronograma(Integer estadoCronograma) {
        this.estadoCronograma = estadoCronograma;
    }

    public Integer getEstadoFicha() {
        return estadoFicha;
    }

    public void setEstadoFicha(Integer estadoFicha) {
        this.estadoFicha = estadoFicha;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean getPeriodoConfigurable() {
        return periodoConfigurable;
    }

    public void setPeriodoConfigurable(boolean periodoConfigurable) {
        this.periodoConfigurable = periodoConfigurable;
    }

    public boolean getEsPM() {
        return esPM;
    }

    public void setEsPM(boolean esPM) {
        this.esPM = esPM;
    }

    public boolean getEsPMO() {
        return esPMO;
    }

    public void setEsPMO(boolean esPMO) {
        this.esPMO = esPMO;
    }

    public boolean getAdmiteNuevosEntregables() {
        return admiteNuevosEntregables;
    }

    public void setAdmiteNuevosEntregables(boolean admiteNuevosEntregables) {
        this.admiteNuevosEntregables = admiteNuevosEntregables;
    }

    public Integer getEsfuerzoTotal() {
        return esfuerzoTotal;
    }

    public void setEsfuerzoTotal(Integer esfuerzoTotal) {
        this.esfuerzoTotal = esfuerzoTotal;
    }

    public String getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(String horasTotales) {
        this.horasTotales = horasTotales;
    }

    public Integer getEntregableSeleccionado() {
        return entregableSeleccionado;
    }

    public void setEntregableSeleccionado(Integer entregableSeleccionado) {
        this.entregableSeleccionado = entregableSeleccionado;
    }

    public List<EntregableTO> getEntregables() {
        return entregables;
    }

    public void setEntregables(List<EntregableTO> entregables) {
        this.entregables = entregables;
    }

    public String getEntregablesEliminados() {
        return entregablesEliminados;
    }

    public void setEntregablesEliminados(String entregablesEliminados) {
        this.entregablesEliminados = entregablesEliminados;
    }
        
    public boolean getIsGerenteOAdjunto() {
        return isGerenteOAdjunto;
    }

    public void setIsGerenteOAdjunto(boolean isGerenteOAdjunto) {
        this.isGerenteOAdjunto = isGerenteOAdjunto;
    }
    
        

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 71 * hash + Objects.hashCode(this.id);
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
		final CronogramaTO other = (CronogramaTO) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

}
