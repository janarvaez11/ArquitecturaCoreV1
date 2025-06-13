package com.banquito.core.aplicacion.prestamos.excepcion;

public class BusquedaExcepcion extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    private final String entidad;
    private final String mensaje;

    public BusquedaExcepcion(String entidad, String mensaje) {
        super(mensaje);
        this.entidad = entidad;
        this.mensaje = mensaje;
    }

    public BusquedaExcepcion(String entidad, String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.entidad = entidad;
        this.mensaje = mensaje;
    }

    public String getEntidad() {
        return entidad;
    }

    @Override
    public String getMessage() {
        return String.format("Error al buscar en %s: %s", entidad, mensaje);
    }
} 