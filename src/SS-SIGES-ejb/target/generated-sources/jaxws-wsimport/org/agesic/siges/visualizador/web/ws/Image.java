
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para image complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="image">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="imageDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageExt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="imagePk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="orden" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "image", propOrder = {
    "imageDesc",
    "imageExt",
    "imageName",
    "imagePk",
    "imagen",
    "orden",
    "tipo"
})
public class Image {

    protected String imageDesc;
    protected String imageExt;
    protected String imageName;
    protected Integer imagePk;
    protected byte[] imagen;
    protected Integer orden;
    protected Integer tipo;

    /**
     * Obtiene el valor de la propiedad imageDesc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageDesc() {
        return imageDesc;
    }

    /**
     * Define el valor de la propiedad imageDesc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageDesc(String value) {
        this.imageDesc = value;
    }

    /**
     * Obtiene el valor de la propiedad imageExt.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageExt() {
        return imageExt;
    }

    /**
     * Define el valor de la propiedad imageExt.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageExt(String value) {
        this.imageExt = value;
    }

    /**
     * Obtiene el valor de la propiedad imageName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * Define el valor de la propiedad imageName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageName(String value) {
        this.imageName = value;
    }

    /**
     * Obtiene el valor de la propiedad imagePk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getImagePk() {
        return imagePk;
    }

    /**
     * Define el valor de la propiedad imagePk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setImagePk(Integer value) {
        this.imagePk = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImagen(byte[] value) {
        this.imagen = value;
    }

    /**
     * Obtiene el valor de la propiedad orden.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * Define el valor de la propiedad orden.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOrden(Integer value) {
        this.orden = value;
    }

    /**
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipo(Integer value) {
        this.tipo = value;
    }

}
