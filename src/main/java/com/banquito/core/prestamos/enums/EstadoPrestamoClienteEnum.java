package com.banquito.core.prestamos.enums;

public enum EstadoPrestamoClienteEnum {
    ACTIVO("ACTIVO"),
    INACTIVO("INACTIVO"),
    SOLICITADO("SOLICITADO"),
    APROBADO("APROBADO"),
    DESEMBOLSADO("DESEMBOLSADO"),
    VIGENTE("VIGENTE"),
    EN_MORA("EN MORA"),
    REFINANCIADO("REFINANCIADO"),
    PAGADO("PAGADO"),
    CASTIGADO("CASTIGADO");

    private final String valor;

    EstadoPrestamoClienteEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
} 