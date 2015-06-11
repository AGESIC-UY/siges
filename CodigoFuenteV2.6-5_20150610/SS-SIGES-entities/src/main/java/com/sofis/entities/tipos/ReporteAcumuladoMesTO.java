package com.sofis.entities.tipos;

import com.sofis.entities.constantes.ConstantesEstandares;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class ReporteAcumuladoMesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private short anio;
    private short mes;
    private Integer objPk;
    private Double valorPlan = 0D;
    private Double valorReal = 0D;
    private Double valorProyectado = 0D;
    private boolean proyectadoNegativo;
    private String colorReal = ConstantesEstandares.COLOR_TRANSPARENT;

    public ReporteAcumuladoMesTO() {
    }

    public ReporteAcumuladoMesTO(short anio, short mes) {
        this.anio = anio;
        this.mes = mes;
    }

    public ReporteAcumuladoMesTO(short anio, short mes, Double valorPlan, Double valorReal) {
        this.anio = anio;
        this.mes = mes;
        this.valorPlan = valorPlan;
        this.valorReal = valorReal;
    }

    public ReporteAcumuladoMesTO(short anio, short mes, Double valorPlan, Double valorReal, String colorReal) {
        this.anio = anio;
        this.mes = mes;
        this.valorPlan = valorPlan;
        this.valorReal = valorReal;
        this.colorReal = colorReal;
    }

    public ReporteAcumuladoMesTO(short anio, short mes, Integer objPk, Double valorPlan, Double valorReal, String colorReal) {
        this.anio = anio;
        this.mes = mes;
        this.objPk = objPk;
        this.valorPlan = valorPlan;
        this.valorReal = valorReal;
        this.colorReal = colorReal;
    }

    public ReporteAcumuladoMesTO(short anio, short mes, Integer objPk, Double valorPlan, Double valorReal, Double valorProyectado, boolean proyectadoNegativo, String colorReal) {
        this.anio = anio;
        this.mes = mes;
        this.objPk = objPk;
        this.valorPlan = valorPlan;
        this.valorReal = valorReal;
        this.valorProyectado = valorProyectado;
        this.proyectadoNegativo = proyectadoNegativo;
        this.colorReal = colorReal;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public short getMes() {
        return mes;
    }

    public void setMes(short mes) {
        this.mes = mes;
    }

    public Integer getObjPk() {
        return objPk;
    }

    public void setObjPk(Integer objPk) {
        this.objPk = objPk;
    }

    public Double getValorPlan() {
        return valorPlan;
    }

    public void setValorPlan(Double valorPlan) {
        this.valorPlan = valorPlan;
    }

    public Double getValorReal() {
        return valorReal;
    }

    public void setValorReal(Double valorReal) {
        this.valorReal = valorReal;
    }

    public Double getValorProyectado() {
        return valorProyectado;
    }

    public void setValorProyectado(Double valorProyectado) {
        this.valorProyectado = valorProyectado;
    }

    public boolean isProyectadoNegativo() {
        return proyectadoNegativo;
    }

    public void setProyectadoNegativo(boolean proyectadoNegativo) {
        this.proyectadoNegativo = proyectadoNegativo;
    }

    public String getColorReal() {
        return colorReal;
    }

    public void setColorReal(String colorReal) {
        this.colorReal = colorReal;
    }
}
