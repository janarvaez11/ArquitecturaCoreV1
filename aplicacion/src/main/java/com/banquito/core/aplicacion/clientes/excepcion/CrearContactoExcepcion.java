package com.banquito.core.aplicacion.clientes.excepcion;

public class CrearContactoExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 2301;

    public CrearContactoExcepcion(String mensaje) {
        super(mensaje);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    public CrearContactoExcepcion(String mensaje, Integer codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Entidad: ContactoTransaccionCliente" + ", Mensaje: " + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}
