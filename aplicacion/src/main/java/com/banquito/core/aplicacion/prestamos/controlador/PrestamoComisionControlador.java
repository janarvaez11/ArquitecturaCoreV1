package com.banquito.core.aplicacion.prestamos.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
//import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.PrestamoComisionCargoExepcion;
import com.banquito.core.aplicacion.prestamos.modelo.PrestamoComisionCargo;
import com.banquito.core.aplicacion.prestamos.modelo.PrestamoComisionCargoId;
import com.banquito.core.aplicacion.prestamos.servicio.PrestamoComisionCargoServicio;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/prestamo-comision")
public class PrestamoComisionControlador {

    private final PrestamoComisionCargoServicio servicio;

    public PrestamoComisionControlador(PrestamoComisionCargoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{idPrestamo}/{idComisionPrestamo}")
    public ResponseEntity<PrestamoComisionCargo> obtenerPorId(
            @PathVariable Integer idPrestamo,
            @PathVariable Integer idComisionPrestamo) {
        try {
            PrestamoComisionCargoId id = new PrestamoComisionCargoId(idPrestamo, idComisionPrestamo);
            PrestamoComisionCargo prestamoComisionCargo = servicio.findById(id);
            return ResponseEntity.ok(prestamoComisionCargo);
        } catch (PrestamoComisionCargoExepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@RequestBody PrestamoComisionCargo prestamoComisionCargo) {
        try {
            if (prestamoComisionCargo.getId() == null || 
                prestamoComisionCargo.getId().getIdPrestamo() == null || 
                prestamoComisionCargo.getId().getIdComisionPrestamo() == null) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Se requieren los IDs de préstamo y comisión");
                return ResponseEntity.badRequest().body(error);
            }

            if (prestamoComisionCargo.getFechaAsignacion() == null) {
                prestamoComisionCargo.setFechaAsignacion(LocalDateTime.now());
            }

            servicio.create(prestamoComisionCargo);
            return ResponseEntity.ok(prestamoComisionCargo);
        } catch (CrearEntidadExcepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    /*
    @PutMapping("/{idPrestamo}/{idComisionPrestamo}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer idPrestamo,
            @PathVariable Integer idComisionPrestamo,
            @RequestBody PrestamoComisionCargo prestamoComisionCargo) {
        try {
            PrestamoComisionCargoId id = new PrestamoComisionCargoId(idPrestamo, idComisionPrestamo);
            prestamoComisionCargo.setId(id);
            servicio.update(prestamoComisionCargo);
            return ResponseEntity.ok(prestamoComisionCargo);
        } catch (ActualizarEntidadExcepcion | PrestamoComisionCargoExepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    */

    /*
    @DeleteMapping("/{idPrestamo}/{idComisionPrestamo}")
    public ResponseEntity<?> eliminar(
            @PathVariable Integer idPrestamo,
            @PathVariable Integer idComisionPrestamo) {
        try {
            PrestamoComisionCargoId id = new PrestamoComisionCargoId(idPrestamo, idComisionPrestamo);
            servicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion | PrestamoComisionCargoExepcion e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    */
} 