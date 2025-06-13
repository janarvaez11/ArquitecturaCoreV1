package com.banquito.core.aplicacion.cuentas.repositorio;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.TasaSaldo;

@Repository
public interface TasaSaldoRepositorio extends JpaRepository<TasaSaldo, Integer> {
    List<TasaSaldo> findBySaldoMinimoBetweenOrSaldoMaximoBetween(
        BigDecimal saldoMinimo1, BigDecimal saldoMinimo2,
        BigDecimal saldoMaximo1, BigDecimal saldoMaximo2);
    List<TasaSaldo> findByTasaGreaterThanEqual(BigDecimal tasaMinima);
    List<TasaSaldo> findByTasaLessThanEqual(BigDecimal tasaMaxima);
}
