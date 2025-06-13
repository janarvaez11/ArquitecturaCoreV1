package com.banquito.core.aplicacion.prestamos.servicio;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.CronogramasPagos;
import com.banquito.core.aplicacion.prestamos.modelo.PagosPrestamos;
import com.banquito.core.aplicacion.prestamos.repositorio.CronogramasPagosRepositorio;
import com.banquito.core.aplicacion.prestamos.repositorio.PagosPrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PagosPrestamoServicio {

    private final PagosPrestamoRepositorio pagosPrestamoRepositorio;
    private final CronogramasPagosRepositorio cronogramasPagosRepositorio;
    private final CronogramasPagosServicio cronogramasPagosServicio;

    public PagosPrestamoServicio(PagosPrestamoRepositorio pagosPrestamoRepositorio,
            CronogramasPagosRepositorio cronogramasPagosRepositorio,
            CronogramasPagosServicio cronogramasPagosServicio) {
        this.pagosPrestamoRepositorio = pagosPrestamoRepositorio;
        this.cronogramasPagosRepositorio = cronogramasPagosRepositorio;
        this.cronogramasPagosServicio = cronogramasPagosServicio;
    }

    public PagosPrestamos buscarPorId(Integer id) {
        Optional<PagosPrestamos> pagoOpcional = this.pagosPrestamoRepositorio.findById(id);
        if (pagoOpcional.isPresent()) {
            return pagoOpcional.get();
        } else {
            throw new RuntimeException("Pago no encontrado con ID: " + id);
        }
    }

    public List<PagosPrestamos> buscarTodos() {
        return this.pagosPrestamoRepositorio.findAll();
    }

    @Transactional
    public void registrarPago(PagosPrestamos pago) {
        try {
            // Validar el pago
            validarPago(pago);

            // Buscar cuotas pendientes del préstamo
            List<CronogramasPagos> cuotasPendientes = this.cronogramasPagosRepositorio
                    .findByIdPrestamoAndEstado(pago.getIdPrestamo(), "PENDIENTE");

            if (cuotasPendientes.isEmpty()) {
                throw new CrearEntidadExcepcion("Pagos Prestamo",
                        "No existen cuotas pendientes para este préstamo");
            }

            // Obtener la primera cuota pendiente
            CronogramasPagos cuotaPendiente = cuotasPendientes.get(0);

            // Validar que el monto total del pago coincida con la cuota
            if (pago.getTotal().compareTo(cuotaPendiente.getTotal()) != 0) {
                throw new CrearEntidadExcepcion("Pagos Prestamo",
                        "El monto del pago no coincide con el monto de la cuota pendiente");
            }

            // Registrar el pago
            pago.setFecha(LocalDate.now());
            this.pagosPrestamoRepositorio.save(pago);

            // Actualizar el estado de la cuota en el cronograma
            this.cronogramasPagosServicio.registrarPago(cuotaPendiente.getId(), LocalDate.now());

        } catch (Exception e) {
            throw new CrearEntidadExcepcion("Pagos Prestamo",
                    "Error al registrar el pago: " + e.getMessage());
        }
    }

    @Transactional
    public void actualizarPago(PagosPrestamos pago) {
        try {
            Optional<PagosPrestamos> pagoOpcional = this.pagosPrestamoRepositorio.findById(pago.getId());
            if (pagoOpcional.isPresent()) {
                PagosPrestamos pagoExistente = pagoOpcional.get();

                // Solo se permite actualizar ciertos campos
                pagoExistente.setReferencia(pago.getReferencia());
                pagoExistente.setUsuarioRegistro(pago.getUsuarioRegistro());

                this.pagosPrestamoRepositorio.save(pagoExistente);
            } else {
                throw new ActualizarEntidadExcepcion("Pagos Prestamo",
                        "No se encontró el pago con ID: " + pago.getId());
            }
        } catch (Exception e) {
            throw new ActualizarEntidadExcepcion("Pagos Prestamo",
                    "Error al actualizar el pago: " + e.getMessage());
        }
    }

    public List<PagosPrestamos> buscarPorPrestamo(Integer idPrestamo) {
        return this.pagosPrestamoRepositorio.findByIdPrestamo(idPrestamo);
    }

    public List<PagosPrestamos> buscarPorPrestamoYRangoDeFechas(Integer idPrestamo,
            LocalDate fechaInicio, LocalDate fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son requeridas");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha fin");
        }
        return this.pagosPrestamoRepositorio.findByIdPrestamoAndFechaBetween(idPrestamo, fechaInicio, fechaFin);
    }

    public List<PagosPrestamos> buscarPorTipoPago(String tipoPago) {
        if (tipoPago == null || tipoPago.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de pago es requerido");
        }
        return this.pagosPrestamoRepositorio.findByTipoPago(tipoPago);
    }

    public List<PagosPrestamos> buscarUltimosPagosPorPrestamo(Integer idPrestamo) {
        return this.pagosPrestamoRepositorio.findLastPaymentsByPrestamo(idPrestamo);
    }

    @Transactional
    public void registrarPagoAnticipado(PagosPrestamos pago) {
        try {
            validarPago(pago);

            // Buscar todas las cuotas pendientes del préstamo
            List<CronogramasPagos> cuotasPendientes = this.cronogramasPagosRepositorio
                    .findByIdPrestamoAndEstado(pago.getIdPrestamo(), "PENDIENTE");

            if (cuotasPendientes.isEmpty()) {
                throw new CrearEntidadExcepcion("Pagos Prestamo",
                        "No existen cuotas pendientes para este préstamo");
            }

            // Validar que el monto del pago sea suficiente para al menos una cuota
            CronogramasPagos primeraCuota = cuotasPendientes.get(0);
            if (pago.getTotal().compareTo(primeraCuota.getTotal()) < 0) {
                throw new CrearEntidadExcepcion("Pagos Prestamo",
                        "El monto del pago debe ser al menos igual al valor de la siguiente cuota");
            }

            // Registrar el pago
            pago.setFecha(LocalDate.now());
            pago.setTipoPago("ANTICIPADO");
            this.pagosPrestamoRepositorio.save(pago);

            // Actualizar el estado de la cuota en el cronograma
            this.cronogramasPagosServicio.registrarPago(primeraCuota.getId(), LocalDate.now());

            // Si el pago es mayor, aplicar a las siguientes cuotas
            BigDecimal saldoPago = pago.getTotal().subtract(primeraCuota.getTotal());
            int i = 1;
            while (saldoPago.compareTo(BigDecimal.ZERO) > 0 && i < cuotasPendientes.size()) {
                CronogramasPagos siguienteCuota = cuotasPendientes.get(i);
                if (saldoPago.compareTo(siguienteCuota.getTotal()) >= 0) {
                    this.cronogramasPagosServicio.registrarPago(siguienteCuota.getId(), LocalDate.now());
                    saldoPago = saldoPago.subtract(siguienteCuota.getTotal());
                }
                i++;
            }

        } catch (Exception e) {
            throw new CrearEntidadExcepcion("Pagos Prestamo",
                    "Error al registrar el pago anticipado: " + e.getMessage());
        }
    }

    private void validarPago(PagosPrestamos pago) {
        if (pago == null) {
            throw new CrearEntidadExcepcion("Pagos Prestamo", "El pago no puede ser nulo");
        }
        if (pago.getIdPrestamo() == null) {
            throw new CrearEntidadExcepcion("Pagos Prestamo", "El ID del préstamo es requerido");
        }
        if (pago.getCapital() == null || pago.getCapital().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CrearEntidadExcepcion("Pagos Prestamo", "El capital debe ser mayor a cero");
        }
        if (pago.getInteres() == null || pago.getInteres().compareTo(BigDecimal.ZERO) < 0) {
            throw new CrearEntidadExcepcion("Pagos Prestamo", "El interés no puede ser negativo");
        }
        if (pago.getTipoPago() == null || pago.getTipoPago().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("Pagos Prestamo", "El tipo de pago es requerido");
        }

        // Calcular el total del pago
        BigDecimal total = pago.getCapital()
                .add(pago.getInteres())
                .add(pago.getMora() != null ? pago.getMora() : BigDecimal.ZERO)
                .add(pago.getComisiones() != null ? pago.getComisiones() : BigDecimal.ZERO)
                .add(pago.getSeguros() != null ? pago.getSeguros() : BigDecimal.ZERO);

        pago.setTotal(total);
    }
}
