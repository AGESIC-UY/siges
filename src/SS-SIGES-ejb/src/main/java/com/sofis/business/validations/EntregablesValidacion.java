package com.sofis.business.validations;

import com.sofis.business.utils.EntregablesUtils;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class EntregablesValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    private static final String DEPENDENCIA_REGEX = "^((\\d+(:[+-]?\\d*)?)((,((\\d+(:[+-]?\\d*)?))?)*)?)$";
    public static final Pattern DEPENDENCIA_PATTERN = Pattern.compile(DEPENDENCIA_REGEX);

    public static boolean validar(Set<Entregables> entList, Boolean lineaBaseRequerida) throws BusinessException {

        BusinessException be = new BusinessException();
        List<Entregables> entListAux = new ArrayList<>(entList);
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

                    /**
                     * BRUNO 03-06-17: Validar fechas de hijos si es padre
                     */
//                    if (en.getEntParent()) {
//                        Boolean tieneHijos = false;
//                        Integer entIdHijo = en.getEntId() + 1;
//                        Entregables enHijo = entIdMap.get(entIdHijo);
//                        while (enHijo != null
//                                && enHijo.getEntNivel() != null
//                                && en.getEntNivel() != null
//                                && enHijo.getEntNivel().equals(en.getEntNivel() + 1)) {
//                            
//                            tieneHijos = true;
//                            if (enHijo.getEntInicio() < en.getEntInicio()) {
//                                be.addError(MensajesNegocio.ERROR_ENTREGABLES_INICIO_HIJO_INVALIDO);
//                                break;
//                            }
//                            if (enHijo.getEntFin() > en.getEntFin()) {
//                                be.addError(MensajesNegocio.ERROR_ENTREGABLES_FIN_HIJO_INVALIDO);
//                                break;
//                            }
//
//                            if (lineaBaseRequerida) {
//                                if (enHijo.getEntInicioLineaBase() < en.getEntInicioLineaBase()) {
//                                    be.addError(MensajesNegocio.ERROR_ENTREGABLES_INICIO_LB_HIJO_INVALIDO);
//                                    break;
//                                }
//                                if (enHijo.getEntFinLineaBase() > en.getEntFinLineaBase()) {
//                                    be.addError(MensajesNegocio.ERROR_ENTREGABLES_FIN_LB_HIJO_INVALIDO);
//                                    break;
//                                }
//                            }
//
//                            entIdHijo++;
//                            enHijo = entIdMap.get(entIdHijo);
//                            
//                        }
//                        if (!tieneHijos) {
//                            be.addError(MensajesNegocio.ERROR_ENTREGABLES_FIN_LB_HIJO_INVALIDO);
//                            break;
//                        }
//
//                        if (!be.getErrores().isEmpty()) {
//                            break;
//                        }
//
//                    }
                    /**
                     * BRUNO 03-06-17: Validar fechas de dependenciasLa fecha de inicio de la línea base no puede ser mayor a la fin de la línea base
                     */
//                    if (be.getErrores().isEmpty()) {
//                        if (en.getEntPredecesorFk() != null && !StringsUtils.isEmpty(en.getEntPredecesorFk())) {
//                            try {
//
//                                Matcher matcher = DEPENDENCIA_PATTERN.matcher(en.getEntPredecesorFk());
//                                if (matcher.matches()) {
//                                    String[] dependencias = en.getEntPredecesorFk().split(",");
//                                    String[] de;
//                                    Integer[] elem;
//                                    Entregables entDep = null;
//                                    Long maxFinDep = 0L;
//                                    Date finDepAux = null;
//                                    for (String d : dependencias) {
//                                        de = d.split(":");
//                                        Integer entPreId = Integer.parseInt(de[0]);
//                                        Integer dias = de.length > 1 && !StringsUtils.isEmpty(de[1])
//                                                ? Integer.parseInt(de[1])
//                                                : null;
//                                        elem = new Integer[2];
//                                        elem[0] = entPreId;
//                                        elem[1] = dias;
//                                        entDep = entIdMap.get(elem[0]);
//                                        if (entDep == null) {
//                                            be.addError(MensajesNegocio.ERROR_ENTREGABLES_PREDECESOR_NO_EXISTE);
//                                            throw be;
//                                        }
//                                        if(entDep.getEntId().equals(en.getEntId())){
//                                            be.addError(MensajesNegocio.ERROR_ENTREGABLES_DEPENDENCIA_CICLICA);
//                                            throw be;
//                                        }
//                                        if(EntregablesUtils.entregableEsHijo(entListAux, entDep, en)){
//                                            be.addError(MensajesNegocio.ERROR_ENTREGABLES_DEPENDENCIA_PADRE);
//                                            throw be;
//                                        }
//                                        if (elem[1] != null) {
//                                            finDepAux = DatesUtils.incrementarDias(entDep.getEntFinDate(), elem[1]);
//                                        } else {
//                                            finDepAux = entDep.getEntFinDate();
//                                        }
//                                        maxFinDep = maxFinDep < finDepAux.getTime() ? finDepAux.getTime() : maxFinDep;
//                                    }
//
//                                    Integer dias = DatesUtils.diasEntreFechas(new Date(maxFinDep), en.getEntInicioDate());
//                                    if(dias != 1){
//                                        be.addError(MensajesNegocio.ERROR_ENTREGABLES_FIN_INICIO_DEPENDENCIA);
//                                    }
//
//                                } else {
//                                    be.addError(MensajesNegocio.ERROR_ENTREGABLES_DEPENDENCIAS_INVALIDA_REGEX);
//                                    break;
//                                }
//
//                            } catch (BusinessException ex) {
//                                throw ex;
//                            } catch (Exception ex) {
//                                BusinessException be2 = new BusinessException(ex);
//                                be2.addErrores(be.getErrores());
//                                throw be2;
//                            }
//
//                        }
//                    }

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
            if (ent.getCoordinadorUsuFk() == null) {
                be.addError(MensajesNegocio.ERROR_ENTREGABLE_COORDINADOR);
            }
            if (ent.getEntNivel() == null || ent.getEntNivel() < 0){
                be.addError(MensajesNegocio.ERROR_ENTREGABLES_NIVEL_REQUERIDO);
            }
            if (ent.getEntFin() == null) {
                be.addError(MensajesNegocio.ERROR_ENTREGABLES_INICIO_REQUERIDO);
            }
            if (ent.getEntInicio() == null) {
                be.addError(MensajesNegocio.ERROR_ENTREGABLES_FIN_REQUERIDO);
            }

            /**
             * BRUNO 01-06-17: Esto es para que valide el progreso de los
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
}
