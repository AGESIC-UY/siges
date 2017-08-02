package uy.gub.agesic.pge.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

import uy.gub.agesic.pge.AgesicConstants;


/**
 * Este Handler lo que hace es setear el atributo actor con el valor actorDP.
 * Esto se debe a que al setear usuario/password por WS-Security
 * 
 * @author mcaponi
 *
 */
@SuppressWarnings("rawtypes")
public class WSSecurityUsernamePasswordHandler implements SOAPHandler<SOAPMessageContext> {

	private static Logger log = Logger.getLogger(WSSecurityUsernamePasswordHandler.class);
	
	
	public boolean handleMessage(SOAPMessageContext msgContext) {
		Boolean outbound = (Boolean) msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outbound == null)
			throw new IllegalStateException("Cannot obtain required property: "	+ MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outbound) {
			SOAPMessage soapMessage = msgContext.getMessage();
			String samlActor = (String) msgContext.get(AgesicConstants.SAML_ACTOR);
			dispatchMessage(samlActor, soapMessage);
		}
		return true;
	}
		
	private void dispatchMessage(String samlActor, SOAPMessage soapMessage) {
		try {
			SOAPEnvelope soapEnv = soapMessage.getSOAPPart().getEnvelope();
			SOAPHeader soapHeader = soapEnv.getHeader();
			
			SOAPHeaderElement samlHeader = getSAMLSoapHeader(soapHeader);
			samlHeader.setActor(samlActor);

		} catch (SOAPException e) {
			log.error("Internal error occured handling outbound message:", e);
		}
	}

	
	private SOAPHeaderElement getSAMLSoapHeader(SOAPHeader soapHeader) {
		SOAPHeaderElement headerElement = null;
		Iterator allHeaders = soapHeader.examineAllHeaderElements();
		boolean found = false;
		
		while (allHeaders.hasNext() && !found) {
        	 headerElement = (SOAPHeaderElement)allHeaders.next();
             Name headerName = headerElement.getElementName();
             
             if ("wsse:Security".equalsIgnoreCase(headerName.getQualifiedName())) {
            	 if ("Assertion".equalsIgnoreCase(headerElement.getFirstChild().getLocalName())) {
            		found = true;
				}
            }
        }

		return headerElement;
	}

	@Override
	public void close(MessageContext arg0) {
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
	
	
}
