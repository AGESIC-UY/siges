package com.sofis.visualizador.ws.client;

import com.sofis.exceptions.PGEProxyException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import org.agesic.siges.visualizador.web.ws.CategoriaProyectosResponse;
import org.agesic.siges.visualizador.web.ws.ProyectoImportado;
import org.agesic.siges.visualizador.web.ws.PublicarProyecto;
import org.agesic.siges.visualizador.web.ws.PublicarProyecto_Service;
import uy.gub.agesic.pge.context.PGEContext;
import uy.gub.agesic.pge.core.config.PGEConfiguration;

/**
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
public class PGEClientTest {

    private static final Logger logger = Logger.getLogger(PGEClientTest.class.getName());

    public static void main(String[] args) {
        PGEClientTest p = new PGEClientTest();
//        p.obtenerCategoria();
        p.publicarProyecto();
    }
    
    public void obtenerCategoria(){
       CategoriaProyectosResponse response = null;
        try {
            String usuario = "mgarcia";
            PGEClientTest p = new PGEClientTest();
            PublicarProyecto port = p.publicarProyectoConexion(usuario);
            response = port.obtenerCategoriasXml();
            System.out.println("response.error:"+response.getError());

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error invocando el servicio web.", e);
        } 
    }

    public void publicarProyecto() {
        String respuesta = null;
        try {
            String usuario = "mgarcia";
            PublicarProyecto port = publicarProyectoConexion(usuario);
            ProyectoImportado pi = new ProyectoImportado();
            respuesta = port.publicarProyecto(pi);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error invocando el servicio web.", e);
        }
    }
    
    public PublicarProyecto publicarProyectoConexion(String usuario) throws PGEProxyException {

        String service = "http://servicios.pge.red.uy/agesic/PublicarProyecto/MiradorGobierno";
        String action = "http://servicios.pge.red.uy/agesic/PublicarProyecto/obtenerCategoriasXml";
        String paramNamespace = "http://ws.web.visualizador.siges.agesic.org/";
        String paramServiceName = "PublicarProyecto";
        String paramURL = "file:\\//C:\\Users\\Usuario\\Documents\\SOFIS\\SIGES\\AGESIC_SIGES_PublicarProyecto_v2.wsdl";
        String rol = "ou=siges,o=agesic";
        String mtomStr = "true";
        boolean mtom = mtomStr == null || mtomStr.isEmpty() || mtomStr.equalsIgnoreCase("true");

        try {
            QName qname = new QName(paramNamespace, paramServiceName);
            URL url = new URL(paramURL);
            InputStream is = new ByteArrayInputStream(armoXMLConfiguracion(usuario, rol).getBytes());
            PGEConfiguration pgeConfiguration = new PGEConfiguration(is);
            is.close();

            PGEContext<PublicarProyecto_Service, PublicarProyecto> context;
            context = new PGEContext<PublicarProyecto_Service, PublicarProyecto>(PublicarProyecto_Service.class,
                    PublicarProyecto.class, service, action, pgeConfiguration, url, qname, mtom);

            PublicarProyecto port = context.getPort();
            return port;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error invocando el WS: " + service, e);
            throw new PGEProxyException("Error invocando el WS: " + service, e.getMessage());
        }
    }


    private String armoXMLConfiguracion(String usuario, String rol) {
        return armoXMLConfiguracion(usuario, rol, null, null);
    }

    private String armoXMLConfiguracion(String usuario, String rol, String producer, String topic) {

        String stsURL = "https://servicios.pge.red.uy:10001/TrustServer/SecurityTokenServiceProtected";
        String policy = "urn:std15";
        String issuer = "Agesic";
        String keystoreOrganismoPath = "/srv/siges/jboss-as-7.1.1.Final/PGE/organismo.keystore";
        String keystoreOrganismoPass = "Clave_4ges!c-PD1";
        String keystoreOrganismoAlias = "agesic-pdi (correo uruguayo - ca)";
        String keystoreSSLPath = "/srv/siges/jboss-as-7.1.1.Final/PGE/vsiges_ssl.keystore";
        String keystoreSSLPass = "agesic";
        String truststorePath = "/srv/siges/jboss-as-7.1.1.Final/PGE/agesic.truststore";
        String truststorePass = "Clave_4ges!c-PD1";

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
