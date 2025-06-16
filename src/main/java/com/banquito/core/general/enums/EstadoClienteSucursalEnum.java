package com.banquito.core.general.enums;

public enum EstadoClienteSucursalEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    SUSPENDIDO("SUSPENDIDO");

    private final String valor;

    EstadoClienteSucursalEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
