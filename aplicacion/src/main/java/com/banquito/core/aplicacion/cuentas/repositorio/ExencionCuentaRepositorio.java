package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.ExencionCuenta;

@Repository
public interface ExencionCuentaRepositorio extends JpaRepository<ExencionCuenta, Integer> {
    List<ExencionCuenta> findByNombreContainingIgnoreCase(String nombre);
    List<ExencionCuenta> findByComision_IdComisionCargo(Integer idComision);
    ExencionCuenta findByNombre(String nombre);
}
