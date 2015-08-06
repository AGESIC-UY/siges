package com.sofis.business.ejbs;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.Proyectos;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.enums.ReporteCroAlcColumnasEnum;
import com.sofis.entities.tipos.FiltroReporteTO;
import com.sofis.entities.tipos.ReporteAcumuladoMesTO;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "ReporteCronoAlcanceBean")
@LocalBean
public class ReporteCronoAlcanceBean {

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    @Inject
    private EstadosBean estadosBean;
    @Inject
    private CronogramasBean cronogramasBean;
    @Inject
    private ReportesBean reportesBean;
    @Inject
    private EntregablesBean entregablesBean;

    public byte[] generarReportePlanillaPorFiltro(Integer orgPk, FiltroReporteTO filtro, SsUsuario usuario) {

        String hojaName = LabelsEJB.getValue("rep_cro_alc_xls_hoja_alcance");

        //Proyectos obtenidos por medio del filtro.
        List<Proyectos> listProy = reportesBean.buscarProyectosPorFiltro(filtro, orgPk, usuario);

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
        for (int i = 0; i <= ReporteCroAlcColumnasEnum.DICIEMBRE.ordinal(); i++) {
            HSSFCell celdaTit = rowTitulo.createCell(i);
            celdaTit.setCellStyle(cellStyleTitle);
        }

        HSSFCell celdaTitulo = rowTitulo.createCell(0);
        celdaTitulo.setCellValue(StringsUtils.concat(LabelsEJB.getValue("rep_cro_alc_xls_titulo"), ": ", hojaName));
        celdaTitulo.setCellStyle(cellStyleTitle);

        HSSFCell celdaAnio = rowTitulo.createCell(ReporteCroAlcColumnasEnum.ENERO.ordinal());
        celdaAnio.setCellValue(StringsUtils.concat(LabelsEJB.getValue("rep_cro_alc_xls_anio"), ": ", filtro.getAnio().toString()));
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

        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.ID_PROG.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_prog_id"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.PROG.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_prog"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.ID_PROY.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_proy_id"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.PROY.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_proy"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.AREA.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_area"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.GERENTE.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_gerente"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.ESTADO.ordinal(), LabelsEJB.getValue("rep_cro_alc_xls_col_estado"), cellStyleColTitulos);
        HSSFCellUtil.createCell(rowColTitulos, ReporteCroAlcColumnasEnum.TIPO_LINEA.ordinal(), "", cellStyleColTitulos);
        for (int mes = 1; mes <= 12; mes++) {
            String mesName = StringsUtils.concat("date_mes_abreviado_", String.valueOf(mes));
            HSSFCellUtil.createCell(rowColTitulos, obtenerCeldaOrdinalMes(mes), LabelsEJB.getValue(mesName), cellStyleColTitulos);
        }

        //Fila detalles
        for (Proyectos proy : listProy) {
            Set<Entregables> listEnt = proy.getProyCroFk() != null ? proy.getProyCroFk().getEntregablesSet() : null;

            if (CollectionsUtils.isNotEmpty(listEnt)) {

                Map<String, ReporteAcumuladoMesTO> valoresCroMes = entregablesBean.obtenerAcumuladoMapMes(new ArrayList<Entregables>(listEnt));

                //0-Planificado, 1-Real, 2-Proyectado
                for (int i = 0; i < 3; i++) {
                    HSSFCellStyle cellStyleAlc = planilla.createCellStyle();
                    if (i == 2) {
                        cellStyleAlc.setBorderBottom(CellStyle.BORDER_THIN);
                    }
                    HSSFRow rowMon = hoja.createRow(++filaNro);
                    columnasProyecto(rowMon, proy, cellStyleAlc);

                    celda = rowMon.createCell(ReporteCroAlcColumnasEnum.TIPO_LINEA.ordinal());
                    celda.setCellValue(nombreTipoLinea(i + 1));
                    celda.setCellStyle(cellStyleAlc);

                    ReporteAcumuladoMesTO valorMes = null;
                    for (int mes = 1; mes <= 12; mes++) {
                        if (valoresCroMes != null) {
                            String clave = mes + "-" + anio;
                            valorMes = valoresCroMes.get(clave);
                        }
                        if (valorMes == null) {
                            valorMes = new ReporteAcumuladoMesTO();
                        }

                        celda = rowMon.createCell(obtenerCeldaOrdinalMes(mes));
                        celda.setCellStyle(cellStyleAlc);

                        Double valorTipo = 0D;
                        switch (i) {
                            case 0:
                                valorTipo = valorMes.getValorPlan();
                                break;
                            case 1:
                                valorTipo = valorMes.getValorReal();
                                break;
                            case 2:
                                valorTipo = valorMes.getValorProyectado();
                                break;
                        }
                        celda.setCellValue(Math.round(valorTipo));
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
        celda = row.createCell(ReporteCroAlcColumnasEnum.ID_PROG.ordinal());
        celda.setCellStyle(cellStyle);
        String progPk = proy.getProyProgFk() != null ? proy.getProyProgFk().getProgPk().toString() : "";
        celda.setCellValue(progPk);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReporteCroAlcColumnasEnum.PROG.ordinal());
        String progName = proy.getProyProgFk() != null ? proy.getProyProgFk().getProgNombre() : "";
        celda.setCellValue(progName);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReporteCroAlcColumnasEnum.ID_PROY.ordinal());
        celda.setCellValue(proy.getProyPk());
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReporteCroAlcColumnasEnum.PROY.ordinal());
        celda.setCellValue(proy.getProyNombre());
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReporteCroAlcColumnasEnum.AREA.ordinal());
        String area = proy.getProyAreaFk() != null ? proy.getProyAreaFk().getAreaNombre() : "";
        celda.setCellValue(area);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReporteCroAlcColumnasEnum.GERENTE.ordinal());
        String gerente = proy.getProyUsrGerenteFk() != null ? proy.getProyUsrGerenteFk().getUsuNombreApellido() : "";
        celda.setCellValue(gerente);
        celda.setCellStyle(cellStyle);
        celda = row.createCell(ReporteCroAlcColumnasEnum.ESTADO.ordinal());
        celda.setCellValue(estadosBean.estadoStr(proy.getProyEstFk()));
        celda.setCellStyle(cellStyle);
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
                return LabelsEJB.getValue("rep_cro_alc_tipo_base");
            case 2:
                return LabelsEJB.getValue("rep_cro_alc_tipo_real");
            case 3:
                return LabelsEJB.getValue("rep_cro_alc_tipo_proyectado");
            default:
                return "";
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
                return ReporteCroAlcColumnasEnum.ENERO.ordinal();
            case 2:
                return ReporteCroAlcColumnasEnum.FEBRERO.ordinal();
            case 3:
                return ReporteCroAlcColumnasEnum.MARZO.ordinal();
            case 4:
                return ReporteCroAlcColumnasEnum.ABRIL.ordinal();
            case 5:
                return ReporteCroAlcColumnasEnum.MAYO.ordinal();
            case 6:
                return ReporteCroAlcColumnasEnum.JUNIO.ordinal();
            case 7:
                return ReporteCroAlcColumnasEnum.JULIO.ordinal();
            case 8:
                return ReporteCroAlcColumnasEnum.AGOSTO.ordinal();
            case 9:
                return ReporteCroAlcColumnasEnum.SETIEMBRE.ordinal();
            case 10:
                return ReporteCroAlcColumnasEnum.OCTUBRE.ordinal();
            case 11:
                return ReporteCroAlcColumnasEnum.NOVIEMBRE.ordinal();
            case 12:
                return ReporteCroAlcColumnasEnum.DICIEMBRE.ordinal();
            default:
                return null;
        }

    }
}
