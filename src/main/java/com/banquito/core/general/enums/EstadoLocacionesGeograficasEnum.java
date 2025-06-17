package com.banquito.core.general.enums;

public enum EstadoLocacionesGeograficasEnum {

    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    MANTENIMIENTO("MANTENIMIENTO");

    private final String valor;

    EstadoLocacionesGeograficasEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }


}
