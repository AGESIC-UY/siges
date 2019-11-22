package com.sofis.web.mb;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import com.sofis.web.delegates.ConfiguracionDelegate;
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

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "configuracionGeneralMB")
@ViewScoped
public class ConfiguracionGeneralMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ConfiguracionGeneralMB.class.getName());
        
        private static final String POPUP_MSG = "confGeneralMsgs";
	
	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;
	@Inject
	private ConfiguracionDelegate confDelegate;
	
	private String bCodigo;
	private String bDescripcion;
	private Boolean renderResultado = false;
	private Configuracion confEnEdicion = new Configuracion();
	private Boolean renderPopupEdicion = false;
	private String cantElementosPorPagina = "25";
	private List<EntityReference<Integer>> listaResultado = new ArrayList<EntityReference<Integer>>();
	private SofisCombo comboCategoria = new SofisCombo();

	/**
	 * Creates a new instance of ConfiguracionMB
	 */
	public ConfiguracionGeneralMB() {
	}
	
	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}
	
	@PostConstruct
	public void init() {
		inicioMB.cargarOrganismoSeleccionado();
		comboCategoria = new SofisCombo();
		comboCategoria.addEmptyItem(ConstantesPresentacion.DEFECTO_COMBO);
		comboCategoria.setSelected(0);
                if(confEnEdicion.getCnfValor() == null || confEnEdicion.getCnfValor().equals("")){
                    confEnEdicion.setCnfValor("<p></p>");
                }
                buscar();
	}
	
	private void reset() {
		bCodigo = "";
		bDescripcion = "";
		listaResultado = new ArrayList<EntityReference<Integer>>();
		renderResultado = false;
	}

	// <editor-fold defaultstate="collapsed" desc="eventos">
	public String buscar() {
		renderResultado = true;
		List<CriteriaTO> criterios = new ArrayList<CriteriaTO>();
		
		MatchCriteriaTO criterioSinOrg = CriteriaTOUtils.createMatchCriteriaTO(
			MatchCriteriaTO.types.NULL, "cnfOrgFk.orgPk",
			"0");
		criterios.add(criterioSinOrg);
		
		if (!StringsUtils.isEmpty(bCodigo)) {
			MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
				MatchCriteriaTO.types.CONTAINS, "cnfCodigo",
				bCodigo.toUpperCase().trim());
			criterios.add(criterio);
		}
		
		if (!StringsUtils.isEmpty(bDescripcion)) {
			CriteriaTO criterio = DAOUtils.createMatchCriteriaTOString("cnfDescripcion", bDescripcion.toUpperCase().trim());
			criterios.add(criterio);
		}
		CriteriaTO condicion = null;
		if (!criterios.isEmpty()) {
			if (criterios.size() == 1) {
				condicion = criterios.get(0);
			} else {
				condicion = CriteriaTOUtils.createANDTO(criterios
					.toArray(new CriteriaTO[0]));
			}
		} else {
			// condicion dummy para que el count by criteria funcione
			condicion = CriteriaTOUtils.createMatchCriteriaTO(
				MatchCriteriaTO.types.NOT_NULL, "id", 1);
		}
		String[] propiedades = {"id", "cnfCodigo", "cnfValor", "cnfDescripcion", "cnfOrgFk.orgNombre"};
		String className = Configuracion.class.getName();
		String[] orderBy = {"cnfCodigo"};
		boolean[] asc = {true};
		
		EntityReferenceDataProvider cd = new EntityReferenceDataProvider(
			propiedades, className, condicion, orderBy, asc);
		listaResultado = new LazyLoadingList(cd, ConstantesPresentacion.CANTIDAD_PAGINACION, ConstantesPresentacion.PAGINAS_BUFFERED, false);
		return null;
	}
	
	public String limpiar() {
		reset();
		return null;
	}
	
	public String agregar() {
		confEnEdicion = new Configuracion();
		confEnEdicion.setCnfOrgFk(new Organismos());
		renderPopupEdicion = true;
		return null;
	}
	
	public String editar(Integer id) {
		try {
			confEnEdicion = confDelegate.obtenerCnfPorId(id);
			renderPopupEdicion = true;
		} catch (GeneralException ex) {
			Logger.getLogger(ConfiguracionGeneralMB.class.getName()).log(Level.SEVERE, null, ex);                     
		}
		return null;
	}
	
	public void cnfHtmlChange(ValueChangeEvent ev) {
		confEnEdicion.setCnfHtml((Boolean) ev.getNewValue());
	}
	
	public String guardar() {
		try {
			confEnEdicion.setCnfOrgFk(null);
			confDelegate.guardar(confEnEdicion);
			renderPopupEdicion = false;
			buscar();
		} catch (GeneralException ex) {
			logger.log(Level.SEVERE, null, ex);
			//JSFUtils.agregarMsgs("", ex.getErrores());
                        /*
                        *   28-06-2018 Nico: Se agrega este for para poder mostrar en el front todos los mensajes
                        *           enviados desde la lógica.
                        */
                        
                        for(String iterStr : ex.getErrores()){
                            JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);                
                        }                           
		} catch (Exception ex) {
			logger.log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
	public String cancelar() {
		//reset();
		renderPopupEdicion = false;
		return null;
	}
	
	public void cambiarCantPaginas(ValueChangeEvent evt) {
		buscar();
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc="getter-setter">
	public String getCantElementosPorPagina() {
		return cantElementosPorPagina;
	}
	
	public void setCantElementosPorPagina(String cantElementosPorPagina) {
		this.cantElementosPorPagina = cantElementosPorPagina;
	}
	
	public Configuracion getConfEnEdicion() {
		return confEnEdicion;
	}
	
	public void setConfEnEdicion(Configuracion confEnEdicion) {
		this.confEnEdicion = confEnEdicion;
	}
	
	public Boolean getRenderPopupEdicion() {
		return renderPopupEdicion;
	}
	
	public void setRenderPopupEdicion(Boolean renderPopupEdicion) {
		this.renderPopupEdicion = renderPopupEdicion;
	}
	
	public String getbCodigo() {
		return bCodigo;
	}
	
	public void setbCodigo(String bCodigo) {
		this.bCodigo = bCodigo;
	}
	
	public String getbDescripcion() {
		return bDescripcion;
	}
	
	public void setbDescripcion(String bDescripcion) {
		this.bDescripcion = bDescripcion;
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
	// </editor-fold>

	public String obtenerCnfValorPorCodigo(String codigo, Integer orgPk) {
		return confDelegate.obtenerCnfValorPorCodigo(codigo, orgPk);
	}
}
