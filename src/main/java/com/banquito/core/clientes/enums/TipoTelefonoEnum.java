package com.banquito.core.clientes.enums;

public enum TipoTelefonoEnum {
    CELULAR("CELULAR"),
    RESIDENCIA("RESIDENCIA"),
    LABORAL("LABORAL");

    private final String valor;

    TipoTelefonoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
