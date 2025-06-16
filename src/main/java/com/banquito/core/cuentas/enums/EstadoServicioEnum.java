package com.banquito.core.cuentas.enums;

public enum EstadoServicioEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoServicioEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
