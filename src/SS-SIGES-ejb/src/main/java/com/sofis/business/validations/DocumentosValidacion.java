package com.sofis.business.validations;

import com.sofis.business.properties.LabelsEJB;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.Documentos;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.DatesUtils;
import com.sofis.generico.utils.generalutils.StringsUtils;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class DocumentosValidacion {

    private static final Logger logger = Logger.getLogger(DocumentosValidacion.class.getName());

    public static boolean validar(Set<Documentos> docs) throws BusinessException {
        if (docs != null) {
            for (Documentos d : docs) {
                validar(d);
            }
        }
        return true;
    }

    public static boolean validar(Documentos doc) throws BusinessException {
        logger.logp(Level.FINEST, DocumentosValidacion.class.getName(), "validar", "Validar Documentos.");
        BusinessException be = new BusinessException();

        if (doc == null) {
            be.addError(MensajesNegocio.ERROR_DOCS_NULL);
        } else {
            if (doc.getDocsTipo() == null
                    || doc.getDocsTipo().getTipodocInstTipoDocFk() == null
                    || doc.getDocsTipo().getTipodocInstPk() < 0) {
                be.addError(MensajesNegocio.ERROR_DOCS_TIPO);
            }
            if (StringsUtils.isEmpty(doc.getDocsNombre())) {
                be.addError(MensajesNegocio.ERROR_DOCS_NOMBRE);
            }
            if (doc.getDocsNombre() != null
                    && doc.getDocsNombre().trim().equalsIgnoreCase(LabelsEJB.getValue("ficha_doc_pendiente").trim())) {
                be.addError(MensajesNegocio.ERROR_DOCS_NOMBRE_INCORRECTO);
            }
            if (doc.getDocsFecha() == null) {
                be.addError(MensajesNegocio.ERROR_DOCS_FECHA);
            } else if (DatesUtils.esMayor(doc.getDocsFecha(), new Date())) {
                be.addError(MensajesNegocio.ERROR_DOCS_FECHA_ANTERIOR);
            }
            if (doc.getDocsEstado() != null && !(doc.getDocsEstado().equals(0d)
                    || doc.getDocsEstado().equals(0.5d)
                    || doc.getDocsEstado().equals(1d))) {
                be.addError(MensajesNegocio.ERROR_DOCS_ESTADO);
            }
//            if(doc.getDocfileFile() == null){
//                be.addError(MensajesNegocio.ERROR_DOCS_FILE_NULL);
//            }
//            if(StringsUtils.isEmpty(doc.getDocfileNombre())){
//                be.addError(MensajesNegocio.ERROR_DOCS_FILE_NOMBRE);
//            }
        }

        if (be.getErrores().size() > 0) {
            logger.logp(Level.WARNING, DocumentosValidacion.class.getName(), "validar", be.getErrores().toString(), be);
            throw be;
        }

        return true;
    }
}
