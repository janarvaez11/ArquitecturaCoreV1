package com.banquito.core.aplicacion.cuentas.controlador;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.cuentas.modelo.TasaPlazo;
import com.banquito.core.aplicacion.cuentas.servicio.TasaPlazoServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

@RestController
@RequestMapping("/api/tasas-plazo")
@CrossOrigin(maxAge = 3600)
public class TasaPlazoControlador {

    private final TasaPlazoServicio tasaPlazoServicio;

    public TasaPlazoControlador(TasaPlazoServicio tasaPlazoServicio) {
        this.tasaPlazoServicio = tasaPlazoServicio;
    }

    @GetMapping
    public ResponseEntity<List<TasaPlazo>> listarTodos() {
        try {
            List<TasaPlazo> tasas = tasaPlazoServicio.listarTodos();
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<TasaPlazo>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<TasaPlazo> tasas = tasaPlazoServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasaPlazo> buscarPorId(@PathVariable Integer id) {
        try {
            TasaPlazo tasa = tasaPlazoServicio.buscarPorId(id);
            return ResponseEntity.ok(tasa);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasaPlazo> crear(@RequestBody TasaPlazo tasaPlazo) {
        try {
            TasaPlazo nuevaTasa = tasaPlazoServicio.crear(tasaPlazo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTasa);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasaPlazo> actualizar(
            @PathVariable Integer id,
            @RequestBody TasaPlazo tasaPlazo) {
        try {
            TasaPlazo tasaActualizada = tasaPlazoServicio.actualizar(id, tasaPlazo);
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
            tasaPlazoServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/rango-plazo")
    public ResponseEntity<List<TasaPlazo>> buscarPorRangoPlazo(
            @RequestParam Integer plazoMinimo,
            @RequestParam Integer plazoMaximo) {
        try {
            List<TasaPlazo> tasas = tasaPlazoServicio.buscarPorRangoPlazo(plazoMinimo, plazoMaximo);
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/tasa-interes/{idTasaInteres}")
    public ResponseEntity<List<TasaPlazo>> buscarPorTasaInteres(
            @PathVariable Integer idTasaInteres) {
        try {
            List<TasaPlazo> tasas = tasaPlazoServicio.buscarPorTasaInteres(idTasaInteres);
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
}
