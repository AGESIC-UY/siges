package com.sofis.web.mb;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.business.utils.EntregablesUtils;
import com.sofis.business.utils.ProyectosUtils;
import com.sofis.business.validations.EntregablesValidacion;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.data.Calidad;
import com.sofis.entities.data.Configuracion;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Documentos;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.Interesados;
import com.sofis.entities.data.Participantes;
import com.sofis.entities.data.PlantillaCronograma;
import com.sofis.entities.data.Productos;
import com.sofis.entities.data.ProyIndices;
import com.sofis.entities.data.ProyOtrosDatos;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.Riesgos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.TipoFichaEnum;
import com.sofis.entities.tipos.AdqPagosTO;
import com.sofis.entities.tipos.CronogramaTO;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.web.delegates.CronogramaDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.EmailValidator;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.ConfiguracionDelegate;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.PlantillaCronogramaDelegate;
import com.sofis.web.delegates.ProductosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.genericos.constantes.ConstantesPresentacion;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.CronogramaUtils;
import com.sofis.web.utils.EntregablesCargaPorFomulario;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.SofisCombo;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.icefaces.ace.json.JSONArray;
import org.icefaces.ace.json.JSONException;
import org.icefaces.ace.json.JSONObject;
import org.icefaces.util.JavaScriptRunner;

@ManagedBean(name = "moduloCronogramaMB")
@SessionScoped
public class ModuloCronogramaMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(ModuloCronogramaMB.class.getName());

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @Inject
    private CronogramaDelegate cronogramaDelegate;
    @Inject
    private ProductosDelegate productosDelegate;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;
    @Inject
    private ConfiguracionDelegate configuracionDelegate;
    @Inject
    private AdquisicionDelegate adquisicionDelegate;
    @Inject
    private PlantillaCronogramaDelegate plantillaCronogramaDelegate;
    @Inject
    private ConfiguracionBean configuracionBean;

    public static final String PLANTILLA_CRO_MSG_ID = "plantCroPopupMsg";

    private int[] indiceAvanceProd;
    private int[] indiceAvanceTiempo;
    private int[] indiceAvanceFinalizado;
    private int[] indiceAvanceParcial;
    private Map<Integer, Boolean> entregablesEditablesPorUsuario = new HashMap<Integer, Boolean>();
    private List<Entregables> listaEntregables;

    private Cronogramas cronograma = new Cronogramas();
    private String dataCron = "";
    private List<Entregables> listaEntregablesResumen;

    private boolean cargaDesdeArchivo = false;
    private boolean cargaPorFormulario = false;

    private PlantillaCronograma plantillaCro;
    private boolean renderPopupPlantillaCro = false;
    private Date plantillaFechaInicio;
    private List<PlantillaCronograma> plantillaCroList;
    //parche: Borrar
    private boolean primeraCargarGantt = true;
    private boolean recienCreado = false;

    // Plantilla Cro Popup
    private SofisCombo plantillaCroListCombo = new SofisCombo();

    private SofisCombo proyectosCombo = new SofisCombo();
    private SofisCombo entregablesCombo = new SofisCombo();
    private SofisCombo entregablesProyectoCombo = new SofisCombo();

    private boolean mostrarPopupEntregableReferencia;
    private boolean mostrarBotonEntregableReferencia;

    private Integer proyectoCodigoFiltro = 0;
    private String proyectoNombreFiltro = "";
    private FichaTO fichaSeleccionada;
    private String entregableNombreFiltro = "";
    private Integer proyectoCodigoSeleccionado = 0;
    private Boolean remplazarEntrgable;

    private Boolean filtroRender;

    @PostConstruct
    public void init() {
    }

    public String guardarEntregableReferencia(Integer estadoCronograma) {

        if (entregablesCombo.getSelectedObject() == null) {

            return null;
        }

        EntregableTO entregable = EntregablesUtils.convert((Entregables) entregablesCombo.getSelectedObject());
        EntregableTO entregableARemplazar = null;

        if (remplazarEntrgable && entregablesProyectoCombo.getSelectedObject() == null) {
            return null;
        }

        if (entregablesProyectoCombo.getSelectedObject() != null) {
            entregableARemplazar = EntregablesUtils.convert((Entregables) entregablesProyectoCombo.getSelectedObject());
            entregable.setDependencias(null);
        }

        entregable.setId(remplazarEntrgable ? entregableARemplazar.getId() : null);
        entregable.setNombre(remplazarEntrgable ? entregableARemplazar.getNombre() : entregable.getNombre());
        entregable.setDescripcion(remplazarEntrgable ? entregableARemplazar.getDescripcion() : entregable.getNombre());
        entregable.setNivel(null);
        entregable.setEsfuerzo(0);
        entregable.setEsReferencia(true);
        entregable.setReferido(EntregablesUtils.convert((Entregables) entregablesCombo.getSelectedObject()));

        entregable.setProyectoReferido(ProyectosUtils.convert((Proyectos) proyectosCombo.getSelectedObject()));

        try {
            JSONObject entregableJSON = CronogramaUtils.entregableToJSON(entregable, estadoCronograma);

            String js;
            if (remplazarEntrgable) {
                js = "triggerReplaceReferenceTaskBelowCurrentTaskGantt(" + entregableJSON + ")";
            } else {
                js = "triggerAddReferenceTaskBelowCurrentTaskGantt(" + entregableJSON + ")";
            }

            JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), js);

        } catch (JSONException ex) {
            LOGGER.log(Level.SEVERE, "crono_error_entregable_referencia_crear_json", ex);
            JSFUtils.agregarMsg("ganttForm", "crono_error_entregable_referencia_crear_json", null);
        }
        limpiarFiltroEntregables();
        return null;
    }

    public String cancelarEntregableReferencia(Integer estadoCronograma) {
        EntregableTO referido = EntregablesUtils.convert((Entregables) entregablesCombo.getSelectedObject());

        EntregableTO entregable = EntregablesUtils.convert((Entregables) entregablesCombo.getSelectedObject());
        entregable.setEsReferencia(true);
        entregable.setReferido(referido);
        entregable.setProyectoReferido(ProyectosUtils.convert((Proyectos) proyectosCombo.getSelectedObject()));

        try {
            JSONObject entregableJSON = CronogramaUtils.entregableToJSON(entregable, estadoCronograma);

            String js = "triggerAddReferenceTaskBelowCurrentTaskGantt(" + entregableJSON + ")";

            JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), js);

        } catch (JSONException ex) {
            LOGGER.log(Level.SEVERE, "crono_error_entregable_referencia_crear_json", ex);
            JSFUtils.agregarMsg("ganttForm", "crono_error_entregable_referencia_crear_json", null);
        }

        return null;
    }

    public boolean isMostrarPopupEntregableReferencia() {
        return mostrarPopupEntregableReferencia;
    }

    public void setMostrarPopupEntregableReferencia(boolean mostrarPopupEntregableReferencia) {
        this.mostrarPopupEntregableReferencia = mostrarPopupEntregableReferencia;
    }

    public void cambioProyecto(ValueChangeEvent ev) {

        Integer id = (Integer) ev.getNewValue();

        proyectoCodigoSeleccionado = id;

        cambiarProyectoPorId(proyectoCodigoSeleccionado);
        cargarEntregableFiltrado();
    }

    public SofisCombo getProyectosCombo() {
        return proyectosCombo;
    }

    public void setProyectosCombo(SofisCombo proyectosCombo) {
        this.proyectosCombo = proyectosCombo;
    }

    public SofisCombo getEntregablesCombo() {
        return entregablesCombo;
    }

    public void setEntregablesCombo(SofisCombo entregablesCombo) {
        this.entregablesCombo = entregablesCombo;
    }

    public void cargarOtrosDatos(FichaTO fichaTO) {
        Set<Entregables> setEntAvance = null;
        ProyOtrosDatos pod = fichaTO.getOtrosDatos();
        if (fichaTO.getCroFk() != null) {
            if (pod != null && pod.getProyOtrEntFk() != null) {
                List<Entregables> entHijos = EntregablesUtils.obtenerHijos(new ArrayList<>(fichaTO.getCroFk().getEntregablesSet()), pod.getProyOtrEntFk(), Boolean.TRUE);
                setEntAvance = new HashSet<>(entHijos);
            } else {
                setEntAvance = fichaTO.getCroFk().getEntregablesSet();
            }
        }
        indiceAvanceProd = cronogramaDelegate.calcularAvanceCronoParcial(setEntAvance);
        Integer porcAvance = proyectoDelegate.porcentajeAvanceEnTiempoLBase(setEntAvance);
        indiceAvanceTiempo = porcAvance != null ? new int[]{porcAvance, (100 - porcAvance), 0} : null;
    }

    public void cargarEntregablesEditablesPorUsuario(FichaTO fichaTO, SsUsuario usuario) {
        // En la siguiente iteración voy a recorrer los entregables asociados al cronograma
        // y así poder conocer cuales son los documentos a los que  el usuario loggeado puede editar y eliminar.
        Cronogramas cro = fichaTO.getCroFk();

        if (cro != null && cro.getEntregablesSet() != null) {

            // Se agrega esta condición de "isEstadoFinalizado" para que cuando se recorra se fije que si esta finalizado el proyecto
            // se ponga en false el valor. De esta manera no se pisa el valor obtenido en setRenderAttribute.
            boolean isEstadoFinalizado = fichaTO.isEstado(Estados.ESTADOS.FINALIZADO.estado_id);

            List<Entregables> entregables = new ArrayList<>(cro.getEntregablesSet());
            for (Entregables iterEnt : entregables) {

                boolean entregableEditablePorUsuario = !isEstadoFinalizado && ((iterEnt.getCoordinadorUsuFk() != null) && (iterEnt.getCoordinadorUsuFk().getUsuId().equals(usuario.getUsuId())));
                entregablesEditablesPorUsuario.put(iterEnt.getEntId(), entregableEditablePorUsuario);
            }
        }

    }

    public void cargarEntregablesAsociadosAUsuario(FichaTO fichaTO, SsUsuario usuario) {
        // Para saber si el usuario esta asociado a algún entregable, primero me fijo si es Gerente o Adjunto.
        // Si no lo es le paso el Mapa que tiene la información de a cual entregable  esta asociado el usuario.
        boolean isGerente = SsUsuariosUtils.isUsuarioGerenteFicha(fichaTO, usuario);
        boolean isAdjunto = SsUsuariosUtils.isUsuarioAdjuntoFicha(fichaTO, usuario);
        boolean isPmof=SsUsuariosUtils.isUsuarioPMOF(fichaTO, usuario, fichaTO.getOrgFk().getOrgPk());
        Boolean isPmofEditor = Boolean.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PMOF_GESTIONA_SUS_PROYECTOS, fichaTO.getOrgFk().getOrgPk()).getCnfValor());
        fichaTO.setIsPmofEditor(isPmof && isPmofEditor);
        boolean isGerenteOAdjunto = isGerente || isAdjunto || isPmofEditor;
        if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
            listaEntregables = new ArrayList<>(fichaTO.getCroFk().getEntregablesSet());
            if (isGerenteOAdjunto) {
                listaEntregables = EntregablesUtils.cargarCamposCombos(listaEntregables);
            } else {
                listaEntregables = EntregablesUtils.cargarCamposCombos(listaEntregables, entregablesEditablesPorUsuario);
                listaEntregables = EntregablesUtils.filtrarEntregablesEditables(listaEntregables, entregablesEditablesPorUsuario);
            }
        }
    }

    public void cargarResumenCronograma(FichaTO fichaTO) {

        indiceAvanceFinalizado = null;
        indiceAvanceParcial = null;

        if (fichaTO.getCroFk() != null) {
            if (fichaTO.getProyIndices() != null && fichaTO.getProyIndices().getProyindAvanceFinAzul() != null) {
                indiceAvanceFinalizado = fichaTO.getProyIndices().getProyindAvanceFinal();
                indiceAvanceParcial = fichaTO.getProyIndices().getProyindAvanceParcial();
            } else {
                indiceAvanceFinalizado = cronogramaDelegate.calcularAvanceCronoFinalizado(fichaTO.getCroFk().getEntregablesSet());
                indiceAvanceParcial = cronogramaDelegate.calcularAvanceCronoParcial(fichaTO.getCroFk().getEntregablesSet());
                if (fichaTO.getProyIndices() == null) {
                    fichaTO.setProyIndices(new ProyIndices());
                }
                fichaTO.getProyIndices().setProyindAvanceFinal(indiceAvanceFinalizado);
                fichaTO.getProyIndices().setProyindAvanceParcial(indiceAvanceParcial);
            }
        }

        listaEntregablesResumen = cronogramaDelegate.obtenerResumenCronograma(fichaTO.getFichaFk(), 5);
    }

    public void cargarComboProyectosParaReferencia(FichaTO fichaTO) {

        fichaSeleccionada = fichaTO;
        Configuracion mostrarBotonEntRef = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PERMITIR_REFERENCIAR_ENTREGABLES, inicioMB.getOrganismo().getOrgPk());
        SsUsuario usuario = inicioMB.getUsuario();

        boolean isGerente = SsUsuariosUtils.isUsuarioGerenteFicha(fichaTO, usuario);
        boolean isAdjunto = SsUsuariosUtils.isUsuarioAdjuntoFicha(fichaTO, usuario);
        boolean isGerenteOAdjunto = isGerente || isAdjunto;

        if (mostrarBotonEntRef.getCnfValor().equals("true") && isGerenteOAdjunto) {
            List<Proyectos> proyectos = proyectoDelegate.obtenerActivosMenosElActual(fichaTO.getOrgFk().getOrgPk(), fichaTO.getFichaFk(), proyectoCodigoFiltro, proyectoNombreFiltro);

            proyectosCombo = new SofisCombo((List) proyectos, "proyPkNombre");
            proyectosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

            entregablesCombo = new SofisCombo((List) new ArrayList(), "entNombre");
            entregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        }
    }

    public void cargarProyectos() {
        List<Proyectos> proyectos = new ArrayList<>();
        if (fichaSeleccionada != null) {

            proyectos = proyectoDelegate.obtenerActivosMenosElActual(fichaSeleccionada.getOrgFk().getOrgPk(), fichaSeleccionada.getFichaFk(), proyectoCodigoFiltro, proyectoNombreFiltro);

            proyectosCombo = new SofisCombo((List) proyectos, "proyPkNombre");
            proyectosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

            entregablesCombo = new SofisCombo((List) new ArrayList(), "entNombre");
            entregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
        } else {

            proyectosCombo = new SofisCombo((List) proyectos, "proyPkNombre");
            proyectosCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

        }
        proyectosCombo.setSelected(-1);

    }

    public void cargarEntregableFiltrado() {

        if (entregablesCombo != null) {

            if (StringsUtils.isEmpty(entregableNombreFiltro)) {
                cambiarProyectoPorId(proyectoCodigoSeleccionado);
            } else {
                List<Entregables> entregablesList = entregablesCombo.getAllObjects();
                List<Entregables> entregablesFitrados = new ArrayList();
                for (Entregables e : entregablesList) {

                    if (e != null && e.getEntNombre() != null && e.getEntNombre().toLowerCase().contains(entregableNombreFiltro.toLowerCase())) {
                        entregablesFitrados.add(e);
                    }
                }
                entregablesCombo = new SofisCombo((List) entregablesFitrados, null);
                entregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));
            }

        }

    }

    /**
     * Cargar los datos necesarios para el Frame Cronograma.
     *
     * @param actualizar Si actualiza los datos de la ficha.
     */
    public void cargarFrameCronograma(boolean actualizar, FichaTO fichaTO) {

        try {

            SsUsuario usuario = inicioMB.getUsuario();
            Integer orgPk = inicioMB.getOrganismo().getOrgPk();

            if (fichaTO.getCroFk() != null) {
                cronograma = fichaTO.getCroFk();
            }
            boolean isGerente = SsUsuariosUtils.isUsuarioGerenteFicha(fichaTO, usuario);
            boolean isAdjunto = SsUsuariosUtils.isUsuarioAdjuntoFicha(fichaTO, usuario);
            boolean isPmof = SsUsuariosUtils.isUsuarioPMOF(fichaTO, usuario, orgPk);
            boolean isGerenteOAdjunto = isGerente || isAdjunto;

            Configuracion mostrarBotonEntRef = configuracionBean.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PERMITIR_REFERENCIAR_ENTREGABLES, inicioMB.getOrganismo().getOrgPk());
            mostrarBotonEntregableReferencia = mostrarBotonEntRef.getCnfValor().equals("true");

            if (fichaTO.getFichaFk() == null) {
                return;
            }

            /**
             * Para que abra siempre el módulo en el gantt
             */
            cargaDesdeArchivo = false;
            cargaPorFormulario = false;

            /**
             * Para que abra siempre el módulo en el gantt
             */
            cargaDesdeArchivo = false;
            cargaPorFormulario = false;

            Cronogramas cro = fichaTO.getCroFk();

            CronogramaTO cronograma = cronogramaDelegate.obtenerCronogramaCompletoPorIdProyecto(fichaTO.getFichaFk());

            //Agregar al CronogramaTO si el usuario es gerente o adjunto
            cronograma.setIsGerenteOAdjunto(isGerenteOAdjunto);

            if (fichaTO.getCroFk() != null && fichaTO.getCroFk().getEntregablesSet() != null) {
                indiceAvanceFinalizado = cronogramaDelegate.calcularAvanceCronoFinalizado(fichaTO.getCroFk().getEntregablesSet());
                indiceAvanceParcial = cronogramaDelegate.calcularAvanceCronoParcial(fichaTO.getCroFk().getEntregablesSet());
            }

            List<SsUsuario> usuarios = ssUsuarioDelegate.obtenerTodosPorOrganismo(orgPk, true);

            Boolean isPmofEditor = Boolean.valueOf(configuracionDelegate.obtenerCnfPorCodigoYOrg(ConfiguracionCodigos.PMOF_GESTIONA_SUS_PROYECTOS, orgPk).getCnfValor());
            fichaTO.setIsPmofEditor(isPmof && isPmofEditor);
            try {
                JSONObject dataCrono = CronogramaUtils.cronogramaToJSON(cronograma, usuario, usuarios, fichaTO);
                if (actualizar) {
                    JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "configGannt();");
                }

                JavaScriptRunner.runScript(FacesContext.getCurrentInstance(), "loadGanttValues(" + dataCrono + ");");

            } catch (JSONException ex) {
                LOGGER.log(Level.SEVERE, null, ex);

                JSFUtils.agregarMsgError("", Labels.getValue("error_cargar_cronograma"), null);

                inicioMB.abrirPopupMensajes();
            }

        } catch (Exception ex) {

            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    // ======================================================================================================================
    // Carga masiva de tareas del Gantt desde archivo
    public boolean getCargaDesdeArchivo() {
        return cargaDesdeArchivo;
    }

    public boolean isCargaPorFormulario() {
        return cargaPorFormulario;
    }

    public void setCargaPorFormulario(boolean cargaPorFormulario) {
        this.cargaPorFormulario = cargaPorFormulario;
    }

    public boolean habilitarCargaDesdeArchivo() {
        //return fichaTO.isEstado(Estados.ESTADOS.INICIO.estado_id) || fichaTO.isEstado(Estados.ESTADOS.PLANIFICACION.estado_id);
        return false;
    }

    public void mostrarCargaDesdeArchivo(FichaTO fichaTO) {

        if (TipoFichaEnum.PROGRAMA.getValue() == fichaTO.getTipoFicha()) {
            JSFUtils.agregarMsgError("ganttForm", "Esta funcionalidad solo está disponible para proyectos.", null);
            return;
        }

        Cronogramas cro = fichaTO.getCroFk();
        if (cro == null) {
            return;
        }

        //Solo se permite cargar datos de un archivo si sus entregables no tienen avance (avance mayor a 0) y no estén asociados a productos, 
        //interesados, colaboradores, calidad, riesgos o pagos
        for (Entregables entregable : cro.getEntregablesSet()) {
            if ((entregable.getEntProgreso() != null && entregable.getEntProgreso() > 0)) {
                JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable tiene avance mayor a cero.", null);
                return;
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene interesados registrados
        if (fichaTO.getInteresados() != null) {
            for (Interesados interesado : fichaTO.getInteresados()) {
                if (interesado.getIntEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un interesado.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene participantes registrados
        if (fichaTO.getParticipantes() != null) {
            for (Participantes participante : fichaTO.getParticipantes()) {
                if (participante.getPartEntregablesFk() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un participante.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene registros de calidad
        if (fichaTO.getCalidadList() != null) {
            for (Calidad calidad : fichaTO.getCalidadList()) {
                if (calidad.getCalEntFk() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a una calidad.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene riesgos registrados
        if (fichaTO.getRiesgos() != null) {
            for (Riesgos riesgo : fichaTO.getRiesgos()) {
                if (riesgo.getRiskEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un riesgo.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene documentos registrados
        if (fichaTO.getDocumentos() != null) {
            for (Documentos documento : fichaTO.getDocumentos()) {
                if (documento.getDocsEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un documento.", null);
                    return;
                } else {
                    if (documento.getDocsPagoFk() != null && documento.getDocsPagoFk().getEntregables() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un pago.", null);
                        return;
                    }
                }
            }
        }

        //Solo se permite cargar datos de un archivo si el proyecto no tiene productos registrados
        Proyectos proyecto = proyectoDelegate.obtenerProyPorId(fichaTO.getFichaFk());
        if (proyecto != null) {
            List<Productos> productos = productosDelegate.obtenerProdPorProyPk(proyecto.getProyPk());
            if (productos != null) {
                for (Productos producto : productos) {
                    if (producto.getProdEntregableFk() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque al menos un entregable está asociado a un producto.", null);
                        return;
                    }
                }
            }
        }

        //Los pagos están siempre asociados a un entregable, por lo que si existe un pago no se pueden eliminar los entregables actuales
        List<AdqPagosTO> adqPagos = adquisicionDelegate.obtenerAdquisicionPagosList(fichaTO.getPreFk().getPrePk());
        if (adqPagos != null) {
            for (AdqPagosTO adqPago : adqPagos) {
                if (adqPago.getTipo() == 2) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga desde un archivo porque hay al menos un pago asociado a entregables.", null);
                    return;
                }
            }
        }

        //Si llegó hasta acá es porque pasó todas las validaciones
        cargaDesdeArchivo = true;
    }

    public void cancelarCargaDesdeArchivo(FichaTO fichaTO) {

        JSFUtils.removerMensages();
        cargarFrameCronograma(true, fichaTO);
        cargaDesdeArchivo = false;
    }

    private File planillaCargarEntregables;

    public File getPlanillaCargarEntregables() {
        return planillaCargarEntregables;
    }

    public void setPlanillaCargarEntregables(File planillaCargarEntregables) {
        this.planillaCargarEntregables = planillaCargarEntregables;
    }

    public void subirPlanillaCargaEntregablesAction(FileEntryEvent evento) {
        JSFUtils.removerMensages();
        try {
            planillaCargarEntregables = null;
            FileEntry fileEntry = (FileEntry) evento.getComponent();
            FileEntryResults results = fileEntry.getResults();
            planillaCargarEntregables = results.getFiles().get(0).getFile();
        } catch (Exception e2) {
            JSFUtils.agregarMsg("error_subir_archivo");
            LOGGER.log(Level.SEVERE, e2.getMessage(), e2);
        }
    }

    public Object importarEntregablesDesdeArchivo(FichaTO fichaTO) {

        HSSFSheet hoja;
        try {
            //Abrir el archivo
            InputStream stream = new FileInputStream(planillaCargarEntregables);
            //Abrir el libro
            HSSFWorkbook libro = new HSSFWorkbook(stream);
            //Obtener la hoja 1
            hoja = libro.getSheetAt(0);
        } catch (Exception ex) {
            throw new BusinessException("No se puede acceder a la planilla indicada o la misma no tiene el formato correcto");
        }
        HSSFRow fila;
        //Iterar por las filas 
        Iterator rows = hoja.rowIterator();
        int nroFila = 0;
        List<Entregables> entregables = new LinkedList();
        boolean procesamientoTerminado = false;
        while (!procesamientoTerminado && rows.hasNext()) {
            nroFila++;
            fila = (HSSFRow) rows.next();
            if (nroFila == 1) {
                //Saltear la primer fila (los encabezados)
                continue;
            }
            //Si no hay valor en la columna 0 se asume que se terminaron los datos
            if (fila.getCell(0) == null || fila.getCell(0).toString().trim().isEmpty()) {
                procesamientoTerminado = true;
                continue;
            }
            Entregables entregable = new Entregables();
            //Columna 0: número (identificador del entregable)
            try {
                entregable.setEntId((Double.valueOf(fila.getCell(0).toString().trim())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'número' en la fila " + nroFila + " no es correcto");
            }
            //Columna 1: nombre
            try {
                entregable.setEntNombre(fila.getCell(1).toString().trim());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'nombre' en la fila " + nroFila + " no es correcto");
            }
            //Columna 2: nivel
            try {
                entregable.setEntNivel((Double.valueOf(fila.getCell(2).toString().trim())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'nivel' en la fila " + nroFila + " no es correcto");
            }
            //Columna 3: esfuerzo
            try {
                entregable.setEntEsfuerzo((Double.valueOf(fila.getCell(3).toString().trim())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'esfuerzo' en la fila " + nroFila + " no es correcto");
            }
            //Columna 4: avance
            try {
                entregable.setEntProgreso((Double.valueOf(fila.getCell(4).toString().trim())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'avance' en la fila " + nroFila + " no es correcto", ex);
            }
            //Columna 5: fecha de inicio
            try {
                //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                entregable.setEntInicio(fila.getCell(5).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
            } catch (Exception ex) {
                throw new BusinessException("El campo 'fecha de inicio' en la fila " + nroFila + " no es correcto");
            }
            //Columna 6: fecha de fin
            try {
                //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                entregable.setEntFin(fila.getCell(6).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
            } catch (Exception ex) {
                throw new BusinessException("El campo 'fecha de fin' en la fila " + nroFila + " no es correcto");
            }
            //Columna 7: fecha de inicio de línea base (no es requerida)
            try {
                if (fila.getCell(7) != null && !fila.getCell(7).toString().trim().isEmpty()) {
                    //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                    entregable.setEntInicioLineaBase(fila.getCell(7).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
                }
            } catch (Exception ex) {
                throw new BusinessException("El campo 'fecha de inicio de línea base' en la fila " + nroFila + " no es correcto");
            }
            //Columna 8: fecha de fin de línea base (no es requerida)
            try {
                if (fila.getCell(8) != null && !fila.getCell(8).toString().trim().isEmpty()) {
                    //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                    entregable.setEntFinLineaBase(fila.getCell(8).getDateCellValue().getTime() + 12 * 60 * 60 * 1000);
                }
            } catch (Exception ex) {
                throw new BusinessException("El campo 'fecha de fin de línea base' en la fila " + nroFila + " no es correcto");
            }
            //Columna 9: es hito
            try {
                entregable.setEntInicioEsHito(Boolean.FALSE);
                String value = fila.getCell(9).toString().trim();
                if (value == null || !("SI".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value))) {
                    throw new BusinessException("El campo 'es hito' en la fila " + nroFila + " no es correcto");
                }
                entregable.setEntFinEsHito(fila.getCell(9).toString().equalsIgnoreCase("SI"));
            } catch (BusinessException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new BusinessException("El campo 'es hito' en la fila " + nroFila + " no es correcto");
            }
            //Columna 10: predecesores
            try {
                if (fila.getCell(10) != null && !fila.getCell(10).toString().trim().isEmpty()) {
                    //Es necesario convertirla a String porque sino cuando hay un solo dígito lo toma como número y lo devuelve con decimales
                    fila.getCell(10).setCellType(HSSFCell.CELL_TYPE_STRING);
                    String value = fila.getCell(10).toString().trim();
                    entregable.setEntPredecesorFk(value);
                    entregable.setEntPredecesorDias(0);
                    //Si tiene predecesores configurados debe cumplir el patrón
                    if (entregable.getEntPredecesorFk() != null && !entregable.getEntPredecesorFk().isEmpty()
                            && !entregable.getEntPredecesorFk().matches(EntregablesValidacion.DEPENDENCIA_REGEX)) {
                        throw new BusinessException("El campo 'dependencia' en la fila " + nroFila + " no es correcto");
                    }
                }
            } catch (BusinessException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new BusinessException("El campo 'dependencia' en la fila " + nroFila + " no es correcto");
            }
            //Columna 11: es clave PMO
            try {
                String value = fila.getCell(11).toString().trim();
                if (value == null || !("SI".equalsIgnoreCase(value) || "NO".equalsIgnoreCase(value))) {
                    throw new BusinessException("El campo 'es clave PMO' en la fila " + nroFila + " no es correcto");
                }
                entregable.setEntRelevante(value.equalsIgnoreCase("SI"));
            } catch (BusinessException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new BusinessException("El campo 'es clave PMO' en la fila " + nroFila + " no es correcto");
            }
            //Columna 12: coordinador
            String coordinador;
            try {
                if (fila.getCell(12) != null && !fila.getCell(12).toString().trim().isEmpty()) {
                    coordinador = fila.getCell(12).toString();
                    //Si no es vacío debe ser una dirección de correo electrónico
                    if (!coordinador.trim().isEmpty() && !EmailValidator.validateEmail(coordinador)) {
                        throw new BusinessException("El campo 'coordinador' en la fila " + nroFila + " no es correcto");
                    }
                    if (coordinador.length() > 50) {
                        throw new BusinessException("El campo 'coordinador' en la fila " + nroFila + " tiene más de 50 caracteres.");
                    }
                    entregable.setCoordinadorUsuFk(ssUsuarioDelegate.obtenerSsUsuarioPorMail(coordinador));
                }
            } catch (BusinessException ex) {
                throw ex;
            } catch (Exception ex) {
                throw new BusinessException("El campo 'coordinador' en la fila " + nroFila + " no es correcto");
            }
            //Columna 13: observaciones
            try {
                if (fila.getCell(13) != null && !fila.getCell(13).toString().trim().isEmpty()) {
                    entregable.setEntDescripcion(fila.getCell(13).toString());
                }
            } catch (Exception ex) {
                throw new BusinessException("El campo 'observaciones' en la fila " + nroFila + " no es correcto");
            }
            //Otros campos que no vienen en la planilla
            entregable.setEntStatus("STATUS_ACTIVE");
            entregable.setEntCollapsed(Boolean.FALSE);
            entregable.setEntInicioProyecto(Boolean.FALSE);
            entregable.setEntFinProyecto(Boolean.FALSE);
            //Si es un hito se pone como fecha de inicio la misma que la fecha de fin
            if (entregable.getEntFinEsHito()) {
                entregable.setEntInicio(entregable.getEntFin());
            }
            entregables.add(entregable);
            //Queda pendiente lo siguiente porque dependen de otros entregables (hay que hacerlo después de cargar todos los entregables):
            //1-Ajustar las fechas de inicio y fin, y las fechas de inicio y fin de la línea base
            //2-Completar los campos entParent, entDuracion, entDuracionLineaBase
        }
        //Ajustar las fechas de los entregables en base a los hijos y las dependencias
        entregables = EntregablesUtils.ajustarFechas(entregables);
        //Validar los datos y las fechas de los entregables
        EntregablesValidacion.validarEntregables(entregables);
        //Si llegó hasta acá se importaron y validaron todos los entregables
        fichaTO.getCroFk().getEntregablesSet().clear();
        for (Entregables entregable : entregables) {
            entregable.setEntCroFk(fichaTO.getCroFk());
            fichaTO.getCroFk().getEntregablesSet().add(entregable);
        }
        //Guardar el cronograma con todos los entregables
        return cronogramaDelegate.guardarCronograma(fichaTO.getCroFk(), fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());
// 
    }

    // ======================================================================================================================
    // Carga masiva de tareas del Gantt
    public boolean getCargaPorFormulario() {
        return cargaPorFormulario;
    }

    public void mostrarCargaPorFormulario(FichaTO fichaTO) {

        if (TipoFichaEnum.PROGRAMA.getValue() == fichaTO.getTipoFicha()) {
            JSFUtils.agregarMsgError("ganttForm", "Esta funcionalidad solo está disponible para proyectos.", null);
            return;
        }

        Cronogramas cro = fichaTO.getCroFk();
        if (cro == null) {
            return;
        }

        //Solo se permite cargar datos por formulario si sus entregables no tienen avance (avance mayor a 0) y no estén asociados a productos, 
        //interesados, colaboradores, calidad, riesgos o pagos
        for (Entregables entregable : cro.getEntregablesSet()) {
            if ((entregable.getEntProgreso() != null && entregable.getEntProgreso() > 0)) {
                JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable tiene avance mayor a cero.", null);
                return;
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene interesados registrados
        if (fichaTO.getInteresados() != null) {
            for (Interesados interesado : fichaTO.getInteresados()) {
                if (interesado.getIntEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un interesado.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene participantes registrados
        if (fichaTO.getParticipantes() != null) {
            for (Participantes participante : fichaTO.getParticipantes()) {
                if (participante.getPartEntregablesFk() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un participante.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene registros de calidad
        if (fichaTO.getCalidadList() != null) {
            for (Calidad calidad : fichaTO.getCalidadList()) {
                if (calidad.getCalEntFk() != null) {
                    JSFUtils.agregarMsgError(null, "No se permite la carga por formulario porque al menos un entregable está asociado a una calidad.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene riesgos registrados
        if (fichaTO.getRiesgos() != null) {
            for (Riesgos riesgo : fichaTO.getRiesgos()) {
                if (riesgo.getRiskEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un riesgo.", null);
                    return;
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene documentos registrados
        if (fichaTO.getDocumentos() != null) {
            for (Documentos documento : fichaTO.getDocumentos()) {
                if (documento.getDocsEntregable() != null) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un documento.", null);
                    return;
                } else {
                    if (documento.getDocsPagoFk() != null && documento.getDocsPagoFk().getEntregables() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un pago.", null);
                        return;
                    }
                }
            }
        }

        //Solo se permite cargar datos por formulario si el proyecto no tiene productos registrados
        Proyectos proyecto = proyectoDelegate.obtenerProyPorId(fichaTO.getFichaFk());
        if (proyecto != null) {
            List<Productos> productos = productosDelegate.obtenerProdPorProyPk(proyecto.getProyPk());
            if (productos != null) {
                for (Productos producto : productos) {
                    if (producto.getProdEntregableFk() != null) {
                        JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque al menos un entregable está asociado a un producto.", null);
                        return;
                    }
                }
            }
        }

        //Los pagos están siempre asociados a un entregable, por lo que si existe un pago no se pueden eliminar los entregables actuales
        List<AdqPagosTO> adqPagos = adquisicionDelegate.obtenerAdquisicionPagosList(fichaTO.getPreFk().getPrePk());
        if (adqPagos != null) {
            for (AdqPagosTO adqPago : adqPagos) {
                if (adqPago.getTipo() == 2) {
                    JSFUtils.agregarMsgError("ganttForm", "No se permite la carga por formulario porque hay al menos un pago asociado a entregables.", null);
                    return;
                }
            }
        }

        if (cro == null) {
            return;
        }
        entregablesCargaPorFormulario = new LinkedList();
        if (cro.getEntregablesSet() != null && !cro.getEntregablesSet().isEmpty()) {
            List<Entregables> entregables = EntregablesUtils.sortById(new LinkedList<>(cro.getEntregablesSet()));
            for (Entregables entregable : entregables) {
                EntregablesCargaPorFomulario entregableCargaFormulario = new EntregablesCargaPorFomulario();
                entregableCargaFormulario.setEsHito(entregable.getEntFinEsHito());
                entregableCargaFormulario.setEsfuerzo(entregable.getEntEsfuerzo() != null ? entregable.getEntEsfuerzo().toString() : "");
                entregableCargaFormulario.setFin(entregable.getEntFinDate());
                entregableCargaFormulario.setInicio(entregable.getEntInicioDate());
                entregableCargaFormulario.setNivel(entregable.getEntNivel() != null ? entregable.getEntNivel().toString() : "");
                entregableCargaFormulario.setNombre(entregable.getEntNombre() != null ? entregable.getEntNombre() : "");
                entregableCargaFormulario.setPredecesores(entregable.getEntPredecesorFk() != null ? entregable.getEntPredecesorFk() : "");
                entregableCargaFormulario.setProgreso(entregable.getEntProgreso() != null ? entregable.getEntProgreso().toString() : "");
                //Determinar si el entregable puede ser eliminado (luego, además, hay que verificar cada vez que ningun otro entregable dependa de él)
                boolean tieneProductos = productosDelegate.tieneProdporEnt(entregable.getEntPk());
                boolean tieneDependencias = entregablesDelegate.tieneDependencias(entregable.getEntPk());
                entregableCargaFormulario.setEliminable(!tieneDependencias && !tieneProductos);
                entregablesCargaPorFormulario.add(entregableCargaFormulario);
            }
        } else {
            entregablesCargaPorFormulario.add(new EntregablesCargaPorFomulario());
        }

        cargaPorFormulario = true;
    }

    public void cancelarCargaPorFormulario(FichaTO fichaTO) {
        cargarFrameCronograma(true, fichaTO);
        cargaPorFormulario = false;
    }

    List<EntregablesCargaPorFomulario> entregablesCargaPorFormulario = new LinkedList();

    public List<EntregablesCargaPorFomulario> getEntregablesCargaPorFormulario() {
        return entregablesCargaPorFormulario;
    }

    //Añade un entregable en la posición siguiente a la indicada
    public String agregarEntregableCargaPorFormulario(EntregablesCargaPorFomulario entregable) {
        //Los entregables se indexan a partir de 1 pero la lista en Java a partir de 0, por eso se suma 1
        Integer entId = entregablesCargaPorFormulario.indexOf(entregable) + 1;
        entregablesCargaPorFormulario.add(entId, new EntregablesCargaPorFomulario());
        //Ajustar todos los entregables: dado que todos los entregables preexistentes que tienen EntId mayor al actual aumentan su EntId en 1
        //hay que ajustar las dependencias
        for (EntregablesCargaPorFomulario entregableCargaFormulario : entregablesCargaPorFormulario) {
            if (entregableCargaFormulario.getPredecesoresLista() != null) {
                StringBuilder sDependencias = new StringBuilder("");
                for (EntregablesCargaPorFomulario.Predecesor predecesor : entregableCargaFormulario.getPredecesoresLista()) {
                    //La coma, si ya hay un predecesor incluido
                    if (sDependencias.length() > 0) {
                        sDependencias.append(",");
                    }
                    //Si el id de la dependencia es mayor al eliminado, se aumenta en 1 sino se mantiene como está
                    if (predecesor.getId() > entId) {
                        sDependencias.append(predecesor.getId() + 1);
                    } else {
                        sDependencias.append(predecesor.getId());
                    }
                    //Si hay desplazamiento en días se incluye
                    if (predecesor.getDias() != null) {
                        sDependencias.append(":").append(predecesor.getDias());
                    }
                }
                entregableCargaFormulario.setPredecesores(sDependencias.toString());
            }
        }
        return null;
    }

    //Añade un entregable al final
    public void agregarEntregableCargaPorFormulario() {
        entregablesCargaPorFormulario.add(new EntregablesCargaPorFomulario());
    }

    //Quita un entregable en la posición indicada
    public String quitarEntregableCargaPorFormulario(EntregablesCargaPorFomulario entregable) {
        //Primero verificar si el entregable puede ser eliminado por sí mismo
        if (!entregable.isEliminable()) {
            JSFUtils.agregarMsgError("ficha:tblCargaPorFormulario", "No es posible eliminar el entregable. Es un entregable padre o es usado en reg. de horas, productos, pagos, colaboradores, riesgos, calidad o interesados.", null);
            return null;
        }
        //Los entregables se indexan a partir de 1 pero la lista en Java a partir de 0, por eso se suma 1
        int entId = entregablesCargaPorFormulario.indexOf(entregable) + 1;
        //Si pasó las validaciones se puede quitar el entregable, ajustando los demás entregables
        //Dado que todos los entregables preexistentes que tienen EntId mayor al actual disminuyen su EntId en 1 hay que ajustar las dependencias
        for (EntregablesCargaPorFomulario entregableCargaFormulario : entregablesCargaPorFormulario) {
            if (entregableCargaFormulario.getPredecesoresLista() != null) {
                StringBuilder sDependencias = new StringBuilder("");
                for (EntregablesCargaPorFomulario.Predecesor predecesor : entregableCargaFormulario.getPredecesoresLista()) {
                    //Si el id de la dependencia es igual al eliminado, se ignora (se quita la dependencia)
                    if (predecesor.getId() == entId) {
                        continue;
                    }          //La coma, si ya hay un predecesor incluido
                    if (sDependencias.length() > 0) {
                        sDependencias.append(",");
                    }
                    //Si el id de la dependencia es mayor al eliminado, se reduce en 1 sino se mantiene como está
                    if (predecesor.getId() > entId) {
                        sDependencias.append(predecesor.getId() - 1);
                    } else {
                        sDependencias.append(predecesor.getId());
                    }
                    //Si hay desplazamiento en días se incluye
                    if (predecesor.getDias() != null) {
                        sDependencias.append(":").append(predecesor.getDias());
                    }
                }
                //Volver a poner las depedencias (vuelve a calcular la lista)
                entregableCargaFormulario.setPredecesores(sDependencias.toString());
            }
        }
        //La eliminación hay que hacerla después de ajustar las dependencias para no modificar los identificadores antes de terminar los cálculos
        entregablesCargaPorFormulario.remove(entregable);
        return null;
    }

    public Object importarEntregablesPorFormulario(FichaTO fichaTO) {

        List<Entregables> entregables = new LinkedList();
        int nroFila = 0;
        for (EntregablesCargaPorFomulario entregableCargaPorFormulario : entregablesCargaPorFormulario) {
            nroFila++;
            Entregables entregable = new Entregables();
            entregable.setEntId(nroFila);
            entregable.setEntNombre(entregableCargaPorFormulario.getNombre());
            try {
                entregable.setEntNivel((Double.valueOf(entregableCargaPorFormulario.getNivel())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'nivel' en la fila " + nroFila + " no es correcto");
            }
            try {
                entregable.setEntEsfuerzo((Double.valueOf(entregableCargaPorFormulario.getEsfuerzo())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'esfuerzo' en la fila " + nroFila + " no es correcto");
            }
            try {
                entregable.setEntProgreso((Double.valueOf(entregableCargaPorFormulario.getProgreso())).intValue());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'progreso' en la fila " + nroFila + " no es correcto", ex);
            }
            try {
                //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                entregable.setEntInicio(entregableCargaPorFormulario.getInicio().getTime() + 12 * 60 * 60 * 1000);
            } catch (Exception ex) {
                throw new BusinessException("El campo 'fecha de inicio' en la fila " + nroFila + " no es correcto");
            }
            try {
                //A la fecha obtenida de la planilla se le suma 12 horas para que quede al mediodía
                entregable.setEntFin(entregableCargaPorFormulario.getFin().getTime() + 12 * 60 * 60 * 1000);
            } catch (Exception ex) {
                throw new BusinessException("El campo 'fecha de fin' en la fila " + nroFila + " no es correcto");
            }
            //Columna 9: es hito
            try {
                entregable.setEntInicioEsHito(Boolean.FALSE);
                entregable.setEntFinEsHito(entregableCargaPorFormulario.getEsHito());
            } catch (Exception ex) {
                throw new BusinessException("El campo 'es hito' en la fila " + nroFila + " no es correcto");
            }
            //Columna 10: predecesores
            try {
                if (entregableCargaPorFormulario.getPredecesores() != null && !entregableCargaPorFormulario.getPredecesores().trim().isEmpty()) {
                    entregable.setEntPredecesorFk(entregableCargaPorFormulario.getPredecesores());
                    entregable.setEntPredecesorDias(0);
                }
                if (entregable.getEntPredecesorFk() != null && !entregable.getEntPredecesorFk().isEmpty()
                        && !entregable.getEntPredecesorFk().matches(EntregablesValidacion.DEPENDENCIA_REGEX)) {
                    throw new BusinessException("El campo 'dependencia' en la fila " + nroFila + " no es correcto");
                }

            } catch (Exception ex) {
                throw new BusinessException("El campo 'predecesores' en la fila " + nroFila + " no es correcto");
            }
            //Columna 12: coordinador
            entregable.setCoordinadorUsuFk(inicioMB.getUsuario());
            //Otros campos que no se toman del formulario
            entregable.setEntStatus("STATUS_ACTIVE");
            entregable.setEntCollapsed(Boolean.FALSE);
            entregable.setEntInicioProyecto(Boolean.FALSE);
            entregable.setEntFinProyecto(Boolean.FALSE);
            //Si es un hito se pone como fecha de inicio la misma que la fecha de fin
            if (entregable.getEntFinEsHito()) {
                entregable.setEntInicio(entregable.getEntFin());
            }
            entregables.add(entregable);
            //Queda pendiente lo siguiente porque dependen de otros entregables (hay que hacerlo después de cargar todos los entregables):
            //1-Ajustar las fechas de inicio y fin, y las fechas de inicio y fin de la línea base
            //2-Completar los campos entParent, entDuracion, entDuracionLineaBase
        }
        //Ajustar las fechas de los entregables en base a los hijos y las dependencias
        entregables = EntregablesUtils.ajustarFechas(entregables);
        //Validar los datos y las fechas de los entregables
        EntregablesValidacion.validarEntregables(entregables);
        //Si llegó hasta acá se importaron y validaron todos los entregables
        fichaTO.getCroFk().getEntregablesSet().clear();
        for (Entregables entregable : entregables) {
            entregable.setEntCroFk(fichaTO.getCroFk());
            fichaTO.getCroFk().getEntregablesSet().add(entregable);
        }
        //Guardar el cronograma con todos los entregables
        return cronogramaDelegate.guardarCronograma(fichaTO.getCroFk(), fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

    }

    // ======================================================================================================================
    // Carga masiva de tareas del Gantt desde archivo
    public void cerrarPlantillaCroPopup(FichaTO fichaTO) {
        renderPopupPlantillaCro = false;
        plantillaCro = null;
        plantillaFechaInicio = null;

        /*
        *   07-06-2018 Nico: Se agrega este control para poder cargar el template desde el 
        *        programa cuando se abre por primera vez el popup de plantilla cronograma y 
        *        luego se le da cerrar.
         */
        if (primeraCargarGantt) {
            cargarFrameCronograma(true, fichaTO);
            primeraCargarGantt = false;
        }

    }

    public Object generarCroDesdePlantilla(FichaTO fichaTO) {

        return plantillaCronogramaDelegate.generarCroDesdePlantilla(fichaTO.getFichaFk(), plantillaCro, plantillaFechaInicio, inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

    }

    public void cargarGanttSiEsPrimera(FichaTO fichaTO) {
        if (primeraCargarGantt) {
            if (recienCreado) {
                cargarFrameCronograma(false, fichaTO);
            } else {
                cargarFrameCronograma(true, fichaTO);
            }
        } else {
            cargarFrameCronograma(false, fichaTO);
        }
        primeraCargarGantt = false;

        renderPopupPlantillaCro = false;
        JSFUtils.agregarMsg("ganttForm", "info_cronograma_guardado", null);
    }

    public String plantillaCroPopup() {
        renderPopupPlantillaCro = true;
        plantillaCroList = plantillaCronogramaDelegate.buscarPorFiltro(null, inicioMB.getOrganismo().getOrgPk());
        if (CollectionsUtils.isNotEmpty(plantillaCroList)) {
            plantillaCroListCombo = new SofisCombo((List) plantillaCroList, "pcronoNombre");
            plantillaCroListCombo.setSelectedObject(plantillaCroList.get(0));
        }
        return null;
    }

    public Object guardarCronograma(FichaTO fichaTO) throws Exception {

        JSONObject json = new JSONObject(dataCron);

        LOGGER.log(Level.INFO, "Entrando a Guardar Cronograma en modulo");
        CronogramaTO cronogramaTO = CronogramaUtils.jsonToCronograma(json);
//                            Cronogramas cro = jsonToCronograma(json);
        Cronogramas cro = com.sofis.business.utils.CronogramaUtils.convert(cronogramaTO);

        cro.setCroPermisoEscrituraPadre(false);

        HashSet<Integer> setEntregablesString = new HashSet();
        for (EntregableTO e : cronogramaTO.getEntregables()) {
            if (e.getId() != null) {
                setEntregablesString.add(e.getId());
            }

        }

        for (EntregableTO e : cronogramaTO.getEntregables()) {
            Entregables entregable = EntregablesUtils.convert(e);

            if (entregable.getEsReferencia() && e.getReferido() != null) {

                if (!setEntregablesString.contains(e.getReferido().getId())) {
//					entregable.setEntDescripcion(null);
                    entregable.setEntDuracion(null);
                    entregable.setEntInicioEsHito(null);
//					entregable.setEntFinEsHito(null);
                    entregable.setEntRelevante(null);
                    entregable.setEntInicioLineaBase(null);
                    entregable.setEntFinLineaBase(null);
                    entregable.setEntDuracionLineaBase(null);
                    entregable.setEntInicioProyecto(false);
                    entregable.setEntFinProyecto(false);
                    entregable.setEntHorasEstimadas(null);
                    entregable.setCoordinadorUsuFk(null);

                    if (e.getReferido() != null) {
                        Entregables referido = EntregablesUtils.convert(e.getReferido());
                        entregable.setReferido(referido);
                        entregable.setCoordinadorUsuFk(new SsUsuario(e.getCoordinador().getId()));
                    }

                } else {
                    JSFUtils.agregarMsgError("ganttForm", Labels.getValue("crono_error_entregable_referencia_crear_idem_proyecto"), null);
                    cargarFrameCronograma(false, fichaTO);
                    return null;
                }
            } else {
                if (e.getCoordinador() != null) {
                    entregable.setCoordinadorUsuFk(new SsUsuario(e.getCoordinador().getId()));
                }
            }

            entregable.setEntCroFk(cro);
            cro.getEntregablesSet().add(entregable);
        }

        Integer countLevel0 = 0;
        for (Entregables e : cro.getEntregablesSet()) {
            if (e.getEntNivel() == 0) {
                countLevel0++;
            }
        }

        if (countLevel0 > 1) {
            JSFUtils.agregarMsgError("ganttForm", Labels.getValue("error_cro_guardar_ent_level_0_mult"), null);
            cargarFrameCronograma(false, fichaTO);
            return null;
        }

        Entregables e;
        Object[] eSet = cro.getEntregablesSet().toArray();
        for (int i = 0; i < cro.getEntregablesSet().size(); i++) {
            e = (Entregables) eSet[i];
            if (i < cro.getEntregablesSet().size() - 2) {
                if (e.getEntFinEsHito()) {
                    for (Entregables e2 : cro.getEntregablesSet()) {
                        if (e2.getEntId() == e.getEntId() + 1) {
                            if (e2.getEntNivel() > e.getEntNivel()) {
                                JSFUtils.agregarMsgError("ganttForm", Labels.getValue("error_cro_guardar_ent_hito_padre"), null);
                                cargarFrameCronograma(false, fichaTO);
                                return null;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }

        cro.setEntregablesSet(new HashSet<>(EntregablesUtils.ajustarFechas(cro.getEntregablesSet())));

        return cronogramaDelegate.guardarCronograma(cro, fichaTO.getFichaFk(), fichaTO.getTipoFicha(), inicioMB.getUsuario(), inicioMB.getOrganismo().getOrgPk());

//                            if (progProy == null) {
//                                    JSFUtils.agregarMsgError("ganttForm", "error_cro_guardar", null);
//                            } else {
//                                    fichaMB.actualizarFichaTO(progProy);
//
//                                    if (fichaMB.alAbrirEnTodaLaPantalla && inicioMB.isAlHacerConsultaHistorico()) {
//                                            cargarFrameCronograma(true, fichaTO);
//
//                                            inicioMB.setAlHacerConsultaHistorico(false);
//                                            fichaMB.alAbrirEnTodaLaPantalla = false;
//                                    } else {
//                                            cargarFrameCronograma(false,fichaTO);
//                                    }
//
//                                    JSFUtils.agregarMsg("ganttForm", "info_cronograma_guardado", null);
//                            }
//
//                            JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "setUnchangeGantt();");
    }

    /**
     * Convierte un objeto JSONObject en un Cronogramas
     *
     * @param j
     * @return Un objeto Cronogramas.
     */
    private Cronogramas jsonToCronograma(JSONObject j) {
        if (j != null) {
            try {
                Cronogramas c = new Cronogramas();
                if (j.has(ConstantesPresentacion.CRONO_PK)) {
                    c.setCroPk(j.getInt(ConstantesPresentacion.CRONO_PK));
                } else {
                }
                if (j.has(ConstantesPresentacion.CRONO_SELECTED_ROW)) {
                    c.setCroEntSeleccionado(j.getInt(ConstantesPresentacion.CRONO_SELECTED_ROW));
                }
                if (j.has(ConstantesPresentacion.CRONO_CAN_WRITE)) {
                    c.setCroPermisoEscritura(true);
                }
                if (j.has(ConstantesPresentacion.CRONO_CAN_WRITE_PARENT)) {
                    //                    c.setCroPermisoEscrituraPadre(j.getBoolean(ConstantesPresentacion.CRONO_CAN_WRITE_PARENT));
                    c.setCroPermisoEscrituraPadre(true);
                }
                if (j.has(ConstantesPresentacion.CRONO_DELETED_TASK_ID)) {
                    c.setCroEntBorrados(j.getString(ConstantesPresentacion.CRONO_DELETED_TASK_ID));
                }

                c.setEntregablesSet(jsonToEntregables(j));
                //                c.setEntregablesToDeleteSet(jsonToEntregablesDeleted(j));
                return c;

            } catch (Exception w) {
                LOGGER.log(Level.SEVERE, w.getMessage(), w);
                JSFUtils.agregarMsg("ganttForm", "error_cro_guardar", null);
            }
        }

        return null;
    }

    /**
     * Convierte el atributo task del JSONObject en un set de Entregables.
     *
     * @param j
     * @return Set de Entregables
     */
    private Set<Entregables> jsonToEntregables(JSONObject j) throws Exception {
        if (j != null) {
            try {
                Set<Entregables> setEnt = new HashSet<Entregables>();
                JSONArray tasks = j.getJSONArray(ConstantesPresentacion.TASKS);

                for (int i = 0; i < tasks.length(); i++) {
                    JSONObject task = tasks.getJSONObject(i);
                    Entregables e = new Entregables();

                    if (task.has(ConstantesPresentacion.CRONO_PK)) {
                        Cronogramas c = new Cronogramas(j.getInt(ConstantesPresentacion.CRONO_PK));
                        e.setEntCroFk(c);
                    }
                    //                    if (task.get(TASK_ID) instanceof Integer) {
                    //                        e.setEntId(task.getInt(TASK_ID));
                    //                    }
                    e.setEntId(i + 1);
                    //                    e.setEntAssigs(null);
                    if (task.has(ConstantesPresentacion.TASK_CODE)) {
                        e.setEntCodigo(task.getString(ConstantesPresentacion.TASK_CODE));
                    }
                    if (task.has(ConstantesPresentacion.TASK_COORDINADOR)) {
                        Object coordId = task.get(ConstantesPresentacion.TASK_COORDINADOR);
                        if (coordId instanceof Integer) {
                            e.setCoordinadorUsuFk(new SsUsuario(task.getInt(ConstantesPresentacion.TASK_COORDINADOR)));
                        }
                    }
                    if (task.has(ConstantesPresentacion.TASK_COLLAPSED)) {
                        e.setEntCollapsed(task.getBoolean(ConstantesPresentacion.TASK_COLLAPSED));
                    }
                    if (task.has(ConstantesPresentacion.TASK_DESCRIPTION)) {
                        e.setEntDescripcion(task.getString(ConstantesPresentacion.TASK_DESCRIPTION));
                    }
                    if (task.has(ConstantesPresentacion.TASK_PARENT)) {
                        e.setEntParent(task.getBoolean(ConstantesPresentacion.TASK_PARENT));
                    }
                    if (task.has(ConstantesPresentacion.TASK_DURATION)) {
                        e.setEntDuracion(task.getInt(ConstantesPresentacion.TASK_DURATION));
                    }
                    if (task.has(ConstantesPresentacion.TASK_DURACION_LINEA_BASE)) {
                        e.setEntDuracionLineaBase(task.getInt(ConstantesPresentacion.TASK_DURACION_LINEA_BASE));
                    }
                    e.setEntEsfuerzo(task.has(ConstantesPresentacion.TASK_ESFUERZO) ? task.getInt(ConstantesPresentacion.TASK_ESFUERZO) : 0);
                    if (task.has(ConstantesPresentacion.TASK_END)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_END);
                        e.setEntFin(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_END_LINEA_BASE)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_END_LINEA_BASE);
                        e.setEntFinLineaBase(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_START)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_START);
                        e.setEntInicio(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_START_LINEA_BASE)) {
                        Long l = task.getLong(ConstantesPresentacion.TASK_START_LINEA_BASE);
                        e.setEntInicioLineaBase(l != null && l > 0 ? l : null);
                    }
                    if (task.has(ConstantesPresentacion.TASK_HORAS_ESTIMADAS)) {
                        String he = task.getString(ConstantesPresentacion.TASK_HORAS_ESTIMADAS);
                        e.setEntHorasEstimadas(he);
                    }
                    e.setEntNivel(task.has(ConstantesPresentacion.TASK_LEVEL) ? task.getInt(ConstantesPresentacion.TASK_LEVEL) : 0);
                    if (task.has(ConstantesPresentacion.TASK_NAME)) {
                        e.setEntNombre(task.getString(ConstantesPresentacion.TASK_NAME));
                    }
                    if (task.has(ConstantesPresentacion.TASK_PK)) {
                        e.setEntPk(task.getInt(ConstantesPresentacion.TASK_PK));
                    }

                    if (task.has(ConstantesPresentacion.TASK_DEPENDS)) {
                        String[] pred = task.getString(ConstantesPresentacion.TASK_DEPENDS).split(":");
                        try {
                            e.setEntPredecesorFk(task.getString(ConstantesPresentacion.TASK_DEPENDS));
                            e.setEntPredecesorDias(pred.length >= 2 ? Integer.valueOf(pred[1]) : 0);
                        } catch (NumberFormatException numberFormatException) {
                            numberFormatException.printStackTrace();
                        }
                    }
                    if (task.has(ConstantesPresentacion.TASK_PROGRESS)) {
                        e.setEntProgreso(task.getInt(ConstantesPresentacion.TASK_PROGRESS));
                    }

                    if (task.has(ConstantesPresentacion.TASK_STATUS)) {
                        e.setEntStatus(task.getString(ConstantesPresentacion.TASK_STATUS));
                    }

                    if (task.has(ConstantesPresentacion.TASK_RELEVANTE)) {
                        e.setEntRelevante(task.getBoolean(ConstantesPresentacion.TASK_RELEVANTE));
                    }

                    if (task.has(ConstantesPresentacion.TASK_INICIO_PROYECTO)) {
                        e.setEntInicioProyecto(task.getBoolean(ConstantesPresentacion.TASK_INICIO_PROYECTO));
                    }

                    if (task.has(ConstantesPresentacion.TASK_FIN_PROYECTO)) {
                        e.setEntFinProyecto(task.getBoolean(ConstantesPresentacion.TASK_FIN_PROYECTO));
                    }
                    if (task.has(ConstantesPresentacion.TASK_END_IS_MILESTONE)) {
                        e.setEntFinEsHito(task.getBoolean(ConstantesPresentacion.TASK_END_IS_MILESTONE));
                        /**
                         * [BRUNO] 30-05-17: seteo el inicio igual al fin a
                         * prepo cuando es hito.
                         */
                        if (e.getEntFinEsHito()) {
                            e.setEntInicio(e.getEntFin());
                            e.setEntInicioLineaBase(e.getEntFinLineaBase());
                        }
                    }
                    if (task.has(ConstantesPresentacion.TASK_START_IS_MILESTONE)) {
                        e.setEntInicioEsHito(task.getBoolean(ConstantesPresentacion.TASK_START_IS_MILESTONE));

                    }

                    setEnt.add(e);
                }

                /**
                 * [BRUNO] 30-05-17: TODO corrijo fechas de los entregables 1-
                 * Si la fecha de inicio es mayor a la de fin 2- Si la fecha de
                 * fin del que depende es mayor a la fecha de inicio
                 */
                return setEnt;

            } catch (Exception w) {
                LOGGER.log(Level.SEVERE, "Error al convertir jsonToEntregables");
                throw w;
            }
        }

        return null;
    }

    // Esta operación es utilizada para poder cheaquer se existen inconsistencias en el Cronograma
    public void checkDatosEntregablesCronograma(FichaTO fichaTO) {
        if (fichaTO.getCroFk() != null
                && fichaTO.getCroFk().getEntregablesSet() != null
                && !fichaTO.getCroFk().getEntregablesSet().isEmpty()) {

            //hash auxiliar para controlar la dependecias circulares
            Map<Integer, Boolean> visitados = new HashMap<Integer, Boolean>();

            // Variable utilizada para poder corroborar si existe algún entregable de nivel 0
            boolean cronoNivelesCorrecto = false;

            for (Entregables iterEnt : fichaTO.getCroFk().getEntregablesSet()) {
                // utilizo el for para poder cargar el hash auxiliar para controlar dependencias circulares
                visitados.put(iterEnt.getEntId(), Boolean.FALSE);

                // Si existe un entregable de nivel 0, el cronograma no presenta problemas, por lo que la variable queda en "true"
                if (iterEnt.getEntNivel() == 0) {
                    cronoNivelesCorrecto = true;
                }
            }

            if (!cronoNivelesCorrecto) {
                // Corrijo los niveles de los entregables en el Cronograma
                for (Entregables iterEnt : fichaTO.getCroFk().getEntregablesSet()) {
                    iterEnt.setEntNivel(iterEnt.getEntNivel() - 1);
                }
            }
        }
    }

    public void irAProyecto() {
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        inicioMB.irEditarProgramaProyecto("2-" + id, true);
    }

    public boolean isRecienCreado() {
        return recienCreado;
    }

    public void setRecienCreado(boolean recienCreado) {
        this.recienCreado = recienCreado;
    }

    public SofisCombo getPlantillaCroListCombo() {
        return plantillaCroListCombo;
    }

    public void setPlantillaCroListCombo(SofisCombo plantillaCroListCombo) {
        this.plantillaCroListCombo = plantillaCroListCombo;
    }

    public Date getPlantillaFechaInicio() {
        return plantillaFechaInicio;
    }

    public void setPlantillaFechaInicio(Date plantillaFechaInicio) {
        this.plantillaFechaInicio = plantillaFechaInicio;
    }

    public List<PlantillaCronograma> getPlantillaCroList() {
        return plantillaCroList;
    }

    public void setPlantillaCroList(List<PlantillaCronograma> plantillaCroList) {
        this.plantillaCroList = plantillaCroList;
    }

    public PlantillaCronograma getPlantillaCro() {
        return plantillaCro;
    }

    public void setPlantillaCro(PlantillaCronograma plantillaCro) {
        this.plantillaCro = plantillaCro;
    }

    public boolean isRenderPopupPlantillaCro() {
        return renderPopupPlantillaCro;
    }

    public void setRenderPopupPlantillaCro(boolean renderPopupPlantillaCro) {
        this.renderPopupPlantillaCro = renderPopupPlantillaCro;
    }

    public int[] getIndiceAvanceProd() {
        return indiceAvanceProd;
    }

    public void setIndiceAvanceProd(int[] indiceAvanceProd) {
        this.indiceAvanceProd = indiceAvanceProd;
    }

    public int[] getIndiceAvanceTiempo() {
        return indiceAvanceTiempo;
    }

    public void setIndiceAvanceTiempo(int[] indiceAvanceTiempo) {
        this.indiceAvanceTiempo = indiceAvanceTiempo;
    }

    public Map<Integer, Boolean> getEntregablesEditablesPorUsuario() {
        return entregablesEditablesPorUsuario;
    }

    public void setEntregablesEditablesPorUsuario(Map<Integer, Boolean> entregablesEditablesPorUsuario) {
        this.entregablesEditablesPorUsuario = entregablesEditablesPorUsuario;
    }

    public List<Entregables> getListaEntregables() {
        return listaEntregables;
    }

    public void setListaEntregables(List<Entregables> listaEntregables) {
        this.listaEntregables = listaEntregables;
    }

    public int[] getIndiceAvanceFinalizado() {
        return indiceAvanceFinalizado;
    }

    public void setIndiceAvanceFinalizado(int[] indiceAvanceFinalizado) {
        this.indiceAvanceFinalizado = indiceAvanceFinalizado;
    }

    public int[] getIndiceAvanceParcial() {
        return indiceAvanceParcial;
    }

    public void setIndiceAvanceParcial(int[] indiceAvanceParcial) {
        this.indiceAvanceParcial = indiceAvanceParcial;
    }

    public Cronogramas getCronograma() {
        return cronograma;
    }

    public void setCronograma(Cronogramas cronograma) {
        this.cronograma = cronograma;
    }

    public String getDataCron() {
        return dataCron;
    }

    public void setDataCron(String dataCron) {
        this.dataCron = dataCron;
    }

    public List<Entregables> getListaEntregablesResumen() {
        return listaEntregablesResumen;
    }

    public void setListaEntregablesResumen(List<Entregables> listaEntregablesResumen) {
        this.listaEntregablesResumen = listaEntregablesResumen;
    }

    public InicioMB getInicioMB() {
        return inicioMB;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public boolean isCargaDesdeArchivo() {
        return cargaDesdeArchivo;
    }

    public void setCargaDesdeArchivo(boolean cargaDesdeArchivo) {
        this.cargaDesdeArchivo = cargaDesdeArchivo;
    }

    public boolean isMostrarBotonEntregableReferencia() {
        return mostrarBotonEntregableReferencia;
    }

    public void setMostrarBotonEntregableReferencia(boolean mostrarBotonEntregableReferencia) {
        this.mostrarBotonEntregableReferencia = mostrarBotonEntregableReferencia;
    }

    public Integer getProyectoCodigoFiltro() {
        return proyectoCodigoFiltro;
    }

    public void setProyectoCodigoFiltro(Integer proyectoCodigoFiltro) {
        this.proyectoCodigoFiltro = proyectoCodigoFiltro;
    }

    public String getProyectoNombreFiltro() {
        return proyectoNombreFiltro;
    }

    public void setProyectoNombreFiltro(String proyectoNombreFiltro) {
        this.proyectoNombreFiltro = proyectoNombreFiltro;
    }

    public String getEntregableNombreFiltro() {
        return entregableNombreFiltro;
    }

    public void setEntregableNombreFiltro(String entregableNombreFiltro) {
        this.entregableNombreFiltro = entregableNombreFiltro;
    }

    public Boolean getRemplazarEntrgable() {
        return remplazarEntrgable != null ? remplazarEntrgable : false;
    }

    public void setRemplazarEntrgable(Boolean remplazarEntrgable) {
        this.remplazarEntrgable = remplazarEntrgable;
    }

    public Boolean validarEntregableReferencia() {
        if (entregablesCombo.getSelectedObject() != null && proyectosCombo.getSelectedObject() != null) {
            Proyectos proyActual = ((Proyectos) proyectosCombo.getSelectedObject());

            Boolean result = cronograma.getProyecto().getProyEstFk() != proyActual.getProyEstFk();

            EntregableTO entregable = EntregablesUtils.convert((Entregables) entregablesCombo.getSelectedObject());
            for (Entregables e : cronograma.getEntregablesSet()) {
                if (e.getEntId() == entregable.getId()) {
                    result = false;
                    break;
                }
            }
            return result;
        }
        return false;
    }

    private void cambiarProyectoPorId(Integer proyectoCodigoSeleccionado) {

        this.proyectoCodigoSeleccionado = proyectoCodigoSeleccionado;
        List<Entregables> entregables = new ArrayList<>();
        if (this.proyectoCodigoSeleccionado > 0) {
            entregables = entregablesDelegate.obtenerEntSinReferenciaPorProyPk(this.proyectoCodigoSeleccionado);

            entregables = EntregablesUtils.cargarNivelStr(entregablesDelegate.obtenerEntSinReferenciaPorProyPk(this.proyectoCodigoSeleccionado));

        } else if (proyectoCodigoSeleccionado == -1) {
            cargarProyectos();
        }
        entregablesCombo = new SofisCombo((List) entregables, null);
        entregablesCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

    }

    public void limpiarFiltroEntregables() {

        proyectoCodigoFiltro = 0;
        proyectoNombreFiltro = "";
        entregableNombreFiltro = "";
        proyectoCodigoSeleccionado = -1;
        remplazarEntrgable = null;
        cambiarProyectoPorId(proyectoCodigoSeleccionado);
        cargarProyectos();

    }

    public void overriteFiltro() {

        proyectoCodigoSeleccionado = -1;
        cambiarProyectoPorId(proyectoCodigoSeleccionado);
        cargarProyectos();

    }

    public void cargarEntregables() {
        List<Entregables> entregables = new ArrayList<>();

        List<Entregables> entregablesAux = new ArrayList<>();
        if (cronograma != null && cronograma.getProyecto() != null) {
            entregablesAux = entregablesDelegate.obtenerEntSinReferenciaPorProyPk(cronograma.getProyecto().getProyPk());

            for (Entregables e : entregablesAux) {
                if (e != null && e.getEntParent() != null && !e.getEntParent() && e.getEntProductosSet() != null && e.getEntProductosSet().isEmpty() && e.getEntProgreso() == 0) {
                    entregables.add(e);
                }

            }
        }

        entregablesProyectoCombo = new SofisCombo((List) entregables, "entNombre");
        entregablesProyectoCombo.addEmptyItem(Labels.getValue("comboEmptyItem"));

    }

    public SofisCombo getEntregablesProyectoCombo() {
        return entregablesProyectoCombo;
    }

    public void setEntregablesProyectoCombo(SofisCombo entregablesProyectoCombo) {
        this.entregablesProyectoCombo = entregablesProyectoCombo;
    }

    public Boolean getFiltroRender() {
        return filtroRender != null ? filtroRender : false;
    }

    public void setFiltroRender(Boolean filtroRender) {
        this.filtroRender = filtroRender;
    }

    public void renderizarFiltro() {
        filtroRender = filtroRender == null || filtroRender == false;
    }

}
