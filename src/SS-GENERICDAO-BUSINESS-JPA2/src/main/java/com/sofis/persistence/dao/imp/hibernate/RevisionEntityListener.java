/*
 *  Clase desarrollada por Sofis Solutions
 */

package com.sofis.persistence.dao.imp.hibernate;

import java.io.Serializable;
import org.hibernate.envers.RevisionListener;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */
public class RevisionEntityListener implements RevisionListener, Serializable{


    //La variable para setear la description y el nombre del usuario
    public static ThreadLocal<String> description = new ThreadLocal<String>();
    public static ThreadLocal<String> userName = new ThreadLocal<String>();


    public void newRevision(Object revisionEntity) {
        String decri = description.get();
        String user = userName.get();
        RevisionEntitySofis rev = (RevisionEntitySofis) revisionEntity;
        rev.setDescription(decri);
        rev.setUsername(user);
    }
}
