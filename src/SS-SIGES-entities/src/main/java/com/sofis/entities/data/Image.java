package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "image")
@XmlRootElement
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "image_pk")
    private Integer imagePk;
    /**
     * Extensión del archivo de imagen.
     */
    @Column(name = "image_ext")
    private String imageExt;
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "image_desc", length = 255)
    private String imageDesc;
    /**
     * Tipo de imagen: 1-Banner, 2-Categorias, 0-Varios
     */
    @Column(name = "image_tipo")
    private Integer tipo;
    @Column(name = "image_blob")
//    @Lob
    private byte[] imagen;
    /**
     * Orden en el que se van visualizando los banner.
     */
    @Column(name = "image_orden", nullable = false, length = 5)
    private Integer orden;

    public Integer getImagePk() {
        return imagePk;
    }

    public void setImagePk(Integer imagePk) {
        this.imagePk = imagePk;
    }

    public String getImageExt() {
        return imageExt;
    }

    public void setImageExt(String imageExt) {
        this.imageExt = imageExt;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public void setImageDesc(String imageDesc) {
        this.imageDesc = imageDesc;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

}
