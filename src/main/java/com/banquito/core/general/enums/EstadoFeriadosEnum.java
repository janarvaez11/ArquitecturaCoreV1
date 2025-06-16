package com.banquito.core.general.enums;

public enum EstadoFeriadosEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoFeriadosEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
