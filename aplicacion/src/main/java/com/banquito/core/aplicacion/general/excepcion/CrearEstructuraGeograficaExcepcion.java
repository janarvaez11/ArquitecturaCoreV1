package com.banquito.core.aplicacion.general.excepcion;

public class CrearEstructuraGeograficaExcepcion extends RuntimeException {

    private final Integer codigoError;
    private static final Integer CODIGO_ERROR_DEFAULT = 3001;

    public CrearEstructuraGeograficaExcepcion(String nombreEntidad, String mensaje) {
        super(mensaje);
        this.codigoError = CODIGO_ERROR_DEFAULT;
    }

    public CrearEstructuraGeograficaExcepcion(String nombreEntidad, String mensaje, Integer codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
    }

    @Override
    public String getMessage() {
        return "Código de error: " + this.codigoError + ", Entidad: EstructuraGeografica" + ", Mensaje: "
                + super.getMessage();
    }

    public Integer getCodigoError() {
        return codigoError;
    }
}
