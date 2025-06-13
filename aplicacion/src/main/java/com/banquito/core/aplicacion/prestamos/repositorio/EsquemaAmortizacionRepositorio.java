package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.aplicacion.prestamos.modelo.EsquemasAmortizacion;
import com.banquito.core.aplicacion.prestamos.modelo.TipoPrestamo;

public interface EsquemaAmortizacionRepositorio extends JpaRepository<EsquemasAmortizacion, Integer> {
    List<EsquemasAmortizacion> findByTipoPrestamo(TipoPrestamo tipoPrestamo);
}
