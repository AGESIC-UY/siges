
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fuenteFinanciamiento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fuenteFinanciamiento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fueCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fueHabilitado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="fueNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fueOrgFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fuePk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="fuePkOriginal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fuenteFinanciamiento", propOrder = {
    "fueCodigo",
    "fueHabilitado",
    "fueNombre",
    "fueOrgFk",
    "fuePk",
    "fuePkOriginal"
})
public class FuenteFinanciamiento {

    protected String fueCodigo;
    protected Boolean fueHabilitado;
    protected String fueNombre;
    protected Integer fueOrgFk;
    protected Integer fuePk;
    protected Integer fuePkOriginal;

    /**
     * Gets the value of the fueCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFueCodigo() {
        return fueCodigo;
    }

    /**
     * Sets the value of the fueCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFueCodigo(String value) {
        this.fueCodigo = value;
    }

    /**
     * Gets the value of the fueHabilitado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFueHabilitado() {
        return fueHabilitado;
    }

    /**
     * Sets the value of the fueHabilitado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFueHabilitado(Boolean value) {
        this.fueHabilitado = value;
    }

    /**
     * Gets the value of the fueNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFueNombre() {
        return fueNombre;
    }

    /**
     * Sets the value of the fueNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFueNombre(String value) {
        this.fueNombre = value;
    }

    /**
     * Gets the value of the fueOrgFk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFueOrgFk() {
        return fueOrgFk;
    }

    /**
     * Sets the value of the fueOrgFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFueOrgFk(Integer value) {
        this.fueOrgFk = value;
    }

    /**
     * Gets the value of the fuePk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFuePk() {
        return fuePk;
    }

    /**
     * Sets the value of the fuePk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFuePk(Integer value) {
        this.fuePk = value;
    }

    /**
     * Gets the value of the fuePkOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFuePkOriginal() {
        return fuePkOriginal;
    }

    /**
     * Sets the value of the fuePkOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFuePkOriginal(Integer value) {
        this.fuePkOriginal = value;
    }

}
