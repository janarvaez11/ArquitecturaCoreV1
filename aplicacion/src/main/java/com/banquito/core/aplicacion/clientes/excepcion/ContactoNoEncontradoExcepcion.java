package com.banquito.core.aplicacion.clientes.excepcion;

public class ContactoNoEncontradoExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 2303;

    public ContactoNoEncontradoExcepcion(Integer id) {
        super("No se encontró Contacto Transaccional con ID de Cliente: " + id);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Entidad: ContactoTransaccionCliente" + ", Mensaje: " + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}
