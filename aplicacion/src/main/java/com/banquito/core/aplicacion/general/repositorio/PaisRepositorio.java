package com.banquito.core.aplicacion.general.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.general.modelo.Pais;

@Repository
public interface PaisRepositorio extends JpaRepository<Pais, String> {

}
