
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.agesic.siges.visualizador.web.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MediaImpProyectos_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "mediaImpProyectos");
    private final static QName _LatlngProyectoImp_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "latlngProyectoImp");
    private final static QName _PublicarProyecto_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "publicarProyecto");
    private final static QName _PublicarProyectoResponse_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "publicarProyectoResponse");
    private final static QName _EstadoProyectos_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "estadoProyectos");
    private final static QName _EntregablesImp_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "entregablesImp");
    private final static QName _CategoriaProyectosResponse_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "categoriaProyectosResponse");
    private final static QName _Moneda_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "moneda");
    private final static QName _Image_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "image");
    private final static QName _ProyectoImportado_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "ProyectoImportado");
    private final static QName _FuenteFinanciamiento_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "fuenteFinanciamiento");
    private final static QName _CategoriaProyectos_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "categoriaProyectos");
    private final static QName _ObtenerCategoriasXml_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "obtenerCategoriasXml");
    private final static QName _TiposMedia_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "tiposMedia");
    private final static QName _Departamentos_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "departamentos");
    private final static QName _Organizaciones_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "organizaciones");
    private final static QName _Organismos_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "organismos");
    private final static QName _ObtenerCategoriasXmlResponse_QNAME = new QName("http://ws.web.visualizador.siges.agesic.org/", "obtenerCategoriasXmlResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.agesic.siges.visualizador.web.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PublicarProyecto_Type }
     * 
     */
    public PublicarProyecto_Type createPublicarProyecto_Type() {
        return new PublicarProyecto_Type();
    }

    /**
     * Create an instance of {@link MediaImpProyectos }
     * 
     */
    public MediaImpProyectos createMediaImpProyectos() {
        return new MediaImpProyectos();
    }

    /**
     * Create an instance of {@link LatlngProyectoImp }
     * 
     */
    public LatlngProyectoImp createLatlngProyectoImp() {
        return new LatlngProyectoImp();
    }

    /**
     * Create an instance of {@link EntregablesImp }
     * 
     */
    public EntregablesImp createEntregablesImp() {
        return new EntregablesImp();
    }

    /**
     * Create an instance of {@link PublicarProyectoResponse }
     * 
     */
    public PublicarProyectoResponse createPublicarProyectoResponse() {
        return new PublicarProyectoResponse();
    }

    /**
     * Create an instance of {@link EstadoProyectos }
     * 
     */
    public EstadoProyectos createEstadoProyectos() {
        return new EstadoProyectos();
    }

    /**
     * Create an instance of {@link Moneda }
     * 
     */
    public Moneda createMoneda() {
        return new Moneda();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link CategoriaProyectosResponse }
     * 
     */
    public CategoriaProyectosResponse createCategoriaProyectosResponse() {
        return new CategoriaProyectosResponse();
    }

    /**
     * Create an instance of {@link FuenteFinanciamiento }
     * 
     */
    public FuenteFinanciamiento createFuenteFinanciamiento() {
        return new FuenteFinanciamiento();
    }

    /**
     * Create an instance of {@link ProyectoImportado }
     * 
     */
    public ProyectoImportado createProyectoImportado() {
        return new ProyectoImportado();
    }

    /**
     * Create an instance of {@link CategoriaProyectos }
     * 
     */
    public CategoriaProyectos createCategoriaProyectos() {
        return new CategoriaProyectos();
    }

    /**
     * Create an instance of {@link TiposMedia }
     * 
     */
    public TiposMedia createTiposMedia() {
        return new TiposMedia();
    }

    /**
     * Create an instance of {@link ObtenerCategoriasXml }
     * 
     */
    public ObtenerCategoriasXml createObtenerCategoriasXml() {
        return new ObtenerCategoriasXml();
    }

    /**
     * Create an instance of {@link Departamentos }
     * 
     */
    public Departamentos createDepartamentos() {
        return new Departamentos();
    }

    /**
     * Create an instance of {@link Organizaciones }
     * 
     */
    public Organizaciones createOrganizaciones() {
        return new Organizaciones();
    }

    /**
     * Create an instance of {@link ObtenerCategoriasXmlResponse }
     * 
     */
    public ObtenerCategoriasXmlResponse createObtenerCategoriasXmlResponse() {
        return new ObtenerCategoriasXmlResponse();
    }

    /**
     * Create an instance of {@link Organismos }
     * 
     */
    public Organismos createOrganismos() {
        return new Organismos();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MediaImpProyectos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "mediaImpProyectos")
    public JAXBElement<MediaImpProyectos> createMediaImpProyectos(MediaImpProyectos value) {
        return new JAXBElement<MediaImpProyectos>(_MediaImpProyectos_QNAME, MediaImpProyectos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LatlngProyectoImp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "latlngProyectoImp")
    public JAXBElement<LatlngProyectoImp> createLatlngProyectoImp(LatlngProyectoImp value) {
        return new JAXBElement<LatlngProyectoImp>(_LatlngProyectoImp_QNAME, LatlngProyectoImp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublicarProyecto_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "publicarProyecto")
    public JAXBElement<PublicarProyecto_Type> createPublicarProyecto(PublicarProyecto_Type value) {
        return new JAXBElement<PublicarProyecto_Type>(_PublicarProyecto_QNAME, PublicarProyecto_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PublicarProyectoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "publicarProyectoResponse")
    public JAXBElement<PublicarProyectoResponse> createPublicarProyectoResponse(PublicarProyectoResponse value) {
        return new JAXBElement<PublicarProyectoResponse>(_PublicarProyectoResponse_QNAME, PublicarProyectoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstadoProyectos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "estadoProyectos")
    public JAXBElement<EstadoProyectos> createEstadoProyectos(EstadoProyectos value) {
        return new JAXBElement<EstadoProyectos>(_EstadoProyectos_QNAME, EstadoProyectos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntregablesImp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "entregablesImp")
    public JAXBElement<EntregablesImp> createEntregablesImp(EntregablesImp value) {
        return new JAXBElement<EntregablesImp>(_EntregablesImp_QNAME, EntregablesImp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaProyectosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "categoriaProyectosResponse")
    public JAXBElement<CategoriaProyectosResponse> createCategoriaProyectosResponse(CategoriaProyectosResponse value) {
        return new JAXBElement<CategoriaProyectosResponse>(_CategoriaProyectosResponse_QNAME, CategoriaProyectosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Moneda }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "moneda")
    public JAXBElement<Moneda> createMoneda(Moneda value) {
        return new JAXBElement<Moneda>(_Moneda_QNAME, Moneda.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Image }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "image")
    public JAXBElement<Image> createImage(Image value) {
        return new JAXBElement<Image>(_Image_QNAME, Image.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProyectoImportado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "ProyectoImportado")
    public JAXBElement<ProyectoImportado> createProyectoImportado(ProyectoImportado value) {
        return new JAXBElement<ProyectoImportado>(_ProyectoImportado_QNAME, ProyectoImportado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuenteFinanciamiento }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "fuenteFinanciamiento")
    public JAXBElement<FuenteFinanciamiento> createFuenteFinanciamiento(FuenteFinanciamiento value) {
        return new JAXBElement<FuenteFinanciamiento>(_FuenteFinanciamiento_QNAME, FuenteFinanciamiento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaProyectos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "categoriaProyectos")
    public JAXBElement<CategoriaProyectos> createCategoriaProyectos(CategoriaProyectos value) {
        return new JAXBElement<CategoriaProyectos>(_CategoriaProyectos_QNAME, CategoriaProyectos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCategoriasXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "obtenerCategoriasXml")
    public JAXBElement<ObtenerCategoriasXml> createObtenerCategoriasXml(ObtenerCategoriasXml value) {
        return new JAXBElement<ObtenerCategoriasXml>(_ObtenerCategoriasXml_QNAME, ObtenerCategoriasXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TiposMedia }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "tiposMedia")
    public JAXBElement<TiposMedia> createTiposMedia(TiposMedia value) {
        return new JAXBElement<TiposMedia>(_TiposMedia_QNAME, TiposMedia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Departamentos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "departamentos")
    public JAXBElement<Departamentos> createDepartamentos(Departamentos value) {
        return new JAXBElement<Departamentos>(_Departamentos_QNAME, Departamentos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organizaciones }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "organizaciones")
    public JAXBElement<Organizaciones> createOrganizaciones(Organizaciones value) {
        return new JAXBElement<Organizaciones>(_Organizaciones_QNAME, Organizaciones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organismos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "organismos")
    public JAXBElement<Organismos> createOrganismos(Organismos value) {
        return new JAXBElement<Organismos>(_Organismos_QNAME, Organismos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerCategoriasXmlResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.web.visualizador.siges.agesic.org/", name = "obtenerCategoriasXmlResponse")
    public JAXBElement<ObtenerCategoriasXmlResponse> createObtenerCategoriasXmlResponse(ObtenerCategoriasXmlResponse value) {
        return new JAXBElement<ObtenerCategoriasXmlResponse>(_ObtenerCategoriasXmlResponse_QNAME, ObtenerCategoriasXmlResponse.class, null, value);
    }

}
