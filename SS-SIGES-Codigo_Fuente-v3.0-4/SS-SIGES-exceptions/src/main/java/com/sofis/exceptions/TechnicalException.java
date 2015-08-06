package com.sofis.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class TechnicalException extends GeneralException{
    
    public TechnicalException() {
        super("Error en TechnicalException");
    }
    
    public TechnicalException(String codigoError) {
        super(codigoError);
    }
}
