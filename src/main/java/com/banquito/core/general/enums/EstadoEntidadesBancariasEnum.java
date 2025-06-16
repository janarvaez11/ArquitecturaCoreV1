package com.banquito.core.general.enums;

public enum EstadoEntidadesBancariasEnum {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoEntidadesBancariasEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
