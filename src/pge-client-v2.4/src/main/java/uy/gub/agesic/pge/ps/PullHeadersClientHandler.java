package uy.gub.agesic.pge.ps;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class PullHeadersClientHandler implements SOAPHandler<SOAPMessageContext> {
	private String notificationId;

	public PullHeadersClientHandler() {
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
        if (! outbound) {
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
