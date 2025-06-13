package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.modelo.Accionista;
import com.banquito.core.aplicacion.clientes.servicio.AccionistaServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/accionistas")
public class AccionistaControlador {

    private final AccionistaServicio accionistaServicio;

    public AccionistaControlador(AccionistaServicio accionistaServicio) {
        this.accionistaServicio = accionistaServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accionista> obtenerPorId(@PathVariable Integer id) {
        try {
            Accionista accionista = accionistaServicio.buscarPorId(id);
            return ResponseEntity.ok(accionista);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Accionista>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            Page<Accionista> accionistas = accionistaServicio.buscarTodos(pageable);
            return ResponseEntity.ok(accionistas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<?> obtenerPorEmpresa(@PathVariable Integer idEmpresa) {
        try {
            return ResponseEntity.ok(accionistaServicio.obtenerPorEmpresa(idEmpresa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Accionista accionista) {
        try {
            Accionista nuevo = accionistaServicio.crear(accionista);
            return ResponseEntity.status(201).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear accionista: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Accionista accionista) {
        try {
            accionista.setIdAccionista(id);
            Accionista actualizado = accionistaServicio.actualizar(accionista);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar accionista: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            accionistaServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar accionista: " + e.getMessage());
        }
    }
}
