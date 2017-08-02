
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tiposMedia complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad tipCod.
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
     * Define el valor de la propiedad tipCod.
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
     * Obtiene el valor de la propiedad tipNombre.
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
     * Define el valor de la propiedad tipNombre.
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
     * Obtiene el valor de la propiedad tipPk.
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
     * Define el valor de la propiedad tipPk.
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
