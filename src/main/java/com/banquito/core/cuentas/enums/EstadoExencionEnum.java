package com.banquito.core.cuentas.enums;

public enum EstadoExencionEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoExencionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
