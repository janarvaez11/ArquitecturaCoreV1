package com.banquito.core.aplicacion.prestamos.servicio;

import java.util.Optional;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.general.repositorio.MonedaRepositorio;
import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.TipoPrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.TipoPrestamo;
import com.banquito.core.aplicacion.prestamos.repositorio.TipoPrestamoRepositorio;
import com.banquito.core.aplicacion.general.modelo.Moneda;

import jakarta.transaction.Transactional;

@Service
public class TipoPrestamosServicio {

    private final TipoPrestamoRepositorio repositorio;
    private final MonedaRepositorio monedaRepositorio;

    // Lista de tipos de cliente permitidos
    private static final List<String> TIPOS_CLIENTE_PERMITIDOS = Arrays.asList(
            "PERSONA NATURAL",
            "EMPRESA JURIDICA",
            "AMBOS");

    public TipoPrestamosServicio(TipoPrestamoRepositorio repositorio, MonedaRepositorio monedaRepositorio) {
        this.repositorio = repositorio;
        this.monedaRepositorio = monedaRepositorio;
    }

    public TipoPrestamo findById(Integer id) {
        try {
            Optional<TipoPrestamo> tipoOptional = this.repositorio.findById(id);
            if (tipoOptional.isPresent()) {
                return tipoOptional.get();
            } else {
                throw new BusquedaExcepcion("Tipo Prestamos",
                        "No se encontró el tipo de préstamo con ID: " + id);
            }
        } catch (BusquedaExcepcion e) {
            throw e;
        } catch (Exception e) {
            throw new BusquedaExcepcion("Tipo Prestamos",
                    "Error al buscar el tipo de préstamo por ID: " + id, e);
        }
    }

    public List<TipoPrestamo> findByEstado(String estado) {
        try {
            if (estado == null || estado.trim().isEmpty()) {
                throw new BusquedaExcepcion("Tipo Prestamos", "El estado no puede estar vacío");
            }
            if (!estado.equals("ACTIVO") && !estado.equals("INACTIVO")) {
                throw new BusquedaExcepcion("Tipo Prestamos",
                        "El estado debe ser 'ACTIVO' o 'INACTIVO'");
            }
            return this.repositorio.findByEstado(estado);
        } catch (BusquedaExcepcion e) {
            throw e;
        } catch (Exception e) {
            throw new BusquedaExcepcion("Tipo Prestamos",
                    "Error al buscar tipos de préstamo por estado: " + estado, e);
        }
    }

    // Buscar tipos de préstamo por TIPO de cliente
    public List<TipoPrestamo> findByTipoCliente(String tipoCliente) {
        try {
            if (tipoCliente == null || tipoCliente.trim().isEmpty()) {
                throw new BusquedaExcepcion("Tipo Prestamos", "El tipo de cliente no puede estar vacío");
            }
            if (!TIPOS_CLIENTE_PERMITIDOS.contains(tipoCliente)) {
                throw new BusquedaExcepcion("Tipo Prestamos",
                        "El tipo de cliente debe ser uno de los valores permitidos: " +
                                String.join(", ", TIPOS_CLIENTE_PERMITIDOS));
            }
            return this.repositorio.findByTipoCliente(tipoCliente);
        } catch (BusquedaExcepcion e) {
            throw e;
        } catch (Exception e) {
            throw new BusquedaExcepcion("Tipo Prestamos",
                    "Error al buscar tipos de préstamo por tipo de cliente: " + tipoCliente, e);
        }
    }

    @Transactional
    public void create(TipoPrestamo tipoPrestamo) {
        try {
            validarTipoPrestamo(tipoPrestamo);

            if (repositorio.existsByNombre(tipoPrestamo.getNombre())) {
                throw new CrearEntidadExcepcion("Tipo Prestamos",
                        "Ya existe un tipo de préstamo con el nombre: " + tipoPrestamo.getNombre());
            }

            // Buscar la moneda por su ID y asignarla
            Moneda moneda = monedaRepositorio.findById(tipoPrestamo.getMoneda().getId())
                    .orElseThrow(() -> new CrearEntidadExcepcion("Tipo Prestamos", "La moneda especificada no existe"));
            tipoPrestamo.setMoneda(moneda);

            tipoPrestamo.setFechaCreacion(LocalDate.now());
            tipoPrestamo.setFechaModificacion(null);
            tipoPrestamo.setEstado("ACTIVO");
            this.repositorio.save(tipoPrestamo);
        } catch (CrearEntidadExcepcion rte) {
            throw new CrearEntidadExcepcion("Tipo Prestamos",
                    "Error al crear el Tipo de préstamo. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void update(TipoPrestamo tipoPrestamo) {
        try {
            Optional<TipoPrestamo> tipoOptional = this.repositorio.findById(tipoPrestamo.getIdTipoPrestamo());
            if (tipoOptional.isPresent()) {
                TipoPrestamo tipoPrestamoDb = tipoOptional.get();

                // Validar que no se intente cambiar la moneda
                if (!tipoPrestamoDb.getMoneda().getId().equals(tipoPrestamo.getMoneda().getId())) {
                    throw new ActualizarEntidadExcepcion("Tipo Prestamos",
                            "No se puede modificar la moneda base del tipo de préstamo");
                }

                // Validar el resto de campos
                validarTipoPrestamo(tipoPrestamo);

                if (!tipoPrestamoDb.getNombre().equals(tipoPrestamo.getNombre()) &&
                        repositorio.existsByNombre(tipoPrestamo.getNombre())) {
                    throw new ActualizarEntidadExcepcion("Tipo Prestamos",
                            "Ya existe un tipo de préstamo con el nombre: " + tipoPrestamo.getNombre());
                }

                tipoPrestamoDb.setNombre(tipoPrestamo.getNombre());
                tipoPrestamoDb.setDescripcion(tipoPrestamo.getDescripcion());
                tipoPrestamoDb.setMontoMinimo(tipoPrestamo.getMontoMinimo());
                tipoPrestamoDb.setMontoMaximo(tipoPrestamo.getMontoMaximo());
                tipoPrestamoDb.setPlazoMinimo(tipoPrestamo.getPlazoMinimo());
                tipoPrestamoDb.setPlazoMaximo(tipoPrestamo.getPlazoMaximo());
                tipoPrestamoDb.setRequisitos(tipoPrestamo.getRequisitos());
                tipoPrestamoDb.setTipoCliente(tipoPrestamo.getTipoCliente());
                tipoPrestamoDb.setFechaModificacion(LocalDate.now());

                this.repositorio.save(tipoPrestamoDb);
            } else {
                throw new TipoPrestamoNoEncontradoExcepcion("Tipo prestamo",
                        "Error al actualizar el Tipo de préstamo. No se encontró el tipo de préstamo con ID: "
                                + tipoPrestamo.getIdTipoPrestamo());
            }
        } catch (ActualizarEntidadExcepcion e) {
            throw e;
        } catch (Exception rte) {
            throw new ActualizarEntidadExcepcion("Tipo Prestamos",
                    "Error al actualizar el Tipo de préstamo. Texto del error: " + rte.getMessage());
        }
    }

    // Método para eliminar un tipo de préstamo pero marcándolo como inactivo
    @Transactional
    public void delete(Integer id) {
        try {
            Optional<TipoPrestamo> tipoOptional = this.repositorio.findById(id);
            if (tipoOptional.isPresent()) {
                TipoPrestamo tipoPrestamo = tipoOptional.get();
                tipoPrestamo.setEstado("INACTIVO");
                tipoPrestamo.setFechaModificacion(LocalDate.now());
                this.repositorio.save(tipoPrestamo);
            } else {
                throw new TipoPrestamoNoEncontradoExcepcion("Tipo prestamo",
                        "Error al eliminar el Tipo de préstamo. No se encontró el tipo de préstamo con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("Tipo Prestamos",
                    "Error al eliminar el Tipo de préstamo. Texto del error: " + rte.getMessage());
        }
    }

    private void validarTipoPrestamo(TipoPrestamo tipoPrestamo) {
        // Validación de moneda - solo verificar que no sea nula
        if (tipoPrestamo.getMoneda() == null) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "Debe especificar una moneda");
        }

        // Validación de montos
        if (tipoPrestamo.getMontoMinimo() == null || tipoPrestamo.getMontoMaximo() == null) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "Los montos mínimo y máximo son obligatorios");
        }
        if (tipoPrestamo.getMontoMinimo().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "El monto mínimo debe ser mayor a cero");
        }
        if (tipoPrestamo.getMontoMaximo().compareTo(tipoPrestamo.getMontoMinimo()) < 0) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "El monto máximo debe ser mayor o igual al monto mínimo");
        }

        // Validación de plazos
        if (tipoPrestamo.getPlazoMinimo() == null || tipoPrestamo.getPlazoMaximo() == null) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "Los plazos mínimo y máximo son obligatorios");
        }
        if (tipoPrestamo.getPlazoMinimo() <= 0) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "El plazo mínimo debe ser mayor a cero");
        }
        if (tipoPrestamo.getPlazoMaximo() < tipoPrestamo.getPlazoMinimo()) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "El plazo máximo debe ser mayor o igual al plazo mínimo");
        }

        // Validación de tipo de cliente
        if (tipoPrestamo.getTipoCliente() == null || tipoPrestamo.getTipoCliente().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("Tipo Prestamos", "El tipo de cliente es obligatorio");
        }
        if (!TIPOS_CLIENTE_PERMITIDOS.contains(tipoPrestamo.getTipoCliente())) {
            throw new CrearEntidadExcepcion("Tipo Prestamos",
                    "El tipo de cliente debe ser uno de los valores permitidos: "
                            + String.join(", ", TIPOS_CLIENTE_PERMITIDOS));
        }
    }

}
