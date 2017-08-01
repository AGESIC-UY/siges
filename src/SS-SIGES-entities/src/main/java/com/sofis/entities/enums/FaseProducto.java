package com.sofis.entities.enums;

/**
 *
 * @author Usuario
 */
public enum FaseProducto {

    PLANIFICADO(1),
    REAL(2);
    
    public int id;
    
    FaseProducto(int id){
        this.id = id;
    }
    
    public int getValue(){
        return this.id;
    }
    
    public boolean isPlanificado(){
        return this.equals(FaseProducto.PLANIFICADO);
    }
    
    public boolean isReal(){
        return this.equals(FaseProducto.REAL);
    }
}
