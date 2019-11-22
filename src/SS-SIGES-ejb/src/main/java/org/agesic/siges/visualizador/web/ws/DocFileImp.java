
package org.agesic.siges.visualizador.web.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for docFileImp complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="docFileImp">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="docfileImpBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="docfileImpDocFk" type="{http://ws.web.visualizador.siges.agesic.org/}documentosImp" minOccurs="0"/>
 *         &lt;element name="docfileImpNombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docfileImpOrigenPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="docfileImpPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docfileImpPk" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "docFileImp", propOrder = {
    "docfileImpBytes",
    "docfileImpDocFk",
    "docfileImpNombre",
    "docfileImpOrigenPk",
    "docfileImpPath",
    "docfileImpPk"
})
public class DocFileImp {

    protected byte[] docfileImpBytes;
    protected DocumentosImp docfileImpDocFk;
    protected String docfileImpNombre;
    protected Integer docfileImpOrigenPk;
    protected String docfileImpPath;
    protected Integer docfileImpPk;

    /**
     * Gets the value of the docfileImpBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getDocfileImpBytes() {
        return docfileImpBytes;
    }

    /**
     * Sets the value of the docfileImpBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setDocfileImpBytes(byte[] value) {
        this.docfileImpBytes = value;
    }

    /**
     * Gets the value of the docfileImpDocFk property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentosImp }
     *     
     */
    public DocumentosImp getDocfileImpDocFk() {
        return docfileImpDocFk;
    }

    /**
     * Sets the value of the docfileImpDocFk property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentosImp }
     *     
     */
    public void setDocfileImpDocFk(DocumentosImp value) {
        this.docfileImpDocFk = value;
    }

    /**
     * Gets the value of the docfileImpNombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocfileImpNombre() {
        return docfileImpNombre;
    }

    /**
     * Sets the value of the docfileImpNombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocfileImpNombre(String value) {
        this.docfileImpNombre = value;
    }

    /**
     * Gets the value of the docfileImpOrigenPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocfileImpOrigenPk() {
        return docfileImpOrigenPk;
    }

    /**
     * Sets the value of the docfileImpOrigenPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocfileImpOrigenPk(Integer value) {
        this.docfileImpOrigenPk = value;
    }

    /**
     * Gets the value of the docfileImpPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocfileImpPath() {
        return docfileImpPath;
    }

    /**
     * Sets the value of the docfileImpPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocfileImpPath(String value) {
        this.docfileImpPath = value;
    }

    /**
     * Gets the value of the docfileImpPk property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDocfileImpPk() {
        return docfileImpPk;
    }

    /**
     * Sets the value of the docfileImpPk property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDocfileImpPk(Integer value) {
        this.docfileImpPk = value;
    }

}
