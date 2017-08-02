package com.sofis.exceptions;

import java.util.ArrayList;
import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class TechnicalException extends GeneralException{

    private static final long serialVersionUID = 1L;
    
    public TechnicalException() {
        super("Error en TechnicalException");
    }
    
    public TechnicalException(String codigoError) {
        super(codigoError);
        errores = new ArrayList<>();
    }
    
    public TechnicalException(Throwable cause) {
        super(cause);
        errores = new ArrayList<>();
    }
    
    public TechnicalException(String msg, Throwable cause) {
        super(msg,cause);
        errores = new ArrayList<>();
    }
}
