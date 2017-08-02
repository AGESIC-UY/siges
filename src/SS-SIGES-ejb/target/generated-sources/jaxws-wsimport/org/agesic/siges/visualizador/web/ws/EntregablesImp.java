
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para entregablesImp complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad entImpDescripcion.
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
     * Define el valor de la propiedad entImpDescripcion.
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
     * Obtiene el valor de la propiedad entImpFinDate.
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
     * Define el valor de la propiedad entImpFinDate.
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
     * Obtiene el valor de la propiedad entImpFinLineaBaseDateDate.
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
     * Define el valor de la propiedad entImpFinLineaBaseDateDate.
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
     * Obtiene el valor de la propiedad entImpId.
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
     * Define el valor de la propiedad entImpId.
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
     * Obtiene el valor de la propiedad entImpInicioDate.
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
     * Define el valor de la propiedad entImpInicioDate.
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
     * Obtiene el valor de la propiedad entImpInicioLineaBaseDate.
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
     * Define el valor de la propiedad entImpInicioLineaBaseDate.
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
     * Obtiene el valor de la propiedad entImpNivel.
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
     * Define el valor de la propiedad entImpNivel.
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
     * Obtiene el valor de la propiedad entImpNombre.
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
     * Define el valor de la propiedad entImpNombre.
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
     * Obtiene el valor de la propiedad entImpPk.
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
     * Define el valor de la propiedad entImpPk.
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
     * Obtiene el valor de la propiedad entImpProgreso.
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
     * Define el valor de la propiedad entImpProgreso.
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
     * Obtiene el valor de la propiedad entImpProyFk.
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
     * Define el valor de la propiedad entImpProyFk.
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
