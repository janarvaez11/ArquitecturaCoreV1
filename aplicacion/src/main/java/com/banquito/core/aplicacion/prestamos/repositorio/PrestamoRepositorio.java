package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.Prestamo;

public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
    List<Prestamo> findByEstado(String estado);
    List<Prestamo> findByTipoPrestamo_IdTipoPrestamo(Integer idTipoPrestamo);
}
