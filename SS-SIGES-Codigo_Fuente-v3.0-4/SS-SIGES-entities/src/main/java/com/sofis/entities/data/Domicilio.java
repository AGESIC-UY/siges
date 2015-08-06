package com.sofis.entities.data;

import com.sofis.entities.constantes.ConstantesEstandares;
import com.sofis.generico.utils.generalutils.StringsUtils;
import com.sofis.entities.annotations.AtributoUltimaModificacion;
import com.sofis.entities.annotations.AtributoUltimoUsuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import javax.validation.constraints.*;

/**
 *
 * @author Usuario
 */
@Entity
@Audited
@Table(name = "ss_domicilios")
public class Domicilio implements Serializable {

    @Size(max = 255)
    @Column(name = "dom_oficina", length = 255)
    private String domOficina;
    @Size(max = 255)
    @Column(name = "dom_depto_alt", length = 255)
    private String domDeptoAlt;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dom_id")
    private Integer domId;
    //Nombre de Vialidad
    @Column(name = "dom_calle")
    @Size(max = 150)
    private String domCalle;
    //NÃºmero de puerta
    @Column(name = "dom_numero_puerta")
    @Pattern(regexp = "[0-9]*")
    @Size(max = 5)
    private String domNumeroPuerta;
    //KilÃ³metro
    @Pattern(regexp = "[0-9]*")
    @Size(max = 9)
    @Column(name = "dom_kilometro")
    private String domKilometro;
    //Manzana Catastral
    @Size(max = 5)
    @Column(name = "dom_manzana")
    private String domManzana;
    //Solar Catastral
    @Column(name = "dom_solar")
    @Size(max = 5)
    @Pattern(regexp = "[0-9]*")
    private String domSolar;
    //NÃºmero de Ruta
    @Column(name = "dom_ruta")
    @Size(max = 5)
    @Pattern(regexp = "[0-9]*")
    private String domRuta;
    //CÃ³digo Postal
    @Column(name = "dom_codigo_postal")
    @Size(max = 5)
    @Pattern(regexp = "[0-9]*")
    private String domCodigoPostal;
    //Unidad
    @Column(name = "dom_apto")
    @Size(max = 50)
    private String domApto;
    //DecripciÃ³n de la UbicaciÃ³n
    @Column(name = "dom_descripcion")
    @Size(max = 300)
    private String domDescripcion;
    //PaÃ­s
    @ManyToOne
    @JoinColumn(name = "dom_pai_id", referencedColumnName = "pai_id")
    private Pais domPais;
    //Departamento
    @ManyToOne
    @JoinColumn(name = "dom_depto_id", referencedColumnName = "depto_id")
    private Departamento domDepto;
    @ManyToOne
    @JoinColumn(name = "dom_loc_id", referencedColumnName = "loc_id")
    private Localidad domLocId;
    //Letra de Puerta
    @Column(name = "dom_letra")
    private String domBis;
    //Nombre de Inmueble
    @Column(name = "dom_inmueble_nombre")
    @Size(max = 100)
    private String domInmuebleNombre;
    @ManyToOne
    @JoinColumn(name = "dom_tec_id", referencedColumnName = "tec_id")
    private TipoEntradaColectiva domTipoEntradaColectiva;
    @JoinColumn(name = "dom_par_id", referencedColumnName = "par_id")
    @ManyToOne
    private Paridad domParidad;
    @JoinColumn(name = "dom_tvi_id", referencedColumnName = "tvi_id")
    @ManyToOne
    private TipoVialidad domTipoVialidad;
    //Audit
    @Column(name = "dom_ult_usuario")
    @AtributoUltimoUsuario
    private String domUltUsuario;
    @Column(name = "dom_ult_mod")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @AtributoUltimaModificacion
    private Date domUltMod;
    @Column(name = "dom_ult_origen")
    private String domUltOrigen;
    @Column(name = "dom_version")
    @Version
    private Integer domVersion;
    @Transient
    private String formaCanonica1;
    @Transient
    private String formaCanonica3;
    @Transient
    private String formaCanonica;

    public Domicilio() {
    }

    // <editor-fold defaultstate="collapsed" desc="getter-setter">
    public Domicilio(Integer domId) {
        this.domId = domId;
    }

    public Integer getDomId() {
        return domId;
    }

    public void setDomId(Integer domId) {
        this.domId = domId;
    }

    public String getDomRuta() {
        return domRuta;
    }

    public void setDomRuta(String domRuta) {
        this.domRuta = domRuta;
    }

    public String getDomKilometro() {
        return domKilometro;
    }

    public void setDomKilometro(String domKilometro) {
        this.domKilometro = domKilometro;
    }

    public String getDomCalle() {
        return domCalle;
    }

    public void setDomCalle(String domCalle) {
        this.domCalle = domCalle;
    }

    public String getDomNumeroPuerta() {
        return domNumeroPuerta;
    }

    public void setDomNumeroPuerta(String domNumeroPuerta) {
        this.domNumeroPuerta = domNumeroPuerta;
    }

    public String getDomManzana() {
        return domManzana;
    }

    public void setDomManzana(String domManzana) {
        this.domManzana = domManzana;
    }

    public String getDomSolar() {
        return domSolar;
    }

    public void setDomSolar(String domSolar) {
        this.domSolar = domSolar;
    }

    public String getDomCodigoPostal() {
        return domCodigoPostal;
    }

    public void setDomCodigoPostal(String domCodigoPostal) {
        this.domCodigoPostal = domCodigoPostal;
    }

    public String getDomApto() {
        return domApto;
    }

    public void setDomApto(String domApto) {
        this.domApto = domApto;
    }

    public String getDomDescripcion() {
        return domDescripcion;
    }

    public void setDomDescripcion(String domDescripcion) {
        this.domDescripcion = domDescripcion;
    }

    public Localidad getDomLocId() {
        return domLocId;
    }

    public void setDomLocId(Localidad domLocId) {
        this.domLocId = domLocId;
    }

    public Pais getDomPais() {
        return domPais;
    }

    public void setDomPais(Pais domPais) {
        this.domPais = domPais;
    }

    public Departamento getDomDepto() {
        return domDepto;
    }

    public void setDomDepto(Departamento domDepto) {
        this.domDepto = domDepto;
    }

    public String getDomDeptoAlt() {
        return domDeptoAlt;
    }

    public void setDomDeptoAlt(String domDeptoAlt) {
        this.domDeptoAlt = domDeptoAlt;
    }

    public String getDomBis() {
        return domBis;
    }

    public void setDomBis(String domBis) {
        this.domBis = domBis;
    }

    public String getDomInmuebleNombre() {
        return domInmuebleNombre;
    }

    public void setDomInmuebleNombre(String domInmuebleNombre) {
        this.domInmuebleNombre = domInmuebleNombre;
    }

    public TipoEntradaColectiva getDomTipoEntradaColectiva() {
        return domTipoEntradaColectiva;
    }

    public void setDomTipoEntradaColectiva(TipoEntradaColectiva domTipoEntradaColectiva) {
        this.domTipoEntradaColectiva = domTipoEntradaColectiva;
    }

    public Paridad getDomParidad() {
        return domParidad;
    }

    public void setDomParidad(Paridad domParidad) {
        this.domParidad = domParidad;
    }

    public TipoVialidad getDomTipoVialidad() {
        return domTipoVialidad;
    }

    public void setDomTipoVialidad(TipoVialidad domTipoVialidad) {
        this.domTipoVialidad = domTipoVialidad;
    }

    public Integer getDomVersion() {
        return domVersion;
    }

    public void setDomVersion(Integer domVersion) {
        this.domVersion = domVersion;
    }

    public String getDomUltUsuario() {
        return domUltUsuario;
    }

    public void setDomUltUsuario(String domUltUsuario) {
        this.domUltUsuario = domUltUsuario;
    }

    public Date getDomUltMod() {
        return domUltMod;
    }

    public void setDomUltMod(Date domUltMod) {
        this.domUltMod = domUltMod;
    }

    public String getDomUltOrigen() {
        return domUltOrigen;
    }

    public void setDomUltOrigen(String domUltOrigen) {
        this.domUltOrigen = domUltOrigen;
    }
    // </editor-fold>

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (domId != null ? domId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Domicilio)) {
            return false;
        }
        Domicilio other = (Domicilio) object;
        if ((this.domId == null && other.domId != null) || (this.domId != null && !this.domId.equals(other.domId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.certpyme.entity.Domicilios[domId=" + domId + "]";
    }

    @PrePersist
    @PreUpdate
    public void preGrabar() {
        this.domUltMod = new Date();
       normalizar();
    }

    private void normalizar() {
         this.domApto=this.domApto!=null?StringsUtils.normalizarStringMayuscula(domApto):domApto;
        this.domBis=this.domBis!=null?StringsUtils.normalizarStringMayuscula(domBis):domBis;
        this.domCalle=this.domCalle!=null?StringsUtils.normalizarStringMayuscula(domCalle):domCalle;
        this.domCodigoPostal=this.domCodigoPostal!=null?StringsUtils.normalizarStringMayuscula(domCodigoPostal):domCodigoPostal;
        this.domDeptoAlt=this.domDeptoAlt!=null?StringsUtils.normalizarStringMayuscula(domDeptoAlt):domDeptoAlt;
        this.domDescripcion=this.domDescripcion!=null?StringsUtils.normalizarStringMayuscula(domDescripcion):domDescripcion;
        this.domInmuebleNombre=this.domInmuebleNombre!=null?StringsUtils.normalizarStringMayuscula(domInmuebleNombre):domInmuebleNombre;
        this.domKilometro=this.domKilometro!=null?StringsUtils.normalizarStringMayuscula(domKilometro):domKilometro;
        this.domManzana=this.domManzana!=null?StringsUtils.normalizarStringMayuscula(this.domManzana):domManzana;
        this.domNumeroPuerta=this.domNumeroPuerta!=null?StringsUtils.normalizarStringMayuscula(domNumeroPuerta):domNumeroPuerta;
        this.domOficina=this.domOficina!=null?StringsUtils.normalizarStringMayuscula(domOficina):domOficina;
        this.domRuta=this.domRuta!=null?StringsUtils.normalizarStringMayuscula(domRuta):domRuta;
        this.domSolar=this.domSolar!=null?StringsUtils.normalizarStringMayuscula(domSolar):domSolar;
    }
    public String getFormaCanonica1() {
 
//            if (ConstantesEstandares.CODIGO_RUTA.equals(domTipoVialidad.getTviCodigo())) {
//               fc="Es una ruta1";
//            } else {
//                fc="No es una ruta1";
//            }

        /*
         * 
         *   Formas canÃ³nicas 1 y 2 (PaÃ­s, Departamento, CÃ³digo Postal,
         *   Ruta, Nombre de vialidad o NÃºmero de ruta, KilÃ³metro, Paridad)
         * 
         *   RenglÃ³n 5: Ruta + Nombre de vialidad o NÃºmero de ruta +
         *   KilÃ³metro + Paridad + [DirecciÃ³n Interna]
         *   RenglÃ³n 6: [Observaciones]
         *   RenglÃ³n 7: [Barrio]
         *   RenglÃ³n 8: CÃ³digo Postal + Departamento
         *   RenglÃ³n 9: PaÃ­s
         * 
         *   RenglÃ³n 5 RUTA 26 KM 208.500 IZQ
         *   RenglÃ³n 6 A 23 KM DE LA CIUDAD DE TACUAREMBÃ“ RUMBO A
         *   PAYSANDÃš
         *   RenglÃ³n 7
         *   RenglÃ³n 8 45017 TACUAREMBÃ“
         *   RenglÃ³n 9 URUGUAY
         * 
         */
        normalizar();
        String fc = "";
        //renglÃ³n 5
        fc += this.domTipoVialidad != null ? this.domTipoVialidad.getTviCodigo() : "";
        fc += " ";
        fc += this.domRuta != null ? this.domRuta : this.domCalle != null ? this.domCalle : "";
        fc += " ";
        fc += this.domKilometro != null && "".equalsIgnoreCase(this.domKilometro) ? "KM " + this.domKilometro : "";
        fc += " ";
        fc += this.domParidad != null ? this.domParidad.getParCodigo() : "";
        fc += ConstantesEstandares.SEPARADOR;
         //renglÃ³n 6

        //renglÃ³n 7

        //renglÃ³n 8          
        fc += this.domCodigoPostal != null ? this.domCodigoPostal : "";
        fc += " ";
        fc += this.domLocId!=null ? " - "+this.domLocId.getLocNombre():"";
        fc +=" ";
        fc += this.domDepto != null ? ", "+this.domDepto.getDeptoNombre() : this.domDeptoAlt != null ? this.domDeptoAlt : "";
        fc += " ";
        fc += ConstantesEstandares.SEPARADOR;

        //renglÃ³n 9
        fc += this.domPais != null ? this.domPais.getPaiNombre() : "";
        //fc += " ";                   

        formaCanonica1 = fc;
        return formaCanonica1;
    }

    public void setFormaCanonica1(String formaCanonica1) {
        this.formaCanonica1 = formaCanonica1;
    }

    public String getFormaCanonica3() {
        
        String fc  ="";
        //rengÃ³n 5
        fc += this.domTipoVialidad != null ? this.domTipoVialidad.getTviCodigo() : "";
        fc += " ";
        fc += this.domCalle != null ? this.domCalle : "";
        fc += " ";
        fc += this.domNumeroPuerta != null ? this.domNumeroPuerta : this.domInmuebleNombre != null ? this.domInmuebleNombre : "";
        fc += this.domApto != null && "".equalsIgnoreCase(this.domApto) ? " APTO " + this.domApto : "";
        fc += ConstantesEstandares.SEPARADOR;

        //renglÃ³n 6

        //renglÃ³n 7

        //renglÃ³n 8
        fc += this.domCodigoPostal != null ? this.domCodigoPostal : "";
        fc += " ";
          fc += this.domDepto != null ? ", "+this.domDepto.getDeptoNombre() : this.domDeptoAlt != null ? this.domDeptoAlt : "";
        
        fc += " ";
        fc += this.domDepto != null ? this.domDepto.getDeptoNombre() : this.domDeptoAlt != null ? this.domDeptoAlt : "";
        fc += " ";
        fc += ConstantesEstandares.SEPARADOR;

        //renglÃ³n 8
        fc += this.domPais != null ? this.domPais.getPaiNombre() : "";
        fc += " ";


//            if (ConstantesEstandares.CODIGO_RUTA.equals(domTipoVialidad.getTviCodigo())) {
//                fc="Es una ruta3";
//                
//                fc = this.domLocId.getLocDeptoId().getDeptoNombre();
//            
//            
//            } else {
//                fc="No es una ruta3";
//                fc = this.domLocId.getLocDeptoId().getDeptoNombre();
//            }

        formaCanonica3 = fc;

        return formaCanonica3;
    }

    public void setFormaCanonica3(String formaCanonica3) {
        this.formaCanonica3 = formaCanonica3;
    }

    public String getDomOficina() {
        return domOficina;
    }

    public void setDomOficina(String domOficina) {
        this.domOficina = domOficina;
    }

    public String getFormaCanonica() {

        formaCanonica = "";
        if (domTipoVialidad != null && ConstantesEstandares.CODIGO_RUTA.equals(domTipoVialidad.getTviCodigo())) {
            formaCanonica = getFormaCanonica1();
        } else {
            formaCanonica = getFormaCanonica3();
        }
        return formaCanonica;

    }

    public void setFormaCanonica(String formaCanonica) {
        this.formaCanonica = formaCanonica;
    }
}
