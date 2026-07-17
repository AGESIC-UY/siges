package com.sofis.business.ejbs;

import com.sofis.business.utils.AdquisicionUtils;
import com.sofis.business.utils.PagoUtils;
import com.sofis.business.utils.SaldoUtils;
import com.sofis.data.daos.AdquisicionDAO;
import com.sofis.data.daos.PagosDAO;
import com.sofis.data.daos.ProyectosDAO;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.CausalCompra;
import com.sofis.entities.data.ComponenteProducto;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.IdentificadorGrpErp;
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
import java.util.Calendar;
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

    @Inject
    private ComponenteProductoBean componenteProductoBean;

    @Inject
    private IdentificadorGrpErpBean identificadorGrpErpBean;

    public MisAdquisicionesTO buscar(FiltroMisAdquisicionesTO filtro) {
      
        SsUsuario usuario = usuarioBean.obtenerSsUsuarioPorMail(datosUsuario.getCodigoUsuario());
        AdquisicionDAO adquisicionDAO = new AdquisicionDAO(em);
        PagosDAO pagoDAO = new PagosDAO(em);

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

        Boolean pmofModifica = Boolean.valueOf(configuracionBean.
                obtenerCnfValorPorCodigo(ConfiguracionCodigos.PMOF_GESTIONA_SUS_PROYECTOS, orgPk));

        boolean usuarioPMOF = usuario.isUsuarioPMOF(orgPk);
        //boolean pmofGrente = pmofModifica && usuarioPMOF || pmofModifica && usuarioEsPMOT;

        MisAdquisicionesTO result = new MisAdquisicionesTO();

        ProyectosDAO proyectosDAO = new ProyectosDAO(em);

        List<ProyectoTO> proyectos = proyectosDAO.obtenerProyectosConAdquisiciones(filtro);
        List<ProyectoTO> proyectosFinal = new ArrayList<>();

        int cantidadAdquisiciones = 0;
        List<PagoTO> listAux = new ArrayList<>();
        for (ProyectoTO proyecto : proyectos) {

            List<AdquisicionTO> adquisiciones = adquisicionDAO.obtenerAdquisicionesPorPresupuesto(proyecto.getIdPresupuesto(), filtro);

            List<SaldoTO> saldos = new ArrayList<>();
            boolean pmofGrente = proyecto.getPmof().getId().equals(usuario.getUsuId()) && pmofModifica;
            boolean usuarioEsGerente = proyecto.getGerente().getId().equals(usuario.getUsuId()) || pmofGrente;
            boolean usuarioEsAdjunto = proyecto.getAdjunto().getId().equals(usuario.getUsuId());
            boolean usuarioEsPMOF = proyecto.getPmof().getId().equals(usuario.getUsuId());

            ReplanificacionTO ultimaReplanificacion = replanificacionBean.obtenerUltimaReplanificacionPorIdProyecto(proyecto.getId());
            boolean proyectoEnReplanificacionPresupuesto = (ultimaReplanificacion == null) || ultimaReplanificacion.getPermiteEditar();

            List<AdquisicionTO> listaPosRemove = new ArrayList<>();
            
            
            for (AdquisicionTO adquisicion : adquisiciones) {

                adquisicion.setEditable(AdquisicionUtils.adquisicionEsEditable(proyecto.getEstado().getId(),
                        proyectoEnReplanificacionPresupuesto, usuarioEsGerente, usuarioEsAdjunto, adjuntoModificaPresupuesto));

                List<PagoTO> pagos = pagoDAO.obtenerPorAdquisicion(adquisicion.getId(), proyecto.getEstado());

                if (filtro.getAbierta() != null) {

                    if (filtro.getAbierta()) {

                        pagos = filtrarPagosPorConfirmacion(pagos);

                    } else {
                        listAux = filtrarPagosPorConfirmacion(pagos);
                        if(!listAux.isEmpty())
                            pagos = new ArrayList<>();
                   
                    }

                }

                List<PagoTO> pagosAux = new ArrayList<>();

                if (filtro.getAnio() != null) {

                    pagosAux = filtrarListaPagosPorAnio(pagos, filtro.getAnio());

                } else {
                    pagosAux.addAll(pagos);
                }

                if (pagosAux.isEmpty()) {
                    listaPosRemove.add(adquisicion);
                }

                adquisicion.setPagos(pagosAux);

                adquisicion.setImportePlanificado(PagoUtils.calcularImportePlanificado(pagosAux));
                adquisicion.setImporteReal(PagoUtils.calcularImporteReal(pagosAux));

                Double importeSaldo = PagoUtils.calcularImporteSaldo(pagosAux);
                adquisicion.setSaldo(importeSaldo);

                incrementarSaldo(saldos, adquisicion, importeSaldo);

                for (PagoTO pago : pagosAux) {

                    pago.setPuedeCambiarAprobacion(PagoUtils.pagoPuedeCambiarAprobacion(pago.getConfirmado(),
                            proyecto.getEstado().getId(), usuarioEsPMOF, usuarioEsPMOT, usuarioEsGerente, usuarioEsAdjunto,
                            usuarioApruebaPagos, adjuntoModificaPresupuesto));

                    pago.setEditable(PagoUtils.pagoEsEditable(pago.getConfirmado(),
                            proyecto.getEstado().getId(), proyectoEnReplanificacionPresupuesto,
                            usuarioEsGerente, usuarioEsAdjunto, adjuntoModificaPresupuesto));
                }
            }

            for (AdquisicionTO position : listaPosRemove) {
                adquisiciones.remove(position);
            }

            SaldoUtils.ordenarPorMoneda(saldos);
            proyecto.setSaldos(saldos);

            proyecto.setAdquisiciones(adquisiciones);

            cantidadAdquisiciones += adquisiciones.size();

        }

        for (ProyectoTO proyecto : proyectos) {

            if (!proyecto.getAdquisiciones().isEmpty()) {
                proyectosFinal.add(proyecto);
            }
        }

        result.setProyectos(proyectosFinal);
        result.setCantidadProyectos(proyectosFinal.size());

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

        ComponenteProducto componenteProducto = null;
        if (adquisicionTO.getComponenteProducto() != null) {
            componenteProducto = componenteProductoBean.obtenerComponenteProductoPorPk(adquisicionTO.getComponenteProducto().getId());

            if (componenteProducto == null) {

                be.addError(MensajesNegocio.ERROR_ADQISICION_COMP_PROD);
                throw be;
            }

            adquisicion.setAdqComponenteProducto(componenteProducto);
        }

        IdentificadorGrpErp identificadorGrpErp = null;
        if (adquisicionTO.getIdentificadorGrpErp() != null) {
            identificadorGrpErp = identificadorGrpErpBean.obtenerPorId(adquisicionTO.getIdentificadorGrpErp().getId());

            if (identificadorGrpErp == null) {

                be.addError(MensajesNegocio.ERROR_ADQISICION_COMP_PROD);
                throw be;
            }

            adquisicion.setAdqIdGrpErpFk(identificadorGrpErp);
        }

        adquisicion.setAdqNombre(adquisicionTO.getNombre());
        adquisicion.setAdqIdAdquisicion(adquisicionTO.getIdAdquisicion());

        adquisicion.setAdqProvOrga(prveedor);
        adquisicion.setAdqProcedimientoCompra(procedimientoCompra);
        adquisicion.setAdqFuente(fuenteFinanciamiento);
        adquisicion.setAdqTipoRegistro(adquisicionTO.getTipoRegistro());
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
    
    private List<PagoTO> filtrarListaPagosPorAnio(List<PagoTO> pagos, Integer anio) {
        List<PagoTO> pagosAux = new ArrayList<>();

        for (PagoTO p : pagos) {
            boolean agregar = false;

            if (p.getFechaPlanificada() != null) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(p.getFechaPlanificada());

                if (calendar.get(Calendar.YEAR) == anio) {
                    agregar = true;
                }

            }

            if (!agregar) {

                if (p.getFechaReal() != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(p.getFechaReal());

                    if (calendar.get(Calendar.YEAR) == anio) {
                        agregar = true;
                    }
                }
            }

            if (agregar) {
                pagosAux.add(p);
            }

        }

        return pagosAux;
    }
/*
    private List<PagoTO> filtrarPagosPorConfirmacion(List<PagoTO> pagos, boolean abierto) {

        List<PagoTO> listaRetorno = new ArrayList<>();

        for (PagoTO pago : pagos) {

            if (abierto) {
                if (!pago.getConfirmado()) {
                    listaRetorno.add(pago);
                }
            } else {
                if (pago.getConfirmado()) {
                    listaRetorno.add(pago);
                }
            }

        }
        return listaRetorno;

    }*/
    
    private List<PagoTO> filtrarPagosPorConfirmacion(List<PagoTO> pagos) {      
        List<PagoTO> listaRetorno = new ArrayList<>();
        for (PagoTO pago : pagos) {
            if (!pago.getConfirmado()) {
                return pagos;
            }
        }
        return listaRetorno;
    }
}
