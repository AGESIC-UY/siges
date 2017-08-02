package uy.gub.agesic.pge.core.ssl;

import java.security.KeyStore;
import javax.xml.ws.Dispatch;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.DispatchImpl;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.log4j.Logger;
import uy.gub.agesic.pge.core.config.ConfigProperties;
import uy.gub.agesic.pge.core.config.PGEConfiguration;
import uy.gub.agesic.pge.core.security.KeyStoreUtil;

/**
 * 
 * @author fsierra
 * 
 */
public class SSLContextInitializerCXF implements SSLContextInitializer {
	
	private static final Logger log = Logger.getLogger(SSLContextInitializerCXF.class);
	
	public SSLContextInitializerCXF(){};
	
	@Override
	public void initSSLContext(Dispatch<?> service, PGEConfiguration config) {
		Client clientProxy = ((DispatchImpl<?>)service).getClient(); 
		init(config, clientProxy);

	}
	
	@Override
	public void initSSLContextPort(Object port, PGEConfiguration config) {
		Client clientProxy = ClientProxy.getClient(port);
		init(config, clientProxy);
	}

	private void init(PGEConfiguration config, Client clientProxy) {
		HTTPConduit conduit = (HTTPConduit) clientProxy.getConduit();
        try{
        	String trustStoreUrl = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_URL);
    		String trustStorePwd = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_PASS);
    		
    		String sslKeyStoreUrl = config.getKeyStoreAuthValue(ConfigProperties.SSL_KEY_STORE_URL);
    		String sslKeyStorePwd = config.getKeyStoreAuthValue(ConfigProperties.SSL_KEY_STORE_PASS);
    		
    		KeyStore keyStore = KeyStoreUtil.loadKeyStore(sslKeyStoreUrl, sslKeyStorePwd);
			KeyStore trustStore = KeyStoreUtil.loadKeyStore(trustStoreUrl, trustStorePwd);
    		
        	TLSClientParameters tlsParams = new TLSClientParameters();
        	tlsParams.setKeyManagers(KeyStoreUtil.getKeyManagers(keyStore, sslKeyStorePwd));
        	tlsParams.setTrustManagers(KeyStoreUtil.getTrustManagers(trustStore));
        	tlsParams.setDisableCNCheck(true);
        	conduit.setTlsClientParameters(tlsParams);
	    }catch(Exception e){
        	log.error(e, e);
        }
	}

}
