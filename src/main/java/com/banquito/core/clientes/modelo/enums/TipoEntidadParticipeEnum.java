package com.banquito.core.clientes.modelo.enums;

public enum TipoEntidadParticipeEnum {
    PERSONA("PERSONA"),
    EMPRESA("EMPRESA");

    private final String valor;

    TipoEntidadParticipeEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
