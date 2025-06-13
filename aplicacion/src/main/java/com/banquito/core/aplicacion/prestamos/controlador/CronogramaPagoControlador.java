package com.banquito.core.aplicacion.prestamos.controlador;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.aplicacion.prestamos.modelo.CronogramasPagos;
import com.banquito.core.aplicacion.prestamos.servicio.CronogramasPagosServicio;

@RestController
@RequestMapping("/api/v1/cronogramas-pagos")
public class CronogramaPagoControlador {

    private final CronogramasPagosServicio servicio;

    public CronogramaPagoControlador(CronogramasPagosServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CronogramasPagos> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<CronogramasPagos>> listarTodos() {
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody CronogramasPagos cronograma) {
        this.servicio.crear(cronograma);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(@PathVariable Integer id, @RequestBody CronogramasPagos cronograma) {
        cronograma.setId(id);
        this.servicio.actualizar(cronograma);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/prestamo/{idPrestamo}")
    public ResponseEntity<List<CronogramasPagos>> buscarPorPrestamo(@PathVariable Integer idPrestamo) {
        return ResponseEntity.ok(this.servicio.buscarPorPrestamo(idPrestamo));
    }

    @GetMapping("/prestamo/{idPrestamo}/estado/{estado}")
    public ResponseEntity<List<CronogramasPagos>> buscarPorPrestamoYEstado(
            @PathVariable Integer idPrestamo,
            @PathVariable String estado) {
        return ResponseEntity.ok(this.servicio.buscarPorPrestamoYEstado(idPrestamo, estado));
    }

}
