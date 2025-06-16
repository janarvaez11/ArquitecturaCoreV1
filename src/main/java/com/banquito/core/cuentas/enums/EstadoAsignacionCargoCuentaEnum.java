package com.banquito.core.cuentas.enums;

public enum EstadoAsignacionCargoCuentaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoAsignacionCargoCuentaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
