
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para docsImpProyecto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="docsImpProyecto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="docsImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="docsImpNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docsImpFileData" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "docsImpProyecto", propOrder = {
    "docsImpPk",
    "docsImpNombre",
    "docsImpFileData"
})
public class DocsImpProyecto {

    protected Integer docsImpPk;
    protected String docsImpNombre;
    protected byte[] docsImpFileData;

    /**
     * Obtiene el valor de la propiedad docsImpPk.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocsImpPk() {
        return docsImpPk;
    }

    /**
     * Define el valor de la propiedad docsImpPk.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocsImpPk(Integer value) {
        this.docsImpPk = value;
    }

    /**
     * Obtiene el valor de la propiedad docsImpNombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocsImpNombre() {
        return docsImpNombre;
    }

    /**
     * Define el valor de la propiedad docsImpNombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocsImpNombre(String value) {
        this.docsImpNombre = value;
    }

    /**
     * Obtiene el valor de la propiedad docsImpFileData.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocsImpFileData() {
        return docsImpFileData;
    }

    /**
     * Define el valor de la propiedad docsImpFileData.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocsImpFileData(byte[] value) {
        this.docsImpFileData = value;
    }

}
