package com.banquito.core.clientes.enums;

public enum TipoClienteEnum {
    PERSONA_NATURAL("PERSONA NATURAL"),
    PERSONA_JURIDICA("PERSONA JURIDICA");

    private final String valor;

    TipoClienteEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
