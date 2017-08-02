package uy.gub.agesic.pge.context;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Response;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.SOAPBinding;
import org.apache.log4j.Logger;
import uy.gub.agesic.pge.AgesicConstants;
import uy.gub.agesic.pge.beans.ClientCredential;
import uy.gub.agesic.pge.beans.RSTBean;
import uy.gub.agesic.pge.beans.StoreBean;
import uy.gub.agesic.pge.core.config.ConfigProperties;
import uy.gub.agesic.pge.core.config.PGEConfiguration;
import uy.gub.agesic.pge.core.security.KeyStoreUtil;
import uy.gub.agesic.pge.core.ssl.SSLContextInitializer;
import uy.gub.agesic.pge.core.sts.client.PGESTSClient;
import uy.gub.agesic.pge.core.ws.addressing.AddressingBuilder;
import uy.gub.agesic.pge.core.ws.addressing.AddressingProperties;
import uy.gub.agesic.pge.core.ws.addressing.JAXWSAConstants;
import uy.gub.agesic.pge.exceptions.ConfigurationException;
import uy.gub.agesic.pge.exceptions.NoAssertionFoundException;
import uy.gub.agesic.pge.exceptions.PGEContextException;
import uy.gub.agesic.pge.exceptions.ParserException;
import uy.gub.agesic.pge.exceptions.RequestSecurityTokenException;
import uy.gub.agesic.pge.exceptions.UnmarshalException;
import uy.gub.agesic.pge.handler.LoggingHandler;
import uy.gub.agesic.pge.handler.SAMLHandler;
import uy.gub.agesic.pge.handler.WSAddressingHandler;

/**
 *
 * @author Federico Sierra (ACCE)
 *
 * @param <T> Clase del servicio.
 * @param <U> Clase del port, interface del cliente.
 */
@SuppressWarnings("rawtypes")
public class PGEContext<T extends Service, U> implements Serializable {

    private static final long serialVersionUID = -2875346144773320909L;
    private static final Logger log = Logger.getLogger(PGEContext.class);

    private static final String CONFIG_FILE = "pge-config.xml";

    private PGESTSClient stsClient;
    private List<Handler> handlers;
    private Map<String, Object> requestContextProperties;
    private PGEConfiguration config;
    private String serviceName;
    private T service;
    private U port;
    private Class<U> portClass;
    private StoreBean keyStoreSSL;
    private StoreBean trustStore;
    private ClientCredential clientCredential;
    private RSTBean bean;
    private Response<SOAPMessage> stsResponse;
    private SSLContextInitializer sslContextInitializer;
    private String action;
    private boolean mtomEnable;

    /**
     *
     * @param serviceClass
     * @param portClass
     * @param serviceName
     * @param action
     * @param configFile
     * @throws RequestSecurityTokenException
     * @throws ConfigurationException
     * @throws PGEContextException
     */
    public PGEContext(Class<T> serviceClass, Class<U> portClass, String serviceName,
            String action, PGEConfiguration config, URL wsdlDocumentLocation, QName serviceQName) throws RequestSecurityTokenException, ConfigurationException, PGEContextException {
        this(serviceClass, portClass, serviceName, action, config, wsdlDocumentLocation, serviceQName, false);
    }

    /**
     *
     * @param serviceClass
     * @param portClass
     * @param serviceName
     * @param action
     * @param configFile
     * @throws RequestSecurityTokenException
     * @throws ConfigurationException
     * @throws PGEContextException
     */
    public PGEContext(Class<T> serviceClass, Class<U> portClass, String serviceName,
            String action, PGEConfiguration config, URL wsdlDocumentLocation, QName serviceQName, boolean mtomEnable) throws RequestSecurityTokenException, ConfigurationException, PGEContextException {
        this.stsClient = new PGESTSClient();
        this.handlers = new ArrayList<Handler>();
        this.handlers.add(new SAMLHandler());
        this.handlers.add(new WSAddressingHandler());
        this.handlers.add(new LoggingHandler());
        this.serviceName = serviceName;
        this.portClass = portClass;
        this.action = action;
        this.mtomEnable = mtomEnable;

        if (config == null) {
            this.config = new PGEConfiguration(CONFIG_FILE);
        } else {
            this.config = config;
        }

        this.bean = createRSTBean();
        initKeyStoreBean();
        try {
            String sslInitClazz = this.config.getSTSPropValue(ConfigProperties.SSLCONTEXT_INITIALIZER);
            if (sslInitClazz != null) {
                Class clazz = SecurityActions.loadClass(getClass(), sslInitClazz);
                this.sslContextInitializer = (SSLContextInitializer) clazz.newInstance();
            }
            if (wsdlDocumentLocation != null && serviceQName != null) {
                this.service = serviceClass.getDeclaredConstructor(URL.class, QName.class).newInstance(wsdlDocumentLocation, serviceQName);
            } else {
                this.service = serviceClass.newInstance();
            }
            initBindingProvider();
            issueToken();
        } catch (Exception e) {
            throw new PGEContextException(e.getMessage(), e);
        }
    }

    /**
     * Constructor
     *
     * @param serviceClass
     * @param portClass
     * @param serviceName
     * @param actionName
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws RequestSecurityTokenException
     * @throws ConfigurationException
     */
    public PGEContext(Class<T> serviceClass, Class<U> portClass, String serviceName, String action) throws RequestSecurityTokenException, ConfigurationException, PGEContextException {
        this(serviceClass, portClass, serviceName, action, (PGEConfiguration) null, null, null);

    }

    /**
     *
     * @param serviceClass
     * @param portClass
     * @param serviceName
     * @param action
     * @param configFile
     * @throws RequestSecurityTokenException
     * @throws ConfigurationException
     * @throws PGEContextException
     */
    public PGEContext(Class<T> serviceClass, Class<U> portClass, String serviceName, String action, String configFile)
            throws RequestSecurityTokenException, ConfigurationException, PGEContextException {
        this(serviceClass, portClass, serviceName, action, new PGEConfiguration(configFile), null, null);

    }

    /**
     *
     * @param serviceClass
     * @param portClass
     * @param serviceName
     * @param action
     * @param configFile
     * @param wsdlDocumentLocation
     * @param serviceQName
     * @throws RequestSecurityTokenException
     * @throws ConfigurationException
     * @throws PGEContextException
     */
    public PGEContext(Class<T> serviceClass, Class<U> portClass, String serviceName, String action,
            String configFile, URL wsdlDocumentLocation, QName serviceQName)
            throws RequestSecurityTokenException, ConfigurationException, PGEContextException {

        this(serviceClass, portClass, serviceName, action, new PGEConfiguration(configFile), wsdlDocumentLocation, serviceQName);

    }

    /**
     *
     * @param serviceClass
     * @param portClass
     * @param config
     * @param serviceName
     * @param action
     * @param wsdlDocumentLocation
     * @param service
     * @throws RequestSecurityTokenException
     * @throws ConfigurationException
     * @throws PGEContextException
     */
    public PGEContext(Class<T> serviceClass, Class<U> portClass, PGEConfiguration config,
            String serviceName, String action, URL wsdlDocumentLocation, QName serviceQName) throws RequestSecurityTokenException, ConfigurationException, PGEContextException {
        this(serviceClass, portClass, serviceName, action, config, wsdlDocumentLocation, serviceQName);

    }

    private void issueToken() throws InterruptedException,
            ExecutionException, TimeoutException, ParserException,
            NoAssertionFoundException, UnmarshalException, SOAPException, RequestSecurityTokenException {
        this.stsResponse = stsClient.requestSecurityToken(bean, clientCredential, keyStoreSSL, trustStore, config, sslContextInitializer);
        ((BindingProvider) getPort()).getRequestContext().put(AgesicConstants.SAML1_PROPERTY, this.stsResponse);
    }

    /**
     * Sets the handler chain for the protocol binding instance.
     *
     * @param handler
     */
    public void addHandlerChain(Handler handler) {
        this.handlers.add(handler);
    }

    public T getService() {
        return service;
    }

    /**
     * The getPort method returns a proxy. The returned proxy should not be
     * reconfigured by the client.
     *
     * @return
     */
    public U getPort() {
        if (port == null) {
            port = service.getPort(portClass);
        }
        return port;
    }

    /**
     * Renew a token
     *
     * @throws RequestSecurityTokenException
     */
    public void renewToken() throws RequestSecurityTokenException, PGEContextException {
        try {
            issueToken();
        } catch (Exception e) {
            throw new PGEContextException(e.getMessage(), e);
        }
    }

    /**
     * Destroy context.
     */
    public void destroy() {
        this.stsClient = null;
        this.handlers = null;
        this.serviceName = null;
        this.portClass = null;
        this.service = null;
        this.requestContextProperties = null;
    }

    private RSTBean createRSTBean() {
        String issuer = config.getSTSPropValue(ConfigProperties.ISSUER);
        String policyName = config.getSTSPropValue(ConfigProperties.POLICY);
        String role = config.getSTSPropValue(ConfigProperties.ROLE);
        String userName = config.getSTSPropValue(ConfigProperties.USERNAME);

        RSTBean bean = new RSTBean();
        bean.setIssuer(issuer);
        bean.setPolicyName(policyName);
        bean.setRole(role);
        bean.setUserName(userName);
        bean.setService(this.serviceName);

        return bean;
    }

    private void initBindingProvider() throws ConfigurationException {
        String trustStoreFilePath = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_URL);
        String trustStorePwd = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_PASS);
        String sslKeyStoreFilePath = config.getKeyStoreAuthValue(ConfigProperties.SSL_KEY_STORE_URL);
        String sslKeyStorePwd = config.getKeyStoreAuthValue(ConfigProperties.SSL_KEY_STORE_PASS);

        requestContextProperties = ((BindingProvider) getPort()).getRequestContext();

        //Propiedades para jbossws native
        requestContextProperties.put("org.jboss.ws.authType", "org.jboss.ws.authType.wsse");
        requestContextProperties.put("org.jboss.ws.keyStore", sslKeyStoreFilePath);
        requestContextProperties.put("org.jboss.ws.keyStorePassword", sslKeyStorePwd);
        requestContextProperties.put("org.jboss.ws.trustStore", trustStoreFilePath);
        requestContextProperties.put("org.jboss.ws.trustStorePassword", trustStorePwd);

        requestContextProperties.put(ConfigProperties.STS_TIMEOUT, config.getSTSLongPropValue(ConfigProperties.STS_TIMEOUT));

        if (this.sslContextInitializer != null) {
            this.sslContextInitializer.initSSLContextPort(getPort(), config);
        }

        AddressingProperties addrProps;
        try {
            addrProps = buildAddressingProperties();
            requestContextProperties.put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES, addrProps);
        } catch (URISyntaxException e) {
            throw new ConfigurationException(e.getMessage(), e);
        }

        ((BindingProvider) getPort()).getBinding().setHandlerChain(handlers);
        ((SOAPBinding) ((BindingProvider) getPort()).getBinding()).setMTOMEnabled(this.mtomEnable);

    }

    private AddressingProperties buildAddressingProperties() throws URISyntaxException {
        AddressingBuilder builder = AddressingBuilder.getAddressingBuilder();

        AddressingProperties outProps = builder.newAddressingProperties();
        outProps.setTo(builder.newURI(serviceName));
        outProps.setAction(builder.newURI(action));
        //Agregado 31-03-2015
        if (config.getSTSPropValue(ConfigProperties.PRODUCER) != null && config.getSTSPropValue(ConfigProperties.TOPIC) != null) {
            outProps.setProducer(builder.newURI(config.getSTSPropValue(ConfigProperties.PRODUCER)));
            outProps.setTopic(builder.newURI(config.getSTSPropValue(ConfigProperties.TOPIC)));
        }
        return outProps;
    }

    private void initKeyStoreBean() throws ConfigurationException {
        String alias = config.getKeyStoreAuthValue(ConfigProperties.KEY_STORE_ALIAS);
        String keyStoreUrl = config.getKeyStoreAuthValue(ConfigProperties.KEY_STORE_FILE_URL);
        String keyStorePwd = config.getKeyStoreAuthValue(ConfigProperties.KEY_STORE_PASS);
        String trustStoreUrl = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_URL);
        String trustStorePwd = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_PASS);
        String sslKeyStoreUrl = config.getKeyStoreAuthValue(ConfigProperties.SSL_KEY_STORE_URL);
        String sslKeyStorePwd = config.getKeyStoreAuthValue(ConfigProperties.TRUST_STORE_PASS);

        this.keyStoreSSL = new StoreBean();
        this.keyStoreSSL.setAlias(alias);
        this.keyStoreSSL.setStoreFilePath(KeyStoreUtil.getKeystorePath(sslKeyStoreUrl));
        this.keyStoreSSL.setStorePwd(sslKeyStorePwd);

        this.trustStore = new StoreBean();
        this.trustStore.setStoreFilePath(KeyStoreUtil.getKeystorePath(trustStoreUrl));
        this.trustStore.setStorePwd(trustStorePwd);

        try {
            KeyStore keyStore = KeyStoreUtil.loadKeyStore(keyStoreUrl, keyStorePwd);
            X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate(alias);
            Key key = keyStore.getKey(alias, keyStorePwd.toCharArray());
            this.clientCredential = new ClientCredential();
            this.clientCredential.setCertificate(x509Certificate);
            this.clientCredential.setPrivateKey((PrivateKey) key);
        } catch (Exception e) {
            throw new ConfigurationException(e.getMessage(), e);
        }

    }

}
