package com.banquito.core.aplicacion.prestamos.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.prestamos.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.BusquedaExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.ComisionPrestamoNoEncontradoExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.prestamos.modelo.ComisionPrestamo;
import com.banquito.core.aplicacion.prestamos.servicio.ComisionPrestamoServicio;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/comisionesPrestamos")
public class ComisionPrestamoControlador {

    private final ComisionPrestamoServicio comisionPrestamoServicio;

    public ComisionPrestamoControlador(ComisionPrestamoServicio comisionPrestamoServicio) {
        this.comisionPrestamoServicio = comisionPrestamoServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComisionPrestamo> obtenerPorId(@PathVariable Integer id) {
        try {
            ComisionPrestamo comision = comisionPrestamoServicio.findById(id);
            return ResponseEntity.ok(comision);
        } catch (ComisionPrestamoNoEncontradoExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo/{tipoComision}")
    public ResponseEntity<List<ComisionPrestamo>> obtenerPorTipoComision(@PathVariable String tipoComision) {
        try {
            List<ComisionPrestamo> comisiones = comisionPrestamoServicio.findByTipoComision(tipoComision);
            return ResponseEntity.ok(comisiones);
        } catch (BusquedaExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> crear(@RequestBody ComisionPrestamo comisionPrestamo) {
        try {
            comisionPrestamoServicio.create(comisionPrestamo);
            return ResponseEntity.ok().build();
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody ComisionPrestamo comisionPrestamo) {
        try {
            comisionPrestamo.setId(id);
            comisionPrestamoServicio.update(comisionPrestamo);
            return ResponseEntity.ok().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            comisionPrestamoServicio.delete(id);
            return ResponseEntity.ok().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
