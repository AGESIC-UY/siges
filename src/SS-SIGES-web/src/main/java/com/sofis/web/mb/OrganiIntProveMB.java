package com.sofis.web.mb;

import com.sofis.entities.data.Ambito;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AmbitoDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisComboG;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.inject.Inject;

@ManagedBean(name = "organiIntProveMB")
@ViewScoped
public class OrganiIntProveMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(OrganiIntProveMB.class.getName());

    private static final String ORGA_NOMBRE = "orgaNombre";
    private static final String ORGA_RAZON_SOCIAL = "orgaRazonSocial";
    private static final String ORGA_HABILITADO = "orgaHabilitado";
    private static final String ORGA_RUT = "orgaRut";

    private static final String POPUP_MSG = "popupMsg";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;

    @Inject
    private AmbitoDelegate ambitoDelegate;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = ORGA_NOMBRE;

    // 0=descendente, 1=ascendente.
    private int ascendente = 1;

    private SofisPopupUI renderPopupEdicion;

    private String filtroNombre;
    private String filtroRazonSocial;
    private String filtroHabilitado;
    private String filtroRut;

    private List<OrganiIntProve> listaResultado;
    private OrganiIntProve orgaEnEdicion;
    private SofisComboG<Ambito> listaIntAmbitoCombo = new SofisComboG<>();

    public OrganiIntProveMB() {
    }

    @PostConstruct
    public void init() {

        filtroNombre = "";
        listaResultado = new ArrayList<OrganiIntProve>();
        orgaEnEdicion = new OrganiIntProve();
        renderPopupEdicion = new SofisPopupUI();

        inicioMB.cargarOrganismoSeleccionado();

        List<Ambito> listaAmbito = ambitoDelegate.obtenerAmbitoPorOrg(inicioMB.getOrganismo().getOrgPk());
        if (listaAmbito != null) {
            listaIntAmbitoCombo = new SofisComboG<>(listaAmbito, "ambNombre");
            listaIntAmbitoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        buscar();
    }

    public String agregar() {
        orgaEnEdicion = new OrganiIntProve();
        orgaEnEdicion.setOrgaHabilitado(true);

        renderPopupEdicion.abrir();
        return null;
    }

    public String eliminar(Integer orgaPk) {
        if (orgaPk != null) {
            try {
                organiIntProveDelegate.eliminarOrga(orgaPk);
                aplicacionMB.cargarOrganiIntPorOrganismo(inicioMB.getOrganismo().getOrgPk());
                for (OrganiIntProve orga : listaResultado) {
                    if (orga.getOrgaPk().equals(orgaPk)) {
                        listaResultado.remove(orga);
                        break;
                    }
                }
            } catch (BusinessException e) {
                LOGGER.log(Level.SEVERE, null, e);

                for (String iterStr : e.getErrores()) {
                    JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
                }
                inicioMB.abrirPopupMensajes();
            }
        }
        return null;
    }

    public String buscar() {

        Boolean habilitado;

        if (filtroHabilitado == null || filtroHabilitado.isEmpty()) {
            habilitado = null;
        } else {
            habilitado = filtroHabilitado.equals("1");
        }

        Map<String, Object> mapFiltro = new HashMap<>();
        mapFiltro.put(ORGA_NOMBRE, filtroNombre);
        mapFiltro.put(ORGA_RAZON_SOCIAL, filtroRazonSocial);
        mapFiltro.put(ORGA_HABILITADO, habilitado);
        mapFiltro.put(ORGA_RUT, filtroRut);
        listaResultado = organiIntProveDelegate.busquedaOrgaFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);

        return null;
    }

    public String editar(Integer orgaPk) {
        try {
            orgaEnEdicion = organiIntProveDelegate.obtenerOrganiIntProvePorId(orgaPk);

            if (orgaEnEdicion != null && orgaEnEdicion.getOrgaAmbFk() != null) {
                listaIntAmbitoCombo.setSelectedT(orgaEnEdicion.getOrgaAmbFk());
            } else {
                listaIntAmbitoCombo.setSelected(-1);
            }
        } catch (BusinessException ex) {
            LOGGER.log(Level.SEVERE, null, ex);

            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        orgaEnEdicion.setOrgaOrgFk(inicioMB.getOrganismo());

        Ambito ambito = listaIntAmbitoCombo.getSelectedT();
        orgaEnEdicion.setOrgaAmbFk(ambito);

        try {
            orgaEnEdicion = organiIntProveDelegate.guardarOrga(orgaEnEdicion);
            aplicacionMB.cargarOrganiIntPorOrganismo(inicioMB.getOrganismo().getOrgPk());
            if (orgaEnEdicion != null) {
                renderPopupEdicion.cerrar();
                buscar();
            }
        } catch (BusinessException be) {
            LOGGER.log(Level.SEVERE, be.getMessage(), be);

            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
        }
        return null;
    }

    public void cancelar() {
        renderPopupEdicion.cerrar();
        orgaEnEdicion = new OrganiIntProve();
        listaIntAmbitoCombo.setSelected(-1);
    }

    public String limpiar() {
        filtroNombre = null;
        filtroRazonSocial = null;
        filtroHabilitado = null;
        filtroRut = null;
        listaResultado = null;
        elementoOrdenacion = ORGA_NOMBRE;
        ascendente = 1;

        return null;
    }

    public void cambiarCantPaginas(ValueChangeEvent evt) {
        buscar();
    }

    public void cambiarCriterioOrdenacion(ValueChangeEvent evt) {
        elementoOrdenacion = evt.getNewValue().toString();
        buscar();
    }

    public void cambiarAscendenteOrdenacion(ValueChangeEvent evt) {
        ascendente = Integer.valueOf(evt.getNewValue().toString());
        buscar();
    }

    public void organismoHabilitadoChange(OrganiIntProve organismo) {

        try {
            organismo.setOrgaHabilitado(!organismo.getOrgaHabilitado());
            organiIntProveDelegate.guardarOrga(organismo);

            buscar();
            aplicacionMB.cargarOrganiIntPorOrganismo(inicioMB.getOrganismo().getOrgPk());

            JSFUtils.agregarMsgInfo(Labels.getValue("general_form_success"));
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);

        } catch (GeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);

            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
            }
            inicioMB.setRenderPopupMensajes(Boolean.TRUE);
        }
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public String getCantElementosPorPagina() {
        return cantElementosPorPagina;
    }

    public void setCantElementosPorPagina(String cantElementosPorPagina) {
        this.cantElementosPorPagina = cantElementosPorPagina;
    }

    public String getElementoOrdenacion() {
        return elementoOrdenacion;
    }

    public void setElementoOrdenacion(String elementoOrdenacion) {
        this.elementoOrdenacion = elementoOrdenacion;
    }

    public int getAscendente() {
        return ascendente;
    }

    public void setAscendente(int ascendente) {
        this.ascendente = ascendente;
    }

    public SofisPopupUI getRenderPopupEdicion() {
        return renderPopupEdicion;
    }

    public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
        this.renderPopupEdicion = renderPopupEdicion;
    }

    public String getFiltroNombre() {
        return filtroNombre;
    }

    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    public List<OrganiIntProve> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<OrganiIntProve> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public OrganiIntProve getOrgaEnEdicion() {
        return orgaEnEdicion;
    }

    public void setOrgaEnEdicion(OrganiIntProve orgaEnEdicion) {
        this.orgaEnEdicion = orgaEnEdicion;
    }

    public SofisComboG<Ambito> getListaIntAmbitoCombo() {
        return listaIntAmbitoCombo;
    }

    public void setListaIntAmbitoCombo(SofisComboG<Ambito> listaIntAmbitoCombo) {
        this.listaIntAmbitoCombo = listaIntAmbitoCombo;
    }

    public String getFiltroRazonSocial() {
        return filtroRazonSocial;
    }

    public void setFiltroRazonSocial(String filtroRazonSocial) {
        this.filtroRazonSocial = filtroRazonSocial;
    }

    public String getFiltroHabilitado() {
        return filtroHabilitado;
    }

    public void setFiltroHabilitado(String filtroHabilitado) {
        this.filtroHabilitado = filtroHabilitado;
    }

    public String getFiltroRut() {
        return filtroRut;
    }

    public void setFiltroRut(String filtroRut) {
        this.filtroRut = filtroRut;
    }

}
