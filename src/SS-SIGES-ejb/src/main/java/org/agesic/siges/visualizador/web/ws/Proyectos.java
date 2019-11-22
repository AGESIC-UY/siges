
package org.agesic.siges.visualizador.web.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para proyectos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="proyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ordered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="comentariosList" type="{http://ws.web.visualizador.siges.agesic.org/}comentario" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="latlngProyectosList" type="{http://ws.web.visualizador.siges.agesic.org/}latlngProyectos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mediaProyectosList" type="{http://ws.web.visualizador.siges.agesic.org/}mediaProyectos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="proyAreaOrg" type="{http://ws.web.visualizador.siges.agesic.org/}areaOrg" minOccurs="0"/>
 *         &lt;element name="proyAvanceFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyAvancePorc" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyCamara" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyCategsList" type="{http://ws.web.visualizador.siges.agesic.org/}categoriaProyectos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="proyCompartido" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyContacto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyDeptoAreaGerente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyDescripcionStrip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyEjecOrgPk" type="{http://ws.web.visualizador.siges.agesic.org/}organizaciones" minOccurs="0"/>
 *         &lt;element name="proyEstadoFk" type="{http://ws.web.visualizador.siges.agesic.org/}estadoProyectos" minOccurs="0"/>
 *         &lt;element name="proyEstadoPubFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyFechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyFechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyFechaMod" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyFechaPub" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyFechaSituacionActual" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyFinanciamientoFueFk" type="{http://ws.web.visualizador.siges.agesic.org/}fuenteFinanciamiento" minOccurs="0"/>
 *         &lt;element name="proyFotos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyGerente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyGerenteEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyGerenteTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyInvMonedaMonFk" type="{http://ws.web.visualizador.siges.agesic.org/}moneda" minOccurs="0"/>
 *         &lt;element name="proyInvTotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="proyMeta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyModificado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyObjPublico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyObjetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyObservaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyOrgFk" type="{http://ws.web.visualizador.siges.agesic.org/}organismos" minOccurs="0"/>
 *         &lt;element name="proyOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyOrigenPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyOrigenrecOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyOrigenrecOrgPk" type="{http://ws.web.visualizador.siges.agesic.org/}organizaciones" minOccurs="0"/>
 *         &lt;element name="proyPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyPlazoEstimado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyProvOrgFk" type="{http://ws.web.visualizador.siges.agesic.org/}organizaciones" minOccurs="0"/>
 *         &lt;element name="proySituacionActual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyTemaFk" type="{http://ws.web.visualizador.siges.agesic.org/}categoriaProyectos" minOccurs="0"/>
 *         &lt;element name="proyUsrMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyUsrPub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyVideos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyVisto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proyectos", propOrder = {
    "ordered",
    "comentariosList",
    "latlngProyectosList",
    "mediaProyectosList",
    "proyAreaOrg",
    "proyAvanceFecha",
    "proyAvancePorc",
    "proyCamara",
    "proyCategsList",
    "proyCompartido",
    "proyContacto",
    "proyDeptoAreaGerente",
    "proyDescripcion",
    "proyDescripcionStrip",
    "proyEjecOrgPk",
    "proyEstadoFk",
    "proyEstadoPubFk",
    "proyFechaFin",
    "proyFechaInicio",
    "proyFechaMod",
    "proyFechaPub",
    "proyFechaSituacionActual",
    "proyFinanciamientoFueFk",
    "proyFotos",
    "proyGerente",
    "proyGerenteEmail",
    "proyGerenteTel",
    "proyInvMonedaMonFk",
    "proyInvTotal",
    "proyMeta",
    "proyModificado",
    "proyNombre",
    "proyObjPublico",
    "proyObjetivo",
    "proyObservaciones",
    "proyOrgFk",
    "proyOrigen",
    "proyOrigenPk",
    "proyOrigenrecOrg",
    "proyOrigenrecOrgPk",
    "proyPk",
    "proyPlazoEstimado",
    "proyProvOrgFk",
    "proySituacionActual",
    "proyTemaFk",
    "proyUsrMod",
    "proyUsrPub",
    "proyVideos",
    "proyVisto"
})
public class Proyectos {

    protected Boolean ordered;
    @XmlElement(nillable = true)
    protected List<Comentario> comentariosList;
    @XmlElement(nillable = true)
    protected List<LatlngProyectos> latlngProyectosList;
    @XmlElement(nillable = true)
    protected List<MediaProyectos> mediaProyectosList;
    protected AreaOrg proyAreaOrg;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyAvanceFecha;
    protected Integer proyAvancePorc;
    protected Long proyCamara;
    @XmlElement(nillable = true)
    protected List<CategoriaProyectos> proyCategsList;
    protected Long proyCompartido;
    protected String proyContacto;
    protected String proyDeptoAreaGerente;
    protected String proyDescripcion;
    protected String proyDescripcionStrip;
    protected Organizaciones proyEjecOrgPk;
    protected EstadoProyectos proyEstadoFk;
    protected Integer proyEstadoPubFk;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyFechaFin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyFechaInicio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyFechaMod;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyFechaPub;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyFechaSituacionActual;
    protected FuenteFinanciamiento proyFinanciamientoFueFk;
    protected Long proyFotos;
    protected String proyGerente;
    protected String proyGerenteEmail;
    protected String proyGerenteTel;
    protected Moneda proyInvMonedaMonFk;
    protected Double proyInvTotal;
    protected String proyMeta;
    protected Integer proyModificado;
    protected String proyNombre;
    protected String proyObjPublico;
    protected String proyObjetivo;
    protected String proyObservaciones;
    protected Organismos proyOrgFk;
    protected String proyOrigen;
    protected Integer proyOrigenPk;
    protected String proyOrigenrecOrg;
    protected Organizaciones proyOrigenrecOrgPk;
    protected Integer proyPk;
    protected Integer proyPlazoEstimado;
    protected Organizaciones proyProvOrgFk;
    protected String proySituacionActual;
    protected CategoriaProyectos proyTemaFk;
    protected String proyUsrMod;
    protected String proyUsrPub;
    protected Long proyVideos;
    protected Long proyVisto;

    /**
     * Obtiene el valor de la propiedad ordered.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrdered() {
        return ordered;
    }

    /**
     * Define el valor de la propiedad ordered.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrdered(Boolean value) {
        this.ordered = value;
    }

    /**
     * Gets the value of the comentariosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentariosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentariosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comentario }
     * 
     * 
     */
    public List<Comentario> getComentariosList() {
        if (comentariosList == null) {
            comentariosList = new ArrayList<Comentario>();
        }
        return this.comentariosList;
    }

    /**
     * Gets the value of the latlngProyectosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the latlngProyectosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLatlngProyectosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LatlngProyectos }
     * 
     * 
     */
    public List<LatlngProyectos> getLatlngProyectosList() {
        if (latlngProyectosList == null) {
            latlngProyectosList = new ArrayList<LatlngProyectos>();
        }
        return this.latlngProyectosList;
    }

    /**
     * Gets the value of the mediaProyectosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mediaProyectosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMediaProyectosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MediaProyectos }
     * 
     * 
     */
    public List<MediaProyectos> getMediaProyectosList() {
        if (mediaProyectosList == null) {
            mediaProyectosList = new ArrayList<MediaProyectos>();
        }
        return this.mediaProyectosList;
    }

    /**
     * Obtiene el valor de la propiedad proyAreaOrg.
     * 
     * @return
     *     possible object is
     *     {@link AreaOrg }
     *     
     */
    public AreaOrg getProyAreaOrg() {
        return proyAreaOrg;
    }

    /**
     * Define el valor de la propiedad proyAreaOrg.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaOrg }
     *     
     */
    public void setProyAreaOrg(AreaOrg value) {
        this.proyAreaOrg = value;
    }

    /**
     * Obtiene el valor de la propiedad proyAvanceFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyAvanceFecha() {
        return proyAvanceFecha;
    }

    /**
     * Define el valor de la propiedad proyAvanceFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyAvanceFecha(XMLGregorianCalendar value) {
        this.proyAvanceFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad proyAvancePorc.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyAvancePorc() {
        return proyAvancePorc;
    }

    /**
     * Define el valor de la propiedad proyAvancePorc.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyAvancePorc(Integer value) {
        this.proyAvancePorc = value;
    }

    /**
     * Obtiene el valor de la propiedad proyCamara.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyCamara() {
        return proyCamara;
    }

    /**
     * Define el valor de la propiedad proyCamara.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyCamara(Long value) {
        this.proyCamara = value;
    }

    /**
     * Gets the value of the proyCategsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the proyCategsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProyCategsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CategoriaProyectos }
     * 
     * 
     */
    public List<CategoriaProyectos> getProyCategsList() {
        if (proyCategsList == null) {
            proyCategsList = new ArrayList<CategoriaProyectos>();
        }
        return this.proyCategsList;
    }

    /**
     * Obtiene el valor de la propiedad proyCompartido.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyCompartido() {
        return proyCompartido;
    }

    /**
     * Define el valor de la propiedad proyCompartido.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyCompartido(Long value) {
        this.proyCompartido = value;
    }

    /**
     * Obtiene el valor de la propiedad proyContacto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyContacto() {
        return proyContacto;
    }

    /**
     * Define el valor de la propiedad proyContacto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyContacto(String value) {
        this.proyContacto = value;
    }

    /**
     * Obtiene el valor de la propiedad proyDeptoAreaGerente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyDeptoAreaGerente() {
        return proyDeptoAreaGerente;
    }

    /**
     * Define el valor de la propiedad proyDeptoAreaGerente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyDeptoAreaGerente(String value) {
        this.proyDeptoAreaGerente = value;
    }

    /**
     * Obtiene el valor de la propiedad proyDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyDescripcion() {
        return proyDescripcion;
    }

    /**
     * Define el valor de la propiedad proyDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyDescripcion(String value) {
        this.proyDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad proyDescripcionStrip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyDescripcionStrip() {
        return proyDescripcionStrip;
    }

    /**
     * Define el valor de la propiedad proyDescripcionStrip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyDescripcionStrip(String value) {
        this.proyDescripcionStrip = value;
    }

    /**
     * Obtiene el valor de la propiedad proyEjecOrgPk.
     * 
     * @return
     *     possible object is
     *     {@link Organizaciones }
     *     
     */
    public Organizaciones getProyEjecOrgPk() {
        return proyEjecOrgPk;
    }

    /**
     * Define el valor de la propiedad proyEjecOrgPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organizaciones }
     *     
     */
    public void setProyEjecOrgPk(Organizaciones value) {
        this.proyEjecOrgPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyEstadoFk.
     * 
     * @return
     *     possible object is
     *     {@link EstadoProyectos }
     *     
     */
    public EstadoProyectos getProyEstadoFk() {
        return proyEstadoFk;
    }

    /**
     * Define el valor de la propiedad proyEstadoFk.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoProyectos }
     *     
     */
    public void setProyEstadoFk(EstadoProyectos value) {
        this.proyEstadoFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyEstadoPubFk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyEstadoPubFk() {
        return proyEstadoPubFk;
    }

    /**
     * Define el valor de la propiedad proyEstadoPubFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyEstadoPubFk(Integer value) {
        this.proyEstadoPubFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFechaFin.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyFechaFin() {
        return proyFechaFin;
    }

    /**
     * Define el valor de la propiedad proyFechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyFechaFin(XMLGregorianCalendar value) {
        this.proyFechaFin = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyFechaInicio() {
        return proyFechaInicio;
    }

    /**
     * Define el valor de la propiedad proyFechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyFechaInicio(XMLGregorianCalendar value) {
        this.proyFechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFechaMod.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyFechaMod() {
        return proyFechaMod;
    }

    /**
     * Define el valor de la propiedad proyFechaMod.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyFechaMod(XMLGregorianCalendar value) {
        this.proyFechaMod = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFechaPub.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyFechaPub() {
        return proyFechaPub;
    }

    /**
     * Define el valor de la propiedad proyFechaPub.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyFechaPub(XMLGregorianCalendar value) {
        this.proyFechaPub = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFechaSituacionActual.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyFechaSituacionActual() {
        return proyFechaSituacionActual;
    }

    /**
     * Define el valor de la propiedad proyFechaSituacionActual.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyFechaSituacionActual(XMLGregorianCalendar value) {
        this.proyFechaSituacionActual = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFinanciamientoFueFk.
     * 
     * @return
     *     possible object is
     *     {@link FuenteFinanciamiento }
     *     
     */
    public FuenteFinanciamiento getProyFinanciamientoFueFk() {
        return proyFinanciamientoFueFk;
    }

    /**
     * Define el valor de la propiedad proyFinanciamientoFueFk.
     * 
     * @param value
     *     allowed object is
     *     {@link FuenteFinanciamiento }
     *     
     */
    public void setProyFinanciamientoFueFk(FuenteFinanciamiento value) {
        this.proyFinanciamientoFueFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyFotos.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyFotos() {
        return proyFotos;
    }

    /**
     * Define el valor de la propiedad proyFotos.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyFotos(Long value) {
        this.proyFotos = value;
    }

    /**
     * Obtiene el valor de la propiedad proyGerente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyGerente() {
        return proyGerente;
    }

    /**
     * Define el valor de la propiedad proyGerente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyGerente(String value) {
        this.proyGerente = value;
    }

    /**
     * Obtiene el valor de la propiedad proyGerenteEmail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyGerenteEmail() {
        return proyGerenteEmail;
    }

    /**
     * Define el valor de la propiedad proyGerenteEmail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyGerenteEmail(String value) {
        this.proyGerenteEmail = value;
    }

    /**
     * Obtiene el valor de la propiedad proyGerenteTel.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyGerenteTel() {
        return proyGerenteTel;
    }

    /**
     * Define el valor de la propiedad proyGerenteTel.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyGerenteTel(String value) {
        this.proyGerenteTel = value;
    }

    /**
     * Obtiene el valor de la propiedad proyInvMonedaMonFk.
     * 
     * @return
     *     possible object is
     *     {@link Moneda }
     *     
     */
    public Moneda getProyInvMonedaMonFk() {
        return proyInvMonedaMonFk;
    }

    /**
     * Define el valor de la propiedad proyInvMonedaMonFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Moneda }
     *     
     */
    public void setProyInvMonedaMonFk(Moneda value) {
        this.proyInvMonedaMonFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyInvTotal.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getProyInvTotal() {
        return proyInvTotal;
    }

    /**
     * Define el valor de la propiedad proyInvTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setProyInvTotal(Double value) {
        this.proyInvTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad proyMeta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyMeta() {
        return proyMeta;
    }

    /**
     * Define el valor de la propiedad proyMeta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyMeta(String value) {
        this.proyMeta = value;
    }

    /**
     * Obtiene el valor de la propiedad proyModificado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyModificado() {
        return proyModificado;
    }

    /**
     * Define el valor de la propiedad proyModificado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyModificado(Integer value) {
        this.proyModificado = value;
    }

    /**
     * Obtiene el valor de la propiedad proyNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyNombre() {
        return proyNombre;
    }

    /**
     * Define el valor de la propiedad proyNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyNombre(String value) {
        this.proyNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad proyObjPublico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyObjPublico() {
        return proyObjPublico;
    }

    /**
     * Define el valor de la propiedad proyObjPublico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyObjPublico(String value) {
        this.proyObjPublico = value;
    }

    /**
     * Obtiene el valor de la propiedad proyObjetivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyObjetivo() {
        return proyObjetivo;
    }

    /**
     * Define el valor de la propiedad proyObjetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyObjetivo(String value) {
        this.proyObjetivo = value;
    }

    /**
     * Obtiene el valor de la propiedad proyObservaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyObservaciones() {
        return proyObservaciones;
    }

    /**
     * Define el valor de la propiedad proyObservaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyObservaciones(String value) {
        this.proyObservaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad proyOrgFk.
     * 
     * @return
     *     possible object is
     *     {@link Organismos }
     *     
     */
    public Organismos getProyOrgFk() {
        return proyOrgFk;
    }

    /**
     * Define el valor de la propiedad proyOrgFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organismos }
     *     
     */
    public void setProyOrgFk(Organismos value) {
        this.proyOrgFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyOrigen() {
        return proyOrigen;
    }

    /**
     * Define el valor de la propiedad proyOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyOrigen(String value) {
        this.proyOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad proyOrigenPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyOrigenPk() {
        return proyOrigenPk;
    }

    /**
     * Define el valor de la propiedad proyOrigenPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyOrigenPk(Integer value) {
        this.proyOrigenPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyOrigenrecOrg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyOrigenrecOrg() {
        return proyOrigenrecOrg;
    }

    /**
     * Define el valor de la propiedad proyOrigenrecOrg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyOrigenrecOrg(String value) {
        this.proyOrigenrecOrg = value;
    }

    /**
     * Obtiene el valor de la propiedad proyOrigenrecOrgPk.
     * 
     * @return
     *     possible object is
     *     {@link Organizaciones }
     *     
     */
    public Organizaciones getProyOrigenrecOrgPk() {
        return proyOrigenrecOrgPk;
    }

    /**
     * Define el valor de la propiedad proyOrigenrecOrgPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organizaciones }
     *     
     */
    public void setProyOrigenrecOrgPk(Organizaciones value) {
        this.proyOrigenrecOrgPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyPk() {
        return proyPk;
    }

    /**
     * Define el valor de la propiedad proyPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyPk(Integer value) {
        this.proyPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyPlazoEstimado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyPlazoEstimado() {
        return proyPlazoEstimado;
    }

    /**
     * Define el valor de la propiedad proyPlazoEstimado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyPlazoEstimado(Integer value) {
        this.proyPlazoEstimado = value;
    }

    /**
     * Obtiene el valor de la propiedad proyProvOrgFk.
     * 
     * @return
     *     possible object is
     *     {@link Organizaciones }
     *     
     */
    public Organizaciones getProyProvOrgFk() {
        return proyProvOrgFk;
    }

    /**
     * Define el valor de la propiedad proyProvOrgFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organizaciones }
     *     
     */
    public void setProyProvOrgFk(Organizaciones value) {
        this.proyProvOrgFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proySituacionActual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProySituacionActual() {
        return proySituacionActual;
    }

    /**
     * Define el valor de la propiedad proySituacionActual.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProySituacionActual(String value) {
        this.proySituacionActual = value;
    }

    /**
     * Obtiene el valor de la propiedad proyTemaFk.
     * 
     * @return
     *     possible object is
     *     {@link CategoriaProyectos }
     *     
     */
    public CategoriaProyectos getProyTemaFk() {
        return proyTemaFk;
    }

    /**
     * Define el valor de la propiedad proyTemaFk.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriaProyectos }
     *     
     */
    public void setProyTemaFk(CategoriaProyectos value) {
        this.proyTemaFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyUsrMod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyUsrMod() {
        return proyUsrMod;
    }

    /**
     * Define el valor de la propiedad proyUsrMod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyUsrMod(String value) {
        this.proyUsrMod = value;
    }

    /**
     * Obtiene el valor de la propiedad proyUsrPub.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyUsrPub() {
        return proyUsrPub;
    }

    /**
     * Define el valor de la propiedad proyUsrPub.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyUsrPub(String value) {
        this.proyUsrPub = value;
    }

    /**
     * Obtiene el valor de la propiedad proyVideos.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyVideos() {
        return proyVideos;
    }

    /**
     * Define el valor de la propiedad proyVideos.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyVideos(Long value) {
        this.proyVideos = value;
    }

    /**
     * Obtiene el valor de la propiedad proyVisto.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyVisto() {
        return proyVisto;
    }

    /**
     * Define el valor de la propiedad proyVisto.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyVisto(Long value) {
        this.proyVisto = value;
    }

}
