package com.sofis.web.mb;

import com.sofis.business.utils.OrganiIntProveUtils;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.FuenteFinanciamiento;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.OrganiIntProve;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.FuenteFinanciamientoDelegate;
import com.sofis.web.delegates.MonedaDelegate;
import com.sofis.web.delegates.OrganiIntProveDelegate;
import com.sofis.web.delegates.OrganismoDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.delegates.TipoDocumentoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import com.sofis.web.validations.MoverProyectoValidacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "moverProyectoMB")
@ViewScoped
public class MoverProyectoMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @ManagedProperty("#{fichaMB}")
    private FichaMB fichaMB;
    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private ProyectosDelegate proyDelegate;

    @Inject
    private OrganismoDelegate orgDelegate;
    @Inject
    private ProgramasDelegate programaDelegate;
    @Inject
    private MonedaDelegate monedaDelegate;
    @Inject
    private FuenteFinanciamientoDelegate fuenteFinanciamientoDelegate;
    @Inject
    private TipoDocumentoDelegate tipoDocumentosDelegate;
    @Inject
    private AreasDelegate areasDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private OrganiIntProveDelegate organiIntProveDelegate;

    private Proyectos fichaDestinoTO;
    //Mover Proyecto
    private Boolean renderPopupMoverProy = false;
    private Boolean mapearCatalogoVisible = false;

    private SofisCombo listaAreasOrganismoCombo = new SofisCombo();
    private SofisCombo listaProgramasCombo = new SofisCombo();
    private SofisCombo listaSponsorCombo = new SofisCombo();
    private SofisCombo listaAdjuntoCombo = new SofisCombo();
    private SofisCombo listaGerenteCombo = new SofisCombo();
    private SofisCombo listaPmoFederadaCombo = new SofisCombo();
    private SofisCombo listaMonedaPreCombo = new SofisCombo();
    private SofisCombo listaFuentesPreCombo = new SofisCombo();
    private SofisCombo listaOrganizacionCombo = new SofisCombo();
    private SofisCombo listaTipoDocCombo = new SofisCombo();

    public MoverProyectoMB() {

    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public void setFichaMB(FichaMB fichaMB) {
        this.fichaMB = fichaMB;
    }

    public String verMoverProyAction() {
        init();
        renderPopupMoverProy = true;
        return null;
    }

    public void cerrarRenderPopupMoverProy() {
        renderPopupMoverProy = false;
    }

    public void init() {
        mapearCatalogoVisible = false;
        fichaDestinoTO = proyDelegate.obtenerProyPorIdEager(fichaMB.getFichaTO().getFichaFk());
        fichaDestinoTO.setProyAreaFk(new Areas());
        fichaDestinoTO.setProyUsrGerenteFk(new SsUsuario());
        fichaDestinoTO.setProyUsrAdjuntoFk(new SsUsuario());
        fichaDestinoTO.setProyUsrPmofedFk(new SsUsuario());
        fichaDestinoTO.getProyPreFk().setPreMoneda(new Moneda());
        fichaDestinoTO.getProyPreFk().setFuenteFinanciamiento(new FuenteFinanciamiento());
	/**
	 * Manuel 16-02-2017
	 * BUG: movía el proyecto y quedaba asociado a un programa.
	 */
	fichaDestinoTO.setProyProgFk(null);

        for (Adquisicion adq : fichaDestinoTO.getProyPreFk().getAdquisicionSet()) {
            if (adq.getAdqProvOrga() == null) {
                adq.setAdqProvOrga(new OrganiIntProve());
            }
            if (adq.getAdqFuente() == null) {
                adq.setAdqFuente(new FuenteFinanciamiento());
            }
        }
    }

    public String mapearCatalogos() {
        Integer orgPk = fichaDestinoTO.getProyOrgFk().getOrgPk();
        if (orgPk == inicioMB.getOrganismo().getOrgPk()) {
            JSFUtils.agregarMsgError(null, Labels.getValue("ficha_mover_org_error"), null);
            return null;
        }

        mapearCatalogoVisible = true;

        //el organismos seleccionado
        fichaDestinoTO.setProyOrgFk(orgDelegate.obtenerOrgPorId(orgPk, false));

        //carga las areas del organismo destino
//        List listaAreas = aplicacionMB.obtenerAreasPorOrganismo(orgPk);
        List<Areas> listaAreas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
        if (listaAreas != null && !listaAreas.isEmpty()) {
            listaAreasOrganismoCombo = new SofisCombo((List) listaAreas, "areaNombre");
        }

        //carga los programas del organismo destino
        List listaProgramas = programaDelegate.obtenerProgPorOrganismo(orgPk);
        if (listaProgramas == null) {
            listaProgramas = new ArrayList();
        }
        listaProgramasCombo = new SofisCombo((List) listaProgramas, "progNombre");
        listaProgramasCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        //la lista de usuarios de la organizacion son los que se puede selecionar como adjunto.
        List<SsUsuario> listaAdjunto = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaAdjunto != null && !listaAdjunto.isEmpty()) {
            listaAdjuntoCombo = new SofisCombo((List) listaAdjunto, "usuNombreApellido");
            listaAdjuntoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //la lista de los usuarios de la organizacion, son los que se pueden seleccionar como gerente
        List<SsUsuario> listaGerente = aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk);//ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk);
        if (listaGerente != null && !listaGerente.isEmpty()) {
            listaGerenteCombo = new SofisCombo((List) listaGerente, "usuNombreApellido");
            listaGerenteCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //la lista de usuarios con rol PMO Federeda
        List<SsUsuario> listaPmoFederada = aplicacionMB.obtenerUsuariosPorRolActivos(new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL}, orgPk);
        listaPmoFederada = SsUsuariosUtils.sortByNombreApellido(listaPmoFederada);
        if (listaPmoFederada != null && !listaPmoFederada.isEmpty()) {
            listaPmoFederadaCombo = new SofisCombo((List) listaPmoFederada, "usuNombreApellido");
            listaPmoFederadaCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        //TODO pasarlas al managedBean
        List listaMonedas = monedaDelegate.obtenerMonedas();
        if (listaMonedas != null && !listaMonedas.isEmpty()) {
            listaMonedaPreCombo = new SofisCombo((List) listaMonedas, "monSigno");
            listaMonedaPreCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List<FuenteFinanciamiento> listaFuentes = fuenteFinanciamientoDelegate.obtenerFuentesPorOrgId(orgPk);
        if (listaFuentes == null) {
            listaFuentes = new ArrayList<>();
        }
        listaFuentesPreCombo = new SofisCombo((List) listaFuentes, "fueNombre");
        listaFuentesPreCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        List<OrganiIntProve> listaOrganizacion = aplicacionMB.obtenerOrganiIntOrganizaciones(orgPk);
        if (listaOrganizacion != null && !listaOrganizacion.isEmpty()) {
            listaOrganizacion = OrganiIntProveUtils.sortByNombre(listaOrganizacion);
            listaOrganizacionCombo = new SofisCombo((List) listaOrganizacion, "orgaNombre");
            listaOrganizacionCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        List listaTipoDocumento = tipoDocumentosDelegate.obtenerTodos(orgPk);
        if (listaTipoDocumento != null && !listaTipoDocumento.isEmpty()) {
            listaTipoDocCombo = new SofisCombo((List) listaTipoDocumento, "tipodocNombre");
            listaTipoDocCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }

        
        return null;
    }

    public String moverProyecto() {
        loadDestinoComboSelected();

        try {
            MoverProyectoValidacion.validar(fichaDestinoTO, fichaMB.getFichaTO());
            proyDelegate.moverProyecto(fichaDestinoTO, inicioMB.getUsuario());
            this.renderPopupMoverProy = false;

            return "IR_A_INICIO";
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            JSFUtils.agregarMsgs(null, be.getErrores());
            return null;
        }
    }

    /**
     * Carga en la ficha de destino lo que se seleccionó en los combos.
     */
    private void loadDestinoComboSelected() {
        if (fichaDestinoTO.getProyAreaFk() != null && !(fichaDestinoTO.getProyAreaFk().getAreaPk() == -1 || fichaDestinoTO.getProyAreaFk().getAreaPk() == null)) {
            fichaDestinoTO.setProyAreaFk(areasDelegate.obtenerAreaPorPk(fichaDestinoTO.getProyAreaFk().getAreaPk()));
        } else {
            fichaDestinoTO.getProyAreaFk().setAreaPk(-1);
        }

        if (listaProgramasCombo.getSelectedObject() != null) {
            fichaDestinoTO.setProyProgFk((Programas) listaProgramasCombo.getSelectedObject());
        }

        if (fichaDestinoTO.getProyUsrPmofedFk() != null && !(fichaDestinoTO.getProyUsrPmofedFk().getUsuId() == -1 || fichaDestinoTO.getProyUsrPmofedFk().getUsuId() == null)) {
            fichaDestinoTO.setProyUsrPmofedFk(ssUsuarioDelegate.obtenerSsUsuarioPorId(fichaDestinoTO.getProyUsrPmofedFk().getUsuId()));
        } else {
            fichaDestinoTO.getProyUsrPmofedFk().setUsuId(-1);
        }

        if (fichaDestinoTO.getProyUsrGerenteFk() != null && !(fichaDestinoTO.getProyUsrGerenteFk().getUsuId() == null || fichaDestinoTO.getProyUsrGerenteFk().getUsuId() == -1)) {
            fichaDestinoTO.setProyUsrGerenteFk(ssUsuarioDelegate.obtenerSsUsuarioPorId(fichaDestinoTO.getProyUsrGerenteFk().getUsuId()));
        } else {
            fichaDestinoTO.getProyUsrGerenteFk().setUsuId(-1);
        }

        if (fichaDestinoTO.getProyPreFk().getFuenteFinanciamiento() != null && !(fichaDestinoTO.getProyPreFk().getFuenteFinanciamiento().getFuePk() == null || fichaDestinoTO.getProyPreFk().getFuenteFinanciamiento().getFuePk() == -1)) {
            fichaDestinoTO.getProyPreFk().setFuenteFinanciamiento(fuenteFinanciamientoDelegate.obtenerFuentePorPk(fichaDestinoTO.getProyPreFk().getFuenteFinanciamiento().getFuePk()));
        } else {
            fichaDestinoTO.getProyPreFk().getFuenteFinanciamiento().setFuePk(-1);
        }

        if (fichaDestinoTO.getProyPreFk().getPreMoneda() != null && !(fichaDestinoTO.getProyPreFk().getPreMoneda().getMonPk() == null || fichaDestinoTO.getProyPreFk().getPreMoneda().getMonPk() == -1)) {
            fichaDestinoTO.getProyPreFk().setPreMoneda(monedaDelegate.obtenerMonedaPorId(fichaDestinoTO.getProyPreFk().getPreMoneda().getMonPk()));
        } else {
            fichaDestinoTO.getProyPreFk().getPreMoneda().setMonPk(-1);
        }

        for (Adquisicion adq : fichaDestinoTO.getProyPreFk().getAdquisicionSet()) {
            if (adq.getAdqProvOrga() != null && !(adq.getAdqProvOrga().getOrgaPk() == null || adq.getAdqProvOrga().getOrgaPk() == -1)) {
                adq.setAdqProvOrga(organiIntProveDelegate.obtenerOrganiIntProvePorId(adq.getAdqProvOrga().getOrgaPk()));
            } else {
                adq.getAdqProvOrga().setOrgaPk(-1);
            }
            if (adq.getAdqFuente() != null && !(adq.getAdqFuente().getFuePk() == null || adq.getAdqFuente().getFuePk() == -1)) {
                adq.setAdqFuente(fuenteFinanciamientoDelegate.obtenerFuentePorPk(adq.getAdqFuente().getFuePk()));
            } else {
                adq.getAdqFuente().setFuePk(-1);
            }
        }
        
        for(Documentos doc : fichaDestinoTO.getDocumentosSet()){
            doc.getDocsTipo().setTipodocInstTipoDocFk(
                    tipoDocumentosDelegate.obtenerTipoDocPorId(
                            doc.getDocsTipo().getTipodocInstTipoDocFk().getTipdocPk()
                    )
            );
        }
        
    }

    public Proyectos getFichaDestinoTO() {
        return fichaDestinoTO;
    }

    public void setFichaDestinoTO(Proyectos fichaDestinoTO) {
        this.fichaDestinoTO = fichaDestinoTO;
    }

    public Boolean getRenderPopupMoverProy() {
        return renderPopupMoverProy;
    }

    public void setRenderPopupMoverProy(Boolean renderPopupMoverProy) {
        this.renderPopupMoverProy = renderPopupMoverProy;
    }

    public ProyectosDelegate getProyDelegate() {
        return proyDelegate;
    }

    public void setProyDelegate(ProyectosDelegate proyDelegate) {
        this.proyDelegate = proyDelegate;
    }

    public SofisCombo getListaAreasOrganismoCombo() {
        return listaAreasOrganismoCombo;
    }

    public void setListaAreasOrganismoCombo(SofisCombo listaAreasOrganismoCombo) {
        this.listaAreasOrganismoCombo = listaAreasOrganismoCombo;
    }

    public SofisCombo getListaProgramasCombo() {
        return listaProgramasCombo;
    }

    public void setListaProgramasCombo(SofisCombo listaProgramasCombo) {
        this.listaProgramasCombo = listaProgramasCombo;
    }

    public SofisCombo getListaSponsorCombo() {
        return listaSponsorCombo;
    }

    public void setListaSponsorCombo(SofisCombo listaSponsorCombo) {
        this.listaSponsorCombo = listaSponsorCombo;
    }

    public SofisCombo getListaAdjuntoCombo() {
        return listaAdjuntoCombo;
    }

    public void setListaAdjuntoCombo(SofisCombo listaAdjuntoCombo) {
        this.listaAdjuntoCombo = listaAdjuntoCombo;
    }

    public SofisCombo getListaGerenteCombo() {
        return listaGerenteCombo;
    }

    public void setListaGerenteCombo(SofisCombo listaGerenteCombo) {
        this.listaGerenteCombo = listaGerenteCombo;
    }

    public SofisCombo getListaPmoFederadaCombo() {
        return listaPmoFederadaCombo;
    }

    public void setListaPmoFederadaCombo(SofisCombo listaPmoFederadaCombo) {
        this.listaPmoFederadaCombo = listaPmoFederadaCombo;
    }

    public Boolean getMapearCatalogoVisible() {
        return mapearCatalogoVisible;
    }

    public void setMapearCatalogoVisible(Boolean mapearCatalogoVisible) {
        this.mapearCatalogoVisible = mapearCatalogoVisible;
    }

    public SofisCombo getListaMonedaPreCombo() {
        return listaMonedaPreCombo;
    }

    public void setListaMonedaPreCombo(SofisCombo listaMonedaPreCombo) {
        this.listaMonedaPreCombo = listaMonedaPreCombo;
    }

    public SofisCombo getListaFuentesPreCombo() {
        return listaFuentesPreCombo;
    }

    public void setListaFuentesPreCombo(SofisCombo listaFuentesPreCombo) {
        this.listaFuentesPreCombo = listaFuentesPreCombo;
    }

    public SofisCombo getListaOrganizacionCombo() {
        return listaOrganizacionCombo;
    }

    public void setListaOrganizacionCombo(SofisCombo listaOrganizacionCombo) {
        this.listaOrganizacionCombo = listaOrganizacionCombo;
    }

    public SofisCombo getListaTipoDocCombo() {
        return listaTipoDocCombo;
    }

    public void setListaTipoDocCombo(SofisCombo listaTipoDocCombo) {
        this.listaTipoDocCombo = listaTipoDocCombo;
    }

}
