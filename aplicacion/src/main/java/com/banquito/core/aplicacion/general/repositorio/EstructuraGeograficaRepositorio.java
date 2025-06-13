package com.banquito.core.aplicacion.general.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.general.modelo.EstructuraGeografica;
import com.banquito.core.aplicacion.general.modelo.EstructuraGeograficaId;

@Repository
public interface EstructuraGeograficaRepositorio extends JpaRepository<EstructuraGeografica, EstructuraGeograficaId> {
    List<EstructuraGeografica> findByIdPaisId(String paisId);

}
