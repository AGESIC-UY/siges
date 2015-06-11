package com.sofis.exceptions;

import java.util.ArrayList;
import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class BusinessException extends GeneralException {
    
    public BusinessException() {
        super("Error en BusinessException");
        errores = new ArrayList<>();
    }
    
    public BusinessException(String codigoError) {
        super(codigoError);
        errores = new ArrayList<>();
    }
}
