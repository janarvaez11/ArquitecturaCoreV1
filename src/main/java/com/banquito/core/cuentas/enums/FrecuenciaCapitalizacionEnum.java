package com.banquito.core.cuentas.enums;

public enum FrecuenciaCapitalizacionEnum {
    DIARIA("DIARIA"),
    MENSUAL("MENSUAL"),
    SEMESTRAL("SEMESTRAL"),
    ANUAL("ANUAL");

    private final String valor;

    FrecuenciaCapitalizacionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
