
package org.agesic.siges.visualizador.web.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para latlngProyectos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="latlngProyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="latlangBarrio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latlangCodigopostal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latlangDepFk" type="{http://ws.web.visualizador.siges.agesic.org/}departamentos" minOccurs="0"/>
 *         &lt;element name="latlangLocFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latlangLocalidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latlngLat" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="latlngLng" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="latlngPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latlngProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectos" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "latlngProyectos", propOrder = {
    "latlangBarrio",
    "latlangCodigopostal",
    "latlangDepFk",
    "latlangLocFk",
    "latlangLocalidad",
    "latlngLat",
    "latlngLng",
    "latlngPk",
    "latlngProyFk"
})
public class LatlngProyectos {

    protected String latlangBarrio;
    protected Integer latlangCodigopostal;
    protected Departamentos latlangDepFk;
    protected Integer latlangLocFk;
    protected String latlangLocalidad;
    protected BigDecimal latlngLat;
    protected BigDecimal latlngLng;
    protected Integer latlngPk;
    protected Proyectos latlngProyFk;

    /**
     * Obtiene el valor de la propiedad latlangBarrio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatlangBarrio() {
        return latlangBarrio;
    }

    /**
     * Define el valor de la propiedad latlangBarrio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatlangBarrio(String value) {
        this.latlangBarrio = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangCodigopostal.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatlangCodigopostal() {
        return latlangCodigopostal;
    }

    /**
     * Define el valor de la propiedad latlangCodigopostal.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatlangCodigopostal(Integer value) {
        this.latlangCodigopostal = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangDepFk.
     * 
     * @return
     *     possible object is
     *     {@link Departamentos }
     *     
     */
    public Departamentos getLatlangDepFk() {
        return latlangDepFk;
    }

    /**
     * Define el valor de la propiedad latlangDepFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Departamentos }
     *     
     */
    public void setLatlangDepFk(Departamentos value) {
        this.latlangDepFk = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangLocFk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatlangLocFk() {
        return latlangLocFk;
    }

    /**
     * Define el valor de la propiedad latlangLocFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatlangLocFk(Integer value) {
        this.latlangLocFk = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangLocalidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatlangLocalidad() {
        return latlangLocalidad;
    }

    /**
     * Define el valor de la propiedad latlangLocalidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatlangLocalidad(String value) {
        this.latlangLocalidad = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngLat.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLatlngLat() {
        return latlngLat;
    }

    /**
     * Define el valor de la propiedad latlngLat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLatlngLat(BigDecimal value) {
        this.latlngLat = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngLng.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLatlngLng() {
        return latlngLng;
    }

    /**
     * Define el valor de la propiedad latlngLng.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLatlngLng(BigDecimal value) {
        this.latlngLng = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatlngPk() {
        return latlngPk;
    }

    /**
     * Define el valor de la propiedad latlngPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatlngPk(Integer value) {
        this.latlngPk = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngProyFk.
     * 
     * @return
     *     possible object is
     *     {@link Proyectos }
     *     
     */
    public Proyectos getLatlngProyFk() {
        return latlngProyFk;
    }

    /**
     * Define el valor de la propiedad latlngProyFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Proyectos }
     *     
     */
    public void setLatlngProyFk(Proyectos value) {
        this.latlngProyFk = value;
    }

}
