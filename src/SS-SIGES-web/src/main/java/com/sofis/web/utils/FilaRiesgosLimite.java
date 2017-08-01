/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sofis.web.utils;

/**
 *
 * @author Usuario
 */
public class FilaRiesgosLimite {

    private Integer impacto;
    private Integer cant1;
    private Integer cant2;
    private Integer cant3;
    private Integer cant4;
    private Integer cant5;
    private Double val1;
    private Double val2;
    private Double val3;
    private Double val4;
    private Double val5;

    public FilaRiesgosLimite(Integer impacto, Integer cant1, Integer cant2, Integer cant3, Integer cant4, Integer cant5, Double val1, Double val2, Double val3, Double val4, Double val5) {
        this.impacto = impacto;
        this.cant1 = cant1;
        this.cant2 = cant2;
        this.cant3 = cant3;
        this.cant4 = cant4;
        this.cant5 = cant5;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.val4 = val4;
        this.val5 = val5;
    }

    public FilaRiesgosLimite(Integer impacto, Double val1, Double val2, Double val3, Double val4, Double val5) {
        this.impacto = impacto;
        this.val1 = val1;
        this.val2 = val2;
        this.val3 = val3;
        this.val4 = val4;
        this.val5 = val5;
    }

    public FilaRiesgosLimite(Integer impacto, Integer val1, Integer val2, Integer val3, Integer val4, Integer val5) {
        this.impacto = impacto;
        this.cant1 = val1;
        this.cant2 = val2;
        this.cant3 = val3;
        this.cant4 = val4;
        this.cant5 = val5;
    }

    public Double getVal1() {
        return val1;
    }

    public void setVal1(Double val1) {
        this.val1 = val1;
    }

    public Double getVal2() {
        return val2;
    }

    public void setVal2(Double val2) {
        this.val2 = val2;
    }

    public Integer getImpacto() {
        return impacto;
    }

    public void setImpacto(Integer impacto) {
        this.impacto = impacto;
    }

    public Double getVal3() {
        return val3;
    }

    public void setVal3(Double val3) {
        this.val3 = val3;
    }

    public Double getVal4() {
        return val4;
    }

    public void setVal4(Double val4) {
        this.val4 = val4;
    }

    public Double getVal5() {
        return val5;
    }

    public void setVal5(Double val5) {
        this.val5 = val5;
    }

    public Integer getCant1() {
        return cant1;
    }

    public void setCant1(Integer cant1) {
        this.cant1 = cant1;
    }

    public Integer getCant2() {
        return cant2;
    }

    public void setCant2(Integer cant2) {
        this.cant2 = cant2;
    }

    public Integer getCant3() {
        return cant3;
    }

    public void setCant3(Integer cant3) {
        this.cant3 = cant3;
    }

    public Integer getCant4() {
        return cant4;
    }

    public void setCant4(Integer cant4) {
        this.cant4 = cant4;
    }

    public Integer getCant5() {
        return cant5;
    }

    public void setCant5(Integer cant5) {
        this.cant5 = cant5;
    }
}
