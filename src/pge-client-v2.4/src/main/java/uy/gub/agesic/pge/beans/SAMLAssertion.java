/**
 * 
 */
package uy.gub.agesic.pge.beans;

import org.w3c.dom.Element;

/**
 * @author Guzman Llambias
 * @author Federico Sierra (ACCE)
 *
 */
public class SAMLAssertion {
	
	private Element assertion;

	/**
	 * @return the assertion
	 */
	public Element getAssertion() {
		return assertion;
	}

	/**
	 * @param assertion the assertion to set
	 */
	public void setAssertion(Element assertion) {
		this.assertion = assertion;
	}	
	
}
