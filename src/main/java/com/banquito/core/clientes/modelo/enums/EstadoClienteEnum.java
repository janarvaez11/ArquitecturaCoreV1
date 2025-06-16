package com.banquito.core.clientes.modelo.enums;

public enum EstadoClienteEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    SUSPENDIDO("SUSPENDIDO"),
    BLOQUEADO("BLOQUEADO");

    private final String valor;

    EstadoClienteEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
