package com.banquito.core.aplicacion.prestamos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;
import com.banquito.core.aplicacion.prestamos.modelo.ExencionesPrestamo;

public interface ExencionesPrestamoRepositorio extends JpaRepository<ExencionesPrestamo, Integer> {
    boolean existsByIdComisionPrestamoAndTipoExencion(ComisionPrestamo idComisionPrestamo, String tipoExencion);
}
