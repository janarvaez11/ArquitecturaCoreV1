package com.banquito.core.cuentas.enums;

public enum EstadoTasaInteresEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoTasaInteresEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
