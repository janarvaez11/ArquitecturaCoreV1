package com.banquito.core.general.enums;

public enum EstadoSucursalesEnum {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    CONSTRUCCION("CONSTRUCCION"),
    REMODELACION("REMODELACION");

    private final String valor;

    EstadoSucursalesEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
