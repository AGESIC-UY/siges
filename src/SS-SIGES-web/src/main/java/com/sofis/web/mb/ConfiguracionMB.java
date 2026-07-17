package com.sofis.web.mb;

import com.sofis.data.utils.DAOUtils;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Organismos;
import com.sofis.exceptions.GeneralException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.persistence.dao.reference.EntityReference;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.ConsultaHistoricoDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.EntityReferenceDataProvider;
import com.sofis.web.utils.FilaRiesgosLimite;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.LazyLoadingList;
import com.sofis.web.utils.ProcesarMensajes;
import com.sofis.web.utils.SofisCombo;
import java.io.Serializable;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "configuracionMB")
@ViewScoped
public class ConfiguracionMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConfiguracionMB.class.getName());

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ConfiguracionDelegate confDelegate;
    @Inject
    private ConsultaHistoricoDelegate histDelegate;

    private String bCodigo;
    private String bDescripcion;
    private Boolean renderResultado = false;
    private Configuracion confEnEdicion = new Configuracion();
    private Boolean renderPopupEdicion = false;
    private Boolean renderPopupHistorial = false;
    private String cantElementosPorPagina = "25";
    private List<EntityReference<Integer>> listaResultado = new ArrayList<>();
    private List<Configuracion> listaHitorial = new ArrayList<>();
    private SofisCombo comboCategoria = new SofisCombo();
    //la configuracion de riesgos
    private List<FilaRiesgosLimite> limiteGestionRiesgos;
    private Configuracion confRiesgoLimiteAmarillo = new Configuracion();
    private Configuracion confRiesgoLimiteRojo = new Configuracion();
    private Configuracion confRiesgoTiempoLimiteAmarillo = new Configuracion();
    private Configuracion confRiesgoTiempoLimiteRojo = new Configuracion();
    //Configuracion de Documentos
    private Configuracion confDocLimiteAmarillo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO);
    private Configuracion confDocLimiteRojo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO);
    private Configuracion tamanioMaxArchivoDocumento = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO);
    private Configuracion tamanioMaxArchivoManualUsuario = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MANUAL_USUARIO);
    private Configuracion tamanioMaxArchivoMultimedia = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA);

    private Configuracion confColor0_4 = new Configuracion();
    private Configuracion confColor0_8 = new Configuracion();
    private Configuracion confColor1_2 = new Configuracion();
    private Configuracion confColor1_6 = new Configuracion();
    private Configuracion confColor2_0 = new Configuracion();
    private Configuracion confColor2_4 = new Configuracion();
    private Configuracion confColor3_2 = new Configuracion();
    private Configuracion confColor3_6 = new Configuracion();
    private Configuracion confColor4_0 = new Configuracion();
    private Configuracion confColor4_8 = new Configuracion();
    private Configuracion confColor6_0 = new Configuracion();
    private Configuracion confColor6_4 = new Configuracion();
    private Configuracion confColor8_0 = new Configuracion();
    private Configuracion confColor10_0 = new Configuracion();

    private Configuracion confTituloRiesgoX = new Configuracion();
    private Configuracion confTituloRiesgoY = new Configuracion();
    private Configuracion confValoresRiesgoX = new Configuracion();
    private Configuracion confValoresRiesgoY = new Configuracion();

    private String mensajeArchivos = "El tamaño del archivo excede el máximo permitido.";

    private String color0_4;
    private String color0_8;
    private String color1_2;
    private String color1_6;
    private String color2_0;
    private String color2_4;
    private String color3_2;
    private String color3_6;
    private String color4_0;
    private String color4_8;
    private String color6_0;
    private String color6_4;
    private String color8_0;
    private String color10_0;

    /**
     * Creates a new instance of ConfiguracionMB
     */
    public ConfiguracionMB() {
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();
        if (confEnEdicion.getCnfValor() == null || confEnEdicion.getCnfValor().equals("")) {
            confEnEdicion.setCnfValor("<p></p>");
        }
        comboCategoria = new SofisCombo();
        comboCategoria.addEmptyItem(ConstantesPresentacion.DEFECTO_COMBO);
        comboCategoria.setSelected(0);

        //inicializa el limite de riesgos
        limiteGestionRiesgos = new ArrayList<>();

        Integer orgPk = inicioMB.getOrganismo() != null ? inicioMB.getOrganismo().getOrgPk() : null;

        confValoresRiesgoY = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_VALORES_EJE_Y, orgPk);
        if (confValoresRiesgoY == null) {
            confValoresRiesgoY = new Configuracion(ConfiguracionCodigos.RIESGO_VALORES_EJE_Y);
        }

        String[] split = confValoresRiesgoY.getCnfValor().split(",");
        FilaRiesgosLimite fila1 = new FilaRiesgosLimite(10, 2d, 4d, 6d, 8d, 10d);
        FilaRiesgosLimite fila2 = new FilaRiesgosLimite(8, 1.6d, 3.2d, 4.8d, 6.4d, 8d);
        FilaRiesgosLimite fila3 = new FilaRiesgosLimite(6, 1.2d, 2.4d, 3.6d, 4.8d, 6d);
        FilaRiesgosLimite fila4 = new FilaRiesgosLimite(4, 0.8d, 1.6d, 2.4d, 3.2d, 4d);
        FilaRiesgosLimite fila5 = new FilaRiesgosLimite(2, 0.4d, 0.8d, 1.2d, 1.6d, 2d);
        limiteGestionRiesgos.add(fila1);
        limiteGestionRiesgos.add(fila2);
        limiteGestionRiesgos.add(fila3);
        limiteGestionRiesgos.add(fila4);
        limiteGestionRiesgos.add(fila5);

        //carga el valor de los limites de riesgos
        confRiesgoLimiteAmarillo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO, orgPk);
        if (confRiesgoLimiteAmarillo == null) {
            confRiesgoLimiteAmarillo = new Configuracion(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_AMARILLO);
        }
        confRiesgoLimiteRojo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO, orgPk);
        if (confRiesgoLimiteRojo == null) {
            confRiesgoLimiteRojo = new Configuracion(ConfiguracionCodigos.RIESGO_INDICE_LIMITE_ROJO);
        }
        confRiesgoTiempoLimiteAmarillo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO, orgPk);
        if (confRiesgoTiempoLimiteAmarillo == null) {
            confRiesgoTiempoLimiteAmarillo = new Configuracion(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_AMARILLO);
        }
        confRiesgoTiempoLimiteRojo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO, orgPk);
        if (confRiesgoTiempoLimiteRojo == null) {
            confRiesgoTiempoLimiteRojo = new Configuracion(ConfiguracionCodigos.RIESGO_TIEMPO_LIMITE_ROJO);
        }

        //Carga los valores de porcentaje de documentos.
        confDocLimiteAmarillo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO, orgPk);
        if (confDocLimiteAmarillo == null) {
            confDocLimiteAmarillo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_AMARILLO);
        }

        confDocLimiteRojo = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO, orgPk);
        if (confDocLimiteRojo == null) {
            confDocLimiteRojo = new Configuracion(ConfiguracionCodigos.DOCUMENTO_PORCENTAJE_LIMITE_ROJO);
        }

        tamanioMaxArchivoDocumento = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO, orgPk);
        if (tamanioMaxArchivoDocumento == null) {
            tamanioMaxArchivoDocumento = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_DOCUMENTO);
        } else {
            mensajeArchivos = "El tamaño del archivo no debe ser superior a " + this.calculoMegas(new Long(tamanioMaxArchivoDocumento.getCnfValor()));
        }

        tamanioMaxArchivoManualUsuario = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MANUAL_USUARIO, null);
        if (tamanioMaxArchivoManualUsuario == null) {
            tamanioMaxArchivoManualUsuario = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MANUAL_USUARIO);
        }

        tamanioMaxArchivoMultimedia = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA, orgPk);
        if (tamanioMaxArchivoMultimedia == null) {
            tamanioMaxArchivoMultimedia = new Configuracion(ConfiguracionCodigos.TAMANIO_MAX_ARCHIVO_MULTIMEDIA);
        }

        confColor0_4 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_0_4, orgPk);
        if (confColor0_4 == null) {
            confColor0_4 = new Configuracion(ConfiguracionCodigos.COLOR_0_4);
        }

        confColor0_8 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_0_8, orgPk);
        if (confColor0_8 == null) {
            confColor0_8 = new Configuracion(ConfiguracionCodigos.COLOR_0_8);
        }

        confColor1_2 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_1_2, orgPk);
        if (confColor1_2 == null) {
            confColor1_2 = new Configuracion(ConfiguracionCodigos.COLOR_1_2);
        }
        confColor1_6 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_1_6, orgPk);
        if (confColor1_6 == null) {
            confColor1_6 = new Configuracion(ConfiguracionCodigos.COLOR_1_6);
        }

        confColor2_0 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_2_0, orgPk);
        if (confColor2_0 == null) {
            confColor2_0 = new Configuracion(ConfiguracionCodigos.COLOR_2_0);
        }
        confColor2_4 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_2_4, orgPk);
        if (confColor2_4 == null) {
            confColor2_4 = new Configuracion(ConfiguracionCodigos.COLOR_2_4);
        }

        confColor3_2 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_3_2, orgPk);
        if (confColor3_2 == null) {
            confColor3_2 = new Configuracion(ConfiguracionCodigos.COLOR_3_2);
        }
        confColor3_6 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_3_6, orgPk);
        if (confColor3_6 == null) {
            confColor3_6 = new Configuracion(ConfiguracionCodigos.COLOR_3_6);
        }

        confColor4_0 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_4_0, orgPk);
        if (confColor4_0 == null) {
            confColor4_0 = new Configuracion(ConfiguracionCodigos.COLOR_4_0);
        }
        confColor4_8 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_4_8, orgPk);
        if (confColor4_8 == null) {
            confColor4_8 = new Configuracion(ConfiguracionCodigos.COLOR_4_8);
        }

        confColor6_0 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_6_0, orgPk);
        if (confColor6_0 == null) {
            confColor6_0 = new Configuracion(ConfiguracionCodigos.COLOR_6_0);
        }
        confColor6_4 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_6_4, orgPk);
        if (confColor6_4 == null) {
            confColor6_4 = new Configuracion(ConfiguracionCodigos.COLOR_6_4);
        }

        confColor8_0 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_8_0, orgPk);
        if (confColor8_0 == null) {
            confColor8_0 = new Configuracion(ConfiguracionCodigos.COLOR_8_0);
        }
        confColor10_0 = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.COLOR_10_0, orgPk);
        if (confColor10_0 == null) {
            confColor10_0 = new Configuracion(ConfiguracionCodigos.COLOR_10_0);
        }

        confTituloRiesgoX = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TITULO_EJE_X, orgPk);
        if (confTituloRiesgoX == null) {
            confTituloRiesgoX = new Configuracion(ConfiguracionCodigos.RIESGO_TITULO_EJE_X);
        }
        confTituloRiesgoY = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_TITULO_EJE_Y, orgPk);
        if (confTituloRiesgoY == null) {
            confTituloRiesgoY = new Configuracion(ConfiguracionCodigos.RIESGO_TITULO_EJE_Y);
        }
        confValoresRiesgoX = confDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.RIESGO_VALORES_EJE_X, orgPk);
        if (confValoresRiesgoX == null) {
            confValoresRiesgoX = new Configuracion(ConfiguracionCodigos.RIESGO_VALORES_EJE_X);
        }

        color0_4 = confColor0_4.getCnfValor();
        color0_8 = confColor0_8.getCnfValor();
        color1_2 = confColor1_2.getCnfValor();
        color1_6 = confColor1_6.getCnfValor();
        color2_0 = confColor2_0.getCnfValor();
        color2_4 = confColor2_4.getCnfValor();
        color3_2 = confColor3_2.getCnfValor();
        color3_6 = confColor3_6.getCnfValor();
        color4_0 = confColor4_0.getCnfValor();
        color4_8 = confColor4_8.getCnfValor();
        color6_0 = confColor6_0.getCnfValor();
        color6_4 = confColor6_4.getCnfValor();
        color8_0 = confColor8_0.getCnfValor();
        color10_0 = confColor10_0.getCnfValor();


        /*
        *   14-06-2018 Nico: Se agrega esta operación para que se listen las configuraciones cuando se carga la pantalla.
         */
        buscar();
    }

    public String obtenerValorX(Integer posicion) {

        try {
            if (confValoresRiesgoX == null) {
                confValoresRiesgoX = new Configuracion(ConfiguracionCodigos.RIESGO_VALORES_EJE_X);
            }
            String[] split = confValoresRiesgoX.getCnfValor().split(",");
            return split[posicion - 1];
        } catch (Exception e) {
        }
        return "S/D";
    }

    private void reset() {
        bCodigo = "";
        bDescripcion = "";
        listaResultado = new ArrayList<>();
        renderResultado = false;
    }

    // <editor-fold defaultstate="collapsed" desc="eventos">
    public String buscar() {
        renderResultado = true;
        List<CriteriaTO> criterios = new ArrayList<>();

        MatchCriteriaTO criterioOrg = CriteriaTOUtils.createMatchCriteriaTO(
                MatchCriteriaTO.types.EQUALS, "cnfOrgFk.orgPk",
                inicioMB.getOrganismo().getOrgPk());
        criterios.add(criterioOrg);

        if (!StringsUtils.isEmpty(bCodigo)) {
            MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(
                    MatchCriteriaTO.types.CONTAINS, "cnfCodigo",
                    bCodigo.toUpperCase().trim());
            criterios.add(criterio);
        }

        if (!StringsUtils.isEmpty(bDescripcion)) {
//	    MatchCriteriaTO criterio = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, "cnfDescripcion", bDescripcion.toUpperCase().trim());
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
            Logger.getLogger(ConfiguracionMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String consultarHistorial(Integer id) {
        try {
            listaHitorial = histDelegate.obtenerConfiguracion(id);
            renderPopupHistorial = true;
        } catch (GeneralException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConfiguracionMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Configuracion getConfRiesgoLimiteAmarillo() {
        return confRiesgoLimiteAmarillo;
    }

    public void setConfRiesgoLimiteAmarillo(Configuracion confRiesgoLimiteAmarillo) {
        this.confRiesgoLimiteAmarillo = confRiesgoLimiteAmarillo;
    }

    public Configuracion getConfRiesgoLimiteRojo() {
        return confRiesgoLimiteRojo;
    }

    public void setConfRiesgoLimiteRojo(Configuracion confRiesgoLimiteRojo) {
        this.confRiesgoLimiteRojo = confRiesgoLimiteRojo;
    }

    public Configuracion getConfRiesgoTiempoLimiteAmarillo() {
        return confRiesgoTiempoLimiteAmarillo;
    }

    public void setConfRiesgoTiempoLimiteAmarillo(Configuracion confRiesgoTiempoLimiteAmarillo) {
        this.confRiesgoTiempoLimiteAmarillo = confRiesgoTiempoLimiteAmarillo;
    }

    public Configuracion getConfRiesgoTiempoLimiteRojo() {
        return confRiesgoTiempoLimiteRojo;
    }

    public void setConfRiesgoTiempoLimiteRojo(Configuracion confRiesgoTiempoLimiteRojo) {
        this.confRiesgoTiempoLimiteRojo = confRiesgoTiempoLimiteRojo;
    }

    public Configuracion getConfDocLimiteAmarillo() {
        return confDocLimiteAmarillo;
    }

    public void setConfDocLimiteAmarillo(Configuracion confDocLimiteAmarillo) {
        this.confDocLimiteAmarillo = confDocLimiteAmarillo;
    }

    public Configuracion getConfDocLimiteRojo() {
        return confDocLimiteRojo;
    }

    public void setConfDocLimiteRojo(Configuracion confDocLimiteRojo) {
        this.confDocLimiteRojo = confDocLimiteRojo;
    }

    public Configuracion getTamanioMaxArchivoDocumento() {
        return tamanioMaxArchivoDocumento;
    }

    public void setTamanioMaxArchivoDocumento(Configuracion tamanioMaxArchivoDocumento) {
        this.tamanioMaxArchivoDocumento = tamanioMaxArchivoDocumento;
    }

    public Configuracion getTamanioMaxArchivoManualUsuario() {
        return tamanioMaxArchivoManualUsuario;
    }

    public void setTamanioMaxArchivoManualUsuario(Configuracion tamanioMaxArchivoManualUsuario) {
        this.tamanioMaxArchivoManualUsuario = tamanioMaxArchivoManualUsuario;
    }

    public Configuracion getTamanioMaxArchivoMultimedia() {
        return tamanioMaxArchivoMultimedia;
    }

    public void setTamanioMaxArchivoMultimedia(Configuracion tamanioMaxArchivoMultimedia) {
        this.tamanioMaxArchivoMultimedia = tamanioMaxArchivoMultimedia;
    }

    public String cerrarPopupHistorial() {
        renderPopupHistorial = false;
        return null;
    }

    public String guardarRiesgoLimite() {
        try {
            Double dAmarillo = new Double(confRiesgoLimiteAmarillo.getCnfValor());
            Double dRojo = new Double(confRiesgoLimiteRojo.getCnfValor());
            if (dAmarillo >= dRojo) {
                JSFUtils.agregarMsgError("El limite Amarillo no puede ser mayor o igual al Rojo.");
                return null;
            } else if (dRojo < dAmarillo) {
                JSFUtils.agregarMsgError("El limite Rojo no puede ser menor al Amarillo.");
                return null;
            }

            String[] split = confValoresRiesgoX.getCnfValor().split(",");

            if (split.length < 5) {
                JSFUtils.agregarMsgError("Lista valores de eje X, deben ser 5 separados por coma.");
                return null;
            }

            split = confValoresRiesgoY.getCnfValor().split(",");

            if (split.length < 5) {
                JSFUtils.agregarMsgError("Lista valores de eje Y, deben ser 5 separados por coma.");
                return null;
            }

            confRiesgoLimiteAmarillo = confDelegate.guardar(confRiesgoLimiteAmarillo);
            confRiesgoLimiteRojo = confDelegate.guardar(confRiesgoLimiteRojo);
            confRiesgoTiempoLimiteAmarillo = confDelegate.guardar(confRiesgoTiempoLimiteAmarillo);
            confRiesgoTiempoLimiteRojo = confDelegate.guardar(confRiesgoTiempoLimiteRojo);

            confTituloRiesgoX = confDelegate.guardar(confTituloRiesgoX);
            confTituloRiesgoY = confDelegate.guardar(confTituloRiesgoY);
            confValoresRiesgoX = confDelegate.guardar(confValoresRiesgoX);
            confValoresRiesgoY = confDelegate.guardar(confValoresRiesgoY);
            init();
        } catch (GeneralException ex) {
            for (FacesMessage s : ProcesarMensajes.obtenerMensajes(ex.getErrores())) {
                FacesContext.getCurrentInstance().addMessage("", s);
            }
        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return null;
    }

    public String guardarColoresExposicion() {
        try {
            confColor0_4.setCnfValor(color0_4);
            confColor0_8.setCnfValor(color0_8);
            confColor1_2.setCnfValor(color1_2);
            confColor1_6.setCnfValor(color1_6);
            confColor2_0.setCnfValor(color2_0);
            confColor2_4.setCnfValor(color2_4);
            confColor3_2.setCnfValor(color3_2);
            confColor3_6.setCnfValor(color3_6);
            confColor4_0.setCnfValor(color4_0);
            confColor4_8.setCnfValor(color4_8);
            confColor6_0.setCnfValor(color6_0);
            confColor6_4.setCnfValor(color6_4);
            confColor8_0.setCnfValor(color8_0);
            confColor10_0.setCnfValor(color10_0);

            confDelegate.guardar(confColor0_4);
            confDelegate.guardar(confColor0_8);
            confDelegate.guardar(confColor1_2);

            confDelegate.guardar(confColor1_6);
            confDelegate.guardar(confColor2_0);
            confDelegate.guardar(confColor2_4);

            confDelegate.guardar(confColor3_2);
            confDelegate.guardar(confColor3_6);
            confDelegate.guardar(confColor4_0);

            confDelegate.guardar(confColor4_8);
            confDelegate.guardar(confColor6_0);
            confDelegate.guardar(confColor6_4);

            confDelegate.guardar(confColor8_0);
            confDelegate.guardar(confColor10_0);

            init();

        } catch (GeneralException ex) {
            for (FacesMessage s : ProcesarMensajes.obtenerMensajes(ex.getErrores())) {
                FacesContext.getCurrentInstance().addMessage("", s);
            }
        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return null;
    }

    public String guardarDocLimite() {
        try {
            Integer iAmarillo = Integer.valueOf(confDocLimiteAmarillo.getCnfValor());
            Integer iRojo = Integer.valueOf(confDocLimiteRojo.getCnfValor());
            if (iAmarillo >= iRojo) {
                JSFUtils.agregarMsgError("El limite Amarillo no puede ser mayor o igual al Rojo.");
                return null;
            } else if (iRojo < iAmarillo) {
                JSFUtils.agregarMsgError("El limite Rojo no puede ser menor al Amarillo.");
                return null;
            }

            confDocLimiteAmarillo = confDelegate.guardar(confDocLimiteAmarillo);
            confDocLimiteRojo = confDelegate.guardar(confDocLimiteRojo);
        } catch (GeneralException ex) {
            for (FacesMessage s : ProcesarMensajes.obtenerMensajes(ex.getErrores())) {
                FacesContext.getCurrentInstance().addMessage("", s);
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return null;
    }

    public String guardar() {
        try {
            confDelegate.guardar(confEnEdicion);
            renderPopupEdicion = false;
            buscar();
        } catch (GeneralException ex) {
            logger.log(Level.SEVERE, null, ex);
            //JSFUtils.agregarMsgs("", ex.getErrores());
            /*
            *   14-06-2018 Nico: Se agrega este for para poder mostrar en el front todos los mensajes
            *           enviados desde la lógica.
             */

            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError("confMsgs", Labels.getValue(iterStr), null);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
//            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }
        return null;
    }

    public String riesgoColor(Double valor) {
        try {

            Double limiteAmarillo = Double.parseDouble(confRiesgoLimiteAmarillo.getCnfValor());
            Double limiteRojo = Double.parseDouble(confRiesgoLimiteRojo.getCnfValor());

            if (valor <= limiteAmarillo) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            }

            if (valor > limiteAmarillo && valor < limiteRojo) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            }
            if (valor >= limiteRojo) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }

            return ConstantesEstandares.COLOR_TRANSPARENT;
        } catch (Exception w) {
            return ConstantesEstandares.COLOR_TRANSPARENT;
        }

    }

    public String riesgoColorNuevaConfiguracion(Double valor,Integer fila) {
        try {

            String colorAsignado = "";
            
            if (fila<1){
                return ConstantesEstandares.COLOR_TRANSPARENT;
            }
            switch (valor.toString()) {
                case "0.4":
                    colorAsignado = confColor0_4.getCnfValor();
                    break;
                case "0.8":
                    colorAsignado = confColor0_8.getCnfValor();
                    break;
                case "1.2":
                    colorAsignado = confColor1_2.getCnfValor();
                    break;
                case "1.6":
                    colorAsignado = confColor1_6.getCnfValor();
                    break;
                case "2.0":
                    colorAsignado = confColor2_0.getCnfValor();
                    break;
                case "2.4":
                    colorAsignado = confColor2_4.getCnfValor();
                    break;
                case "3.2":
                    colorAsignado = confColor3_2.getCnfValor();
                    break;
                case "3.6":
                    colorAsignado = confColor3_6.getCnfValor();
                    break;
                case "4.0":
                    colorAsignado = confColor4_0.getCnfValor();
                    break;
                case "4.8":
                    colorAsignado = confColor4_8.getCnfValor();
                    break;
                case "6.0":
                    colorAsignado = confColor6_0.getCnfValor();
                    break;
                case "6.4":
                    colorAsignado = confColor6_4.getCnfValor();
                    break;
                case "8.0":
                    colorAsignado = confColor8_0.getCnfValor();
                    break;
                default:
                    colorAsignado = confColor10_0.getCnfValor();

            }

            if (colorAsignado.equalsIgnoreCase("Verde")) {
                return ConstantesEstandares.SEMAFORO_VERDE;
            }

            if (colorAsignado.equalsIgnoreCase("Amarillo")) {
                return ConstantesEstandares.SEMAFORO_AMARILLO;
            }
            if (colorAsignado.equalsIgnoreCase("Rojo")) {
                return ConstantesEstandares.SEMAFORO_ROJO;
            }

            if (colorAsignado.equalsIgnoreCase("Azul")) {
                return ConstantesEstandares.SEMAFORO_AZUL + ";color:white";
            }

            return ConstantesEstandares.COLOR_TRANSPARENT;
        } catch (Exception w) {
            return ConstantesEstandares.COLOR_TRANSPARENT;
        }

    }

    public String probabilidadNuevaConfiguracion(Integer valor) {
        String[] split = confValoresRiesgoX.getCnfValor().split(",");
        switch (valor) {
            case 20:
                return split[0];
            case 40:
                return split[1];
            case 60:
                return split[2];
            case 80:
                return split[3];
            default:
                return split[4];

        }

    }

    public String impactoNuevaConfiguracion(Integer valor) {
        try {
            String[] split = confValoresRiesgoY.getCnfValor().split(",");
            switch (valor) {
                case 0:
                    return confTituloRiesgoY.getCnfValor();
                case 2:
                    return split[0];
                case 4:
                    return split[1];
                case 6:
                    return split[2];
                case 8:
                    return split[3];
                default:
                    return split[4];

            }
        } catch (Exception e) {
        }
        return "S/D";
    }
    
    public String styleNuevaConfiguracion(Integer valor) {
      return valor>0?"":"float:left";
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

    public List<Configuracion> getListaHitorial() {
        return listaHitorial;
    }

    public void setListaHitorial(List<Configuracion> listaHitorial) {
        this.listaHitorial = listaHitorial;
    }

    public Boolean getRenderPopupHistorial() {
        return renderPopupHistorial;
    }

    public void setRenderPopupHistorial(Boolean renderPopupHistorial) {
        this.renderPopupHistorial = renderPopupHistorial;
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

    public List<FilaRiesgosLimite> getLimiteGestionRiesgos() {
        return limiteGestionRiesgos;
    }

    public void setLimiteGestionRiesgos(List<FilaRiesgosLimite> limiteGestionRiesgos) {
        this.limiteGestionRiesgos = limiteGestionRiesgos;
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

    public void cnfHtmlChange(ValueChangeEvent ev) {
        confEnEdicion.setCnfHtml((Boolean) ev.getNewValue());
    }

    public String getMensajeArchivos() {
        return mensajeArchivos;
    }

    public void setMensajeArchivos(String mensajeArchivos) {
        this.mensajeArchivos = mensajeArchivos;
    }

    public String getColor0_4() {
        return color0_4;
    }

    public void setColor0_4(String color0_4) {
        this.color0_4 = color0_4;
    }

    public String getColor0_8() {
        return color0_8;
    }

    public void setColor0_8(String color0_8) {
        this.color0_8 = color0_8;
    }

    public String getColor1_2() {
        return color1_2;
    }

    public void setColor1_2(String color1_2) {
        this.color1_2 = color1_2;
    }

    public String getColor1_6() {
        return color1_6;
    }

    public void setColor1_6(String color1_6) {
        this.color1_6 = color1_6;
    }

    public String getColor2_0() {
        return color2_0;
    }

    public void setColor2_0(String color2_0) {
        this.color2_0 = color2_0;
    }

    public String getColor2_4() {
        return color2_4;
    }

    public void setColor2_4(String color2_4) {
        this.color2_4 = color2_4;
    }

    public String getColor3_2() {
        return color3_2;
    }

    public void setColor3_2(String color3_2) {
        this.color3_2 = color3_2;
    }

    public String getColor3_6() {
        return color3_6;
    }

    public void setColor3_6(String color3_6) {
        this.color3_6 = color3_6;
    }

    public String getColor4_0() {
        return color4_0;
    }

    public void setColor4_0(String color4_0) {
        this.color4_0 = color4_0;
    }

    public String getColor4_8() {
        return color4_8;
    }

    public void setColor4_8(String color4_8) {
        this.color4_8 = color4_8;
    }

    public String getColor6_0() {
        return color6_0;
    }

    public void setColor6_0(String color6_0) {
        this.color6_0 = color6_0;
    }

    public String getColor6_4() {
        return color6_4;
    }

    public void setColor6_4(String color6_4) {
        this.color6_4 = color6_4;
    }

    public String getColor8_0() {
        return color8_0;
    }

    public void setColor8_0(String color8_0) {
        this.color8_0 = color8_0;
    }

    public String getColor10_0() {
        return color10_0;
    }

    public void setColor10_0(String color10_0) {
        this.color10_0 = color10_0;
    }

    public String calculoMegas(long bytes) {
        if (-1000 < bytes && bytes < 1000) {
            return bytes + " B";
        }
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        while (bytes <= -999_950 || bytes >= 999_950) {
            bytes /= 1000;
            ci.next();
        }
        return String.format("%.1f %cB", bytes / 1000.0, ci.current());
    }

    public Configuracion getConfTituloRiesgoX() {
        return confTituloRiesgoX;
    }

    public void setConfTituloRiesgoX(Configuracion confTituloRiesgoX) {
        this.confTituloRiesgoX = confTituloRiesgoX;
    }

    public Configuracion getConfTituloRiesgoY() {
        return confTituloRiesgoY;
    }

    public void setConfTituloRiesgoY(Configuracion confTituloRiesgoY) {
        this.confTituloRiesgoY = confTituloRiesgoY;
    }

    public Configuracion getConfValoresRiesgoX() {
        return confValoresRiesgoX;
    }

    public void setConfValoresRiesgoX(Configuracion confValoresRiesgoX) {
        this.confValoresRiesgoX = confValoresRiesgoX;
    }

    public Configuracion getConfValoresRiesgoY() {
        return confValoresRiesgoY;
    }

    public void setConfValoresRiesgoY(Configuracion confValoresRiesgoY) {
        this.confValoresRiesgoY = confValoresRiesgoY;
    }

}
