package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Estados;
import com.sofis.entities.data.SsUsuario;
import com.sofis.entities.tipos.FichaTO;
import com.sofis.entities.utils.SsUsuariosUtils;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(FichaTO fichaTO, SsUsuario usuario, Integer orgPk) throws BusinessException {
        logger.fine("Validar datos de Ficha.");
        BusinessException be = new BusinessException();

        if (fichaTO == null) {
            be.addError(MensajesNegocio.ERROR_FICHA_NULL);
        } else {
            if (fichaTO.getOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_FICHA_ORGANISMO);
            }
            if (StringsUtils.isEmpty(fichaTO.getNombre())) {
                be.addError(MensajesNegocio.ERROR_FICHA_NOMBRE);
            }
            if (fichaTO.getAreaFk() == null) {
                be.addError(MensajesNegocio.ERROR_FICHA_AREA);
            }
            if (fichaTO.getUsrAdjuntoFk() == null) {
                be.addError(MensajesNegocio.ERROR_FICHA_ADJUNTO);
            }
            if (fichaTO.getUsrSponsorFk() == null) {
                be.addError(MensajesNegocio.ERROR_FICHA_SPONSOR);
            }
            if (fichaTO.getUsrGerenteFk() == null) {
                be.addError(MensajesNegocio.ERROR_FICHA_GERENTE);
            }
            if (fichaTO.getSemaforoAmarillo() == null || fichaTO.getSemaforoAmarillo() <= 0) {
                be.addError(MensajesNegocio.ERROR_FICHA_SEMAFORO_AMARILLO);
            }
            if (fichaTO.getSemaforoRojo() == null || fichaTO.getSemaforoRojo() <= 0) {
                be.addError(MensajesNegocio.ERROR_FICHA_SEMAFORO_ROJO);
            }

            if (fichaTO.isProyecto()) {
                if (fichaTO.getProyProgId() != null && (fichaTO.getPeso() == null || fichaTO.getPeso() < 0)) {
                    be.addError(MensajesNegocio.ERROR_FICHA_PESO);
                }
            }

            if (fichaTO.hasPk()) {
                if (fichaTO.getUsrPmofedFk() == null) {
                    be.addError(MensajesNegocio.ERROR_FICHA_PMOF);
                }
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
