package com.banquito.core.aplicacion.general.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.general.modelo.LocacionGeografica;

@Repository
public interface LocacionGeograficaRepositorio extends JpaRepository<LocacionGeografica, Integer> {
    List<LocacionGeografica> findByCodigoNivel(Integer codigoNivel);
}