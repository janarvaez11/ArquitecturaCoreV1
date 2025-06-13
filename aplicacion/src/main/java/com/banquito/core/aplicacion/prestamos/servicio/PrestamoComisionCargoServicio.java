package com.banquito.core.aplicacion.prestamos.servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

//import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
//import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.PrestamoComisionCargoExepcion;
import com.banquito.core.aplicacion.prestamos.modelo.Prestamo;
import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;
import com.banquito.core.aplicacion.prestamos.modelo.PrestamoComisionCargo;
import com.banquito.core.aplicacion.prestamos.modelo.PrestamoComisionCargoId;
import com.banquito.core.aplicacion.prestamos.repositorio.PrestamoComisionCargoRepositorio;
import com.banquito.core.aplicacion.prestamos.repositorio.PrestamoRepositorio;
import com.banquito.core.aplicacion.prestamos.repositorio.ComisionPrestamoRepositorio;

import jakarta.transaction.Transactional;

@Service
public class PrestamoComisionCargoServicio {
    
    private final PrestamoComisionCargoRepositorio repositorio;
    private final PrestamoRepositorio prestamoRepositorio;
    private final ComisionPrestamoRepositorio comisionPrestamoRepositorio;

    public PrestamoComisionCargoServicio(
            PrestamoComisionCargoRepositorio repositorio,
            PrestamoRepositorio prestamoRepositorio,
            ComisionPrestamoRepositorio comisionPrestamoRepositorio) {
        this.repositorio = repositorio;
        this.prestamoRepositorio = prestamoRepositorio;
        this.comisionPrestamoRepositorio = comisionPrestamoRepositorio;
    }

    public PrestamoComisionCargo findById(PrestamoComisionCargoId id) {
        Optional<PrestamoComisionCargo> prestamoComisionCargoOpcional = this.repositorio.findById(id);
        if (prestamoComisionCargoOpcional.isPresent()) {
            return prestamoComisionCargoOpcional.get();
        } else {
            throw new PrestamoComisionCargoExepcion("PrestamoComisionCargo", 
                "Comisión de préstamo no encontrada con ID: " + id);
        }
    }

    @Transactional
    public void create(PrestamoComisionCargo prestamoComisionCargo) {
        try {
            // Validar que el préstamo existe
            Optional<Prestamo> prestamoOpcional = prestamoRepositorio.findById(prestamoComisionCargo.getId().getIdPrestamo());
            if (!prestamoOpcional.isPresent()) {
                throw new CrearEntidadExcepcion("PrestamoComisionCargo", 
                    "El préstamo con ID " + prestamoComisionCargo.getId().getIdPrestamo() + " no existe");
            }

            // Validar que la comisión existe
            Optional<ComisionPrestamo> comisionOpcional = comisionPrestamoRepositorio.findById(prestamoComisionCargo.getId().getIdComisionPrestamo());
            if (!comisionOpcional.isPresent()) {
                throw new CrearEntidadExcepcion("PrestamoComisionCargo", 
                    "La comisión con ID " + prestamoComisionCargo.getId().getIdComisionPrestamo() + " no existe");
            }

            // Validar que no existe ya esta combinación
            if (repositorio.existsById(prestamoComisionCargo.getId())) {
                throw new CrearEntidadExcepcion("PrestamoComisionCargo", 
                    "Ya existe una comisión asignada a este préstamo con estos IDs");
            }

            this.repositorio.save(prestamoComisionCargo);
        } catch (Exception rte) {
            throw new CrearEntidadExcepcion("PrestamoComisionCargo", 
                "Error al crear la comisión de préstamo. Texto del error: " + rte.getMessage());
        }
    }

    /*
    @Transactional
    public void update(PrestamoComisionCargo prestamoComisionCargo) {
        try {
            Optional<PrestamoComisionCargo> prestamoComisionCargoOpcional = 
                this.repositorio.findById(prestamoComisionCargo.getId());

            if (prestamoComisionCargoOpcional.isPresent()) {
                PrestamoComisionCargo prestamoComisionCargoDb = prestamoComisionCargoOpcional.get();
                prestamoComisionCargoDb.setFechaAsignacion(prestamoComisionCargo.getFechaAsignacion());
                this.repositorio.save(prestamoComisionCargoDb);
            } else {
                throw new PrestamoComisionCargoExepcion("PrestamoComisionCargo", 
                    "Error al actualizar la comisión de préstamo. No se encontró con ID: " + prestamoComisionCargo.getId());
            }
        } catch (Exception rte) {
            throw new ActualizarEntidadExcepcion("PrestamoComisionCargo", 
                "Error al actualizar la comisión de préstamo. Texto del error: " + rte.getMessage());
        }
    }
    */

    /*
    @Transactional
    public void delete(PrestamoComisionCargoId id) {
        try {
            Optional<PrestamoComisionCargo> prestamoComisionCargoOpcional = this.repositorio.findById(id);
            if (prestamoComisionCargoOpcional.isPresent()) {
                this.repositorio.delete(prestamoComisionCargoOpcional.get());
            } else {
                throw new PrestamoComisionCargoExepcion("PrestamoComisionCargo", 
                    "Error al eliminar la comisión de préstamo. No se encontró con ID: " + id);
            }
        } catch (Exception rte) {
            throw new EliminarEntidadExcepcion("PrestamoComisionCargo", 
                "Error al eliminar la comisión de préstamo. Texto del error: " + rte.getMessage());
        }
    }
    */
} 