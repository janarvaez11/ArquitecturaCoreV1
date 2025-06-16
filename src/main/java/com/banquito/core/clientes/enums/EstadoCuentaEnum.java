package com.banquito.core.clientes.enums;

public enum EstadoCuentaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    SUSPENDIDO("SUSPENDIDO"),
    BLOQUEADO("BLOQUEADO");

    private final String valor;

    EstadoCuentaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
