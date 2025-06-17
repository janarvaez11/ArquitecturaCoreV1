package com.banquito.core.clientes.enums;

public enum NivelEstudioEnum {
    PRIMARIA("PRIMARIA"),
    SECUNDARIA("SECUNDARIA"),
    UNIVERSITARIA("UNIVERSITARIA"),
    POSGRADO("POSGRADO"),
    DOCTORADO("DOCTORADO");

    private final String valor;

    NivelEstudioEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
