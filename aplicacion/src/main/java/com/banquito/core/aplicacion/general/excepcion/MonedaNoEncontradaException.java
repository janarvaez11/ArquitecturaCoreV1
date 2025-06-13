package com.banquito.core.aplicacion.general.excepcion;

public class MonedaNoEncontradaException extends RuntimeException{

    private Integer errorCode;

    public MonedaNoEncontradaException(String message) {
        super(message);
        this.errorCode = 1;
    }

    @Override
    public String getMessage() {
        return "Error Code: " + this.errorCode + "message: " + super.getMessage();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}