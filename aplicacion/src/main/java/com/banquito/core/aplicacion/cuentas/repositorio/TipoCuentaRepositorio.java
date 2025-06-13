package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.TipoCuenta;

@Repository
public interface TipoCuentaRepositorio extends JpaRepository<TipoCuenta, Integer> {
    List<TipoCuenta> findByNombreContainingIgnoreCase(String nombre);
    List<TipoCuenta> findByMonedaId(String idMoneda);
    List<TipoCuenta> findByTipoCliente(String tipoCliente);
}


