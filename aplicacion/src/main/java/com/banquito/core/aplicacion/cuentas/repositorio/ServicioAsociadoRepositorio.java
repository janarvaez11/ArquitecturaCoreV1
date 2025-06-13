package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.ServicioAsociado;

@Repository
public interface ServicioAsociadoRepositorio extends JpaRepository<ServicioAsociado, Integer> {
    List<ServicioAsociado> findByNombreContaining(String nombre);
    List<ServicioAsociado> findByEstado(String estado);
    //List<ServicioAsociado> findByServicioTipoCuentas_IdCuenta(Integer idCuenta);
    //boolean existsByServicioTipoCuentas_Cuenta_IdCuentaAndIdServicio(Integer idCuenta, Integer idServicio);
}
