package com.banquito.core.clientes.modelo.enums;

public enum EstadoRepresentanteEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoRepresentanteEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
