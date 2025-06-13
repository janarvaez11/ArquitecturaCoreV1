package com.banquito.core.aplicacion.prestamos.servicio;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.PrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.Prestamo;
import com.banquito.core.aplicacion.prestamos.modelo.TipoPrestamo;
import com.banquito.core.aplicacion.prestamos.repositorio.PrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PrestamoServicio {

    private final PrestamoRepositorio repositorio;
    private final TipoPrestamosServicio tipoPrestamosServicio;

    // Lista de bases de cálculo permitidas
    private static final List<String> BASES_CALCULO_PERMITIDAS = Arrays.asList(
            "30/360",
            "31/365",
            "Actual/360",
            "Actual/365");

    // Esto es para prestamos clientes- lo modifico
    // Lista de estados permitidos
    private static final List<String> ESTADOS_PERMITIDOS = Arrays.asList(
            "ACTIVO",
            "INACTIVO");

    public PrestamoServicio(PrestamoRepositorio repositorio, TipoPrestamosServicio tipoPrestamosServicio) {
        this.tipoPrestamosServicio = tipoPrestamosServicio;
        this.repositorio = repositorio;
    }

    public Prestamo findById(Integer id) {
        Optional<Prestamo> prestamoOpcional = this.repositorio.findById(id);
        if (prestamoOpcional.isPresent()) {
            return prestamoOpcional.get();
        } else {
            throw new PrestamoNoEncontradoExcepcion("Prestamos", "Tipo de préstamo no encontrado con ID: " + id);
        }
    }

    @Transactional
    public void create(Prestamo prestamo) {
        try {
            validarPrestamo(prestamo);

            // Verificar si el tipo de préstamo se puede encontrar por ID
            TipoPrestamo tipoEncontrado = this.tipoPrestamosServicio.findById(
                    prestamo.getTipoPrestamo().getIdTipoPrestamo());
            if (tipoEncontrado == null) {
                throw new CrearEntidadExcepcion("Prestamos", "El tipo de préstamo especificado no existe");
            }

            // Establecer valores por defecto
            prestamo.setEstado(ESTADOS_PERMITIDOS.get(0));
            prestamo.setFechaModificacion(null);

            this.repositorio.save(prestamo);
        } catch (RuntimeException rte) {
            throw new CrearEntidadExcepcion("Prestamos",
                    "Error al crear el prestamo. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void update(Prestamo prestamo) {
        try {
            validarPrestamo(prestamo);

            Optional<Prestamo> prestamoOpcional = this.repositorio.findById(prestamo.getId());

            if (!prestamoOpcional.isPresent()) {
                throw new PrestamoNoEncontradoExcepcion("Prestamo",
                        "Error al actualizar el préstamo. No se encontró el préstamo con ID: " + prestamo.getId());
            }

            // Validar tipo de préstamo
            if (prestamo.getTipoPrestamo() == null ||
                    prestamo.getTipoPrestamo().getIdTipoPrestamo() == null) {
                throw new ActualizarEntidadExcepcion("Prestamos", "Debe especificarse un tipo de préstamo válido.");
            }

            TipoPrestamo tipoEncontrado = this.tipoPrestamosServicio.findById(
                    prestamo.getTipoPrestamo().getIdTipoPrestamo());

            if (tipoEncontrado == null) {
                throw new ActualizarEntidadExcepcion("Prestamos", "El tipo de préstamo especificado no existe.");
            }

            Prestamo prestamoDb = prestamoOpcional.get();
            prestamoDb.setTipoPrestamo(tipoEncontrado);
            prestamoDb.setNombre(prestamo.getNombre());
            prestamoDb.setDescripcion(prestamo.getDescripcion());
            prestamoDb.setBaseCalculo(prestamo.getBaseCalculo());
            prestamoDb.setTasaMonetaria(prestamo.getTasaMonetaria());
            prestamoDb.setFechaModificacion(LocalDate.now());

            this.repositorio.save(prestamoDb);
        } catch (RuntimeException rte) {
            throw new ActualizarEntidadExcepcion("Prestamos",
                    "Error al actualizar el prestamo. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void delete(Integer id) {
        try {
            Optional<Prestamo> prestamoOpcional = this.repositorio.findById(id);
            if (prestamoOpcional.isPresent()) {
                Prestamo prestamo = prestamoOpcional.get();
                prestamo.setEstado(ESTADOS_PERMITIDOS.get(1));
                prestamo.setFechaModificacion(LocalDate.now());
                this.repositorio.save(prestamo);
            } else {
                throw new PrestamoNoEncontradoExcepcion("Prestamo",
                        "Error al eliminar el prestamo. No se encontró el tipo de prestamo con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("Prestamos",
                    "Error al eliminar el prestamo. Texto del error: " + rte.getMessage());
        }
    }

    private void validarPrestamo(Prestamo prestamo) {
        // Validar tipo de préstamo
        if (prestamo.getTipoPrestamo() == null || prestamo.getTipoPrestamo().getIdTipoPrestamo() == null) {
            throw new CrearEntidadExcepcion("Prestamo", "Debe especificar un tipo de préstamo válido");
        }

        // Validar nombre
        if (prestamo.getNombre() == null || prestamo.getNombre().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("Prestamo", "El nombre es obligatorio");
        }
        if (prestamo.getNombre().length() > 100) {
            throw new CrearEntidadExcepcion("Prestamo", "El nombre no puede exceder los 100 caracteres");
        }

        // Validar descripción
        if (prestamo.getDescripcion() == null || prestamo.getDescripcion().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("Prestamo", "La descripción es obligatoria");
        }
        if (prestamo.getDescripcion().length() > 200) {
            throw new CrearEntidadExcepcion("Prestamo", "La descripción no puede exceder los 200 caracteres");
        }

        // Validar base de cálculo
        if (prestamo.getBaseCalculo() == null || prestamo.getBaseCalculo().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("Prestamo", "La base de cálculo es obligatoria");
        }
        if (!BASES_CALCULO_PERMITIDAS.contains(prestamo.getBaseCalculo())) {
            throw new CrearEntidadExcepcion("Prestamo",
                    "La base de cálculo debe ser uno de los valores permitidos: " +
                            String.join(", ", BASES_CALCULO_PERMITIDAS));
        }

        // Validar tasa moratoria
        if (prestamo.getTasaMonetaria() == null
                || prestamo.getTasaMonetaria().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            throw new CrearEntidadExcepcion("Prestamo", "La tasa moratoria debe ser mayor a cero");
        }
    }

    public List<Prestamo> findByEstado(String estado) {
        try {
            if (estado == null || estado.trim().isEmpty()) {
                throw new BusquedaExcepcion("Prestamo", "El estado no puede estar vacío");
            }
            if (!ESTADOS_PERMITIDOS.contains(estado)) {
                throw new BusquedaExcepcion("Prestamo",
                        "El estado debe ser uno de los valores permitidos: " +
                                String.join(", ", ESTADOS_PERMITIDOS));
            }
            return this.repositorio.findByEstado(estado);
        } catch (BusquedaExcepcion e) {
            throw e;
        } catch (Exception e) {
            throw new BusquedaExcepcion("Prestamo",
                    "Error al buscar préstamos por estado: " + estado, e);
        }
    }

    public List<Prestamo> findByTipoPrestamo(Integer idTipoPrestamo) {
        try {
            if (idTipoPrestamo == null) {
                throw new BusquedaExcepcion("Prestamo", "El idTipoPrestamo no puede ser nulo");
            }
            return this.repositorio.findByTipoPrestamo_IdTipoPrestamo(idTipoPrestamo);
        } catch (BusquedaExcepcion e) {
            throw new BusquedaExcepcion("Prestamo",
                "Error al buscar préstamos por tipo de préstamo con ID: " + idTipoPrestamo, e);
        }
    }
}
