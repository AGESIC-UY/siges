package com.sofis.entities.enums;

public enum EstadoRiesgo {

    SUPERADO(1),
    ACTIVO(2),
    ATRASADO(3);

    public int estado_id;

    EstadoRiesgo(int estado) {
        this.estado_id = estado;
    }
}
