/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sofis.persistence.dao.reference;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author SGDC 
 */
public interface EntityBase  extends Serializable{

    /**
     * Es invocado antes de salvar el objeto en la base de datos
     */
    public void beforeSaveOrUpadate();

    /**
     * Es invocado despues de salvar el objeto en la base de datos
     */
    public void afterSaveOrUpadate();

    /**
     * Es invocado antes de renderizar el objeto en la interface en el caso de edicion del objeto
     * este metodo puede ser utilizado para seteat valores por defecto en el caso de EDIT
     */
    public void beforeRendered();

    /**
     *
     * Es invocado despues de realizar el new del objeto en el caso de una accion de tipo NEW
     * este metodo puede ser utilizado para setear valores por defecto al objeto en el caso de NEW
     *
     * @param objetos es el objeto con los elementos del contexto, por ejemplo se puede acceder al model
     * mediante el nombre del mismo.
     */
    public void afterInit(HashMap objetos);

    /**
     * Es invocado cuando se ejecuta la accion de previsualizar la entidad desde el resultado de la búsqueda
     * @return EL string representando el HTML que se va a desplegar para previsualizar la entidad
     */
    public String previewHTML();

    /**
     * Metodo qeu es invocado en la accion de clone, luego de clonar el objeto por el clonador por defecto
     * @param clonedObject el elemento clonado
     * @return
     */
    public Object onClone(Object clonedObject);



}
