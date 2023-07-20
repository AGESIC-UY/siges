package com.sofis.web.mb;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.data.ConfiguracionDefecto;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import com.sofis.web.delegates.ConfiguracionDefectoDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.EntityReferenceDataProvider;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.LazyLoadingList;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class ConfiguracionDefectoOrganismosMB implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ConfiguracionDefectoOrganismosMB.class.getName());

	private static final String POPUP_MSG = "confGeneralMsgs";

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@Inject
	private ConfiguracionDefectoDelegate configuracionDefectoDelegate;

	private String codigo;
	private String descripcion;
	private Boolean renderResultado = false;
	private ConfiguracionDefecto confEnEdicion = new ConfiguracionDefecto();
	private Boolean renderPopupEdicion = false;
	private String cantElementosPorPagina = "25";
	private List<EntityReference<Integer>> listaResultado = new ArrayList<>();
	private SofisCombo comboCategoria = new SofisCombo();

	@PostConstruct
	public void init() {
		inicioMB.cargarOrganismoSeleccionado();
		comboCategoria = new SofisCombo();
		comboCategoria.addEmptyItem(ConstantesPresentacion.DEFECTO_COMBO);
		comboCategoria.setSelected(0);

		if (confEnEdicion.getValor() == null || confEnEdicion.getValor().equals("")) {
			confEnEdicion.setValor("<p></p>");
		}

		buscar();
	}

	private void reset() {
		codigo = "";
		descripcion = "";
		listaResultado = new ArrayList<>();
		renderResultado = false;
	}

	public String buscar() {
		renderResultado = true;
		List<CriteriaTO> criterios = new ArrayList<>();
                
		if (!StringsUtils.isEmpty(codigo)) {
			MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "codigo", codigo.toUpperCase().trim());
			criterios.add(criterio);
		}

		if (!StringsUtils.isEmpty(descripcion)) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("descripcion", descripcion.toUpperCase().trim());
			criterios.add(criterio);
		}

		CriteriaTO condicion;
		if (!criterios.isEmpty()) {
			if (criterios.size() == 1) {
				condicion = criterios.get(0);
			} else {
				condicion = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
			}
		} else {
			// condicion dummy para que el count by criteria funcione
			condicion = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.NOT_NULL, "id", 1);
		}

		String[] propiedades = {"id", "codigo", "valor", "descripcion"};
		String className = ConfiguracionDefecto.class.getName();
		String[] orderBy = {"codigo"};
		boolean[] asc = {true};

		EntityReferenceDataProvider cd = new EntityReferenceDataProvider(propiedades, className, condicion, orderBy, asc);
		listaResultado = new LazyLoadingList(cd, ConstantesPresentacion.CANTIDAD_PAGINACION, ConstantesPresentacion.PAGINAS_BUFFERED, false);

		return null;
	}

	public String limpiar() {
		reset();
		return null;
	}

	public String agregar() {
		confEnEdicion = new ConfiguracionDefecto();
		renderPopupEdicion = true;

		return null;
	}

	public String editar(Integer id) {
		try {
			confEnEdicion = configuracionDefectoDelegate.obtenerPorId(id);
			renderPopupEdicion = true;
		} catch (GeneralException ex) {
			Logger.getLogger(ConfiguracionDefectoOrganismosMB.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void cnfHtmlChange(ValueChangeEvent ev) {
		confEnEdicion.setEsHTML((Boolean) ev.getNewValue());
	}

	public String guardar() {
		try {
			configuracionDefectoDelegate.guardar(confEnEdicion);
			renderPopupEdicion = false;
			buscar();
		} catch (GeneralException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
			for (String iterStr : ex.getErrores()) {
				JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
			}
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public String cancelar() {

		renderPopupEdicion = false;
		return null;
	}

	public void cambiarCantPaginas(ValueChangeEvent evt) {
		buscar();
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public String getCantElementosPorPagina() {
		return cantElementosPorPagina;
	}

	public void setCantElementosPorPagina(String cantElementosPorPagina) {
		this.cantElementosPorPagina = cantElementosPorPagina;
	}

	public ConfiguracionDefecto getConfEnEdicion() {
		return confEnEdicion;
	}

	public void setConfEnEdicion(ConfiguracionDefecto confEnEdicion) {
		this.confEnEdicion = confEnEdicion;
	}

	public Boolean getRenderPopupEdicion() {
		return renderPopupEdicion;
	}

	public void setRenderPopupEdicion(Boolean renderPopupEdicion) {
		this.renderPopupEdicion = renderPopupEdicion;
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

	public List<EntityReference<Integer>> getListaResultado() {
		return listaResultado;
	}

	public void setListaResultado(List<EntityReference<Integer>> listaResultado) {
		this.listaResultado = listaResultado;
	}

	public SofisCombo getComboCategoria() {
		return comboCategoria;
	}

	public void setComboCategoria(SofisCombo comboCategoria) {
		this.comboCategoria = comboCategoria;
	}

	public Boolean getRenderResultado() {
		return renderResultado;
	}

	public void setRenderResultado(Boolean renderResultado) {
		this.renderResultado = renderResultado;
	}

}
