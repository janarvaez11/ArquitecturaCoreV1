package com.banquito.core.prestamos.enums;

public enum TipoCalculoComisionEnum {
    PORCENTAJE("PORCENTAJE"),
    FIJO("FIJO");

    private final String valor;

    TipoCalculoComisionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
} 