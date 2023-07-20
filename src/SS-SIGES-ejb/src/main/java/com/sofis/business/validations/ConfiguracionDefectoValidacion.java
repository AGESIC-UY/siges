package com.sofis.business.validations;

import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstantesErrores;
import com.sofis.entities.data.ConfiguracionDefecto;
import com.sofis.exceptions.BusinessException;
import com.sofis.generico.utils.generalutils.StringsUtils;

public class ConfiguracionDefectoValidacion {

    public static boolean validar(ConfiguracionDefecto cnf) throws BusinessException {
        boolean respuesta = true;
        BusinessException ge = new BusinessException();
        if (cnf == null) {
            ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
        } else {
            if (StringsUtils.isEmpty(cnf.getCodigo())) {
                ge.addError(ConstantesErrores.ERROR_CODIGO_VACIO);
            }
            if (StringsUtils.isEmpty(cnf.getValor()) && !StringsUtils.isEmpty(cnf.getCodigo()) &&
                    !ConfiguracionCodigos.PAGO_FILTRO_PROCEDIMIENTO_COMPRA.equalsIgnoreCase(cnf.getCodigo())
                    ) {
                ge.addError(ConstantesErrores.ERROR_DATO_VACIO);
            }

        }
        if (ge.getErrores().size() > 0) {
            throw ge;
        }
        return respuesta;
    }

}
