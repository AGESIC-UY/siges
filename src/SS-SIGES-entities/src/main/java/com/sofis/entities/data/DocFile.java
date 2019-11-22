package com.sofis.entities.data;

import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimaOrigen;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 *
 * @author Usuario
 */
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "doc_file")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocFile.findAll", query = "SELECT d FROM DocFile d"),
    @NamedQuery(name = "DocFile.findByDocfilePk", query = "SELECT d FROM DocFile d WHERE d.docfilePk = :docfilePk"),
    @NamedQuery(name = "DocFile.findByDocfileNombre", query = "SELECT d FROM DocFile d WHERE d.docfileNombre = :docfileNombre")})
public class DocFile implements Serializable {
    
    public static final int NOMBRE_LENGHT = 256;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "docfile_pk")
    private Integer docfilePk;
//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "docfile_file")
    
    @Basic(optional = true)
    @Column(name = "docfile_path")
    private String docfilePath;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "docfile_nombre")
    private String docfileNombre;
    
    

    @JoinColumn(name = "docfile_doc_fk", referencedColumnName = "docs_pk")
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    private Documentos docfileDocFk;
    
    @Column(name = "docfile_ult_usuario")
    @AtributoUltimoUsuario
    private String docfileUltUsuario;
    @Column(name = "docfile_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date docfileUltMod;
    @Column(name = "docfile_ult_origen")
    @AtributoUltimaOrigen
    private String docfileUltOrigen;
    @Column(name = "docfile_version")
    @Version
    private Integer docfileVersion;
    
    
    @Transient
    private InputStream docfileFileStream;
    
    @Transient
    private String docfileNombreHist;
    
    @Transient
    private Integer rev;
    
    public DocFile() {
    }

    public DocFile(Integer docfilePk) {
        this.docfilePk = docfilePk;
    }

//    public DocFile(Integer docfilePk, byte[] docfileFile, String docfileNombre) {
//        this.docfilePk = docfilePk;
//        this.docfileFile = docfileFile;
//        this.docfileNombre = docfileNombre;
//    }
    
    public DocFile(Integer docfilePk, InputStream docfileFileStream, String docfileNombre) {
        this.docfilePk = docfilePk;
	this.docfileFileStream = docfileFileStream;
        this.docfileNombre = docfileNombre;
    }

    
    public String[] getDocfileFileNameExtension(){
	String regex = "(.*)\\.(.*)";
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(this.docfileNombre);
	if(matcher.matches()){
	    String[] ret = new String[2];
	    ret[0] = matcher.group(1);
	    ret[1] = matcher.group(2);
	    return ret;
	}
	return null;
    }
    

    
    public Integer getDocfilePk() {
        return docfilePk;
    }

    public void setDocfilePk(Integer docfilePk) {
        this.docfilePk = docfilePk;
    }

//    public byte[] getDocfileFile() {
//        return docfileFile;
//    }
//
//    public void setDocfileFile(byte[] docfileFile) {
//        this.docfileFile = docfileFile;
//    }

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

//    public File getFile() {
//        if (this.docfileFile.length > 0) {
//            FileOutputStream stream = null;
//            File file = new File(this.docfileNombre);
//            try {
//                stream = new FileOutputStream(file);
//                stream.write(this.docfileFile);
//                stream.close();
//
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IOException ex) {
//                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return file;
//        }
//        return null;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (docfilePk != null ? docfilePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
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

    /**
     * @return the docfileFileStream
     */
    public InputStream getDocfileFileStream() {
	return docfileFileStream;
    }

    /**
     * @param docfileFileStream the docfileFileStream to set
     */
    public void setDocfileFileStream(InputStream docfileFileStream) {
	this.docfileFileStream = docfileFileStream;
    }

    /**
     * @return the docfilePath
     */
    public String getDocfilePath() {
	return docfilePath;
    }

    /**
     * @param docfilePath the docfilePath to set
     */
    public void setDocfilePath(String docfilePath) {
	this.docfilePath = docfilePath;
    }

    /**
     * @return the docfileUltUsuario
     */
    public String getDocfileUltUsuario() {
	return docfileUltUsuario;
    }

    /**
     * @param docfileUltUsuario the docfileUltUsuario to set
     */
    public void setDocfileUltUsuario(String docfileUltUsuario) {
	this.docfileUltUsuario = docfileUltUsuario;
    }

    /**
     * @return the docfileUltMod
     */
    public Date getDocfileUltMod() {
	return docfileUltMod;
    }

    /**
     * @param docfileUltMod the docfileUltMod to set
     */
    public void setDocfileUltMod(Date docfileUltMod) {
	this.docfileUltMod = docfileUltMod;
    }

    /**
     * @return the docfileUltOrigen
     */
    public String getDocfileUltOrigen() {
	return docfileUltOrigen;
    }

    /**
     * @param docfileUltOrigen the docfileUltOrigen to set
     */
    public void setDocfileUltOrigen(String docfileUltOrigen) {
	this.docfileUltOrigen = docfileUltOrigen;
    }

    /**
     * @return the docfileVersion
     */
    public Integer getDocfileVersion() {
	return docfileVersion;
    }

    /**
     * @param docfileVersion the docfileVersion to set
     */
    public void setDocfileVersion(Integer docfileVersion) {
	this.docfileVersion = docfileVersion;
    }

    /**
     * @return the docfileNombreHist
     */
    public String getDocfileNombreHist() {
	return docfileNombreHist;
    }

    /**
     * @param docfileNombreHist the docfileNombreHist to set
     */
    public void setDocfileNombreHist(String docfileNombreHist) {
	this.docfileNombreHist = docfileNombreHist;
    }

    /**
     * @return the rev
     */
    public Integer getRev() {
        return rev;
    }

    /**
     * @param rev the rev to set
     */
    public void setRev(Integer rev) {
        this.rev = rev;
    }


}
