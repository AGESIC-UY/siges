/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.entities.data;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "prog_docs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgDocs.findAll", query = "SELECT p FROM ProgDocs p")})
public class ProgDocs implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgDocsPK progDocsPK;
    @JoinColumn(name = "progdocs_doc_pk", referencedColumnName = "docs_pk", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Documentos documentos;

    public ProgDocs() {
    }

    public ProgDocs(ProgDocsPK progDocsPK) {
        this.progDocsPK = progDocsPK;
    }

    public ProgDocs(int progdocsProgPk, int progdocsDocPk) {
        this.progDocsPK = new ProgDocsPK(progdocsProgPk, progdocsDocPk);
    }

    public ProgDocsPK getProgDocsPK() {
        return progDocsPK;
    }

    public void setProgDocsPK(ProgDocsPK progDocsPK) {
        this.progDocsPK = progDocsPK;
    }

    public Documentos getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Documentos documentos) {
        this.documentos = documentos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (progDocsPK != null ? progDocsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ProgDocs)) {
            return false;
        }
        ProgDocs other = (ProgDocs) object;
        if ((this.progDocsPK == null && other.progDocsPK != null) || (this.progDocsPK != null && !this.progDocsPK.equals(other.progDocsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.ProgDocs[ progDocsPK=" + progDocsPK + " ]";
    }
    
}
