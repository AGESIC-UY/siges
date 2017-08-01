package com.sofis.entities.enums;

/**
 *
 * @author USUARIO
 */

public enum TipoCategoria {

    TEMA_PRIMARIA(1),
    CATEGORIA_SECUNDARIA(2);

    public Integer cod;

    TipoCategoria(Integer cod) {
        this.cod = cod;
    }

    public Integer getCod() {
        return cod;
    }
}