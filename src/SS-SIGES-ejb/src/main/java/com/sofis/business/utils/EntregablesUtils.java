package com.sofis.business.utils;

import com.sofis.business.validations.EntregablesValidacion;
import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.constantes.MensajesNegocio;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;

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
                if (ent.getEntParent() != null && !ent.getEntParent()) {
                    result.add(ent);
                }
            }
            return result;
        }
        return entregables;
    }

    /**
     * Retorna la lista de entregables sin los hitos.
     *
     * @param entregables
     * @return List Entregables
     */
    public static List<Entregables> entregablesSinHitos(List<Entregables> entregables) {
        if (entregables != null && !entregables.isEmpty()) {
            List<Entregables> result = new ArrayList<>();
            for (Entregables ent : entregables) {
                if (ent.getEntFinEsHito() == null || !ent.getEntFinEsHito()) {
                    result.add(ent);
                }
            }
            return result;
        }
        return entregables;
    }

    public static List<Entregables> calculcarEntSinPadres(List<Entregables> entregables) {
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
        return calcularWitdhEntByDate(inicio, fin, duracion, anio, inicioAnioPx, finAnioPx, false);
    }

    public static int calcularWitdhEntByDate(Date inicio, Date fin, Integer duracion, int anio, int inicioAnioPx, int finAnioPx, boolean verUnDia) {
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
                if (calIni.get(Calendar.YEAR) == anio
                        && calFin.get(Calendar.YEAR) == anio) {
                    if (verUnDia
                            && calIni.get(Calendar.MONTH) == calFin.get(Calendar.MONTH)
                            && calIni.get(Calendar.DAY_OF_MONTH) == calFin.get(Calendar.DAY_OF_MONTH)) {
                        duracion = 1;
                    } else {
                        duracion = DatesUtils.diasEntreFechas(inicio, fin);
                    }
                    diaDelAnio = duracion;
                } else {
                    diaDelAnio = calFin.get(Calendar.DAY_OF_YEAR);
                }
                int totalAncho = finAnioPx - inicioAnioPx;
                double pixelDia = totalAncho / 365d;

                double d = (diaDelAnio * pixelDia);
                return (d > 0 && d < 1) ? 1 : (int) Math.round(d);
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

    /**
     * Dada la lista de entregables marca los que son padres. Se toma en cuenta
     * que la lista aportada son todos los entregables de un cronograma y no
     * falta ninguno.
     *
     * @param listaEntregables
     * @return
     */
    public static List<Entregables> marcarPadres(List<Entregables> listaEntregables) {
        if (CollectionsUtils.isNotEmpty(listaEntregables)) {
            listaEntregables = sortById(listaEntregables);
            Entregables[] arrEnt = listaEntregables.toArray(new Entregables[listaEntregables.size()]);

            for (int i = 0; i < arrEnt.length; i++) {
                arrEnt[i].setEntParent(arrEnt[i].getEntId() == 0
                        || (i + 1 < arrEnt.length && arrEnt[i].getEntNivel() < arrEnt[i + 1].getEntNivel()));
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
            listaEntregables = sortById(listaEntregables);

            for (Entregables ent : listaEntregables) {
                String s = "";
                for (int i = 0; i < ent.getEntNivel(); i++) {
                    s = StringsUtils.concat(s, "-");
                }
                if (ent.esEntParent()) {
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
                if ((arrEnt[i].getEntPk() != null && entPadre.getEntPk() != null && arrEnt[i].getEntPk().equals(entPadre.getEntPk()))
                        || (arrEnt[i].getEntId() != null && entPadre.getEntId() != null && arrEnt[i].getEntId().equals(entPadre.getEntId()))) {
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

    /**
     *
     * @param listEnt
     * @return List de Entregables
     */
    public static List<Entregables> cargarCamposCombos(List<Entregables> listEnt) {
        if (CollectionsUtils.isNotEmpty(listEnt)) {
//            listEnt = sortById(listEnt);
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

    /**
     * Retorna true si la fecha de fin del entregable es menor a hoy o si es
     * mayor al fin de la linea base.
     *
     * @param entFin
     * @param entFinLineaBase
     * @return boolean true si es atrasado.
     */
    public static boolean entAtrasado(Date entFin, Date entFinLineaBase) {
        if (entFin != null) {
            Calendar calNow = new GregorianCalendar();
            if (DatesUtils.esMayor(calNow.getTime(), entFin) || (entFinLineaBase != null && DatesUtils.esMayor(entFin, entFinLineaBase))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Corrige las fechas de los entregables padres para que sean coherentes con
     * las de sus hijos. Criterio a corregir: 1- La fecha de inicio de un
     * entregable padre, no puede ser mayor a la de alguno de sus hijos 2- La
     * fecha de fin de un entregable padre, no puede ser mayor a la de alguno de
     * sus hijos 3- (corregirLineaBase = true): aplica el criterio 1 para el
     * inicio de la linea base 3- (corregirLineaBase = true): aplica el criterio
     * 2 para el fin de la linea base 4- Si un entregable debería ser padre, se
     * corrige la marca.
     *
     * @param entregables entregables del cronograma
     * @param corregirLineaBase corregir linea base
     * @param marcarPadres corregir marca de padres e hijos
     * @return
     */
    public static List<Entregables> corregirFechasPadres(List<Entregables> entregables, Boolean corregirLineaBase, Boolean marcarPadres) {

        if (entregables != null && !entregables.isEmpty()) {
            entregables = sortById(entregables);
            int size = entregables.size();
            Entregables e;
            Entregables eHijo;
            Boolean esPadre = null;
            Map<Integer, Entregables> entIdMap = new HashMap<>();
            for (int i = 0; i < size; i++) {
                e = entregables.get(i);
                if (e.getEntId() != null) {
                    entIdMap.put(e.getEntId(), e);
                }
                Long minInicio = null;
                Long maxFin = null;
                Long minInicioLB = null;
                Long maxFinLB = null;
                esPadre = false;
                for (int j = i + 1; j < size; j++) {
                    eHijo = entregables.get(j);
                    if (e.getEntNivel() < eHijo.getEntNivel()) {
                        esPadre = true;
                        if (eHijo.getEntInicio() != null) {
                            minInicio = minInicio == null
                                    || eHijo.getEntInicio() < e.getEntInicio()
                                    ? eHijo.getEntInicio()
                                    : minInicio;

                        }
                        if (eHijo.getEntFin() != null) {
                            maxFin = maxFin == null
                                    || eHijo.getEntFin() > e.getEntFin()
                                    ? eHijo.getEntFin()
                                    : maxFin;
                        }
                        if (corregirLineaBase && eHijo.getEntInicioLineaBase() != null && e.getEntInicioLineaBase() != null) {
                            minInicioLB = minInicioLB == null
                                    || eHijo.getEntInicioLineaBase() < e.getEntInicioLineaBase()
                                    ? eHijo.getEntInicioLineaBase()
                                    : minInicioLB;

                        }
                        if (corregirLineaBase && eHijo.getEntFinLineaBase() != null && e.getEntFinLineaBase() != null) {
                            maxFinLB = maxFinLB == null
                                    || eHijo.getEntFinLineaBase() > e.getEntFinLineaBase()
                                    ? eHijo.getEntFinLineaBase()
                                    : maxFinLB;
                        }

                    } else {
                        break;
                    }

                }
                if (esPadre) {
                    if (minInicio != null) {
                        e.setEntInicio(minInicio);
                    }
                    if (maxFin != null) {
                        e.setEntFin(maxFin);
                    }

                    if (minInicioLB != null) {
                        e.setEntInicioLineaBase(minInicioLB);
                    }
                    if (maxFinLB != null) {
                        e.setEntFinLineaBase(maxFinLB);
                    }
                    if (marcarPadres) {
                        e.setEntParent(esPadre);
                    }
                    e.setEntDuracion(DatesUtils.diasEntreFechas(
                            e.getEntInicioDate(),
                            e.getEntFinDate())
                            + 1);
                    if (e.getEntFinLineaBase() != null && e.getEntInicioLineaBase() != null) {
                        e.setEntDuracionLineaBase(
                                DatesUtils.diasEntreFechas(
                                        e.getEntInicioLineaBaseDate(),
                                        e.getEntFinLineaBaseDate())
                                + 1);
                    }

                }
            }

//            for (Entregables en : entregables) {
//                if (en.getEntPredecesorFk() != null && !StringsUtils.isEmpty(en.getEntPredecesorFk())) {
//                    Matcher matcher = EntregablesValidacion.DEPENDENCIA_PATTERN.matcher(en.getEntPredecesorFk());
//                    if (matcher.matches()) {
//                        String[] dependencias = en.getEntPredecesorFk().split(",");
//                        String[] de;
//                        Integer[] elem;
//                        Entregables entDep = null;
//                        Long maxFinDep = 0L;
//                        Date finDepAux = null;
//                        for (String d : dependencias) {
//                            de = d.split(":");
//                            Integer entPreId = Integer.parseInt(de[0]);
//                            Integer dias = de.length > 1 && !StringsUtils.isEmpty(de[1])
//                                    ? Integer.parseInt(de[1])
//                                    : null;
//                            elem = new Integer[2];
//                            elem[0] = entPreId;
//                            elem[1] = dias;
//                            entDep = entIdMap.get(elem[0]);
//                            if (entDep != null && !(entDep.getEntId().equals(en.getEntId()))
//                                    && !EntregablesUtils.entregableEsHijo(entregables, entDep, en)) {
//
//                                if (elem[1] != null) {
//                                    finDepAux = DatesUtils.incrementarDias(entDep.getEntFinDate(), elem[1]);
//                                } else {
//                                    finDepAux = entDep.getEntFinDate();
//                                }
//                                maxFinDep = maxFinDep < finDepAux.getTime() ? finDepAux.getTime() : maxFinDep;
//                            }
//                        }
//
//                        en.setEntInicio(DatesUtils.incrementarDias(new Date(maxFinDep), 1).getTime());
//                        en.setEntFin(DatesUtils.incrementarDias(en.getEntInicioDate(), en.getEntDuracion()).getTime());
//                    }
//                }
//            }

        }
        return entregables;
    }

    public Set<Entregables> setFromList(List<Entregables> entregables) {
        return new HashSet<Entregables>(entregables);
    }

}
