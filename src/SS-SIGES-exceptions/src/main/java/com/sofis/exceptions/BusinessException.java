package com.sofis.exceptions;

import java.util.ArrayList;
import javax.ejb.ApplicationException;

/**
 *
 * @author Usuario
 */
@ApplicationException(rollback = true)
public class BusinessException extends GeneralException {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super("Error en BusinessException");
		errores = new ArrayList<>();
	}

	public BusinessException(String codigoError) {
		super(codigoError);
		errores = new ArrayList<>();
	}

	public BusinessException(Throwable cause) {
		super(cause);
		errores = new ArrayList<>();
	}

	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
		errores = new ArrayList<>();
	}

	@Override
	public String getMessage() {
		if (this.errores != null && !this.errores.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String s : this.getErrores()) {
				sb.append(s).append(" ");
			}
			return sb.toString();
		} else {
			return super.getMessage();
		}

	}

}
