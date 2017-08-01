package com.sofis.entities.tipos;

import com.sofis.entities.constantes.ConstantesEstandares;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    private Double valorRealFinalizado = 0D;
    private Double valorRealParcial = 0D;
    /**
     * Proyectado es el valor real sin confirmar.
     */
    private Double valorProyectadoFinalizado = 0D;
    private Double valorProyectadoParcial = 0D;
    /**
     * Proyectado es el valor real sin confirmar y cuya fecha venció.
     */
    private Double valorProyectadoAtrasadoFinalizado = 0D;
    private Double valorProyectadoAtrasadoParcial = 0D;
    private Boolean proyectadoNegativoFinalizado;
    private Boolean proyectadoNegativoParcial;
    private String colorRealFinalizado = ConstantesEstandares.COLOR_TRANSPARENT;
    private String colorRealParcial = ConstantesEstandares.COLOR_TRANSPARENT;

    public ReporteAcumuladoMesTO() {
    }

    public ReporteAcumuladoMesTO(short anio, short mes) {
	this.anio = anio;
	this.mes = mes;
    }

//    public ReporteAcumuladoMesTO(short anio, short mes, Double valorPlan, Double valorRealFinalizado) {
//	this.anio = anio;
//	this.mes = mes;
//	this.valorPlan = valorPlan;
//	this.valorRealFinalizado = valorRealFinalizado;
//    }

    public ReporteAcumuladoMesTO(short anio, short mes, Integer objPk, Double valorPlan, Double valorRealFinalizado, String colorReal) {
	this.anio = anio;
	this.mes = mes;
	this.objPk = objPk;
	this.valorPlan = valorPlan;
	this.valorRealFinalizado = valorRealFinalizado;
	this.colorRealFinalizado = colorReal;
    }

    public ReporteAcumuladoMesTO(short anio, short mes, Integer objPk, Double valorPlan, Double valorRealFinalizado, Double valorProyectado, Double valorProyectadoAtrasado, Boolean proyectadoNegativo, String colorReal) {
	this.anio = anio;
	this.mes = mes;
	this.objPk = objPk;
	this.valorPlan = valorPlan;
	this.valorRealFinalizado = valorRealFinalizado;
	this.valorProyectadoFinalizado = valorProyectado;
	this.valorProyectadoAtrasadoFinalizado = valorProyectadoAtrasado;
	this.proyectadoNegativoFinalizado = proyectadoNegativo;
	this.colorRealFinalizado = colorReal;
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

    public Double getValorRealFinalizado() {
	return valorRealFinalizado;
    }

    public void setValorRealFinalizado(Double valorRealFinalizado) {
	this.valorRealFinalizado = valorRealFinalizado;
    }

    public Double getValorRealParcial() {
	return valorRealParcial;
    }

    public void setValorRealParcial(Double valorRealParcial) {
	this.valorRealParcial = valorRealParcial;
    }

    public Double getValorProyectadoFinalizado() {
	return valorProyectadoFinalizado;
    }

    public void setValorProyectadoFinalizado(Double valorProyectadoFinalizado) {
	this.valorProyectadoFinalizado = valorProyectadoFinalizado;
    }

    public Double getValorProyectadoParcial() {
	return valorProyectadoParcial;
    }

    public void setValorProyectadoParcial(Double valorProyectadoParcial) {
	this.valorProyectadoParcial = valorProyectadoParcial;
    }

    public Double getValorProyectadoAtrasadoFinalizado() {
	return valorProyectadoAtrasadoFinalizado;
    }

    public void setValorProyectadoAtrasadoFinalizado(Double valorProyectadoAtrasadoFinalizado) {
	this.valorProyectadoAtrasadoFinalizado = valorProyectadoAtrasadoFinalizado;
    }

    public Double getValorProyectadoAtrasadoParcial() {
	return valorProyectadoAtrasadoParcial;
    }

    public void setValorProyectadoAtrasadoParcial(Double valorProyectadoAtrasadoParcial) {
	this.valorProyectadoAtrasadoParcial = valorProyectadoAtrasadoParcial;
    }

    public Boolean getProyectadoNegativoFinalizado() {
	return proyectadoNegativoFinalizado;
    }

    public void setProyectadoNegativoFinalizado(Boolean proyectadoNegativoFinalizado) {
	this.proyectadoNegativoFinalizado = proyectadoNegativoFinalizado;
    }

    public Boolean getProyectadoNegativoParcial() {
	return proyectadoNegativoParcial;
    }

    public void setProyectadoNegativoParcial(Boolean proyectadoNegativoParcial) {
	this.proyectadoNegativoParcial = proyectadoNegativoParcial;
    }

    public String getColorRealFinalizado() {
	return colorRealFinalizado;
    }

    public void setColorRealFinalizado(String colorRealFinalizado) {
	this.colorRealFinalizado = colorRealFinalizado;
    }

    public String getColorRealParcial() {
	return colorRealParcial;
    }

    public void setColorRealParcial(String colorRealParcial) {
	this.colorRealParcial = colorRealParcial;
    }

    public Double getValorProyectadoTotalFinalizado() {
	double vPr = valorProyectadoFinalizado != null ? valorProyectadoFinalizado : 0;
	double vPrAt = valorProyectadoAtrasadoFinalizado != null ? valorProyectadoAtrasadoFinalizado : 0;
	return vPr + vPrAt;
    }

    public Double getValorProyectadoTotalParcial() {
	double vPr = valorProyectadoParcial != null ? valorProyectadoParcial : 0;
	double vPrAt = valorProyectadoAtrasadoParcial != null ? valorProyectadoAtrasadoParcial : 0;
	return vPr + vPrAt;
    }

    public boolean isProyectadoAtrasadoFinalizado() {
	return isProyectadoAtrasado(0);
    }
    public boolean isProyectadoAtrasadoParcial() {
	return isProyectadoAtrasado(1);
    }

    /**
     *
     * @param tipoProyectado 0-Finalizado, 1-Parcial
     * @return
     */
    public boolean isProyectadoAtrasado(int tipoProyectado) {
	GregorianCalendar calNow = new GregorianCalendar();
	if (this.anio > calNow.get(Calendar.YEAR)
		|| (this.anio == calNow.get(Calendar.YEAR) && (this.mes - 1) > calNow.get(Calendar.MONTH))) {
	    return false;
	}

	if (tipoProyectado == 1) {
	    return proyectadoNegativoParcial != null ? proyectadoNegativoParcial : (valorProyectadoAtrasadoParcial != null && valorProyectadoAtrasadoParcial > 0);
	} else {
	    return proyectadoNegativoFinalizado != null ? proyectadoNegativoFinalizado : (valorProyectadoAtrasadoFinalizado != null && valorProyectadoAtrasadoFinalizado > 0);
	}
    }
}
