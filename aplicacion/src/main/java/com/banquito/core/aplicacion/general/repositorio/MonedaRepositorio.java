package com.banquito.core.aplicacion.general.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.general.modelo.Moneda;

import java.util.List;

@Repository
public interface MonedaRepositorio extends JpaRepository<Moneda, String> {
    List<Moneda> findByPaisId(String paisId);
    List<Moneda> findByEntidadBancariaId(Integer idEntidadBancaria);
}
