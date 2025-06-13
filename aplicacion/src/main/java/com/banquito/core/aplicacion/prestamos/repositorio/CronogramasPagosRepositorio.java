package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.CronogramasPagos;

public interface CronogramasPagosRepositorio extends JpaRepository<CronogramasPagos, Integer> {
    List<CronogramasPagos> findByIdPrestamo(Integer idPrestamo);

    List<CronogramasPagos> findByIdPrestamoAndEstado(Integer idPrestamo, String estado);
}