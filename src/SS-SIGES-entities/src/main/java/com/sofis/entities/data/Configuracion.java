package com.sofis.entities.data;


import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Usuario
 */
@Entity
//@Audited
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@Table(name = "ss_configuraciones")
@NamedQueries({@NamedQuery(name = "Configuracion.obtenerPorNombre", query = "SELECT c FROM Configuracion c where c.cnfDescripcion LIKE :cnfDescripcion"),
              @NamedQuery(name = "Configuracion.obtenerPorCodigo", query = "SELECT c FROM Configuracion c where c.cnfCodigo LIKE :cnfCodigo)")})
public class Configuracion implements Serializable {
    
    public static final int CODIGO_LENGHT = 145;
    public static final int VALOR_LENGHT = 45;
    public static final int DESCRIPCION_LENGHT = 245;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="cnf_codigo")
    private String cnfCodigo;
    @Column(name="cnf_descripcion")
    private String cnfDescripcion;
    @Column(name="cnf_valor")
    private String cnfValor;
    @Column(name="cnf_protegido")
    private Boolean cnfProtegido;
    @Column(name="cnf_html")
    private Boolean cnfHtml;
    @JoinColumn(name = "cnf_org_fk", referencedColumnName = "org_pk", nullable = true)
    @ManyToOne(optional = true)
    private Organismos cnfOrgFk;
    
    //Audit
    @Column(name="cnf_ult_usuario")
    @AtributoUltimoUsuario
    private String cnfUltUsuario;
    @Column(name="cnf_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date cnfUltMod;
    @Column(name="cnf_ult_origen")
    private String cnfUltOrigen;
    @Column(name="cnf_version")
    @Version
    private Integer cnfVersion;

    public Configuracion() {
    }

    public Configuracion(String cnfCodigo) {
        this.cnfCodigo = cnfCodigo;
    }

    public Configuracion(String cnfCodigo, String cnfDescripcion, String cnfValor, Organismos cnfOrgFk, Date cnfUltMod, Integer cnfVersion) {
        this.cnfCodigo = cnfCodigo;
        this.cnfDescripcion = cnfDescripcion;
        this.cnfValor = cnfValor;
        this.cnfOrgFk = cnfOrgFk;
        this.cnfUltMod = cnfUltMod;
        this.cnfVersion = cnfVersion;
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Organismos getCnfOrgFk() {
        return cnfOrgFk;
    }

    public void setCnfOrgFk(Organismos cnfOrgFk) {
        this.cnfOrgFk = cnfOrgFk;
    }

    public String getCnfCodigo() {
        return cnfCodigo;
    }

    public void setCnfCodigo(String cnfCodigo) {
        this.cnfCodigo = cnfCodigo;
    }

    public String getCnfDescripcion() {
        return cnfDescripcion;
    }

    public void setCnfDescripcion(String cnfDescripcion) {
        this.cnfDescripcion = cnfDescripcion;
    }

    public String getCnfValor() {
        return cnfValor;
    }

    public void setCnfValor(String cnfValor) {
        this.cnfValor = cnfValor;
    }

    public Boolean getCnfProtegido() {
        return cnfProtegido;
    }

    public void setCnfProtegido(Boolean cnfProtegido) {
        this.cnfProtegido = cnfProtegido;
    }

    public Boolean getCnfHtml() {
        return cnfHtml;
    }

    public void setCnfHtml(Boolean cnfHtml) {
        this.cnfHtml = cnfHtml;
    }

    public String getCnfUltUsuario() {
        return cnfUltUsuario;
    }

    public void setCnfUltUsuario(String cnfUltUsuario) {
        this.cnfUltUsuario = cnfUltUsuario;
    }

    public Date getCnfUltMod() {
        return cnfUltMod;
    }

    public void setCnfUltMod(Date cnfUltMod) {
        this.cnfUltMod = cnfUltMod;
    }

    public String getCnfUltOrigen() {
        return cnfUltOrigen;
    }

    public void setCnfUltOrigen(String cnfUltOrigen) {
        this.cnfUltOrigen = cnfUltOrigen;
    }

    public Integer getCnfVersion() {
        return cnfVersion;
    }

    public void setCnfVersion(Integer cnfVersion) {
        this.cnfVersion = cnfVersion;
    }

    // </editor-fold>
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.generico.entities.Configuracion[ id=" + id + " ]";
    }
    
    @PrePersist
    @PreUpdate
    private void asignarUltMod() {
        this.cnfUltMod=new Date();
    }
}
