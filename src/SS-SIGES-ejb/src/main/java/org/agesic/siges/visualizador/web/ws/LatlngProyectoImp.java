
package org.agesic.siges.visualizador.web.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for latlngProyectoImp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the latlangImpBarrio property.
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
     * Sets the value of the latlangImpBarrio property.
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
     * Gets the value of the latlangImpCodigopostal property.
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
     * Sets the value of the latlangImpCodigopostal property.
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
     * Gets the value of the latlangImpDepFk property.
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
     * Sets the value of the latlangImpDepFk property.
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
     * Gets the value of the latlangImpLoc property.
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
     * Sets the value of the latlangImpLoc property.
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
     * Gets the value of the latlangImpLocFk property.
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
     * Sets the value of the latlangImpLocFk property.
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
     * Gets the value of the latlngImpLat property.
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
     * Sets the value of the latlngImpLat property.
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
     * Gets the value of the latlngImpLng property.
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
     * Sets the value of the latlngImpLng property.
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
     * Gets the value of the latlngImpPk property.
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
     * Sets the value of the latlngImpPk property.
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
     * Gets the value of the latlngImpProyFk property.
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
     * Sets the value of the latlngImpProyFk property.
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
