package com.sofis.business.ejbs;

import com.sofis.business.utils.AdquisicionUtils;
import com.sofis.business.utils.PagoUtils;
import com.sofis.business.utils.PagosUtils;
import com.sofis.business.utils.SaldoUtils;
import com.sofis.data.daos.AdquisicionDAO;
import com.sofis.data.daos.PagosDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Pagos;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.FiltroMisAdquisicionesTO;
import com.sofis.entities.tipos.MisAdquisicionesTO;
import com.sofis.entities.tipos.PagoTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.entities.tipos.ReplanificacionTO;
import com.sofis.entities.tipos.SaldoTO;
import com.sofis.exceptions.BusinessException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Stateless
@LocalBean
public class MisAdquisicionesBean {

	@PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
	private EntityManager em;

	@Inject
	private DatosUsuario datosUsuario;

	@Inject
	private SsUsuarioBean usuarioBean;

	@Inject
	private AdquisicionBean adquisicionBean;

	@Inject
	private PagosBean pagoBean;

	@Inject
	private OrganiIntProveBean organizacionBean;

	@Inject
	private FuenteFinanciamientoBean fuenteFinanciamientoBean;

	@Inject
	private ProcedimientoCompraBean procedimientoCompraBean;

	@Inject
	private CausalCompraBean causalCompraBean;

	@Inject
	private ConfiguracionBean configuracionBean;

	@Inject
	private ProyReplanificacionBean replanificacionBean;

	public MisAdquisicionesTO buscar(FiltroMisAdquisicionesTO filtro) {

		SsUsuario usuario = usuarioBean.obtenerSsUsuarioPorMail(datosUsuario.getCodigoUsuario());

		if (usuario == null) {

			BusinessException be = new BusinessException();
			be.addError(MensajesNegocio.ERROR_USUARIO_OBTENER);
			
			throw be;
		}

		Integer orgPk = filtro.getOrganismo().getOrgPk();

		boolean usuarioEsPMOT = usuario.isUsuarioPMOT(orgPk);
		boolean usuarioApruebaPagos = usuario.isUsuAprobFact();

		Boolean adjuntoModificaPresupuesto = Boolean.valueOf(configuracionBean.
				obtenerCnfValorPorCodigo(ConfiguracionCodigos.ADJUNTO_MODIFICA_PRESUPUESTO, orgPk));

		MisAdquisicionesTO result = new MisAdquisicionesTO();

		ProyectosDAO proyectosDAO = new ProyectosDAO(em);

		List<ProyectoTO> proyectos = proyectosDAO.obtenerProyectosConAdquisiciones(filtro);

		result.setProyectos(proyectos);
		result.setCantidadProyectos(proyectos.size());

		AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);
		PagosDAO pagoDAO = new PagosDAO(em);

		int cantidadAdquisiciones = 0;
		for (ProyectoTO proyecto : proyectos) {

			List<AdquisicionTO> adquisiciones = adquisicionDAO.obtenerAdquisicionesPorPresupuesto(proyecto.getIdPresupuesto(), filtro);
			proyecto.setAdquisiciones(adquisiciones);

			cantidadAdquisiciones += adquisiciones.size();

			List<SaldoTO> saldos = new ArrayList<>();

			boolean usuarioEsGerente = proyecto.getGerente().getId().equals(usuario.getUsuId());
			boolean usuarioEsAdjunto = proyecto.getAdjunto().getId().equals(usuario.getUsuId());
			boolean usuarioEsPMOF = proyecto.getPmof().getId().equals(usuario.getUsuId());

			ReplanificacionTO ultimaReplanificacion = replanificacionBean.obtenerUltimaReplanificacionPorIdProyecto(proyecto.getId());
			boolean proyectoEnReplanificacionPresupuesto = (ultimaReplanificacion == null) || ultimaReplanificacion.getPermiteEditar();

			for (AdquisicionTO adquisicion : adquisiciones) {

				adquisicion.setEditable(AdquisicionUtils.adquisicionEsEditable(proyecto.getEstado().getId(),
						proyectoEnReplanificacionPresupuesto, usuarioEsGerente, usuarioEsAdjunto, adjuntoModificaPresupuesto));

				List<PagoTO> pagos = pagoDAO.obtenerPorAdquisicion(adquisicion.getId(), proyecto.getEstado());
                                
				adquisicion.setPagos(pagos);

				adquisicion.setImportePlanificado(PagoUtils.calcularImportePlanificado(pagos));
				adquisicion.setImporteReal(PagoUtils.calcularImporteReal(pagos));

				Double importeSaldo = PagoUtils.calcularImporteSaldo(pagos);
				adquisicion.setSaldo(importeSaldo);

				incrementarSaldo(saldos, adquisicion, importeSaldo);

				for (PagoTO pago : pagos) {

					pago.setPuedeCambiarAprobacion(PagoUtils.pagoPuedeCambiarAprobacion(pago.getConfirmado(),
							proyecto.getEstado().getId(), usuarioEsPMOF, usuarioEsPMOT, usuarioEsGerente, usuarioEsAdjunto,
							usuarioApruebaPagos, adjuntoModificaPresupuesto));

					pago.setEditable(PagoUtils.pagoEsEditable(pago.getConfirmado(),
							proyecto.getEstado().getId(), proyectoEnReplanificacionPresupuesto,
							usuarioEsGerente, usuarioEsAdjunto, adjuntoModificaPresupuesto));
				}
			}

			SaldoUtils.ordenarPorMoneda(saldos);
			proyecto.setSaldos(saldos);
		}

		result.setCantidadAdquisiciones(cantidadAdquisiciones);

		return result;
	}

	public void actualizarAdquisicion(AdquisicionTO adquisicionTO) {

		BusinessException be = new BusinessException();

		SsUsuario usuario = usuarioBean.obtenerSsUsuarioPorMail(datosUsuario.getCodigoUsuario());

		if (usuario == null) {

			be.addError(MensajesNegocio.ERROR_USUARIO_OBTENER);
			throw be;
		}

		Adquisicion adquisicion = adquisicionBean.obtenerAdquisicionPorId(adquisicionTO.getId());

		if (adquisicion == null) {

			be.addError(MensajesNegocio.ERROR_ADQISICION_OBTENER);
			throw be;
		}

		OrganiIntProve prveedor = null;
		if (adquisicionTO.getProveedor() != null) {
			prveedor = organizacionBean.obtenerOrganiIntProvePorId(adquisicionTO.getProveedor().getId());

			if (prveedor == null) {

				be.addError(MensajesNegocio.ERROR_ORGANIZACION_OBTENER);
				throw be;
			}
		}

		FuenteFinanciamiento fuenteFinanciamiento = null;
		if (adquisicionTO.getFuenteFinanciamiento() != null) {
			fuenteFinanciamiento = fuenteFinanciamientoBean.obtenerFuentePorPk(adquisicionTO.getFuenteFinanciamiento().getId());

			if (fuenteFinanciamiento == null) {

				be.addError(MensajesNegocio.ERROR_FUENTE_FINANC_OBTENER);
				throw be;
			}
		}

		ProcedimientoCompra procedimientoCompra = null;
		if (adquisicionTO.getProcedimientoCompra() != null) {
			procedimientoCompra = procedimientoCompraBean.obtenerProcedimientoCompraPorPk(adquisicionTO.getProcedimientoCompra().getId());

			if (procedimientoCompra == null) {

				be.addError(MensajesNegocio.ERROR_FUENTE_FINANC_OBTENER);
				throw be;
			}
		}

		CausalCompra causalCompra = null;
		if (adquisicionTO.getCausalCompra() != null) {
			causalCompra = causalCompraBean.obtenerPorId(adquisicionTO.getCausalCompra().getId());

			if (causalCompra == null) {

				be.addError(MensajesNegocio.ERROR_CAUSAL_COMPRA_OBTENER);
				throw be;
			}
		}

		adquisicion.setAdqNombre(adquisicionTO.getNombre());
		adquisicion.setAdqIdAdquisicion(adquisicionTO.getIdAdquisicion());

		adquisicion.setAdqProvOrga(prveedor);
		adquisicion.setAdqProcedimientoCompra(procedimientoCompra);
		adquisicion.setAdqFuente(fuenteFinanciamiento);
		adquisicion.setAdqCausalCompra(causalCompra);

		Proyectos proyecto = adquisicion.getAdqPreFk().getProyecto();

		adquisicionBean.guardar(adquisicion, proyecto.getProyOrgFk().getOrgPk());
	}

	public void actualizarPago(PagoTO pagoTO) {

		BusinessException be = new BusinessException();

		Pagos pago = pagoBean.obtenerPagosPorId(pagoTO.getId());

		if (pago == null) {

			be.addError(MensajesNegocio.ERROR_PAGO_OBTENER);
			throw be;
		}

		OrganiIntProve prveedor = null;
		if (pagoTO.getProveedor() != null) {
			prveedor = organizacionBean.obtenerOrganiIntProvePorId(pagoTO.getProveedor().getId());

			if (prveedor == null) {

				be.addError(MensajesNegocio.ERROR_ORGANIZACION_OBTENER);
				throw be;
			}
		}

		OrganiIntProve cliente = null;
		if (pagoTO.getCliente() != null) {
			cliente = organizacionBean.obtenerOrganiIntProvePorId(pagoTO.getCliente().getId());

			if (cliente == null) {

				be.addError(MensajesNegocio.ERROR_ORGANIZACION_OBTENER);
				throw be;
			}
		}

		Proyectos proyecto = pago.getPagAdqFk().getAdqPreFk().getProyecto();

		boolean proyectoEnEjecucion = proyecto.getProyEstFk().getEstPk().equals(Estados.ESTADOS.EJECUCION.estado_id)
				|| proyecto.getProyEstFk().getEstPk().equals(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOF.estado_id)
				|| proyecto.getProyEstFk().getEstPk().equals(Estados.ESTADOS.SOLICITUD_FINALIZADO_PMOT.estado_id);

		pago.setPagProveedorFk(prveedor);
		pago.setPagContrOrganizacionFk(cliente);

		pago.setPagTxtReferencia(pagoTO.getReferencia());

		if (proyectoEnEjecucion) {
			pago.setPagFechaReal(pagoTO.getFechaReal());
			pago.setPagImporteReal(pagoTO.getImporteReal());
		} else {
			pago.setPagFechaPlanificada(pagoTO.getFechaPlanificada());
			pago.setPagImportePlanificado(pagoTO.getImportePlanificado());
		}

		pagoBean.guardarPago(pago, proyecto.getProyPk(), null, proyecto.getProyOrgFk().getOrgPk());
	}

	private void incrementarSaldo(List<SaldoTO> saldos, AdquisicionTO adquisicion, Double importeSaldo) {

		SaldoTO saldo = null;

		for (SaldoTO s : saldos) {

			if (s.getMoneda().getId().equals(adquisicion.getMoneda().getId())) {

				saldo = s;
				break;
			}
		}

		if (saldo == null) {

			saldo = new SaldoTO(adquisicion.getMoneda());
			saldos.add(saldo);
		}

		saldo.setImporte(saldo.getImporte() + importeSaldo);
	}

	public boolean cambiarEstadoPago(PagoTO pago) {

		return pagoBean.confirmarPago(pago.getId());
	}

	public PagoTO obtenerPago(Integer id) {

		PagosDAO pagoDAO = new PagosDAO(em);

		return pagoDAO.obtenerPagoTOPorId(id);
	}

	public AdquisicionTO obtenerAdquisicion(Integer id) {

		AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);

		return adquisicionDAO.obtenerAdquisicionTOPorId(id);
	}

}
