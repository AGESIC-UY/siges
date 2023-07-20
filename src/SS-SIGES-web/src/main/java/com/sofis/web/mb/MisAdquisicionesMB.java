package com.sofis.web.mb;

import com.sofis.business.utils.OrganiIntProveUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.FuenteProcedimientoCompra;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.CausalCompraTO;
import com.sofis.entities.tipos.EstadoTO;
import com.sofis.entities.tipos.FiltroMisAdquisicionesTO;
import com.sofis.entities.tipos.FuenteFinanciamientoTO;
import com.sofis.entities.tipos.MisAdquisicionesTO;
import com.sofis.entities.tipos.OrganizacionTO;
import com.sofis.entities.tipos.PagoTO;
import com.sofis.entities.tipos.ProcedimientoCompraTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.entities.utils.CausalCompraTOUtils;
import com.sofis.entities.utils.FuenteFinanciamientoTOUtils;
import com.sofis.entities.utils.OrganizacionTOUtils;
import com.sofis.entities.utils.ProcedimientoCompraTOUtils;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.entities.validations.FichaAdquisicionValidacion;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.TechnicalException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
import com.sofis.web.delegates.FuenteProcedimientoCompraDelegate;
import com.sofis.web.delegates.MisAdquisicionesDelegate;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.ProcedimientoCompraDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.PagoUtils;
import com.sofis.web.utils.RepeatPaginator;
import com.sofis.web.utils.SofisComboG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.omnifaces.util.Ajax;

@ManagedBean(name = "misAdquisicionesMB")
@ViewScoped
public class MisAdquisicionesMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(MisAdquisicionesMB.class.getName());

	private static final String GUARDAR_ADQUISICION_MSG = "guardarAdquisicion";
	private static final String GUARDAR_PAGO_MSG = "guardarPago";
	private static final String CONFIRMAR_PAGO_MSG = "confirmarPago";

	@ManagedProperty("#{inicioMB}")
	private InicioMB inicioMB;

	@ManagedProperty("#{aplicacionMB}")
	private AplicacionMB aplicacionMB;

	@Inject
	private AreasDelegate areasDelegate;

	@Inject
	private SsUsuarioDelegate ssUsuarioDelegate;

	@Inject
	private OrganiIntProveDelegate organizacionDelegate;

	@Inject
	private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;

	@Inject
	private MonedaDelegate monedaDelegate;

	@Inject
	private ProcedimientoCompraDelegate procedimientoCompraDelegate;

	@Inject
	private FuenteProcedimientoCompraDelegate fuenteProcedimientoCompraDelegate;

	@Inject
	private MisAdquisicionesDelegate misAdquisicionesDelegate;

	@Inject
	private ConfiguracionDelegate configuracionDelegate;

	private FiltroMisAdquisicionesTO filtro;
	private MisAdquisicionesTO misAdquisiciones;

	private List<SelectItem> listaEstados;

	private SofisComboG<Areas> areaFiltroCombo;
	private SofisComboG<SsUsuario> gerenteAdjuntoComboFiltro;
	private SofisComboG<SsUsuario> pmofComboFiltro;

	private SofisComboG<OrganiIntProve> proveedorFiltroCombo;
	private SofisComboG<FuenteFinanciamiento> fuenteFinanciamientoFiltroCombo;
	private SofisComboG<Moneda> monedaFiltroCombo;
	private SofisComboG<ProcedimientoCompra> procedimientoCompraFiltroCombo;

	private Map<Integer, Boolean> proyectosExpandidos;
	private Map<Integer, Map<Integer, Boolean>> adquisicionesExpandidas;

	private PagoTO pagoEnEdicion;
	private PagoTO pagoAConfirmar;
	private PagoTO pagoEnVisualizacion;

	private AdquisicionTO adquisicionEnEdicion;
	private AdquisicionTO adquisicionEnVisualizacion;

	private boolean mostrarFiltro;
	private boolean ocultarPagosConfirmados;

	private SofisComboG<OrganizacionTO> proveedorAdquisicionCombo;
	private SofisComboG<FuenteFinanciamientoTO> fuenteFinanciamientoAdquisicionCombo;
	private SofisComboG<CausalCompraTO> causalCompraAdquisicionCombo;
	private SofisComboG<ProcedimientoCompraTO> procedimientoCompraAdquisicionCombo;

	private List<ProcedimientoCompraTO> procedimientosCompraHabilitados;
	private List<FuenteFinanciamientoTO> fuentesFinanciamientoHabilitadas;
	private List<OrganizacionTO> proveedoresHabilitados;
	private List<CausalCompraTO> causalesCompraHabilitados;

	private SofisComboG<OrganizacionTO> proveedorPagoCombo;
	private SofisComboG<OrganizacionTO> clientePagoCombo;

	private List<OrganizacionTO> clientesHabilitados;

	private boolean exigeProveedorEnCompra;
	private boolean camposExigidosEnAdquisicion;
	private int largoMaximoIdAdquisicion;
	private boolean exigeProveedorEnPago;
	private boolean exigeClienteEnPago;

	private boolean mostrarMensajesConfirmacion;

	private RepeatPaginator paginator;
        
        /**
	 * Compara en primer lugar por fecha de planificacion y luego por fecha real
	 */
	private static final Comparator<PagoTO> COMPARADOR_PLANIFICACION = new Comparator<PagoTO>() {
		@Override
		public int compare(PagoTO o1, PagoTO o2) {

			int comparacionPlanificada = o1.getFechaPlanificada().compareTo(o2.getFechaPlanificada());

			if (comparacionPlanificada != 0) {

				return comparacionPlanificada;
			}

			int result;

			if (o1.getFechaReal()!= null) {
				result = (o2.getFechaReal() != null) ? o1.getFechaReal().compareTo(o2.getFechaReal()) : -1;

			} else {
				result = (o2.getFechaReal() != null) ? 1 : 0;
			}

			return result;
		}
	};

	/**
	 * Compara en primer lugar por fecha real y luego por fecha de planificacion
	 */
	private static final Comparator<PagoTO> COMPARADOR_EJECUCION = new Comparator<PagoTO>() {
		@Override
		public int compare(PagoTO o1, PagoTO o2) {

			int result;

			if (o1.getFechaReal() != null) {
				result = (o2.getFechaReal() != null) ? o1.getFechaReal().compareTo(o2.getFechaReal()) : -1;

			} else {
				result = (o2.getFechaReal() != null) ? 1 : 0;
			}

			return (result != 0) ? result : o1.getFechaPlanificada().compareTo(o2.getFechaPlanificada());
		}
	};
        

	@PostConstruct
	public void init() {

		inicioMB.cargarOrganismoSeleccionado();

		filtro = new FiltroMisAdquisicionesTO();

		cargarConfiguracion();
		cargarCombosFiltro();

		limpiarFiltro();

		cargarCombosAdquisicionPagos();

		buscar(false);

		ocultarPagosConfirmados = false;
	}

	private void cargarConfiguracion() {

		Integer orgPk = inicioMB.getOrganismo().getOrgPk();

		exigeProveedorEnCompra = Boolean.valueOf(configuracionDelegate
				.obtenerCnfValorPorCodigo(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_COMPRA, orgPk));

		camposExigidosEnAdquisicion = Boolean.valueOf(configuracionDelegate
				.obtenerCnfValorPorCodigo(ConfiguracionCodigos.ID_ADQUISICION_REQUERIDO, orgPk));

		largoMaximoIdAdquisicion = Integer.valueOf(configuracionDelegate
				.obtenerCnfValorPorCodigo(ConfiguracionCodigos.LARGO_MAXIMO_ID_ADQUISICION, orgPk));

		exigeProveedorEnPago = Boolean.valueOf(configuracionDelegate
				.obtenerCnfValorPorCodigo(ConfiguracionCodigos.PROVEEDOR_ES_EXIGIDO_EN_PAGO, orgPk));

		exigeClienteEnPago = Boolean.valueOf(configuracionDelegate
				.obtenerCnfValorPorCodigo(ConfiguracionCodigos.CLIENTE_ES_EXIGIDO_EN_PAGO, orgPk));
	}

	private void cargarCombosFiltro() {

		Integer orgPk = inicioMB.getOrganismo().getOrgPk();

		// areas
		List<Areas> areas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
		areaFiltroCombo = new SofisComboG<>(areas, "areaNombre");
		areaFiltroCombo.addEmptyItem(Labels.getValue("comboTodas"));

		// gerente/adjunto
		List<SsUsuario> usuarios = new ArrayList<>(aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk));
		usuarios.addAll(ssUsuarioDelegate.obtenerInactivosPorOrganismo(orgPk));

		usuarios = SsUsuariosUtils.sortByNombreApellido(usuarios);

		gerenteAdjuntoComboFiltro = new SofisComboG<>(usuarios, "usuNombreApellido");
		gerenteAdjuntoComboFiltro.addEmptyItem(Labels.getValue("comboTodos"));

		// PMOF
		String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
		boolean[] ascUsuarios = new boolean[]{true, true, true, true};

		String[] rolCodArr = new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL};
		List<SsUsuario> pmofs = ssUsuarioDelegate.obtenerUsuariosPorRol(rolCodArr, orgPk, ordenUsuarios, ascUsuarios);
		pmofs = SsUsuariosUtils.sortByNombreApellido(pmofs);

		pmofComboFiltro = new SofisComboG<>(pmofs, "usuNombreApellido");
		pmofComboFiltro.addEmptyItem(Labels.getValue("comboTodos"));

		// estados
		listaEstados = new ArrayList<>();
		listaEstados.add(new SelectItem(Estados.ESTADOS.INICIO.estado_id, Labels.getValue("estado_Inicio")));
		listaEstados.add(new SelectItem(Estados.ESTADOS.PLANIFICACION.estado_id, Labels.getValue("estado_Planificacion")));
		listaEstados.add(new SelectItem(Estados.ESTADOS.EJECUCION.estado_id, Labels.getValue("estado_Ejecucion")));
		listaEstados.add(new SelectItem(Estados.ESTADOS.FINALIZADO.estado_id, Labels.getValue("estado_Finalizado")));

		// Proveedor
		List<OrganiIntProve> organizacionesProveedoras = organizacionDelegate.obtenerOrganiIntProvePorOrgPk(orgPk, true);
		organizacionesProveedoras = OrganiIntProveUtils.sortByNombre(organizacionesProveedoras);

		proveedorFiltroCombo = new SofisComboG<>(organizacionesProveedoras, "orgaNombre");
		proveedorFiltroCombo.addEmptyItem(Labels.getValue("comboTodos"));

		// Fuente financiamiento
		List<FuenteFinanciamiento> fuentesFinanciamiento = fuenteFinanciamientoDelegate.obtenerFuentesPorOrgId(orgPk);
		fuenteFinanciamientoFiltroCombo = new SofisComboG<>(fuentesFinanciamiento, "fueNombre");
		fuenteFinanciamientoFiltroCombo.addEmptyItem(Labels.getValue("comboTodas"));

		// Moneda
		List<Moneda> monedas = monedaDelegate.obtenerMonedas();
		monedaFiltroCombo = new SofisComboG<>(monedas, "monNombre");
		monedaFiltroCombo.addEmptyItem(Labels.getValue("comboTodas"));

		//Procedimimiento Compra
		List<ProcedimientoCompra> procedimientosCompra = procedimientoCompraDelegate.obtenerProcedimientosComprasPorOrgId(orgPk);
		procedimientoCompraFiltroCombo = new SofisComboG<>(procedimientosCompra, "procCompNombre");
		procedimientoCompraFiltroCombo.addEmptyItem(Labels.getValue("comboTodos"));
	}

	private void cargarCombosAdquisicionPagos() {

		Integer orgPk = inicioMB.getOrganismo().getOrgPk();

		procedimientosCompraHabilitados = new ArrayList<>();
		for (ProcedimientoCompra pc : procedimientoCompraDelegate.obtenerProcedimientosComprasHabilitadosPorOrgId(orgPk)) {
			procedimientosCompraHabilitados.add(new ProcedimientoCompraTO(pc.getProcCompPk(), pc.getProcCompNombre()));
		}

		fuentesFinanciamientoHabilitadas = new ArrayList<>();
		for (FuenteFinanciamiento ff : fuenteFinanciamientoDelegate.obtenerFuentesHabilitadasPorOrgId(orgPk)) {
			fuentesFinanciamientoHabilitadas.add(new FuenteFinanciamientoTO(ff.getFuePk(), ff.getFueNombre()));
		}

		proveedoresHabilitados = new ArrayList<>();
		for (OrganiIntProve p : organizacionDelegate.obtenerOrganiIntProveHabilitadosPorOrgPk(orgPk, true)) {
			proveedoresHabilitados.add(new OrganizacionTO(p.getOrgaPk(), p.getOrgaNombre()));
		}

		clientesHabilitados = new ArrayList<>();
		for (OrganiIntProve p : organizacionDelegate.obtenerOrganiIntProveHabilitadosPorOrgPk(orgPk, false)) {
			clientesHabilitados.add(new OrganizacionTO(p.getOrgaPk(), p.getOrgaNombre()));
		}
	}

	public boolean busquedaHabilitada() {

		return !StringsUtils.isEmpty(filtro.getNombreProyectoPrograma())
				|| (gerenteAdjuntoComboFiltro.getSelectedT() != null)
				|| (pmofComboFiltro.getSelectedT() != null)
				|| (areaFiltroCombo.getSelectedT() != null)
				|| (filtro.getIdAdquisicion() != null && filtro.getIdAdquisicion() > 0)
				|| (proveedorFiltroCombo.getSelectedT() != null)
				|| (fuenteFinanciamientoFiltroCombo.getSelectedT() != null)
				|| (procedimientoCompraFiltroCombo.getSelectedT() != null)
				|| (monedaFiltroCombo.getSelectedT() != null);
	}

	public String limpiarFiltro() {
		areaFiltroCombo.setSelected(-1);
		proveedorFiltroCombo.setSelected(-1);
		fuenteFinanciamientoFiltroCombo.setSelected(-1);
		monedaFiltroCombo.setSelected(-1);
		procedimientoCompraFiltroCombo.setSelected(-1);

		gerenteAdjuntoComboFiltro.setSelectedT(inicioMB.getUsuario());

		Integer orgPk = inicioMB.getOrganismo().getOrgPk();
		SsUsuario usuarioLogeado = inicioMB.getUsuario();
		String rolUsuario = usuarioLogeado.getRolUsuario(orgPk).getRolCod();

		if (rolUsuario.equals(SsRolCodigos.PMO_FEDERADA) || rolUsuario.equals(SsRolCodigos.PMO_TRANSVERSAL)) {

			gerenteAdjuntoComboFiltro.setSelected(-1);
			pmofComboFiltro.setSelectedT(inicioMB.getUsuario());
		} else {
			gerenteAdjuntoComboFiltro.setSelectedT(inicioMB.getUsuario());
			pmofComboFiltro.setSelected(-1);
		}

		filtro = new FiltroMisAdquisicionesTO();

		List<Integer> estados = filtro.getEstados();
		estados.add(Estados.ESTADOS.INICIO.estado_id);
		estados.add(Estados.ESTADOS.PLANIFICACION.estado_id);
		estados.add(Estados.ESTADOS.EJECUCION.estado_id);

		misAdquisiciones = null;

		return null;
	}

	public String buscar() {

		return buscar(false);
	}

	private String buscar(boolean mantenerEstadoElementos) {

		if (!busquedaHabilitada()) {

			return null;
		}

		cargarCombosSeleccionados();

		misAdquisiciones = misAdquisicionesDelegate.buscar(filtro);

		adquisicionEnEdicion = null;
		adquisicionEnVisualizacion = null;
		pagoEnEdicion = null;
		pagoEnVisualizacion = null;

		if (mantenerEstadoElementos) {
			paginator.changeModel(misAdquisiciones.getProyectos());

		} else {

			proyectosExpandidos = new HashMap<>();
			adquisicionesExpandidas = new HashMap<>();

			for (ProyectoTO proyecto : misAdquisiciones.getProyectos()) {
				proyectosExpandidos.put(proyecto.getId(), false);

				Map<Integer, Boolean> adquisicionesExpandidasProyecto = new HashMap<>();

				for (AdquisicionTO adquisicion : proyecto.getAdquisiciones()) {
					adquisicionesExpandidasProyecto.put(adquisicion.getId(), true);
				}

				adquisicionesExpandidas.put(proyecto.getId(), adquisicionesExpandidasProyecto);
			}

			paginator = new RepeatPaginator(misAdquisiciones.getProyectos());
		}

		return null;
	}

	private void cargarCombosSeleccionados() {

		filtro.setArea(areaFiltroCombo.getSelectedT());
		filtro.setGerenteAdjunto(gerenteAdjuntoComboFiltro.getSelectedT());
		filtro.setPmof(pmofComboFiltro.getSelectedT());
		filtro.setProveedor(proveedorFiltroCombo.getSelectedT());
		filtro.setFuenteFinanciamiento(fuenteFinanciamientoFiltroCombo.getSelectedT());
		filtro.setMoneda(monedaFiltroCombo.getSelectedT());
		filtro.setProcedimientoCompra(procedimientoCompraFiltroCombo.getSelectedT());
		filtro.setOrganismo(inicioMB.getOrganismo());
	}

	public void ocultarPagosConfirmadosCheckboxListener(ValueChangeEvent event) {

		Ajax.updateAll();
	}

	public List<PagoTO> obtenerPagosVisibles(AdquisicionTO adquisicion, EstadoTO proyEstado) {

		List<PagoTO> pagos = new ArrayList<>();
		for (PagoTO pago : adquisicion.getPagos()) {
			if (!pago.getConfirmado() || !ocultarPagosConfirmados) {
				pagos.add(pago);
			}
		}
                if (proyEstado.getId() == Estados.ESTADOS.INICIO.estado_id
						|| proyEstado.getId() == Estados.ESTADOS.PLANIFICACION.estado_id) {
                        Collections.sort(pagos, COMPARADOR_PLANIFICACION);

                } else {
                        Collections.sort(pagos, COMPARADOR_EJECUCION);
                }

		return pagos;
	}

	public Integer cantidadPagosVisibles(AdquisicionTO adquisicion) {

		if (!ocultarPagosConfirmados) {
			return adquisicion.getPagos().size();
		}

		Integer result = 0;

		for (PagoTO pago : adquisicion.getPagos()) {

			if (!pago.getConfirmado()) {

				result++;
			}
		}

		return result;
	}

	public void mostrarFiltroAction() {

		mostrarFiltro = !mostrarFiltro;
	}

	public void expandirProyecto(ProyectoTO proyecto) {

		proyectosExpandidos.put(proyecto.getId(), true);
	}

	public void colapsarProyecto(ProyectoTO proyecto) {

		proyectosExpandidos.put(proyecto.getId(), false);
	}

	public void expandirAdquisicion(AdquisicionTO adquisicion, ProyectoTO proyecto) {

		adquisicionesExpandidas.get(proyecto.getId()).put(adquisicion.getId(), true);
	}

	public void colapsarAdquisicion(AdquisicionTO adquisicion, ProyectoTO proyecto) {

		adquisicionesExpandidas.get(proyecto.getId()).put(adquisicion.getId(), false);
	}

	public void expandirColapsarProyectos() {

		boolean expandir = !proyectosExpandidos.values().contains(true);

		for (Integer idProyecto : proyectosExpandidos.keySet()) {
			proyectosExpandidos.put(idProyecto, expandir);
		}
	}

	public void expandirColapsarAdquisiciones(ProyectoTO proyecto) {

		Map<Integer, Boolean> adquisicionesExpandidasProyecto = adquisicionesExpandidas.get(proyecto.getId());

		boolean expandir = !adquisicionesExpandidasProyecto.values().contains(true);

		for (Integer idAdquisicion : adquisicionesExpandidasProyecto.keySet()) {
			adquisicionesExpandidasProyecto.put(idAdquisicion, expandir);
		}
	}

	public void verAdquisicion(AdquisicionTO adquisicion) {

		adquisicionEnVisualizacion = misAdquisicionesDelegate.obtenerAdquisicion(adquisicion.getId());
	}

	public void cerrarPopupVerAdquisicion() {
		adquisicionEnVisualizacion = null;
	}

	public void editarAdquisicion(AdquisicionTO adquisicion) {

		try {
			adquisicionEnEdicion = (AdquisicionTO) adquisicion.clone();
		} catch (CloneNotSupportedException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}

		cargarComboProveedorAdquisicion(adquisicion);

		ProcedimientoCompraTO procedimientoCompra = cargarComboProcedimientoCompraAdquisicion(adquisicion);

		FuenteFinanciamientoTO fuenteFinanciamiento = cargarComboFuenteFinanciamientoAdquisicion(adquisicion);

		cargarComboCausalCompraAdquisicion(adquisicion, fuenteFinanciamiento, procedimientoCompra);
	}

	public void guardarAdquisicion(AdquisicionTO adquisicion, ProyectoTO proyecto) {

		try {
			adquisicionEnEdicion.setProveedor(proveedorAdquisicionCombo.getSelectedT());
			adquisicionEnEdicion.setProcedimientoCompra(procedimientoCompraAdquisicionCombo.getSelectedT());
			adquisicionEnEdicion.setFuenteFinanciamiento(fuenteFinanciamientoAdquisicionCombo.getSelectedT());
			adquisicionEnEdicion.setCausalCompra(causalCompraAdquisicionCombo.getSelectedT());

			validar(adquisicionEnEdicion);

			misAdquisicionesDelegate.actualizarAdquisicion(adquisicionEnEdicion);

			Collections.replaceAll(proyecto.getAdquisiciones(), adquisicion, adquisicionEnEdicion);

			adquisicionEnEdicion = null;

		} catch (BusinessException | TechnicalException e) {

			for (String iterStr : e.getErrores()) {
				JSFUtils.agregarMsgError(GUARDAR_ADQUISICION_MSG, Labels.getValue(iterStr), null);
			}

		} catch (Exception e) {

			JSFUtils.agregarMsgError(GUARDAR_ADQUISICION_MSG, "Error al guardar Adquisicion", null);

			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void cancelarEdicionAdquisicion(AdquisicionTO adquisicion) {

		adquisicionEnEdicion = null;
	}

	public String obtenerColorPago(PagoTO pago) {

		return PagoUtils.obtenerColorFechaReal(pago);
	}

	public void verPago(PagoTO pago) {

		pagoEnVisualizacion = misAdquisicionesDelegate.obtenerPago(pago.getId());
	}

	public void cerrarPopupVerPago() {
		pagoEnVisualizacion = null;
	}

	public void editarPago(PagoTO pago) {

		try {
			pagoEnEdicion = (PagoTO) pago.clone();
		} catch (CloneNotSupportedException ex) {
			LOGGER.log(Level.SEVERE, null, ex);
		}

		cargarComboProveedorPago(pago);
		cargarComboClientePago(pago);
	}

	public void guardarPago(PagoTO pago, AdquisicionTO adquisicion, ProyectoTO proyecto) {

		try {
			pagoEnEdicion.setProveedor(proveedorPagoCombo.getSelectedT());
			pagoEnEdicion.setCliente(clientePagoCombo.getSelectedT());

			boolean proyectoEnEjecucion = proyecto.getEstado().getId().equals(Estados.ESTADOS.EJECUCION.estado_id)
					|| proyecto.getEstado().getId().equals(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id)
					|| proyecto.getEstado().getId().equals(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);

			validar(pagoEnEdicion, proyectoEnEjecucion, exigeProveedorEnPago, exigeClienteEnPago);

			misAdquisicionesDelegate.actualizarPago(pagoEnEdicion);

			buscar(true);

			pagoEnEdicion = null;

		} catch (BusinessException | TechnicalException e) {

			for (String codigoError : e.getErrores()) {

				JSFUtils.agregarMsgError(GUARDAR_PAGO_MSG, Labels.getValue(codigoError), null);
			}

		} catch (Exception e) {

			JSFUtils.agregarMsgError(GUARDAR_PAGO_MSG, "Error al guardar Pago", null);

			LOGGER.log(Level.SEVERE, null, e);
		}
	}

	public void cancelarEdicionPago(PagoTO pago) {

		pagoEnEdicion = null;
	}

	public void cambiarEstadoPago(PagoTO pago) {

		pagoAConfirmar = pago;
	}

	public void cancelarCambioEstadoPago() {

		pagoAConfirmar = null;
		mostrarMensajesConfirmacion = false;
	}

	public String cambiarEstadoPago() {

		try {

			boolean estado = misAdquisicionesDelegate.cambiarEstadoPago(pagoAConfirmar);

			pagoAConfirmar.setConfirmado(estado);
			pagoAConfirmar = null;

			buscar(true);

		} catch (BusinessException | TechnicalException e) {

			mostrarMensajesConfirmacion = true;

			for (String codigoError : e.getErrores()) {

				JSFUtils.agregarMsgError(CONFIRMAR_PAGO_MSG, Labels.getValue(codigoError), null);
			}

		} catch (Exception e) {

			mostrarMensajesConfirmacion = true;

			JSFUtils.agregarMsgError(CONFIRMAR_PAGO_MSG, "Error al cambiar el estado del Pago", null);

			LOGGER.log(Level.SEVERE, null, e);
		}

		return null;
	}

	public void irAEditarProyecto(Integer id) {

		inicioMB.irEditarProgramaProyecto(2, id, true);
	}

	private OrganizacionTO cargarComboProveedorAdquisicion(AdquisicionTO adquisicion) {

		List<OrganizacionTO> proveedores = new ArrayList<>(proveedoresHabilitados);

		OrganizacionTO proveedor = adquisicion.getProveedor();

		if (proveedor != null) {

			if (!proveedores.contains(proveedor)) {

				proveedores.add(proveedor);
			}
		}

		OrganizacionTOUtils.sortByNombre(proveedores);

		proveedorAdquisicionCombo = new SofisComboG<>(proveedores, "nombre");
		proveedorAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		if (proveedor != null) {
			proveedorAdquisicionCombo.setSelectedT(proveedor);
		} else {
			proveedorAdquisicionCombo.setSelected(-1);
		}

		return proveedor;
	}

	private ProcedimientoCompraTO cargarComboProcedimientoCompraAdquisicion(AdquisicionTO adquisicion) {

		List<ProcedimientoCompraTO> procedimientosCompra = new ArrayList<>(procedimientosCompraHabilitados);
		ProcedimientoCompraTO procedimientoCompra = adquisicion.getProcedimientoCompra();

		if (procedimientoCompra != null && !procedimientosCompra.contains(procedimientoCompra)) {

			procedimientosCompra.add(procedimientoCompra);
		}

		ProcedimientoCompraTOUtils.sortByNombre(procedimientosCompra);
		procedimientoCompraAdquisicionCombo = new SofisComboG<>(procedimientosCompra, "nombre");
		procedimientoCompraAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		if (procedimientoCompra != null) {
			procedimientoCompraAdquisicionCombo.setSelectedT(procedimientoCompra);
		} else {
			procedimientoCompraAdquisicionCombo.setSelected(-1);
		}

		return procedimientoCompra;
	}

	private FuenteFinanciamientoTO cargarComboFuenteFinanciamientoAdquisicion(AdquisicionTO adquisicion) {

		List<FuenteFinanciamientoTO> fuentesFinanciamiento = new ArrayList<>(fuentesFinanciamientoHabilitadas);
		FuenteFinanciamientoTO fuenteFinanciamiento = adquisicion.getFuenteFinanciamiento();

		if (fuenteFinanciamiento != null && !fuentesFinanciamiento.contains(fuenteFinanciamiento)) {

			fuentesFinanciamiento.add(fuenteFinanciamiento);
		}

		FuenteFinanciamientoTOUtils.sortByNombre(fuentesFinanciamiento);
		fuenteFinanciamientoAdquisicionCombo = new SofisComboG<>(fuentesFinanciamiento, "nombre");
		fuenteFinanciamientoAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		if (fuenteFinanciamiento != null) {
			fuenteFinanciamientoAdquisicionCombo.setSelectedT(fuenteFinanciamiento);
		} else {
			fuenteFinanciamientoAdquisicionCombo.setSelected(-1);
		}

		return fuenteFinanciamiento;
	}

	private void cargarComboCausalCompraAdquisicion(AdquisicionTO adquisicion, FuenteFinanciamientoTO fuenteFinanciamiento,
			ProcedimientoCompraTO procedimientoCompra) {

		causalesCompraHabilitados = new ArrayList();

		if (fuenteFinanciamiento != null && procedimientoCompra != null) {

			FuenteProcedimientoCompra fuenteProcedimientoCompra = fuenteProcedimientoCompraDelegate
					.obtenerPorFuenteProcedimientoCompra(fuenteFinanciamiento.getNombre(), procedimientoCompra.getNombre(),
							inicioMB.getOrganismo().getOrgPk());

			if (fuenteProcedimientoCompra != null) {
				causalesCompraHabilitados = new ArrayList<>();

				for (CausalCompra cc : fuenteProcedimientoCompra.getFueProComCausalesCompra()) {
					causalesCompraHabilitados.add(new CausalCompraTO(cc.getCauComPk(), cc.getCauComNombre()));
				}
			}
		}

		CausalCompraTO causalCompra = adquisicion.getCausalCompra();

		if (causalCompra != null && !causalesCompraHabilitados.contains(causalCompra)) {
			causalesCompraHabilitados.add(causalCompra);
		}

		CausalCompraTOUtils.sortByNombre(causalesCompraHabilitados);
		causalCompraAdquisicionCombo = new SofisComboG<>(causalesCompraHabilitados, "nombre");
		causalCompraAdquisicionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		if (causalCompra != null) {
			causalCompraAdquisicionCombo.setSelectedT(causalCompra);
		} else {
			causalCompraAdquisicionCombo.setSelected(-1);
		}
	}

	private OrganizacionTO cargarComboProveedorPago(PagoTO pago) {

		List<OrganizacionTO> proveedores = new ArrayList<>(proveedoresHabilitados);

		OrganizacionTO proveedor = pago.getProveedor();

		if (proveedor != null && !proveedores.contains(proveedor)) {

			proveedores.add(proveedor);
		}

		OrganizacionTOUtils.sortByNombre(proveedores);

		proveedorPagoCombo = new SofisComboG<>(proveedores, "nombre");
		proveedorPagoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		if (proveedor != null) {
			proveedorPagoCombo.setSelectedT(proveedor);
		} else {
			proveedorPagoCombo.setSelected(-1);
		}

		return proveedor;
	}

	private OrganizacionTO cargarComboClientePago(PagoTO pago) {

		List<OrganizacionTO> proveedores = new ArrayList<>(clientesHabilitados);

		OrganizacionTO cliente = pago.getCliente();

		if (cliente != null && !proveedores.contains(cliente)) {

			proveedores.add(cliente);
		}

		OrganizacionTOUtils.sortByNombre(proveedores);

		clientePagoCombo = new SofisComboG<>(proveedores, "nombre");
		clientePagoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

		if (cliente != null) {
			clientePagoCombo.setSelectedT(cliente);
		} else {
			clientePagoCombo.setSelected(-1);
		}

		return cliente;
	}

	private void validar(AdquisicionTO adquisicion) {

		boolean existeFuenteFinanciamiento = adquisicion.getFuenteFinanciamiento() != null;
		boolean existeProcedimientoCompra = adquisicion.getProcedimientoCompra() != null;
		boolean existeProveedor = adquisicion.getProveedor() != null;
		boolean existeCausalCompra = adquisicion.getCausalCompra() != null;

		Boolean existeFuenteProcedimientoCompra = existeFuenteFinanciamiento
				&& existeProcedimientoCompra
				&& !causalesCompraHabilitados.isEmpty();

		BusinessException be = new BusinessException();

		if (StringsUtils.isEmpty(adquisicion.getNombre())) {
			be.addError(MensajesNegocio.ERROR_ADQISICION_NOMBRE);
		}

		if (!existeFuenteFinanciamiento) {
			be.addError(MensajesNegocio.ERROR_ADQISICION_FUENTE);
		}

		if (existeFuenteProcedimientoCompra && adquisicion.getCausalCompra() == null) {
			be.addError(MensajesNegocio.ERROR_ADQUISICION_SIN_CAUSAL);
		}

		FichaAdquisicionValidacion.validarProveedor(exigeProveedorEnCompra, existeFuenteProcedimientoCompra,
				existeProveedor, existeFuenteProcedimientoCompra, existeCausalCompra, be);

		FichaAdquisicionValidacion.validarIdAdquisicion(be, camposExigidosEnAdquisicion, adquisicion.getIdAdquisicion(), largoMaximoIdAdquisicion);

		if (be.getErrores().size() > 0) {

			throw be;
		}
	}

	private void validar(PagoTO pago, boolean proyectoEnEjecucion, boolean exigeProveedorEnPago, boolean exigeClienteEnPago) {

		BusinessException be = new BusinessException();

		if (pago.getFechaPlanificada() == null && !proyectoEnEjecucion) {
			be.addError(MensajesNegocio.ERROR_PAGO_FECHA_PLANIFICADA);
		}
		if (pago.getImportePlanificado() == null && !proyectoEnEjecucion) {
			be.addError(MensajesNegocio.ERROR_PAGO_IMPORTE_PLANIFICADO);
		}
		if (pago.getFechaReal() == null && proyectoEnEjecucion) {
			be.addError(MensajesNegocio.ERROR_PAGO_FECHA_REAL);
		}
		if (pago.getImporteReal() == null && proyectoEnEjecucion) {
			be.addError(MensajesNegocio.ERROR_PAGO_IMPORTE_REAL);
		}
		if (exigeProveedorEnPago && pago.getConfirmado() && pago.getProveedor() == null) {
			be.addError(MensajesNegocio.ERROR_PAGO_SIN_PROVEEDOR);
		}
		if (exigeClienteEnPago && pago.getConfirmado() && pago.getCliente() == null) {
			be.addError(MensajesNegocio.ERROR_PAGO_SIN_CLIENTE);
		}

		if (be.getErrores().size() > 0) {
			throw be;
		}
	}

	public void cambioProcedimientoCompra(ValueChangeEvent ev) {

		adquisicionEnEdicion.setCausalCompra(null);

		ProcedimientoCompraTO procedimientoCompra = null;
		Integer id = (Integer) ev.getNewValue();

		for (ProcedimientoCompraTO pc : procedimientosCompraHabilitados) {

			if (pc.getId().equals(id)) {
				procedimientoCompra = pc;
				break;
			}
		}

		cargarComboCausalCompraAdquisicion(adquisicionEnEdicion,
				fuenteFinanciamientoAdquisicionCombo.getSelectedT(), procedimientoCompra);
	}

	public void cambioFuenteFinanciamiento(ValueChangeEvent ev) {

		adquisicionEnEdicion.setCausalCompra(null);

		FuenteFinanciamientoTO fuenteFinanciamiento = null;
		Integer id = (Integer) ev.getNewValue();

		for (FuenteFinanciamientoTO ff : fuentesFinanciamientoHabilitadas) {

			if (ff.getId().equals(id)) {
				fuenteFinanciamiento = ff;
				break;
			}
		}

		cargarComboCausalCompraAdquisicion(adquisicionEnEdicion,
				fuenteFinanciamiento, procedimientoCompraAdquisicionCombo.getSelectedT());
	}

	public boolean esEditableAdquisicion(AdquisicionTO adquisicion) {

		return adquisicion.getEditable()
				&& (adquisicionEnEdicion == null || adquisicionEnEdicion.getId().equals(adquisicion.getId()))
				&& pagoEnEdicion == null;
	}

	public boolean esEditablePago(PagoTO pago) {

		return pago.getEditable()
				&& (pagoEnEdicion == null || pagoEnEdicion.getId().equals(pago.getId()))
				&& adquisicionEnEdicion == null;
	}

	public boolean puedeCambiarAprobacion(PagoTO pago) {

		return pago.getPuedeCambiarAprobacion()
				&& pagoEnEdicion == null
				&& adquisicionEnEdicion == null;
	}

	public boolean valorPlanificadoEditable(PagoTO pago, ProyectoTO proyecto) {

		return pagoEnEdicion != null
				&& pagoEnEdicion.getId().equals(pago.getId())
				&& proyecto.getEstado().getId().equals(Estados.ESTADOS.PLANIFICACION.estado_id);
	}

	public boolean valorRealEditable(PagoTO pago, ProyectoTO proyecto) {

		return pagoEnEdicion != null
				&& pagoEnEdicion.getId().equals(pago.getId())
				&& proyecto.getEstado().getId().equals(Estados.ESTADOS.EJECUCION.estado_id);
	}

	public void setInicioMB(InicioMB inicioMB) {
		this.inicioMB = inicioMB;
	}

	public InicioMB getInicioMB() {
		return inicioMB;
	}

	public SsUsuarioDelegate getSsUsuarioDelegate() {
		return ssUsuarioDelegate;
	}

	public OrganiIntProveDelegate getOrganizacionDelegate() {
		return organizacionDelegate;
	}

	public FiltroMisAdquisicionesTO getFiltro() {
		return filtro;
	}

	public List<SelectItem> getListaEstados() {
		return listaEstados;
	}

	public SofisComboG<Areas> getAreaFiltroCombo() {
		return areaFiltroCombo;
	}

	public SofisComboG<SsUsuario> getGerenteAdjuntoComboFiltro() {
		return gerenteAdjuntoComboFiltro;
	}

	public SofisComboG<SsUsuario> getPmofComboFiltro() {
		return pmofComboFiltro;
	}

	public SofisComboG<OrganiIntProve> getProveedorFiltroCombo() {
		return proveedorFiltroCombo;
	}

	public SofisComboG<FuenteFinanciamiento> getFuenteFinanciamientoFiltroCombo() {
		return fuenteFinanciamientoFiltroCombo;
	}

	public SofisComboG<Moneda> getMonedaFiltroCombo() {
		return monedaFiltroCombo;
	}

	public SofisComboG<ProcedimientoCompra> getProcedimientoCompraFiltroCombo() {
		return procedimientoCompraFiltroCombo;
	}

	public MisAdquisicionesTO getMisAdquisiciones() {
		return misAdquisiciones;
	}

	public Map<Integer, Boolean> getProyectosExpandidos() {
		return proyectosExpandidos;
	}

	public Map<Integer, Map<Integer, Boolean>> getAdquisicionesExpandidas() {
		return adquisicionesExpandidas;
	}

	public PagoTO getPagoEnEdicion() {
		return pagoEnEdicion;
	}

	public AdquisicionTO getAdquisicionEnEdicion() {
		return adquisicionEnEdicion;
	}

	public void setAplicacionMB(AplicacionMB aplicacionMB) {
		this.aplicacionMB = aplicacionMB;
	}

	public AplicacionMB getAplicacionMB() {
		return aplicacionMB;
	}

	public boolean isMostrarFiltro() {
		return mostrarFiltro;
	}

	public boolean isOcultarPagosConfirmados() {
		return ocultarPagosConfirmados;
	}

	public void setOcultarPagosConfirmados(boolean ocultarPagosConfirmados) {
		this.ocultarPagosConfirmados = ocultarPagosConfirmados;
	}

	public SofisComboG<OrganizacionTO> getProveedorAdquisicionCombo() {
		return proveedorAdquisicionCombo;
	}

	public SofisComboG<FuenteFinanciamientoTO> getFuenteFinanciamientoAdquisicionCombo() {
		return fuenteFinanciamientoAdquisicionCombo;
	}

	public SofisComboG<CausalCompraTO> getCausalCompraAdquisicionCombo() {
		return causalCompraAdquisicionCombo;
	}

	public SofisComboG<ProcedimientoCompraTO> getProcedimientoCompraAdquisicionCombo() {
		return procedimientoCompraAdquisicionCombo;
	}

	public SofisComboG<OrganizacionTO> getProveedorPagoCombo() {
		return proveedorPagoCombo;
	}

	public SofisComboG<OrganizacionTO> getClientePagoCombo() {
		return clientePagoCombo;
	}

	public PagoTO getPagoAConfirmar() {
		return pagoAConfirmar;
	}

	public PagoTO getPagoEnVisualizacion() {
		return pagoEnVisualizacion;
	}

	public AdquisicionTO getAdquisicionEnVisualizacion() {
		return adquisicionEnVisualizacion;
	}

	public boolean getCamposExigidosEnAdquisicion() {

		return camposExigidosEnAdquisicion;
	}

	public boolean getMostrarMensajesConfirmacion() {
		return mostrarMensajesConfirmacion;
	}

	public RepeatPaginator getPaginator() {
		return paginator;
	}
}
