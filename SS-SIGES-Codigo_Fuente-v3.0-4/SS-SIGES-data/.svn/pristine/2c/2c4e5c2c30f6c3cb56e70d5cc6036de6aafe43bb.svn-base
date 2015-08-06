package com.sofis.data.utils;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.exceptions.BusinessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class DAOUtils {

    private static final Logger logger = Logger.getLogger(ConstanteApp.LOGGER_NAME);

    /**
     * Para las query que retornan un objeto, en vez de usar getSingleResult
     * usar getResultList. Este metodo retorna el objeto, null, o una excepciÃ³n
     * si se obtienen mas de un resultado.
     *
     * @param listResult
     * @return Object
     */
    public static Object obtenerSingleResult(List<?> listResult) {
        if (listResult == null || listResult.isEmpty()) {
            return null;
        } else if (listResult.size() == 1) {
            return listResult.get(0);
        } else {
            BusinessException be = new BusinessException();
            be.addError(ConstantesErrores.ERROR_DEMASIADOS_RESULTADOS);
            logger.log(Level.SEVERE, be.getMessage(), be);
            throw be;
        }
    }
}
