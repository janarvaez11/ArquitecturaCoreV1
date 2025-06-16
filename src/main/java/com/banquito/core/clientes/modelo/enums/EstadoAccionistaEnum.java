package com.banquito.core.clientes.modelo.enums;

public enum EstadoAccionistaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoAccionistaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
