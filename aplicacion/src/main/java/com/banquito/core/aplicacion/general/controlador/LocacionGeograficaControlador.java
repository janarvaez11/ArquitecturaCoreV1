package com.banquito.core.aplicacion.general.controlador;

import com.banquito.core.aplicacion.general.servicio.LocacionGeograficaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locaciones")
public class LocacionGeograficaControlador {

    private final LocacionGeograficaServicio servicio;

    public LocacionGeograficaControlador(LocacionGeograficaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> listarTodas() {
        return ResponseEntity.ok(servicio.listarTodas());
    }

    @GetMapping("/nivel/{codigoNivel}")
    public ResponseEntity<List<Map<String, Object>>> listarPorNivel(
            @PathVariable Integer codigoNivel) {
        return ResponseEntity.ok(servicio.listarPorNivel(codigoNivel));
    }
}