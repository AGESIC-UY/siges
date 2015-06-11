package com.sofis.web.mb;

import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.RegistroHorasUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.RegistrosHoras;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.ComboItemTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.ParticipantesDelegate;
import com.sofis.web.delegates.RegistrosHorasDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.SofisComboG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "revisionHorasMB")
@ViewScoped
public class RevisionHorasMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final String PARTICIPANTE_MSG_ID = "ficha:guardarPart";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @ManagedProperty("#{fichaMB}")
    private FichaMB fichaMB;
    @Inject
    private RegistrosHorasDelegate registrosHorasDelegate;
    @Inject
    private ParticipantesDelegate participantesDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;

    //Atributos
    private List<RegistrosHoras> revisionHorasListado;
    private Participantes participante = null;
    private int cantElementosPorPagina = 25;
    //0=Listado Part., 1=Detalle Horas, 2=Detalle Gastos.
    private int renderedHorasGastos;
    //Para el filtro
    private Integer filtroProyPk;
    private Date filtroFechaDesde;
    private Date filtroFechaHasta;
    private List<Entregables> listaEntregablesFiltro;
    private SofisCombo listaEntregablesCombo;
    private SofisCombo listaEntregablesComboSeleccionar;
    private List<ComboItemTO> listaAprobItems;
    private SofisComboG<ComboItemTO> listaAprobCombo;
    private Integer tipoRegistro;
    //Para la generacion de horas
    private Integer generarHorasEntPk;
    private Date generarHorasFechaDesde;
    private Date generarHorasFechaHasta;
    private Float generarHorasHorasDiarias;
    private boolean renderPopupGenerarHoras;
    private Boolean todoAprobado;
    //Participantes
    private List<SsUsuario> listaUsuarios;
    private SofisCombo listaUsuariosCombo;

    public RevisionHorasMB() {
        renderedHorasGastos = 0;
        renderPopupGenerarHoras = false;
        participante = new Participantes();
        listaUsuariosCombo = new SofisCombo();
        listaEntregablesCombo = new SofisCombo();
        listaEntregablesComboSeleccionar = new SofisCombo();
    }

    public List<RegistrosHoras> getRevisionHorasListado() {
        return revisionHorasListado;
    }

    public Integer getFiltroProyPk() {
        return filtroProyPk;
    }

    public void setFiltroProyPk(Integer filtroProyPk) {
        this.filtroProyPk = filtroProyPk;
    }

    public Date getFiltroFechaDesde() {
        return filtroFechaDesde;
    }

    public void setFiltroFechaDesde(Date filtroFechaDesde) {
        this.filtroFechaDesde = filtroFechaDesde;
    }

    public Date getFiltroFechaHasta() {
        return filtroFechaHasta;
    }

    public void setFiltroFechaHasta(Date filtroFechaHasta) {
        this.filtroFechaHasta = filtroFechaHasta;
    }

    public List<Entregables> getListaEntregablesFiltro() {
        return listaEntregablesFiltro;
    }

    public SofisCombo getListaEntregablesComboSeleccionar() {
        return listaEntregablesComboSeleccionar;
    }

    public void setListaEntregablesComboSeleccionar(SofisCombo listaEntregablesComboSeleccionar) {
        this.listaEntregablesComboSeleccionar = listaEntregablesComboSeleccionar;
    }

    public SofisCombo getListaEntregablesCombo() {
        return listaEntregablesCombo;
    }

    public void setListaEntregablesCombo(SofisCombo listaEntregablesCombo) {
        this.listaEntregablesCombo = listaEntregablesCombo;
    }

    public List<ComboItemTO> getListaAprobItems() {
        return listaAprobItems;
    }

    public void setListaAprobItems(List<ComboItemTO> listaAprobItems) {
        this.listaAprobItems = listaAprobItems;
    }

    public SofisComboG<ComboItemTO> getListaAprobCombo() {
        return listaAprobCombo;
    }

    public void setListaAprobCombo(SofisComboG<ComboItemTO> listaAprobCombo) {
        this.listaAprobCombo = listaAprobCombo;
    }

    public Integer getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(Integer tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public Participantes getParticipante() {
        return participante;
    }

    public void setParticipante(Participantes participante) {
        this.participante = participante;
    }

    public int getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(int cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setFichaMB(FichaMB fichaMB) {
        this.fichaMB = fichaMB;
    }

    public void setRegistrosHorasDelegate(RegistrosHorasDelegate registrosHorasDelegate) {
        this.registrosHorasDelegate = registrosHorasDelegate;
    }

    public Integer getGenerarHorasEntPk() {
        return generarHorasEntPk;
    }

    public void setGenerarHorasEntPk(Integer generarHorasEntPk) {
        this.generarHorasEntPk = generarHorasEntPk;
    }

    public Date getGenerarHorasFechaDesde() {
        return generarHorasFechaDesde;
    }

    public void setGenerarHorasFechaDesde(Date generarHorasFechaDesde) {
        this.generarHorasFechaDesde = generarHorasFechaDesde;
    }

    public Date getGenerarHorasFechaHasta() {
        return generarHorasFechaHasta;
    }

    public void setGenerarHorasFechaHasta(Date generarHorasFechaHasta) {
        this.generarHorasFechaHasta = generarHorasFechaHasta;
    }

    public Float getGenerarHorasHorasDiarias() {
        return generarHorasHorasDiarias;
    }

    public void setGenerarHorasHorasDiarias(Float generarHorasHorasDiarias) {
        this.generarHorasHorasDiarias = generarHorasHorasDiarias;
    }

    public boolean isRenderPopupGenerarHoras() {
        return renderPopupGenerarHoras;
    }

    public void setRenderPopupGenerarHoras(boolean renderPopupGenerarHoras) {
        this.renderPopupGenerarHoras = renderPopupGenerarHoras;
    }

    public Boolean getTodoAprobado() {
        return todoAprobado;
    }

    public void setTodoAprobado(Boolean todoAprobado) {
        this.todoAprobado = todoAprobado;
    }

    public List<SsUsuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<SsUsuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public SofisCombo getListaUsuariosCombo() {
        return listaUsuariosCombo;
    }

    public void setListaUsuariosCombo(SofisCombo listaUsuariosCombo) {
        this.listaUsuariosCombo = listaUsuariosCombo;
    }

    public int getRenderedHorasGastos() {
        return renderedHorasGastos;
    }

    public void setRenderedHorasGastos(int renderedHorasGastos) {
        this.renderedHorasGastos = renderedHorasGastos;
    }

    @PostConstruct
    public void init() {
        inicializarRevisionHoras();
    }

    private void inicializarRevisionHoras() {
        participante = new Participantes();
        participante.setPartProyectoFk(null);
        participante.setPartUsuarioFk(new SsUsuario(0));

        filtroProyPk = fichaMB.getFichaTO().getFichaFk();
        cargarEntregablesProyectoFiltro(filtroProyPk, null);
        filtroFechaDesde = null;
        filtroFechaHasta = null;

        listaUsuarios = ssUsuarioDelegate.obtenerTodosPorOrganismo(inicioMB.getOrganismo().getOrgPk());
        if (listaUsuarios != null) {
            listaUsuariosCombo = new SofisCombo((List) listaUsuarios, "usuNombreApellido");
            listaUsuariosCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        listaAprobItems = new ArrayList<>();
        listaAprobItems.add(new ComboItemTO(1, Labels.getValue("revisionHoras_aprobado")));
        listaAprobItems.add(new ComboItemTO(0, Labels.getValue("revisionHoras_pendiente")));
        listaAprobCombo = new SofisComboG(listaAprobItems, "itemNombre");
        listaAprobCombo.addEmptyItem(Labels.getValue("comboTodos"));
    }

    private void cargarEntregablesProyectoFiltro(Integer proyId, Participantes part) {
        listaEntregablesFiltro = new LinkedList<>();
        if (proyId != null && proyId.intValue() > 0) {
            listaEntregablesFiltro = entregablesDelegate.obtenerEntPorProyPk(proyId);
            listaEntregablesFiltro = EntregablesUtils.cargarCamposCombos(listaEntregablesFiltro);
        }

        if (part != null) {
            listaEntregablesFiltro = EntregablesUtils.obtenerHijos(listaEntregablesFiltro, part.getPartEntregablesFk(), Boolean.TRUE);
        }

        if (listaEntregablesFiltro != null) {
            listaEntregablesCombo = new SofisCombo((List) listaEntregablesFiltro, "nivelNombreCombo");
            listaEntregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        if (listaEntregablesFiltro != null) {
            listaEntregablesComboSeleccionar = new SofisCombo((List) listaEntregablesFiltro, "nivelNombreCombo");
            listaEntregablesComboSeleccionar.addEmptyItem(Labels.getValue("comboTodos"));
        }
    }

    public String buscarConFiltro() {
        Entregables entregable = (Entregables) listaEntregablesComboSeleccionar.getSelectedObject();
        ComboItemTO aprob = listaAprobCombo.getSelectedT();
        revisionHorasListado = registrosHorasDelegate.obtenerRegistrosHoras(participante.getPartUsuarioFk().getUsuId(),
                participante.getPartProyectoFk().getProyPk(),
                (entregable != null ? entregable.getEntPk() : null),
                filtroFechaDesde, filtroFechaHasta, null, null,
                (aprob != null ? (Integer) aprob.getItemObject() : null));
        revisionHorasListado = RegistroHorasUtils.sortByFecha(revisionHorasListado, false);
        return null;
    }

    public String buscarSinFiltro() {
        //Aunque sea sin filtro, siempre incluye el usuario y el proyecto
        filtroFechaDesde = null;
        filtroFechaHasta = null;
        return buscarConFiltro();
    }

    public String guardar() {
        registrosHorasDelegate.registrarHoras(revisionHorasListado, inicioMB.getOrganismo().getOrgPk());

        renderedHorasGastos = 0;
        limpiarParticipante();
        fichaMB.actualizarFichaTO(null);
        fichaMB.cargarResumenParticipantes();
        return null;
    }

    public String cancelarHoras() {
        renderedHorasGastos = 0;
        return null;
    }

    public String mostrarPopupGenerarHoras(Participantes part) {
        renderPopupGenerarHoras = true;
        cargarEntregablesProyectoFiltro(fichaMB.getFichaTO().getFichaFk(), part);
        return null;
    }

    public String cerrarPopupGenerarHoras() {
        renderPopupGenerarHoras = false;
        cargarEntregablesProyectoFiltro(fichaMB.getFichaTO().getFichaFk(), null);
        return null;
    }

    public String confirmarGenerarHoras() {
        Entregables entregable = (Entregables) listaEntregablesCombo.getSelectedObject();
        if (entregable == null || entregable.getEntPk() <= 0
                || generarHorasFechaHasta == null
                || generarHorasFechaDesde == null
                || generarHorasFechaDesde.after(generarHorasFechaHasta)
                || generarHorasHorasDiarias == null
                || generarHorasHorasDiarias.intValue() < 0 || generarHorasHorasDiarias.intValue() > 24) {
            return null;
        }

        Proyectos proyecto = participante.getPartProyectoFk();
        SsUsuario usuario = participante.getPartUsuarioFk();
        RegistrosHoras registroHoras = null;

        Calendar cal = Calendar.getInstance();
        cal.setTime(generarHorasFechaDesde);

        while (!cal.getTime().after(generarHorasFechaHasta)) {
            if (!(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                    || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                registroHoras = new RegistrosHoras();
                registroHoras.setRhAprobado(true);
                registroHoras.setRhComentario("Generado automáticamente");
                registroHoras.setRhEntregableFk(entregable);
                registroHoras.setRhFecha(cal.getTime());
                registroHoras.setRhHoras(generarHorasHorasDiarias);
                registroHoras.setRhProyectoFk(proyecto);
                registroHoras.setRhUsuarioFk(usuario);
                registrosHorasDelegate.registrarHoras(registroHoras, inicioMB.getOrganismo().getOrgPk());
            }

            cal.add(Calendar.DATE, 1);
        }
        buscarConFiltro();
        fichaMB.actualizarFichaTO(null);
        fichaMB.cargarResumenParticipantes();
        return cerrarPopupGenerarHoras();
    }

    public String cancelarGenerarHoras() {
        return cerrarPopupGenerarHoras();
    }

    public boolean getPermitirAcceso() {
        return participante != null;
    }

    public void limpiarFiltro() {
        listaEntregablesCombo.setSelected(-1);
        listaEntregablesComboSeleccionar.setSelected(-1);
        listaAprobCombo.setSelected(-1);
        filtroFechaDesde = null;
        filtroFechaHasta = null;
    }

    public String guardarParticipanteAction() {
        participante.setPartUsuarioFk((SsUsuario) listaUsuariosCombo.getSelectedObject());
        participante.setPartProyectoFk(new Proyectos(fichaMB.getFichaTO().getFichaFk()));
        participante.setPartEntregablesFk((Entregables) listaEntregablesCombo.getSelectedObject());

        try {
            participante = participantesDelegate.guardar(participante);
            if (participante != null) {
                fichaMB.actualizarFichaTO(null);
                fichaMB.cargarResumenParticipantes();
                cerrarFormCollapsable();
            }
        } catch (GeneralException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsgs(PARTICIPANTE_MSG_ID, ex.getErrores());
        }
        return null;
    }

    public String limpiarParticipante() {
        try {
            participante = new Participantes();
            participante.setPartProyectoFk(null);
            participante.setPartUsuarioFk(new SsUsuario(0));
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param part
     * @param aprobado null=todos, 0=no aprobados, 1=aprobados.
     * @return
     */
    public String cambiarUsuDetalleHs(Participantes part, Long aprobado) {
        listaEntregablesFiltro = null;
        filtroFechaDesde = null;
        filtroFechaHasta = null;

        if (part != null) {
            renderedHorasGastos = 1;
            participante = part;
            Integer hsAprob = (aprobado != null ? aprobado.intValue() : null);

            listaAprobCombo.setSelected(hsAprob);

            revisionHorasListado = registrosHorasDelegate.obtenerRegistrosHoras(participante.getPartUsuarioFk().getUsuId(),
                    participante.getPartProyectoFk().getProyPk(), null,
                    filtroFechaDesde, filtroFechaHasta, null, null, hsAprob);
            revisionHorasListado = RegistroHorasUtils.sortByFecha(revisionHorasListado, false);

        } else {
            renderedHorasGastos = 0;
            limpiarParticipante();

            fichaMB.cargarResumenParticipantes();
        }

        return null;
    }

    public String eliminarParticipanteAction(Integer partPk) {
        try {
            //Eliminarlo de la base de datos
            participantesDelegate.eliminar(partPk);
            fichaMB.actualizarFichaTO(null);
            fichaMB.cargarResumenParticipantes();
        } catch (GeneralException ge) {
            logger.log(Level.SEVERE, null, ge);
            JSFUtils.agregarMsgs(PARTICIPANTE_MSG_ID, ge.getErrores());
        }

        return null;
    }

    public String eliminarHorasAction(Integer rhPk) {
        try {
            registrosHorasDelegate.eliminarHoras(rhPk);
            for (RegistrosHoras rh : revisionHorasListado) {
                if (rh.getRhPk().equals(rhPk)) {
                    revisionHorasListado.remove(rh);
                    break;
                }
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            JSFUtils.agregarMsgs(PARTICIPANTE_MSG_ID, be.getErrores());
        }
        return null;
    }

    public String editarPart(Participantes part) {
        if (part != null) {
            participante = part;
            listaUsuariosCombo.setSelectedObject(part.getPartUsuarioFk());
            listaEntregablesCombo.setSelectedObject(part.getPartEntregablesFk());
            fichaMB.setPartFormDataExpanded(true);

            if (listaEntregablesCombo.getAllObjects().size() <= 1) {
                JSFUtils.agregarMsgWarn(PARTICIPANTE_MSG_ID, Labels.getValue("registroHoras_sin_entregables"), null);
            }
        }
        return null;
    }

    public void cerrarFormCollapsable() {
        fichaMB.cerrarFormCollapsable();
        participante = new Participantes();
        listaUsuariosCombo.setSelected(-1);
        listaEntregablesCombo.setSelected(-1);
        listaEntregablesComboSeleccionar.setSelected(-1);
    }

    public String marcarHoras() {
        if (todoAprobado == null || todoAprobado.equals(Boolean.FALSE)) {
            todoAprobado = true;
        } else {
            todoAprobado = false;
        }
        if (revisionHorasListado != null) {
            for (RegistrosHoras rh : revisionHorasListado) {
                rh.setRhAprobado(todoAprobado);
            }
        }

        return null;
    }

    public String mostrarFramePart() {
        fichaMB.miMostrar(6L);
        cargarEntregablesProyectoFiltro(filtroProyPk, null);
        return null;
    }
}
