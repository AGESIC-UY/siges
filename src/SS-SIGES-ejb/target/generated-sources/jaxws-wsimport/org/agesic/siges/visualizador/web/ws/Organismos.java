
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para organismos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad orgActivo.
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
     * Define el valor de la propiedad orgActivo.
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
     * Obtiene el valor de la propiedad orgCod.
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
     * Define el valor de la propiedad orgCod.
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
     * Obtiene el valor de la propiedad orgLogo.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOrgLogo() {
        return orgLogo;
    }

    /**
     * Define el valor de la propiedad orgLogo.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOrgLogo(byte[] value) {
        this.orgLogo = value;
    }

    /**
     * Obtiene el valor de la propiedad orgLogoNombre.
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
     * Define el valor de la propiedad orgLogoNombre.
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
     * Obtiene el valor de la propiedad orgNombre.
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
     * Define el valor de la propiedad orgNombre.
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
     * Obtiene el valor de la propiedad orgPk.
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
     * Define el valor de la propiedad orgPk.
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
     * Obtiene el valor de la propiedad orgToken.
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
     * Define el valor de la propiedad orgToken.
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
