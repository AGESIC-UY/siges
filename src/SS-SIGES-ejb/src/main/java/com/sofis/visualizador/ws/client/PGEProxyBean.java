package com.sofis.visualizador.ws.client;

import com.sofis.business.ejbs.ConfiguracionBean;
import com.sofis.entities.codigueras.ConfiguracionCodigos;
import com.sofis.entities.constantes.ConstanteApp;
import com.sofis.exceptions.PGEProxyException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import org.agesic.siges.visualizador.web.ws.CategoriaProyectosResponse;
import org.agesic.siges.visualizador.web.ws.ProyectoImportado;
import org.agesic.siges.visualizador.web.ws.PublicarProyecto;
import org.agesic.siges.visualizador.web.ws.PublicarProyecto_Service;
import uy.gub.agesic.pge.context.PGEContext;
import uy.gub.agesic.pge.core.config.PGEConfiguration;

/**
 *
 * @author sofis
 */
@Stateless

public class PGEProxyBean {

    private static final Logger logger = Logger.getLogger(PGEProxyBean.class.getName());

    @EJB
    private ConfiguracionBean conf;

    @PostConstruct
    public void inicializar() {
    }

    private PublicarProyecto publicarProyectoConexion(String usuario, Integer orgPk) throws PGEProxyException {

        String service = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_URLLOGICA, orgPk);
        String action = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_SOAPACTION, orgPk);
        String paramNamespace = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_NAMESPACE, orgPk);
        String paramServiceName = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_SERVICENAME, orgPk);
        String paramURL = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_WSDL, orgPk);
        String rol = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_ROL, orgPk);
        String mtomStr = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.VISUALIZADOR_PUBLICARSERVICIO_MTOM, orgPk);
        boolean mtom = mtomStr == null || mtomStr.isEmpty() || mtomStr.equalsIgnoreCase("true");

		InputStream is = null;
        try {
            QName qname = new QName(paramNamespace, paramServiceName);
            URL url = new URL(paramURL);
            is = new ByteArrayInputStream(armoXMLConfiguracion(usuario, rol, orgPk).getBytes());
            PGEConfiguration pgeConfiguration = new PGEConfiguration(is);

            PGEContext<PublicarProyecto_Service, PublicarProyecto> context;
            context = new PGEContext<>(PublicarProyecto_Service.class,
                    PublicarProyecto.class, service, action, pgeConfiguration, url, qname, mtom);

            PublicarProyecto port = context.getPort();
            return port;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error invocando el WS: " + service, e);
            throw new PGEProxyException("Error invocando el WS: " + service, e.getMessage());
        } finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException ex) {
					logger.log(Level.SEVERE, null, ex);
				}
			}
		}
    }

    public String proxyPublicarProyecto(String usuario, ProyectoImportado pi, Integer orgPk) throws PGEProxyException {
        String respuesta = null;
        try {
            PublicarProyecto port = publicarProyectoConexion(usuario, orgPk);
            respuesta = port.publicarProyecto(pi);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error invocando el servicio web.", e);
            throw new PGEProxyException("Error invocando el servicio web.", e.getMessage());
        }
        return respuesta;
    }

    public CategoriaProyectosResponse proxyObtenerCategorias(String usuario, Integer orgPk, String token) throws PGEProxyException {
        CategoriaProyectosResponse response = null;
        try {
            PublicarProyecto port = publicarProyectoConexion(usuario, orgPk);
            response = port.obtenerCategoriasXml(token);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error invocando el servicio web.", e);
            throw new PGEProxyException("Error invocando el servicio web.", e.getMessage());
        }
        return response;
    }

    private String armoXMLConfiguracion(String usuario, String rol, Integer orgPk) {
        return armoXMLConfiguracion(usuario, rol, null, null, orgPk);
    }

    //Si es P&S publicacion de novedad hay que enviar producer y topic.
    private String armoXMLConfiguracion(String usuario, String rol, String producer, String topic, Integer orgPk) {

        String stsURL = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.STS_URL, orgPk);
        String policy = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.STS_POLITICA, orgPk);
        String issuer = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.STS_EMISOR, orgPk);
        String keystoreOrganismoPath = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.KEYSTORE_ORG_PATH, orgPk);
        String keystoreOrganismoPass = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.KEYSTORE_ORG_PASS, orgPk);
        String keystoreOrganismoAlias = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.KEYSTORE_ORG_ALIAS, orgPk);
        String keystoreSSLPath = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.KEYSTORE_SSL_PATH, orgPk);
        String keystoreSSLPass = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.KEYSTORE_SSL_PASS, orgPk);
        String truststorePath = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.TRUSTSTORE_SSL_PATH, orgPk);
        String truststorePass = conf.obtenerCnfValorPorCodigo(ConfiguracionCodigos.TRUSTSTORE_SSL_PASS, orgPk);

        String producerAndTopicLine = "";
        if (producer != null && topic != null) {//Solo para Publish&Subscribe publicacion de novedad
            producerAndTopicLine = "<Property Key=\"Producer\" Value=\"" + producer + "\"/>"
                    + "<Property Key=\"Topic\" Value=\"" + topic + "\"/>";
        }

        String respuesta = "";
        respuesta = "<!-- Configuracion para JBoss con stack CXF -->"
                + "<PGEConfig xmlns=\"urn:agesic:pge:config:1.0\" schemaLocation=\"urn:agesic:pge:config:1.0 pge-config_1_0.xsd\">"
                + "<STSConfig url=\"" + stsURL + "\">"
                + "<Property Key=\"SSLContextInitializer\" Value=\"uy.gub.agesic.pge.core.ssl.SSLContextInitializerCXF\"/>"
                + "<Property Key=\"Role\" Value=\"" + rol + "\"/>"
                + "<Property Key=\"Policy\" Value=\"" + policy + "\"/>"
                + "<Property Key=\"Issuer\" Value=\"" + issuer + "\"/>"
                + "<Property Key=\"Username\" Value=\"" + usuario + "\"/>"
                + "<Property Key=\"STSTimeOut\" Value=\"5000\"/>"
                + "<Property Key=\"TokenTimeOut\" Value=\"950000\"/>"
                + producerAndTopicLine
                + "</STSConfig>"
                + "<KeyStore>"
                + "<Auth Key=\"KeyStoreURL\" Value=\"file:///" + keystoreOrganismoPath + "\"/>"
                + "<Auth Key=\"KeyStorePass\" Value=\"" + keystoreOrganismoPass + "\"/>"
                + "<Auth Key=\"KeyStoreAlias\" Value=\"" + keystoreOrganismoAlias + "\"/>"
                + "<Auth Key=\"SSLKeyStoreURL\" Value=\"file:///" + keystoreSSLPath + "\"/>"
                + "<Auth Key=\"SSLKeyStorePass\" Value=\"" + keystoreSSLPass + "\"/>"
                + "<Auth Key=\"TrustStoreURL\" Value=\"file:///" + truststorePath + "\"/>"
                + "<Auth Key=\"TrustStorePass\" Value=\"" + truststorePass + "\"/>"
                + "<Auth Key=\"salt\" Value=\"saltword\"/>"
                + "<Auth Key=\"iterationCount\" Value=\"117\"/>"
                + "</KeyStore>"
                + "</PGEConfig>";
        return respuesta;
    }

}
