package com.banquito.core.aplicacion.cuentas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.ServicioTipoCuenta;
import com.banquito.core.aplicacion.cuentas.modelo.ServicioTipoCuentaId;

@Repository
public interface ServicioTipoCuentaRepositorio extends JpaRepository<ServicioTipoCuenta, ServicioTipoCuentaId> {
} 