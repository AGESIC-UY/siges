
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para estadoProyectos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="estadoProyectos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estCodigo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estHabilitado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="estNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estadoProyectos", propOrder = {
    "estCodigo",
    "estHabilitado",
    "estNombre",
    "estPk"
})
public class EstadoProyectos {

    protected String estCodigo;
    protected Boolean estHabilitado;
    protected String estNombre;
    protected Integer estPk;

    /**
     * Obtiene el valor de la propiedad estCodigo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstCodigo() {
        return estCodigo;
    }

    /**
     * Define el valor de la propiedad estCodigo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstCodigo(String value) {
        this.estCodigo = value;
    }

    /**
     * Obtiene el valor de la propiedad estHabilitado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEstHabilitado() {
        return estHabilitado;
    }

    /**
     * Define el valor de la propiedad estHabilitado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEstHabilitado(Boolean value) {
        this.estHabilitado = value;
    }

    /**
     * Obtiene el valor de la propiedad estNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstNombre() {
        return estNombre;
    }

    /**
     * Define el valor de la propiedad estNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstNombre(String value) {
        this.estNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad estPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEstPk() {
        return estPk;
    }

    /**
     * Define el valor de la propiedad estPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEstPk(Integer value) {
        this.estPk = value;
    }

}
