
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tiposMedia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tiposMedia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tipCod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tiposMedia", propOrder = {
    "tipCod",
    "tipNombre",
    "tipPk"
})
public class TiposMedia {

    protected String tipCod;
    protected String tipNombre;
    protected Integer tipPk;

    /**
     * Gets the value of the tipCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipCod() {
        return tipCod;
    }

    /**
     * Sets the value of the tipCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipCod(String value) {
        this.tipCod = value;
    }

    /**
     * Gets the value of the tipNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipNombre() {
        return tipNombre;
    }

    /**
     * Sets the value of the tipNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipNombre(String value) {
        this.tipNombre = value;
    }

    /**
     * Gets the value of the tipPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipPk() {
        return tipPk;
    }

    /**
     * Sets the value of the tipPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipPk(Integer value) {
        this.tipPk = value;
    }

}
