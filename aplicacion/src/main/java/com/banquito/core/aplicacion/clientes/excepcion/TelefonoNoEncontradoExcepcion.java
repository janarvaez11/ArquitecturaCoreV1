package com.banquito.core.aplicacion.clientes.excepcion;

public class TelefonoNoEncontradoExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 2103;

    public TelefonoNoEncontradoExcepcion(Integer id) {
        super("No se encontró Teléfono con ID: " + id);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Entidad: TelefonoCliente" + ", Mensaje: " + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}
