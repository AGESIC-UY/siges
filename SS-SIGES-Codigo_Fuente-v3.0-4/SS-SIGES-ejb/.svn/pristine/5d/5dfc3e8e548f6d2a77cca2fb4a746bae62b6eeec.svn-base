package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.RegistrosHoras;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class RegistrosHorasValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(RegistrosHoras registrosHoras) throws BusinessException {

        BusinessException be = new BusinessException();

        if (registrosHoras == null) {
            be.addError(MensajesNegocio.ERROR_REGISTROSHORAS_NULL);
        } else {
            if (registrosHoras.getRhProyectoFk() == null
                    || registrosHoras.getRhProyectoFk().getProyPk() == null
                    || registrosHoras.getRhProyectoFk().getProyPk().intValue() == 0) {
                be.addError(MensajesNegocio.ERROR_REGISTROSHORAS_PROYECTO);
            }
            if (registrosHoras.getRhEntregableFk() == null
                    || registrosHoras.getRhEntregableFk().getEntPk() == null
                    || registrosHoras.getRhEntregableFk().getEntPk().intValue() == 0) {
                be.addError(MensajesNegocio.ERROR_REGISTROSHORAS_ENTREGABLE);
            }
            if (registrosHoras.getRhUsuarioFk() == null
                    || registrosHoras.getRhUsuarioFk().getUsuId() == null
                    || registrosHoras.getRhUsuarioFk().getUsuId().intValue() == 0) {
                be.addError(MensajesNegocio.ERROR_REGISTROSHORAS_USUARIO);
            }
            if (registrosHoras.getRhFecha() == null
                    || DatesUtils.esMayor(registrosHoras.getRhFecha(), new Date())) {
                be.addError(MensajesNegocio.ERROR_REGISTROSHORAS_FECHA);
            }
            if (registrosHoras.getRhHoras() == null
                    || registrosHoras.getRhHoras() < 0) {
                be.addError(MensajesNegocio.ERROR_REGISTROSHORAS_HORAS);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, RegistrosHorasValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
