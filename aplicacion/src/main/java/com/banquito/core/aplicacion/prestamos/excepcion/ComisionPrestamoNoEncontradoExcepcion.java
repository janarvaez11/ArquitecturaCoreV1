package com.banquito.core.aplicacion.prestamos.excepcion;

public class ComisionPrestamoNoEncontradoExcepcion extends RuntimeException {

    private Integer errorCode;
    private String entityName;

    public ComisionPrestamoNoEncontradoExcepcion(String entityName, String message){
        super(message);
        this.errorCode = 2;
        this.entityName = entityName;
    }

    @Override
    public String getMessage() {
        return "errorCode=" + errorCode + ", entityName=" + entityName + ", message=" + super.getMessage();
    }

    

}
