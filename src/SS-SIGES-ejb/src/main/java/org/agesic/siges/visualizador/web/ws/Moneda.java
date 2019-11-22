
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for moneda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="moneda">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="monCodPais" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monOrgFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monPkOriginal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="monSigno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moneda", propOrder = {
    "monCodPais",
    "monNombre",
    "monOrgFk",
    "monPk",
    "monPkOriginal",
    "monSigno"
})
public class Moneda {

    protected String monCodPais;
    protected String monNombre;
    protected Integer monOrgFk;
    protected Integer monPk;
    protected Integer monPkOriginal;
    protected String monSigno;

    /**
     * Gets the value of the monCodPais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonCodPais() {
        return monCodPais;
    }

    /**
     * Sets the value of the monCodPais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonCodPais(String value) {
        this.monCodPais = value;
    }

    /**
     * Gets the value of the monNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonNombre() {
        return monNombre;
    }

    /**
     * Sets the value of the monNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonNombre(String value) {
        this.monNombre = value;
    }

    /**
     * Gets the value of the monOrgFk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonOrgFk() {
        return monOrgFk;
    }

    /**
     * Sets the value of the monOrgFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonOrgFk(Integer value) {
        this.monOrgFk = value;
    }

    /**
     * Gets the value of the monPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonPk() {
        return monPk;
    }

    /**
     * Sets the value of the monPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonPk(Integer value) {
        this.monPk = value;
    }

    /**
     * Gets the value of the monPkOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMonPkOriginal() {
        return monPkOriginal;
    }

    /**
     * Sets the value of the monPkOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMonPkOriginal(Integer value) {
        this.monPkOriginal = value;
    }

    /**
     * Gets the value of the monSigno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonSigno() {
        return monSigno;
    }

    /**
     * Sets the value of the monSigno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonSigno(String value) {
        this.monSigno = value;
    }

}
