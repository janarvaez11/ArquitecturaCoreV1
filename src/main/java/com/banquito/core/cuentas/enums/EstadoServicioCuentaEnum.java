package com.banquito.core.cuentas.enums;

public enum EstadoServicioCuentaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoServicioCuentaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
