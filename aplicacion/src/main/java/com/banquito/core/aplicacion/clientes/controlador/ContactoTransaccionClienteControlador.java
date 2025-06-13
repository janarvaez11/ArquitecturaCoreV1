package com.banquito.core.aplicacion.clientes.controlador;

import com.banquito.core.aplicacion.clientes.modelo.ContactoTransaccionCliente;
import com.banquito.core.aplicacion.clientes.servicio.ContactoTransaccionClienteServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contactos")
public class ContactoTransaccionClienteControlador {
/*
    private final ContactoTransaccionClienteServicio servicio;

    public ContactoTransaccionClienteControlador(ContactoTransaccionClienteServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactoTransaccionCliente> obtenerPorId(@PathVariable Integer id) {
        try {
            ContactoTransaccionCliente contacto = servicio.buscarPorId(id);
            return ResponseEntity.ok(contacto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<ContactoTransaccionCliente>> listarTodos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "20") int tamanio) {
        try {
            Pageable pageable = PageRequest.of(pagina, tamanio);
            Page<ContactoTransaccionCliente> contactos = servicio.listarTodos(pageable);
            return ResponseEntity.ok(contactos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<ContactoTransaccionCliente> obtenerPorCliente(@PathVariable Integer idCliente) {
        try {
            ContactoTransaccionCliente contacto = servicio.obtenerPorCliente(idCliente);
            return ResponseEntity.ok(contacto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ContactoTransaccionCliente contacto) {
        try {
            ContactoTransaccionCliente nuevo = servicio.crear(contacto);
            return ResponseEntity.status(201).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear contacto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody ContactoTransaccionCliente contacto) {
        try {
            contacto.setIdContactoTransaccionCliente(id);
            ContactoTransaccionCliente actualizado = servicio.actualizar(contacto);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar contacto: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            servicio.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar contacto: " + e.getMessage());
        }
    }
         */
}
