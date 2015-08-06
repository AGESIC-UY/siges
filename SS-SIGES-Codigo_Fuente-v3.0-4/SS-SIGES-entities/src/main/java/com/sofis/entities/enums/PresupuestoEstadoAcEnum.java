package com.sofis.entities.enums;

/**
 *
 * @author Usuario
 */
public enum PresupuestoEstadoAcEnum {

    VERDE(1),
    AMARILLO_MAS(2),
    AMARILLO_MENOS(3),
    ROJO(4),
    NARANJA(5);
    
    public int id;
    
    PresupuestoEstadoAcEnum(int id){
        this.id = id;
    }
}
