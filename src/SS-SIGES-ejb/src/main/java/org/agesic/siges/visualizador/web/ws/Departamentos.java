
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for departamentos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the depLat property.
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
     * Sets the value of the depLat property.
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
     * Gets the value of the depLng property.
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
     * Sets the value of the depLng property.
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
     * Gets the value of the depNombre property.
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
     * Sets the value of the depNombre property.
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
     * Gets the value of the depPk property.
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
     * Sets the value of the depPk property.
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
     * Gets the value of the depZoom property.
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
     * Sets the value of the depZoom property.
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
