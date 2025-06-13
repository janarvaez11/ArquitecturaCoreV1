package com.banquito.core.aplicacion.prestamos.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.prestamos.modelo.Garantia;

@Repository
public interface GarantiaRepositorio extends JpaRepository<Garantia, Integer> {
    List<Garantia> findByTipoGarantia(String tipoGarantia);
    List<Garantia> findByTipoPrestamo_IdTipoPrestamo(Integer idTipoPrestamo);
}
