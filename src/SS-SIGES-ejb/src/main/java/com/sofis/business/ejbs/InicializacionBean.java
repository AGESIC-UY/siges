package com.sofis.business.ejbs;

import com.sofis.business.interceptors.LoggedInterceptor;
import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import com.sofis.entities.constantes.ConstanteApp;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Usuario
 */
@Named
@Stateless(name = "InicializacionBean")
@LocalBean
@Interceptors({LoggedInterceptor.class})
public class InicializacionBean {
    
    private static final Logger logger = Logger.getLogger(InicializacionBean.class.getName());

    @PersistenceContext(unitName = ConstanteApp.PERSISTENCE_CONTEXT_UNIT_NAME)
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void inicializarBaseDeDatos() {
        Document doc;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse(new File("C:\\ProyectosSofis\\archivoinicializacion.xml"));
            doc.getDocumentElement().normalize();
            NodeList listEntidades = doc.getElementsByTagName("entity");
            for (int i = 0; i < listEntidades.getLength(); i++) {
                try {
                    Node n = listEntidades.item(i);
                    String nombreClase = n.getAttributes().getNamedItem("name").getNodeValue();
                    Class clase = Class.forName(nombreClase);
                    Constructor constructor = clase.getConstructor();
                    Object o = constructor.newInstance();
                    Class cls = Class.forName(nombreClase);
                    o = cls.cast(o);
                    Field campoUltimaModificacion = obtenerCampoAnotado(o, AtributoUltimaModificacion.class);
                    if (campoUltimaModificacion != null) {
                        campoUltimaModificacion.set(o, new Date());
                    }
                    Field campoUltimoUsuario = obtenerCampoAnotado(o, AtributoUltimoUsuario.class);
                    if (campoUltimoUsuario != null) {
                        campoUltimoUsuario.set(o, "SCRIPT");
                    }
                    Field campoUltimoOrigen = obtenerCampoAnotado(o, AtributoUltimaOrigen.class);
                    if (campoUltimoOrigen != null) {
                        campoUltimoOrigen.set(o, "INIC");
                    }

                    NodeList listaCampos = n.getChildNodes();

                    for (int j = 0; j < listaCampos.getLength(); j++) {
                        Node nc = listaCampos.item(j);
                        //logger.info("***********"+nc.getNodeName());
                        if ("campo".equals(nc.getNodeName())) {
                            for (int k = 0; k < nc.getAttributes().getLength(); k++) {
                                Field fieldSeleccionado = obtenerCampo(o, nc.getAttributes().getNamedItem("name").getNodeValue());
                                if (fieldSeleccionado != null) {
                                    fieldSeleccionado.set(o, nc.getAttributes().getNamedItem("value").getNodeValue());
                                }

                            }

                        }

                        if ("campofk".equals(nc.getNodeName())) {
                            //logger.info("Es fk");

                            String codigo = nc.getAttributes().getNamedItem("codigo").getNodeValue();
                            String nombreCampo = nc.getAttributes().getNamedItem("name").getNodeValue();
                            String nombreCampoCodigo = nc.getAttributes().getNamedItem("codigoNombre").getNodeValue();
                            //logger.info("codigo = "+codigo+", nombreCampo= "+nombreCampo+", nombreCampocodigo="+nombreCampoCodigo);
                            Field fieldSeleccionado = obtenerCampo(o, nombreCampo);
                            String claseNombre = fieldSeleccionado.getType().getName();
                            String consulta = "select s from " + claseNombre + " s where s." + nombreCampoCodigo + "=:valor";
                            //logger.info("consulta = "+consulta);
                            Query query = em.createQuery(consulta);
                            query.setParameter("valor", codigo);
                            Object obj = query.getSingleResult();
                            Class cls2 = Class.forName(claseNombre);
                            obj = cls2.cast(obj);
                            if (fieldSeleccionado != null) {
                                fieldSeleccionado.set(o, obj);
                            }

                        }
                        ////logger.info("valor="+nc.getAttributes().getNamedItem("valor"));
                    }
                    guardar(o);
                } catch (Exception ex) {
                    //logger.info("ERROR: " + ex.getMessage());
                }
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(InicializacionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(InicializacionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InicializacionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void guardar(Object o) {
        em.persist(o);
    }

    private Field obtenerCampo(Object o, String nombre) {
        Field fieldSeleccionado = null;
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if (field.getName().equals(nombre)) {
                fieldSeleccionado = field;
                break;
            }

        }
        return fieldSeleccionado;
    }

    private Field obtenerCampoAnotado(Object o, Class claseAnotacion) {
        Field fieldSeleccionado = null;
        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if (field.isAnnotationPresent(claseAnotacion)) {
                fieldSeleccionado = field;
                break;
            }

        }
        return fieldSeleccionado;
    }
}
