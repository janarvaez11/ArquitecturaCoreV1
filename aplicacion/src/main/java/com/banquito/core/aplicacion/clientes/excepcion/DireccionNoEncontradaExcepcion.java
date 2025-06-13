package com.banquito.core.aplicacion.clientes.excepcion;

public class DireccionNoEncontradaExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 2203;

    public DireccionNoEncontradaExcepcion(Integer id) {
        super("No se encontró Dirección con ID: " + id);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Entidad: DireccionCliente" + ", Mensaje: " + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}
