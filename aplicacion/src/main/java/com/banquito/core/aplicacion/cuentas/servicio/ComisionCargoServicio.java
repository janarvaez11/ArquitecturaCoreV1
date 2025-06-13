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
import com.banquito.core.aplicacion.cuentas.modelo.ComisionCargo;
import com.banquito.core.aplicacion.cuentas.repositorio.ComisionCargoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ComisionCargoServicio {

    private static final String ESTADO_ACTIVO = "ACT";
    private static final String ESTADO_INACTIVO = "INA";

    private final ComisionCargoRepositorio comisionCargoRepositorio;

    public ComisionCargoServicio(ComisionCargoRepositorio comisionCargoRepositorio) {
        this.comisionCargoRepositorio = comisionCargoRepositorio;
    }

    public List<ComisionCargo> listarTodos() {
        List<ComisionCargo> comisiones = this.comisionCargoRepositorio.findAll();
        if (comisiones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ComisionCargo", 
                "No existen comisiones/cargos registrados");
        }
        return comisiones;
    }

    public Page<ComisionCargo> listarTodosPaginado(Pageable pageable) {
        Page<ComisionCargo> page = this.comisionCargoRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ComisionCargo", 
                "No existen comisiones/cargos registrados");
        }
        return page;
    }

    public ComisionCargo buscarPorId(Integer id) {
        return this.comisionCargoRepositorio.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaExcepcion("ComisionCargo", 
                "No se encontró la comisión/cargo con ID: " + id));
    }

    @Transactional
    public ComisionCargo crear(ComisionCargo comisionCargo) {
        try {
            validarComisionCargo(comisionCargo);
            validarNombreUnico(comisionCargo.getNombre(), null);
            comisionCargo.setEstado(ESTADO_ACTIVO);
            return this.comisionCargoRepositorio.save(comisionCargo);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("ComisionCargo", 
                "Error al crear la comisión/cargo: " + e.getMessage());
        }
    }

    @Transactional
    public ComisionCargo actualizar(Integer id, ComisionCargo comisionCargo) {
        try {
            ComisionCargo comisionExistente = buscarPorId(id);
            validarComisionCargo(comisionCargo);
            validarNombreUnico(comisionCargo.getNombre(), id);
            
            comisionExistente.setNombre(comisionCargo.getNombre());
            comisionExistente.setTipoComision(comisionCargo.getTipoComision());
            comisionExistente.setBaseCalculo(comisionCargo.getBaseCalculo());
            comisionExistente.setMonto(comisionCargo.getMonto());
            comisionExistente.setFrecuencia(comisionCargo.getFrecuencia());
            comisionExistente.setEstado(comisionCargo.getEstado());
            comisionExistente.setServicioAsociado(comisionCargo.getServicioAsociado());
            
            return this.comisionCargoRepositorio.save(comisionExistente);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("ComisionCargo", 
                "Error al actualizar la comisión/cargo: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            ComisionCargo comisionCargo = buscarPorId(id);
            if (ESTADO_ACTIVO.equals(comisionCargo.getEstado())) {
                throw new EliminarEntidadExcepcion("ComisionCargo", 
                    "No se puede eliminar una comisión/cargo en estado activo");
            }
            this.comisionCargoRepositorio.delete(comisionCargo);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("ComisionCargo", 
                "Error al eliminar la comisión/cargo: " + e.getMessage());
        }
    }

    /* 
    public List<ComisionCargo> buscarPorTipoCuenta(Integer idTipoCuenta) {
        List<ComisionCargo> comisiones = this.comisionCargoRepositorio.findByTipoCuenta_IdTipoCuenta(idTipoCuenta);
        if (comisiones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ComisionCargo", 
                "No se encontraron comisiones/cargos para el tipo de cuenta: " + idTipoCuenta);
        }
        return comisiones;
    }
        */

    public List<ComisionCargo> buscarPorTipoComision(String tipoComision) {
        List<ComisionCargo> comisiones = this.comisionCargoRepositorio.findByTipoComision(tipoComision);
        if (comisiones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ComisionCargo", 
                "No se encontraron comisiones/cargos del tipo: " + tipoComision);
        }
        return comisiones;
    }

    public List<ComisionCargo> buscarComisionesVigentes() {
        List<ComisionCargo> comisiones = this.comisionCargoRepositorio.findByEstado(ESTADO_ACTIVO);
        if (comisiones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ComisionCargo", 
                "No existen comisiones/cargos vigentes");
        }
        return comisiones;
    }

    private void validarComisionCargo(ComisionCargo comisionCargo) {
        if (comisionCargo.getNombre() == null || comisionCargo.getNombre().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ComisionCargo", "El nombre es obligatorio");
        }
        if (comisionCargo.getTipoComision() == null || comisionCargo.getTipoComision().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ComisionCargo", "El tipo de comisión es obligatorio");
        }
        if (comisionCargo.getBaseCalculo() == null || comisionCargo.getBaseCalculo().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ComisionCargo", "La base de cálculo es obligatoria");
        }
        if (comisionCargo.getMonto() == null) {
            throw new CrearEntidadExcepcion("ComisionCargo", "El monto es obligatorio");
        }
        if (comisionCargo.getMonto().compareTo(BigDecimal.ZERO) < 0) {
            throw new CrearEntidadExcepcion("ComisionCargo", "El monto no puede ser negativo");
        }
        if (comisionCargo.getFrecuencia() == null || comisionCargo.getFrecuencia().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ComisionCargo", "La frecuencia es obligatoria");
        }
        if (comisionCargo.getEstado() == null || comisionCargo.getEstado().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ComisionCargo", "El estado es obligatorio");
        }
        validarEstado(comisionCargo.getEstado());
    }

    private void validarNombreUnico(String nombre, Integer idExcluir) {
        List<ComisionCargo> comisionesExistentes = this.comisionCargoRepositorio.findAll();
        for (ComisionCargo comision : comisionesExistentes) {
            if (comision.getNombre().equalsIgnoreCase(nombre) && 
                (idExcluir == null || !idExcluir.equals(comision.getIdComisionCargo()))) {
                throw new CrearEntidadExcepcion("ComisionCargo", 
                    "Ya existe una comisión/cargo con el nombre: " + nombre);
            }
        }
    }

    private void validarEstado(String estado) {
        if (!ESTADO_ACTIVO.equals(estado) && !ESTADO_INACTIVO.equals(estado)) {
            throw new CrearEntidadExcepcion("ComisionCargo", 
                "El estado debe ser: " + ESTADO_ACTIVO + " o " + ESTADO_INACTIVO);
        }
    }
}