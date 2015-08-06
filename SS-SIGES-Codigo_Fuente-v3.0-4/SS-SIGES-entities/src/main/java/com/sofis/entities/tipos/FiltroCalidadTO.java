package com.sofis.entities.tipos;

/**
 *
 * @author Usuario
 */
public class FiltroCalidadTO {

    private Integer proyPk;
    private Integer tipo;
    private Integer valor;
    private String codValorCalidad;
    private Integer temaCalidadPk;
    private Integer EntPk;
    private Integer ProdPk;
    private String[] orderBy;
    private boolean[] asc;

    public FiltroCalidadTO() {
    }

    public Integer getProyPk() {
        return proyPk;
    }

    public void setProyPk(Integer proyPk) {
        this.proyPk = proyPk;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public String getCodValorCalidad() {
        return codValorCalidad;
    }

    public void setCodValorCalidad(String codValorCalidad) {
        this.codValorCalidad = codValorCalidad;
    }

    public Integer getTemaCalidadPk() {
        return temaCalidadPk;
    }

    public void setTemaCalidadPk(Integer temaCalidadPk) {
        this.temaCalidadPk = temaCalidadPk;
    }

    public Integer getEntPk() {
        return EntPk;
    }

    public void setEntPk(Integer EntPk) {
        this.EntPk = EntPk;
    }

    public Integer getProdPk() {
        return ProdPk;
    }

    public void setProdPk(Integer ProdPk) {
        this.ProdPk = ProdPk;
    }

    public String[] getOrderBy() {
        return orderBy != null ? orderBy : new String[]{};
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }

    public boolean[] getAsc() {
        return asc != null ? asc : new boolean[]{};
    }

    public void setAsc(boolean[] asc) {
        this.asc = asc;
    }
}
