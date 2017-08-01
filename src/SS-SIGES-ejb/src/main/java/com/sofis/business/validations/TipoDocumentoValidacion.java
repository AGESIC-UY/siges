package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Moneda;
import com.sofis.entities.data.TipoDocumento;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class TipoDocumentoValidacion {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    public static boolean validar(TipoDocumento tipoDoc) throws BusinessException {
        BusinessException be = new BusinessException();

        if (tipoDoc == null) {
            be.addError(MensajesNegocio.ERROR_TIPO_DOC_NULL);
        } else {
            if (StringsUtils.isEmpty(tipoDoc.getTipodocNombre())) {
                be.addError(MensajesNegocio.ERROR_TIPO_DOC_NOMBRE);
            }

            if (tipoDoc.getTipodocExigidoDesde() == null) {
                be.addError(MensajesNegocio.ERROR_TIPO_DOC_EXIGIDO_DESDE);
            }

            if (tipoDoc.getTipodocPeso() == null) {
                be.addError(MensajesNegocio.ERROR_TIPO_DOC_PESO);
            }

            if (tipoDoc.getTipoDocOrgFk() == null) {
                be.addError(MensajesNegocio.ERROR_TIPO_DOC_ORG);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
