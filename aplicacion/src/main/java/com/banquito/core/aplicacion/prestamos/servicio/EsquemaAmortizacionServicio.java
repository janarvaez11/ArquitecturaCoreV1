package com.banquito.core.aplicacion.prestamos.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.banquito.core.aplicacion.prestamos.repositorio.*;
import com.banquito.core.aplicacion.prestamos.modelo.*;
import com.banquito.core.aplicacion.prestamos.excepcion.*;

@Service
public class EsquemaAmortizacionServicio {

    private final EsquemaAmortizacionRepositorio esquemaAmortizacionRepositorio;
    private final TipoPrestamoRepositorio tipoPrestamoRepositorio;

    public EsquemaAmortizacionServicio(EsquemaAmortizacionRepositorio esquemaAmortizacionRepositorio,
            TipoPrestamoRepositorio tipoPrestamoRepositorio) {
        this.esquemaAmortizacionRepositorio = esquemaAmortizacionRepositorio;
        this.tipoPrestamoRepositorio = tipoPrestamoRepositorio;
    }

    public void crearEsquemaAmortizacion(EsquemasAmortizacion esquemaAmortizacion) {
        try {
            validarEsquemaAmortizacion(esquemaAmortizacion);
            if (esquemaAmortizacion.getTipoPrestamo() != null
                    && esquemaAmortizacion.getTipoPrestamo().getIdTipoPrestamo() != null) {
                Optional<TipoPrestamo> tipoPrestamoOpt = this.tipoPrestamoRepositorio.findById(
                        esquemaAmortizacion.getTipoPrestamo().getIdTipoPrestamo());
                if (!tipoPrestamoOpt.isPresent()) {
                    throw new TipoPrestamoNoEncontradoExcepcion("Tipo Prestamo",
                            "El tipo de préstamo especificado no existe");
                }
            }
            this.esquemaAmortizacionRepositorio.save(esquemaAmortizacion);
        } catch (Exception e) {
            throw new CrearEntidadExcepcion("EsquemaAmortizacion",
                    "Error al crear el esquema de amortización: " + e.getMessage());
        }
    }

    public void actualizarEsquemaAmortizacion(EsquemasAmortizacion esquemaAmortizacion) {
        try {
            validarEsquemaAmortizacion(esquemaAmortizacion);
            Optional<EsquemasAmortizacion> esquemaAmortizacionOpcional = this.esquemaAmortizacionRepositorio
                    .findById(esquemaAmortizacion.getId());

            if (esquemaAmortizacionOpcional.isPresent()) {
                EsquemasAmortizacion esquemaAmortizacionDb = esquemaAmortizacionOpcional.get();

                if (esquemaAmortizacion.getTipoPrestamo() != null &&
                        esquemaAmortizacion.getTipoPrestamo().getIdTipoPrestamo() != null) {
                    Optional<TipoPrestamo> tipoPrestamoOpt = this.tipoPrestamoRepositorio.findById(
                            esquemaAmortizacion.getTipoPrestamo().getIdTipoPrestamo());
                    if (!tipoPrestamoOpt.isPresent()) {
                        throw new TipoPrestamoNoEncontradoExcepcion("Tipo Prestamo",
                                "El tipo de préstamo especificado no existe");
                    }
                    esquemaAmortizacionDb.setTipoPrestamo(tipoPrestamoOpt.get());
                }

                esquemaAmortizacionDb.setNombre(esquemaAmortizacion.getNombre());
                esquemaAmortizacionDb.setDescripcion(esquemaAmortizacion.getDescripcion());
                esquemaAmortizacionDb.setPermiteGracia(esquemaAmortizacion.getPermiteGracia());

                this.esquemaAmortizacionRepositorio.save(esquemaAmortizacionDb);
            } else {
                throw new EsquemaNoEncontradoExcepcion("Esquemas de Amortización",
                        "Error al actualizar el esquema de amortización. No se encontró con ID: " +
                                esquemaAmortizacion.getId());
            }
        } catch (Exception e) {
            throw new ActualizarEntidadExcepcion("Esquemas de Amortización",
                    "Error al actualizar el esquema de amortización: " + e.getMessage());
        }
    }

    public EsquemasAmortizacion obtenerEsquemaAmortizacionPorId(Integer id) {
        Optional<EsquemasAmortizacion> esquemaAmortizacionOpcional = this.esquemaAmortizacionRepositorio.findById(id);
        if (esquemaAmortizacionOpcional.isPresent()) {
            return esquemaAmortizacionOpcional.get();
        } else {
            throw new EsquemaNoEncontradoExcepcion("Esquemas de Amortización",
                    "Esquema de amortización no encontrado con ID: " + id);
        }
    }

    public List<EsquemasAmortizacion> obtenerTodosLosEsquemasAmortizacion() {
        return this.esquemaAmortizacionRepositorio.findAll();
    }

    public List<EsquemasAmortizacion> obtenerEsquemasAmortizacionPorTipoPrestamo(Integer idTipoPrestamo) {
        Optional<TipoPrestamo> tipoPrestamoOpcional = this.tipoPrestamoRepositorio.findById(idTipoPrestamo);
        if (tipoPrestamoOpcional.isPresent()) {
            return this.esquemaAmortizacionRepositorio.findByTipoPrestamo(tipoPrestamoOpcional.get());
        } else {
            throw new TipoPrestamoNoEncontradoExcepcion("Tipos Prestamos",
                    "Tipo de préstamo no encontrado con ID: " + idTipoPrestamo);
        }
    }

    private void validarEsquemaAmortizacion(EsquemasAmortizacion esquemaAmortizacion) {
        if (esquemaAmortizacion == null) {
            throw new CrearEntidadExcepcion("EsquemaAmortizacion",
                    "El esquema de amortización no puede ser nulo");
        }
        if (esquemaAmortizacion.getNombre() == null || esquemaAmortizacion.getNombre().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("EsquemaAmortizacion",
                    "El nombre del esquema de amortización es requerido");
        }
        if (esquemaAmortizacion.getNombre().length() > 20) {
            throw new CrearEntidadExcepcion("EsquemaAmortizacion",
                    "El nombre del esquema de amortización no puede exceder los 20 caracteres");
        }
        if (esquemaAmortizacion.getDescripcion() != null && esquemaAmortizacion.getDescripcion().length() > 200) {
            throw new CrearEntidadExcepcion("EsquemaAmortizacion",
                    "La descripción del esquema de amortización no puede exceder los 200 caracteres");
        }
    }
}
