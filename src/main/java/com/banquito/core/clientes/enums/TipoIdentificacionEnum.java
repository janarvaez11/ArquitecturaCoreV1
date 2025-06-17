package com.banquito.core.clientes.enums;

public enum TipoIdentificacionEnum {
    CEDULA("CEDULA"),
    PASAPORTE("PASAPORTE"),
    RUC("RUC");

    private final String valor;

    TipoIdentificacionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
