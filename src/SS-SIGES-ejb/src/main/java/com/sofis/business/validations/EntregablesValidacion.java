package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Entregables;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class EntregablesValidacion {

        private static final Logger logger = Logger.getLogger(EntregablesValidacion.class.getName());

	public static final String DEPENDENCIA_REGEX = "^((\\d+(:[+-]?\\d*)?)((,((\\d+(:[+-]?\\d*)?))?)*)?)$";
	public static final Pattern DEPENDENCIA_PATTERN = Pattern.compile(DEPENDENCIA_REGEX);

	public static boolean validar(Set<Entregables> entList, Boolean lineaBaseRequerida) throws BusinessException {

		BusinessException be = new BusinessException();
		if (CollectionsUtils.isNotEmpty(entList)) {
			Map<Integer, Entregables> entIdMap = new HashMap<>();
			Integer entregablesNivel0 = 0;
			for (Entregables en : entList) {
				if (en.getEntId() == null) {
					be.addError(MensajesNegocio.ERROR_ENTREGABLES_ENT_ID_REQUERIDO);
					break;
				} else {
					if (entIdMap.containsKey(en.getEntId())) {
						be.addError(MensajesNegocio.ERROR_ENTREGABLES_ENT_ID_REPETIDO);
						break;
					} else {
						entIdMap.put(en.getEntId(), en);
					}
				}
				if (en.getEntNivel() != null && en.getEntNivel().equals(0)) {
					entregablesNivel0++;
					if (entregablesNivel0 > 1) {
						be.addError(MensajesNegocio.ERROR_ENTREGABLES_NIVEL_CERO_MULTI);
						break;
					}
				}

				//validar dependencia y fechas
			}

			if (be.getErrores().isEmpty()) {
				for (Entregables en : entList) {
					validar(en, lineaBaseRequerida);

					//si hay errores no sigo validando
					if (!be.getErrores().isEmpty()) {
						break;
					}

				}

			}

			if (be.getErrores().size() > 0) {
				logger.logp(Level.WARNING, EntregablesValidacion.class.getName(), "validar", be.getErrores().toString(), be);
				throw be;
			}

		}

		if (be.getErrores().size() > 0) {
			logger.logp(Level.WARNING, EntregablesValidacion.class.getName(), "validar", be.getErrores().toString(), be);
			throw be;
		}

		return true;
	}

	public static boolean validar(Entregables ent, Boolean lineaBaseRequerida) throws BusinessException {
		logger.finest("Validar Entregables.");
		BusinessException be = new BusinessException();

		if (ent == null) {
			be.addError(MensajesNegocio.ERROR_ENTREGABLE_NULL);
		} else {
			if (StringsUtils.isEmpty(ent.getEntNombre())) {
				be.addError(MensajesNegocio.ERROR_ENTREGABLE_NOMBRE);
			}
			if (ent.getCoordinadorUsuFk() == null && !ent.getEsReferencia()){
				be.addError(MensajesNegocio.ERROR_ENTREGABLE_COORDINADOR);
			}
			if (ent.getEntNivel() == null || ent.getEntNivel() < 0) {
				be.addError(MensajesNegocio.ERROR_ENTREGABLES_NIVEL_REQUERIDO);
			}
			if (ent.getEntFin() == null) {
				be.addError(MensajesNegocio.ERROR_ENTREGABLES_INICIO_REQUERIDO);
			}
			if (ent.getEntInicio() == null) {
				be.addError(MensajesNegocio.ERROR_ENTREGABLES_FIN_REQUERIDO);
			}

			/**
			 * BRUNO 01-06-17: Esto es para que valide el avance de los
			 * entregables
			 */
			if (ent.getEntFinEsHito()) {
				if (!(ent.getEntProgreso() == 0 || ent.getEntProgreso() == 100)) {
					be.addError(MensajesNegocio.ERROR_ENTREGABLES_HITO_PROGRESO);
				}
				if (!ent.getEntFin().equals(ent.getEntInicio())) {
					be.addError(MensajesNegocio.ERROR_ENTREGABLES_HITO_FECHAS);
				}
				if (ent.getEntFinLineaBase() != null && ent.getEntInicioLineaBase() != null) {
					if (!ent.getEntFinLineaBase().equals(ent.getEntInicioLineaBase())) {
						be.addError(MensajesNegocio.ERROR_ENTREGABLES_HITO_FECHAS_BASE);
					}
				}
				if (ent.getEntParent() != null && ent.getEntParent()) {
					//un hito no puede ser padre
					be.addError(MensajesNegocio.ERROR_ENTREGABLES_HITO_PADRE);
				}
				if (ent.getEntDuracion() != 1) {
					//duración del hito inválida
					be.addError(MensajesNegocio.ERROR_ENTREGABLES_HITO_DURACION_INVALIDA);
				}

			} else {
				if (ent.getEntProgreso() < 0 || ent.getEntProgreso() > 100) {
					be.addError(MensajesNegocio.ERROR_ENTREGABLES_PROGRESO_INVALIDO);
				}
				// TODO validar duración respecto a las fechas ingresadas
			}

			if (lineaBaseRequerida && ent.getEntInicioLineaBase() == null && ent.getEntFinLineaBase() == null) {
				be.addError(MensajesNegocio.ERROR_ENTREGABLES_LINEA_BASE_REQUERIDA);
			}

			if (!((ent.getEntInicioLineaBase() == null && ent.getEntFinLineaBase() == null)
				|| (ent.getEntInicioLineaBase() != null && ent.getEntFinLineaBase() != null))) {
				/**
				 * Verifica que ambos son nulos o ambos no nulos. No puede ser
				 * uno null y el otro no.
				 */
				be.addError(MensajesNegocio.ERROR_ENTREGABLES_LINEA_BASE_INVALIDA);
			}

			if (ent.getEntInicio() != null && ent.getEntFin() != null && ent.getEntInicio() > ent.getEntFin()) {
				be.addError(MensajesNegocio.ERROR_ENTREGABLES_INICIO_FIN_INVALIDO);
			}

//            if (ent.getEntInicioLineaBase() != null && ent.getEntFinLineaBase() != null && ent.getEntInicioLineaBase() > ent.getEntFinLineaBase()) {
//                be.addError(MensajesNegocio.ERROR_ENTREGABLES_INICIO_FIN_LINEA_BASE_INVALIDO);
//            }
		}

		if (be.getErrores().size() > 0) {
			logger.logp(Level.WARNING, EntregablesValidacion.class.getName(), "validar", be.getErrores().toString(), be);
			throw be;
		}

		return true;
	}

	/**
	 * Valida los entregables (ordenados). Este método es utilizado en las
	 * importaciones masivas de entregables. Si alguna validación falla lanza
	 * excepción. Si no lanza excepción es porque no encontró problemas.
	 *
	 * @param entregable
	 * @param entregables
	 */
	public static void validarEntregables(List<Entregables> entregables) throws BusinessException {

		if (entregables == null) {
			return;
		}

		for (int indiceEntregable = 0; indiceEntregable < entregables.size(); indiceEntregable++) {

			Entregables entregableAnterior = indiceEntregable == 0 ? null : entregables.get(indiceEntregable - 1);
			Entregables entregable = entregables.get(indiceEntregable);
			Entregables entregableSiguiente = indiceEntregable == entregables.size() - 1 ? null : entregables.get(indiceEntregable + 1);

			//El identificador es requerido
			if (entregable.getEntId() == null) {
				throw new BusinessException("El identificador es requerido");
			}
			//El identificador debe ser mayor o igual a 1
			if (entregable.getEntId() < 1) {
				throw new BusinessException("El identificador debe ser mayor o igual a 1");
			}
			//Si es el primer entregable el identificador debe ser 1
			if (entregableAnterior == null && entregable.getEntId() != 1) {
				throw new BusinessException("El identificador del primer entregable debe ser 1");
			}
			//Si no es el primer entregable el identificador debe ser igual al identificador del entregable anterior más 1
			if (entregableAnterior != null && entregable.getEntId() != entregableAnterior.getEntId() + 1) {
				throw new BusinessException("El identificador debe ser igual al identificador del entregable anterior más 1");
			}
			//El nombre es requerido
			if (entregable.getEntNombre() == null) {
				throw new BusinessException("El nombre es requerido");
			}
			//El nombre debe tener largo mayor a 1 y menor a 256
			if (entregable.getEntNombre().trim().isEmpty() || entregable.getEntNombre().trim().length() > 256) {
				throw new BusinessException("El nombre debe tener entre 1 y 256 caracteres");
			}
			//El nivel es requerido
			if (entregable.getEntNivel() == null) {
				throw new BusinessException("El nivel es requerido");
			}
			//El nivel debe ser mayor o igual a 0
			if (entregable.getEntNivel() < 0) {
				throw new BusinessException("El nivel debe ser mayor o igual a 0");
			}
			//Si es el primer entregable el nivel debe ser 0
			if (entregableAnterior == null && entregable.getEntNivel() != 0) {
				throw new BusinessException("El nivel del primer entregable debe ser 0");
			}
			//Si no es el primer entregable el nivel debe ser mayor a 0
			if (entregableAnterior != null && entregable.getEntNivel() == 0) {
				throw new BusinessException("El nivel del entregable debe ser mayor a 0. Excepto el primer entregable que tiene que tener nivel 0");
			}
			//Si no es el primer entregable el nivel debe ser menor, igual o 1 mayor que el nivel anterior
			if (entregableAnterior != null && entregable.getEntNivel() > entregableAnterior.getEntNivel() + 1) {
				throw new BusinessException("Si no es el primer entregable el nivel debe ser menor, igual o 1 mayor que el nivel del entregable anterior");
			}
			if (entregable.getEntFinEsHito() == null) {
				throw new BusinessException("El es_hito es requerido");
			}
			//Si es un hito no puede tener hijos
			if (entregable.getEntFinEsHito() && entregableSiguiente != null && entregable.getEntNivel() + 1 == entregableSiguiente.getEntNivel()) {
				throw new BusinessException("Si el entregable es un hito no puede tener hijos");
			}
			//El esfuerzo es requerido
			if (entregable.getEntEsfuerzo() == null) {
				throw new BusinessException("El esfuerzo es requerido");
			}
			//El esfuerzo debe ser mayor a 0
			if (entregable.getEntEsfuerzo() < 0) {
				throw new BusinessException("El esfuerzo debe ser igual o mayor a 0");
			}
			//El esfuerzo es requerido
			if (entregable.getEntProgreso() == null) {
				throw new BusinessException("El avance es requerido");
			}
			//El avance debe ser mayor o igual a 0 y menor o igual a 100
			if (entregable.getEntProgreso() < 0 || entregable.getEntProgreso() > 100) {
				throw new BusinessException("El avance debe estar entre 0 y 100 inclusivos");
			}
			//Si es hito el avance debe ser igual a 0 o 100
			if (entregable.getEntFinEsHito() && entregable.getEntProgreso() != 0 && entregable.getEntProgreso() != 100) {
				throw new BusinessException("Si el entregable es un hito el avance debe ser 0 o 100");
			}
			//La fecha de inicio es requerida
			if (entregable.getEntInicio() == null) {
				throw new BusinessException("La fecha de inicio es requerida");
			}
			//La fecha de fin es requerida
			if (entregable.getEntFin() == null) {
				throw new BusinessException("La fecha de fin es requerida");
			}
			//La fecha de fin debe ser igual o posterior a la fecha de inicio
			if (entregable.getEntFin() < entregable.getEntInicio()) {
				throw new BusinessException("La fecha de fin del entregable debe ser igual o posterior a la fecha de inicio");
			}
			//Si es un hito la fecha de inicio debe ser igual a la fecha de fin
			if (entregable.getEntFinEsHito() && !entregable.getEntInicio().equals(entregable.getEntFin())) {
				throw new BusinessException("Si el entregable es un hito las fechas de inicio y fin deben ser las mismas");
			}
			//Si tiene fecha de inicio de línea base debe tener fecha de fin de línea base
			if (entregable.getEntInicioLineaBase() != null && entregable.getEntFinLineaBase() == null || entregable.getEntInicioLineaBase() == null && entregable.getEntFinLineaBase() != null) {
				throw new BusinessException("Si tiene línea base debe tener fechas de inicio y fin");
			}
			//La fecha de fin debe ser igual o posterior a la fecha de inicio
			if (entregable.getEntFinLineaBase() != null && entregable.getEntInicioLineaBase() != null && entregable.getEntFinLineaBase() < entregable.getEntInicioLineaBase()) {
				throw new BusinessException("La fecha de fin de la línea base del entregable debe ser igual o posterior a la fecha de inicio de la línea base");
			}
			//Si tiene predecesores configurados debe cumplir el patrón
			if (entregable.getEntPredecesorFk() != null && !entregable.getEntPredecesorFk().matches(DEPENDENCIA_REGEX)) {
				throw new BusinessException("Los predecesores no tienen el formato correcto");
			}
			//Si tiene hijos no puede tener predecesores
			if (entregableSiguiente != null && entregable.getEntNivel() + 1 == entregableSiguiente.getEntNivel() && entregable.getEntPredecesorFk() != null) {
				throw new BusinessException("Si el entregable tiene hijos no puede tener predecesores");
			}
			//Es relevante es requerido
			if (entregable.getEntRelevante() == null) {
				throw new BusinessException("El es_relevante es requerido");
			}
			//El coordinador es requerido
			if (entregable.getCoordinadorUsuFk() == null) {
				throw new BusinessException("El coordinador es requerido");
			}
		}
	}

	public static void validarEntregablesPlantilla(List<Entregables> entregables) throws BusinessException {

		if (entregables == null) {
			return;
		}

		for (int indiceEntregable = 0; indiceEntregable < entregables.size(); indiceEntregable++) {

			Entregables entregableAnterior = indiceEntregable == 0 ? null : entregables.get(indiceEntregable - 1);
			Entregables entregable = entregables.get(indiceEntregable);
			Entregables entregableSiguiente = indiceEntregable == entregables.size() - 1 ? null : entregables.get(indiceEntregable + 1);

			//El identificador es requerido
			if (entregable.getEntId() == null) {
				throw new BusinessException("El identificador es requerido");
			}
			//El identificador debe ser mayor o igual a 1
			if (entregable.getEntId() < 1) {
				throw new BusinessException("El identificador debe ser mayor o igual a 1");
			}
			//Si es el primer entregable el identificador debe ser 1
			if (entregableAnterior == null && entregable.getEntId() != 1) {
				throw new BusinessException("El identificador del primer entregable debe ser 1");
			}
			//Si no es el primer entregable el identificador debe ser igual al identificador del entregable anterior más 1
			if (entregableAnterior != null && entregable.getEntId() != entregableAnterior.getEntId() + 1) {
				throw new BusinessException("El identificador debe ser igual al identificador del entregable anterior más 1");
			}
			//El nombre es requerido
			if (entregable.getEntNombre() == null) {
				throw new BusinessException("El nombre es requerido");
			}
			//El nombre debe tener largo mayor a 1 y menor a 256
			if (entregable.getEntNombre().trim().isEmpty() || entregable.getEntNombre().trim().length() > 256) {
				throw new BusinessException("El nombre debe tener entre 1 y 256 caracteres");
			}
			//El nivel es requerido
			if (entregable.getEntNivel() == null) {
				throw new BusinessException("El nivel es requerido");
			}
			//El nivel debe ser mayor o igual a 0
			if (entregable.getEntNivel() < 0) {
				throw new BusinessException("El nivel debe ser mayor o igual a 0");
			}
			//Si es el primer entregable el nivel debe ser 0
			if (entregableAnterior == null && entregable.getEntNivel() != 0) {
				throw new BusinessException("El nivel del primer entregable debe ser 0");
			}
			//Si no es el primer entregable el nivel debe ser mayor a 0
			if (entregableAnterior != null && entregable.getEntNivel() == 0) {
				throw new BusinessException("El nivel del entregable debe ser mayor a 0. Excepto el primer entregable que tiene que tener nivel 0");
			}
			//Si no es el primer entregable el nivel debe ser menor, igual o 1 mayor que el nivel anterior
			if (entregableAnterior != null && entregable.getEntNivel() > entregableAnterior.getEntNivel() + 1) {
				throw new BusinessException("Si no es el primer entregable el nivel debe ser menor, igual o 1 mayor que el nivel del entregable anterior");
			}
			if (entregable.getEntFinEsHito() == null) {
				throw new BusinessException("El es_hito es requerido");
			}
			//Si es un hito no puede tener hijos
			if (entregable.getEntFinEsHito() && entregableSiguiente != null && entregable.getEntNivel() + 1 == entregableSiguiente.getEntNivel()) {
				throw new BusinessException("Si el entregable es un hito no puede tener hijos");
			}
			//El esfuerzo es requerido
			if (entregable.getEntEsfuerzo() == null) {
				throw new BusinessException("El esfuerzo es requerido");
			}
			//El esfuerzo debe ser mayor a 0
			if (entregable.getEntEsfuerzo() < 0) {
				throw new BusinessException("El esfuerzo debe ser igual o mayor a 0");
			}

			//Si es hito el avance debe ser igual a 0 o 100
			if (entregable.getEntFinEsHito() && entregable.getEntProgreso() != 0 && entregable.getEntProgreso() != 100) {
				throw new BusinessException("Si el entregable es un hito el avance debe ser 0 o 100");
			}
			//La fecha de inicio es requerida
			if (entregable.getEntInicio() == null) {
				throw new BusinessException("La fecha de inicio es requerida");
			}
			//La fecha de fin es requerida
			if (entregable.getEntFin() == null) {
				throw new BusinessException("La fecha de fin es requerida");
			}
			//La fecha de fin debe ser igual o posterior a la fecha de inicio
			if (!entregable.getEntFinEsHito() && entregable.getEntDuracion() < 0) {
				throw new BusinessException("La duración debe ser mayor o igual a cero");
			}
			//Si es un hito la fecha de inicio debe ser igual a la fecha de fin
			if (entregable.getEntFinEsHito() && !entregable.getEntInicio().equals(entregable.getEntFin())) {
				throw new BusinessException("Si el entregable es un hito las fechas de inicio y fin deben ser las mismas");
			}
			//Si tiene predecesores configurados debe cumplir el patrón
			if (entregable.getEntPredecesorFk() != null && !StringsUtils.isEmpty(entregable.getEntPredecesorFk()) && !entregable.getEntPredecesorFk().matches(DEPENDENCIA_REGEX)) {
				throw new BusinessException("Los predecesores no tienen el formato correcto");
			}
			//Si tiene hijos no puede tener predecesores
			if (entregableSiguiente != null && entregable.getEntNivel() + 1 == entregableSiguiente.getEntNivel() && entregable.getEntPredecesorFk() != null
				&& !StringsUtils.isEmpty(entregable.getEntPredecesorFk()) && entregable.getEntPredecesorFk().matches(DEPENDENCIA_REGEX)) {
				throw new BusinessException("Si el entregable tiene hijos no puede tener predecesores");
			}

		}
	}

}
