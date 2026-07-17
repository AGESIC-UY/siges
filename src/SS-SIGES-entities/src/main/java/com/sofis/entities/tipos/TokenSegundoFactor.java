package com.sofis.entities.tipos;

import java.io.Serializable;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;



public class TokenSegundoFactor implements Serializable {

    String codigo;
    LocalDateTime fechaGenerado;
    LocalDateTime fechaVencimiento;
    LocalDateTime fechaConfirmado;
    int intentosFallidos = 0;

    public TokenSegundoFactor(String codigo, LocalDateTime fechaGenerado, LocalDateTime fechaVencimiento) {
        this.codigo = codigo;
        this.fechaGenerado = fechaGenerado;
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getCodigo() {
        return codigo;
    }
    
    public int getTiempoVencimiento() {
        
        return Seconds.secondsBetween(LocalDateTime.now(), this.getFechaVencimiento()).getSeconds();
        
    }

    public void setFechaConfirmado(LocalDateTime fechaConfirmado) {
        this.fechaConfirmado = fechaConfirmado;
    }

    public LocalDateTime getFechaGenerado() {
        return fechaGenerado;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public LocalDateTime getFechaConfirmado() {
        return fechaConfirmado;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public void marcarUsado() {
        this.fechaConfirmado = LocalDateTime.now();
    }

    public int incrementarIntentosFallidos() {
        this.intentosFallidos = this.intentosFallidos + 1;
        return this.intentosFallidos;
    }


}
