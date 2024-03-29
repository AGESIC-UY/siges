package com.sofis.business.validations;

import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.entities.constantes.MensajesNegocio;
import com.sofis.entities.data.ProdMes;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.CollectionsUtils;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class ProdMesValidacion {

    private static final Logger logger = Logger.getLogger(ProdMesValidacion.class.getName());

    public static boolean validar(Collection<ProdMes> colProdMes) throws BusinessException {
        if (CollectionsUtils.isNotEmpty(colProdMes)) {
            for (ProdMes prodMes : colProdMes) {
                validar(prodMes);
            }
        }
        return true;
    }

    public static boolean validar(ProdMes prodMes) throws BusinessException {
        BusinessException be = new BusinessException();

        if (prodMes == null) {
            be.addError(MensajesNegocio.ERROR_PRODMES_NULL);
        } else {
            if (prodMes.getProdmesPlan() == null) {
                be.addError(MensajesNegocio.ERROR_PRODMES_PLAN);
            } else if (prodMes.getProdmesPlan() < 0) {
                be.addError(MensajesNegocio.ERROR_PRODMES_PLAN_VALOR);
            }
            if (prodMes.getProdmesReal() == null) {
                be.addError(MensajesNegocio.ERROR_PRODMES_REAL);
            } else if (prodMes.getProdmesPlan() < 0) {
                be.addError(MensajesNegocio.ERROR_PRODMES_REAL_VALOR);
            }
            if (prodMes.getProdmesAcuPlan() == null) {
                be.addError(MensajesNegocio.ERROR_PRODMES_ACU_PLAN);
            }
            if (prodMes.getProdmesAcuReal() == null) {
                be.addError(MensajesNegocio.ERROR_PRODMES_ACU_REAL);
            }
            if (prodMes.getProdmesMes() <= 0) {
                be.addError(MensajesNegocio.ERROR_PRODMES_MES);
            }
            if (prodMes.getProdmesAnio() <= 0) {
                be.addError(MensajesNegocio.ERROR_PRODMES_ANIO);
            }
        }

        if (be.getErrores().size() > 0) {
            logger.log(Level.WARNING, "validar", be);
            throw be;
        }

        return true;
    }
}
