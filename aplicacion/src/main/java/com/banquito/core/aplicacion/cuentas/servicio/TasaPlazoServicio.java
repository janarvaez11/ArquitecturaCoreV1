package com.banquito.core.aplicacion.cuentas.servicio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.modelo.TasaPlazo;
import com.banquito.core.aplicacion.cuentas.repositorio.TasaPlazoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class TasaPlazoServicio {

    private final TasaPlazoRepositorio tasaPlazoRepositorio;

    public TasaPlazoServicio(TasaPlazoRepositorio tasaPlazoRepositorio) {
        this.tasaPlazoRepositorio = tasaPlazoRepositorio;
    }

    public List<TasaPlazo> listarTodos() {
        List<TasaPlazo> tasas = this.tasaPlazoRepositorio.findAll();
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaPlazo", 
                "No existen tasas por plazo registradas");
        }
        return tasas;
    }

    public Page<TasaPlazo> listarTodosPaginado(Pageable pageable) {
        Page<TasaPlazo> page = this.tasaPlazoRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaPlazo", 
                "No existen tasas por plazo registradas");
        }
        return page;
    }

    public TasaPlazo buscarPorId(Integer id) {
        return this.tasaPlazoRepositorio.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaExcepcion("TasaPlazo", 
                "No se encontró la tasa por plazo con id: " + id));
    }

    @Transactional
    public TasaPlazo crear(TasaPlazo tasaPlazo) {
        try {
            validarTasaPlazo(tasaPlazo);
            return this.tasaPlazoRepositorio.save(tasaPlazo);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("TasaPlazo", 
                "Error al crear la tasa por plazo: " + e.getMessage());
        }
    }

    @Transactional
    public TasaPlazo actualizar(Integer id, TasaPlazo tasaPlazo) {
        try {
            TasaPlazo tasaExistente = buscarPorId(id);
            validarTasaPlazo(tasaPlazo);
            
            tasaExistente.setPlazoMinimo(tasaPlazo.getPlazoMinimo());
            tasaExistente.setPlazoMaximo(tasaPlazo.getPlazoMaximo());
            tasaExistente.setTasa(tasaPlazo.getTasa());
            tasaExistente.setTasaInteres(tasaPlazo.getTasaInteres());
            
            return this.tasaPlazoRepositorio.save(tasaExistente);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("TasaPlazo", 
                "Error al actualizar la tasa por plazo: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            TasaPlazo tasaPlazo = buscarPorId(id);
            this.tasaPlazoRepositorio.delete(tasaPlazo);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("TasaPlazo", 
                "Error al eliminar la tasa por plazo: " + e.getMessage());
        }
    }

    public List<TasaPlazo> buscarPorRangoPlazo(Integer plazoMinimo, Integer plazoMaximo) {
        List<TasaPlazo> tasas = this.tasaPlazoRepositorio.findByPlazoMinimoBetweenOrPlazoMaximoBetween(
            plazoMinimo, plazoMaximo, plazoMinimo, plazoMaximo);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaPlazo", 
                "No se encontraron tasas para el rango de plazo especificado");
        }
        return tasas;
    }

    public List<TasaPlazo> buscarPorTasaInteres(Integer idTasaInteres) {
        List<TasaPlazo> tasas = this.tasaPlazoRepositorio.findByTasaInteres_IdTasaInteres(idTasaInteres);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaPlazo", 
                "No se encontraron tasas para la tasa de interés: " + idTasaInteres);
        }
        return tasas;
    }
    

    private void validarTasaPlazo(TasaPlazo tasaPlazo) {
        if (tasaPlazo.getPlazoMinimo() == null || tasaPlazo.getPlazoMinimo() < 0) {
            throw new CrearEntidadExcepcion("TasaPlazo", 
                "El plazo mínimo debe ser un valor positivo");
        }
        if (tasaPlazo.getPlazoMaximo() == null || tasaPlazo.getPlazoMaximo() < 0) {
            throw new CrearEntidadExcepcion("TasaPlazo", 
                "El plazo máximo debe ser un valor positivo");
        }
        if (tasaPlazo.getPlazoMinimo() > tasaPlazo.getPlazoMaximo()) {
            throw new CrearEntidadExcepcion("TasaPlazo", 
                "El plazo mínimo no puede ser mayor que el plazo máximo");
        }
        if (tasaPlazo.getTasa() == null || tasaPlazo.getTasa().compareTo(BigDecimal.ZERO) < 0) {
            throw new CrearEntidadExcepcion("TasaPlazo", 
                "La tasa debe ser un valor positivo");
        }
        if (tasaPlazo.getTasaInteres() == null) {
            throw new CrearEntidadExcepcion("TasaPlazo", 
                "La tasa de interés es obligatoria");
        }
    }
}
