
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
 * <p>Clase Java para proyectoImportado complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="proyectoImportado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entregablesProyectoImpList" type="{http://ws.web.visualizador.siges.agesic.org/}entregablesImp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="latlngProyectoImpList" type="{http://ws.web.visualizador.siges.agesic.org/}latlngProyectoImp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mediaImpProyectosList" type="{http://ws.web.visualizador.siges.agesic.org/}mediaImpProyectos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="proyCategsList" type="{http://ws.web.visualizador.siges.agesic.org/}categoriaProyectos" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="proyDeptoAreaGerente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyGerente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyGerenteEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyGerenteTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpAreaNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpAreaPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpAvanceFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpAvancePorc" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpCamara" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyImpCompartido" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyImpContacto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpDescripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpDescripcionStrip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpEjecOrgPk" type="{http://ws.web.visualizador.siges.agesic.org/}organizaciones" minOccurs="0"/>
 *         &lt;element name="proyImpEstadoFk" type="{http://ws.web.visualizador.siges.agesic.org/}estadoProyectos" minOccurs="0"/>
 *         &lt;element name="proyImpEstadoPubFk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpFechaFin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpFechaImp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpFechaInicio" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpFechaMod" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpFechaPub" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpFinanciamientoFueFk" type="{http://ws.web.visualizador.siges.agesic.org/}fuenteFinanciamiento" minOccurs="0"/>
 *         &lt;element name="proyImpFotos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyImpInvMonedaMonFk" type="{http://ws.web.visualizador.siges.agesic.org/}moneda" minOccurs="0"/>
 *         &lt;element name="proyImpInvTotal" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="proyImpModificado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpObjPublico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpObjetivo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpObservaciones" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpOrgFk" type="{http://ws.web.visualizador.siges.agesic.org/}organismos" minOccurs="0"/>
 *         &lt;element name="proyImpOrigen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpOrigenPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpOrigenrecOrg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpOrigenrecOrgPk" type="{http://ws.web.visualizador.siges.agesic.org/}organizaciones" minOccurs="0"/>
 *         &lt;element name="proyImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpPlazoEstimado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="proyImpProvOrgFk" type="{http://ws.web.visualizador.siges.agesic.org/}organizaciones" minOccurs="0"/>
 *         &lt;element name="proyImpSitActual" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpSitActualDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="proyImpTemaFk" type="{http://ws.web.visualizador.siges.agesic.org/}categoriaProyectos" minOccurs="0"/>
 *         &lt;element name="proyImpUsrMod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpUsrPub" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proyImpVideos" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyImpVisto" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="proyImpareaAbreviacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publicar" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proyectoImportado", propOrder = {
    "entregablesProyectoImpList",
    "latlngProyectoImpList",
    "mediaImpProyectosList",
    "proyCategsList",
    "proyDeptoAreaGerente",
    "proyGerente",
    "proyGerenteEmail",
    "proyGerenteTel",
    "proyImpAreaNombre",
    "proyImpAreaPk",
    "proyImpAvanceFecha",
    "proyImpAvancePorc",
    "proyImpCamara",
    "proyImpCompartido",
    "proyImpContacto",
    "proyImpDescripcion",
    "proyImpDescripcionStrip",
    "proyImpEjecOrgPk",
    "proyImpEstadoFk",
    "proyImpEstadoPubFk",
    "proyImpFechaFin",
    "proyImpFechaImp",
    "proyImpFechaInicio",
    "proyImpFechaMod",
    "proyImpFechaPub",
    "proyImpFinanciamientoFueFk",
    "proyImpFotos",
    "proyImpInvMonedaMonFk",
    "proyImpInvTotal",
    "proyImpModificado",
    "proyImpNombre",
    "proyImpObjPublico",
    "proyImpObjetivo",
    "proyImpObservaciones",
    "proyImpOrgFk",
    "proyImpOrigen",
    "proyImpOrigenPk",
    "proyImpOrigenrecOrg",
    "proyImpOrigenrecOrgPk",
    "proyImpPk",
    "proyImpPlazoEstimado",
    "proyImpProvOrgFk",
    "proyImpSitActual",
    "proyImpSitActualDate",
    "proyImpTemaFk",
    "proyImpUsrMod",
    "proyImpUsrPub",
    "proyImpVideos",
    "proyImpVisto",
    "proyImpareaAbreviacion",
    "publicar"
})
public class ProyectoImportado {

    @XmlElement(nillable = true)
    protected List<EntregablesImp> entregablesProyectoImpList;
    @XmlElement(nillable = true)
    protected List<LatlngProyectoImp> latlngProyectoImpList;
    @XmlElement(nillable = true)
    protected List<MediaImpProyectos> mediaImpProyectosList;
    @XmlElement(nillable = true)
    protected List<CategoriaProyectos> proyCategsList;
    protected String proyDeptoAreaGerente;
    protected String proyGerente;
    protected String proyGerenteEmail;
    protected String proyGerenteTel;
    protected String proyImpAreaNombre;
    protected Integer proyImpAreaPk;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpAvanceFecha;
    protected Integer proyImpAvancePorc;
    protected Long proyImpCamara;
    protected Long proyImpCompartido;
    protected String proyImpContacto;
    protected String proyImpDescripcion;
    protected String proyImpDescripcionStrip;
    protected Organizaciones proyImpEjecOrgPk;
    protected EstadoProyectos proyImpEstadoFk;
    protected Integer proyImpEstadoPubFk;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpFechaFin;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpFechaImp;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpFechaInicio;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpFechaMod;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpFechaPub;
    protected FuenteFinanciamiento proyImpFinanciamientoFueFk;
    protected Long proyImpFotos;
    protected Moneda proyImpInvMonedaMonFk;
    protected Double proyImpInvTotal;
    protected Integer proyImpModificado;
    protected String proyImpNombre;
    protected String proyImpObjPublico;
    protected String proyImpObjetivo;
    protected String proyImpObservaciones;
    protected Organismos proyImpOrgFk;
    protected String proyImpOrigen;
    protected Integer proyImpOrigenPk;
    protected String proyImpOrigenrecOrg;
    protected Organizaciones proyImpOrigenrecOrgPk;
    protected Integer proyImpPk;
    protected Integer proyImpPlazoEstimado;
    protected Organizaciones proyImpProvOrgFk;
    protected String proyImpSitActual;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar proyImpSitActualDate;
    protected CategoriaProyectos proyImpTemaFk;
    protected String proyImpUsrMod;
    protected String proyImpUsrPub;
    protected Long proyImpVideos;
    protected Long proyImpVisto;
    protected String proyImpareaAbreviacion;
    protected boolean publicar;

    /**
     * Gets the value of the entregablesProyectoImpList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entregablesProyectoImpList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntregablesProyectoImpList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntregablesImp }
     * 
     * 
     */
    public List<EntregablesImp> getEntregablesProyectoImpList() {
        if (entregablesProyectoImpList == null) {
            entregablesProyectoImpList = new ArrayList<EntregablesImp>();
        }
        return this.entregablesProyectoImpList;
    }

    /**
     * Gets the value of the latlngProyectoImpList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the latlngProyectoImpList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLatlngProyectoImpList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LatlngProyectoImp }
     * 
     * 
     */
    public List<LatlngProyectoImp> getLatlngProyectoImpList() {
        if (latlngProyectoImpList == null) {
            latlngProyectoImpList = new ArrayList<LatlngProyectoImp>();
        }
        return this.latlngProyectoImpList;
    }

    /**
     * Gets the value of the mediaImpProyectosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mediaImpProyectosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMediaImpProyectosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MediaImpProyectos }
     * 
     * 
     */
    public List<MediaImpProyectos> getMediaImpProyectosList() {
        if (mediaImpProyectosList == null) {
            mediaImpProyectosList = new ArrayList<MediaImpProyectos>();
        }
        return this.mediaImpProyectosList;
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
     * Obtiene el valor de la propiedad proyImpAreaNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpAreaNombre() {
        return proyImpAreaNombre;
    }

    /**
     * Define el valor de la propiedad proyImpAreaNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpAreaNombre(String value) {
        this.proyImpAreaNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpAreaPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpAreaPk() {
        return proyImpAreaPk;
    }

    /**
     * Define el valor de la propiedad proyImpAreaPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpAreaPk(Integer value) {
        this.proyImpAreaPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpAvanceFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpAvanceFecha() {
        return proyImpAvanceFecha;
    }

    /**
     * Define el valor de la propiedad proyImpAvanceFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpAvanceFecha(XMLGregorianCalendar value) {
        this.proyImpAvanceFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpAvancePorc.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpAvancePorc() {
        return proyImpAvancePorc;
    }

    /**
     * Define el valor de la propiedad proyImpAvancePorc.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpAvancePorc(Integer value) {
        this.proyImpAvancePorc = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpCamara.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyImpCamara() {
        return proyImpCamara;
    }

    /**
     * Define el valor de la propiedad proyImpCamara.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyImpCamara(Long value) {
        this.proyImpCamara = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpCompartido.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyImpCompartido() {
        return proyImpCompartido;
    }

    /**
     * Define el valor de la propiedad proyImpCompartido.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyImpCompartido(Long value) {
        this.proyImpCompartido = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpContacto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpContacto() {
        return proyImpContacto;
    }

    /**
     * Define el valor de la propiedad proyImpContacto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpContacto(String value) {
        this.proyImpContacto = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpDescripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpDescripcion() {
        return proyImpDescripcion;
    }

    /**
     * Define el valor de la propiedad proyImpDescripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpDescripcion(String value) {
        this.proyImpDescripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpDescripcionStrip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpDescripcionStrip() {
        return proyImpDescripcionStrip;
    }

    /**
     * Define el valor de la propiedad proyImpDescripcionStrip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpDescripcionStrip(String value) {
        this.proyImpDescripcionStrip = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpEjecOrgPk.
     * 
     * @return
     *     possible object is
     *     {@link Organizaciones }
     *     
     */
    public Organizaciones getProyImpEjecOrgPk() {
        return proyImpEjecOrgPk;
    }

    /**
     * Define el valor de la propiedad proyImpEjecOrgPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organizaciones }
     *     
     */
    public void setProyImpEjecOrgPk(Organizaciones value) {
        this.proyImpEjecOrgPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpEstadoFk.
     * 
     * @return
     *     possible object is
     *     {@link EstadoProyectos }
     *     
     */
    public EstadoProyectos getProyImpEstadoFk() {
        return proyImpEstadoFk;
    }

    /**
     * Define el valor de la propiedad proyImpEstadoFk.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoProyectos }
     *     
     */
    public void setProyImpEstadoFk(EstadoProyectos value) {
        this.proyImpEstadoFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpEstadoPubFk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpEstadoPubFk() {
        return proyImpEstadoPubFk;
    }

    /**
     * Define el valor de la propiedad proyImpEstadoPubFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpEstadoPubFk(Integer value) {
        this.proyImpEstadoPubFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFechaFin.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpFechaFin() {
        return proyImpFechaFin;
    }

    /**
     * Define el valor de la propiedad proyImpFechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpFechaFin(XMLGregorianCalendar value) {
        this.proyImpFechaFin = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFechaImp.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpFechaImp() {
        return proyImpFechaImp;
    }

    /**
     * Define el valor de la propiedad proyImpFechaImp.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpFechaImp(XMLGregorianCalendar value) {
        this.proyImpFechaImp = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpFechaInicio() {
        return proyImpFechaInicio;
    }

    /**
     * Define el valor de la propiedad proyImpFechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpFechaInicio(XMLGregorianCalendar value) {
        this.proyImpFechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFechaMod.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpFechaMod() {
        return proyImpFechaMod;
    }

    /**
     * Define el valor de la propiedad proyImpFechaMod.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpFechaMod(XMLGregorianCalendar value) {
        this.proyImpFechaMod = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFechaPub.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpFechaPub() {
        return proyImpFechaPub;
    }

    /**
     * Define el valor de la propiedad proyImpFechaPub.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpFechaPub(XMLGregorianCalendar value) {
        this.proyImpFechaPub = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFinanciamientoFueFk.
     * 
     * @return
     *     possible object is
     *     {@link FuenteFinanciamiento }
     *     
     */
    public FuenteFinanciamiento getProyImpFinanciamientoFueFk() {
        return proyImpFinanciamientoFueFk;
    }

    /**
     * Define el valor de la propiedad proyImpFinanciamientoFueFk.
     * 
     * @param value
     *     allowed object is
     *     {@link FuenteFinanciamiento }
     *     
     */
    public void setProyImpFinanciamientoFueFk(FuenteFinanciamiento value) {
        this.proyImpFinanciamientoFueFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpFotos.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyImpFotos() {
        return proyImpFotos;
    }

    /**
     * Define el valor de la propiedad proyImpFotos.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyImpFotos(Long value) {
        this.proyImpFotos = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpInvMonedaMonFk.
     * 
     * @return
     *     possible object is
     *     {@link Moneda }
     *     
     */
    public Moneda getProyImpInvMonedaMonFk() {
        return proyImpInvMonedaMonFk;
    }

    /**
     * Define el valor de la propiedad proyImpInvMonedaMonFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Moneda }
     *     
     */
    public void setProyImpInvMonedaMonFk(Moneda value) {
        this.proyImpInvMonedaMonFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpInvTotal.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getProyImpInvTotal() {
        return proyImpInvTotal;
    }

    /**
     * Define el valor de la propiedad proyImpInvTotal.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setProyImpInvTotal(Double value) {
        this.proyImpInvTotal = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpModificado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpModificado() {
        return proyImpModificado;
    }

    /**
     * Define el valor de la propiedad proyImpModificado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpModificado(Integer value) {
        this.proyImpModificado = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpNombre() {
        return proyImpNombre;
    }

    /**
     * Define el valor de la propiedad proyImpNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpNombre(String value) {
        this.proyImpNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpObjPublico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpObjPublico() {
        return proyImpObjPublico;
    }

    /**
     * Define el valor de la propiedad proyImpObjPublico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpObjPublico(String value) {
        this.proyImpObjPublico = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpObjetivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpObjetivo() {
        return proyImpObjetivo;
    }

    /**
     * Define el valor de la propiedad proyImpObjetivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpObjetivo(String value) {
        this.proyImpObjetivo = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpObservaciones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpObservaciones() {
        return proyImpObservaciones;
    }

    /**
     * Define el valor de la propiedad proyImpObservaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpObservaciones(String value) {
        this.proyImpObservaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpOrgFk.
     * 
     * @return
     *     possible object is
     *     {@link Organismos }
     *     
     */
    public Organismos getProyImpOrgFk() {
        return proyImpOrgFk;
    }

    /**
     * Define el valor de la propiedad proyImpOrgFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organismos }
     *     
     */
    public void setProyImpOrgFk(Organismos value) {
        this.proyImpOrgFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpOrigen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpOrigen() {
        return proyImpOrigen;
    }

    /**
     * Define el valor de la propiedad proyImpOrigen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpOrigen(String value) {
        this.proyImpOrigen = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpOrigenPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpOrigenPk() {
        return proyImpOrigenPk;
    }

    /**
     * Define el valor de la propiedad proyImpOrigenPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpOrigenPk(Integer value) {
        this.proyImpOrigenPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpOrigenrecOrg.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpOrigenrecOrg() {
        return proyImpOrigenrecOrg;
    }

    /**
     * Define el valor de la propiedad proyImpOrigenrecOrg.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpOrigenrecOrg(String value) {
        this.proyImpOrigenrecOrg = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpOrigenrecOrgPk.
     * 
     * @return
     *     possible object is
     *     {@link Organizaciones }
     *     
     */
    public Organizaciones getProyImpOrigenrecOrgPk() {
        return proyImpOrigenrecOrgPk;
    }

    /**
     * Define el valor de la propiedad proyImpOrigenrecOrgPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organizaciones }
     *     
     */
    public void setProyImpOrigenrecOrgPk(Organizaciones value) {
        this.proyImpOrigenrecOrgPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpPk() {
        return proyImpPk;
    }

    /**
     * Define el valor de la propiedad proyImpPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpPk(Integer value) {
        this.proyImpPk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpPlazoEstimado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProyImpPlazoEstimado() {
        return proyImpPlazoEstimado;
    }

    /**
     * Define el valor de la propiedad proyImpPlazoEstimado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProyImpPlazoEstimado(Integer value) {
        this.proyImpPlazoEstimado = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpProvOrgFk.
     * 
     * @return
     *     possible object is
     *     {@link Organizaciones }
     *     
     */
    public Organizaciones getProyImpProvOrgFk() {
        return proyImpProvOrgFk;
    }

    /**
     * Define el valor de la propiedad proyImpProvOrgFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Organizaciones }
     *     
     */
    public void setProyImpProvOrgFk(Organizaciones value) {
        this.proyImpProvOrgFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpSitActual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpSitActual() {
        return proyImpSitActual;
    }

    /**
     * Define el valor de la propiedad proyImpSitActual.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpSitActual(String value) {
        this.proyImpSitActual = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpSitActualDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProyImpSitActualDate() {
        return proyImpSitActualDate;
    }

    /**
     * Define el valor de la propiedad proyImpSitActualDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProyImpSitActualDate(XMLGregorianCalendar value) {
        this.proyImpSitActualDate = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpTemaFk.
     * 
     * @return
     *     possible object is
     *     {@link CategoriaProyectos }
     *     
     */
    public CategoriaProyectos getProyImpTemaFk() {
        return proyImpTemaFk;
    }

    /**
     * Define el valor de la propiedad proyImpTemaFk.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriaProyectos }
     *     
     */
    public void setProyImpTemaFk(CategoriaProyectos value) {
        this.proyImpTemaFk = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpUsrMod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpUsrMod() {
        return proyImpUsrMod;
    }

    /**
     * Define el valor de la propiedad proyImpUsrMod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpUsrMod(String value) {
        this.proyImpUsrMod = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpUsrPub.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpUsrPub() {
        return proyImpUsrPub;
    }

    /**
     * Define el valor de la propiedad proyImpUsrPub.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpUsrPub(String value) {
        this.proyImpUsrPub = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpVideos.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyImpVideos() {
        return proyImpVideos;
    }

    /**
     * Define el valor de la propiedad proyImpVideos.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyImpVideos(Long value) {
        this.proyImpVideos = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpVisto.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProyImpVisto() {
        return proyImpVisto;
    }

    /**
     * Define el valor de la propiedad proyImpVisto.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProyImpVisto(Long value) {
        this.proyImpVisto = value;
    }

    /**
     * Obtiene el valor de la propiedad proyImpareaAbreviacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProyImpareaAbreviacion() {
        return proyImpareaAbreviacion;
    }

    /**
     * Define el valor de la propiedad proyImpareaAbreviacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProyImpareaAbreviacion(String value) {
        this.proyImpareaAbreviacion = value;
    }

    /**
     * Obtiene el valor de la propiedad publicar.
     * 
     */
    public boolean isPublicar() {
        return publicar;
    }

    /**
     * Define el valor de la propiedad publicar.
     * 
     */
    public void setPublicar(boolean value) {
        this.publicar = value;
    }

}
