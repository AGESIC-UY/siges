
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para moneda complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad monCodPais.
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
     * Define el valor de la propiedad monCodPais.
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
     * Obtiene el valor de la propiedad monNombre.
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
     * Define el valor de la propiedad monNombre.
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
     * Obtiene el valor de la propiedad monOrgFk.
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
     * Define el valor de la propiedad monOrgFk.
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
     * Obtiene el valor de la propiedad monPk.
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
     * Define el valor de la propiedad monPk.
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
     * Obtiene el valor de la propiedad monPkOriginal.
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
     * Define el valor de la propiedad monPkOriginal.
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
     * Obtiene el valor de la propiedad monSigno.
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
     * Define el valor de la propiedad monSigno.
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
