package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.modelo.Representante;
import com.banquito.core.aplicacion.clientes.servicio.RepresentanteServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/representantes")
public class RepresentanteControlador {
/*
    private final RepresentanteServicio representanteServicio;

    public RepresentanteControlador(RepresentanteServicio representanteServicio) {
        this.representanteServicio = representanteServicio;
    }

    @GetMapping("/{idEmpresa}/{idCliente}/{rol}")
    public ResponseEntity<Representante> obtenerPorId(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idCliente,
            @PathVariable String rol) {
        try {
            RepresentanteId id = new RepresentanteId(idEmpresa, idCliente, rol);
            Representante representante = representanteServicio.buscarPorId(id);
            return ResponseEntity.ok(representante);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<Representante>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            Page<Representante> representantes = representanteServicio.buscarTodos(pageable);
            return ResponseEntity.ok(representantes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/empresa/{idEmpresa}")
    public ResponseEntity<?> obtenerPorEmpresa(@PathVariable Integer idEmpresa) {
        try {
            return ResponseEntity.ok(representanteServicio.obtenerPorEmpresa(idEmpresa));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<?> obtenerPorCliente(@PathVariable Integer idCliente) {
        try {
            return ResponseEntity.ok(representanteServicio.obtenerPorCliente(idCliente));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Representante representante) {
        try {
            Representante nuevo = representanteServicio.crear(representante);
            return ResponseEntity.status(201).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear representante: " + e.getMessage());
        }
    }

    @PutMapping("/{idEmpresa}/{idCliente}/{rol}")
    public ResponseEntity<?> actualizar(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idCliente,
            @PathVariable String rol,
            @RequestBody Representante representante) {
        try {
            RepresentanteId id = new RepresentanteId(idEmpresa, idCliente, rol);
            representante.setId(id);
            Representante actualizado = representanteServicio.actualizar(representante);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar representante: " + e.getMessage());
        }
    }

    @PatchMapping("/{idEmpresa}/{idCliente}/{rol}/estado")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idCliente,
            @PathVariable String rol,
            @RequestParam String estado) {
        try {
            RepresentanteId id = new RepresentanteId(idEmpresa, idCliente, rol);
            Representante representante = representanteServicio.cambiarEstado(id, estado);
            return ResponseEntity.ok(representante);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cambiar estado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idEmpresa}/{idCliente}/{rol}")
    public ResponseEntity<?> eliminar(
            @PathVariable Integer idEmpresa,
            @PathVariable Integer idCliente,
            @PathVariable String rol) {
        try {
            RepresentanteId id = new RepresentanteId(idEmpresa, idCliente, rol);
            representanteServicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar representante: " + e.getMessage());
        }
    }
         */
}
