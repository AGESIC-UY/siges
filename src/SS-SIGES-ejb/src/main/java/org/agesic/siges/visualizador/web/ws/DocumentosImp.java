
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for documentosImp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentosImp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="docsImpAprobado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="docsImpEstado" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="docsImpFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="docsImpFile" type="{http://ws.web.visualizador.siges.agesic.org/}docFileImp" minOccurs="0"/>
 *         &lt;element name="docsImpNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docsImpOrigenPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="docsImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="docsImpPrivado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="docsImpProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectoImportado" minOccurs="0"/>
 *         &lt;element name="docsImpTipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentosImp", propOrder = {
    "docsImpAprobado",
    "docsImpEstado",
    "docsImpFecha",
    "docsImpFile",
    "docsImpNombre",
    "docsImpOrigenPk",
    "docsImpPk",
    "docsImpPrivado",
    "docsImpProyFk",
    "docsImpTipo"
})
public class DocumentosImp {

    protected Boolean docsImpAprobado;
    protected Double docsImpEstado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar docsImpFecha;
    protected DocFileImp docsImpFile;
    protected String docsImpNombre;
    protected Integer docsImpOrigenPk;
    protected Integer docsImpPk;
    protected Boolean docsImpPrivado;
    protected ProyectoImportado docsImpProyFk;
    protected String docsImpTipo;

    /**
     * Gets the value of the docsImpAprobado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDocsImpAprobado() {
        return docsImpAprobado;
    }

    /**
     * Sets the value of the docsImpAprobado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDocsImpAprobado(Boolean value) {
        this.docsImpAprobado = value;
    }

    /**
     * Gets the value of the docsImpEstado property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDocsImpEstado() {
        return docsImpEstado;
    }

    /**
     * Sets the value of the docsImpEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDocsImpEstado(Double value) {
        this.docsImpEstado = value;
    }

    /**
     * Gets the value of the docsImpFecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDocsImpFecha() {
        return docsImpFecha;
    }

    /**
     * Sets the value of the docsImpFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDocsImpFecha(XMLGregorianCalendar value) {
        this.docsImpFecha = value;
    }

    /**
     * Gets the value of the docsImpFile property.
     * 
     * @return
     *     possible object is
     *     {@link DocFileImp }
     *     
     */
    public DocFileImp getDocsImpFile() {
        return docsImpFile;
    }

    /**
     * Sets the value of the docsImpFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocFileImp }
     *     
     */
    public void setDocsImpFile(DocFileImp value) {
        this.docsImpFile = value;
    }

    /**
     * Gets the value of the docsImpNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocsImpNombre() {
        return docsImpNombre;
    }

    /**
     * Sets the value of the docsImpNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocsImpNombre(String value) {
        this.docsImpNombre = value;
    }

    /**
     * Gets the value of the docsImpOrigenPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocsImpOrigenPk() {
        return docsImpOrigenPk;
    }

    /**
     * Sets the value of the docsImpOrigenPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocsImpOrigenPk(Integer value) {
        this.docsImpOrigenPk = value;
    }

    /**
     * Gets the value of the docsImpPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocsImpPk() {
        return docsImpPk;
    }

    /**
     * Sets the value of the docsImpPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocsImpPk(Integer value) {
        this.docsImpPk = value;
    }

    /**
     * Gets the value of the docsImpPrivado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDocsImpPrivado() {
        return docsImpPrivado;
    }

    /**
     * Sets the value of the docsImpPrivado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDocsImpPrivado(Boolean value) {
        this.docsImpPrivado = value;
    }

    /**
     * Gets the value of the docsImpProyFk property.
     * 
     * @return
     *     possible object is
     *     {@link ProyectoImportado }
     *     
     */
    public ProyectoImportado getDocsImpProyFk() {
        return docsImpProyFk;
    }

    /**
     * Sets the value of the docsImpProyFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProyectoImportado }
     *     
     */
    public void setDocsImpProyFk(ProyectoImportado value) {
        this.docsImpProyFk = value;
    }

    /**
     * Gets the value of the docsImpTipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocsImpTipo() {
        return docsImpTipo;
    }

    /**
     * Sets the value of the docsImpTipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocsImpTipo(String value) {
        this.docsImpTipo = value;
    }

}
