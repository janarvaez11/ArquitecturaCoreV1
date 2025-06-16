package com.banquito.core.clientes.enums;

public enum CanalAfiliacionEnum {
    PAGINA_WEB("PAGINA WEB"),
    AGENCIA("AGENCIA"),
    EXTERNO("EXTERNO"),
    CALL_CENTER("CALL CENTER");

    private final String valor;

    CanalAfiliacionEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
