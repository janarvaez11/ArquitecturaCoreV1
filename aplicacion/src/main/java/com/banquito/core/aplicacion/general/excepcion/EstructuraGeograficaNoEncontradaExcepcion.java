package com.banquito.core.aplicacion.general.excepcion;

public class EstructuraGeograficaNoEncontradaExcepcion extends RuntimeException {
    private final Integer codigoError = 3001;

    public EstructuraGeograficaNoEncontradaExcepcion(String mensaje) {
        super(mensaje);
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}