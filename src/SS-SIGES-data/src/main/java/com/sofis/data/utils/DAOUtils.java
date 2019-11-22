package com.sofis.data.utils;

import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.sofisform.to.CriteriaTO;
import com.sofis.sofisform.to.MatchCriteriaTO;
import com.sofis.utils.CriteriaTOUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class DAOUtils {

    private static final Logger logger = Logger.getLogger(DAOUtils.class.getName()); 

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
	    int i = 0;
	    try {
		for (Object obj : listResult) {
		    i++;
		    logger.log(Level.INFO, "{0}) objeto:{1}", new Object[]{i, obj.toString()});
		}
	    } catch (Exception e) {
	    }
	    throw be;
	}
    }

    /**
     * Crea un MatchCriteriaTO en el cual todas las palabras aportadas en el
     * valor deben de coincidir en la property aportada. Las palabras van
     * separadas por espacio en blanco.
     *
     * @param property
     * @param valor
     * @return MatchCriteriaTO
     */
    public static CriteriaTO createMatchCriteriaTOString(String property, String valor) {
	if (!StringsUtils.isEmpty(property)) {
	    if (!StringsUtils.isEmpty(valor)) {
		String[] valorParse = valor.split(" ");

		CriteriaTO criteria;
		List<MatchCriteriaTO> criterios = new ArrayList<>();
		for (String word : valorParse) {

                    if (!StringsUtils.isEmpty(word)) {
                        MatchCriteriaTO matchWord = CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, property, word.trim());
                        criterios.add(matchWord);
                    }
		}

		if (criterios.size() == 1) {
		    criteria = criterios.get(0);
		} else {
		    criteria = CriteriaTOUtils.createANDTO(criterios.toArray(new CriteriaTO[0]));
		}

		return criteria;
	    } else {
		return CriteriaTOUtils.createMatchCriteriaTO(MatchCriteriaTO.types.CONTAINS, property, valor);
	    }
	}
	BusinessException be = new BusinessException();
	be.addError("Error al crear Match Criteria, propiedad vacía.");
	throw be;
    }
}
