package com.sofis.web.mb;

import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.ProcedimientoCompra;
import com.sofis.entities.data.Organismos;
import com.sofis.entities.data.ProcedimientoCompraInfo;
import com.sofis.exceptions.BusinessException;
import com.sofis.web.componentes.SofisPopupUI;
import com.sofis.web.delegates.AdquisicionDelegate;
import com.sofis.web.delegates.ProcedimientoCompraDelegate;
import com.sofis.web.delegates.ProcedimientoCompraInfoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.icefaces.ace.component.fileentry.FileEntry;
import org.icefaces.ace.component.fileentry.FileEntryEvent;
import org.icefaces.ace.component.fileentry.FileEntryResults;
import org.mozilla.universalchardet.ReaderFactory;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "procedimientoCompraMB")
@ViewScoped
public class ProcedimientoCompraMB implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ProcedimientoCompraMB.class.getName());
    //private static final String BUSQUEDA_MSG = "busquedaMsg";
    private static final String POPUP_MSG = "popupMsg";
    // private static final String POPUP_MSG_MASIVA = "popupMsgMasiva";

    private static final String AREAS_NOMBRE = "procCompNombre";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private SofisPopupUI renderPopupEdicion;
    @Inject
    private ProcedimientoCompraDelegate procedimientoCompraDelegate;
    
    @Inject
    private ProcedimientoCompraInfoDelegate procedimientoCompraInfoDelegate;


    @Inject
    private AdquisicionDelegate adquisiconDelegate;
    //agregado par acarga masiva
    @Inject
    private SofisPopupUI renderPopupCargaArchivo;

    // Variables
    private String cantElementosPorPagina = "25";
    private String elementoOrdenacion = AREAS_NOMBRE;
    // 0=descendente, 1=ascendente.
    private int ascendente = 1;
    private String filtroNombre;
    private List<ProcedimientoCompra> listaResultado;
    private ProcedimientoCompra procedimientoCompraEnEdicion;

    private File exelCargaMasiva;

    public ProcedimientoCompraMB() {
    }

    // agregado para carga masiva
    public File getExelCargaMasiva() {
        return exelCargaMasiva;
    }

    public void setExelCargaMasiva(File exelCargaMasiva) {
        this.exelCargaMasiva = exelCargaMasiva;
    }

    public SofisPopupUI getRenderPopupCargaArchivo() {
        return renderPopupCargaArchivo;
    }

    public void setRenderPopupCargaArchivo(SofisPopupUI renderPopupCargaArchivo) {
        this.renderPopupCargaArchivo = renderPopupCargaArchivo;
    }
    // fin agregado para carga masiva

    public SofisPopupUI getRenderPopupEdicion() {
        return renderPopupEdicion;
    }

    public void setRenderPopupEdicion(SofisPopupUI renderPopupEdicion) {
        this.renderPopupEdicion = renderPopupEdicion;
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

    public String getFiltroNombre() {
        return filtroNombre;
    }

    public void setFiltroNombre(String filtroNombre) {
        this.filtroNombre = filtroNombre;
    }

    public List<ProcedimientoCompra> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<ProcedimientoCompra> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public ProcedimientoCompra getProcedimientoCompraEnEdicion() {
        return procedimientoCompraEnEdicion;
    }

    public void setFuenteEnEdicion(ProcedimientoCompra procedimientoCompraEnEdicion) {
        this.procedimientoCompraEnEdicion = procedimientoCompraEnEdicion;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    @PostConstruct
    public void init() {

        /*
            *   31-05-2018 Nico: Se sacan las variables que se inicializan del constructor y se pasan al PostConstruct
         */
        filtroNombre = "";
        listaResultado = new ArrayList<ProcedimientoCompra>();
        procedimientoCompraEnEdicion = new ProcedimientoCompra();

        inicioMB.cargarOrganismoSeleccionado();
        buscar();
    }

    /**
     * Action agregar.
     *
     * @return
     */
    public String agregar() {
        procedimientoCompraEnEdicion = new ProcedimientoCompra();
        renderPopupEdicion.abrir();
        return null;
    }

    //Acá va lo operación de carga masiva
    public String agregarArchivo() {
        renderPopupCargaArchivo.abrir();
        return null;
    }

    //Acá termina la operación de carga masiva
    /**
     * Action eliminar un componente-producto.
     *
     * @return
     */
    public String eliminar(Integer procedimientoCompraPk) {
        if (procedimientoCompraPk != null) {
            try {
                procedimientoCompraDelegate.eliminarProcedimientoCompra(procedimientoCompraPk);
                for (ProcedimientoCompra f : listaResultado) {
                    if (f.getProcCompPk().equals(procedimientoCompraPk)) {
                        listaResultado.remove(f);
                        break;
                    }
                }
            } catch (BusinessException e) {
                logger.log(Level.SEVERE, null, e);

                /*
                    *  18-06-2018 Inspección de código.
                 */
                //JSFUtils.agregarMsgs(BUSQUEDA_MSG, e.getErrores());
                for (String iterStr : e.getErrores()) {
                    JSFUtils.agregarMsgError("", Labels.getValue(iterStr), null);
                }

                inicioMB.setRenderPopupMensajes(Boolean.TRUE);
            }
        }
        return null;
    }

    /**
     * Action Buscar.
     *
     * @return
     */
    public String buscar() {
        Map<String, Object> mapFiltro = new HashMap<String, Object>();
        mapFiltro.put("nombre", filtroNombre);
        listaResultado = procedimientoCompraDelegate.busquedaProcedimientoCompraFiltro(inicioMB.getOrganismo().getOrgPk(), mapFiltro, elementoOrdenacion, ascendente);
        return null;
    }

    public String editar(Integer procedimientoCompraPk) {
        Organismos org = inicioMB.getOrganismo();
        try {
            procedimientoCompraEnEdicion = procedimientoCompraDelegate.obtenerProcedimientoCompraPorPk(procedimientoCompraPk);
        } catch (BusinessException ex) {
            logger.log(Level.SEVERE, null, ex);
            /*
                *  18-06-2018 Inspección de código.
             */

            //JSFUtils.agregarMsgs(POPUP_MSG, ex.getErrores());
            for (String iterStr : ex.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
        }

        renderPopupEdicion.abrir();
        return null;
    }

    public String guardar() {
        logger.log(Level.INFO, "1");
        procedimientoCompraEnEdicion.setProcCompOrgFk(inicioMB.getOrganismo());

        try {
            logger.log(Level.INFO, "2");
            
            procedimientoCompraEnEdicion = procedimientoCompraDelegate.guardarProcedimientoCompra(procedimientoCompraEnEdicion);

            if (procedimientoCompraEnEdicion != null) {
                logger.log(Level.INFO, "3");
                renderPopupEdicion.cerrar();
                buscar();
            }else{
                logger.log(Level.INFO, "3.1");
            }
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);
            
             logger.log(Level.INFO, "4");

            /*
                *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
             logger.log(Level.INFO, "5");
        }
        return null;
    }

    public void subirArchivo(FileEntryEvent event) {
        JSFUtils.removerMensages();
        exelCargaMasiva = null;
        logger.info("[subirArchivoAction] Subiendo archivo...");

        if (event != null && event.getComponent() != null) {
            FileEntry archEntry = (FileEntry) event.getComponent();
            FileEntryResults results = archEntry.getResults();
            File archivo = results.getFiles() != null ? results.getFiles().get(0).getFile() : null;
            if (archivo == null) {
                /*
                    *  18-06-2018 Inspección de código.
                 */
                //JSFUtils.agregarMsg("popupMsgMasiva", "error_archivo_subido_fail", null);
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_archivo_subido_fail_carga_masiva"), null);
                return;
            }
            try {
                if (archivo.getName().contains(".csv")) {
                    exelCargaMasiva = archivo;
                    JSFUtils.agregarMsg("popupMsgMasiva", "upload_file_carga_masiva_archivo_cargado", null);
                } else {
                    /*
                        *  18-06-2018 Inspección de código.
                     */
                    //JSFUtils.agregarMsg("popupMsgMasiva", "error_upload_file_carga_masiva_tipo_archivo", null);
                    JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva_tipo_archivo"), null);
                }
            } catch (Exception exec) {
                logger.log(Level.SEVERE, exec.getMessage(), exec);
                /*
                    *  18-06-2018 Inspección de código.
                 */
                //JSFUtils.agregarMsg("popupMsgMasiva", "error_subir_archivo", null);
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_subir_archivo"), null);
            }

        }
    }

    public String cargaMasiva() {
        List<ProcedimientoCompra> sheetData = new ArrayList();

        List<ProcedimientoCompraInfo> listaProcedimientoCompraInfo = new ArrayList();

        if (exelCargaMasiva != null) {
            try {
                CSVParser csvParser = new CSVParser(ReaderFactory.createBufferedReader(exelCargaMasiva), CSVFormat.DEFAULT);

                for (CSVRecord csvRecord : csvParser) {
                    if ((csvRecord.get(1) != null) && (csvRecord.get(2) != null) && (csvRecord.get(3) != null)) {
                        ProcedimientoCompra data = new ProcedimientoCompra();

                        try {
                            data.setProcCompPk(Integer.valueOf(csvRecord.get(0).toString()));
                        } catch (Exception exec) {
                            data.setProcCompPk(null);
                        }

                        data.setProcCompNombre(csvRecord.get(1).toString());
                        data.setProcCompDescripcion(csvRecord.get(2).toString());
                        data.setProcCompOrgFk(inicioMB.getOrganismo());

                        if (csvRecord.get(3).toString().equals("true") || csvRecord.get(3).toString().equals("True")
                                || csvRecord.get(3).toString().equals("TRUE") || csvRecord.get(3).toString().equals("1")) {
                            data.setProcCompHabilitado(Boolean.TRUE);
                        } else {
                            data.setProcCompHabilitado(Boolean.FALSE);
                        }

                        try {

                            if (csvRecord.get(4) != null) {
                                data.setProcAdquisicionId(Integer.valueOf(csvRecord.get(4).toString()));
                            }

                            if (data.getProcCompNombre() != "") {

                                List<Adquisicion> listaAdquisiciones = adquisiconDelegate.obtenerProOrganisacionYIdAdquisicion(inicioMB.getOrganismoSeleccionado(), data.getProcAdquisicionId());

                                for (Adquisicion adq : listaAdquisiciones) {
                                    ProcedimientoCompraInfo pci = new ProcedimientoCompraInfo();
                                    pci.setPciNombre(data.getProcCompNombre());
                                    pci.setPciDescripcion(data.getProcCompDescripcion());
                                    pci.setPciOrgFk(inicioMB.getOrganismo());
                                    pci.setPciAdquisicionId(data.getProcAdquisicionId());
                                    pci.setPciFecha(new Date());
                                    pci.setPciAdquisicionFK(adq);
                                    pci.setPciProyectoFK(adq.getAdqPreFk().getProyecto().getProyPk());

                                    listaProcedimientoCompraInfo.add(pci);
                                }

                            }

                        } catch (Exception exec) {

                        }

                        if (data.getProcCompNombre() != "") {
                            sheetData.add(data);
                        }

                    }
                }

                ProcedimientoCompra checkFirstElement = (ProcedimientoCompra) sheetData.get(0);
                if ((checkFirstElement.getProcCompNombre().equals("NOMBRE")) || (checkFirstElement.getProcCompNombre().equals("nombre")) || (checkFirstElement.getProcCompNombre().equals("Nombre"))) {
                    sheetData.remove(0);
                }

                procedimientoCompraDelegate.cargaMasiva(sheetData, inicioMB.getOrganismo().getOrgPk());
                procedimientoCompraInfoDelegate.cargaMasiva(listaProcedimientoCompraInfo);

                JSFUtils.agregarMsg(null, "upload_file_carga_masiva_exitosa", null);
                this.exelCargaMasiva = null;
                renderPopupCargaArchivo.cerrar();
                buscar();
            } catch (Exception exec) {
                //Este catch me lo pidio para cuando crea el FileInputStream
                logger.log(Level.SEVERE, exec.getMessage(), exec);
                JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_carga_masiva_proc_comp"), null);
            }
        } else {
            /*
                *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsg("popupMsgMasiva", "error_upload_file_carga_masiva", null);
            JSFUtils.agregarMsgError("popupMsgMasiva", Labels.getValue("error_upload_file_carga_masiva"), null);
        }

        return null;
    }

    public void actualizarHabilitado(ProcedimientoCompra procCompParam) {
        try {
            procCompParam.setProcCompHabilitado(!(procCompParam.getProcCompHabilitado()));
            procedimientoCompraDelegate.guardarProcedimientoCompra(procCompParam);
        } catch (BusinessException be) {
            logger.log(Level.SEVERE, be.getMessage(), be);

            /*
                *  18-06-2018 Inspección de código.
             */
            //JSFUtils.agregarMsgs(BUSQUEDA_MSG, be.getErrores());
            for (String iterStr : be.getErrores()) {
                JSFUtils.agregarMsgError(POPUP_MSG, Labels.getValue(iterStr), null);
            }
        }
    }

    public void cancelar() {
        if (renderPopupEdicion.isRenderPopup()) {
            renderPopupEdicion.cerrar();
        }
        if (renderPopupCargaArchivo.isRenderPopup()) {
            this.exelCargaMasiva = null;
            renderPopupCargaArchivo.cerrar();
        }
    }

    /**
     * Action limpiar formulario de busqueda.
     *
     * @return
     */
    public String limpiar() {
        filtroNombre = null;
        listaResultado = null;
        elementoOrdenacion = AREAS_NOMBRE;
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
}
