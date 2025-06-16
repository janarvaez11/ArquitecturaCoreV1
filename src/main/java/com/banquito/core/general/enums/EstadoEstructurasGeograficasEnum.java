package com.banquito.core.general.enums;

public enum EstadoEstructurasGeograficasEnum {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoEstructurasGeograficasEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
