package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.TasaInteres;
import com.banquito.core.aplicacion.cuentas.modelo.TasaPlazo;

@Repository
public interface TasaPlazoRepositorio extends JpaRepository<TasaPlazo, Integer> {
    List<TasaPlazo> findByTasaInteres(TasaInteres tasaInteres);
    List<TasaPlazo> findByTasaInteres_IdTasaInteres(Integer idTasaInteres);
    List<TasaPlazo> findByPlazoMinimoBetweenOrPlazoMaximoBetween(
        Integer plazoMinimo1, Integer plazoMaximo1, 
        Integer plazoMinimo2, Integer plazoMaximo2);
}