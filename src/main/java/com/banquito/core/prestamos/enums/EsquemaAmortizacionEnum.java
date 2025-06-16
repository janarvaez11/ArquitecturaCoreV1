package com.banquito.core.prestamos.enums;

public enum NombreEsquemaAmortizacionEnum {
    FRANCES("FRANCES"),
    AMERICANO("AMERICANO"),
    ALEMAN("ALEMAN");

    private final String valor;

    NombreEsquemaAmortizacionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
} 