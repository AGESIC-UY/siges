/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Usuario
 */
@Embeddable
public class ProgDocsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "progdocs_prog_pk")
    private int progdocsProgPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "progdocs_doc_pk")
    private int progdocsDocPk;

    public ProgDocsPK() {
    }

    public ProgDocsPK(int progdocsProgPk, int progdocsDocPk) {
        this.progdocsProgPk = progdocsProgPk;
        this.progdocsDocPk = progdocsDocPk;
    }

    public int getProgdocsProgPk() {
        return progdocsProgPk;
    }

    public void setProgdocsProgPk(int progdocsProgPk) {
        this.progdocsProgPk = progdocsProgPk;
    }

    public int getProgdocsDocPk() {
        return progdocsDocPk;
    }

    public void setProgdocsDocPk(int progdocsDocPk) {
        this.progdocsDocPk = progdocsDocPk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) progdocsProgPk;
        hash += (int) progdocsDocPk;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProgDocsPK)) {
            return false;
        }
        ProgDocsPK other = (ProgDocsPK) object;
        if (this.progdocsProgPk != other.progdocsProgPk) {
            return false;
        }
        if (this.progdocsDocPk != other.progdocsDocPk) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProgDocsPK[ progdocsProgPk=" + progdocsProgPk + ", progdocsDocPk=" + progdocsDocPk + " ]";
    }
    
}
