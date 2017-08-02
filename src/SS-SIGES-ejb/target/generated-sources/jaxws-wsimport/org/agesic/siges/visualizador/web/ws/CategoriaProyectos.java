
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para categoriaProyectos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad catCod.
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
     * Define el valor de la propiedad catCod.
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
     * Obtiene el valor de la propiedad catIcono.
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
     * Define el valor de la propiedad catIcono.
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
     * Obtiene el valor de la propiedad catIconoMarker.
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
     * Define el valor de la propiedad catIconoMarker.
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
     * Obtiene el valor de la propiedad catIconoMarkerUrl.
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
     * Define el valor de la propiedad catIconoMarkerUrl.
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
     * Obtiene el valor de la propiedad catIconoUrl.
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
     * Define el valor de la propiedad catIconoUrl.
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
     * Obtiene el valor de la propiedad catNombre.
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
     * Define el valor de la propiedad catNombre.
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
     * Obtiene el valor de la propiedad catOrgFk.
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
     * Define el valor de la propiedad catOrgFk.
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
     * Obtiene el valor de la propiedad catPk.
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
     * Define el valor de la propiedad catPk.
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
     * Obtiene el valor de la propiedad catTipo.
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
     * Define el valor de la propiedad catTipo.
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
     * Obtiene el valor de la propiedad vigente.
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
     * Define el valor de la propiedad vigente.
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
