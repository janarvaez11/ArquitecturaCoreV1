package com.banquito.core.aplicacion.prestamos.excepcion;

public class PrestamoComisionCargoExepcion extends RuntimeException {

    private Integer errorCode;
    private String entityName;

    public PrestamoComisionCargoExepcion(String entityName, String message){
        super(message);
        this.errorCode = 1;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        return "errorCode=" + errorCode + ", entityName=" + entityName + ", message=" + super.getMessage();
    }
}
