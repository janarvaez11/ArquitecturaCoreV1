package com.banquito.core.aplicacion.prestamos.excepcion;

public class EsquemaNoEncontradoExcepcion extends RuntimeException {

    private final String entidad;
    private final String mensaje;

    public EsquemaNoEncontradoExcepcion(String entidad, String mensaje) {
        super(mensaje);
        this.entidad = entidad;
        this.mensaje = mensaje;
    }

    public String getEntidad() {
        return entidad;
    }

    public String getMensaje() {
        return mensaje;
    }
}