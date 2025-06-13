package com.banquito.core.aplicacion.general.controlador;

import com.banquito.core.aplicacion.general.servicio.PaisServicio;
import org.springframework.http.ResponseEntity;
import com.banquito.core.aplicacion.general.modelo.Pais;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/paises")
public class PaisControlador {

    private final PaisServicio paisServicio;

    public PaisControlador(PaisServicio paisServicio) {
        this.paisServicio = paisServicio;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(paisServicio.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarTodos() {
        return ResponseEntity.ok(paisServicio.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Map<String, Object> paisData) {
        Pais pais = new Pais();
        pais.setId((String) paisData.get("id"));
        pais.setNombre((String) paisData.get("nombre"));
        pais.setCodigoTelefono((String) paisData.get("codigoTelefono"));
        
        paisServicio.crearPais(pais);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizar(
            @PathVariable String id, 
            @RequestBody Map<String, Object> paisData) {
        Pais pais = new Pais();
        pais.setId(id);
        pais.setNombre((String) paisData.get("nombre"));
        pais.setCodigoTelefono((String) paisData.get("codigoTelefono"));
        
        paisServicio.actualizarPais(id, pais);
        return ResponseEntity.noContent().build();
    }
}