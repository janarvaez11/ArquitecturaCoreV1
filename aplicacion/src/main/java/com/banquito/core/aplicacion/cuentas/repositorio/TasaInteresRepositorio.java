package com.banquito.core.aplicacion.cuentas.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.banquito.core.aplicacion.cuentas.modelo.TasaInteres;

@Repository
public interface TasaInteresRepositorio extends JpaRepository<TasaInteres, Integer> {
    
    @Query("SELECT t FROM TasaInteres t JOIN t.cuentas c WHERE c.tipoCuenta.idTipoCuenta = :idTipoCuenta")
    List<TasaInteres> findByTipoCuentaId(@Param("idTipoCuenta") Integer idTipoCuenta);
    
    List<TasaInteres> findByEstado(String estado);
}