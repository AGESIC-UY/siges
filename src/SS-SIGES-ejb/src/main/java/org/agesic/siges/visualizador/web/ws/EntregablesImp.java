
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for entregablesImp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="entregablesImp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entImpDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entImpFinDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="entImpFinLineaBaseDateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="entImpId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="entImpInicioDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="entImpInicioLineaBaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="entImpNivel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="entImpNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="entImpProgreso" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="entImpProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectoImportado" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entregablesImp", propOrder = {
    "entImpDescripcion",
    "entImpFinDate",
    "entImpFinLineaBaseDateDate",
    "entImpId",
    "entImpInicioDate",
    "entImpInicioLineaBaseDate",
    "entImpNivel",
    "entImpNombre",
    "entImpPk",
    "entImpProgreso",
    "entImpProyFk"
})
public class EntregablesImp {

    protected String entImpDescripcion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entImpFinDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entImpFinLineaBaseDateDate;
    protected Integer entImpId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entImpInicioDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entImpInicioLineaBaseDate;
    protected Integer entImpNivel;
    protected String entImpNombre;
    protected Integer entImpPk;
    protected Integer entImpProgreso;
    protected ProyectoImportado entImpProyFk;

    /**
     * Gets the value of the entImpDescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntImpDescripcion() {
        return entImpDescripcion;
    }

    /**
     * Sets the value of the entImpDescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntImpDescripcion(String value) {
        this.entImpDescripcion = value;
    }

    /**
     * Gets the value of the entImpFinDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntImpFinDate() {
        return entImpFinDate;
    }

    /**
     * Sets the value of the entImpFinDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntImpFinDate(XMLGregorianCalendar value) {
        this.entImpFinDate = value;
    }

    /**
     * Gets the value of the entImpFinLineaBaseDateDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntImpFinLineaBaseDateDate() {
        return entImpFinLineaBaseDateDate;
    }

    /**
     * Sets the value of the entImpFinLineaBaseDateDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntImpFinLineaBaseDateDate(XMLGregorianCalendar value) {
        this.entImpFinLineaBaseDateDate = value;
    }

    /**
     * Gets the value of the entImpId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEntImpId() {
        return entImpId;
    }

    /**
     * Sets the value of the entImpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEntImpId(Integer value) {
        this.entImpId = value;
    }

    /**
     * Gets the value of the entImpInicioDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntImpInicioDate() {
        return entImpInicioDate;
    }

    /**
     * Sets the value of the entImpInicioDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntImpInicioDate(XMLGregorianCalendar value) {
        this.entImpInicioDate = value;
    }

    /**
     * Gets the value of the entImpInicioLineaBaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntImpInicioLineaBaseDate() {
        return entImpInicioLineaBaseDate;
    }

    /**
     * Sets the value of the entImpInicioLineaBaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntImpInicioLineaBaseDate(XMLGregorianCalendar value) {
        this.entImpInicioLineaBaseDate = value;
    }

    /**
     * Gets the value of the entImpNivel property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEntImpNivel() {
        return entImpNivel;
    }

    /**
     * Sets the value of the entImpNivel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEntImpNivel(Integer value) {
        this.entImpNivel = value;
    }

    /**
     * Gets the value of the entImpNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntImpNombre() {
        return entImpNombre;
    }

    /**
     * Sets the value of the entImpNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntImpNombre(String value) {
        this.entImpNombre = value;
    }

    /**
     * Gets the value of the entImpPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEntImpPk() {
        return entImpPk;
    }

    /**
     * Sets the value of the entImpPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEntImpPk(Integer value) {
        this.entImpPk = value;
    }

    /**
     * Gets the value of the entImpProgreso property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEntImpProgreso() {
        return entImpProgreso;
    }

    /**
     * Sets the value of the entImpProgreso property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEntImpProgreso(Integer value) {
        this.entImpProgreso = value;
    }

    /**
     * Gets the value of the entImpProyFk property.
     * 
     * @return
     *     possible object is
     *     {@link ProyectoImportado }
     *     
     */
    public ProyectoImportado getEntImpProyFk() {
        return entImpProyFk;
    }

    /**
     * Sets the value of the entImpProyFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProyectoImportado }
     *     
     */
    public void setEntImpProyFk(ProyectoImportado value) {
        this.entImpProyFk = value;
    }

}
