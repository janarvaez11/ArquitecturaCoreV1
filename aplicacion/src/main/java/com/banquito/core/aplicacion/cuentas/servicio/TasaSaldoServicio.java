package com.banquito.core.aplicacion.cuentas.servicio;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.modelo.TasaSaldo;
import com.banquito.core.aplicacion.cuentas.repositorio.TasaSaldoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class TasaSaldoServicio {

    private final TasaSaldoRepositorio tasaSaldoRepositorio;

    public TasaSaldoServicio(TasaSaldoRepositorio tasaSaldoRepositorio) {
        this.tasaSaldoRepositorio = tasaSaldoRepositorio;
    }

    public List<TasaSaldo> listarTodos() {
        List<TasaSaldo> tasas = this.tasaSaldoRepositorio.findAll();
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaSaldo", 
                "No existen tasas por saldo registradas");
        }
        return tasas;
    }

    public Page<TasaSaldo> listarTodosPaginado(Pageable pageable) {
        Page<TasaSaldo> page = this.tasaSaldoRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaSaldo", 
                "No existen tasas por saldo registradas");
        }
        return page;
    }

    public TasaSaldo buscarPorId(Integer id) {
        return this.tasaSaldoRepositorio.findById(id)
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion("TasaSaldo", 
                    "No se encontró la tasa por saldo con ID: " + id));
    }

    @Transactional
    public TasaSaldo crear(TasaSaldo tasaSaldo) {
        try {
            validarTasaSaldo(tasaSaldo);
            validarRangosSaldo(tasaSaldo);
            return this.tasaSaldoRepositorio.save(tasaSaldo);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("TasaSaldo", 
                "Error al crear la tasa por saldo: " + e.getMessage());
        }
    }

    @Transactional
    public TasaSaldo actualizar(Integer id, TasaSaldo tasaSaldo) {
        try {
            TasaSaldo tasaSaldoDB = buscarPorId(id);
            validarTasaSaldo(tasaSaldo);
            validarRangosSaldo(tasaSaldo, id);
            
            tasaSaldoDB.setNombre(tasaSaldo.getNombre());
            tasaSaldoDB.setSaldoMinimo(tasaSaldo.getSaldoMinimo());
            tasaSaldoDB.setSaldoMaximo(tasaSaldo.getSaldoMaximo());
            tasaSaldoDB.setTasa(tasaSaldo.getTasa());
            tasaSaldoDB.setTasaInteres(tasaSaldo.getTasaInteres());
            
            return this.tasaSaldoRepositorio.save(tasaSaldoDB);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("TasaSaldo", 
                "Error al actualizar la tasa por saldo: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            TasaSaldo tasaSaldo = buscarPorId(id);
            this.tasaSaldoRepositorio.delete(tasaSaldo);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("TasaSaldo", 
                "Error al eliminar la tasa por saldo: " + e.getMessage());
        }
    }

    public List<TasaSaldo> buscarPorRangoDeSaldo(BigDecimal saldoMinimo, BigDecimal saldoMaximo) {
        List<TasaSaldo> tasas = this.tasaSaldoRepositorio.findBySaldoMinimoBetweenOrSaldoMaximoBetween(
            saldoMinimo, saldoMaximo, saldoMinimo, saldoMaximo);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaSaldo", 
                "No se encontraron tasas para el rango de saldo especificado");
        }
        return tasas;
    }

    public List<TasaSaldo> buscarPorTasaMayorQue(BigDecimal tasaMinima) {
        List<TasaSaldo> tasas = this.tasaSaldoRepositorio.findByTasaGreaterThanEqual(tasaMinima);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaSaldo", 
                "No se encontraron tasas mayores o iguales a: " + tasaMinima);
        }
        return tasas;
    }

    public List<TasaSaldo> buscarPorTasaMenorQue(BigDecimal tasaMaxima) {
        List<TasaSaldo> tasas = this.tasaSaldoRepositorio.findByTasaLessThanEqual(tasaMaxima);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaSaldo", 
                "No se encontraron tasas menores o iguales a: " + tasaMaxima);
        }
        return tasas;
    }

    private void validarTasaSaldo(TasaSaldo tasaSaldo) {
        if (tasaSaldo.getNombre() == null || tasaSaldo.getNombre().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TasaSaldo", "El nombre es obligatorio");
        }
        if (tasaSaldo.getSaldoMinimo() == null) {
            throw new CrearEntidadExcepcion("TasaSaldo", "El saldo mínimo es obligatorio");
        }
        if (tasaSaldo.getSaldoMaximo() == null) {
            throw new CrearEntidadExcepcion("TasaSaldo", "El saldo máximo es obligatorio");
        }
        if (tasaSaldo.getTasa() == null) {
            throw new CrearEntidadExcepcion("TasaSaldo", "La tasa es obligatoria");
        }
        if (tasaSaldo.getSaldoMinimo().compareTo(BigDecimal.ZERO) < 0) {
            throw new CrearEntidadExcepcion("TasaSaldo", "El saldo mínimo no puede ser negativo");
        }
        if (tasaSaldo.getSaldoMaximo().compareTo(tasaSaldo.getSaldoMinimo()) <= 0) {
            throw new CrearEntidadExcepcion("TasaSaldo", "El saldo máximo debe ser mayor al saldo mínimo");
        }
        if (tasaSaldo.getTasa().compareTo(BigDecimal.ZERO) < 0 || 
            tasaSaldo.getTasa().compareTo(new BigDecimal("100")) > 0) {
            throw new CrearEntidadExcepcion("TasaSaldo", "La tasa debe estar entre 0 y 100");
        }
        if (tasaSaldo.getTasaInteres() == null) {
            throw new CrearEntidadExcepcion("TasaSaldo", "La tasa de interés es obligatoria");
        }
    }

    private void validarRangosSaldo(TasaSaldo tasaSaldo) {
        validarRangosSaldo(tasaSaldo, null);
    }

    private void validarRangosSaldo(TasaSaldo tasaSaldo, Integer idExcluir) {
        List<TasaSaldo> tasasExistentes = this.tasaSaldoRepositorio.findAll();
        for (TasaSaldo tasa : tasasExistentes) {
            if (idExcluir != null && tasa.getIdSaldo().equals(idExcluir)) {
                continue;
            }
            if (hayTraslape(tasaSaldo.getSaldoMinimo(), tasaSaldo.getSaldoMaximo(),
                    tasa.getSaldoMinimo(), tasa.getSaldoMaximo())) {
                throw new CrearEntidadExcepcion("TasaSaldo", 
                    "El rango de saldo se traslapa con una tasa existente");
            }
        }
    }

    private boolean hayTraslape(BigDecimal min1, BigDecimal max1, BigDecimal min2, BigDecimal max2) {
        return min1.compareTo(max2) <= 0 && max1.compareTo(min2) >= 0;
    }
}
