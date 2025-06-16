package com.banquito.core.general.enums;

public enum EstadoFeriadoEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoFeriadoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
