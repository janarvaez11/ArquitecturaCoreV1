package com.banquito.core.clientes.modelo.enums;

public enum SegmentoClienteEnum {
    MASIVO("MASIVO"),
    PREFERENCIAL("PREFERENCIAL"),
    CORPORATIVO("CORPORATIVO"),
    EMPRESARIAL("EMPRESARIAL"),
    PYMES("PYMES"),
    MICROFINANZAS("MICROFINANZAS");

    private final String valor;

    SegmentoClienteEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
