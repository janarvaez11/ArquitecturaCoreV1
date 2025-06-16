package com.banquito.core.general.enums;

public enum EstadoMonedaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    DESCONTINUADA("DESCONTINUADA");

    private final String valor;

    EstadoMonedaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
