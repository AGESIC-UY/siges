package com.sofis.web.utils;

import com.sofis.data.daos.EntregablesDAO;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Cronogramas;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.CronogramaTO;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.tipos.ProyectoTO;
import com.sofis.entities.tipos.UsuarioTO;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.web.genericos.constantes.ConstantesCronograma;
import com.sofis.web.mb.ModuloCronogramaMB;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.icefaces.ace.json.JSONArray;
import org.icefaces.ace.json.JSONException;
import org.icefaces.ace.json.JSONObject;

public abstract class CronogramaUtils {

    public final static String REFERENCIA_CRONOGRAMA_SMALL = coloresGantt();
    private static final Logger LOGGER = Logger.getLogger(CronogramaUtils.class.getName());

    private static String coloresGantt() {

        StringBuilder result = new StringBuilder();
        result.append("<div style=\"margin-left: -12px;\"> ")
                .append("<input class=\"botonSemaforo-dis small\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_AZUL).append(";\" disabled=\"disabled\">")
                .append(" Azul: Avance finalizado.")
                .append("<input class=\"botonSemaforo-dis small\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_VERDE).append(";\" disabled=\"disabled\">")
                .append(" Verde: Avance en plazo.")
                .append("<input class=\"botonSemaforo-dis small\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_ROJO).append(";\" disabled=\"disabled\">")
                .append(" Rojo: Avance atrasado.")
                .append("<input class=\"botonSemaforo-dis small\" type=\"submit\" value=\"\" style=\"background-color: ")
                .append(ConstantesEstandares.SEMAFORO_GRIS).append(";\" disabled=\"disabled\">")
                .append(" Gris: Entregable padre sin esfuerzo.<br/>")
                .append("</div>");

        return result.toString();
    }

    public static CronogramaTO jsonToCronograma(JSONObject json) throws JSONException {
        LOGGER.log(Level.INFO, "Entrando a jsonToCronograma");

        if (json == null) {
            return null;
        }

        CronogramaTO c = new CronogramaTO();
        if (json.has(ConstantesCronograma.CRONO_PK)) {
            c.setId(json.getInt(ConstantesCronograma.CRONO_PK));
        }
        if (json.has(ConstantesCronograma.CRONO_SELECTED_ROW)) {
            c.setEntregableSeleccionado(json.getInt(ConstantesCronograma.CRONO_SELECTED_ROW));
        }
        if (json.has(ConstantesCronograma.CRONO_CAN_WRITE)) {
            c.setEditable(true);
        }
        //if (j.has(ConstantesCronograma.CRONO_CAN_WRITE_PARENT)) {
        //	c.setPermisoEscrituraPadre(true);
        //}
        if (json.has(ConstantesCronograma.CRONO_DELETED_TASK_ID)) {
            c.setEntregablesEliminados(json.getString(ConstantesCronograma.CRONO_DELETED_TASK_ID));
        }

        c.setEntregables(jsonToEntregables(json));

        return c;
    }

    private static List<EntregableTO> jsonToEntregables(JSONObject json) throws JSONException {

        if (json == null) {
            return null;
        }

        List<EntregableTO> setEnt = new ArrayList<>();
        JSONArray tasks = json.getJSONArray(ConstantesCronograma.TASKS);

        for (int i = 0; i < tasks.length(); i++) {

            EntregableTO e = jsonToEntregable(tasks.getJSONObject(i), i + 1);

            setEnt.add(e);
        }

        return setEnt;
    }

    private static EntregableTO jsonToEntregable(JSONObject task, int numero) throws JSONException {

        if (task == null) {
            return null;
        }

        EntregableTO e = new EntregableTO();
        //if (task.has(ConstantesCronograma.CRONO_PK)) {
        //	Cronogramas c = new Cronogramas(j.getInt(ConstantesCronograma.CRONO_PK));
        //	e.setEntCroFk(c);
        //}

        e.setNumero(numero);

        //if (task.has(ConstantesCronograma.TASK_CODE)) {
        //	e.setEntCodigo(task.getString(ConstantesCronograma.TASK_CODE));
        //}
        if (task.has(ConstantesCronograma.TASK_COORDINADOR)) {
            Object coordId = task.get(ConstantesCronograma.TASK_COORDINADOR);
            if (coordId instanceof Integer) {
                e.setCoordinador(new UsuarioTO(task.getInt(ConstantesCronograma.TASK_COORDINADOR)));
            }
        }

        //if (task.has(ConstantesCronograma.TASK_COLLAPSED)) {
        //	e.setEntCollapsed(task.getBoolean(ConstantesCronograma.TASK_COLLAPSED));
        //}
        if (task.has(ConstantesCronograma.TASK_DESCRIPTION)) {
            e.setDescripcion(task.getString(ConstantesCronograma.TASK_DESCRIPTION));
        }
        if (task.has(ConstantesCronograma.TASK_PARENT)) {
            e.setEsPadre(task.getBoolean(ConstantesCronograma.TASK_PARENT));
        }
        if (task.has(ConstantesCronograma.TASK_DURATION)) {
            e.setDuracion(task.getInt(ConstantesCronograma.TASK_DURATION));
        }
        if (task.has(ConstantesCronograma.TASK_DURACION_LINEA_BASE)) {
            e.setDuracionLineaBase(task.getInt(ConstantesCronograma.TASK_DURACION_LINEA_BASE));
        }

        
        
          Integer aEsfuerzo = null;
            try {
                aEsfuerzo = task.getInt(ConstantesCronograma.TASK_ESFUERZO);
            } catch (JSONException jSONException) {
                System.out.println("falla");
                jSONException.printStackTrace();
                
            }
            e.setEsfuerzo(aEsfuerzo != null ?  aEsfuerzo : 0);
        
        
      

        if (task.has(ConstantesCronograma.TASK_END)) {
            Long l = task.getLong(ConstantesCronograma.TASK_END);
            e.setFechaFin((l > 0) ? new Date(l) : null);
        }
        if (task.has(ConstantesCronograma.TASK_END_LINEA_BASE)) {
            Long l = task.getLong(ConstantesCronograma.TASK_END_LINEA_BASE);
            e.setFinLineaBase((l > 0) ? new Date(l) : null);
        }
        if (task.has(ConstantesCronograma.TASK_START)) {
            Long l = task.getLong(ConstantesCronograma.TASK_START);
            e.setFechaInicio((l > 0) ? new Date(l) : null);
        }
        if (task.has(ConstantesCronograma.TASK_START_LINEA_BASE)) {
            Long l = task.getLong(ConstantesCronograma.TASK_START_LINEA_BASE);
            e.setInicioLineaBase((l > 0) ? new Date(l) : null);
        }
        if (task.has(ConstantesCronograma.TASK_HORAS_ESTIMADAS)) {
            String he = task.getString(ConstantesCronograma.TASK_HORAS_ESTIMADAS);
            e.setHorasEstimadas(he);
        }

        e.setNivel(task.has(ConstantesCronograma.TASK_LEVEL) ? task.getInt(ConstantesCronograma.TASK_LEVEL) : 0);

        if (task.has(ConstantesCronograma.TASK_NAME)) {
            e.setNombre(task.getString(ConstantesCronograma.TASK_NAME));
        }

        if (task.has(ConstantesCronograma.TASK_PK)) {
            e.setId(task.getInt(ConstantesCronograma.TASK_PK));
        }

        if (task.has(ConstantesCronograma.TASK_DEPENDS)) {
            e.setDependencias(task.getString(ConstantesCronograma.TASK_DEPENDS));
            //String[] pred = task.getString(ConstantesCronograma.TASK_DEPENDS).split(":");
            //e.setEntPredecesorDias(pred.length >= 2 ? Integer.valueOf(pred[1]) : 0);
        }

        if (task.has(ConstantesCronograma.TASK_PROGRESS)) {
            Integer aInt = null;
            try {
                aInt = task.getInt(ConstantesCronograma.TASK_PROGRESS);
            } catch (JSONException jSONException) {
                System.out.println("falla");
                jSONException.printStackTrace();
                
            }
            e.setProgreso(aInt != null ?  aInt : 0);

        }
        if (task.has(ConstantesCronograma.TASK_STATUS)) {
            e.setStatus(task.getString(ConstantesCronograma.TASK_STATUS));
        }
        if (task.has(ConstantesCronograma.TASK_RELEVANTE)) {
            e.setEsRelevantePMO(task.getBoolean(ConstantesCronograma.TASK_RELEVANTE));
        }
        if (task.has(ConstantesCronograma.TASK_INICIO_PROYECTO)) {
            e.setEsInicioProyecto(task.getBoolean(ConstantesCronograma.TASK_INICIO_PROYECTO));
        }
        if (task.has(ConstantesCronograma.TASK_FIN_PROYECTO)) {
            e.setEsFinProyecto(task.getBoolean(ConstantesCronograma.TASK_FIN_PROYECTO));
        }

        if (task.has(ConstantesCronograma.TASK_END_IS_MILESTONE)) {
            e.setFinEsHito(task.getBoolean(ConstantesCronograma.TASK_END_IS_MILESTONE));

            if (e.getFinEsHito()) {
                e.setFechaInicio(e.getFechaFin());
                e.setInicioLineaBase(e.getFinLineaBase());
            }
        }

        if (task.has(ConstantesCronograma.TASK_START_IS_MILESTONE)) {
            e.setInicioEsHito(task.getBoolean(ConstantesCronograma.TASK_START_IS_MILESTONE));
        }
        if (task.has(ConstantesCronograma.TASK_ES_REFERENCIA)) {
            e.setEsReferencia(task.getBoolean(ConstantesCronograma.TASK_ES_REFERENCIA));
        }
        if (task.has(ConstantesCronograma.TASK_REFERIDO)) {
            e.setReferido(jsonToEntregable(task.getJSONObject(ConstantesCronograma.TASK_REFERIDO), 0));
        }
        if (task.has(ConstantesCronograma.TASK_PROYECTO_REFERIDO)) {
            e.setProyectoReferido(jsonToProyecto(task.getJSONObject(ConstantesCronograma.TASK_PROYECTO_REFERIDO)));
        }

        return e;
    }

    public static JSONObject cronogramaToJSON(CronogramaTO cronograma, SsUsuario usuario, List<SsUsuario> usuarios, FichaTO fichaTO) throws JSONException {

        if (cronograma == null) {
            return null;
        }

        JSONObject cronogramaJSON = new JSONObject();

        cronogramaJSON.put(ConstantesCronograma.CRONO_PK, cronograma.getId())
                .put(ConstantesCronograma.CRONO_SELECTED_ROW, cronograma.getEntregableSeleccionado())
                .put(ConstantesCronograma.CRONO_DELETED_TASK_ID, new JSONArray())
                .put(ConstantesCronograma.CRONO_RESOURCES, new JSONArray())
                .put(ConstantesCronograma.CRONO_CAN_WRITE, cronograma.getEditable())
                //.put(ConstantesCronograma.CRONO_CAN_WRITE_PARENT, cro.getCroPermisoEscrituraPadre())
                .put(ConstantesCronograma.CRONO_CAN_WRITE_PARENT, true)
                .put(ConstantesCronograma.FICHA_PK, StringsUtils.toString(fichaTO.getFichaFk()))
                .put(ConstantesCronograma.FICHA_TIPO, StringsUtils.toString(fichaTO.getTipoFicha()))
                //.put(ConstantesCronograma.FICHA_PK, fichaTO.getFichaFk())
                //.put(ConstantesCronograma.FICHA_TIPO, fichaTO.getTipoFicha())
                .put(ConstantesCronograma.GERENTEOADJUNTO, cronograma.getIsGerenteOAdjunto())
                .put(ConstantesCronograma.CRONO_MORE_ROWS, cronograma.getAdmiteNuevosEntregables())
                .put(ConstantesCronograma.FICHA_ESTADO, cronograma.getEstadoFicha())
                .put(ConstantesCronograma.CRONO_ESFUERZO_TOTAL, cronograma.getEsfuerzoTotal())
                .put(ConstantesCronograma.CRONO_HORAS_TOTAL, cronograma.getHorasTotales())
                .put(ConstantesCronograma.CRONO_ROWS_GANTT, "100")
                .put(ConstantesCronograma.CRONO_SET_PERIODO_ENTREGABLE, cronograma.getPeriodoConfigurable())
                .put(ConstantesCronograma.CRONO_USUARIO_ID, usuario.getUsuId().toString())
                .put(ConstantesCronograma.CRONO_IS_PM, cronograma.getEsPM())
                .put(ConstantesCronograma.CRONO_IS_PMO, cronograma.getEsPMO())
                .put(ConstantesCronograma.CRONO_IS_PMOditor, fichaTO.getIsPmofEditor());

        if (cronograma.getEntregables() != null) {

            JSONArray tasks = new JSONArray();

            for (EntregableTO ent : cronograma.getEntregables()) {

                tasks.put(entregableToJSON(ent, cronograma.getEstadoCronograma()));
            }

            cronogramaJSON.put(ConstantesCronograma.TASKS, tasks);
        }

        if (usuarios != null) {

            JSONArray coordinadores = new JSONArray();

            for (SsUsuario u : usuarios) {

                coordinadores.put(u.getUsuId().toString() + ":" + u.getUsuNombreApellido());
            }

            cronogramaJSON.put(ConstantesCronograma.CRONO_COORDINADORES, coordinadores);
        }

        return cronogramaJSON;
    }

    public static JSONObject entregableToJSON(EntregableTO ent, Integer estadoCronograma) throws JSONException {

        if (ent == null) {
            return null;
        }

        JSONObject task = new JSONObject();

        task.put(ConstantesCronograma.TASK_PK, ent.getId())
                .put(ConstantesCronograma.TASK_ID, ent.getNumero())
                .put(ConstantesCronograma.TASK_NAME, ent.getNombre())
                .put(ConstantesCronograma.TASK_CODE, "")
                .put(ConstantesCronograma.TASK_DESCRIPTION, StringsUtils.toCleanString(ent.getDescripcion()))
                .put(ConstantesCronograma.TASK_LEVEL, ent.getNivel())
                .put(ConstantesCronograma.TASK_STATUS, ent.getStatus())
                .put(ConstantesCronograma.TASK_ESTADO, estadoCronograma)
                .put(ConstantesCronograma.TASK_START, DatesUtils.corregirGMT(ent.getFechaInicio().getTime()))
                .put(ConstantesCronograma.TASK_DURATION, ent.getDuracion())
                .put(ConstantesCronograma.TASK_END, DatesUtils.corregirGMT(ent.getFechaFin().getTime()))
                .put(ConstantesCronograma.TASK_START_IS_MILESTONE, ent.getInicioEsHito())
                .put(ConstantesCronograma.TASK_END_IS_MILESTONE, ent.getFinEsHito())
                .put(ConstantesCronograma.TASK_COLLAPSED, false)
                .put(ConstantesCronograma.TASK_ASSIGS, new JSONArray())
                .put(ConstantesCronograma.TASK_RELEVANTE, ent.getEsRelevantePMO())
                .put(ConstantesCronograma.TASK_CAN_DELETE, ent.getEliminable())
                .put(ConstantesCronograma.TASK_COORDINADOR, (ent.getCoordinador() != null) ? ent.getCoordinador().getId() : -1)
                .put(ConstantesCronograma.TASK_ESFUERZO, ent.getEsfuerzo())
                .put(ConstantesCronograma.TASK_START_LINEA_BASE, (ent.getInicioLineaBase() != null) ? DatesUtils.corregirGMT(ent.getInicioLineaBase().getTime()) : 0)
                .put(ConstantesCronograma.TASK_END_LINEA_BASE, (ent.getFinLineaBase() != null) ? DatesUtils.corregirGMT(ent.getFinLineaBase().getTime()) : 0)
                .put(ConstantesCronograma.TASK_DURACION_LINEA_BASE, ent.getDuracionLineaBase())
                .put(ConstantesCronograma.TASK_INICIO_PROYECTO, ent.getEsInicioProyecto())
                .put(ConstantesCronograma.TASK_FIN_PROYECTO, ent.getEsFinProyecto())
                .put(ConstantesCronograma.TASK_PROGRESS, ent.getProgreso())
                .put(ConstantesCronograma.TASK_HORAS_ESTIMADAS, ent.getHorasEstimadas())
                .put(ConstantesCronograma.TASK_DEPENDS, ent.getDependencias())
                .put(ConstantesCronograma.TASK_TIENE_PRODUCTOS, ent.getTieneProductos())
                .put(ConstantesCronograma.TASK_TIENE_VINCULACION_WEKAN, ent.getTieneVinculacionWekan())
                .put(ConstantesCronograma.TASK_ES_REFERENCIA, ent.getEsReferencia())
                .put(ConstantesCronograma.TASK_REFERIDO, entregableToJSON(ent.getReferido()))
                .put(ConstantesCronograma.TASK_PROYECTO_REFERIDO, proyectoToJSON(ent.getProyectoReferido()))
                .put(ConstantesCronograma.TASK_ES_REFERIDO_DESDE_OTRO_PROYECTO, ent.getEsReferenciaDesdeOtroProyecto());

        return task;
    }

    public static JSONObject entregableToJSON(EntregableTO ent) throws JSONException {
        return entregableToJSON(ent, null);
    }

    public static JSONObject proyectoToJSON(ProyectoTO proyecto) throws JSONException {

        if (proyecto == null) {
            return null;
        }

        JSONObject proyectoJSON = new JSONObject();

        proyectoJSON.put(ConstantesCronograma.PROYECTO_ID, proyecto.getId())
                .put(ConstantesCronograma.PROYECTO_NOMBRE, proyecto.getNombre());

        return proyectoJSON;
    }

    private static ProyectoTO jsonToProyecto(JSONObject json) throws JSONException {

        if (json == null) {
            return null;
        }

        ProyectoTO proyecto = new ProyectoTO();

        if (json.has(ConstantesCronograma.PROYECTO_ID)) {
            proyecto.setId(json.getInt(ConstantesCronograma.PROYECTO_ID));
        }
        if (json.has(ConstantesCronograma.PROYECTO_NOMBRE)) {
            proyecto.setNombre(json.getString(ConstantesCronograma.PROYECTO_NOMBRE));
        }

        return proyecto;
    }

}
