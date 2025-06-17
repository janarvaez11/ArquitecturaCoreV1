package com.banquito.core.clientes.enums;

public enum EstadoCivilEnum {
    SOLTERO("SOLTERO"),
    CASADO("CASADO"),
    DIVORICIADO("DIVORICIADO"),
    VIUDO("VIUDO"),
    UNION_LIBRE("UNION LIBRE");

    private final String valor;

    EstadoCivilEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
