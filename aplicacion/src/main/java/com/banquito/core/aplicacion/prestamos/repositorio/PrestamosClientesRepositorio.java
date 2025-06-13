package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.PrestamosClientes;
import com.banquito.core.aplicacion.prestamos.modelo.Prestamo;

public interface PrestamosClientesRepositorio extends JpaRepository<PrestamosClientes, Integer> {
    List<PrestamosClientes> findByIdPrestamo(Prestamo prestamo);

    List<PrestamosClientes> findByEstado(String estado);

    /// Optional<PrestamosClientes> findFirstByCliente(Cliente cliente);
    Optional<PrestamosClientes> findFirstByIdCliente_idCliente(Integer idCliente);
}