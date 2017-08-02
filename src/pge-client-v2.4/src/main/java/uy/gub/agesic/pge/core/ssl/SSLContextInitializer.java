package uy.gub.agesic.pge.core.ssl;

import javax.xml.ws.Dispatch;

import uy.gub.agesic.pge.core.config.PGEConfiguration;

public interface SSLContextInitializer {
	
	void initSSLContext(Dispatch<?> service, PGEConfiguration config);
	
	void initSSLContextPort(Object port, PGEConfiguration config);
}
