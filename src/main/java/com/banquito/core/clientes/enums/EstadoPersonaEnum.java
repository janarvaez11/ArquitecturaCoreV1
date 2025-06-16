package com.banquito.core.clientes.enums;

public enum EstadoPersonaEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    SUSPENDIDO("SUSPENDIDO"),
    BLOQUEADO("BLOQUEADO"),
    PROSPECTO("PROSPECTO");

    private final String valor;

    EstadoPersonaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
