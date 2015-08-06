package com.sofis.business.validations;

import com.sofis.business.utils.Utils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Programas;
import com.sofis.entities.data.SsUsuario;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ProgramaValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(Programas prog, SsUsuario usuario, Integer orgPk) throws BusinessException {

        BusinessException be = new BusinessException();
        be.addError(MensajesNegocio.ERROR_PROGRAMA);

        if (prog == null) {
            be.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            boolean isAlta = prog.getProgPk() == null;

            if (prog.isActivo()) {
                if (prog.getProgOrgFk() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_ORGANISMO);
                }
                if (StringsUtils.isEmpty(prog.getProgNombre())) {
                    be.addError(MensajesNegocio.ERROR_FICHA_NOMBRE);
                } else if (prog.getProgNombre().length() > 100) {
                    be.addError(Utils.mensajeLargoCampo("nombreProgProy", 100));
                }
                if (prog.getProgAreaFk() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_AREA);
                }
                if (prog.getProgUsrAdjuntoFk() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_ADJUNTO);
                }
                if (prog.getProgUsrSponsorFk() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_SPONSOR);
                }
                if (prog.getProgUsrGerenteFk() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_GERENTE);
                }
                if (prog.getProgSemaforoAmarillo() == null || prog.getProgSemaforoAmarillo() <= 0) {
                    be.addError(MensajesNegocio.ERROR_FICHA_SEMAFORO_AMARILLO);
                }
                if (prog.getProgSemaforoRojo() == null || prog.getProgSemaforoRojo() <= 0) {
                    be.addError(MensajesNegocio.ERROR_FICHA_SEMAFORO_ROJO);
                }
                if (prog.getActivo() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_ACTIVO);
                }

                if (prog.getProgPreFk() != null) {
                    if (prog.getProgPreFk().getFuenteFinanciamiento() != null
                            || prog.getProgPreFk().getPreBase() != null
                            || prog.getProgPreFk().getPreMoneda() != null) {
                        /*
                        if (prog.getProgPreFk().getFuenteFinanciamiento() == null) {
                            be.addError(MensajesNegocio.ERROR_PRESUPUESTO_FUENTE_FINANC);
                        }
                        if (prog.getProgPreFk().getPreBase() == null
                                || prog.getProgPreFk().getPreBase() <= 0) {
                            be.addError(MensajesNegocio.ERROR_PRESUPUESTO_BASE);
                        }
                        if (prog.getProgPreFk().getPreMoneda() == null) {
                            be.addError(MensajesNegocio.ERROR_PRESUPUESTO_MONEDA);
                        }
                        */
                    }
                }

                if (!isAlta) {
                    if (prog.getProgUsrPmofedFk() == null) {
                        be.addError(MensajesNegocio.ERROR_FICHA_PMOF);
                    }
                }
            }
        }

        if (be.getErrores().size() > 1) {
            logger.logp(Level.INFO, ProgramaValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
