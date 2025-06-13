package com.banquito.core.aplicacion.cuentas.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.modelo.TasaInteres;
import com.banquito.core.aplicacion.cuentas.repositorio.TasaInteresRepositorio;

import jakarta.transaction.Transactional;

@Service
public class TasaInteresServicio {

    private static final String ESTADO_ACTIVO = "ACTIVA";
    private static final String ESTADO_INACTIVO = "INACTIVA";

    private final TasaInteresRepositorio tasaInteresRepositorio;

    public TasaInteresServicio(TasaInteresRepositorio tasaInteresRepositorio) {
        this.tasaInteresRepositorio = tasaInteresRepositorio;
    }

    public List<TasaInteres> listarTodos() {
        List<TasaInteres> tasas = this.tasaInteresRepositorio.findAll();
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaInteres", 
                "No existen tasas de interés registradas");
        }
        return tasas;
    }

    public Page<TasaInteres> listarTodosPaginado(Pageable pageable) {
        Page<TasaInteres> page = this.tasaInteresRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaInteres", 
                "No existen tasas de interés registradas");
        }
        return page;
    }

    public TasaInteres buscarPorId(Integer id) {
        return this.tasaInteresRepositorio.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaExcepcion("TasaInteres", 
                "No se encontró la tasa de interés con id: " + id));
    }

    public List<TasaInteres> buscarPorTipoCuenta(Integer idTipoCuenta) {
        List<TasaInteres> tasas = this.tasaInteresRepositorio.findByTipoCuentaId(idTipoCuenta);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaInteres", 
                "No se encontraron tasas de interés para el tipo de cuenta con id: " + idTipoCuenta);
        }
        return tasas;
    }

    @Transactional
    public TasaInteres crear(TasaInteres tasaInteres) {
        try {
            validarTasaInteres(tasaInteres);
            tasaInteres.setEstado(ESTADO_ACTIVO);
            return this.tasaInteresRepositorio.save(tasaInteres);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("TasaInteres", 
                "Error al crear la tasa de interés: " + e.getMessage());
        }
    }

    @Transactional
    public TasaInteres actualizar(Integer id, TasaInteres tasaInteres) {
        try {
            TasaInteres tasaExistente = buscarPorId(id);
            validarTasaInteres(tasaInteres);
            
            tasaExistente.setBaseCalculo(tasaInteres.getBaseCalculo());
            tasaExistente.setMetodoCalculo(tasaInteres.getMetodoCalculo());
            tasaExistente.setFrecuenciaCapitalizacion(tasaInteres.getFrecuenciaCapitalizacion());
            tasaExistente.setEstado(tasaInteres.getEstado());
            tasaExistente.setFechaInicioVigencia(tasaInteres.getFechaInicioVigencia());
            tasaExistente.setFechaFinVigencia(tasaInteres.getFechaFinVigencia());
            
            return this.tasaInteresRepositorio.save(tasaExistente);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("TasaInteres", 
                "Error al actualizar la tasa de interés: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            TasaInteres tasaInteres = buscarPorId(id);
            if (ESTADO_ACTIVO.equals(tasaInteres.getEstado())) {
                throw new EliminarEntidadExcepcion("TasaInteres", 
                    "No se puede eliminar una tasa de interés activa");
            }
            this.tasaInteresRepositorio.delete(tasaInteres);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("TasaInteres", 
                "Error al eliminar la tasa de interés: " + e.getMessage());
        }
    }

    public List<TasaInteres> buscarTasasVigentes() {
        List<TasaInteres> tasas = this.tasaInteresRepositorio.findByEstado(ESTADO_ACTIVO);
        if (tasas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("TasaInteres", 
                "No existen tasas de interés vigentes");
        }
        return tasas;
    }

    private void validarTasaInteres(TasaInteres tasaInteres) {
        if (tasaInteres.getBaseCalculo() == null || tasaInteres.getBaseCalculo().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TasaInteres", "La base de cálculo es obligatoria");
        }
        if (tasaInteres.getMetodoCalculo() == null || tasaInteres.getMetodoCalculo().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TasaInteres", "El método de cálculo es obligatorio");
        }
        if (tasaInteres.getFrecuenciaCapitalizacion() == null || 
            tasaInteres.getFrecuenciaCapitalizacion().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TasaInteres", "La frecuencia de capitalización es obligatoria");
        }
        if (tasaInteres.getEstado() == null || tasaInteres.getEstado().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("TasaInteres", "El estado es obligatorio");
        }
        validarEstado(tasaInteres.getEstado());
        if (tasaInteres.getFechaInicioVigencia() == null) {
            throw new CrearEntidadExcepcion("TasaInteres", "La fecha de inicio de vigencia es obligatoria");
        }
        if (tasaInteres.getFechaFinVigencia() == null) {
            throw new CrearEntidadExcepcion("TasaInteres", "La fecha de fin de vigencia es obligatoria");
        }
        if (tasaInteres.getFechaInicioVigencia().after(tasaInteres.getFechaFinVigencia())) {
            throw new CrearEntidadExcepcion("TasaInteres", 
                "La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }

    private void validarEstado(String estado) {
        if (!ESTADO_ACTIVO.equals(estado) && !ESTADO_INACTIVO.equals(estado)) {
            throw new CrearEntidadExcepcion("TasaInteres", 
                "El estado debe ser: " + ESTADO_ACTIVO + " o " + ESTADO_INACTIVO);
        }
    }
}
