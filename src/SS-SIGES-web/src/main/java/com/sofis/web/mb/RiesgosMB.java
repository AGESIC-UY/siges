/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sofis.web.mb;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.sofis.entities.codigueras.SsRolCodigos;
import com.sofis.entities.data.Areas;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.data.TipoRiesgo;
import com.sofis.entities.enums.EstadoRiesgo;
import com.sofis.entities.tipos.FiltroRiesgosTO;
import com.sofis.entities.tipos.RiesgosTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.web.delegates.AreasDelegate;
import com.sofis.web.delegates.RiesgosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.delegates.TipoRiesgoDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.JSFUtils;
import com.sofis.web.utils.RepeatPaginator;
import com.sofis.web.utils.SofisComboG;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

/**
 *
 * @author developer
 */
@ManagedBean(name = "riesgosMB")
@ViewScoped
public class RiesgosMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(RiesgosMB.class.getName());

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;

    @ManagedProperty("#{aplicacionMB}")
    private AplicacionMB aplicacionMB;

    @Inject
    private RiesgosDelegate riesgosDelegate;
    
    @Inject
    private TipoRiesgoDelegate tipoRiesgoDelegate;

    @Inject
    private AreasDelegate areasDelegate;

    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;

    private List<RiesgosTO> riesgosTOList;
    private List<SelectItem> listaEstadosProyectoItem;
    private List<Integer> estadosSeleccionados = new ArrayList<>();
    private SofisComboG<SsUsuario> gerenteAdjuntoComboFiltro;
    private SofisComboG<SsUsuario> pmofComboFiltro;
    private SofisComboG<TipoRiesgo> tipoRiesgoCombo;
    private SofisComboG<Areas> areaFiltroCombo;
    private RepeatPaginator paginator;
    private boolean mostrarFiltro = Boolean.FALSE;
    private FiltroRiesgosTO filtro;
    private List<SelectItem> estadosItems;

    @PostConstruct
    public void init() {

        if (filtro == null) {
            filtro = new FiltroRiesgosTO();
        }
        inicializarFiltroFase();
        inicializarFiltroEstado();
        filtro.setOrgPk(inicioMB.getOrganismo().getOrgPk());
        cargarCombosFiltro();
    }

    private void inicializarFiltroEstado() {
        estadosItems = new ArrayList<>();
        estadosItems.add(new SelectItem(EstadoRiesgo.ACTIVO.estado_id, Labels.getValue("estado_Activo")));
        estadosItems.add(new SelectItem(EstadoRiesgo.ATRASADO.estado_id, Labels.getValue("estado_Atrasado")));
        estadosItems.add(new SelectItem(EstadoRiesgo.SUPERADO.estado_id, Labels.getValue("estado_Superado")));

        filtro.setEstados(new Integer[]{
            EstadoRiesgo.ACTIVO.estado_id,
            EstadoRiesgo.ATRASADO.estado_id
        });
    }

    public String buscar() {

        cargarCombosSeleccionados();
        LOGGER.severe("despues de cargar " + filtro.getNombreProyecto());
        riesgosTOList = riesgosDelegate.buscar(filtro);
        paginator = new RepeatPaginator(riesgosTOList);
        return null;

    }

    public String exportarAction() {
        HSSFWorkbook planilla = new HSSFWorkbook();
        HSSFSheet hoja = planilla.createSheet("reporte_riesgos");

        HSSFPalette palette = planilla.getCustomPalette();
        palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 3, (byte) 94, (byte) 159);
        HSSFColor colorAzul = palette.getColor(HSSFColor.BLUE.index);

        // Fuente blanca negrita
        HSSFFont fontWhiteBold = planilla.createFont();
        fontWhiteBold.setColor(HSSFColor.WHITE.index);
        fontWhiteBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // Fuente negra negrita
        HSSFFont fontBlackBold = planilla.createFont();
        fontBlackBold.setColor(HSSFColor.BLACK.index);
        fontBlackBold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // Estilo para títulos
        HSSFCellStyle estiloTitulo = planilla.createCellStyle();
        estiloTitulo.setFont(fontBlackBold);
        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // Estilo de área (color azul)
        HSSFCellStyle areaStyle = planilla.createCellStyle();
        areaStyle.setFillForegroundColor(colorAzul.getIndex());
        areaStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        areaStyle.setFont(fontWhiteBold);

        // Estilo de filtro
        HSSFCellStyle filtroStyle = planilla.createCellStyle();
        filtroStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        filtroStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        // Obtener datos
        List<RiesgosTO> riesgos = this.riesgosTOList;

        // Encabezados
        String[] titulos = new String[]{
            "Proyecto", "Nombre Proyecto", "Nombre Riesgo", "Fecha Act.",
            "Tipo Riesgo", "Exposición", "Estado", "Responsables",
            "Fecha Límite", "Observaciones"
        };

        // Escribir encabezado
        HSSFRow filaTitulo = hoja.createRow(0);
        for (int i = 0; i < titulos.length; i++) {
            HSSFCell celda = filaTitulo.createCell(i);
            celda.setCellValue(titulos[i]);
            celda.setCellStyle(estiloTitulo);
        }

        // Escribir datos
        int filaActual = 1;
        for (RiesgosTO r : riesgos) {
            HSSFRow fila = hoja.createRow(filaActual++);

            int col = 0;

            fila.createCell(col++).setCellValue(r.getProyecto() != null ? r.getProyecto().toString() : "");
            fila.createCell(col++).setCellValue(r.getNombreProyecto() != null ? r.getNombreProyecto() : "");
            fila.createCell(col++).setCellValue(r.getNombreRiesgo() != null ? r.getNombreRiesgo() : "");
            fila.createCell(col++).setCellValue(r.getFechaAct() != null ? new SimpleDateFormat("dd/MM/yyyy").format(r.getFechaAct()) : "");
            fila.createCell(col++).setCellValue(r.getTipoRiesgo() != null ? r.getTipoRiesgo() : "");
            fila.createCell(col++).setCellValue(r.getExposicion() != null ? r.getExposicion().toString() : "");
            fila.createCell(col++).setCellValue(r.getEstado() != null ? r.getEstado() : "");
            fila.createCell(col++).setCellValue(r.getResponsable() != null ? r.getResponsable() : "");
            fila.createCell(col++).setCellValue(r.getFechaLimite() != null ? new SimpleDateFormat("dd/MM/yyyy").format(r.getFechaLimite()) : "");
            fila.createCell(col++).setCellValue(r.getObservaciones() != null ? r.getObservaciones() : "");
        }

        // Autoajustar ancho
        for (int i = 0; i < titulos.length; i++) {
            hoja.autoSizeColumn(i);
        }

        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            planilla.write(bos);
            bytes = bos.toByteArray();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            JSFUtils.agregarMsgError("No se pudo generar la planilla");
            return null;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        }

        if (bytes != null && bytes.length > 0) {
            Calendar cal = new GregorianCalendar();
            String horaString = String.format("%d_%d_%d_%d_%d_%d",
                    cal.get(Calendar.DAY_OF_MONTH),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    cal.get(Calendar.SECOND)
            );

            String fileName = "Planilla_" + horaString + ".xls";

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.setAttribute("bytes", bytes);
                session.setAttribute("fileName", fileName);

                HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                String servlet = req.getContextPath() + "/servlet/descargar?sesId=" + session.getId();

                JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(), "window.open('" + servlet + "','Descargar');");
            }
        } else {
            JSFUtils.agregarMsgWarn("No se ha generado la planilla");
        }

        return null;
    }
    
    public void limpiarFiltro() {
        gerenteAdjuntoComboFiltro.setSelected(-1);
        pmofComboFiltro.setSelected(-1);
        tipoRiesgoCombo.setSelected(-1);
        areaFiltroCombo.setSelected(-1);
        
    }

    public void mostrarFiltroAction() {

        mostrarFiltro = !mostrarFiltro;
        LOGGER.severe("mostrando filtro" + mostrarFiltro);
    }

    private void cargarAreas(Integer orgPk) {
        List<Areas> areas = areasDelegate.obtenerAreasPorOrganismo(orgPk, false);
        areaFiltroCombo = new SofisComboG<>(areas, "areaNombre");
        areaFiltroCombo.addEmptyItem(Labels.getValue("comboTodas"));
    }

    private void cargarCombosFiltro() {
        Integer orgPk = inicioMB.getOrganismo().getOrgPk();
        cargarGerenteAdjunto(orgPk);

        cargarPmoF(orgPk);

        cargarAreas(orgPk);
        
 
        cargarComboTipoRiesgo();
    }

    private void cargarComboTipoRiesgo() {
        List<TipoRiesgo> tipoRiesgos = tipoRiesgoDelegate.busquedaTipoRiesgoFiltro(inicioMB.getOrganismo().getOrgPk(), null, "trsNombre", 1);
        tipoRiesgoCombo = new SofisComboG<>(tipoRiesgos, "trsNombre");
        tipoRiesgoCombo.addEmptyItem(Labels.getValue("comboTodas"));
    }

    private void inicializarFiltroFase() {
        listaEstadosProyectoItem = new ArrayList<SelectItem>();
        listaEstadosProyectoItem.add(new SelectItem(Estados.ESTADOS.INICIO.estado_id, Labels.getValue("estado_Inicio")));
        listaEstadosProyectoItem.add(new SelectItem(Estados.ESTADOS.PLANIFICACION.estado_id, Labels.getValue("estado_Planificacion")));
        listaEstadosProyectoItem.add(new SelectItem(Estados.ESTADOS.EJECUCION.estado_id, Labels.getValue("estado_Ejecucion")));
        listaEstadosProyectoItem.add(new SelectItem(Estados.ESTADOS.FINALIZADO.estado_id, Labels.getValue("estado_Finalizado")));
        listaEstadosProyectoItem.add(new SelectItem(Estados.ESTADOS.PENDIENTE.estado_id, Labels.getValue("estado_pendiente")));

        filtro.setEstadosProyecto(new Integer[]{
            Estados.ESTADOS.INICIO.estado_id,
            Estados.ESTADOS.PLANIFICACION.estado_id,
            Estados.ESTADOS.EJECUCION.estado_id,
            Estados.ESTADOS.PENDIENTE.estado_id
        });
    }

    private void cargarCombosSeleccionados() {

        if (pmofComboFiltro.getSelectedT() != null) {
            filtro.setIdPmof(pmofComboFiltro.getSelectedT().getUsuId());
        } else {
            filtro.setIdPmof(null);
        }
        
        if (tipoRiesgoCombo.getSelectedT() != null) {
            filtro.setTipoRiesgoPk(tipoRiesgoCombo.getSelectedT().getTrsPk());
        } else {
            filtro.setTipoRiesgoPk(null);
        }

        if (gerenteAdjuntoComboFiltro.getSelectedT() != null) {
            filtro.setIdAdjunto(gerenteAdjuntoComboFiltro.getSelectedT().getUsuId());
        } else {
            filtro.setIdAdjunto(null);

        }

        if (areaFiltroCombo.getSelectedT() != null) {
            filtro.setIdArea(areaFiltroCombo.getSelectedT().getAreaPk());
        } else {
            filtro.setIdArea(null);
        }

    }

    private void cargarPmoF(Integer orgPk) {

        String[] ordenUsuarios = new String[]{"usuPrimerNombre", "usuSegundoNombre", "usuPrimerApellido", "usuSegundoApellido"};
        boolean[] ascUsuarios = new boolean[]{true, true, true, true};

        String[] rolCodArr = new String[]{SsRolCodigos.PMO_FEDERADA, SsRolCodigos.PMO_TRANSVERSAL};
        List<SsUsuario> pmofs = ssUsuarioDelegate.obtenerUsuariosPorRol(rolCodArr, orgPk, ordenUsuarios, ascUsuarios);
        pmofs = SsUsuariosUtils.sortByNombreApellido(pmofs);

        pmofComboFiltro = new SofisComboG<>(pmofs, "usuNombreApellido");
        pmofComboFiltro.addEmptyItem(Labels.getValue("comboTodos"));
    }

    private void cargarGerenteAdjunto(Integer orgPk) {
        List<SsUsuario> usuarios = new ArrayList<>(aplicacionMB.obtenerTodosPorOrganismoActivos(orgPk));
        usuarios.addAll(ssUsuarioDelegate.obtenerInactivosPorOrganismo(orgPk));

        usuarios = SsUsuariosUtils.sortByNombreApellido(usuarios);

        gerenteAdjuntoComboFiltro = new SofisComboG<>(usuarios, "usuNombreApellido");
        gerenteAdjuntoComboFiltro.addEmptyItem(Labels.getValue("comboTodos"));
    }

    public RiesgosDelegate getRiesgosDelegate() {
        return riesgosDelegate;
    }

    public void setRiesgosDelegate(RiesgosDelegate riesgosDelegate) {
        this.riesgosDelegate = riesgosDelegate;
    }

    public List<RiesgosTO> getRiesgosTOList() {
        return riesgosTOList;
    }

    public void setRiesgosTOList(List<RiesgosTO> riesgosTOList) {
        this.riesgosTOList = riesgosTOList;
    }

    public RepeatPaginator getPaginator() {
        return paginator;
    }

    public void setPaginator(RepeatPaginator paginator) {
        this.paginator = paginator;
    }

    public InicioMB getInicioMB() {
        return inicioMB;
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public AplicacionMB getAplicacionMB() {
        return aplicacionMB;
    }

    public void setAplicacionMB(AplicacionMB aplicacionMB) {
        this.aplicacionMB = aplicacionMB;
    }

    public boolean isMostrarFiltro() {
        return mostrarFiltro;
    }

    public void setMostrarFiltro(boolean mostrarFiltro) {
        this.mostrarFiltro = mostrarFiltro;
    }

    public FiltroRiesgosTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroRiesgosTO filtro) {
        this.filtro = filtro;
    }

    public SofisComboG<SsUsuario> getGerenteAdjuntoComboFiltro() {
        return gerenteAdjuntoComboFiltro;
    }

    public void setGerenteAdjuntoComboFiltro(SofisComboG<SsUsuario> gerenteAdjuntoComboFiltro) {
        this.gerenteAdjuntoComboFiltro = gerenteAdjuntoComboFiltro;
    }

    public AreasDelegate getAreasDelegate() {
        return areasDelegate;
    }

    public void setAreasDelegate(AreasDelegate areasDelegate) {
        this.areasDelegate = areasDelegate;
    }

    public List<SelectItem> getEstadosItems() {
        return estadosItems;
    }

    public void setEstadosItems(List<SelectItem> estadosItems) {
        this.estadosItems = estadosItems;
    }

    public SsUsuarioDelegate getSsUsuarioDelegate() {
        return ssUsuarioDelegate;
    }

    public void setSsUsuarioDelegate(SsUsuarioDelegate ssUsuarioDelegate) {
        this.ssUsuarioDelegate = ssUsuarioDelegate;
    }

    public SofisComboG<SsUsuario> getPmofComboFiltro() {
        return pmofComboFiltro;
    }

    public void setPmofComboFiltro(SofisComboG<SsUsuario> pmofComboFiltro) {
        this.pmofComboFiltro = pmofComboFiltro;
    }

    public SofisComboG<Areas> getAreaFiltroCombo() {
        return areaFiltroCombo;
    }

    public void setAreaFiltroCombo(SofisComboG<Areas> areaFiltroCombo) {
        this.areaFiltroCombo = areaFiltroCombo;
    }

    public List<Integer> getEstadosSeleccionados() {
        return estadosSeleccionados;
    }

    public void setEstadosSeleccionados(List<Integer> estadosSeleccionados) {
        this.estadosSeleccionados = estadosSeleccionados;
    }

    public List<SelectItem> getListaEstadosProyectoItem() {
        return listaEstadosProyectoItem;
    }

    public void setListaEstadosProyectoItem(List<SelectItem> listaEstadosProyectoItem) {
        this.listaEstadosProyectoItem = listaEstadosProyectoItem;
    }

    public SofisComboG<TipoRiesgo> getTipoRiesgoCombo() {
        return tipoRiesgoCombo;
    }

    public void setTipoRiesgoCombo(SofisComboG<TipoRiesgo> tipoRiesgoCombo) {
        this.tipoRiesgoCombo = tipoRiesgoCombo;
    }

}
