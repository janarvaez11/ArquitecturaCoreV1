package com.banquito.core.aplicacion.prestamos.repositorio;

import com.banquito.core.aplicacion.prestamos.modelo.PrestamosClientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoClienteRepositorio extends JpaRepository<PrestamosClientes, Integer> {
}
