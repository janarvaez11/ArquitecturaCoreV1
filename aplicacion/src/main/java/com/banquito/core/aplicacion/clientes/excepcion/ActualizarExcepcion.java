package com.banquito.core.aplicacion.clientes.excepcion;

public class ActualizarExcepcion extends RuntimeException {
    private final String dato;
    private final String entidad;

    public ActualizarExcepcion(String dato, String entidad) {
        this.dato = dato;
        this.entidad = entidad;
    }

    @Override
    public String getMessage() {
        return "Error al actualizar la entidad" + entidad+ "con el dato " + dato;
    }
}
