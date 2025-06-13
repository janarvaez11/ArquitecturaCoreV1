package com.banquito.core.aplicacion.general.excepcion;

public class LocacionGeograficaNoEncontradaExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 3003;

    public LocacionGeograficaNoEncontradaExcepcion(String mensaje) {
        super(mensaje);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    public LocacionGeograficaNoEncontradaExcepcion(String mensaje, Integer codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Mensaje: " + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}