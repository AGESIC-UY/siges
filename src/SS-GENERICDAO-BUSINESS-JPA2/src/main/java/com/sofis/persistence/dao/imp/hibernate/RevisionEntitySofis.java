/*
 *  Clase desarrollada por Sofis Solutions para el
 *  proyecto SGREC.
 */
package com.sofis.persistence.dao.imp.hibernate;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

/**
 * Clase desarrollada por Sofis Solutions
 * @author Sofis Solutions
 */

@Entity
@Table(name="his_revinfo")
@RevisionEntity(RevisionEntityListener.class)
public class RevisionEntitySofis implements Serializable{

    @Id
    @GeneratedValue
    @RevisionNumber
    private int id;
    @RevisionTimestamp
    private long dateMod;
    private String username;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
