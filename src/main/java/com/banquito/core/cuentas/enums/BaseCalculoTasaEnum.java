package com.banquito.core.cuentas.enums;

public enum BaseCalculoTasaEnum {
    _30_360("30/360"),
    _31_365("31/365");

    private final String valor;

    BaseCalculoTasaEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}