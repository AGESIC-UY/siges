/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.exceptions;

import java.io.Serializable;

/**
 * 
 * @author sofis
 */
public class PGEProxyException extends Exception implements Serializable {

	private static final long serialVersionUID = 691061497395365181L;
	
	private String mensajeOriginal;

	public PGEProxyException(String message, Throwable cause) {
		super(message, cause);
	}

	public PGEProxyException(String message) {
		super(message);
	}
	
	public PGEProxyException(String message, String mensajeOriginal) {
		super(message);
		this.mensajeOriginal = mensajeOriginal;
	}

	public String getMensajeOriginal() {
		return mensajeOriginal;
	}
	
	

}

