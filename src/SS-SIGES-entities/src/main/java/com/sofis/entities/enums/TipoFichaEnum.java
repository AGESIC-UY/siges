package com.sofis.entities.enums;

/**
 *
 * @author Usuario
 */
public enum TipoFichaEnum {
    PROGRAMA(1),
    PROYECTO(2);
    
    public int id;
    
    TipoFichaEnum(int id){
        this.id = id;
    }
    
    public int getValue(){
        return this.id;
    }
    
    public boolean isPrograma(){
        return this.equals(TipoFichaEnum.PROGRAMA);
    }
    
    public boolean isProyecto(){
        return this.equals(TipoFichaEnum.PROYECTO);
    }
}