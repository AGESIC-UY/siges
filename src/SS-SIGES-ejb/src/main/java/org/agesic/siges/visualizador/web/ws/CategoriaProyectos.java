
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for categoriaProyectos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="categoriaProyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="catCod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catIcono" type="{http://ws.web.visualizador.siges.agesic.org/}image" minOccurs="0"/>
 *         &lt;element name="catIconoMarker" type="{http://ws.web.visualizador.siges.agesic.org/}image" minOccurs="0"/>
 *         &lt;element name="catIconoMarkerUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catIconoUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="catOrgFk" type="{http://ws.web.visualizador.siges.agesic.org/}organismos" minOccurs="0"/>
 *         &lt;element name="catPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="catTipo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vigente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "categoriaProyectos", propOrder = {
    "catCod",
    "catIcono",
    "catIconoMarker",
    "catIconoMarkerUrl",
    "catIconoUrl",
    "catNombre",
    "catOrgFk",
    "catPk",
    "catTipo",
    "vigente"
})
public class CategoriaProyectos {

    protected String catCod;
    protected Image catIcono;
    protected Image catIconoMarker;
    protected String catIconoMarkerUrl;
    protected String catIconoUrl;
    protected String catNombre;
    protected Organismos catOrgFk;
    protected Integer catPk;
    protected Integer catTipo;
    protected Boolean vigente;

    /**
     * Gets the value of the catCod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatCod() {
        return catCod;
    }

    /**
     * Sets the value of the catCod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatCod(String value) {
        this.catCod = value;
    }

    /**
     * Gets the value of the catIcono property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getCatIcono() {
        return catIcono;
    }

    /**
     * Sets the value of the catIcono property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setCatIcono(Image value) {
        this.catIcono = value;
    }

    /**
     * Gets the value of the catIconoMarker property.
     * 
     * @return
     *     possible object is
     *     {@link Image }
     *     
     */
    public Image getCatIconoMarker() {
        return catIconoMarker;
    }

    /**
     * Sets the value of the catIconoMarker property.
     * 
     * @param value
     *     allowed object is
     *     {@link Image }
     *     
     */
    public void setCatIconoMarker(Image value) {
        this.catIconoMarker = value;
    }

    /**
     * Gets the value of the catIconoMarkerUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatIconoMarkerUrl() {
        return catIconoMarkerUrl;
    }

    /**
     * Sets the value of the catIconoMarkerUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatIconoMarkerUrl(String value) {
        this.catIconoMarkerUrl = value;
    }

    /**
     * Gets the value of the catIconoUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatIconoUrl() {
        return catIconoUrl;
    }

    /**
     * Sets the value of the catIconoUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatIconoUrl(String value) {
        this.catIconoUrl = value;
    }

    /**
     * Gets the value of the catNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatNombre() {
        return catNombre;
    }

    /**
     * Sets the value of the catNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatNombre(String value) {
        this.catNombre = value;
    }

    /**
     * Gets the value of the catOrgFk property.
     * 
     * @return
     *     possible object is
     *     {@link Organismos }
     *     
     */
    public Organismos getCatOrgFk() {
        return catOrgFk;
    }

    /**
     * Sets the value of the catOrgFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Organismos }
     *     
     */
    public void setCatOrgFk(Organismos value) {
        this.catOrgFk = value;
    }

    /**
     * Gets the value of the catPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCatPk() {
        return catPk;
    }

    /**
     * Sets the value of the catPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCatPk(Integer value) {
        this.catPk = value;
    }

    /**
     * Gets the value of the catTipo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCatTipo() {
        return catTipo;
    }

    /**
     * Sets the value of the catTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCatTipo(Integer value) {
        this.catTipo = value;
    }

    /**
     * Gets the value of the vigente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVigente() {
        return vigente;
    }

    /**
     * Sets the value of the vigente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVigente(Boolean value) {
        this.vigente = value;
    }

}
