package com.banquito.core.prestamos.enums;

public enum BaseCalculoEnum {
    BASE_30_360("30/360"),
    BASE_31_365("31/365");

    private final String valor;

    BaseCalculoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
} 