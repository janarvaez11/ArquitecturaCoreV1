package com.banquito.core.aplicacion.cuentas.repositorio;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.banquito.core.aplicacion.cuentas.modelo.CuentaCliente;

@Repository
public interface CuentaClienteRepositorio extends JpaRepository<CuentaCliente, Integer> {
    //List<CuentaCliente> findByClienteId(Integer clienteId);
    List<CuentaCliente> findByCliente_idCliente(Integer clienteId);
    List<CuentaCliente> findByEstado(String estado);
    List<CuentaCliente> findBySaldoDisponibleGreaterThan(BigDecimal saldoMinimo);
    CuentaCliente findByNumeroCuenta(String numeroCuenta);
    boolean existsByNumeroCuentaAndClienteIdCliente(String numeroCuenta, Integer idCliente);
    CuentaCliente findByNumeroCuentaAndClienteIdCliente(String numeroCuenta, Integer idCliente);
}

