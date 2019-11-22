
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estadoProyectos complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the estCodigo property.
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
     * Sets the value of the estCodigo property.
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
     * Gets the value of the estHabilitado property.
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
     * Sets the value of the estHabilitado property.
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
     * Gets the value of the estNombre property.
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
     * Sets the value of the estNombre property.
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
     * Gets the value of the estPk property.
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
     * Sets the value of the estPk property.
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
