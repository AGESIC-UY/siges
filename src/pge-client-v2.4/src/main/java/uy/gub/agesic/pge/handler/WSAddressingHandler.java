package uy.gub.agesic.pge.handler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import uy.gub.agesic.pge.core.ws.addressing.AddressingBuilder;
import uy.gub.agesic.pge.core.ws.addressing.AddressingConstantsImpl;
import uy.gub.agesic.pge.core.ws.addressing.AttributedURI;
import uy.gub.agesic.pge.core.ws.addressing.JAXWSAConstants;
import uy.gub.agesic.pge.core.ws.addressing.SOAPAddressingProperties;
import uy.gub.agesic.pge.core.ws.addressing.SOAPAddressingPropertiesImpl;

/**
 * 
 * @author fsierra
 * 
 */
public class WSAddressingHandler implements SOAPHandler<SOAPMessageContext> {

	private static AddressingBuilder ADDR_BUILDER;

	private static AddressingConstantsImpl ADDR_CONSTANTS;

	private static Set<QName> HEADERS = new HashSet<QName>();

	static {
		ADDR_CONSTANTS = new AddressingConstantsImpl();
		ADDR_BUILDER = AddressingBuilder.getAddressingBuilder();

		HEADERS.add(ADDR_CONSTANTS.getActionQName());
		HEADERS.add(ADDR_CONSTANTS.getToQName());
	}

	@Override
	public boolean handleMessage(SOAPMessageContext msgContext) {
		Boolean outbound = (Boolean) msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (outbound == null)
			throw new IllegalStateException("Cannot obtain required property: "
					+ MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (outbound) {
			
			    
			
		    
			SOAPAddressingProperties addrProps = (SOAPAddressingProperties) msgContext.get(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES);
			if (addrProps == null) {
				addrProps = (SOAPAddressingPropertiesImpl) ADDR_BUILDER.newAddressingProperties();
			}

			// Add required wsa:Action
			if (addrProps.getAction() == null) {
				addrProps.setAction(this.getAction(msgContext));
			}

			// Add optional wsa:MessageID
			if (addrProps.getMessageID() == null) {
				addrProps.setMessageID(this.newURI("urn:uuid:" + UUID.randomUUID()));
			}

			// Add optional wsa:ReplyTo
			if (addrProps.getReplyTo() == null) {
				addrProps.setReplyTo(this.newAnonymousURI());
			}

			SOAPMessage soapMessage = ((SOAPMessageContext) msgContext).getMessage();
			addrProps.writeHeaders(soapMessage);

		}
		return true;

	}

	@Override
	public void close(MessageContext messageContext) {
	}

	@Override
	public boolean handleFault(SOAPMessageContext messageContext) {
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		return Collections.unmodifiableSet(HEADERS);
	}

	private AttributedURI getAction(final MessageContext msgContext) {
		String action = "UNSPECIFIED";

		if (msgContext.get(BindingProvider.SOAPACTION_URI_PROPERTY) != null) {
			action = msgContext.get(BindingProvider.SOAPACTION_URI_PROPERTY).toString();
		}

		return this.newURI(action);
	}

	private URI newAnonymousURI() {
		return this.newURI(ADDR_CONSTANTS.getAnonymousURI()).getURI();
	}

	private AttributedURI newURI(final String uri) {
		try {
			return ADDR_BUILDER.newURI(uri);
		} catch (URISyntaxException e) {
			throw new WebServiceException(e.getMessage(), e);
		}
	}
}
