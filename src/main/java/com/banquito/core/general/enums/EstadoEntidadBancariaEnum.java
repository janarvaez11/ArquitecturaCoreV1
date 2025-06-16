package com.banquito.core.general.enums;

public enum EstadoEntidadBancariaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    SUSPENDIDO("SUSPENDIDO");

    private final String valor;

    EstadoEntidadBancariaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
