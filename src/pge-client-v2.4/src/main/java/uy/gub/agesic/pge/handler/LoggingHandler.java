package uy.gub.agesic.pge.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger log = Logger.getLogger(LoggingHandler.class);

    @Override
    public void close(MessageContext arg0) {
    }

    @Override
    public boolean handleFault(SOAPMessageContext ctx) {
        /*Boolean outbound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound == null) {
            throw new IllegalStateException("Cannot obtain required property: " + MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        }
        SOAPMessage msg = ctx.getMessage();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            msg.writeTo(out);
            String strMsg = new String(out.toByteArray());
        } catch (Exception e) {
        }*/
        return true;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext ctx) {
        /*Boolean outbound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound == null) {
            throw new IllegalStateException("Cannot obtain required property: " + MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        }
        SOAPMessage msg = ctx.getMessage();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            msg.writeTo(out);
            String strMsg = new String(out.toByteArray());
        } catch (Exception e) {
        }*/
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

}
