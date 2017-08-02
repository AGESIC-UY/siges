
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para fuenteFinanciamiento complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad fueCodigo.
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
     * Define el valor de la propiedad fueCodigo.
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
     * Obtiene el valor de la propiedad fueHabilitado.
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
     * Define el valor de la propiedad fueHabilitado.
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
     * Obtiene el valor de la propiedad fueNombre.
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
     * Define el valor de la propiedad fueNombre.
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
     * Obtiene el valor de la propiedad fueOrgFk.
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
     * Define el valor de la propiedad fueOrgFk.
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
     * Obtiene el valor de la propiedad fuePk.
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
     * Define el valor de la propiedad fuePk.
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
     * Obtiene el valor de la propiedad fuePkOriginal.
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
     * Define el valor de la propiedad fuePkOriginal.
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
