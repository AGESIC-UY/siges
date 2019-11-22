
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para areaOrg complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="areaOrg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="abreviacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organismo" type="{http://ws.web.visualizador.siges.agesic.org/}organismos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "areaOrg", propOrder = {
    "abreviacion",
    "id",
    "nombre",
    "organismo"
})
public class AreaOrg {

    protected String abreviacion;
    protected Integer id;
    protected String nombre;
    protected Organismos organismo;

    /**
     * Obtiene el valor de la propiedad abreviacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAbreviacion() {
        return abreviacion;
    }

    /**
     * Define el valor de la propiedad abreviacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAbreviacion(String value) {
        this.abreviacion = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad organismo.
     * 
     * @return
     *     possible object is
     *     {@link Organismos }
     *     
     */
    public Organismos getOrganismo() {
        return organismo;
    }

    /**
     * Define el valor de la propiedad organismo.
     * 
     * @param value
     *     allowed object is
     *     {@link Organismos }
     *     
     */
    public void setOrganismo(Organismos value) {
        this.organismo = value;
    }

}
