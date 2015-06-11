package com.sofis.web.mb;

import com.sofis.business.utils.EntregablesUtils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FiltroMisTareasTO;
import com.sofis.entities.tipos.MisTareasTO;
import com.sofis.web.delegates.EntregablesDelegate;
import com.sofis.web.delegates.ProgramasDelegate;
import com.sofis.web.delegates.ProyectosDelegate;
import com.sofis.web.delegates.SsUsuarioDelegate;
import com.sofis.web.properties.Labels;
import com.sofis.web.utils.SofisComboG;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "misTareasMB")
@ViewScoped
public class MisTareasMB {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);
    private static final int INICIO_REPORTE_GANTT = 5;
    private static final int FIN_REPORTE_GANTT = 665;
    private static final String MIS_TAREAS_MSG = "misTareasMsg";

    @ManagedProperty("#{inicioMB}")
    private InicioMB inicioMB;
    @Inject
    private ProgramasDelegate programasDelegate;
    @Inject
    private ProyectosDelegate proyectoDelegate;
    @Inject
    private EntregablesDelegate entregablesDelegate;
    @Inject
    private SsUsuarioDelegate ssUsuarioDelegate;

    private SofisComboG<Programas> programaCombo;
    private SofisComboG<SsUsuario> usuarioCombo;
    private SofisComboG<SelectItem> anioCombo;
    private FiltroMisTareasTO filtro = new FiltroMisTareasTO();
    private List<MisTareasTO> misTareas;

    public MisTareasMB() {
    }

    public void setInicioMB(InicioMB inicioMB) {
        this.inicioMB = inicioMB;
    }

    public SofisComboG<SelectItem> getAnioCombo() {
        return anioCombo;
    }

    public void setAnioCombo(SofisComboG<SelectItem> anioCombo) {
        this.anioCombo = anioCombo;
    }

    public FiltroMisTareasTO getFiltro() {
        return filtro;
    }

    public void setFiltro(FiltroMisTareasTO filtro) {
        this.filtro = filtro;
    }

    public List<MisTareasTO> getMisTareas() {
        return misTareas;
    }

    public void setMisTareas(List<MisTareasTO> misTareas) {
        this.misTareas = misTareas;
    }

    public SofisComboG<Programas> getProgramaCombo() {
        return programaCombo;
    }

    public void setProgramaCombo(SofisComboG<Programas> programaCombo) {
        this.programaCombo = programaCombo;
    }

    public SofisComboG<SsUsuario> getUsuarioCombo() {
        return usuarioCombo;
    }

    public void setUsuarioCombo(SofisComboG<SsUsuario> usuarioCombo) {
        this.usuarioCombo = usuarioCombo;
    }

    @PostConstruct
    public void init() {
        inicioMB.cargarOrganismoSeleccionado();

        cargarCombosFiltro();
    }

    private void cargarCombosFiltro() {

        Calendar cal = new GregorianCalendar();
        Calendar calPrimera = new GregorianCalendar();
        calPrimera.setTime(proyectoDelegate.obtenerPrimeraFecha());
        int primerAnio = calPrimera != null && calPrimera.get(Calendar.YEAR) < cal.get(Calendar.YEAR) ? calPrimera.get(Calendar.YEAR) : cal.get(Calendar.YEAR);
        Calendar calUltima = new GregorianCalendar();
        calUltima.setTime(proyectoDelegate.obtenerUltimaFecha());
        int ultimoAnio = calUltima != null && calUltima.get(Calendar.YEAR) > cal.get(Calendar.YEAR) ? calUltima.get(Calendar.YEAR) : cal.get(Calendar.YEAR);

        List<SelectItem> listaAnios = new ArrayList<>();
        SelectItem itemActual = null;
        for (int i = primerAnio; i <= ultimoAnio; i++) {
            SelectItem item = new SelectItem(i, "" + i);
            listaAnios.add(item);
            if (i == cal.get(Calendar.YEAR)) {
                itemActual = item;
            }
        }

        anioCombo = new SofisComboG(listaAnios, "label");
        anioCombo.setSelectedT(itemActual);

        List<Programas> listaProgramas = programasDelegate.obtenerProgComboPorOrg(inicioMB.getOrganismo().getOrgPk());
        if (listaProgramas != null && !listaProgramas.isEmpty()) {
            programaCombo = new SofisComboG(listaProgramas, "progNombre");
            programaCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }

        List<SsUsuario> listaUsuCoord = ssUsuarioDelegate.obtenerSsUsuariosCoordEnt(inicioMB.getOrganismo().getOrgPk());
        if (listaUsuCoord != null) {
            usuarioCombo = new SofisComboG<>(listaUsuCoord, "nombreApellido");
            usuarioCombo.addEmptyItem(Labels.getValue("comboTodos"));
        }
    }

    public String buscarTareasAction() {
        cargarCombosSeleccionados();
        misTareas = entregablesDelegate.obtenerMisTareasPorFiltro(filtro, inicioMB.getOrganismo().getOrgPk());
        System.out.println("misTareas:" + misTareas.size());
        return null;
    }

    public String limpiarFiltroAction() {
        programaCombo.setSelected(-1);
        usuarioCombo.setSelected(-1);
        filtro = new FiltroMisTareasTO();
        return null;
    }

    public String coloresGantt() {
        StringBuilder result = new StringBuilder();
        result
                .append("<b>Referencia:</b><br/>")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_GRIS + ";\" disabled=\"disabled\">")
                .append("Gris: Entregable padre.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_AZUL + ";\" disabled=\"disabled\">")
                .append("Azul: Porcentaje de avance finalizado.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_VERDE + ";\" disabled=\"disabled\">")
                .append("Verde: Porcentaje de avance en plazo.")
                .append("<input class=\"botonSemaforo-dis\" type=\"submit\" value=\"\" style=\"background-color: " + ConstantesEstandares.SEMAFORO_ROJO + ";\" disabled=\"disabled\">")
                .append("Rojo: Porcentaje de avance atrasado.<br/>");

        return result.toString();
    }

    private void cargarCombosSeleccionados() {
        Integer progPk = programaCombo.getSelected();
        if (progPk != null && progPk > 0) {
            filtro.setProgPk(progPk);
        } else {
            filtro.setProgPk(null);
        }

        Integer usuPk = usuarioCombo.getSelected();
        if (usuPk != null && usuPk > 0) {
            filtro.setUsuCoordPk(usuPk);
        } else {
            filtro.setUsuCoordPk(null);
        }

        Integer anio = (Integer) anioCombo.getSelectedT().getValue();
        if (anio != null && anio > 0) {
            filtro.setAnio(anio);
        }
    }

    public boolean entAtrasado(Date entFin, Date entFinLineaBase) {
        if (entFin != null && entFinLineaBase != null) {
            Calendar cal = new GregorianCalendar();
            Calendar calFin = new GregorianCalendar();
            calFin.setTime(entFin);
            Calendar calFinBase = new GregorianCalendar();
            calFinBase.setTime(entFinLineaBase);

            if (calFin.before(cal) || (calFinBase != null && calFin.after(calFinBase))) {
                return true;
            }
        }
        return false;
    }

    public int calcularLeftEntByDate(Date inicio) {
        return EntregablesUtils.calcularLeftEntByDate(inicio, filtro.getAnio(), INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }

    public int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion) {
        return EntregablesUtils.calcularWitdhEntByDate(inicio, fin, duracion, filtro.getAnio(), INICIO_REPORTE_GANTT, FIN_REPORTE_GANTT);
    }
}
