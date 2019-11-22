
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para mediaProyectos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="mediaProyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="mediaBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="mediaComentario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaContenttype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaEstado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaLinkImportado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaModFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mediaOrden" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaOrigenPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mediaPrincipal" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="mediaProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectos" minOccurs="0"/>
 *         &lt;element name="mediaPubFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mediaRechFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mediaTipoFk" type="{http://ws.web.visualizador.siges.agesic.org/}tiposMedia" minOccurs="0"/>
 *         &lt;element name="mediaUsrMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaUsrPub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mediaUsrRech" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sinGuardarEnBd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mediaProyectos", propOrder = {
    "fileSize",
    "mediaBytes",
    "mediaComentario",
    "mediaContenttype",
    "mediaEstado",
    "mediaLink",
    "mediaLinkImportado",
    "mediaModFecha",
    "mediaOrden",
    "mediaOrigenPk",
    "mediaPk",
    "mediaPrincipal",
    "mediaProyFk",
    "mediaPubFecha",
    "mediaRechFecha",
    "mediaTipoFk",
    "mediaUsrMod",
    "mediaUsrPub",
    "mediaUsrRech",
    "sinGuardarEnBd"
})
public class MediaProyectos {

    protected Long fileSize;
    protected byte[] mediaBytes;
    protected String mediaComentario;
    protected String mediaContenttype;
    protected Integer mediaEstado;
    protected String mediaLink;
    protected String mediaLinkImportado;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mediaModFecha;
    protected Integer mediaOrden;
    protected Integer mediaOrigenPk;
    protected Integer mediaPk;
    protected boolean mediaPrincipal;
    protected Proyectos mediaProyFk;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mediaPubFecha;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar mediaRechFecha;
    protected TiposMedia mediaTipoFk;
    protected String mediaUsrMod;
    protected String mediaUsrPub;
    protected String mediaUsrRech;
    protected Boolean sinGuardarEnBd;

    /**
     * Obtiene el valor de la propiedad fileSize.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * Define el valor de la propiedad fileSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFileSize(Long value) {
        this.fileSize = value;
    }

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
     * Obtiene el valor de la propiedad mediaComentario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaComentario() {
        return mediaComentario;
    }

    /**
     * Define el valor de la propiedad mediaComentario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaComentario(String value) {
        this.mediaComentario = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaContenttype.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaContenttype() {
        return mediaContenttype;
    }

    /**
     * Define el valor de la propiedad mediaContenttype.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaContenttype(String value) {
        this.mediaContenttype = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaEstado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaEstado() {
        return mediaEstado;
    }

    /**
     * Define el valor de la propiedad mediaEstado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaEstado(Integer value) {
        this.mediaEstado = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaLink.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaLink() {
        return mediaLink;
    }

    /**
     * Define el valor de la propiedad mediaLink.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaLink(String value) {
        this.mediaLink = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaLinkImportado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaLinkImportado() {
        return mediaLinkImportado;
    }

    /**
     * Define el valor de la propiedad mediaLinkImportado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaLinkImportado(String value) {
        this.mediaLinkImportado = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaModFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMediaModFecha() {
        return mediaModFecha;
    }

    /**
     * Define el valor de la propiedad mediaModFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMediaModFecha(XMLGregorianCalendar value) {
        this.mediaModFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaOrden.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaOrden() {
        return mediaOrden;
    }

    /**
     * Define el valor de la propiedad mediaOrden.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaOrden(Integer value) {
        this.mediaOrden = value;
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
     * Obtiene el valor de la propiedad mediaPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMediaPk() {
        return mediaPk;
    }

    /**
     * Define el valor de la propiedad mediaPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMediaPk(Integer value) {
        this.mediaPk = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaPrincipal.
     * 
     */
    public boolean isMediaPrincipal() {
        return mediaPrincipal;
    }

    /**
     * Define el valor de la propiedad mediaPrincipal.
     * 
     */
    public void setMediaPrincipal(boolean value) {
        this.mediaPrincipal = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaProyFk.
     * 
     * @return
     *     possible object is
     *     {@link Proyectos }
     *     
     */
    public Proyectos getMediaProyFk() {
        return mediaProyFk;
    }

    /**
     * Define el valor de la propiedad mediaProyFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Proyectos }
     *     
     */
    public void setMediaProyFk(Proyectos value) {
        this.mediaProyFk = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaPubFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMediaPubFecha() {
        return mediaPubFecha;
    }

    /**
     * Define el valor de la propiedad mediaPubFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMediaPubFecha(XMLGregorianCalendar value) {
        this.mediaPubFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaRechFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMediaRechFecha() {
        return mediaRechFecha;
    }

    /**
     * Define el valor de la propiedad mediaRechFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMediaRechFecha(XMLGregorianCalendar value) {
        this.mediaRechFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaTipoFk.
     * 
     * @return
     *     possible object is
     *     {@link TiposMedia }
     *     
     */
    public TiposMedia getMediaTipoFk() {
        return mediaTipoFk;
    }

    /**
     * Define el valor de la propiedad mediaTipoFk.
     * 
     * @param value
     *     allowed object is
     *     {@link TiposMedia }
     *     
     */
    public void setMediaTipoFk(TiposMedia value) {
        this.mediaTipoFk = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaUsrMod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaUsrMod() {
        return mediaUsrMod;
    }

    /**
     * Define el valor de la propiedad mediaUsrMod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaUsrMod(String value) {
        this.mediaUsrMod = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaUsrPub.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaUsrPub() {
        return mediaUsrPub;
    }

    /**
     * Define el valor de la propiedad mediaUsrPub.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaUsrPub(String value) {
        this.mediaUsrPub = value;
    }

    /**
     * Obtiene el valor de la propiedad mediaUsrRech.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaUsrRech() {
        return mediaUsrRech;
    }

    /**
     * Define el valor de la propiedad mediaUsrRech.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaUsrRech(String value) {
        this.mediaUsrRech = value;
    }

    /**
     * Obtiene el valor de la propiedad sinGuardarEnBd.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSinGuardarEnBd() {
        return sinGuardarEnBd;
    }

    /**
     * Define el valor de la propiedad sinGuardarEnBd.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSinGuardarEnBd(Boolean value) {
        this.sinGuardarEnBd = value;
    }

}
