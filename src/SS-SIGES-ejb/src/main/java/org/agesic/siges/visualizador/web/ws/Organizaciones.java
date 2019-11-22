
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for organizaciones complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="organizaciones">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orgDireccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgHabilitado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="orgMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgOrgFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="orgPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="orgPkOriginal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="orgProveedor" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="orgRazonSocial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgRut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgTelefono" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "organizaciones", propOrder = {
    "orgDireccion",
    "orgHabilitado",
    "orgMail",
    "orgNombre",
    "orgOrgFk",
    "orgPk",
    "orgPkOriginal",
    "orgProveedor",
    "orgRazonSocial",
    "orgRut",
    "orgTelefono",
    "orgWeb"
})
public class Organizaciones {

    protected String orgDireccion;
    protected Boolean orgHabilitado;
    protected String orgMail;
    protected String orgNombre;
    protected Integer orgOrgFk;
    protected Integer orgPk;
    protected Integer orgPkOriginal;
    protected Boolean orgProveedor;
    protected String orgRazonSocial;
    protected String orgRut;
    protected String orgTelefono;
    protected String orgWeb;

    /**
     * Gets the value of the orgDireccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgDireccion() {
        return orgDireccion;
    }

    /**
     * Sets the value of the orgDireccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgDireccion(String value) {
        this.orgDireccion = value;
    }

    /**
     * Gets the value of the orgHabilitado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrgHabilitado() {
        return orgHabilitado;
    }

    /**
     * Sets the value of the orgHabilitado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrgHabilitado(Boolean value) {
        this.orgHabilitado = value;
    }

    /**
     * Gets the value of the orgMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgMail() {
        return orgMail;
    }

    /**
     * Sets the value of the orgMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgMail(String value) {
        this.orgMail = value;
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
     * Gets the value of the orgOrgFk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrgOrgFk() {
        return orgOrgFk;
    }

    /**
     * Sets the value of the orgOrgFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrgOrgFk(Integer value) {
        this.orgOrgFk = value;
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
     * Gets the value of the orgPkOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrgPkOriginal() {
        return orgPkOriginal;
    }

    /**
     * Sets the value of the orgPkOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrgPkOriginal(Integer value) {
        this.orgPkOriginal = value;
    }

    /**
     * Gets the value of the orgProveedor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrgProveedor() {
        return orgProveedor;
    }

    /**
     * Sets the value of the orgProveedor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrgProveedor(Boolean value) {
        this.orgProveedor = value;
    }

    /**
     * Gets the value of the orgRazonSocial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgRazonSocial() {
        return orgRazonSocial;
    }

    /**
     * Sets the value of the orgRazonSocial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgRazonSocial(String value) {
        this.orgRazonSocial = value;
    }

    /**
     * Gets the value of the orgRut property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgRut() {
        return orgRut;
    }

    /**
     * Sets the value of the orgRut property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgRut(String value) {
        this.orgRut = value;
    }

    /**
     * Gets the value of the orgTelefono property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgTelefono() {
        return orgTelefono;
    }

    /**
     * Sets the value of the orgTelefono property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgTelefono(String value) {
        this.orgTelefono = value;
    }

    /**
     * Gets the value of the orgWeb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgWeb() {
        return orgWeb;
    }

    /**
     * Sets the value of the orgWeb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgWeb(String value) {
        this.orgWeb = value;
    }

}
