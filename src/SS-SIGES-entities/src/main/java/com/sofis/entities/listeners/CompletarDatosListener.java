/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.listeners;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class CompletarDatosListener {

    @PrePersist
    @PreUpdate
    private void registrarMetadatos(Object o) {

        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            if (field.isAnnotationPresent(AtributoUltimoUsuario.class)) {
                String s;
                try {
                    s = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
                    field.set(o, s);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    //Al guardar audiencia desde webservice RemoteUser = null
                }
                //break;
            }
            if (field.isAnnotationPresent(AtributoUltimaModificacion.class)) {
                String s;
                try {
                    field.set(o, new Date());
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                //break;
            }
            if (field.isAnnotationPresent(AtributoUltimaOrigen.class)) {
                String s;
                try {
                    field.set(o, "SGE");
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(NormalizarEntidadListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                //break;
            }
        }

    }
}
