package com.banquito.core.general.enums;

public enum EstadoPaisesEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoPaisesEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
