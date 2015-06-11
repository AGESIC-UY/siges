package com.sofis.exceptions;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = false)
public class MailException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private String codigo = "OK";
    private Exception ex;
    List<String> errores = new ArrayList();
    
    public MailException() {
        super("Error en MailException");
        errores = new ArrayList();
    }
    
    public MailException(String codigoError) {
        super(codigoError);
        errores = new ArrayList();
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
        this.codigo="ERR";
        this.errores.add(codigoError);
    }
}
