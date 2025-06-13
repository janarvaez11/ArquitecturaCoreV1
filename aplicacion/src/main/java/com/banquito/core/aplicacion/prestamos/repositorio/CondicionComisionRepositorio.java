package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.CondicionComision;

public interface CondicionComisionRepositorio extends JpaRepository<CondicionComision, Integer> {
    List<CondicionComision> findByTipoCondicion(String tipoCondicion);
}