
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para comentario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="comentario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comFecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="comNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="comProyFk" type="{http://ws.web.visualizador.siges.agesic.org/}proyectos" minOccurs="0"/>
 *         &lt;element name="comTexto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comentario", propOrder = {
    "comFecha",
    "comNombre",
    "comPk",
    "comProyFk",
    "comTexto"
})
public class Comentario {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar comFecha;
    protected String comNombre;
    protected Integer comPk;
    protected Proyectos comProyFk;
    protected String comTexto;

    /**
     * Obtiene el valor de la propiedad comFecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getComFecha() {
        return comFecha;
    }

    /**
     * Define el valor de la propiedad comFecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setComFecha(XMLGregorianCalendar value) {
        this.comFecha = value;
    }

    /**
     * Obtiene el valor de la propiedad comNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComNombre() {
        return comNombre;
    }

    /**
     * Define el valor de la propiedad comNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComNombre(String value) {
        this.comNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad comPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getComPk() {
        return comPk;
    }

    /**
     * Define el valor de la propiedad comPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setComPk(Integer value) {
        this.comPk = value;
    }

    /**
     * Obtiene el valor de la propiedad comProyFk.
     * 
     * @return
     *     possible object is
     *     {@link Proyectos }
     *     
     */
    public Proyectos getComProyFk() {
        return comProyFk;
    }

    /**
     * Define el valor de la propiedad comProyFk.
     * 
     * @param value
     *     allowed object is
     *     {@link Proyectos }
     *     
     */
    public void setComProyFk(Proyectos value) {
        this.comProyFk = value;
    }

    /**
     * Obtiene el valor de la propiedad comTexto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComTexto() {
        return comTexto;
    }

    /**
     * Define el valor de la propiedad comTexto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComTexto(String value) {
        this.comTexto = value;
    }

}
