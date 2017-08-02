
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para publicarProyecto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="publicarProyecto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="proyecto" type="{http://ws.web.visualizador.siges.agesic.org/}proyectoImportado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "publicarProyecto", propOrder = {
    "proyecto"
})
public class PublicarProyecto_Type {

    protected ProyectoImportado proyecto;

    /**
     * Obtiene el valor de la propiedad proyecto.
     * 
     * @return
     *     possible object is
     *     {@link ProyectoImportado }
     *     
     */
    public ProyectoImportado getProyecto() {
        return proyecto;
    }

    /**
     * Define el valor de la propiedad proyecto.
     * 
     * @param value
     *     allowed object is
     *     {@link ProyectoImportado }
     *     
     */
    public void setProyecto(ProyectoImportado value) {
        this.proyecto = value;
    }

}
