/**
 * 
 */
package uy.gub.agesic.pge.core.sts.client;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.Service.Mode;
import javax.xml.ws.soap.SOAPBinding;

import uy.gub.agesic.pge.beans.ClientCredential;
import uy.gub.agesic.pge.beans.RSTBean;
import uy.gub.agesic.pge.beans.StoreBean;
import uy.gub.agesic.pge.core.config.ConfigProperties;
import uy.gub.agesic.pge.core.config.PGEConfiguration;
import uy.gub.agesic.pge.core.ssl.SSLContextInitializer;
import uy.gub.agesic.pge.core.xml.util.WSTrustUtil;
import uy.gub.agesic.pge.exceptions.RequestSecurityTokenException;

/**
 * @author Guzman Llambias
 * @author Federico Sierra (ACCE)
 * 
 */
public class PGESTSClient {

	//private static final Logger log = Logger.getLogger(PGESTSClient.class);
	
	
	/**
	 * Send a RequestSecurityToken (RST) message to the PGE using a {@link RSTBean}, a keyStore and a trustStore
	 * 
	 * @param bean
	 * @param credential
	 * @param keyStoreSSL
	 * @param trustStoreSSL
	 * @param urlStsSsl
	 * @param tokenTimeOut
	 * @return
	 * @throws RequestSecurityTokenException
	 */
	public Response<SOAPMessage> requestSecurityToken(RSTBean bean, ClientCredential credential, StoreBean keyStoreSSL, 
			StoreBean trustStoreSSL, PGEConfiguration config, SSLContextInitializer sslContextInitializer)
			throws RequestSecurityTokenException {
		
		Long tokenTimeOut = config.getSTSLongPropValue(ConfigProperties.TOKEN_TIMEOUT);
		String stsUrl = config.getSTSURL();
		
		setHostnameVerifier();
		
		SOAPMessage requestSecurityTokenMessage = WSTrustUtil.createRequestSecurityTokenMessage(bean, credential, tokenTimeOut);
	
		QName service = new QName("http://uy.gub.agesic.trust/sts/", "PGESTS");
	    QName portName = new QName("http://uy.gub.agesic.trust/sts/", "PGESTSPort");
	    
	    Service jaxwsService = Service.create(service);
		jaxwsService.addPort(portName, SOAPBinding.SOAP11HTTP_BINDING, stsUrl);
		Dispatch<SOAPMessage> dispatch = jaxwsService.createDispatch(portName, SOAPMessage.class, Mode.MESSAGE);
		
		//Propiedades para jbossws native
		dispatch.getRequestContext().put("org.jboss.ws.trustStore", trustStoreSSL.getStoreFilePath());
		dispatch.getRequestContext().put("org.jboss.ws.trustStorePassword", trustStoreSSL.getStorePwd());
		dispatch.getRequestContext().put("org.jboss.ws.keyStore", keyStoreSSL.getStoreFilePath());
		dispatch.getRequestContext().put("org.jboss.ws.keyStorePassword", keyStoreSSL.getStorePwd());
		
		if(sslContextInitializer != null){
			sslContextInitializer.initSSLContext(dispatch, config);
		}
		
		return dispatch.invokeAsync(requestSecurityTokenMessage);
			
	}
		
	private void setHostnameVerifier() {
		HostnameVerifier hv = new HostnameVerifier() {
		    public boolean verify(String urlHostName, SSLSession session) {
		        if (urlHostName.equals(session.getPeerHost()))
		        	return true;
		        else return false;
		    }
		};
		
		HttpsURLConnection.setDefaultHostnameVerifier(hv);
	}	
}
