package com.banquito.core.clientes.modelo.enums;

public enum EstadoContactoEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoContactoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
