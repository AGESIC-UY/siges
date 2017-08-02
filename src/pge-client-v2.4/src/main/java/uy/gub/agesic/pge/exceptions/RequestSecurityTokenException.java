/**
 * 
 */
package uy.gub.agesic.pge.exceptions;


/**
 * @author Guzman Llambias
 *
 */
public class RequestSecurityTokenException extends Exception {

	private static final long serialVersionUID = 7230871535650935860L;

	public RequestSecurityTokenException(String message){
		super(message);		
	}

	public RequestSecurityTokenException(Exception e) {
		super(e);
	}
	
	public RequestSecurityTokenException(String message, Exception e) {
		super(message, e);
	}
}
