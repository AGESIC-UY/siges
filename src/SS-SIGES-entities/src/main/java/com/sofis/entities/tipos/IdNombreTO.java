package com.sofis.entities.tipos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto generico para transportar ID y nombre de una entidad.
 *
 * @author Sofis Solutions (www.sofis-solutions.com)
 */
@XmlRootElement
public class IdNombreTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;

    public IdNombreTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
