package com.banquito.core.general.enums;

public enum EstadoMonedasEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoMonedasEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
