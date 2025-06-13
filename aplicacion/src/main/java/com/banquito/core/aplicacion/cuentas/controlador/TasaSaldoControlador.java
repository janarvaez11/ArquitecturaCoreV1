package com.banquito.core.aplicacion.cuentas.controlador;

import java.util.List;
import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.cuentas.modelo.TasaSaldo;
import com.banquito.core.aplicacion.cuentas.servicio.TasaSaldoServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/tasas-saldo")
@CrossOrigin(maxAge = 3600)
public class TasaSaldoControlador {
    /*

    private final TasaSaldoServicio tasaSaldoServicio;

    public TasaSaldoControlador(TasaSaldoServicio tasaSaldoServicio) {
        this.tasaSaldoServicio = tasaSaldoServicio;
    }

    @GetMapping
    public ResponseEntity<List<TasaSaldo>> listarTodos() {
        try {
            List<TasaSaldo> tasas = tasaSaldoServicio.listarTodos();
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<TasaSaldo>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<TasaSaldo> tasas = tasaSaldoServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasaSaldo> buscarPorId(@PathVariable Integer id) {
        try {
            TasaSaldo tasa = tasaSaldoServicio.buscarPorId(id);
            return ResponseEntity.ok(tasa);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasaSaldo> crear(@RequestBody TasaSaldo tasaSaldo) {
        try {
            TasaSaldo nuevaTasa = tasaSaldoServicio.crear(tasaSaldo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTasa);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasaSaldo> actualizar(
            @PathVariable Integer id,
            @RequestBody TasaSaldo tasaSaldo) {
        try {
            TasaSaldo tasaActualizada = tasaSaldoServicio.actualizar(id, tasaSaldo);
            return ResponseEntity.ok(tasaActualizada);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (ActualizarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            tasaSaldoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rango")
    public ResponseEntity<List<TasaSaldo>> buscarPorRangoDeSaldo(
            @RequestParam BigDecimal saldoMinimo,
            @RequestParam BigDecimal saldoMaximo) {
        try {
            List<TasaSaldo> tasas = tasaSaldoServicio.buscarPorRangoDeSaldo(saldoMinimo, saldoMaximo);
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tasa-minima/{tasaMinima}")
    public ResponseEntity<List<TasaSaldo>> buscarPorTasaMayorQue(
            @PathVariable BigDecimal tasaMinima) {
        try {
            List<TasaSaldo> tasas = tasaSaldoServicio.buscarPorTasaMayorQue(tasaMinima);
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tasa-maxima/{tasaMaxima}")
    public ResponseEntity<List<TasaSaldo>> buscarPorTasaMenorQue(
            @PathVariable BigDecimal tasaMaxima) {
        try {
            List<TasaSaldo> tasas = tasaSaldoServicio.buscarPorTasaMenorQue(tasaMaxima);
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
    */
}