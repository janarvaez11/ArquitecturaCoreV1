package com.banquito.core.clientes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.clientes.modelo.Personas;

@Repository
public interface PersonaRepositorio extends JpaRepository<Personas, Integer> {
}