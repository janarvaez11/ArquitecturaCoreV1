package com.banquito.core.clientes.enums;

public enum RolRepresentanteEnum {
    REPRESENTANTE_LEGAL("REPRESENTANTE LEGAL"),
    FIRMA_AUTORIZADA("FIRMA AUTORIZADA"),
    ADMINISTRADOR("ADMINISTRADOR"),
    OPERADOR("OPERADOR");

    private final String valor;

    RolRepresentanteEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
