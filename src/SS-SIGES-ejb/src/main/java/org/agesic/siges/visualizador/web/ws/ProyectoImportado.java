
package org.agesic.siges.visualizador.web.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for proyectoImportado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="proyectoImportado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ordered" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="docsImpList" type="{http://ws.web.visualizador.siges.agesic.org/}documentosImp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="entregablesProyectoImpList" type="{http://ws.web.visualizador.siges.agesic.org/}entregablesImp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="incorporado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="latlngProyectoImpList" type="{http://ws.web.visualizador.siges.agesic.org/}latlngProyectoImp" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mediaImpProyectosIds" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="vigente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "proyectoImportado", propOrder = {
    "ordered",
    "docsImpList",
    "entregablesProyectoImpList",
    "incorporado",
    "latlngProyectoImpList",
    "mediaImpProyectosIds",
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
    "publicar",
    "vigente"
})
public class ProyectoImportado {

    protected Boolean ordered;
    @XmlElement(nillable = true)
    protected List<DocumentosImp> docsImpList;
    @XmlElement(nillable = true)
    protected List<EntregablesImp> entregablesProyectoImpList;
    protected Boolean incorporado;
    @XmlElement(nillable = true)
    protected List<LatlngProyectoImp> latlngProyectoImpList;
    protected String mediaImpProyectosIds;
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
    protected Boolean vigente;

    /**
     * Gets the value of the ordered property.
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
     * Sets the value of the ordered property.
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
     * Gets the value of the docsImpList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docsImpList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocsImpList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentosImp }
     * 
     * 
     */
    public List<DocumentosImp> getDocsImpList() {
       /* if (docsImpList == null) {
            docsImpList = new ArrayList<DocumentosImp>();
        }*/
        return this.docsImpList;
    }

    public void setDocsImpList(List<DocumentosImp> listDocs) {
        docsImpList = listDocs;
    }    
    
    
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
     * Gets the value of the incorporado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncorporado() {
        return incorporado;
    }

    /**
     * Sets the value of the incorporado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncorporado(Boolean value) {
        this.incorporado = value;
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
     * Gets the value of the mediaImpProyectosIds property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMediaImpProyectosIds() {
        return mediaImpProyectosIds;
    }

    /**
     * Sets the value of the mediaImpProyectosIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMediaImpProyectosIds(String value) {
        this.mediaImpProyectosIds = value;
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

    public void setMediaImpProyectosList(List<MediaImpProyectos> listMedia) {
            mediaImpProyectosList = listMedia;
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
     * Gets the value of the proyDeptoAreaGerente property.
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
     * Sets the value of the proyDeptoAreaGerente property.
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
     * Gets the value of the proyGerente property.
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
     * Sets the value of the proyGerente property.
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
     * Gets the value of the proyGerenteEmail property.
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
     * Sets the value of the proyGerenteEmail property.
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
     * Gets the value of the proyGerenteTel property.
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
     * Sets the value of the proyGerenteTel property.
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
     * Gets the value of the proyImpAreaNombre property.
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
     * Sets the value of the proyImpAreaNombre property.
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
     * Gets the value of the proyImpAreaPk property.
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
     * Sets the value of the proyImpAreaPk property.
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
     * Gets the value of the proyImpAvanceFecha property.
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
     * Sets the value of the proyImpAvanceFecha property.
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
     * Gets the value of the proyImpAvancePorc property.
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
     * Sets the value of the proyImpAvancePorc property.
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
     * Gets the value of the proyImpCamara property.
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
     * Sets the value of the proyImpCamara property.
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
     * Gets the value of the proyImpCompartido property.
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
     * Sets the value of the proyImpCompartido property.
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
     * Gets the value of the proyImpContacto property.
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
     * Sets the value of the proyImpContacto property.
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
     * Gets the value of the proyImpDescripcion property.
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
     * Sets the value of the proyImpDescripcion property.
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
     * Gets the value of the proyImpDescripcionStrip property.
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
     * Sets the value of the proyImpDescripcionStrip property.
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
     * Gets the value of the proyImpEjecOrgPk property.
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
     * Sets the value of the proyImpEjecOrgPk property.
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
     * Gets the value of the proyImpEstadoFk property.
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
     * Sets the value of the proyImpEstadoFk property.
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
     * Gets the value of the proyImpEstadoPubFk property.
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
     * Sets the value of the proyImpEstadoPubFk property.
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
     * Gets the value of the proyImpFechaFin property.
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
     * Sets the value of the proyImpFechaFin property.
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
     * Gets the value of the proyImpFechaImp property.
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
     * Sets the value of the proyImpFechaImp property.
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
     * Gets the value of the proyImpFechaInicio property.
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
     * Sets the value of the proyImpFechaInicio property.
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
     * Gets the value of the proyImpFechaMod property.
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
     * Sets the value of the proyImpFechaMod property.
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
     * Gets the value of the proyImpFechaPub property.
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
     * Sets the value of the proyImpFechaPub property.
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
     * Gets the value of the proyImpFinanciamientoFueFk property.
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
     * Sets the value of the proyImpFinanciamientoFueFk property.
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
     * Gets the value of the proyImpFotos property.
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
     * Sets the value of the proyImpFotos property.
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
     * Gets the value of the proyImpInvMonedaMonFk property.
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
     * Sets the value of the proyImpInvMonedaMonFk property.
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
     * Gets the value of the proyImpInvTotal property.
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
     * Sets the value of the proyImpInvTotal property.
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
     * Gets the value of the proyImpModificado property.
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
     * Sets the value of the proyImpModificado property.
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
     * Gets the value of the proyImpNombre property.
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
     * Sets the value of the proyImpNombre property.
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
     * Gets the value of the proyImpObjPublico property.
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
     * Sets the value of the proyImpObjPublico property.
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
     * Gets the value of the proyImpObjetivo property.
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
     * Sets the value of the proyImpObjetivo property.
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
     * Gets the value of the proyImpObservaciones property.
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
     * Sets the value of the proyImpObservaciones property.
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
     * Gets the value of the proyImpOrgFk property.
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
     * Sets the value of the proyImpOrgFk property.
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
     * Gets the value of the proyImpOrigen property.
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
     * Sets the value of the proyImpOrigen property.
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
     * Gets the value of the proyImpOrigenPk property.
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
     * Sets the value of the proyImpOrigenPk property.
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
     * Gets the value of the proyImpOrigenrecOrg property.
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
     * Sets the value of the proyImpOrigenrecOrg property.
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
     * Gets the value of the proyImpOrigenrecOrgPk property.
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
     * Sets the value of the proyImpOrigenrecOrgPk property.
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
     * Gets the value of the proyImpPk property.
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
     * Sets the value of the proyImpPk property.
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
     * Gets the value of the proyImpPlazoEstimado property.
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
     * Sets the value of the proyImpPlazoEstimado property.
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
     * Gets the value of the proyImpProvOrgFk property.
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
     * Sets the value of the proyImpProvOrgFk property.
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
     * Gets the value of the proyImpSitActual property.
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
     * Sets the value of the proyImpSitActual property.
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
     * Gets the value of the proyImpSitActualDate property.
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
     * Sets the value of the proyImpSitActualDate property.
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
     * Gets the value of the proyImpTemaFk property.
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
     * Sets the value of the proyImpTemaFk property.
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
     * Gets the value of the proyImpUsrMod property.
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
     * Sets the value of the proyImpUsrMod property.
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
     * Gets the value of the proyImpUsrPub property.
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
     * Sets the value of the proyImpUsrPub property.
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
     * Gets the value of the proyImpVideos property.
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
     * Sets the value of the proyImpVideos property.
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
     * Gets the value of the proyImpVisto property.
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
     * Sets the value of the proyImpVisto property.
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
     * Gets the value of the proyImpareaAbreviacion property.
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
     * Sets the value of the proyImpareaAbreviacion property.
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
     * Gets the value of the publicar property.
     * 
     */
    public boolean isPublicar() {
        return publicar;
    }

    /**
     * Sets the value of the publicar property.
     * 
     */
    public void setPublicar(boolean value) {
        this.publicar = value;
    }

    /**
     * Gets the value of the vigente property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVigente() {
        return vigente;
    }

    /**
     * Sets the value of the vigente property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVigente(Boolean value) {
        this.vigente = value;
    }

}
