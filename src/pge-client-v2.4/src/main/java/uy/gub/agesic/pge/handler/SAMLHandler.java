package uy.gub.agesic.pge.handler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Response;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;
import org.picketlink.identity.federation.core.saml.v1.SAML11Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.gub.agesic.pge.AgesicConstants;
import uy.gub.agesic.pge.core.config.ConfigProperties;
import uy.gub.agesic.pge.core.ws.Constants;
import uy.gub.agesic.pge.core.ws.Util;

/**
 * @author Guzman Llambias
 * @author Federico Sierra (ACCE)
 * 
 */
public class SAMLHandler implements SOAPHandler<SOAPMessageContext> {

	private static Logger log = Logger.getLogger(SAMLHandler.class);
	protected static Set<QName> headers;

	static {
		HashSet<QName> set = new HashSet<QName>();
		set.add(Constants.WSSE_HEADER_QNAME);
		headers = Collections.unmodifiableSet(set);
	}

	/**
	 * Gets the header blocks that can be processed by this Handler instance.
	 */
	public Set<QName> getHeaders() {
		return headers;
	}

	@SuppressWarnings("unchecked")
	public boolean handleMessage(SOAPMessageContext msgContext) {
		Boolean outbound = (Boolean) msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outbound == null)
			throw new IllegalStateException("Cannot obtain required property: "	+ MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outbound) {
			SOAPMessageContext ctx = (SOAPMessageContext) msgContext;
			SOAPMessage soapMessage = ctx.getMessage();
			Response<SOAPMessage> response = (Response<SOAPMessage>) ctx.get(AgesicConstants.SAML1_PROPERTY);
			Long stsTimeOut = (Long) ctx.get(ConfigProperties.STS_TIMEOUT);
			Element assertion = null;
			try {
				SOAPMessage message = response.get(stsTimeOut, TimeUnit.MILLISECONDS);
				assertion = extractSecurityToken(message);
			} catch (Exception e) {
				log.error(e, e);
			}finally{
				if(response != null){
					try{
						response.cancel(true);
					}catch(Exception e){}
				}
			}
			if (assertion == null) {
				log.error("Outbound::No Assertion was found on the message context. Returning");
				return false;
			}
			
			Document document = soapMessage.getSOAPPart();
			Element soapHeader = Util.findOrCreateSoapHeader(document.getDocumentElement());
			try {
				Element wsse = getSecurityHeaderElement(document);
				wsse.setAttributeNS(soapHeader.getNamespaceURI(), soapHeader.getPrefix() + ":mustUnderstand", "1");
				if (assertion != null) {
					if (document != assertion.getOwnerDocument()) {
						wsse.appendChild(document.importNode(assertion, true));
					} else {
						wsse.appendChild(assertion);
					}
				}
				soapHeader.insertBefore(wsse, soapHeader.getFirstChild());
			} catch (Exception e) {
				log.error(e, e);
				return false;
			}
		}

		return true;
	}

	public boolean handleFault(SOAPMessageContext messagecontext) {
		try {
			SOAPMessage msg = messagecontext.getMessage();
			if (msg.getSOAPBody().getFault() != null) {
				String detailName = null;
				try {
					detailName = msg.getSOAPBody().getFault().getDetail()
							.getFirstChild().getLocalName();
					log.error(detailName);
				} catch (Exception e) {
				}
			}
		} catch (SOAPException e) {
			log.error(e, e);
		}
		return true;
	}

	public void close(MessageContext messageContext) {
	}
	
	protected Element getSecurityHeaderElement(Document document) {
		Element element = document.createElementNS(Constants.WSSE_NS, Constants.WSSE_HEADER);
		Util.addNamespace(element, Constants.WSSE_PREFIX, Constants.WSSE_NS);
		Util.addNamespace(element, Constants.WSU_PREFIX, Constants.WSU_NS);
		Util.addNamespace(element, Constants.XML_ENCRYPTION_PREFIX,	Constants.XML_SIGNATURE_NS);
		return element;
	}
	
	private Element extractSecurityToken(final SOAPMessage message) {
        try {
        	Document doc = message.getSOAPBody().extractContentAsDocument();
            NodeList nl = doc.getElementsByTagNameNS(SAML11Constants.ASSERTION_11_NSURI, "Assertion");
            if (nl.getLength() == 0) {
                nl = doc.getElementsByTagNameNS("*", "Assertion");
                if (nl.getLength() == 0)
                    nl = doc.getElementsByTagName("saml:Assertion");
                if (nl.getLength() == 0)
                    return null;
            }
            return (Element) nl.item(0);
            
        } catch (final SOAPException e) {
        	log.error(e, e);
        	return null;
        }
    }
	
}
