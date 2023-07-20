package com.sofis.web.mb;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.AdquisicionRango;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.ObjetivoEstrategico;
import static com.sofis.entities.data.test.TestDataBuilders.adquisicion;
import com.sofis.entities.tipos.FiltroObjectivoEstategicoTO;
import com.sofis.entities.tipos.FiltroProyectoTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.exceptions.GeneralException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.AdquisicionRangoDelegate;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.ObjetivoEstrategicoDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.RepeatPaginator;
import com.sofis.web.utils.SofisComboG;
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

@ManagedBean(name = "adquisicionRangoMB")
@ViewScoped
public class AdquisicionRangoMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(AdquisicionRangoMB.class.getName());

    private static final String MSG_ID = "fichaMsg";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private AreasDelegate areasDelegate;

    @Inject
    private AdquisicionRangoDelegate adqRangoDelegate;
    @Inject
    private AdquisicionDelegate adquisicionDelegate;

    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;

    @Inject
    private OrganiIntProveDelegate organizacionDelegate;

    @Inject
    private ProyectosDelegate proyectosDelegate;

    @Inject
    private ConfiguracionDelegate configuracionDelegate;

    @Inject
    private ObjetivoEstrategicoDelegate objetivoEstrategicoDelegate;

    @Inject
    private SofisPopupUI renderPopupEdicion;

    private FiltroProyectoTO filtro;

    private SofisComboG<Areas> areaFiltroCombo;

    private SofisComboG<Areas> areaPopupCombo;

    private SofisComboG<ObjetivoEstrategico> listaObjetivosEstrategicosCombo = new SofisComboG<>();

    private SofisComboG<ObjetivoEstrategico> listaObjetivosEstrategicosPopUpCombo = new SofisComboG<>();
    private boolean mostrarFiltro;
    private boolean mostrarMensajesConfirmacion;

    private RepeatPaginator paginator;

    private String cantElementosPorPagina = "25";

    private String labelObjEstValue;

    private AdquisicionRango adquisicionEnEdicion;

    private List<AdquisicionRango> adquisicionesRango;
    private static final String USUARIO_POPUP_MSG_ID = "usuarioPopupMsg";

    @PostConstruct
    public void init() {

        adquisicionesRango = new ArrayList();
        inicioMB.cargarOrganismoSeleccionado();

        filtro = new FiltroProyectoTO();

        cargarCombosFiltro();

        limpiarFiltro();
        List<Integer> estados = new ArrayList();
        filtro.setEstados(estados);

        Configuracion confLabelObjEst = configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.LABEL_OBJETIVO_ESTRATEGICO, inicioMB.getOrganismo().getOrgPk());

        if (confLabelObjEst == null) {
            labelObjEstValue = "Problema";
        } else {
            labelObjEstValue = confLabelObjEst.getCnfValor();
        }

        buscar();

    }

    private void cargarCombosFiltro() {

        Integer orgPk = inicioMB.getOrganismo().getOrgPk();

        // areas
        List<Areas> areas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
        areaFiltroCombo = new SofisComboG<>(areas, "areaNombre");
        areaFiltroCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        areaPopupCombo = new SofisComboG<>(areas, "areaNombre");
        areaPopupCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        FiltroObjectivoEstategicoTO filtroObjEstTO = new FiltroObjectivoEstategicoTO();
        filtroObjEstTO.setOrganismo(inicioMB.getOrganismo());
        filtroObjEstTO.setHabilitado(true);

        List<ObjetivoEstrategico> listaObjetivosEstrategicos = objetivoEstrategicoDelegate.obtenerPorFiltro(filtroObjEstTO);
        if (listaObjetivosEstrategicos != null) {
            listaObjetivosEstrategicosCombo = new SofisComboG<>(listaObjetivosEstrategicos, "objEstNombre");
            listaObjetivosEstrategicosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            listaObjetivosEstrategicosPopUpCombo = new SofisComboG<>(listaObjetivosEstrategicos, "objEstNombre");
            listaObjetivosEstrategicosPopUpCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

    }

    public void mostrarFiltroAction() {

        mostrarFiltro = !mostrarFiltro;
    }

    public String limpiarFiltro() {

        areaFiltroCombo.setSelected(-1);
        listaObjetivosEstrategicosCombo.setSelected(-1);
        adquisicionEnEdicion = null;

        return null;
    }

    public void buscar() {

        try {

            if (areaFiltroCombo.getSelected() > 0) {

                if (listaObjetivosEstrategicosCombo.getSelected() > 0) {
                    adquisicionesRango = adqRangoDelegate.listarPorOrganismoAreaDivision(inicioMB.getOrganismo().getOrgPk(), areaFiltroCombo.getSelected(), listaObjetivosEstrategicosCombo.getSelected());
                } else {
                    adquisicionesRango = adqRangoDelegate.listarPorOrganismoYArea(inicioMB.getOrganismo().getOrgPk(), areaFiltroCombo.getSelected());
                }

            } else if (listaObjetivosEstrategicosCombo.getSelected() > 0) {
                adquisicionesRango = adqRangoDelegate.listarPorOrganismoYDivision(inicioMB.getOrganismo().getOrgPk(), listaObjetivosEstrategicosCombo.getSelected());
            } else {
                //buscar solo por organismo
                adquisicionesRango = adqRangoDelegate.listarPorOrganismo(inicioMB.getOrganismo().getOrgPk());
            }

        } catch (GeneralException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
        }

    }

    public void guardar() {
        try {

            adquisicionEnEdicion.setAdrArea(areaPopupCombo.getSelectedT());
            adquisicionEnEdicion.setAdrDivision(listaObjetivosEstrategicosPopUpCombo.getSelectedT());
            adquisicionEnEdicion.setAdrChequear(Boolean.TRUE);

            boolean noExiste = true;
            if (areaPopupCombo.getSelected() > 0) {

                if (listaObjetivosEstrategicosPopUpCombo.getSelected() > 0) {

                   
                    List<AdquisicionRango> listaAdquisicones = adqRangoDelegate.listarPorOrganismoAreaDivision(inicioMB.getOrganismo().getOrgPk(), areaPopupCombo.getSelected(), listaObjetivosEstrategicosPopUpCombo.getSelected());
                    if (!listaAdquisicones.isEmpty()) {

                        if (adquisicionEnEdicion.getAdrPk() != null) {
                            for (AdquisicionRango aq : listaAdquisicones) {
                                if (aq.getAdrPk() != adquisicionEnEdicion.getAdrPk()) {
                                    noExiste = false;
                                }
                            }
                        } else {
                            noExiste = false;
                        }

                    }

                } 
                
                /*else {
                    List<AdquisicionRango> listaAdquisicones = adqRangoDelegate.listarPorOrganismoYAreaDivisionNull(inicioMB.getOrganismo().getOrgPk(), areaPopupCombo.getSelected());
                   
                    if (!listaAdquisicones.isEmpty()) {

                        if (adquisicionEnEdicion.getAdrPk() != null) {
                            for (AdquisicionRango aq : listaAdquisicones) {
                                if (aq.getAdrPk() != adquisicionEnEdicion.getAdrPk()) {
                                    noExiste = false;
                                }
                            }
                        } else {
                            noExiste = false;
                        }

                    }
                }*/

            }

          

            if (noExiste) {
                if (adqRangoDelegate.existeRango(inicioMB.getOrganismo().getOrgPk(), adquisicionEnEdicion.getAdrDesde(), adquisicionEnEdicion.getAdrHasta(),
                        adquisicionEnEdicion.getAdrPk() != null ? adquisicionEnEdicion.getAdrPk() : 0)) {
                    noExiste = false;

                }
            }

            if (noExiste) {

              
                adquisicionEnEdicion.setAdrUltimo(0);

                

                adqRangoDelegate.guardar(adquisicionEnEdicion);

                limpiarFiltro();
                buscar();

                areaPopupCombo.setSelected(-1);
                listaObjetivosEstrategicosPopUpCombo.setSelected(-1);

                renderPopupEdicion.cerrar();
            } else {
                BusinessException be = new BusinessException();
                be.addError(MensajesNegocio.ERROR_ADQISICION_RANGO_YA_EXISTE);
                JSFUtils.agregarMsgs(USUARIO_POPUP_MSG_ID, be.getErrores());
            }

        } catch (BusinessException be) {
            LOGGER.log(Level.SEVERE, be.getMessage(), be);
            JSFUtils.agregarMsgs(USUARIO_POPUP_MSG_ID, be.getErrores());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            JSFUtils.agregarMsg("error_adquisicion_rango_guardar");
        }

    }

    public void cancelar() {
        renderPopupEdicion.cerrar();
    }

    public void irAEditarProyecto(Integer id) {

        inicioMB.irEditarProgramaProyecto(2, id, true);
    }

    public void cambiarCantPaginas(ValueChangeEvent evt) {
        buscar();
    }

    public String agregar() {
        adquisicionEnEdicion = new AdquisicionRango();
        adquisicionEnEdicion.setAdrOrganismo(inicioMB.getOrganismo());
        renderPopupEdicion.abrir();
        return null;
    }

    public String editar(Integer id) {
        try {
            adquisicionEnEdicion = adqRangoDelegate.obtenerPorId(id);

            cargarCombos();

            renderPopupEdicion.abrir();
        } catch (GeneralException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public SofisComboG<Areas> getAreaFiltroCombo() {
        return areaFiltroCombo;
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public AplicacionMB getAplicacionMB() {
        return aplicacionMB;
    }

    public boolean getMostrarMensajesConfirmacion() {
        return mostrarMensajesConfirmacion;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
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

    public String getLabelObjEstValue() {
        return labelObjEstValue;
    }

    public void setLabelObjEstValue(String labelObjEstValue) {
        this.labelObjEstValue = labelObjEstValue;
    }

    public SofisComboG<ObjetivoEstrategico> getListaObjetivosEstrategicosCombo() {
        return listaObjetivosEstrategicosCombo;
    }

    public void setListaObjetivosEstrategicosCombo(SofisComboG<ObjetivoEstrategico> listaObjetivosEstrategicosCombo) {
        this.listaObjetivosEstrategicosCombo = listaObjetivosEstrategicosCombo;
    }

    public SofisPopupUI getRenderPopupEdicion() {
        return renderPopupEdicion;
    }

    public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
        this.renderPopupEdicion = renderPopupEdicion;
    }

    public AdquisicionRango getAdquisicionEnEdicion() {
        return adquisicionEnEdicion;
    }

    public void setAdquisicionEnEdicion(AdquisicionRango adquisicionEnEdicion) {
        this.adquisicionEnEdicion = adquisicionEnEdicion;
    }

    public SofisComboG<Areas> getAreaPopupCombo() {
        return areaPopupCombo;
    }

    public void setAreaPopupCombo(SofisComboG<Areas> areaPopupCombo) {
        this.areaPopupCombo = areaPopupCombo;
    }

    public SofisComboG<ObjetivoEstrategico> getListaObjetivosEstrategicosPopUpCombo() {
        return listaObjetivosEstrategicosPopUpCombo;
    }

    public void setListaObjetivosEstrategicosPopUpCombo(SofisComboG<ObjetivoEstrategico> listaObjetivosEstrategicosPopUpCombo) {
        this.listaObjetivosEstrategicosPopUpCombo = listaObjetivosEstrategicosPopUpCombo;
    }

    public boolean isMostrarFiltro() {
        return mostrarFiltro;
    }

    public void setMostrarFiltro(boolean mostrarFiltro) {
        this.mostrarFiltro = mostrarFiltro;
    }

    public List<AdquisicionRango> getAdquisicionesRango() {
        return adquisicionesRango;
    }

    public void setAdquisicionesRango(List<AdquisicionRango> adquisicionesRango) {
        this.adquisicionesRango = adquisicionesRango;
    }

    private void cargarCombos() {
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        List<Areas> areas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
        areaPopupCombo = new SofisComboG<>(areas, "areaNombre");
        areaPopupCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        areaPopupCombo.setSelected(adquisicionEnEdicion.getAdrArea().getAreaPk());

        FiltroObjectivoEstategicoTO filtroObjEstTO = new FiltroObjectivoEstategicoTO();
        filtroObjEstTO.setOrganismo(inicioMB.getOrganismo());
        filtroObjEstTO.setHabilitado(true);
        List<ObjetivoEstrategico> listaObjetivosEstrategicos = objetivoEstrategicoDelegate.obtenerPorFiltro(filtroObjEstTO);
        if (listaObjetivosEstrategicos != null) {

            listaObjetivosEstrategicosPopUpCombo = new SofisComboG<>(listaObjetivosEstrategicos, "objEstNombre");
            listaObjetivosEstrategicosPopUpCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        listaObjetivosEstrategicosPopUpCombo.setSelected(adquisicionEnEdicion.getAdrDivision() != null ? adquisicionEnEdicion.getAdrDivision().getObjEstPk() : -1);

    }

    public String eliminar(Integer aPk) {
        if (aPk != null) {
            try {
                adqRangoDelegate.eliminar(aPk);
                for (AdquisicionRango a : adquisicionesRango) {
                    if (a.getAdrPk().equals(aPk)) {
                        adquisicionesRango.remove(a);
                        break;
                    }
                }
            } catch (BusinessException e) {

            }
        }
        return null;
    }

}
