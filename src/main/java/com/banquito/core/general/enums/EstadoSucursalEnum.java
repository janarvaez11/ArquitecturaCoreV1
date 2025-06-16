package com.banquito.core.general.enums;

public enum EstadoSucursalEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoSucursalEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
