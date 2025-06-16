package com.banquito.core.cuentas.enums;

public enum EstadoComisionCargoEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoComisionCargoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
