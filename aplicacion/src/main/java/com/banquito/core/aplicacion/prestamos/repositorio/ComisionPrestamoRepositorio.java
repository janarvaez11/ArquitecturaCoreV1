package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;

public interface ComisionPrestamoRepositorio extends JpaRepository<ComisionPrestamo, Integer> {
    List<ComisionPrestamo> findByTipoComision(String tipoComision);
}