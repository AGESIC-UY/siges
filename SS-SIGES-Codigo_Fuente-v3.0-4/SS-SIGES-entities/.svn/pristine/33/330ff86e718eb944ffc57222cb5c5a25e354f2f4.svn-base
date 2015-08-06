package com.sofis.entities.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByProdPk", query = "SELECT p FROM Productos p WHERE p.prodPk = :prodPk"),
    @NamedQuery(name = "Productos.findByProdPeso", query = "SELECT p FROM Productos p WHERE p.prodPeso = :prodPeso"),
    @NamedQuery(name = "Productos.findByProdUndMedida", query = "SELECT p FROM Productos p WHERE p.prodUndMedida = :prodUndMedida")})
public class Productos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prod_pk")
    private Integer prodPk;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_peso")
    private int prodPeso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_und_medida")
    private String prodUndMedida;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodmesProdFk", fetch = FetchType.EAGER, orphanRemoval = true)
//    @Fetch(FetchMode.SELECT)
    private List<ProdMes> prodMesList;
    /**
     * Indica si los valores en cada mes son acumulados o independiente a cada
     * mes. Esto no debería usarse porque para ello tenemos un detalle de lo
     * acumulado, por ende los valores de los meses son individuales. Se
     * mantiene porque en Siges viejo se usa así y hay que tenerlo en cuenta en
     * la migración. Luego en el recalcular unificar los valores por mes.
     */
    @Column(name = "prod_acumulado")
    private Boolean prodAcumulado;
    @Column(name = "prod_desc")
    private String prodDesc;
    @JoinColumn(name = "prod_ent_fk", referencedColumnName = "ent_pk")
    @ManyToOne(fetch = FetchType.EAGER)
    private Entregables prodEntregableFk;
    @Column(name = "prod_fecha_crea")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date prodFechaCrea;
    @Column(name = "prod_ult_mod")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date prodUltMod;
    /**
     * Lista auxiliar con los meses del anio en el que se está posicionado en la
     * UI. Paginado de los ProdMes.
     */
    @Transient
    private List<ProdMes> prodMesAuxList;
    /**
     * Para saber en la UI en que año se está posicionado. Paginado de los
     * ProdMes.
     */
    @Transient
    private Integer anio;

    public Productos() {
    }

    public Productos(Integer prodPk) {
        this.prodPk = prodPk;
    }

    public Productos(Integer prodPk, int prodPeso, String prodUndMedida) {
        this.prodPk = prodPk;
        this.prodPeso = prodPeso;
        this.prodUndMedida = prodUndMedida;
    }

    public Integer getProdPk() {
        return prodPk;
    }

    public void setProdPk(Integer prodPk) {
        this.prodPk = prodPk;
    }

    public int getProdPeso() {
        return prodPeso;
    }

    public void setProdPeso(int prodPeso) {
        this.prodPeso = prodPeso;
    }

    public String getProdUndMedida() {
        return prodUndMedida;
    }

    public void setProdUndMedida(String prodUndMedida) {
        this.prodUndMedida = prodUndMedida;
    }

    public List<ProdMes> getProdMesList() {
        return prodMesList;
    }

    public void setProdMesList(List<ProdMes> prodMesList) {
        this.prodMesList = prodMesList;
    }

    public Boolean getProdAcumulado() {
        return prodAcumulado;
    }

    public void setProdAcumulado(Boolean prodAcumulado) {
        this.prodAcumulado = prodAcumulado;
    }

    public boolean isProdAcu() {
        return prodAcumulado != null ? prodAcumulado : false;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Entregables getProdEntregableFk() {
        return prodEntregableFk;
    }

    public void setProdEntregableFk(Entregables prodEntregableFk) {
        this.prodEntregableFk = prodEntregableFk;
    }

    public Date getProdFechaCrea() {
        return prodFechaCrea;
    }

    public void setProdFechaCrea(Date prodFechaCrea) {
        this.prodFechaCrea = prodFechaCrea;
    }

    public Date getProdUltMod() {
        return prodUltMod;
    }

    public void setProdUltMod(Date prodUltMod) {
        this.prodUltMod = prodUltMod;
    }

    public List<ProdMes> getProdMesAuxList() {
        return prodMesAuxList;
    }

    public void setProdMesAuxList(List<ProdMes> prodMesAuxList) {
        this.prodMesAuxList = prodMesAuxList;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getEntProdNombre() {
        String ent = prodEntregableFk != null ? prodEntregableFk.getEntNombre() : null;
        String prod = this.prodDesc != null ? this.prodDesc : "";
        return ent != null ? ent + " - " + prod : prod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prodPk != null ? prodPk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.prodPk == null && other.prodPk != null)
                || (this.prodPk != null && !this.prodPk.equals(other.prodPk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sofis.entities.data.Productos[ prodPk=" + prodPk + " ]";
    }

    public boolean contieneProdMesAnioMayor(Integer anio) {
        if (anio != null) {
            for (ProdMes prodMes : prodMesList) {
                if (anio < prodMes.getProdmesAnio()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean contieneProdMesAnioMenor(Integer anio) {
        if (anio != null) {
            for (ProdMes prodMes : prodMesList) {
                if (anio > prodMes.getProdmesAnio()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retorna el mes del producto según el año y mes aportado.
     *
     * @param anio
     * @param mes
     * @return ProdMes
     */
    public ProdMes obtenerProdMes(Integer anio, Integer mes) {
        if (anio != null && mes != null) {
            for (ProdMes prodMes : prodMesList) {
                if (anio == prodMes.getProdmesAnio()
                        && mes == prodMes.getProdmesMes()) {
                    return prodMes;
                }
            }
        }
        return null;
    }

    /**
     * Retorna el último mes del producto.
     *
     * @return ProdMes
     */
    public ProdMes ultimoProdMes() {
        if (prodMesList != null) {
            ProdMes mesResult = null;
            for (ProdMes prodMes : prodMesList) {
                if (mesResult == null
                        || (mesResult.getProdmesAnio() < prodMes.getProdmesAnio()
                        || (mesResult.getProdmesAnio() == prodMes.getProdmesAnio() && mesResult.getProdmesMes() < prodMes.getProdmesMes()))) {
                    mesResult = prodMes;
                }
            }
            return mesResult;
        }
        return null;
    }
}
