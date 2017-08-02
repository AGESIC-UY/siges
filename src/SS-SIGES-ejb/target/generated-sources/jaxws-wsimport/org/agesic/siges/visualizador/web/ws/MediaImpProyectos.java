
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para mediaImpProyectos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
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
     * Obtiene el valor de la propiedad mediaBytes.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMediaBytes() {
        return mediaBytes;
    }

    /**
     * Define el valor de la propiedad mediaBytes.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMediaBytes(byte[] value) {
        this.mediaBytes = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaImpComentario.
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
     * Define el valor de la propiedad mediaImpComentario.
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
     * Obtiene el valor de la propiedad mediaImpContenttype.
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
     * Define el valor de la propiedad mediaImpContenttype.
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
     * Obtiene el valor de la propiedad mediaImpEstado.
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
     * Define el valor de la propiedad mediaImpEstado.
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
     * Obtiene el valor de la propiedad mediaImpLink.
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
     * Define el valor de la propiedad mediaImpLink.
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
     * Obtiene el valor de la propiedad mediaImpModFecha.
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
     * Define el valor de la propiedad mediaImpModFecha.
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
     * Obtiene el valor de la propiedad mediaImpOrden.
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
     * Define el valor de la propiedad mediaImpOrden.
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
     * Obtiene el valor de la propiedad mediaImpPk.
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
     * Define el valor de la propiedad mediaImpPk.
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
     * Obtiene el valor de la propiedad mediaImpProyFk.
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
     * Define el valor de la propiedad mediaImpProyFk.
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
     * Obtiene el valor de la propiedad mediaImpPubFecha.
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
     * Define el valor de la propiedad mediaImpPubFecha.
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
     * Obtiene el valor de la propiedad mediaImpRechFecha.
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
     * Define el valor de la propiedad mediaImpRechFecha.
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
     * Obtiene el valor de la propiedad mediaImpTipoFk.
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
     * Define el valor de la propiedad mediaImpTipoFk.
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
     * Obtiene el valor de la propiedad mediaImpUsrMod.
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
     * Define el valor de la propiedad mediaImpUsrMod.
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
     * Obtiene el valor de la propiedad mediaImpUsrPub.
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
     * Define el valor de la propiedad mediaImpUsrPub.
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
     * Obtiene el valor de la propiedad mediaImpUsrRech.
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
     * Define el valor de la propiedad mediaImpUsrRech.
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
     * Obtiene el valor de la propiedad mediaOrigenPk.
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
     * Define el valor de la propiedad mediaOrigenPk.
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
     * Obtiene el valor de la propiedad mediaImpPrincipal.
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
     * Define el valor de la propiedad mediaImpPrincipal.
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
