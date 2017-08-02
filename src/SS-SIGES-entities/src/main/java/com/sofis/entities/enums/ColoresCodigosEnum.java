package com.sofis.entities.enums;

/**
 * Enumerado de los colores usados en graficas o indicadores.
 *
 * @author Usuario
 */
public enum ColoresCodigosEnum {

    VERDE(1),
    AMARILLO_MAS(2),
    AMARILLO_MENOS(3),
    ROJO(4),
    NARANJA(5),
    AMARILLO(6),
    AZUL(7),
    GRIS(8);

    public int id;

    ColoresCodigosEnum(int id) {
        this.id = id;
    }
}
