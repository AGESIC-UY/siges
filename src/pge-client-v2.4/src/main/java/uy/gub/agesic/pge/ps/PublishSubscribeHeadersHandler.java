package uy.gub.agesic.pge.ps;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class PublishSubscribeHeadersHandler implements SOAPHandler<SOAPMessageContext> {
	private String producerId;
	private String topicId;

	private String notificationId;
	
	public PublishSubscribeHeadersHandler(String producerId, String topicId) {
		this.producerId = producerId;
		this.topicId = topicId;
	}
	
	public Set<QName> getHeaders() {
		return new TreeSet<QName>();
	}

	public void close(MessageContext mc) {
	}

	public boolean handleFault(SOAPMessageContext mc) {
		return true;
	}
	
	@SuppressWarnings({"rawtypes" })
	public boolean handleMessage(SOAPMessageContext mc) {
        Boolean outbound = (Boolean) mc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound) {
        	SOAPMessage message = mc.getMessage();
            try {
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPFactory factory = SOAPFactory.newInstance();
                String prefix = "ps";
                String uri = "http://ps.agesic.gub.uy";
                SOAPElement producerElem = factory.createElement("producer", prefix, uri);
                SOAPElement topicElem = factory.createElement("topic", prefix, uri);
                producerElem.addTextNode(producerId);
                topicElem.addTextNode(topicId);
                SOAPHeader header = envelope.getHeader();
                header.addChildElement(producerElem);
                header.addChildElement(topicElem);
            } catch (Exception e) {
                System.out.println("Exception in handler: " + e);
                e.printStackTrace();
            }
        } else {
        	SOAPMessage message = mc.getMessage();
            try {
    			Iterator it = message.getSOAPHeader().getChildElements(new QName("http://ps.agesic.gub.uy", "notificationId"));
    			if (it.hasNext()) {
    				SOAPHeaderElement elem = (SOAPHeaderElement) it.next();
   					notificationId = elem.getValue();
    			}
            } catch (Exception e) {
                System.out.println("Exception in handler: " + e);
                e.printStackTrace();
            }

        }
        return true;
	}
	
	public String getNotificationId() {
		return notificationId;
	}
}
