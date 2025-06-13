package com.banquito.core.aplicacion.general.controlador;

import com.banquito.core.aplicacion.general.servicio.EstructuraGeograficaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estructuras-geograficas")
public class EstructuraGeograficaControlador {

    private final EstructuraGeograficaServicio servicio;

    public EstructuraGeograficaControlador(EstructuraGeograficaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarTodas() {
        return ResponseEntity.ok(servicio.listarTodas());
    }

    @GetMapping("/pais/{idPais}")
    public ResponseEntity<List<Map<String, Object>>> listarPorPais(
            @PathVariable String idPais) {
        return ResponseEntity.ok(servicio.listarPorPais(idPais));
    }
}