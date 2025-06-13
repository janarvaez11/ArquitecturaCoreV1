package com.banquito.core.aplicacion.prestamos.controlador;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.PrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.Prestamo;
import com.banquito.core.aplicacion.prestamos.servicio.PrestamoServicio;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoControlador {

    private final PrestamoServicio prestamoServicio;

    public PrestamoControlador(PrestamoServicio prestamoServicio) {
        this.prestamoServicio = prestamoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Prestamo prestamo = prestamoServicio.findById(id);
            return ResponseEntity.ok(prestamo);
        } catch (PrestamoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable String estado) {
        try {
            List<Prestamo> prestamos = prestamoServicio.findByEstado(estado);
            return ResponseEntity.ok(prestamos);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Prestamo prestamo) {
        try {
            prestamoServicio.create(prestamo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Préstamo creado exitosamente");
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Prestamo prestamo) {
        try {
            prestamo.setId(id);
            prestamoServicio.update(prestamo);
            return ResponseEntity.ok("Préstamo actualizado exitosamente");
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar: " + e.getMessage());
        } catch (PrestamoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            prestamoServicio.delete(id);
            return ResponseEntity.ok("Préstamo eliminado exitosamente");
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar: " + e.getMessage());
        } catch (PrestamoNoEncontradoExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }
}