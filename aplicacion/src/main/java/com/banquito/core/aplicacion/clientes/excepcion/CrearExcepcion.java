package com.banquito.core.aplicacion.clientes.excepcion;

public class CrearExcepcion extends RuntimeException {
    private final String dato;
    private final String entidad;

    public CrearExcepcion(String dato, String entidad) {
        this.dato = dato;
        this.entidad = entidad;
    }

    @Override
    public String getMessage() {
        return "Error al crear la entidad" + entidad+ "con el dato " + dato;
    }

}
