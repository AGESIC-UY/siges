package com.sofis.entities.enums;

/**
 *
 * @author Usuario
 */
public enum EstadoMediaProyectosEnum {

    PENDIENTE_REVISION(1),
    PUBLICADO(2),
    RECHAZADO(3);

    public int cod;

    EstadoMediaProyectosEnum(int cod) {
        this.cod = cod;
    }
    
    public int getCod(){
        return this.cod;
    }
}
