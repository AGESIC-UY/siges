
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para departamentos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="departamentos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depLat" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="depLng" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="depNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="depZoom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "departamentos", propOrder = {
    "depLat",
    "depLng",
    "depNombre",
    "depPk",
    "depZoom"
})
public class Departamentos {

    protected Double depLat;
    protected Double depLng;
    protected String depNombre;
    protected Integer depPk;
    protected Integer depZoom;

    /**
     * Obtiene el valor de la propiedad depLat.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDepLat() {
        return depLat;
    }

    /**
     * Define el valor de la propiedad depLat.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDepLat(Double value) {
        this.depLat = value;
    }

    /**
     * Obtiene el valor de la propiedad depLng.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDepLng() {
        return depLng;
    }

    /**
     * Define el valor de la propiedad depLng.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDepLng(Double value) {
        this.depLng = value;
    }

    /**
     * Obtiene el valor de la propiedad depNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepNombre() {
        return depNombre;
    }

    /**
     * Define el valor de la propiedad depNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepNombre(String value) {
        this.depNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad depPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDepPk() {
        return depPk;
    }

    /**
     * Define el valor de la propiedad depPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDepPk(Integer value) {
        this.depPk = value;
    }

    /**
     * Obtiene el valor de la propiedad depZoom.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDepZoom() {
        return depZoom;
    }

    /**
     * Define el valor de la propiedad depZoom.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDepZoom(Integer value) {
        this.depZoom = value;
    }

}
