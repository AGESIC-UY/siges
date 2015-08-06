package com.sofis.exceptions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class GeneralException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private String codigo = "OK";
    private Exception ex;
    List<String> errores = new ArrayList();
    
    public GeneralException() {
        super("Error en generalException");
    }
    
    public GeneralException(String msg) {
        super(msg);
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public Exception getEx() {
        return ex;
    }
    
    public void setEx(Exception ex) {
        this.ex = ex;
    }
    
    public List<String> getErrores() {
        return errores;
    }
    
    public void setErrores(List<String> errores) {
        this.errores = errores;
    }
    
    public void addError(String codigoError) {
        this.codigo = "ERR";
        this.errores.add(codigoError);
    }
    
    public void addErrores(List<String> listErrores) {
        if (listErrores != null) {
            for (String error : listErrores) {
                addError(error);
            }
        }
    }
}
