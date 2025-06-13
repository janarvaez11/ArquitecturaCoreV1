package com.banquito.core.aplicacion.cuentas.servicio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.modelo.ExencionCuenta;
import com.banquito.core.aplicacion.cuentas.repositorio.ExencionCuentaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class ExencionCuentaServicio {

    private final ExencionCuentaRepositorio exencionCuentaRepositorio;

    public ExencionCuentaServicio(ExencionCuentaRepositorio exencionCuentaRepositorio) {
        this.exencionCuentaRepositorio = exencionCuentaRepositorio;
    }

    public List<ExencionCuenta> listarTodos() {
        List<ExencionCuenta> exenciones = this.exencionCuentaRepositorio.findAll();
        if (exenciones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ExencionCuenta", 
                "No existen exenciones de cuenta registradas");
        }
        return exenciones;
    }

    public Page<ExencionCuenta> listarTodosPaginado(Pageable pageable) {
        Page<ExencionCuenta> page = this.exencionCuentaRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ExencionCuenta", 
                "No existen exenciones de cuenta registradas");
        }
        return page;
    }

    public ExencionCuenta buscarPorId(Integer id) {
        return this.exencionCuentaRepositorio.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaExcepcion("ExencionCuenta", 
                "No se encontró la exención de cuenta con id: " + id));
    }

    @Transactional
    public ExencionCuenta crear(ExencionCuenta exencionCuenta) {
        try {
            validarExencionCuenta(exencionCuenta);
            validarNombreUnico(exencionCuenta.getNombre(), null);
            return this.exencionCuentaRepositorio.save(exencionCuenta);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("ExencionCuenta", 
                "Error al crear la exención de cuenta: " + e.getMessage());
        }
    }

    @Transactional
    public ExencionCuenta actualizar(Integer id, ExencionCuenta exencionCuenta) {
        try {
            ExencionCuenta existente = buscarPorId(id);
            validarExencionCuenta(exencionCuenta);
            validarNombreUnico(exencionCuenta.getNombre(), id);
            
            existente.setNombre(exencionCuenta.getNombre());
            existente.setDescripcion(exencionCuenta.getDescripcion());
            existente.setComision(exencionCuenta.getComision());
            
            return this.exencionCuentaRepositorio.save(existente);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("ExencionCuenta", 
                "Error al actualizar la exención de cuenta: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            ExencionCuenta exencionCuenta = buscarPorId(id);
            this.exencionCuentaRepositorio.delete(exencionCuenta);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("ExencionCuenta", 
                "Error al eliminar la exención de cuenta: " + e.getMessage());
        }
    }

    public List<ExencionCuenta> buscarPorNombreParcial(String nombre) {
        List<ExencionCuenta> exenciones = this.exencionCuentaRepositorio.findByNombreContainingIgnoreCase(nombre);
        if (exenciones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ExencionCuenta", 
                "No se encontraron exenciones con el nombre: " + nombre);
        }
        return exenciones;
    }

    public List<ExencionCuenta> buscarPorComision(Integer idComision) {
        List<ExencionCuenta> exenciones = this.exencionCuentaRepositorio.findByComision_IdComisionCargo(idComision);
        if (exenciones.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("ExencionCuenta", 
                "No se encontraron exenciones para la comisión: " + idComision);
        }
        return exenciones;
    }

    private void validarExencionCuenta(ExencionCuenta exencionCuenta) {
        if (exencionCuenta.getNombre() == null || exencionCuenta.getNombre().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ExencionCuenta", 
                "El nombre de la exención es obligatorio");
        }
        if (exencionCuenta.getDescripcion() == null || exencionCuenta.getDescripcion().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("ExencionCuenta", 
                "La descripción de la exención es obligatoria");
        }
        if (exencionCuenta.getComision() == null) {
            throw new CrearEntidadExcepcion("ExencionCuenta", 
                "La comisión asociada es obligatoria");
        }
    }

    private void validarNombreUnico(String nombre, Integer idExcluir) {
        ExencionCuenta existente = this.exencionCuentaRepositorio.findByNombre(nombre);
        if (existente != null && (idExcluir == null || !idExcluir.equals(existente.getIdExencion()))) {
            throw new CrearEntidadExcepcion("ExencionCuenta", 
                "Ya existe una exención con el nombre: " + nombre);
        }
    }
}
