package com.banquito.core.prestamos.enums;

public enum TipoSeguroEnum {
    VIDA("VIDA"),
    DESEMPLEO("DESEMPLEO"),
    PROTECCION_PAGOS("PROTECCION_PAGOS"),
    DESGRAVAMEN("DESGRAVAMEN"),
    INCENDIOS("INCENDIOS");

    private final String valor;

    TipoSeguroEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
} 