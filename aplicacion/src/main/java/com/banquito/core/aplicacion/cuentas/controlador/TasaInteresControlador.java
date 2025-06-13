package com.banquito.core.aplicacion.cuentas.controlador;

import com.banquito.core.aplicacion.cuentas.modelo.TasaInteres;
import com.banquito.core.aplicacion.cuentas.servicio.TasaInteresServicio;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasas-interes")
@CrossOrigin(maxAge = 3600)
public class TasaInteresControlador {

    private final TasaInteresServicio tasaInteresServicio;

    public TasaInteresControlador(TasaInteresServicio tasaInteresServicio) {
        this.tasaInteresServicio = tasaInteresServicio;
    }

    @GetMapping
    public ResponseEntity<List<TasaInteres>> listarTodos() {
        try {
            List<TasaInteres> tasas = tasaInteresServicio.listarTodos();
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<TasaInteres>> listarTodosPaginado(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanio) {
        try {
            Page<TasaInteres> tasas = tasaInteresServicio.listarTodosPaginado(PageRequest.of(pagina, tamanio));
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasaInteres> buscarPorId(@PathVariable Integer id) {
        try {
            TasaInteres tasa = tasaInteresServicio.buscarPorId(id);
            return ResponseEntity.ok(tasa);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo-cuenta/{idTipoCuenta}")
    public ResponseEntity<List<TasaInteres>> buscarPorTipoCuenta(@PathVariable Integer idTipoCuenta) {
        try {
            List<TasaInteres> tasas = tasaInteresServicio.buscarPorTipoCuenta(idTipoCuenta);
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TasaInteres> crear(@RequestBody TasaInteres tasaInteres) {
        try {
            TasaInteres nuevaTasa = tasaInteresServicio.crear(tasaInteres);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaTasa);
        } catch (CrearEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TasaInteres> actualizar(
            @PathVariable Integer id, 
            @RequestBody TasaInteres tasaInteres) {
        try {
            TasaInteres tasaActualizada = tasaInteresServicio.actualizar(id, tasaInteres);
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
            tasaInteresServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        } catch (EliminarEntidadExcepcion e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<TasaInteres>> buscarTasasVigentes() {
        try {
            List<TasaInteres> tasas = tasaInteresServicio.buscarTasasVigentes();
            return ResponseEntity.ok(tasas);
        } catch (EntidadNoEncontradaExcepcion e) {
            return ResponseEntity.notFound().build();
        }
    }
}