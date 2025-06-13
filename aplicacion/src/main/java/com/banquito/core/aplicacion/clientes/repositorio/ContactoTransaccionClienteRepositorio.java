package com.banquito.core.aplicacion.clientes.repositorio;

import com.banquito.core.aplicacion.clientes.modelo.ContactoTransaccionCliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactoTransaccionClienteRepositorio extends JpaRepository<ContactoTransaccionCliente, Integer> {
    Optional<ContactoTransaccionCliente> findByCliente_idCliente(Integer idCliente);
}

