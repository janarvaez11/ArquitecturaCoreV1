package com.banquito.core.cuentas.enums;

public enum TipoComisionCargoEnum {
    TRANSACCION("TRANSACCION"),
    SERVICIO("SERVICIO"),
    PERIODICO("PERIODICO");

    private final String valor;

    TipoComisionCargoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
