package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.Cuenta;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Integer> {
    List<Cuenta> findByTipoCuenta_IdTipoCuenta(Integer idTipoCuenta);
    List<Cuenta> findByEstado(String estado);
    List<Cuenta> findByTipoCuenta_IdTipoCuentaAndEstado(Integer idTipoCuenta, String estado);
    Cuenta findByCodigoCuenta(String codigoCuenta);
}