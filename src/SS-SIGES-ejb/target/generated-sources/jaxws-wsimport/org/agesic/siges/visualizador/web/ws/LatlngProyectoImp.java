
package org.agesic.siges.visualizador.web.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para latlngProyectoImp complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="latlngProyectoImp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="latlangImpBarrio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latlangImpCodigopostal" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latlangImpDepFk" type="{http://ws.web.visualizador.siges.agesic.org/}departamentos" minOccurs="0"/>
 *         &lt;element name="latlangImpLoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latlangImpLocFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latlngImpLat" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="latlngImpLng" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="latlngImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="latlngImpProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectoImportado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "latlngProyectoImp", propOrder = {
    "latlangImpBarrio",
    "latlangImpCodigopostal",
    "latlangImpDepFk",
    "latlangImpLoc",
    "latlangImpLocFk",
    "latlngImpLat",
    "latlngImpLng",
    "latlngImpPk",
    "latlngImpProyFk"
})
public class LatlngProyectoImp {

    protected String latlangImpBarrio;
    protected Integer latlangImpCodigopostal;
    protected Departamentos latlangImpDepFk;
    protected String latlangImpLoc;
    protected Integer latlangImpLocFk;
    protected BigDecimal latlngImpLat;
    protected BigDecimal latlngImpLng;
    protected Integer latlngImpPk;
    protected ProyectoImportado latlngImpProyFk;

    /**
     * Obtiene el valor de la propiedad latlangImpBarrio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatlangImpBarrio() {
        return latlangImpBarrio;
    }

    /**
     * Define el valor de la propiedad latlangImpBarrio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatlangImpBarrio(String value) {
        this.latlangImpBarrio = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangImpCodigopostal.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatlangImpCodigopostal() {
        return latlangImpCodigopostal;
    }

    /**
     * Define el valor de la propiedad latlangImpCodigopostal.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatlangImpCodigopostal(Integer value) {
        this.latlangImpCodigopostal = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangImpDepFk.
     * 
     * @return
     *     possible object is
     *     {@link Departamentos }
     *     
     */
    public Departamentos getLatlangImpDepFk() {
        return latlangImpDepFk;
    }

    /**
     * Define el valor de la propiedad latlangImpDepFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Departamentos }
     *     
     */
    public void setLatlangImpDepFk(Departamentos value) {
        this.latlangImpDepFk = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangImpLoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatlangImpLoc() {
        return latlangImpLoc;
    }

    /**
     * Define el valor de la propiedad latlangImpLoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatlangImpLoc(String value) {
        this.latlangImpLoc = value;
    }

    /**
     * Obtiene el valor de la propiedad latlangImpLocFk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatlangImpLocFk() {
        return latlangImpLocFk;
    }

    /**
     * Define el valor de la propiedad latlangImpLocFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatlangImpLocFk(Integer value) {
        this.latlangImpLocFk = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngImpLat.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLatlngImpLat() {
        return latlngImpLat;
    }

    /**
     * Define el valor de la propiedad latlngImpLat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLatlngImpLat(BigDecimal value) {
        this.latlngImpLat = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngImpLng.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLatlngImpLng() {
        return latlngImpLng;
    }

    /**
     * Define el valor de la propiedad latlngImpLng.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLatlngImpLng(BigDecimal value) {
        this.latlngImpLng = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngImpPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLatlngImpPk() {
        return latlngImpPk;
    }

    /**
     * Define el valor de la propiedad latlngImpPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLatlngImpPk(Integer value) {
        this.latlngImpPk = value;
    }

    /**
     * Obtiene el valor de la propiedad latlngImpProyFk.
     * 
     * @return
     *     possible object is
     *     {@link ProyectoImportado }
     *     
     */
    public ProyectoImportado getLatlngImpProyFk() {
        return latlngImpProyFk;
    }

    /**
     * Define el valor de la propiedad latlngImpProyFk.
     * 
     * @param value
     *     allowed object is
     *     {@link ProyectoImportado }
     *     
     */
    public void setLatlngImpProyFk(ProyectoImportado value) {
        this.latlngImpProyFk = value;
    }

}
