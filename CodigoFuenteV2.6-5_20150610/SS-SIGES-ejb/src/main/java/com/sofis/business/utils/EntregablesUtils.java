package com.sofis.business.utils;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Entregables;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class EntregablesUtils {

    /**
     * Ordenar la lista de entregables por la descripción
     *
     * @param listEntregables
     * @return List
     */
    public static List<Entregables> sortByDescripcion(List<Entregables> listEntregables) {
        if (CollectionsUtils.isNotEmpty(listEntregables)) {
            Collections.sort(listEntregables, new Comparator<Entregables>() {
                @Override
                public int compare(Entregables e1, Entregables e2) {
                    if (e1 != null && e1.getEntDescripcion() != null && e2 != null && e2.getEntDescripcion() != null) {
                        return e1.getEntDescripcion().compareTo(e2.getEntDescripcion());
                    }
                    return 0;
                }
            });
        }

        return listEntregables;
    }

    /**
     * Ordenar la lista de entregables por la fecha de fin.
     *
     * @param listEntregables
     * @return Lista ordenada por fecha fin.
     */
    public static List<Entregables> sortByFechaFin(List<Entregables> listEntregables) {
        if (CollectionsUtils.isNotEmpty(listEntregables)) {
            Collections.sort(listEntregables, new Comparator<Entregables>() {
                @Override
                public int compare(Entregables e1, Entregables e2) {
                    if (e1 != null && e1.getEntFin() != null && e2 != null && e2.getEntFin() != null) {
                        int compreFin = e1.getEntFin().compareTo(e2.getEntFin());
                        if (compreFin != 0) {
                            return compreFin;
                        } else {
                            if (e1.getEntInicio() != null && e2.getEntInicio() != null) {
                                return e1.getEntInicio().compareTo(e2.getEntInicio());
                            }
                            return 0;
                        }
                    }
                    return 0;
                }
            });
        }

        return listEntregables;
    }

    /**
     * Ordenar la lista de entregables por id que se muestra en la grafica.
     *
     * @param listEntregables
     * @return List
     */
    public static List<Entregables> sortById(List<Entregables> listEntregables) {
        if (CollectionsUtils.isNotEmpty(listEntregables)) {
            Collections.sort(listEntregables, new Comparator<Entregables>() {
                @Override
                public int compare(Entregables e1, Entregables e2) {
                    if (e1 != null && e1.getEntId() != null && e2 != null && e2.getEntId() != null) {
                        return e1.getEntId().compareTo(e2.getEntId());
                    }
                    return 0;
                }
            });
        }

        return listEntregables;
    }

    public static List<Entregables> sortByEsfuerzo(List<Entregables> listEntregables, final boolean desc) {
        if (listEntregables != null && !listEntregables.isEmpty()) {
            Collections.sort(listEntregables, new Comparator<Entregables>() {
                @Override
                public int compare(Entregables e1, Entregables e2) {
                    if (e1 != null && e1.getEntEsfuerzo() != null && e2 != null && e2.getEntEsfuerzo() != null) {
                        if (!desc) {
                            return e1.getEntEsfuerzo().compareTo(e2.getEntEsfuerzo());
                        } else {
                            return e2.getEntEsfuerzo().compareTo(e1.getEntEsfuerzo());
                        }
                    }
                    return 0;
                }
            });
        }

        return listEntregables;
    }

    public static List<Entregables> primerosEntregables(List<Entregables> list, int size) {
        if (list != null && !list.isEmpty() && size > 0) {
            List<Entregables> result = new ArrayList<>();
            for (Entregables ent : list) {
                if (size > 0) {
                    result.add(ent);
                    size--;
                } else {
                    break;
                }
            }
            return result;
        }
        return list;
    }

    /**
     * Retorna la lista sin los entregables que son Padres.
     *
     * @param entregables
     * @return
     */
    public static List<Entregables> entregablesSinPadres(List<Entregables> entregables) {
        if (entregables != null && !entregables.isEmpty()) {
            List<Entregables> result = new ArrayList<>();
            for (Entregables ent : entregables) {
                int nextId = ent.getEntId() + 1;
                Entregables entNext = null;
                for (Entregables ent2 : entregables) {
                    if (ent2.getEntId().equals(nextId)) {
                        entNext = ent2;
                        break;
                    }
                }
                if (entNext == null || ent.getEntNivel().equals(entNext.getEntNivel())
                        || ent.getEntNivel() > entNext.getEntNivel()) {
                    result.add(ent);
                }
            }
            return result;
        }
        return null;
    }

    public static int calcularLeftEntByDate(Entregables ent, int anio, int inicioAnioPx, int finAnioPx) {
        return calcularLeftEntByDate(ent.getEntInicioDate(), anio, inicioAnioPx, finAnioPx);
    }

    public static int calcularLeftDia(int anio, int margenInicio, int inicioAnioPx, int finAnioPx) {
        Calendar calIni = new GregorianCalendar();
        if (calIni.get(Calendar.YEAR) < anio) {
                return inicioAnioPx;
            } else {
                int diaDelAnio = calIni.get(Calendar.DAY_OF_YEAR);
                int totalAncho = finAnioPx - inicioAnioPx;
                double pixelDia = totalAncho / 365d;

                return (int) Math.round((diaDelAnio * pixelDia)) + inicioAnioPx + margenInicio;
            }
    }

    public static int calcularLeftEntByDate(Date inicio, int anio, int inicioAnioPx, int finAnioPx) {
        if (inicio != null) {
            Calendar calIni = new GregorianCalendar();
            calIni.setTime(inicio);

            if (calIni.get(Calendar.YEAR) < anio) {
                return inicioAnioPx;
            } else {
                int diaDelAnio = calIni.get(Calendar.DAY_OF_YEAR);
                int totalAncho = finAnioPx - inicioAnioPx;
                double pixelDia = totalAncho / 365d;

                return (int) Math.round((diaDelAnio * pixelDia)) + inicioAnioPx;
            }
        }
        return inicioAnioPx;
    }

    public static int calcularWitdhEntByDate(Entregables ent, int anio, int inicioAnioPx, int finAnioPx) {
        return calcularWitdhEntByDate(ent.getEntInicioDate(), ent.getEntFinDate(), ent.getEntDuracion(), anio, inicioAnioPx, finAnioPx);
    }

    public static int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion, int anio, int inicioAnioPx, int finAnioPx) {
        if (inicio != null && fin != null) {
            Calendar calIni = new GregorianCalendar();
            calIni.setTime(inicio);
            Calendar calFin = new GregorianCalendar();
            calFin.setTime(fin);

            if (calFin.get(Calendar.YEAR) > anio) {
                int left = calcularLeftEntByDate(inicio, anio, inicioAnioPx, finAnioPx);
                return finAnioPx - left;
            } else {
                int diaDelAnio = 0;
                if (calIni.get(Calendar.YEAR) >= anio
                        && calFin.get(Calendar.YEAR) <= anio) {
                    if (duracion == null) {
                        duracion = DatesUtils.diasEntreFechas(inicio, fin);
                    }
                    diaDelAnio = duracion;
                } else {
                    diaDelAnio = calFin.get(Calendar.DAY_OF_YEAR);
                }
                int totalAncho = finAnioPx - inicioAnioPx;
                double pixelDia = totalAncho / 365d;

                return (int) Math.round((diaDelAnio * pixelDia));
            }
        }
        return 0;
    }

    public static Integer obtenerUltimoAnio(List<Entregables> entList) {
        if (CollectionsUtils.isNotEmpty(entList)) {
            Integer ultimo = null;
            Calendar cal = new GregorianCalendar();
            for (Entregables ent : entList) {
                cal.setTime(ent.getEntFinDate());
                if (ultimo == null || cal.get(Calendar.YEAR) > ultimo) {
                    ultimo = cal.get(Calendar.YEAR);
                }
            }
            return ultimo;
        }
        return null;
    }

    public static List<Entregables> marcarPadres(List<Entregables> listaEntregables) {
        if (CollectionsUtils.isNotEmpty(listaEntregables)) {
            listaEntregables = sortById(listaEntregables);
            Entregables[] arrEnt = listaEntregables.toArray(new Entregables[listaEntregables.size()]);

            for (int i = 0; i < arrEnt.length; i++) {
                if (arrEnt[i].getEntId() == 0
                        || (i + 1 < arrEnt.length && arrEnt[i].getEntNivel() < arrEnt[i + 1].getEntNivel())) {
                    arrEnt[i].setPadre(true);
                }
            }
            listaEntregables = Arrays.asList(arrEnt);
        }
        return listaEntregables;
    }

    /**
     * Dado una lista de entregables se le carga el indicador de nivel.
     *
     * @param listaEntregables
     * @return listaEntregables
     */
    public static List<Entregables> cargarNivelStr(List<Entregables> listaEntregables) {
        if (CollectionsUtils.isNotEmpty(listaEntregables)) {
            listaEntregables = marcarPadres(listaEntregables);

            for (Entregables ent : listaEntregables) {
                String s = "";
                for (int i = 0; i < ent.getEntNivel(); i++) {
                    s = StringsUtils.concat(s, "-");
                }
                if (ent.isPadre()) {
                    s = StringsUtils.concat(s, "*");
                }
                ent.setNivelStr(s);
            }
        }

        return listaEntregables;
    }

    /**
     * Retorna true si el entregable
     *
     * @param listEnt Entregables del proyecto.
     * @param entPadre
     * @param entHijo
     * @return
     */
    public static Boolean entregableEsHijo(List<Entregables> listEnt, Entregables entPadre, Entregables entHijo) {
        if (CollectionsUtils.isNotEmpty(listEnt)
                && entPadre != null
                && entHijo != null) {
            List<Entregables> hijos = obtenerHijos(listEnt, entPadre, Boolean.TRUE);

//            for (Entregables ent : hijos) {
//                if (ent.getEntPk().equals(entHijo.getEntPk())) {
//                    return true;
//                }
//            }
//            return false;
            return hijos.contains(entHijo);
        }
        return null;
    }

    /**
     * Retorna una lista de entregables hijos a partir del entregable padre
     * indicado.
     *
     * @param listEnt
     * @param entPadre
     * @param agregarPadre
     * @return
     */
    public static List<Entregables> obtenerHijos(List<Entregables> listEnt, Entregables entPadre, Boolean agregarPadre) {
        if (CollectionsUtils.isNotEmpty(listEnt) && entPadre != null) {
            List<Entregables> result = new ArrayList<>();
            listEnt = sortById(listEnt);
            Entregables[] arrEnt = listEnt.toArray(new Entregables[listEnt.size()]);

            for (int i = 0; i < arrEnt.length; i++) {
                if (arrEnt[i].getEntPk().equals(entPadre.getEntPk())) {
                    if (agregarPadre != null && agregarPadre) {
                        result.add(arrEnt[i]);
                    }
                    for (int j = i + 1; j < arrEnt.length; j++) {
                        if (arrEnt[i].getEntNivel() < arrEnt[j].getEntNivel()) {
                            result.add(arrEnt[j]);
                        } else {
                            break;
                        }
                    }
                }
            }
            return result;
        }
        return listEnt;
    }

    public static List<Entregables> cargarCamposCombos(List<Entregables> listEnt) {
        if (CollectionsUtils.isNotEmpty(listEnt)) {
            listEnt = sortById(listEnt);
            listEnt = cargarNivelStr(listEnt);

            for (Entregables ent : listEnt) {
                String ini = DatesUtils.toStringFormat(ent.getEntInicioDate(), ConstantesEstandares.CALENDAR_PATTERN);
                String fin = DatesUtils.toStringFormat(ent.getEntFinDate(), ConstantesEstandares.CALENDAR_PATTERN);
                String nivel = ent.getNivelStr() != null ? StringsUtils.concat(ent.getNivelStr(), " ") : "";
                ent.setNivelNombreCombo(StringsUtils.concat(nivel, ent.getEntNombre()));
                ent.setFechaNivelNombreCombo(StringsUtils.concat("(", ini, "-", fin, ") ", nivel, ent.getEntNombre()));
            }
        }

        return listEnt;
    }
}
