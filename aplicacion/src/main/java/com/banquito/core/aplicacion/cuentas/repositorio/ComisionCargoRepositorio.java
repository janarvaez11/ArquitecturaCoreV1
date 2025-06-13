package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.ComisionCargo;

@Repository
public interface ComisionCargoRepositorio extends JpaRepository<ComisionCargo, Integer> {
    //List<ComisionCargo> findByTipoCuenta_IdTipoCuenta(Integer idTipoCuenta);
    List<ComisionCargo> findByTipoComision(String tipoComision);
    List<ComisionCargo> findByEstado(String estado);
}
