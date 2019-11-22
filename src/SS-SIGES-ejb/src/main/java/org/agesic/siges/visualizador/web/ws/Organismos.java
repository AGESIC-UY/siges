
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for organismos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organismos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orgActivo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="orgCod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgLogo" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="orgLogoNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="orgToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organismos", propOrder = {
    "orgActivo",
    "orgCod",
    "orgLogo",
    "orgLogoNombre",
    "orgNombre",
    "orgPk",
    "orgToken"
})
public class Organismos {

    protected Boolean orgActivo;
    protected String orgCod;
    protected byte[] orgLogo;
    protected String orgLogoNombre;
    protected String orgNombre;
    protected Integer orgPk;
    protected String orgToken;

    /**
     * Gets the value of the orgActivo property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrgActivo() {
        return orgActivo;
    }

    /**
     * Sets the value of the orgActivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrgActivo(Boolean value) {
        this.orgActivo = value;
    }

    /**
     * Gets the value of the orgCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCod() {
        return orgCod;
    }

    /**
     * Sets the value of the orgCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCod(String value) {
        this.orgCod = value;
    }

    /**
     * Gets the value of the orgLogo property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOrgLogo() {
        return orgLogo;
    }

    /**
     * Sets the value of the orgLogo property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOrgLogo(byte[] value) {
        this.orgLogo = value;
    }

    /**
     * Gets the value of the orgLogoNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgLogoNombre() {
        return orgLogoNombre;
    }

    /**
     * Sets the value of the orgLogoNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgLogoNombre(String value) {
        this.orgLogoNombre = value;
    }

    /**
     * Gets the value of the orgNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgNombre() {
        return orgNombre;
    }

    /**
     * Sets the value of the orgNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgNombre(String value) {
        this.orgNombre = value;
    }

    /**
     * Gets the value of the orgPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrgPk() {
        return orgPk;
    }

    /**
     * Sets the value of the orgPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrgPk(Integer value) {
        this.orgPk = value;
    }

    /**
     * Gets the value of the orgToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgToken() {
        return orgToken;
    }

    /**
     * Sets the value of the orgToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgToken(String value) {
        this.orgToken = value;
    }

}
