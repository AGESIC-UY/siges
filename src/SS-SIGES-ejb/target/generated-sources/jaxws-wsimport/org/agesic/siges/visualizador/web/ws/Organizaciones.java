
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para organizaciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad orgDireccion.
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
     * Define el valor de la propiedad orgDireccion.
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
     * Obtiene el valor de la propiedad orgHabilitado.
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
     * Define el valor de la propiedad orgHabilitado.
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
     * Obtiene el valor de la propiedad orgMail.
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
     * Define el valor de la propiedad orgMail.
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
     * Obtiene el valor de la propiedad orgOrgFk.
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
     * Define el valor de la propiedad orgOrgFk.
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
     * Obtiene el valor de la propiedad orgPkOriginal.
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
     * Define el valor de la propiedad orgPkOriginal.
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
     * Obtiene el valor de la propiedad orgProveedor.
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
     * Define el valor de la propiedad orgProveedor.
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
     * Obtiene el valor de la propiedad orgRazonSocial.
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
     * Define el valor de la propiedad orgRazonSocial.
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
     * Obtiene el valor de la propiedad orgRut.
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
     * Define el valor de la propiedad orgRut.
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
     * Obtiene el valor de la propiedad orgTelefono.
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
     * Define el valor de la propiedad orgTelefono.
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
     * Obtiene el valor de la propiedad orgWeb.
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
     * Define el valor de la propiedad orgWeb.
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
