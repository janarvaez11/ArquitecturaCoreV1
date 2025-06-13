package com.banquito.core.aplicacion.prestamos.controlador;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.aplicacion.prestamos.modelo.PagosPrestamos;
import com.banquito.core.aplicacion.prestamos.servicio.PagosPrestamoServicio;

@RestController
@RequestMapping("/api/v1/pagos-prestamos")
public class PagoPrestamoControlador {

    private final PagosPrestamoServicio servicio;

    public PagoPrestamoControlador(PagosPrestamoServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagosPrestamos> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(this.servicio.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PagosPrestamos>> listarTodos() {
        return ResponseEntity.ok(this.servicio.buscarTodos());
    }

    @PostMapping("/regular")
    public ResponseEntity<Void> registrarPago(@RequestBody PagosPrestamos pago) {
        this.servicio.registrarPago(pago);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/anticipado")
    public ResponseEntity<Void> registrarPagoAnticipado(@RequestBody PagosPrestamos pago) {
        this.servicio.registrarPagoAnticipado(pago);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarPago(@PathVariable Integer id, @RequestBody PagosPrestamos pago) {
        pago.setId(id);
        this.servicio.actualizarPago(pago);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/prestamo/{idPrestamo}")
    public ResponseEntity<List<PagosPrestamos>> buscarPorPrestamo(@PathVariable Integer idPrestamo) {
        return ResponseEntity.ok(this.servicio.buscarPorPrestamo(idPrestamo));
    }

    @GetMapping("/prestamo/{idPrestamo}/fecha")
    public ResponseEntity<List<PagosPrestamos>> buscarPorPrestamoYRangoDeFechas(
            @PathVariable Integer idPrestamo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(this.servicio.buscarPorPrestamoYRangoDeFechas(idPrestamo, fechaInicio, fechaFin));
    }

    @GetMapping("/tipo/{tipoPago}")
    public ResponseEntity<List<PagosPrestamos>> buscarPorTipoPago(@PathVariable String tipoPago) {
        return ResponseEntity.ok(this.servicio.buscarPorTipoPago(tipoPago));
    }

    @GetMapping("/prestamo/{idPrestamo}/ultimos")
    public ResponseEntity<List<PagosPrestamos>> buscarUltimosPagos(@PathVariable Integer idPrestamo) {
        return ResponseEntity.ok(this.servicio.buscarUltimosPagosPorPrestamo(idPrestamo));
    }
}
