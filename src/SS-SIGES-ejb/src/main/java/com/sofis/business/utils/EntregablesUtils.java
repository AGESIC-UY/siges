package com.sofis.business.utils;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.entities.data.Entregables;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.EntregableTO;
import com.sofis.entities.tipos.UsuarioTO;
import com.sofis.exceptions.BusinessException;
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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class EntregablesUtils {

	public static EntregableTO convert(Entregables entregable) {

		EntregableTO result = new EntregableTO();
		result.setId(entregable.getEntPk());
		result.setNumero(entregable.getEntId());
		result.setNivel(entregable.getEntNivel());
		result.setEsPadre(entregable.getEntParent());

		result.setNombre(entregable.getEntNombre());
		result.setDescripcion(entregable.getEntDescripcion());

		result.setFechaInicio(entregable.getEntInicioDate());
		result.setFechaFin(entregable.getEntFinDate());
		result.setDuracion(entregable.getEntDuracion());

		result.setProgreso(entregable.getEntProgreso());
		result.setEsfuerzo(entregable.getEntEsfuerzo());

		result.setDependencias(entregable.getEntPredecesorFk());
		result.setStatus(entregable.getEntStatus());

		result.setInicioEsHito(entregable.getEntInicioEsHito());
		result.setFinEsHito(entregable.getEntFinEsHito());

		result.setEsRelevantePMO(entregable.getEntRelevante());

		result.setInicioLineaBase(entregable.getEntInicioLineaBaseDate());
		result.setFinLineaBase(entregable.getEntFinLineaBaseDate());
		result.setDuracionLineaBase(entregable.getEntDuracionLineaBase());

		result.setEsInicioProyecto(entregable.getEntInicioProyecto());
		result.setEsFinProyecto(entregable.getEntFinProyecto());

		result.setHorasEstimadas(entregable.getEntHorasEstimadas());

		result.setEsReferencia(entregable.getEsReferencia());
                
		result.setCoordinador(convert(entregable.getCoordinadorUsuFk()));
                
                if (result.getEsReferencia() != null && result.getEsReferencia() && result.getReferido() != null){
                    result.setReferido(convert(entregable.getReferido()));
                }
		return result;
	}
        
        public static UsuarioTO convert(SsUsuario usuario) {

		UsuarioTO result = new UsuarioTO();
		result.setId(usuario.getUsuId());
                result.setPrimerNombre(usuario.getNombreApellido());
		result.setPrimerApellido(usuario.getNombreApellido());
		

		return result;
	}

	public static Entregables convert(EntregableTO entregable) {

		Entregables result = new Entregables();
		result.setEntPk(entregable.getId());
		result.setEntId(entregable.getNumero());
		result.setEntNivel(entregable.getNivel());
		result.setEntParent(entregable.getEsPadre());

		result.setEntNombre(entregable.getNombre());
		result.setEntDescripcion(entregable.getDescripcion());

		result.setEntInicio(entregable.getFechaInicio().getTime());
		result.setEntFin(entregable.getFechaFin().getTime());
		result.setEntDuracion(entregable.getDuracion());

		result.setEntProgreso((entregable.getProgreso() != null) ? entregable.getProgreso() : 0);
		result.setEntEsfuerzo((entregable.getEsfuerzo() != null) ? entregable.getEsfuerzo() : 0);

		result.setEntPredecesorFk(entregable.getDependencias());
		result.setEntStatus(entregable.getStatus());

		result.setEntInicioEsHito(entregable.getInicioEsHito());
		result.setEntFinEsHito(entregable.getFinEsHito());

		result.setEntRelevante(entregable.getEsRelevantePMO());

		result.setEntInicioLineaBase((entregable.getInicioLineaBase() != null) ? entregable.getInicioLineaBase().getTime() : null);
		result.setEntFinLineaBase((entregable.getFinLineaBase() != null) ? entregable.getFinLineaBase().getTime() : null);
		result.setEntDuracionLineaBase(entregable.getDuracionLineaBase());

		result.setEntInicioProyecto(entregable.getEsInicioProyecto());
		result.setEntFinProyecto(entregable.getEsFinProyecto());

		result.setEntHorasEstimadas(entregable.getHorasEstimadas());
		result.setEsReferencia((entregable.getEsReferencia() != null) ? entregable.getEsReferencia() : false);




		return result;
	}

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

	public static List<Entregables> sortByNivel(List<Entregables> listEntregables, final boolean desc) {
		if (listEntregables != null && !listEntregables.isEmpty()) {
			Collections.sort(listEntregables, new Comparator<Entregables>() {
				@Override
				public int compare(Entregables e1, Entregables e2) {
					if (e1 != null && e1.getEntNivel() != null && e2 != null && e2.getEntNivel() != null) {
						if (!desc) {
							return e1.getEntNivel().compareTo(e2.getEntNivel());
						} else {
							return e2.getEntNivel().compareTo(e1.getEntNivel());
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

	public static List<Entregables> filtrarEntregablesEditables(List<Entregables> entregables, Map<Integer, Boolean> mapaEditables) {

		List<Entregables> result = new ArrayList<>();
		if (entregables != null && !entregables.isEmpty()) {

			for (Entregables ent : entregables) {

				if (mapaEditables.get(ent.getEntId())) {
					result.add(ent);
				}
			}
		}

		return result;
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
	 * Retorna el entregable padre a partir de un entregable hijo
	 * indicado.
	 *
	 * @param listEnt
	 * @param entHijo
	 * @return
	 */
	public static Entregables obtenerPadre(List<Entregables> listEnt, Entregables entHijo) {
		if (CollectionsUtils.isNotEmpty(listEnt) && entHijo != null && entHijo.getEntNivel() != null && entHijo.getEntNivel() != 0) {
			Entregables result = null;
			listEnt = sortById(listEnt);
			Entregables[] arrEnt = listEnt.toArray(new Entregables[listEnt.size()]);

			for (int i = 0; i < arrEnt.length; i++) {
				if ((arrEnt[i].getEntPk() != null && entHijo.getEntPk() != null && arrEnt[i].getEntPk().equals(entHijo.getEntPk()))
						|| (arrEnt[i].getEntId() != null && entHijo.getEntId() != null && arrEnt[i].getEntId().equals(entHijo.getEntId()))) {
					if (arrEnt[i].getEntNivel() == arrEnt[i-1].getEntNivel()-1) {
						result = arrEnt[i-1];
                                        } else {
                                            break;
                                        }
				}
			}
			return result;
		}
		return null;
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
			listEnt = cargarNivelStr(listEnt);
			for (Entregables ent : listEnt) {
				cargarCamposEntregable(ent);
			}
		}

		return listEnt;
	}

	/*
        * 19-03-18 Nico: Redefino esta función para poder sacar los entregables que no estan asociados al coordinador loggeado.
	 */
	public static List<Entregables> cargarCamposCombos(List<Entregables> listEnt, Map<Integer, Boolean> auxAsocCoordEnt) {
		if (CollectionsUtils.isNotEmpty(listEnt)) {
			listEnt = cargarNivelStr(listEnt);
			for (Entregables ent : listEnt) {
				if (auxAsocCoordEnt.get(ent.getEntId())) {
					cargarCamposEntregable(ent);
				}
			}
		}

		return listEnt;
	}

	public static void cargarCamposEntregable(Entregables ent) {
		String ini = DatesUtils.toStringFormat(ent.getEntInicioDate(), ConstantesEstandares.CALENDAR_PATTERN);
		String fin = DatesUtils.toStringFormat(ent.getEntFinDate(), ConstantesEstandares.CALENDAR_PATTERN);
		String nivel = ent.getNivelStr() != null ? StringsUtils.concat(ent.getNivelStr(), " ") : "";
		ent.setNivelNombreCombo(StringsUtils.concat(nivel, ent.getEntNombre()));
		ent.setFechaNivelNombreCombo(StringsUtils.concat("(", ini, "-", fin, ") ", nivel, ent.getEntNombre()));
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
			if (DatesUtils.esMayor(calNow.getTime(), entFin)) {
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
	@Deprecated()
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
		}
		return entregables;
	}

	public Set<Entregables> setFromList(List<Entregables> entregables) {
		return new HashSet<Entregables>(entregables);
	}

	/**
	 * Ajusta la fecha de los entregables en base a las fechas de los hijos y de
	 * las dependencias.
	 *
	 * @param entregables
	 * @return
	 * @throws BusinessException
	 */
	public static List<Entregables> ajustarFechas(Set<Entregables> entregables) throws BusinessException {
		if (entregables == null || entregables.isEmpty()) {
			return new ArrayList<>();
		}
		return ajustarFechas(new ArrayList<>(entregables));
	}

	/**
	 * Ajusta la fecha de los entregables en base a las fechas de los hijos y de
	 * las dependencias.
	 *
	 * @param entregables
	 * @return
	 * @throws BusinessException
	 */
	public static List<Entregables> ajustarFechas(List<Entregables> entregables) throws BusinessException {
		if (entregables == null || entregables.isEmpty()) {
			return new ArrayList<>();
		}

		/**
		 * Se agrega para validar el que el nivel es correcto
		 */
		validarEntregablesNivel(entregables);

		//Ordenar los entregables por su id (EntId)
		entregables = sortById(entregables);
		//Armar el grafo de entregables y validar que no existan ciclos considerando las dependencias
		Object[] grafo = armarGrafoEntregables(entregables);
		if (grafo == null) {
			return entregables;
		}
		NodoEntregable nodoEntregableRaiz = (NodoEntregable) grafo[0];

		//imprimirGrafo(nodoEntregableRaiz, 2);
		Map<Integer, NodoEntregable> nodosEntregables = (Map<Integer, NodoEntregable>) grafo[1];
		//Repetir el procedimiento de ajuste de fechas hasta que en una pasada no cambie ninguna
		boolean huboCambios;
		do {
			huboCambios = ajustarFechas(nodoEntregableRaiz, nodosEntregables);
		} while (huboCambios);

		//Repetir el procedimiento de ajuste de fechas de la línea base hasta que en una pasada no cambie ninguna
		do {
			huboCambios = ajustarFechasLineaBase(nodoEntregableRaiz, nodosEntregables);
		} while (huboCambios);
		//Completar los campos faltantes
		for (NodoEntregable nodoEntregable : nodosEntregables.values()) {
			Entregables entregable = nodoEntregable.getEntregable();
			//Es un nodo padre si tiene al menos un hijo
			entregable.setEntParent(!nodoEntregable.getHijos().isEmpty());
			//Calcular la duración del entregable
			entregable.setEntDuracion(DatesUtils.diasEntreFechas(entregable.getEntInicioDate(), entregable.getEntFinDate()) + 1);
			//Si tiene línea base calcular su duración
			if (entregable.getEntInicioLineaBase() != null && entregable.getEntFinLineaBase() != null) {
				entregable.setEntDuracionLineaBase(DatesUtils.diasEntreFechas(entregable.getEntInicioLineaBaseDate(), entregable.getEntFinLineaBaseDate()) + 1);
			}
		}
		//Los elementos de la colección fueron modificados
		return entregables;
	}

	/**
	 * Arma un grafo de nodos entregables para poder detectar si existen ciclos
	 * y en caso de no haberlos para poder ajustar las fechas de cada entregable
	 * en base a las fechas de sus hijos y sus predecesores. El nodo a construir
	 * debería ser un grafo dirigido acíclico. Si hay ciclos no se puede
	 * serializar y por lo tanto habría una circularidad que causaría un ciclo
	 * infinito al intentar ajustar las fechas de los entregables.
	 *
	 * @param entregables La lista de entregables, ordenada por su identificador
	 * (EntId); el entregable con identificador 1 debe ser el primero y así
	 * sucesivamente.
	 * @return Un array de objetos; en el lugar 0 está el NodoEntregable raíz
	 * del grafo, y en lugar 1 un mapa de NodosEntregable con el EntId del
	 * entregable como clave.
	 * @throws Exception
	 */
	private static Object[] armarGrafoEntregables(List<Entregables> entregables) throws BusinessException {
		if (entregables == null || entregables.isEmpty()) {
			return null;
		}
		NodoEntregable nodoEntregableRaiz = null;
		NodoEntregable nodoEntregableActual = null;
		Map<Integer, NodoEntregable> nodosEntregables = new HashMap<>();
		for (Entregables entregable : entregables) {
			//Si no hay raíz es el primer entregable y por lo tanto la raíz del grafo.
			if (nodoEntregableRaiz == null) {
				nodoEntregableRaiz = new NodoEntregable(entregable, null);
				nodoEntregableActual = nodoEntregableRaiz;
			} else {
				//Ya se tiene la raíz del grafo, hay que determinar si es un hijo, un hermano o un nodo en un nivel anterior
				NodoEntregable nodoEntregableNuevo;
				//Si el nivel de este nodo es uno más que el nivel del nodo actual entonces es un hijo
				//Si es igual, es un hermano y hay que ponerlo en la lista del padre
				//Sino es un nodo en un nivel superior sin relación con el nodo actual
				if (nodoEntregableActual.getEntregable().getEntNivel() + 1 == entregable.getEntNivel()) {
					//Es un hijo del nodo actual
					nodoEntregableNuevo = new NodoEntregable(entregable, nodoEntregableActual);
					nodoEntregableActual.getHijos().add(nodoEntregableNuevo);
				} else if (entregable.getEntNivel().intValue() == nodoEntregableActual.getEntregable().getEntNivel().intValue()) {
					//Es un hermano del nodo actual
					if (nodoEntregableActual.getPadre() != null) {
						nodoEntregableNuevo = new NodoEntregable(entregable, nodoEntregableActual.getPadre());
						nodoEntregableActual.getPadre().getHijos().add(nodoEntregableNuevo);
					} else {
						throw new BusinessException("Se han encontrado dos nodos raíz");
					}
				} else {
					//No es ni hijo ni hermano, hay que buscar hacia arriba el primer nodo que tenga como nivel uno menos que el actual
					boolean encontrado = false;
					while (!encontrado) {
						nodoEntregableActual = nodoEntregableActual.getPadre();
						if (nodoEntregableActual.getEntregable().getEntNivel() + 1 == entregable.getEntNivel()) {
							encontrado = true;
						}
					}
					nodoEntregableNuevo = new NodoEntregable(entregable, nodoEntregableActual);
					nodoEntregableActual.getHijos().add(nodoEntregableNuevo);
				}
				nodoEntregableActual = nodoEntregableNuevo;
			}
			nodosEntregables.put(nodoEntregableActual.getEntregable().getEntId(), nodoEntregableActual);
		}
		//Verificar que no existan ciclos (lanza excepción si los hay)
		detectarCiclos(nodoEntregableRaiz, nodosEntregables);

		//Si no hay cilcos, validar que un nodo no tenga como predecesor a un descendiente
		for (NodoEntregable nodo : nodosEntregables.values()) {
			detectarPrecesorDescendiente(nodo);
		}

		//Respuesta
		Object[] ret = new Object[2];
		ret[0] = nodoEntregableRaiz;
		ret[1] = nodosEntregables;
		return ret;
	}

	/**
	 * Intenta determinar si algún nodo del grafo (ya se debe haber hecha la
	 * validación de ciclos) tiene como dependencia a un nodo que es
	 * descendiente del mismo. Esto es porque un nodo no puede tener como
	 * dependencia a un descendiente porque si lo tuviese tendría que comenzar
	 * cuando ese dependendiente termine, pero un nodo no puede terminar (y por
	 * tanto comenzar) más tarde que el último dependiente.
	 *
	 * @param nodo
	 */
	private static void detectarPrecesorDescendiente(NodoEntregable nodo) {
		if (nodo.getEntregable().getEntPredecesorFk() != null) {
			String[] predecesores = nodo.getEntregable().getEntPredecesorFk().split(",");
			for (String predecesor : predecesores) {
				String[] partes = predecesor.split(":");
				if (!partes[0].trim().isEmpty()) {
					Integer predecesorId = Integer.valueOf(partes[0].trim());
					if (esDescendiente(nodo, predecesorId)) {
						throw new BusinessException("Hay un entregable que tiene como predecesor a un entregable descendiente");
					}
				}
			}
		}
	}

	/**
	 * Determina si el nodo indicado es el nodoId, o alguno de sus hijos lo es
	 *
	 * @param nodo
	 * @param nodoId
	 * @return
	 */
	private static boolean esDescendiente(NodoEntregable nodo, Integer nodoId) {
		if (nodo == null || nodoId == null) {
			return false;
		}
		if (nodo.getEntregable().getEntId().equals(nodoId)) {
			return true;
		}
		if (nodo.getHijos() != null) {
			for (NodoEntregable nodo1 : nodo.getHijos()) {
				if (esDescendiente(nodo1, nodoId)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Intenta detectar si existe algún ciclo en el grafo de entregables. Si en
	 * el grafo de entregables existen ciclos no va a ser posible ajustar las
	 * fechas de los entregables ya que se entraría en un ciclo infinito. Se
	 * utiliza el algoritmo para ordenamiento topológico descrito en Wikipedia
	 * (https://en.wikipedia.org/wiki/Topological_sorting) con una mínima
	 * corrección (la inserción de los nodos se hace en la cola y no en la
	 * cabeza como dice el algoritmo) Si se detecta un ciclo lanza una
	 * excepción; si la ejecución termina sin excepción es porque no hay ningún
	 * ciclo.
	 *
	 * @param nodoVisitar Nodo de partida
	 * @param nodosEntregables Mapa conteniendo a todos los nodos, la clave debe
	 * ser el EntId del entregable correspondiente.
	 * @throws Exception
	 */
	//
	private static void detectarCiclos(NodoEntregable nodoVisitar, Map<Integer, NodoEntregable> nodosEntregables) throws BusinessException {
		if (nodoVisitar == null) {
			return;
		}
		if (nodoVisitar.isMarcaPermanente()) {
			return;
		}
		if (nodoVisitar.isMarcaTemporal()) {
			throw new BusinessException("Se ha detectado un ciclo en los entregables");
		}
		//Armar una lista de todos los nodos de los cuales depende el nodo actual;
		//incluye a los nodos hijos y a las dependencias
		List<NodoEntregable> nodosDestino = new LinkedList();
		nodosDestino.addAll(nodoVisitar.getHijos());
		if (nodoVisitar.getEntregable().getEntPredecesorFk() != null && !StringsUtils.isEmpty(nodoVisitar.getEntregable().getEntPredecesorFk())) {
			String[] predecesores = nodoVisitar.getEntregable().getEntPredecesorFk().split(",");
			for (String predecesor : predecesores) {
				String[] partes = predecesor.split(":");
				if (!partes[0].trim().isEmpty()) {
					Integer predecesorId = Integer.valueOf(partes[0].trim());
					if (nodosEntregables.containsKey(predecesorId)) {
						NodoEntregable nodoPred = nodosEntregables.get(predecesorId);
						if (!nodosDestino.contains(nodoPred)) {
							nodosDestino.add(nodoPred);
						}
					} else {
						throw new BusinessException("Hay un entregable que tiene como predecesor a un entregable inexistente");
					}
				}
			}
		}
		nodoVisitar.setMarcaTemporal(true);
		for (NodoEntregable nodoDestino : nodosDestino) {
			detectarCiclos(nodoDestino, nodosEntregables);
		}
		nodoVisitar.setMarcaPermanente(true);
	}

	/**
	 * Ajusta las fechas de los entregables según las fechas de sus hijos y
	 * dependencias. En cada invocación puede pasar tres cosas: se cambia la
	 * fecha de alguno de los hijos del nodo, se cambia la fecha de inicio del
	 * nodo actual o se cambia la fecha de fin del nodo actual. En cualquiera de
	 * esos casos el método retorna true indicando que hubo al menos una
	 * modificación. Si no ocurre ninguna de las cosas anteriores el método
	 * devuelve false para indicar que no hubo cambios. El resultado de este
	 * método debería ser utilizado por el invocador para determinar si tiene
	 * que hacer otra pasada o no: solo debería detenerse una vez que no ha
	 * habido ningún cambio en una pasada, porque de haber algún cambio se
	 * podría tener que volver a ajustar otro nodo.
	 *
	 * @param nodoEntregable Nodo raíz del grafo.
	 * @param nodosEntregables Mapa conteniendo a todos los nodos, la clave debe
	 * ser el EntId del entregable correspondiente.
	 * @return true si hubo al menos un cambio de fecha en este nodo o alguno de
	 * sus hijos, y false en otro caso.
	 * @throws Exception
	 */
	//Esto hay que hacerlo en varias pasadas porque en cada una se puede llegar a modificar un nodo ya calculado
	//Si retorna false es porque no se hicieron cambios en esta pasada
	private static boolean ajustarFechas(NodoEntregable nodoEntregable, Map<Integer, NodoEntregable> nodosEntregables) throws BusinessException {
		if (nodoEntregable == null) {
			return false;
		}

		//Si al terminar el proceso esta variable está en true entonces hay que hacer otra pasada completa
		boolean hayModificaciones = false;
		//En primer lugar se ajustan las fechas del entregable considerando la menor fecha de inicio y la mayor fecha de fin de los hijos
		//En segundo lugar se ajustan las fechas del entregable considerando las dependencias que tiene
		//1-Ajustar las fechas del entregable según los hijos
		if (!nodoEntregable.getHijos().isEmpty()) {
			Long fechaInicioMinima = null;
			Long fechaFinMaxima = null;
			//Determinar las fechas mínima de inicio y máxima de fin de todos los hijos del nodo actual
			for (NodoEntregable nodoEntregableHijo : nodoEntregable.getHijos()) {
				//1-Asegurar que cada hijo esté ajustado
				boolean huboModificaciones = ajustarFechas(nodoEntregableHijo, nodosEntregables);
				if (huboModificaciones) {
					//Indicar que hubo un cambio
					hayModificaciones = true;
				}
				//2-Ir seleccionando el inicio mínimo y el máximo
				if (fechaInicioMinima == null || fechaInicioMinima > nodoEntregableHijo.getEntregable().getEntInicio()) {
					fechaInicioMinima = nodoEntregableHijo.getEntregable().getEntInicio();
				}
				if (fechaFinMaxima == null || fechaFinMaxima < nodoEntregableHijo.getEntregable().getEntFin()) {
					fechaFinMaxima = nodoEntregableHijo.getEntregable().getEntFin();
				}
			}
			//Si la fecha de inicio determinada no es la misma que la del entregable, se ajusta el entregable
			if (nodoEntregable.getEntregable().getEntInicio() == null || !nodoEntregable.getEntregable().getEntInicio().equals(fechaInicioMinima)) {
				nodoEntregable.getEntregable().setEntInicio(fechaInicioMinima);
				//Indicar que hubo un cambio
				hayModificaciones = true;
			}
			//Si la fecha de fin determinada no es la misma que la del entregable, se ajusta el entregable
			if (nodoEntregable.getEntregable().getEntFin() == null || !nodoEntregable.getEntregable().getEntFin().equals(fechaFinMaxima)) {
				nodoEntregable.getEntregable().setEntFin(fechaFinMaxima);
				//Indicar que hubo un cambio
				hayModificaciones = true;
			}
		}
		//2-Ajustar las fechas según las dependencias (predecesores)
		//Tener en cuenta que solo las hojas (entregables sin hijos) pueden tener predecesores
		if (nodoEntregable.getEntregable().getEntPredecesorFk() != null && !StringsUtils.isEmpty(nodoEntregable.getEntregable().getEntPredecesorFk())) {
			//Los predecesores se separan por coma
			String[] predecesores = nodoEntregable.getEntregable().getEntPredecesorFk().split(",");
			//Fecha de inicio mínima según los predecesores
			Long minFechaInicio = null;
			for (String predecesor : predecesores) {
				//Cada predecesor puede venir en la forma "entregable" o "entregable:tiempo"; el primer caso es equivalente a "entregable:0"
				String[] partes = predecesor.split(":");
				if (!partes[0].trim().isEmpty()) {
					Integer predecesorId = Integer.valueOf(partes[0].trim());
					Integer predecesorDias = 1;
					if (partes.length > 1) {
						predecesorDias = Integer.valueOf(partes[1].trim());
					}
					//Buscar el nodo predecesor
					NodoEntregable nodoPredecesor = nodosEntregables.get(predecesorId);
					if (nodoPredecesor == null) {
						throw new BusinessException("El nodo " + nodoEntregable.getEntregable().getEntId() + " tiene como predecesor a un nodo inexistente (" + predecesorId + ")");
					}
					//Calcular cómo quedaría la fecha de inicio tomando como base la fecha de fin del predecesor
					//Puede ser que el predeceesor no tenga fecha aún si depende de sus hijos y aún no se calculó; en este caso hay que dejarlo como está y
					//se volverá a calcular en otra pasada subsiguiente.
					if (nodoPredecesor.getEntregable().getEntFin() != null) {
						Calendar calPredecesor = new GregorianCalendar();
						calPredecesor.setTimeInMillis(nodoPredecesor.getEntregable().getEntFin());
						calPredecesor.add(Calendar.DAY_OF_YEAR, predecesorDias);
						//Si esta fecha de inicio es mayor que la mínima fecha de inicio se actualiza la mínima fecha de inicio
						if (minFechaInicio == null || calPredecesor.getTimeInMillis() > minFechaInicio) {
							minFechaInicio = calPredecesor.getTimeInMillis();
						}
					}
				}
			}
			//Si se determinó una fecha mínimia de inicio se debe ajustar las fechas de inicio y fin
			if (minFechaInicio != null && minFechaInicio.longValue() != nodoEntregable.getEntregable().getEntInicio()) {
				long diferenciaInicioFin = nodoEntregable.getEntregable().getEntFin() - nodoEntregable.getEntregable().getEntInicio();
				nodoEntregable.getEntregable().setEntInicio(minFechaInicio);
				nodoEntregable.getEntregable().setEntFin(minFechaInicio + diferenciaInicioFin);
				//Indicar que hubo un cambio
				hayModificaciones = true;
			}
		}
		return hayModificaciones;
	}

	/**
	 * Ajusta las fechas de la línea base de los entregables según las fechas de
	 * sus hijos. El ajuste es similar al que se hace para las fechas del
	 * entregable con dos particularidades: no se toman en cuenta las
	 * dependencias (solo los hijos), y se debe tener en cuenta que es posible
	 * que algún hijo no tenga línea base pero otros sí, en cuyo caso se
	 * consideran solo los hijos que tienen línea base.
	 *
	 * @param nodoEntregable Nodo raíz del grafo.
	 * @param nodosEntregables Mapa conteniendo a todos los nodos, la clave debe
	 * ser el EntId del entregable correspondiente.
	 * @return true si hubo al menos un cambio de fecha de línea base en este
	 * nodo o alguno de sus hijos, y false en otro caso.
	 * @throws Exception
	 */
	private static boolean ajustarFechasLineaBase(NodoEntregable nodoEntregable, Map<Integer, NodoEntregable> nodosEntregables) throws BusinessException {

		if (nodoEntregable == null) {
			return false;
		}
		//Si al terminar el proceso esta variable está en true entonces hay que hacer otra pasada completa
		boolean hayModificaciones = false;
		if (!nodoEntregable.getHijos().isEmpty()) {
			Long fechaInicioMinima = null;
			Long fechaFinMaxima = null;
			//Determinar las fechas mínima de inicio y máxima de fin de todos los hijos del nodo actual
			for (NodoEntregable nodoEntregableHijo : nodoEntregable.getHijos()) {
				//1-Asegurar que cada hijo esté ajustado
				boolean huboModificaciones = ajustarFechasLineaBase(nodoEntregableHijo, nodosEntregables);
				if (huboModificaciones) {
					//Indicar que hubo un cambio
					hayModificaciones = true;
				}
				//2-Ir seleccionando el inicio mínimo y el máximo si tiene línea base
				if (nodoEntregableHijo.getEntregable().getEntInicioLineaBase() != null && nodoEntregableHijo.getEntregable().getEntFinLineaBase() != null) {
					if (fechaInicioMinima == null || fechaInicioMinima > nodoEntregableHijo.getEntregable().getEntInicioLineaBase()) {
						fechaInicioMinima = nodoEntregableHijo.getEntregable().getEntInicioLineaBase();
					}
					if (fechaFinMaxima == null || fechaFinMaxima < nodoEntregableHijo.getEntregable().getEntFinLineaBase()) {
						fechaFinMaxima = nodoEntregableHijo.getEntregable().getEntFinLineaBase();
					}
				}
			}
			if (fechaInicioMinima != null && fechaFinMaxima != null) {
				//Si la fecha de inicio determinada no es la misma que la del entregable, se ajusta el entregable
				if (nodoEntregable.getEntregable().getEntInicioLineaBase() == null || !nodoEntregable.getEntregable().getEntInicioLineaBase().equals(fechaInicioMinima)) {
					nodoEntregable.getEntregable().setEntInicioLineaBase(fechaInicioMinima);
					//Indicar que hubo un cambio
					hayModificaciones = true;
				}
				//Si la fecha de fin determinada no es la misma que la del entregable, se ajusta el entregable
				if (nodoEntregable.getEntregable().getEntFinLineaBase() == null || !nodoEntregable.getEntregable().getEntFinLineaBase().equals(fechaFinMaxima)) {
					nodoEntregable.getEntregable().setEntFinLineaBase(fechaFinMaxima);
					//Indicar que hubo un cambio
					hayModificaciones = true;
				}
			}
		}
		return hayModificaciones;
	}

	public static void validarEntregablesNivel(List<Entregables> entregables) throws BusinessException {

		Collections.sort(entregables, new Comparator<Entregables>() {
			@Override
			public int compare(Entregables o1, Entregables o2) {
				return o1.getEntId().compareTo(o2.getEntId());
			}
		});
		int cantNivelesCero = 0;
		if (entregables != null && entregables.size() > 1) {
			Iterator<Entregables> it = entregables.iterator();
			Entregables entAnterior = it.next();
			Entregables entActual;
			if (entAnterior.getEntNivel().equals(0)) {
				cantNivelesCero++;
			}
			do {
				entActual = it.next();
				if (entAnterior.getEntNivel() < 0
						|| entActual.getEntNivel() < 0
						|| entActual.getEntNivel() > entAnterior.getEntNivel() + 1) {
					throw new BusinessException("El entregable \"" + entActual.getEntNombre() + "\" tiene un nivel inválido");
				}
				entAnterior = entActual;
				if (entAnterior.getEntNivel().equals(0)) {
					cantNivelesCero++;
				}
			} while (it.hasNext());

			if (cantNivelesCero != 1) {
				throw new BusinessException("El conjunto de entregables tiene más de un nivel 0");
			}
		}

	}

	public static void asignarProgresoEsfuerzoPadres(List<Entregables> entregables) {

		asignarProgresoEsfuerzoPadres(new HashSet<>(entregables));
	}

	public static void asignarProgresoEsfuerzoPadres(Set<Entregables> entregables) {

             System.out.println("com.sofis.business.utils.EntregablesUtils.asignarProgresoEsfuerzoPadres()");
		Entregables raiz = null;
		for (Entregables e : entregables) {
			if (e.getEntId().equals(1)) {

				raiz = e;
			}
		}

		if (raiz == null) {
			return;
		}

		asignarProgresoEsfuerzoPadres(new ArrayList<>(entregables), raiz);
	}

	private static double[] asignarProgresoEsfuerzoPadres(List<Entregables> entregables, Entregables raiz) {

		if (!raiz.esEntParent()) {

			double[] result = new double[2];

			result[0] = raiz.getEntProgreso();
			result[1] = raiz.getEntEsfuerzo();

			return result;
		}

		List<Entregables> hijos = obtenerHijosDirectos(entregables, raiz);

		double progreso = 0;
		int esfuerzo = 0;
		for (Entregables e : hijos) {

			double[] datosHijos = asignarProgresoEsfuerzoPadres(entregables, e);

			progreso += datosHijos[0] * datosHijos[1];
			esfuerzo += datosHijos[1];
		}

		progreso = (esfuerzo > 0) ? (progreso / esfuerzo) : 0;

		int progresoInt = (progreso % 1 < 0.5)
				? new Double(Math.floor(progreso)).intValue()
				: new Double(Math.ceil(progreso)).intValue();

		raiz.setEntProgreso(progresoInt);
		raiz.setEntEsfuerzo(esfuerzo);

		double[] result = new double[2];

		result[0] = progreso;
		result[1] = esfuerzo;

		return result;
	}
        
        
        public static void asignarFechaInicioFinPadres(Set<Entregables> entregables) {

		Entregables raiz = null;
		for (Entregables e : entregables) {
			if (e.getEntId().equals(1)) {

				raiz = e;
			}
		}

		if (raiz == null) {
			return;
		}

		Long[] result = asignarFechaInicioFinPadres(new ArrayList<>(entregables), raiz);
                raiz.setEntInicio(result[0]);
                raiz.setEntFin(result[1]);
	}
        
        private static Long[] asignarFechaInicioFinPadres(List<Entregables> entregables, Entregables raiz) {

		if (!raiz.esEntParent()) {

			Long[] result = new Long[3];

			result[0] = raiz.getEntInicio();
			result[1] = raiz.getEntFin();
			result[2] = raiz.getEntDuracion().longValue();

			return result;
		}

		List<Entregables> hijos = obtenerHijosDirectos(entregables, raiz);

		Long fechaInicio = null;
		Long fechaFin = null;
		Long duracion = null;
		for (Entregables e : hijos) {

			Long[] datosHijos = asignarFechaInicioFinPadres(entregables, e);
                        
                        if (fechaInicio == null || fechaInicio >= datosHijos[0]){
                            fechaInicio = datosHijos[0];
                        }
                        if (fechaFin == null || fechaFin <= datosHijos[1]){
                            fechaFin = datosHijos[1];
                        }
		}
                if (fechaInicio != null && fechaFin != null){
                    duracion = TimeUnit.DAYS.convert(fechaFin-fechaInicio, TimeUnit.MILLISECONDS);
                }

		raiz.setEntInicio(fechaInicio);
		raiz.setEntFin(fechaFin);
		
		Long[] result = new Long[3];

		result[0] = fechaInicio;
		result[1] = fechaFin;
		result[2] = duracion;

		return result;
	}
        
        

	private static List<Entregables> obtenerHijosDirectos(List<Entregables> entregables, Entregables raiz) {

		int nivel = raiz.getEntNivel() + 1;

		List<Entregables> descendientes = obtenerHijos(entregables, raiz, false);

		List<Entregables> hijosDirectos = new ArrayList<>();

		for (Entregables e : descendientes) {

			if (e.getEntNivel().equals(nivel)) {
				hijosDirectos.add(e);
			}
		}

		return hijosDirectos;
	}

}
