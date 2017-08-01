/*
 *  Clase desarrollada por Sofis Solutions para el
 *  proyecto SGREC.
 */
package com.sofis.persistence.dao.exceptions;

import java.io.Serializable;

/**
 * Clase desarrollada por Sofis Solutions para el sistema SGREC
 *
 * @author Sofis Solutions
 */
public class DAOGeneralException extends Exception implements Serializable {

    public DAOGeneralException() {
        super();
    }

    public DAOGeneralException(String msg) {
        super(msg);
    }

    public DAOGeneralException(Throwable cause) {
        super(cause);
    }

    public DAOGeneralException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
