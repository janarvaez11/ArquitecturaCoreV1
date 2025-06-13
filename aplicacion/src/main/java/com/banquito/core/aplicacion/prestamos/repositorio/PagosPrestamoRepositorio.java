package com.banquito.core.aplicacion.prestamos.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.banquito.core.aplicacion.prestamos.modelo.PagosPrestamos;

public interface PagosPrestamoRepositorio extends JpaRepository<PagosPrestamos, Integer> {

    List<PagosPrestamos> findByIdPrestamo(Integer idPrestamo);

    List<PagosPrestamos> findByIdPrestamoAndFechaBetween(Integer idPrestamo, LocalDate fechaInicio, LocalDate fechaFin);

    List<PagosPrestamos> findByTipoPago(String tipoPago);

    @Query("SELECT p FROM PagosPrestamos p WHERE p.idPrestamo = :idPrestamo ORDER BY p.fecha DESC")
    List<PagosPrestamos> findLastPaymentsByPrestamo(@Param("idPrestamo") Integer idPrestamo);
}
