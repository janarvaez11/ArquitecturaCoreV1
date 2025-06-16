package com.banquito.core.clientes.enums;

public enum TipoDireccionEnum {
    DOMICILIO("DOMICILIO"),
    LABORAL("LABORAL");

    private final String valor;

    TipoDireccionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
