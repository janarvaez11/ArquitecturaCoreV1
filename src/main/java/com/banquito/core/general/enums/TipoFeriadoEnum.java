package com.banquito.core.general.enums;

public enum TipoFeriadoEnum {
    NACIONAL("NACIONAL"),
    REGIONAL("REGIONAL"),
    LOCAL("LOCAL");

    private final String valor;

    TipoFeriadoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
