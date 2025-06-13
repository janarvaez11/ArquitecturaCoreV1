package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.Prestamo;
import com.banquito.core.aplicacion.prestamos.modelo.Seguro;

public interface SeguroRepositorio extends JpaRepository<Seguro, Integer> {

    // encontrar por ID del préstamo
    Optional<Seguro> findFirstByPrestamo(Prestamo prestamo);

}
