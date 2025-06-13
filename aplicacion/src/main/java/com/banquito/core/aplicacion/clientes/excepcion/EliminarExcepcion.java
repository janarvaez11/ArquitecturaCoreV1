package com.banquito.core.aplicacion.clientes.excepcion;

public class EliminarExcepcion extends RuntimeException {
    private final String dato;
    private final String entidad;

    public EliminarExcepcion(String dato, String entidad) {
        this.dato = dato;
        this.entidad = entidad;
    }

    @Override
    public String getMessage() {
        return "Error al eliminar la entidad" + entidad+ "con el dato " + dato;
    }
}
