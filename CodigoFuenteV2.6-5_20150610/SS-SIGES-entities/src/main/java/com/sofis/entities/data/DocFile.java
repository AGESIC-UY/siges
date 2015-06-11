package com.sofis.entities.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "doc_file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocFile.findAll", query = "SELECT d FROM DocFile d"),
    @NamedQuery(name = "DocFile.findByDocfilePk", query = "SELECT d FROM DocFile d WHERE d.docfilePk = :docfilePk"),
    @NamedQuery(name = "DocFile.findByDocfileNombre", query = "SELECT d FROM DocFile d WHERE d.docfileNombre = :docfileNombre")})
public class DocFile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "docfile_pk")
    private Integer docfilePk;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "docfile_file")
    private byte[] docfileFile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "docfile_nombre")
    private String docfileNombre;
    
    @JoinColumn(name = "docfile_doc_fk", referencedColumnName = "docs_pk")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Documentos docfileDocFk;

    public DocFile() {
    }

    public DocFile(Integer docfilePk) {
        this.docfilePk = docfilePk;
    }

    public DocFile(Integer docfilePk, byte[] docfileFile, String docfileNombre) {
        this.docfilePk = docfilePk;
        this.docfileFile = docfileFile;
        this.docfileNombre = docfileNombre;
    }

    public Integer getDocfilePk() {
        return docfilePk;
    }

    public void setDocfilePk(Integer docfilePk) {
        this.docfilePk = docfilePk;
    }

    public byte[] getDocfileFile() {
        return docfileFile;
    }

    public void setDocfileFile(byte[] docfileFile) {
        this.docfileFile = docfileFile;
    }

    public String getDocfileNombre() {
        return docfileNombre;
    }

    public void setDocfileNombre(String docfileNombre) {
        this.docfileNombre = docfileNombre;
    }

    public Documentos getDocfileDocFk() {
        return docfileDocFk;
    }

    public void setDocfileDocFk(Documentos docfileDocFk) {
        this.docfileDocFk = docfileDocFk;
    }

    public File getFile() {
        if (this.docfileFile.length > 0) {
            FileOutputStream stream = null;
            File file = new File(this.docfileNombre);
            try {
                stream = new FileOutputStream(file);
                stream.write(this.docfileFile);
                stream.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return file;
        }
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docfilePk != null ? docfilePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocFile)) {
            return false;
        }
        DocFile other = (DocFile) object;
        if ((this.docfilePk == null && other.docfilePk != null) || (this.docfilePk != null && !this.docfilePk.equals(other.docfilePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.DocFile[ docfilePk=" + docfilePk + " ]";
    }
    
}
