package com.sofis.entities.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Documentos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class FichaDocumentosValidacion {

    private static final Logger logger = Logger.getLogger(FichaDocumentosValidacion.class.getName());

    public static boolean validar(Documentos doc) throws BusinessException {
        logger.finest("Validar Ficha Documentos.");
        BusinessException be = new BusinessException();

        if (doc == null) {
            be.addError(MensajesNegocio.ERROR_DOCS_NULL);
        } else {
            if (StringsUtils.isEmpty(doc.getDocsNombre())) {
                be.addError(MensajesNegocio.ERROR_DOCS_NOMBRE);
            }
            if (doc.getDocsTipo() == null
                    || doc.getDocsTipo().getTipodocInstTipoDocFk() == null
                    || doc.getDocsTipo().getTipodocInstPk() < 0) {
                be.addError(MensajesNegocio.ERROR_DOCS_TIPO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, null, be);
            throw be;
        }

        return true;
    }
}
