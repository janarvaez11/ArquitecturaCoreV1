package com.banquito.core.aplicacion.prestamos.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.PrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.CronogramasPagos;
import com.banquito.core.aplicacion.prestamos.repositorio.CronogramasPagosRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CronogramasPagosServicio {

    private final CronogramasPagosRepositorio repositorio;

    public CronogramasPagosServicio(CronogramasPagosRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public CronogramasPagos buscarPorId(Integer id) {
        Optional<CronogramasPagos> cronogramaOpcional = this.repositorio.findById(id);
        if (cronogramaOpcional.isPresent()) {
            return cronogramaOpcional.get();
        } else {
            throw new PrestamoNoEncontradoExcepcion("Cronograma de Pagos",
                    "Cuota no encontrada con ID: " + id);
        }
    }

    public List<CronogramasPagos> buscarPorPrestamo(Integer idPrestamo) {
        return this.repositorio.findByIdPrestamo(idPrestamo);
    }

    public List<CronogramasPagos> buscarPorPrestamoYEstado(Integer idPrestamo, String estado) {
        return this.repositorio.findByIdPrestamoAndEstado(idPrestamo, estado);
    }

    public List<CronogramasPagos> buscarTodos() {
        return this.repositorio.findAll();
    }

    @Transactional
    public void crear(CronogramasPagos cronogramaPago) {
        try {
            validarCronogramaPago(cronogramaPago);
            cronogramaPago.setEstado("PENDIENTE");
            cronogramaPago.setFechaPago(LocalDate.now());

            // Calcular el total
            BigDecimal total = cronogramaPago.getCapital()
                    .add(cronogramaPago.getInteres())
                    .add(cronogramaPago.getComisiones() != null ? cronogramaPago.getComisiones() : BigDecimal.ZERO)
                    .add(cronogramaPago.getSeguros() != null ? cronogramaPago.getSeguros() : BigDecimal.ZERO);
            cronogramaPago.setTotal(total);
            cronogramaPago.setSaldo(total);

            this.repositorio.save(cronogramaPago);
        } catch (Exception e) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "Error al crear la cuota: " + e.getMessage());
        }
    }

    @Transactional
    public void registrarPago(Integer id, LocalDate fechaPago) {
        try {
            Optional<CronogramasPagos> cronogramaOpcional = this.repositorio.findById(id);
            if (cronogramaOpcional.isPresent()) {
                CronogramasPagos cronograma = cronogramaOpcional.get();
                if (!"PENDIENTE".equals(cronograma.getEstado())) {
                    throw new ActualizarEntidadExcepcion("Cronograma de Pagos",
                            "Solo se pueden registrar pagos de cuotas en estado PENDIENTE");
                }
                cronograma.setEstado("PAGADO");
                cronograma.setFechaPago(fechaPago);
                this.repositorio.save(cronograma);
            } else {
                throw new PrestamoNoEncontradoExcepcion("Cronograma de Pagos",
                        "Cuota no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            throw new ActualizarEntidadExcepcion("Cronograma de Pagos",
                    "Error al registrar el pago de la cuota: " + e.getMessage());
        }
    }

    @Transactional
    public void actualizar(CronogramasPagos cronogramaPago) {
        try {
            validarCronogramaPago(cronogramaPago);
            Optional<CronogramasPagos> cronogramaOpcional = this.repositorio.findById(cronogramaPago.getId());
            if (cronogramaOpcional.isPresent()) {
                CronogramasPagos cronogramaDb = cronogramaOpcional.get();
                if (!"PENDIENTE".equals(cronogramaDb.getEstado())) {
                    throw new ActualizarEntidadExcepcion("Cronograma de Pagos",
                            "Solo se pueden actualizar cuotas en estado PENDIENTE");
                }

                cronogramaDb.setVencimiento(cronogramaPago.getVencimiento());
                cronogramaDb.setCapital(cronogramaPago.getCapital());
                cronogramaDb.setInteres(cronogramaPago.getInteres());
                cronogramaDb.setComisiones(cronogramaPago.getComisiones());
                cronogramaDb.setSeguros(cronogramaPago.getSeguros());

                // Recalcular el total
                BigDecimal total = cronogramaDb.getCapital()
                        .add(cronogramaDb.getInteres())
                        .add(cronogramaDb.getComisiones() != null ? cronogramaDb.getComisiones() : BigDecimal.ZERO)
                        .add(cronogramaDb.getSeguros() != null ? cronogramaDb.getSeguros() : BigDecimal.ZERO);
                cronogramaDb.setTotal(total);

                this.repositorio.save(cronogramaDb);
            } else {
                throw new PrestamoNoEncontradoExcepcion("Cronograma de Pagos",
                        "Cuota no encontrada con ID: " + cronogramaPago.getId());
            }
        } catch (Exception e) {
            throw new ActualizarEntidadExcepcion("Cronograma de Pagos",
                    "Error al actualizar la cuota: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            Optional<CronogramasPagos> cronogramaOpcional = this.repositorio.findById(id);
            if (cronogramaOpcional.isPresent()) {
                CronogramasPagos cronograma = cronogramaOpcional.get();
                if (!"PENDIENTE".equals(cronograma.getEstado())) {
                    throw new EliminarEntidadExcepcion("Cronograma de Pagos",
                            "Solo se pueden eliminar cuotas en estado PENDIENTE");
                }
                this.repositorio.delete(cronograma);
            } else {
                throw new PrestamoNoEncontradoExcepcion("Cronograma de Pagos",
                        "Cuota no encontrada con ID: " + id);
            }
        } catch (Exception e) {
            throw new EliminarEntidadExcepcion("Cronograma de Pagos",
                    "Error al eliminar la cuota: " + e.getMessage());
        }
    }

    private void validarCronogramaPago(CronogramasPagos cronogramaPago) {
        if (cronogramaPago == null) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "La cuota no puede ser nula");
        }
        if (cronogramaPago.getIdPrestamo() == null) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "El ID del préstamo es requerido");
        }
        if (cronogramaPago.getNumeroCuota() == null) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "El número de cuota es requerido");
        }
        if (cronogramaPago.getVencimiento() == null) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "La fecha de vencimiento es requerida");
        }
        if (cronogramaPago.getCapital() == null || cronogramaPago.getCapital().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "El capital debe ser mayor a cero");
        }
        if (cronogramaPago.getInteres() == null || cronogramaPago.getInteres().compareTo(BigDecimal.ZERO) < 0) {
            throw new CrearEntidadExcepcion("Cronograma de Pagos",
                    "El interés no puede ser negativo");
        }
    }
}