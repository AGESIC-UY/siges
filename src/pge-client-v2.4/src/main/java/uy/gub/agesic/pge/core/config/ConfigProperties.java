package uy.gub.agesic.pge.core.config;

/**
 * 
 * @author Federico Sierra (ACCE)
 *
 */
public interface ConfigProperties {
	String SSLCONTEXT_INITIALIZER = "SSLContextInitializer";
	String STS_TIMEOUT = "STSTimeOut";
	String TOKEN_TIMEOUT = "TokenTimeOut";
	String ISSUER = "Issuer";
	String POLICY = "Policy";
	String ROLE = "Role";
	String USERNAME = "Username";
	String TRUST_STORE_URL = "TrustStoreURL";
	String TRUST_STORE_PASS = "TrustStorePass";
	String SSL_KEY_STORE_URL = "SSLKeyStoreURL";
	String SSL_KEY_STORE_PASS = "SSLKeyStorePass";
	String KEY_STORE_ALIAS= "KeyStoreAlias";
	String KEY_STORE_FILE_URL = "KeyStoreURL";
	String KEY_STORE_PASS = "KeyStorePass";
	String SALT = "salt";
	String ITERATION_COUNT = "iterationCount";
        
        //Agregado 31-03-2015
        String TOPIC = "Topic";
        String PRODUCER = "Producer";
}
