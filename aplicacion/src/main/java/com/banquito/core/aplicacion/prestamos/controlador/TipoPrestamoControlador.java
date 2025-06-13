package com.banquito.core.aplicacion.prestamos.controlador;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.TipoPrestamo;
import com.banquito.core.aplicacion.prestamos.servicio.TipoPrestamosServicio;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/tipos-prestamos")
public class TipoPrestamoControlador {

    private final TipoPrestamosServicio tipoPrestamosServicio;

    public TipoPrestamoControlador(TipoPrestamosServicio tipoPrestamosServicio) {
        this.tipoPrestamosServicio = tipoPrestamosServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            TipoPrestamo tipoPrestamo = tipoPrestamosServicio.findById(id);
            return ResponseEntity.ok(tipoPrestamo);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> obtenerPorEstado(@PathVariable String estado) {
        try {
            List<TipoPrestamo> tiposPrestamo = tipoPrestamosServicio.findByEstado(estado);
            return ResponseEntity.ok(tiposPrestamo);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/tipoCliente/{tipoCliente}")
    public ResponseEntity<?> obtenerPorTipoCliente(@PathVariable String tipoCliente) {
        try {
            List<TipoPrestamo> tiposPrestamo = tipoPrestamosServicio.findByTipoCliente(tipoCliente);
            return ResponseEntity.ok(tiposPrestamo);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> crear(@RequestBody TipoPrestamo tipoPrestamo) {
        try {
            tipoPrestamosServicio.create(tipoPrestamo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody TipoPrestamo tipoPrestamo) {
        try {
            tipoPrestamo.setIdTipoPrestamo(id);
            tipoPrestamosServicio.update(tipoPrestamo);
            return ResponseEntity.ok().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            tipoPrestamosServicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al eliminar: " + e.getMessage());
        }
    }
}