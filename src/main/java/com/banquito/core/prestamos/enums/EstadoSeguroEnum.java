package com.banquito.core.prestamos.enums;

public enum EstadoSeguroEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    VENCIDO("VENCIDO"),
    CANCELADO("CANCELADO");

    private final String valor;

    EstadoSeguroEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
} 