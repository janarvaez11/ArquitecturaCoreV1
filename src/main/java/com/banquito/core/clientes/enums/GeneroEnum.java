package com.banquito.core.clientes.enums;

public enum GeneroEnum {
    MASCULINO("MASCULINO"),
    FEMENINO("FEMENINO"),
    OTROS("OTROS");

    private final String valor;

    GeneroEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
