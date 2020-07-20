package com.sofis.business.validations;

import com.sofis.business.utils.Utils;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Ambito;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Validaciones para Ambito
 * @author mgarcia
 */
public class AmbitoValidacion {

    private static final Logger logger = Logger.getLogger(AmbitoValidacion.class.getName());

    public static boolean validar(Ambito ambito) throws BusinessException {
        BusinessException be = new BusinessException();

        if (ambito == null) {
            be.addError(MensajesNegocio.ERROR_AMBITO_NULL);
        } else {
            if (StringsUtils.isEmpty(ambito.getAmbNombre())) {
                be.addError(MensajesNegocio.ERROR_AMBITO_NOMBRE);
            }else if (ambito.getAmbNombre().length() > Ambito.NOMBRE_LENGHT) {
                be.addError(Utils.mensajeLargoCampo("ambito_head_nombre", ambito.getAmbOrgFk().getOrgPk(), Ambito.NOMBRE_LENGHT));
            }

            if (ambito.getAmbOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_AMBITO_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
