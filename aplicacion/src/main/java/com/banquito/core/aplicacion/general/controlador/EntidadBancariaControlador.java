package com.banquito.core.aplicacion.general.controlador;

import com.banquito.core.aplicacion.general.excepcion.ActualizarEntidadException;
import com.banquito.core.aplicacion.general.excepcion.CrearEntidadException;
import com.banquito.core.aplicacion.general.excepcion.EntidadBancariaNoEncontradaException;
import com.banquito.core.aplicacion.general.modelo.EntidadBancaria;
import com.banquito.core.aplicacion.general.servicio.EntidadBancariaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entidadesbancarias")
public class EntidadBancariaControlador {
    private final EntidadBancariaServicio entidadBancariaServicio;

    public EntidadBancariaControlador(EntidadBancariaServicio entidadBancariaServicio) {
        this.entidadBancariaServicio = entidadBancariaServicio;
    }

    @PostMapping
    public ResponseEntity<EntidadBancaria> create(@RequestBody EntidadBancaria entidadBancaria) {
        try{
            this.entidadBancariaServicio.crearEntidadBancaria(entidadBancaria);
            return ResponseEntity.ok().build();
        }catch (CrearEntidadException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadBancaria> findById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(this.entidadBancariaServicio.EncotrarporId(id));
        }catch (EntidadBancariaNoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EntidadBancaria>> findAll() {
        try {
            return ResponseEntity.ok(this.entidadBancariaServicio.ListarEntidadesBancarias());
        } catch (EntidadBancariaNoEncontradaException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<EntidadBancaria> update(@RequestBody EntidadBancaria entidadBancaria) {
        try {
            this.entidadBancariaServicio.actualizarEntidadBancaria(entidadBancaria);
            return ResponseEntity.noContent().build();
        } catch (ActualizarEntidadException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntidadBancaria> delete(@PathVariable Integer id) {
        try {
            this.entidadBancariaServicio.eliminarEntidadBancaria(id);
            return ResponseEntity.ok().build();
        } catch (EntidadBancariaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
