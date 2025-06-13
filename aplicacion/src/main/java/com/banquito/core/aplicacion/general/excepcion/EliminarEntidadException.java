package com.banquito.core.aplicacion.general.excepcion;

public class EliminarEntidadException extends RuntimeException{
    private Integer errorCode;
    private String entityName;

    public EliminarEntidadException(String entityName, String message){
        super(message);
        this.errorCode = 2;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        return "errorCode=" + errorCode + ", entityName=" + entityName + ", message=" + super.getMessage();
    }
}
