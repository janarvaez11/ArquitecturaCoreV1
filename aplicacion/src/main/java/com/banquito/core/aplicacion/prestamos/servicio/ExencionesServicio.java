package com.banquito.core.aplicacion.prestamos.servicio;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.ExencionesPrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;
import com.banquito.core.aplicacion.prestamos.modelo.ExencionesPrestamo;
import com.banquito.core.aplicacion.prestamos.repositorio.ExencionesPrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ExencionesServicio {
    
    private final ExencionesPrestamoRepositorio repositorio;

    private static final List<String> TIPOS_EXENCION_VALIDOS = Arrays.asList(
        "CLIENTE PREFERENCIAL", "CAMPAÑA", "MANUAL"
    );

    public ExencionesServicio(ExencionesPrestamoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public boolean existeExencionParaComision(ComisionPrestamo comisionPrestamo, String tipoExencion) {
        if (!TIPOS_EXENCION_VALIDOS.contains(tipoExencion)) {
            throw new BusquedaExcepcion("ExencionesPrestamo", 
                "Tipo de exención no válido. Debe ser uno de: " + String.join(", ", TIPOS_EXENCION_VALIDOS));
        }
        return repositorio.existsByIdComisionPrestamoAndTipoExencion(comisionPrestamo, tipoExencion);
    }

    public ExencionesPrestamo findById(Integer id) {
        Optional<ExencionesPrestamo> exencionOpcional = this.repositorio.findById(id);
        if (exencionOpcional.isPresent()) {
            return exencionOpcional.get();
        } else {
            throw new ExencionesPrestamoNoEncontradoExcepcion("Exenciones Prestamo","Exenciones Prestamo no encontrado con ID: " + id);
        }
    }

    @Transactional
    public void create(ExencionesPrestamo exencionesPrestamo) {
        try {
            validarExencion(exencionesPrestamo);
            this.repositorio.save(exencionesPrestamo);
        } catch (Exception rte) {
            throw new CrearEntidadExcepcion("Exenciones Prestamo", "Error al crear la Exencion Prestamo. Texto del error: "+rte.getMessage());
        }
    }

    @Transactional
    public void update(ExencionesPrestamo exencionesPrestamo) {
        try {
            Optional<ExencionesPrestamo> exencionOpcional = this.repositorio.findById(exencionesPrestamo.getId());

            if (exencionOpcional.isPresent()) {
                ExencionesPrestamo exencionDb = exencionOpcional.get();
                exencionDb.setTipoExencion(exencionesPrestamo.getTipoExencion());
                exencionDb.setNombre(exencionesPrestamo.getNombre());
                exencionDb.setDescripcion(exencionesPrestamo.getDescripcion());
                exencionDb.setIdComisionPrestamo(exencionesPrestamo.getIdComisionPrestamo());
                
                validarExencion(exencionDb);
                this.repositorio.save(exencionDb);
            } else {
                throw new ExencionesPrestamoNoEncontradoExcepcion("Exenciones Prestamo", 
                    "Error al actualizar la Exencion Prestamo. No se encontró la ExencionesPrestamo con ID: " + exencionesPrestamo.getId());
            }
        } catch (Exception rte) {
            throw new ActualizarEntidadExcepcion("Exenciones Prestamo", "Error al actualizar la Exencion Prestamo. Texto del error: "+rte.getMessage());
        }
    }

    private void validarExencion(ExencionesPrestamo exencion) {
        if (!TIPOS_EXENCION_VALIDOS.contains(exencion.getTipoExencion())) {
            throw new BusquedaExcepcion("ExencionesPrestamo", 
                "Tipo de exención no válido. Debe ser uno de: " + String.join(", ", TIPOS_EXENCION_VALIDOS));
        }

        if (exencion.getIdComisionPrestamo() == null) {
            throw new BusquedaExcepcion("ExencionesPrestamo", 
                "Debe especificar una comisión de préstamo para la exención");
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Optional<ExencionesPrestamo> exencionOpcional = this.repositorio.findById(id);
            if (exencionOpcional.isPresent()) {
                this.repositorio.delete(exencionOpcional.get());
            } else {
                throw new ExencionesPrestamoNoEncontradoExcepcion("Exenciones Prestamo", 
                    "Error al eliminar la ExencionesPrestamo. No se encontró la ExencionesPrestamo con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("Exenciones Prestamo", "Error al eliminar la ExencionesPrestamo. Texto del error: "+rte.getMessage());
        }
    }
}
