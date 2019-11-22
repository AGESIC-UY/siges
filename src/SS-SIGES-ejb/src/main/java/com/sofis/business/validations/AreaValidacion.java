package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Areas;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AreaValidacion {

    private static final Logger logger = Logger.getLogger(AreaValidacion.class.getName());

    public static boolean validar(Areas area) throws BusinessException {
        BusinessException be = new BusinessException();

        if (area == null) {
            be.addError(MensajesNegocio.ERROR_AREAS_NULL);
        } else {
            if (StringsUtils.isEmpty(area.getAreaNombre())) {
                be.addError(MensajesNegocio.ERROR_AREAS_NOMBRE);
            }

            if (area.getAreaPadre() != null 
                    && area.getAreaPadre().getAreaPk().equals(area.getAreaPk())) {
                be.addError(MensajesNegocio.ERROR_AREAS_PADRE);
            }

            if (area.getAreaOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_AREAS_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
