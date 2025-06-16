package com.banquito.core.clientes.modelo.enums;

public enum EstadoTelefonoEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO");

    private final String valor;

    EstadoTelefonoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
