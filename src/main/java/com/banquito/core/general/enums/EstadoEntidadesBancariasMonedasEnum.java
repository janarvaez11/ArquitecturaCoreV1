package com.banquito.core.general.enums;

public enum EstadoEntidadesBancariasMonedasEnum {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoEntidadesBancariasMonedasEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
