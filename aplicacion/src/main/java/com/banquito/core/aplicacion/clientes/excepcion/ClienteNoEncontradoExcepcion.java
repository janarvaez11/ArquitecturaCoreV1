package com.banquito.core.aplicacion.clientes.excepcion;

public class ClienteNoEncontradoExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 2003;

    public ClienteNoEncontradoExcepcion(Integer id) {
        super("No se encontró Cliente con ID: " + id);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Entidad: Cliente" + ", Mensaje: " + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}
