package com.sofis.web.mb;

import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.AdquisicionTO;
import com.sofis.entities.tipos.CausalCompraTO;
import com.sofis.entities.tipos.FiltroProyectoTO;
import com.sofis.entities.tipos.FuenteFinanciamientoTO;
import com.sofis.entities.tipos.OrganizacionTO;
import com.sofis.entities.tipos.ProcedimientoCompraTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.RepeatPaginator;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.utils.SofisComboG;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.icefaces.util.JavaScriptRunner;
import org.omnifaces.util.Ajax;

@ManagedBean(name = "usuariosProyectoMB")
@ViewScoped
public class UsuariosProyectoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(UsuariosProyectoMB.class.getName());

    private static final String MSG_ID = "fichaMsg";

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
    private ProyectosDelegate proyectosDelegate;

    private List<ProyectoTO> proyectos;

    private FiltroProyectoTO filtro;

    private List<SelectItem> listaEstados;

    private SofisComboG<Areas> areaFiltroCombo;
    private SofisComboG<SsUsuario> gerenteAdjuntoComboFiltro;
    private SofisComboG<SsUsuario> pmofComboFiltro;
    private SofisCombo listaGerenteCombo = new SofisCombo();
    private SofisCombo listaSponsorCombo = new SofisCombo();

    private SofisComboG<SsUsuario> gerenteAdjuntoComboAcciones;
    private SofisComboG<SsUsuario> pmofComboFiltroAcciones;
    private SofisCombo listaGerenteComboAcciones = new SofisCombo();
    private SofisCombo listaSponsorComboAcciones = new SofisCombo();

    private Map<Integer, Boolean> proyectosExpandidos;
    private Map<Integer, Map<Integer, Boolean>> adquisicionesExpandidas;

    private boolean mostrarFiltro;
    private boolean mostrarAcciones;

    private SofisComboG<OrganizacionTO> proveedorAdquisicionCombo;
    private SofisComboG<FuenteFinanciamientoTO> fuenteFinanciamientoAdquisicionCombo;
    private SofisComboG<CausalCompraTO> causalCompraAdquisicionCombo;
    private SofisComboG<ProcedimientoCompraTO> procedimientoCompraAdquisicionCombo;

    private boolean mostrarMensajesConfirmacion;

    private RepeatPaginator paginator;

    private String cantElementosPorPagina = "25";

    private boolean primeraBusqueda;
    private boolean hizoBusqueda = false;

    @PostConstruct
    public void init() {

        inicioMB.cargarOrganismoSeleccionado();

        filtro = new FiltroProyectoTO();

        cargarCombosFiltro();
        cargarComboSAcciones();

        limpiarFiltro();
        List<Integer> estados = new ArrayList();
        filtro.setEstados(estados);

        primeraBusqueda = true;
    }

    private void cargarCombosFiltro() {

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        // areas
        List<Areas> areas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
        areaFiltroCombo = new SofisComboG<>(areas, "areaNombre");
        areaFiltroCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        // gerente/adjunto
        List<SsUsuario> usuarios = new ArrayList<>(aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk));
        //  usuarios.addAll(ssUsuarioDelegate.obtenerInactivosPorOrganismo(orgPk));

        usuarios = SsUsuariosUtils.sortByNombreApellido(usuarios);

        gerenteAdjuntoComboFiltro = new SofisComboG<>(usuarios, "usuNombreApellido");
        gerenteAdjuntoComboFiltro.addEmptyItem(Labels.getValue("comboEmptyItem"));

        // PMOF
        String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        boolean[] ascUsuarios = new boolean[]{true, true, true, true};

        String[] rolCodArr = new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL};
        List<SsUsuario> pmofs = ssUsuarioDelegate.obtenerUsuariosPorRol(rolCodArr, orgPk, ordenUsuarios, ascUsuarios);
        pmofs = SsUsuariosUtils.sortByNombreApellido(pmofs);

        pmofComboFiltro = new SofisComboG<>(pmofs, "usuNombreApellido");
        pmofComboFiltro.addEmptyItem(Labels.getValue("comboEmptyItem"));

        // estados
        listaEstados = new ArrayList<>();
        listaEstados.add(new SelectItem(Estados.ESTADOS.PENDIENTE.estado_id, Labels.getValue("estado_Pendiente")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.INICIO.estado_id, Labels.getValue("estado_Inicio")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.PLANIFICACION.estado_id, Labels.getValue("estado_Planificacion")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.EJECUCION.estado_id, Labels.getValue("estado_Ejecucion")));
        listaEstados.add(new SelectItem(Estados.ESTADOS.FINALIZADO.estado_id, Labels.getValue("estado_Finalizado")));

        //la lista de los usuarios de la organizacion, son los que se pueden seleccionar como gerente
        List<SsUsuario> listaGerente = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaGerente != null && !listaGerente.isEmpty()) {
            listaGerenteCombo = new SofisCombo((List) listaGerente, "usuNombreApellido");
            listaGerenteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<SsUsuario> listaSponsor = aplicacionMB.obtenerUsuariosPorRolActivos(SsRolCodigos.DIRECTOR, orgPk);
        if (listaSponsor != null && !listaSponsor.isEmpty()) {
            listaSponsorCombo = new SofisCombo((List) listaSponsor, "usuNombreApellido");
            listaSponsorCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

    }

    private void cargarComboSAcciones() {

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        List<SsUsuario> usuarios = new ArrayList<>(aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk));
        // usuarios.addAll(ssUsuarioDelegate.obtenerInactivosPorOrganismo(orgPk));

        usuarios = SsUsuariosUtils.sortByNombreApellido(usuarios);

        gerenteAdjuntoComboAcciones = new SofisComboG<>(usuarios, "usuNombreApellido");
        gerenteAdjuntoComboAcciones.addEmptyItem(Labels.getValue("comboEmptyItem"));

        // PMOF
        String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        boolean[] ascUsuarios = new boolean[]{true, true, true, true};

        String[] rolCodArr = new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL};
        List<SsUsuario> pmofs = ssUsuarioDelegate.obtenerUsuariosPorRol(rolCodArr, orgPk, ordenUsuarios, ascUsuarios);
        pmofs = SsUsuariosUtils.sortByNombreApellido(pmofs);

        pmofComboFiltroAcciones = new SofisComboG<>(pmofs, "usuNombreApellido");
        pmofComboFiltroAcciones.addEmptyItem(Labels.getValue("comboEmptyItem"));

        //la lista de los usuarios de la organizacion, son los que se pueden seleccionar como gerente
        List<SsUsuario> listaGerente = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaGerente != null && !listaGerente.isEmpty()) {
            listaGerenteComboAcciones = new SofisCombo((List) listaGerente, "usuNombreApellido");
            listaGerenteComboAcciones.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<SsUsuario> listaSponsor = aplicacionMB.obtenerUsuariosPorRolActivos(SsRolCodigos.DIRECTOR, orgPk);
        if (listaSponsor != null && !listaSponsor.isEmpty()) {
            listaSponsorComboAcciones = new SofisCombo((List) listaSponsor, "usuNombreApellido");
            listaSponsorComboAcciones.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

    }

    public String limpiarFiltro() {

        primeraBusqueda = true;
        areaFiltroCombo.setSelected(-1);

        gerenteAdjuntoComboFiltro.setSelected(-1);
        pmofComboFiltro.setSelected(-1);
        listaGerenteCombo.setSelected(-1);
        listaSponsorCombo.setSelected(-1);

        filtro = new FiltroProyectoTO();
        if (proyectos != null) {
            proyectos.clear();
        }
        limpiarCombosGuardado();
        hizoBusqueda = false;
        return null;
    }

    public void buscar() {

        try {
            filtro.setIdOrganismo(inicioMB.getOrganismoSeleccionado());

            filtro.setIdGerente(listaGerenteCombo.getSelected());
            filtro.setIdAdjunto(gerenteAdjuntoComboFiltro.getSelected());

            filtro.setIdPmof(pmofComboFiltro.getSelected());

            filtro.setIdSponsor(listaSponsorCombo.getSelected());

            filtro.setIdArea(areaFiltroCombo.getSelected());
            filtro.setActivo(Boolean.TRUE);

            if (filtro.getEstados().contains(Integer.toString(Estados.ESTADOS.PENDIENTE.estado_id))) {
                filtro.getEstados().add(Estados.ESTADOS.PENDIENTE_PMOT.estado_id);
                filtro.getEstados().add(Estados.ESTADOS.PENDIENTE_PMOF.estado_id);
            }

            if (filtro.getIdGerente() != null && filtro.getIdGerente() != -1
                    || filtro.getIdAdjunto() != null && filtro.getIdAdjunto() != -1
                    || !StringsUtils.isEmpty(filtro.getNombre())
                    || filtro.getId() != null && filtro.getId() > 0
                    || filtro.getIdPmof() != null && filtro.getIdPmof() != -1
                    || filtro.getIdSponsor() != null && filtro.getIdSponsor() != -1
                    || filtro.getIdArea() != null && filtro.getIdArea() != -1
                    || !filtro.getEstados().isEmpty()) {
                proyectos = proyectosDelegate.buscar(filtro);
                hizoBusqueda = true;
            } else {

                proyectos.clear();
                hizoBusqueda = false;
            }

        } catch (GeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public boolean busquedaHabilitada() {

        if (primeraBusqueda) {
            primeraBusqueda = false;
            return true;
        }

        if (filtro != null) {

            if ((filtro.getIdGerente() != null && filtro.getIdGerente() != -1)
                    || (filtro.getIdAdjunto() != null && filtro.getIdAdjunto() != -1)
                    || !StringsUtils.isEmpty(filtro.getNombre())
                    || (filtro.getId() != null && filtro.getId() > 0)
                    || (filtro.getIdPmof() != null && filtro.getIdPmof() != -1)
                    || (filtro.getIdSponsor() != null && filtro.getIdSponsor() != -1)
                    || (filtro.getIdArea() != null && filtro.getIdArea() != -1)
                    || filtro.getEstados() != null && !filtro.getEstados().isEmpty()) {
                return true;
            }
            return false;
        } else {
            return false;
        }

    }

    public String preGuardar() {

        if (busquedaHabilitada()) {

            if (proyectos != null && !proyectos.isEmpty()) {
                JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "confirmationEliminar.show();");

            }
        }

        return null;
    }

    public void guardar() {

        if (!proyectos.isEmpty()) {
            String ids = ",";
            for (ProyectoTO pr : proyectos) {
                ids = ids + pr.getId() + ",";
            }

            ids = ids.substring(1, ids.length() - 1); //ello Worl

            proyectosDelegate.updateUsuariosEnProyecto(ids, this.listaGerenteComboAcciones.getSelected(), this.gerenteAdjuntoComboAcciones.getSelected(),
                    this.pmofComboFiltroAcciones.getSelected(), listaSponsorComboAcciones.getSelected());
            JSFUtils.agregarMsg(MSG_ID, "info_usuarios_reasignados", null);
            limpiarFiltro();
            buscar();
            limpiarCombosGuardado();
            hizoBusqueda = false;
        }

    }

    public boolean guardarVisible() {

        if (listaGerenteComboAcciones.getSelected() != null && listaGerenteComboAcciones.getSelected() > 0
                || gerenteAdjuntoComboAcciones.getSelected() != null && gerenteAdjuntoComboAcciones.getSelected() > 0
                || pmofComboFiltroAcciones.getSelected() != null && pmofComboFiltroAcciones.getSelected() > 0
                || listaSponsorComboAcciones.getSelected() != null && listaSponsorComboAcciones.getSelected() > 0) {
            return false;
        }
        return true;
    }

    public void ocultarPagosConfirmadosCheckboxListener(ValueChangeEvent event) {

        Ajax.updateAll();
    }

    public void mostrarFiltroAction() {

        mostrarFiltro = !mostrarFiltro;
    }

    public void mostrarAccionesAction() {

        mostrarAcciones = !mostrarAcciones;
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

    public void irAEditarProyecto(Integer id) {

        inicioMB.irEditarProgramaProyecto(2, id, true);
    }

    public void cambiarCantPaginas(ValueChangeEvent evt) {
        buscar();
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

    public Map<Integer, Boolean> getProyectosExpandidos() {
        return proyectosExpandidos;
    }

    public Map<Integer, Map<Integer, Boolean>> getAdquisicionesExpandidas() {
        return adquisicionesExpandidas;
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

    public boolean getMostrarMensajesConfirmacion() {
        return mostrarMensajesConfirmacion;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public boolean isMostrarAcciones() {
        return mostrarAcciones;
    }

    public void setMostrarAcciones(boolean mostrarAcciones) {
        this.mostrarAcciones = mostrarAcciones;
    }

    public SofisCombo getListaGerenteCombo() {
        return listaGerenteCombo;
    }

    public void setListaGerenteCombo(SofisCombo listaGerenteCombo) {
        this.listaGerenteCombo = listaGerenteCombo;
    }

    public SofisCombo getListaSponsorCombo() {
        return listaSponsorCombo;
    }

    public void setListaSponsorCombo(SofisCombo listaSponsorCombo) {
        this.listaSponsorCombo = listaSponsorCombo;
    }

    public SofisComboG<SsUsuario> getGerenteAdjuntoComboAcciones() {
        return gerenteAdjuntoComboAcciones;
    }

    public void setGerenteAdjuntoComboAcciones(SofisComboG<SsUsuario> gerenteAdjuntoComboAcciones) {
        this.gerenteAdjuntoComboAcciones = gerenteAdjuntoComboAcciones;
    }

    public SofisComboG<SsUsuario> getPmofComboFiltroAcciones() {
        return pmofComboFiltroAcciones;
    }

    public void setPmofComboFiltroAcciones(SofisComboG<SsUsuario> pmofComboFiltroAcciones) {
        this.pmofComboFiltroAcciones = pmofComboFiltroAcciones;
    }

    public SofisCombo getListaGerenteComboAcciones() {
        return listaGerenteComboAcciones;
    }

    public void setListaGerenteComboAcciones(SofisCombo listaGerenteComboAcciones) {
        this.listaGerenteComboAcciones = listaGerenteComboAcciones;
    }

    public SofisCombo getListaSponsorComboAcciones() {
        return listaSponsorComboAcciones;
    }

    public void setListaSponsorComboAcciones(SofisCombo listaSponsorComboAcciones) {
        this.listaSponsorComboAcciones = listaSponsorComboAcciones;
    }

    public List<ProyectoTO> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<ProyectoTO> proyectos) {
        this.proyectos = proyectos;
    }

    public String getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public FiltroProyectoTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroProyectoTO filtro) {
        this.filtro = filtro;
    }

    public boolean isHizoBusqueda() {
        return hizoBusqueda;
    }

    public void setHizoBusqueda(boolean hizoBusqueda) {
        this.hizoBusqueda = hizoBusqueda;
    }

    private void limpiarCombosGuardado() {

        listaGerenteComboAcciones.setSelected(-1);
        gerenteAdjuntoComboAcciones.setSelected(-1);
        pmofComboFiltroAcciones.setSelected(-1);
        listaSponsorComboAcciones.setSelected(-1);

    }

}
