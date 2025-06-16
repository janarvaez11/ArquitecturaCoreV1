package com.banquito.core.clientes.modelo.enums;

public enum EstadoDireccionEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoDireccionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
