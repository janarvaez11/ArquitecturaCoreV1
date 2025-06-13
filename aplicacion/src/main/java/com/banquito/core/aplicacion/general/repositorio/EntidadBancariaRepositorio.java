package com.banquito.core.aplicacion.general.repositorio;

import com.banquito.core.aplicacion.general.modelo.EntidadBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadBancariaRepositorio extends JpaRepository<EntidadBancaria, Integer> {

}
