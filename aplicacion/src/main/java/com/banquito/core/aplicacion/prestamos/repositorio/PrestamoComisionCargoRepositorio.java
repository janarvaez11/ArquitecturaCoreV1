package com.banquito.core.aplicacion.prestamos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.PrestamoComisionCargo;
import com.banquito.core.aplicacion.prestamos.modelo.PrestamoComisionCargoId;

public interface PrestamoComisionCargoRepositorio extends JpaRepository<PrestamoComisionCargo, PrestamoComisionCargoId> {

} 