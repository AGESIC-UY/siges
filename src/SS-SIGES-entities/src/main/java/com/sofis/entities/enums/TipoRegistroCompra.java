package com.sofis.entities.enums;

/**
 * Enumerado de los tipos registros compra.
 *
 * @author Sofis
 */
public enum TipoRegistroCompra {

    COMPRA_ORIGINAL("Compra original"),
    IVA_COMPRA_ORIGINAL("IVA de compra original"),
    AJUSTE_COMPRA_ORIGINAL("Ajuste de compra original"),
    IVA_AJUSTE_COMPRA("IVA del ajuste de compra");

    public final String text;

    TipoRegistroCompra(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getText() {
        return text;
    }

}
