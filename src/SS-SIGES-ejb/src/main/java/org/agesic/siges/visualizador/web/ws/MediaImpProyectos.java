
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mediaImpProyectos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mediaImpProyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mediaBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="mediaImpComentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaImpContenttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaImpEstado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaImpLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaImpModFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mediaImpOrden" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaImpProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectoImportado" minOccurs="0"/>
 *         &lt;element name="mediaImpPubFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mediaImpRechFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mediaImpTipoFk" type="{http://ws.web.visualizador.siges.agesic.org/}tiposMedia" minOccurs="0"/>
 *         &lt;element name="mediaImpUsrMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaImpUsrPub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaImpUsrRech" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaOrigenPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaImpPrincipal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mediaImpProyectos", propOrder = {
    "mediaBytes",
    "mediaImpComentario",
    "mediaImpContenttype",
    "mediaImpEstado",
    "mediaImpLink",
    "mediaImpModFecha",
    "mediaImpOrden",
    "mediaImpPk",
    "mediaImpProyFk",
    "mediaImpPubFecha",
    "mediaImpRechFecha",
    "mediaImpTipoFk",
    "mediaImpUsrMod",
    "mediaImpUsrPub",
    "mediaImpUsrRech",
    "mediaOrigenPk",
    "mediaImpPrincipal"
})
public class MediaImpProyectos {

    protected byte[] mediaBytes;
    protected String mediaImpComentario;
    protected String mediaImpContenttype;
    protected Integer mediaImpEstado;
    protected String mediaImpLink;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mediaImpModFecha;
    protected Integer mediaImpOrden;
    protected Integer mediaImpPk;
    protected ProyectoImportado mediaImpProyFk;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mediaImpPubFecha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mediaImpRechFecha;
    protected TiposMedia mediaImpTipoFk;
    protected String mediaImpUsrMod;
    protected String mediaImpUsrPub;
    protected String mediaImpUsrRech;
    protected Integer mediaOrigenPk;
    protected Boolean mediaImpPrincipal;

    /**
     * Gets the value of the mediaBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMediaBytes() {
        return mediaBytes;
    }

    /**
     * Sets the value of the mediaBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMediaBytes(byte[] value) {
        this.mediaBytes = value;
    }

    /**
     * Gets the value of the mediaImpComentario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpComentario() {
        return mediaImpComentario;
    }

    /**
     * Sets the value of the mediaImpComentario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpComentario(String value) {
        this.mediaImpComentario = value;
    }

    /**
     * Gets the value of the mediaImpContenttype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpContenttype() {
        return mediaImpContenttype;
    }

    /**
     * Sets the value of the mediaImpContenttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpContenttype(String value) {
        this.mediaImpContenttype = value;
    }

    /**
     * Gets the value of the mediaImpEstado property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaImpEstado() {
        return mediaImpEstado;
    }

    /**
     * Sets the value of the mediaImpEstado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaImpEstado(Integer value) {
        this.mediaImpEstado = value;
    }

    /**
     * Gets the value of the mediaImpLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpLink() {
        return mediaImpLink;
    }

    /**
     * Sets the value of the mediaImpLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpLink(String value) {
        this.mediaImpLink = value;
    }

    /**
     * Gets the value of the mediaImpModFecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMediaImpModFecha() {
        return mediaImpModFecha;
    }

    /**
     * Sets the value of the mediaImpModFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMediaImpModFecha(XMLGregorianCalendar value) {
        this.mediaImpModFecha = value;
    }

    /**
     * Gets the value of the mediaImpOrden property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaImpOrden() {
        return mediaImpOrden;
    }

    /**
     * Sets the value of the mediaImpOrden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaImpOrden(Integer value) {
        this.mediaImpOrden = value;
    }

    /**
     * Gets the value of the mediaImpPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaImpPk() {
        return mediaImpPk;
    }

    /**
     * Sets the value of the mediaImpPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaImpPk(Integer value) {
        this.mediaImpPk = value;
    }

    /**
     * Gets the value of the mediaImpProyFk property.
     * 
     * @return
     *     possible object is
     *     {@link ProyectoImportado }
     *     
     */
    public ProyectoImportado getMediaImpProyFk() {
        return mediaImpProyFk;
    }

    /**
     * Sets the value of the mediaImpProyFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProyectoImportado }
     *     
     */
    public void setMediaImpProyFk(ProyectoImportado value) {
        this.mediaImpProyFk = value;
    }

    /**
     * Gets the value of the mediaImpPubFecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMediaImpPubFecha() {
        return mediaImpPubFecha;
    }

    /**
     * Sets the value of the mediaImpPubFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMediaImpPubFecha(XMLGregorianCalendar value) {
        this.mediaImpPubFecha = value;
    }

    /**
     * Gets the value of the mediaImpRechFecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMediaImpRechFecha() {
        return mediaImpRechFecha;
    }

    /**
     * Sets the value of the mediaImpRechFecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMediaImpRechFecha(XMLGregorianCalendar value) {
        this.mediaImpRechFecha = value;
    }

    /**
     * Gets the value of the mediaImpTipoFk property.
     * 
     * @return
     *     possible object is
     *     {@link TiposMedia }
     *     
     */
    public TiposMedia getMediaImpTipoFk() {
        return mediaImpTipoFk;
    }

    /**
     * Sets the value of the mediaImpTipoFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link TiposMedia }
     *     
     */
    public void setMediaImpTipoFk(TiposMedia value) {
        this.mediaImpTipoFk = value;
    }

    /**
     * Gets the value of the mediaImpUsrMod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpUsrMod() {
        return mediaImpUsrMod;
    }

    /**
     * Sets the value of the mediaImpUsrMod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpUsrMod(String value) {
        this.mediaImpUsrMod = value;
    }

    /**
     * Gets the value of the mediaImpUsrPub property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpUsrPub() {
        return mediaImpUsrPub;
    }

    /**
     * Sets the value of the mediaImpUsrPub property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpUsrPub(String value) {
        this.mediaImpUsrPub = value;
    }

    /**
     * Gets the value of the mediaImpUsrRech property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpUsrRech() {
        return mediaImpUsrRech;
    }

    /**
     * Sets the value of the mediaImpUsrRech property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpUsrRech(String value) {
        this.mediaImpUsrRech = value;
    }

    /**
     * Gets the value of the mediaOrigenPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaOrigenPk() {
        return mediaOrigenPk;
    }

    /**
     * Sets the value of the mediaOrigenPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaOrigenPk(Integer value) {
        this.mediaOrigenPk = value;
    }

    /**
     * Gets the value of the mediaImpPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMediaImpPrincipal() {
        return mediaImpPrincipal;
    }

    /**
     * Sets the value of the mediaImpPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMediaImpPrincipal(Boolean value) {
        this.mediaImpPrincipal = value;
    }

}
