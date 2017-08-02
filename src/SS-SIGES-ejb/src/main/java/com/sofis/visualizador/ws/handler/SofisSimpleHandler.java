/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.visualizador.ws.handler;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author satella
 */
public class SofisSimpleHandler implements SOAPHandler<SOAPMessageContext> {

    private static final Logger logger = Logger.getLogger(SofisSimpleHandler.class.getName());

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {
        try {
            //obtenemos el documento del mensaje
            SOAPMessageContext smc = messageContext;
			SOAPMessage message = smc.getMessage();
            message.setProperty(javax.xml.soap.SOAPMessage.CHARACTER_SET_ENCODING, "iso-8859-1");
            
//            StringOutputStream os = new StringOutputStream();
//            message.writeTo(os);
            
            boolean salida = ((Boolean) messageContext.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY));
            if (salida){
                //logger.info("-->Imprimo desde el handler");
                logger.info(">>>>Execute");
                message.writeTo(System.out);//Imprime a pantalla el mensaje
                
            }else{
                logger.info(">>>>>Response");
                message.writeTo(System.out);

            }

 
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }


        return true;
    }

    @Override
    public Set<QName> getHeaders() {
            return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext messageContext) {

            return true;
    }


    @Override
    public void close(MessageContext context) {
    }

//    private class StringOutputStream extends OutputStream {
//
//      //StringBuilder mBuf = new StringBuilder("");
//      Vector<Byte> v = new Vector<Byte>();
//
//      public void write(int b) throws IOException {
//        //mBuf.append((char) b);
//        byte bb = (byte)b;
//        v.add(bb);
//      }
//
//      public String getString() {
//        //return mBuf.toString();
//        byte[] arreglo = new byte[v.size()];
//        for(int i=0;i<v.size();i++){
//            arreglo[i] = v.get(i);
//        }
//        Charset charset = Charset.forName("ISO-8859-1");
//        return new String(arreglo,charset);
//      }
//    }


}
