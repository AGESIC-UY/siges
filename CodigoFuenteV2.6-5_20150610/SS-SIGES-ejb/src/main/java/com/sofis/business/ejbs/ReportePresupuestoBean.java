package com.sofis.business.ejbs;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Adquisicion;
import com.sofis.entities.data.Devengado;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.Presupuesto;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.ReportePreColumnasEnum;
import com.sofis.entities.tipos.FiltroReporteTO;
import com.sofis.entities.tipos.ReporteAcumuladoMesTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.jboss.ejb3.annotation.TransactionTimeout;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ReportePresupuestoBean")
@LocalBean
public class ReportePresupuestoBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private EstadosBean estadosBean;
    @Inject
    private AdquisicionBean adquisicionBean;
    @Inject
    private PresupuestoBean presupuestoBean;
    @Inject
    private MonedaBean monedaBean;
    @Inject
    private RegistrosHorasBean registrosHorasBean;
    @Inject
    private GastosBean gastosBean;
    @Inject
    private DevengadoBean devengadoBean;
    @Inject
    private ReportesBean reportesBean;

    @TransactionTimeout(value = 60, unit = TimeUnit.MINUTES)
    public byte[] generarReportePlanillaPorFiltro(Integer orgPk, FiltroReporteTO filtro, SsUsuario usuario) {

        String hojaName = "";
        if (filtro.getTipoReporte().equals(1)) {
            hojaName = LabelsEJB.getValue("rep_pre_xls_hoja_moneda");
        } else if (filtro.getTipoReporte().equals(2)) {
            hojaName = LabelsEJB.getValue("rep_pre_xls_hoja_adquisicion");
        } else {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_REPORTE_PRE_TIPO);
        }

        if (filtro.getConcepto() == null || filtro.getConcepto().isEmpty()) {
            BusinessException be = new BusinessException();
            be.addError(MensajesNegocio.ERROR_REPORTE_PRE_CONCEPTO);
        }

        //Proyectos obtenidos por medio del filtro.
        List<Proyectos> listProy = reportesBean.buscarProyectosPorFiltro(filtro, orgPk, usuario);
        logger.log(Level.INFO, "&&& Proyectos encontrados:" + listProy.size());

        int anio = filtro.getAnio() != null ? filtro.getAnio() : new GregorianCalendar().get(Calendar.YEAR);
        int filaNro = -1;

        HSSFWorkbook planilla = new HSSFWorkbook();
        HSSFSheet hoja = planilla.createSheet(hojaName);

        HSSFPalette paletteAzul = planilla.getCustomPalette();
        paletteAzul.setColorAtIndex(HSSFColor.BLUE.index, (byte) 3, (byte) 94, (byte) 159);
        HSSFColor colorAzul = paletteAzul.getColor(HSSFColor.BLUE.index);

        HSSFCellStyle cellStyleTitle = planilla.createCellStyle();
        cellStyleTitle.setFillForegroundColor(colorAzul.getIndex());
        cellStyleTitle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        HSSFFont fontTitle = planilla.createFont();
        fontTitle.setColor(HSSFColor.WHITE.index);
        fontTitle.setBold(true);
        cellStyleTitle.setFont(fontTitle);

        HSSFRow rowTitulo = hoja.createRow(++filaNro);
        for (int i = 1; i <= ReportePreColumnasEnum.TOTAL.ordinal(); i++) {
            HSSFCell celdaTit = rowTitulo.createCell(i);
            celdaTit.setCellStyle(cellStyleTitle);
        }

        HSSFCell celdaTitulo = rowTitulo.createCell(0);
        celdaTitulo.setCellValue(StringsUtils.concat(LabelsEJB.getValue("rep_pre_xls_titulo"), ": ", hojaName));
        celdaTitulo.setCellStyle(cellStyleTitle);

        HSSFCell celdaAnio = rowTitulo.createCell(ReportePreColumnasEnum.ENERO.ordinal());
        celdaAnio.setCellValue(StringsUtils.concat(LabelsEJB.getValue("rep_pre_xls_anio"), ": ", filtro.getAnio().toString()));
        celdaAnio.setCellStyle(cellStyleTitle);

        ++filaNro;
        //Fila titulos columnas
        HSSFRow rowColTitulos = hoja.createRow(++filaNro);
        HSSFCell celda;

        HSSFCellStyle cellStyleColTitulos = planilla.createCellStyle();
        cellStyleColTitulos.setBorderTop(CellStyle.BORDER_THIN);
        cellStyleColTitulos.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyleColTitulos.setFillForegroundColor(colorAzul.getIndex());
        cellStyleColTitulos.setFillPattern(CellStyle.SOLID_FOREGROUND);
        HSSFFont fontColTitulos = planilla.createFont();
        fontColTitulos.setColor(HSSFColor.WHITE.index);
        fontColTitulos.setBold(true);
        cellStyleColTitulos.setFont(fontColTitulos);
        cellStyleColTitulos.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.ID_PROG.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_prog_id"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.PROG.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_prog"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.ID_PROY.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_proy_id"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.PROY.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_proy"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.AREA.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_area"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.GERENTE.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_gerente"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.ESTADO.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_estado"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.PROCEDIMIENTO.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_proc"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.PROC_GRP.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_grp"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.FUENTE.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_fuente"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.PROVEEDOR.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_prov"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.CONCEPTO.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_concepto"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.MONEDA.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_moneda"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.TIPO_LINEA.ordinal(), "", cellStyleColTitulos);
        for (int mes = 1; mes <= 12; mes++) {
            String mesName = StringsUtils.concat("date_mes_abreviado_", String.valueOf(mes));
            HSSFCellUtil.createCell(rowColTitulos, obtenerCeldaOrdinalMes(mes), LabelsEJB.getValue(mesName), cellStyleColTitulos);
        }
        HSSFCellUtil.createCell(rowColTitulos, ReportePreColumnasEnum.TOTAL.ordinal(), LabelsEJB.getValue("rep_pre_xls_col_total"), cellStyleColTitulos);

        Map<String, ReporteAcumuladoMesTO> mapTotales = null;
        Map<Integer, Moneda> totalesMonedas = new HashMap<>();

        //Fila detalles
        if (listProy != null) {
            for (Proyectos proy : listProy) {
                logger.log(Level.INFO, "&&& Proyecto:" + proy.getProyPk());
                Presupuesto pre = proy.getProyPreFk();

                //Por Adq. o Moneda
                if (filtro.isConcepto(1) && pre != null) {
                    Calendar calNow = new GregorianCalendar();

                    if (filtro.getTipoReporte().equals(1)) {
                        //Por Moneda
                        List<Moneda> listMon;
                        if (filtro.getMoneda() != null) {
                            listMon = new ArrayList<>();
                            listMon.add(filtro.getMoneda());
                        } else {
                            listMon = presupuestoBean.obtenerMonedasPresupuesto(pre.getPrePk());
                        }

                        for (Moneda mon : listMon) {
                            //0-Planificado, 1-Real, 2-Proyectado
                            for (int i = 0; i < 3; i++) {
                                HSSFCellStyle cellStyleMon = planilla.createCellStyle();
                                if (i == 2) {
                                    cellStyleMon.setBorderBottom(CellStyle.BORDER_THIN);
                                }
                                HSSFRow rowMon = hoja.createRow(++filaNro);
                                columnasProyecto(rowMon, proy, cellStyleMon);

                                celda = rowMon.createCell(ReportePreColumnasEnum.CONCEPTO.ordinal());
                                celda.setCellValue(conceptoStr(1));
                                celda.setCellStyle(cellStyleMon);
                                celda = rowMon.createCell(ReportePreColumnasEnum.MONEDA.ordinal());
                                celda.setCellValue(mon.getMonSigno());
                                celda.setCellStyle(cellStyleMon);
                                celda = rowMon.createCell(ReportePreColumnasEnum.TIPO_LINEA.ordinal());
                                celda.setCellValue(nombreTipoLinea(i + 1));
                                celda.setCellStyle(cellStyleMon);

                                double total = 0;
                                Double valorMes;
                                Double valorMesReal;
                                for (int mes = 1; mes <= 12; mes++) {
                                    if (i == 1 && (anio > calNow.get(Calendar.YEAR)
                                            || (anio == calNow.get(Calendar.YEAR) && mes > calNow.get(Calendar.MONTH) + 1))) {
                                        valorMes = 0D;
                                    } else if (i == 2 && (anio > calNow.get(Calendar.YEAR)
                                            || (anio == calNow.get(Calendar.YEAR) && mes > calNow.get(Calendar.MONTH) + 1))) {
                                        valorMes = obtenerValorPrePorMesAnio(i, pre.getPrePk(), null, mon, mes, anio);
                                        valorMesReal = obtenerValorPrePorMesAnio(i - 1, pre.getPrePk(), null, mon, calNow.get(Calendar.MONTH) + 1, calNow.get(Calendar.YEAR));
                                        valorMes += valorMesReal != null ? valorMesReal : 0D;
                                    } else {
                                        valorMes = obtenerValorPrePorMesAnio(i, pre.getPrePk(), null, mon, mes, anio);
                                    }

                                    total = valorMes != null ? valorMes : total;
                                    celda = rowMon.createCell(obtenerCeldaOrdinalMes(mes));
                                    celda.setCellValue(valorMes);
                                    celda.setCellStyle(cellStyleMon);

                                    mapTotales = sumarTotalesFactura(mapTotales, mon.getMonPk(), i, mes, anio, valorMes);
                                }

                                celda = rowMon.createCell(ReportePreColumnasEnum.TOTAL.ordinal());
                                celda.setCellValue(total);
                                celda.setCellStyle(cellStyleMon);

                                mapTotales = sumarTotalesFactura(mapTotales, mon.getMonPk(), i, 13, anio, total);
                                totalesMonedas.put(mon.getMonPk(), mon);
                            }
                        }

                    } else if (filtro.getTipoReporte().equals(2)) {
                        //Por Adquisición
                        List<Adquisicion> listAdq = adquisicionBean.obtenerAdquisicionPorPre(pre.getPrePk());

                        if (listAdq != null) {
                            for (Adquisicion adq : listAdq) {
                                //0-Planificado, 1-Real, 2-Proyectado
                                for (int i = 0; i < 3; i++) {
                                    HSSFCellStyle cellStyleAdq = planilla.createCellStyle();
                                    if (i == 2) {
                                        cellStyleAdq.setBorderBottom(CellStyle.BORDER_THIN);
                                    }
                                    HSSFRow rowAdq = hoja.createRow(++filaNro);
                                    columnasProyecto(rowAdq, proy, cellStyleAdq);

                                    celda = rowAdq.createCell(ReportePreColumnasEnum.PROCEDIMIENTO.ordinal());
                                    celda.setCellValue(adq.getAdqProcCompra());
                                    celda.setCellStyle(cellStyleAdq);
                                    celda = rowAdq.createCell(ReportePreColumnasEnum.PROC_GRP.ordinal());
                                    celda.setCellValue(adq.getAdqProcCompraGrp());
                                    celda.setCellStyle(cellStyleAdq);
                                    celda = rowAdq.createCell(ReportePreColumnasEnum.FUENTE.ordinal());
                                    celda.setCellValue(adq.getAdqFuente() != null ? adq.getAdqFuente().getFueNombre() : "");
                                    celda.setCellStyle(cellStyleAdq);
                                    celda = rowAdq.createCell(ReportePreColumnasEnum.PROVEEDOR.ordinal());
                                    celda.setCellValue(adq.getAdqProvOrga() != null ? adq.getAdqProvOrga().getOrgaNombre() : "");
                                    celda.setCellStyle(cellStyleAdq);
                                    celda = rowAdq.createCell(ReportePreColumnasEnum.CONCEPTO.ordinal());
                                    celda.setCellValue(conceptoStr(1));
                                    celda.setCellStyle(cellStyleAdq);
                                    celda = rowAdq.createCell(ReportePreColumnasEnum.MONEDA.ordinal());
                                    celda.setCellValue(adq.getAdqMoneda().getMonSigno());
                                    celda.setCellStyle(cellStyleAdq);
                                    celda = rowAdq.createCell(ReportePreColumnasEnum.TIPO_LINEA.ordinal());
                                    celda.setCellValue(nombreTipoLinea(i + 1));
                                    celda.setCellStyle(cellStyleAdq);

                                    double total = 0;
                                    Double valorMes;
                                    Double valorMesReal;
                                    for (int mes = 1; mes <= 12; mes++) {
                                        if (i == 1 && (anio > calNow.get(Calendar.YEAR)
                                                || (anio == calNow.get(Calendar.YEAR) && mes > calNow.get(Calendar.MONTH) + 1))) {
                                            valorMes = 0D;
                                        } else if (i == 2 && (anio > calNow.get(Calendar.YEAR)
                                                || (anio == calNow.get(Calendar.YEAR) && mes > calNow.get(Calendar.MONTH) + 1))) {
                                            valorMes = obtenerValorPrePorMesAnio(i, pre.getPrePk(), adq.getAdqPk(), adq.getAdqMoneda(), mes, anio);
                                            valorMesReal = obtenerValorPrePorMesAnio(i - 1, pre.getPrePk(), adq.getAdqPk(), adq.getAdqMoneda(), calNow.get(Calendar.MONTH) + 1, calNow.get(Calendar.YEAR));
                                            valorMes += valorMesReal != null ? valorMesReal : 0D;
                                        } else {
                                            valorMes = obtenerValorPrePorMesAnio(i, pre.getPrePk(), adq.getAdqPk(), adq.getAdqMoneda(), mes, anio);
                                        }

                                        total = valorMes != null ? valorMes : total;
                                        celda = rowAdq.createCell(obtenerCeldaOrdinalMes(mes));
                                        celda.setCellValue(valorMes);
                                        celda.setCellStyle(cellStyleAdq);

                                        mapTotales = sumarTotalesFactura(mapTotales, adq.getAdqMoneda().getMonPk(), i, mes, anio, valorMes);
                                    }

                                    celda = rowAdq.createCell(ReportePreColumnasEnum.TOTAL.ordinal());
                                    celda.setCellValue(total);
                                    celda.setCellStyle(cellStyleAdq);

                                    mapTotales = sumarTotalesFactura(mapTotales, adq.getAdqMoneda().getMonPk(), i, 13, anio, total);
                                    totalesMonedas.put(adq.getAdqMoneda().getMonPk(), adq.getAdqMoneda());
                                }
                            }
                        }
                    }
                }

                //Devengado
                if (filtro.isConcepto(2) && pre!=null) {
                    List<Adquisicion> listAdq;
                    if (filtro.getMoneda() != null) {
                        listAdq = adquisicionBean.obtenerAdquisicionPorPre(pre.getPrePk(), filtro.getMoneda().getMonPk());
                    } else {
                        listAdq = adquisicionBean.obtenerAdquisicionPorPre(pre.getPrePk());
                    }

                    if (listAdq != null) {
                        for (Adquisicion adq : listAdq) {
                            //0-Planificado, 1-Real
                            for (int i = 0; i < 2; i++) {
                                HSSFCellStyle cellStyleDev = planilla.createCellStyle();
                                if (i == 1) {
                                    cellStyleDev.setBorderBottom(CellStyle.BORDER_THIN);
                                }
                                HSSFRow rowDev = hoja.createRow(++filaNro);
                                columnasProyecto(rowDev, proy, cellStyleDev);

                                celda = rowDev.createCell(ReportePreColumnasEnum.PROCEDIMIENTO.ordinal());
                                celda.setCellValue(adq.getAdqProcCompra());
                                celda.setCellStyle(cellStyleDev);
                                celda = rowDev.createCell(ReportePreColumnasEnum.PROC_GRP.ordinal());
                                celda.setCellValue(adq.getAdqProcCompraGrp());
                                celda.setCellStyle(cellStyleDev);
                                celda = rowDev.createCell(ReportePreColumnasEnum.FUENTE.ordinal());
                                celda.setCellValue(adq.getAdqFuente() != null ? adq.getAdqFuente().getFueNombre() : "");
                                celda.setCellStyle(cellStyleDev);
                                celda = rowDev.createCell(ReportePreColumnasEnum.PROVEEDOR.ordinal());
                                celda.setCellValue(adq.getAdqProvOrga() != null ? adq.getAdqProvOrga().getOrgaNombre() : "");
                                celda.setCellStyle(cellStyleDev);
                                celda = rowDev.createCell(ReportePreColumnasEnum.CONCEPTO.ordinal());
                                celda.setCellValue(conceptoStr(4));
                                celda.setCellStyle(cellStyleDev);
                                celda = rowDev.createCell(ReportePreColumnasEnum.MONEDA.ordinal());
                                celda.setCellValue(adq.getAdqMoneda().getMonSigno());
                                celda.setCellStyle(cellStyleDev);
                                celda = rowDev.createCell(ReportePreColumnasEnum.TIPO_LINEA.ordinal());
                                celda.setCellValue(nombreTipoLinea(i + 1));
                                celda.setCellStyle(cellStyleDev);

                                double total = 0;
                                for (int mes = 1; mes <= 12; mes++) {
                                    Devengado dev = devengadoBean.obtenerDevengado(adq.getAdqPk(), (short) mes, (short) anio);
                                    Double valorMes = 0D;
                                    if (dev != null) {
                                        valorMes = i == 0 ? dev.getDevPlan() : dev.getDevReal();
                                    }
                                    total = valorMes != null ? valorMes : total;
                                    celda = rowDev.createCell(obtenerCeldaOrdinalMes(mes));
                                    celda.setCellValue(valorMes != null ? valorMes : 0D);
                                    celda.setCellStyle(cellStyleDev);
                                }

                                celda = rowDev.createCell(ReportePreColumnasEnum.TOTAL.ordinal());
                                celda.setCellValue(total);
                                celda.setCellStyle(cellStyleDev);
                            }
                        }
                    }
                }

                //Horas
                if (filtro.isConcepto(3)) {
                    List<Moneda> listMon;
                    if (filtro.getMoneda() != null) {
                        listMon = new ArrayList<>();
                        listMon.add(filtro.getMoneda());
                    } else {
                        listMon = monedaBean.obtenerMonedas();
                    }

                    int lastMon = listMon != null ? listMon.size() : 0;
                    for (Moneda mon : listMon) {
                        --lastMon;
                        HSSFCellStyle cellStyleHora = planilla.createCellStyle();
                        if (lastMon == 0) {
                            cellStyleHora.setBorderBottom(CellStyle.BORDER_THIN);
                        }
                        HSSFRow rowHora = hoja.createRow(++filaNro);
                        columnasProyecto(rowHora, proy, cellStyleHora);

                        celda = rowHora.createCell(ReportePreColumnasEnum.CONCEPTO.ordinal());
                        celda.setCellValue(conceptoStr(2));
                        celda.setCellStyle(cellStyleHora);
                        celda = rowHora.createCell(ReportePreColumnasEnum.MONEDA.ordinal());
                        celda.setCellValue(mon.getMonSigno());
                        celda.setCellStyle(cellStyleHora);
                        celda = rowHora.createCell(ReportePreColumnasEnum.TIPO_LINEA.ordinal());
                        celda.setCellValue(LabelsEJB.getValue("rep_pre_tipo_aprobado"));
                        celda.setCellStyle(cellStyleHora);

                        double total = 0;
                        for (int mes = 1; mes <= 12; mes++) {
                            Double valorMes = registrosHorasBean.obtenerImporteTotalHsAprob(proy.getProyPk(), mon.getMonPk(), mes, anio, null);
                            total = valorMes != null ? valorMes : total;
                            celda = rowHora.createCell(obtenerCeldaOrdinalMes(mes));
                            celda.setCellValue(valorMes != null ? valorMes : 0D);
                            celda.setCellStyle(cellStyleHora);
                        }

                        celda = rowHora.createCell(ReportePreColumnasEnum.TOTAL.ordinal());
                        celda.setCellValue(total);
                        celda.setCellStyle(cellStyleHora);
                    }
                }

                //Gastos
                if (filtro.isConcepto(4)) {
                    List<Moneda> listMon;
                    if (filtro.getMoneda() != null) {
                        listMon = new ArrayList<>();
                        listMon.add(filtro.getMoneda());
                    } else {
                        listMon = monedaBean.obtenerMonedas();
                    }

                    int lastMon = listMon != null ? listMon.size() : 0;
                    for (Moneda mon : listMon) {
                        --lastMon;
                        HSSFCellStyle cellStyleGasto = planilla.createCellStyle();
                        if (lastMon == 0) {
                            cellStyleGasto.setBorderBottom(CellStyle.BORDER_THIN);
                        }
                        HSSFRow rowGasto = hoja.createRow(++filaNro);
                        columnasProyecto(rowGasto, proy, cellStyleGasto);

                        celda = rowGasto.createCell(ReportePreColumnasEnum.CONCEPTO.ordinal());
                        celda.setCellValue(conceptoStr(3));
                        celda.setCellStyle(cellStyleGasto);
                        celda = rowGasto.createCell(ReportePreColumnasEnum.MONEDA.ordinal());
                        celda.setCellValue(mon.getMonSigno());
                        celda.setCellStyle(cellStyleGasto);
                        celda = rowGasto.createCell(ReportePreColumnasEnum.TIPO_LINEA.ordinal());
                        celda.setCellValue(LabelsEJB.getValue("rep_pre_tipo_aprobado"));
                        celda.setCellStyle(cellStyleGasto);

                        double total = 0;
                        for (int mes = 1; mes <= 12; mes++) {
                            Double valorMes = gastosBean.obtenerImpTotalGastosPorProy(proy.getProyPk(), mon.getMonPk(), mes, anio, Boolean.TRUE);
                            total = valorMes != null ? valorMes : total;
                            celda = rowGasto.createCell(obtenerCeldaOrdinalMes(mes));
                            celda.setCellValue(valorMes != null ? valorMes : 0D);
                            celda.setCellStyle(cellStyleGasto);
                        }

                        celda = rowGasto.createCell(ReportePreColumnasEnum.TOTAL.ordinal());
                        celda.setCellValue(total);
                        celda.setCellStyle(cellStyleGasto);
                    }
                }
            }
        }

        // Totales
        if (mapTotales != null && mapTotales.size() > 0) {

            ++filaNro;
            for (Integer monPk : totalesMonedas.keySet()) {
                Moneda mon = monedaBean.obtenerMonedaPorId(monPk);

                for (int i = 0; i <= 2; i++) {
                    HSSFCellStyle cellStyleMon = planilla.createCellStyle();
                    if (i == 2) {
                        cellStyleMon.setBorderBottom(CellStyle.BORDER_THIN);
                    }
                    HSSFRow rowMon = hoja.createRow(++filaNro);
                    celda = rowMon.createCell(ReportePreColumnasEnum.CONCEPTO.ordinal());
                    celda.setCellValue(conceptoStr(0));
                    celda.setCellStyle(cellStyleMon);
                    celda = rowMon.createCell(ReportePreColumnasEnum.MONEDA.ordinal());
                    celda.setCellValue(mon.getMonSigno());
                    celda.setCellStyle(cellStyleMon);
                    celda = rowMon.createCell(ReportePreColumnasEnum.TIPO_LINEA.ordinal());
                    celda.setCellValue(nombreTipoLinea(i + 1));
                    celda.setCellStyle(cellStyleMon);

                    ReporteAcumuladoMesTO acuMes;
                    for (int mes = 1; mes <= 13; mes++) {
                        String clave = monPk + "-" + mes + "-" + filtro.getAnio();
                        acuMes = mapTotales.get(clave);
                        celda = rowMon.createCell(obtenerCeldaOrdinalMes(mes));
                        double value = 0;
                        if (acuMes != null) {
                            if (i == 0) {
                                value = acuMes.getValorPlan() != null ? acuMes.getValorPlan() : 0;
                            } else if (i == 1) {
                                value = acuMes.getValorReal() != null ? acuMes.getValorReal() : 0;
                            } else if (i == 2) {
                                value = acuMes.getValorProyectado() != null ? acuMes.getValorProyectado() : 0;
                            }
                        }
                        celda.setCellValue(value);
                        celda.setCellStyle(cellStyleMon);
                    }
                }
            }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            planilla.write(bos);
            return bos.toByteArray();

        } catch (IOException iOException) {
            logger.log(Level.SEVERE, null, iOException);
            BusinessException be = new BusinessException();
            be.addError("Error al generar el Excel.");
            throw be;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
            BusinessException be = new BusinessException();
            be.addError("Error al generar el Excel.");
            throw be;
        }
    }

    /**
     * Carga las columnas que dependen del Proyecto.
     *
     * @param rowcellStyle@param proy
     * @param rowStyle
     */
    private void columnasProyecto(HSSFRow row, Proyectos proy, HSSFCellStyle cellStyle) {
        HSSFCell celda;
        celda = row.createCell(ReportePreColumnasEnum.ID_PROG.ordinal());
        celda.setCellStyle(cellStyle);
        String progPk = proy.getProyProgFk() != null ? proy.getProyProgFk().getProgPk().toString() : "";
        celda.setCellValue(progPk);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.PROG.ordinal());
        String progName = proy.getProyProgFk() != null ? proy.getProyProgFk().getProgNombre() : "";
        celda.setCellValue(progName);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.ID_PROY.ordinal());
        celda.setCellValue(proy.getProyPk());
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.PROY.ordinal());
        celda.setCellValue(proy.getProyNombre());
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.AREA.ordinal());
        String area = proy.getProyAreaFk() != null ? proy.getProyAreaFk().getAreaNombre() : "";
        celda.setCellValue(area);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.GERENTE.ordinal());
        String gerente = proy.getProyUsrGerenteFk() != null ? proy.getProyUsrGerenteFk().getUsuNombreApellido() : "";
        celda.setCellValue(gerente);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.ESTADO.ordinal());
        celda.setCellValue(estadosBean.estadoStr(proy.getProyEstFk()));
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.PROCEDIMIENTO.ordinal());
        celda.setCellValue("");
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.PROC_GRP.ordinal());
        celda.setCellValue("");
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.FUENTE.ordinal());
        celda.setCellValue("");
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReportePreColumnasEnum.PROVEEDOR.ordinal());
        celda.setCellValue("");
        celda.setCellStyle(cellStyle);
    }

    /**
     * Retorna el nombre de la columna Concepto. 1-Facturas, 2-Horas, 3-Gastos,
     * 4-Devengado.
     *
     * @param i
     * @return String
     */
    private String conceptoStr(int i) {
        switch (i) {
            case 0:
                return LabelsEJB.getValue("rep_pre_concepto_tot");
            case 1:
                return LabelsEJB.getValue("rep_pre_concepto_pre");
            case 2:
                return LabelsEJB.getValue("rep_pre_concepto_horas");
            case 3:
                return LabelsEJB.getValue("rep_pre_concepto_gastos");
            case 4:
                return LabelsEJB.getValue("rep_pre_concepto_devengado");
            default:
                return "";
        }
    }

    /**
     * Retorna el nombre del tipo de linea. 1-Plan, 2-Real, 3-Proyectado,
     * 4-Aprobado
     *
     * @param i
     * @return String
     */
    private String nombreTipoLinea(int i) {
        switch (i) {
            case 1:
                return LabelsEJB.getValue("rep_pre_tipo_plan");
            case 2:
                return LabelsEJB.getValue("rep_pre_tipo_real");
            case 3:
                return LabelsEJB.getValue("rep_pre_tipo_proyectado");
            default:
                return "";
        }
    }

    private Double obtenerValorPrePorMesAnio(int tipoLinea, Integer prePk, Integer adqPk, Moneda mon, int mes, int anio) {
        switch (tipoLinea) {
            case 0:
                return presupuestoBean.obtenerPVPorMonedaAcu(prePk, adqPk, mon, anio, mes);
            case 1:
                return presupuestoBean.obtenerACPorMonedaAcu(prePk, adqPk, mon, anio, mes);
            case 2:
                return presupuestoBean.obtenerPRPorMonedaAcu(prePk, adqPk, mon, anio, mes);
            default:
                return null;
        }
    }

    /**
     * Retorna la columna para el mes indicado.
     *
     * @param mes
     * @return Integer
     */
    private Integer obtenerCeldaOrdinalMes(int mes) {
        switch (mes) {
            case 1:
                return ReportePreColumnasEnum.ENERO.ordinal();
            case 2:
                return ReportePreColumnasEnum.FEBRERO.ordinal();
            case 3:
                return ReportePreColumnasEnum.MARZO.ordinal();
            case 4:
                return ReportePreColumnasEnum.ABRIL.ordinal();
            case 5:
                return ReportePreColumnasEnum.MAYO.ordinal();
            case 6:
                return ReportePreColumnasEnum.JUNIO.ordinal();
            case 7:
                return ReportePreColumnasEnum.JULIO.ordinal();
            case 8:
                return ReportePreColumnasEnum.AGOSTO.ordinal();
            case 9:
                return ReportePreColumnasEnum.SETIEMBRE.ordinal();
            case 10:
                return ReportePreColumnasEnum.OCTUBRE.ordinal();
            case 11:
                return ReportePreColumnasEnum.NOVIEMBRE.ordinal();
            case 12:
                return ReportePreColumnasEnum.DICIEMBRE.ordinal();
            case 13:
                return ReportePreColumnasEnum.TOTAL.ordinal();
            default:
                return null;
        }

    }

    private HSSFSheet generarReporteXLS(HSSFSheet hoja, Map<Integer, Map<Integer, Object>> lineas) throws Exception {

        Set<Integer> lineasKey = lineas.keySet();
        for (Integer key : lineasKey) {
            HSSFRow fila = hoja.createRow(key);
            Map<Integer, Object> linea = lineas.get(key);

            Set<Integer> keys = linea.keySet();
            for (Integer keyCol : keys) {
                HSSFCell celda = fila.createCell(keyCol);
                Object obj = linea.get(keyCol);

                if (obj instanceof Date) {
                    celda.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    celda.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    celda.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    celda.setCellValue((Double) obj);
                } else if (obj instanceof Integer) {
                    celda.setCellValue((Integer) obj);
                } else if (obj instanceof Long) {
                    celda.setCellValue((Integer) obj);
                }
            }
        }

        return hoja;
    }

    private Map<String, ReporteAcumuladoMesTO> sumarTotalesFactura(Map<String, ReporteAcumuladoMesTO> mapTotales, Integer monPk, int tipo, int mes, int anio, Double valor) {
        if (monPk != null) {
            if (mapTotales == null) {
                mapTotales = new HashMap<>();
            }

            String clave = monPk + "-" + mes + "-" + anio;
            ReporteAcumuladoMesTO acu;
            if (mapTotales.containsKey(clave)) {
                acu = mapTotales.get(clave);
            } else {
                acu = new ReporteAcumuladoMesTO((short) anio, (short) mes);
            }

            switch (tipo) {
                case 0:
                    acu.setValorPlan(acu.getValorPlan() + valor);
                    break;
                case 1:
                    acu.setValorReal(acu.getValorReal() + valor);
                    break;
                case 2:
                    acu.setValorProyectado(acu.getValorProyectado() + valor);
                    break;
            }

            mapTotales.put(clave, acu);
        }

        return mapTotales;
    }
}
