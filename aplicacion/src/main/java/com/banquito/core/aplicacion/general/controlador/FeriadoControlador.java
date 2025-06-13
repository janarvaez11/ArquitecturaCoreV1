package com.banquito.core.aplicacion.general.controlador;

import com.banquito.core.aplicacion.general.excepcion.FeriadoNoEncontradoException;
import com.banquito.core.aplicacion.general.modelo.Feriado;
import com.banquito.core.aplicacion.general.servicio.FeriadoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/feriados")
public class FeriadoControlador {

    private final FeriadoServicio feriadoServicio;

    public FeriadoControlador(FeriadoServicio feriadoServicio) {
        this.feriadoServicio = feriadoServicio;
    }

    @PostMapping("/pais/{paisId}")
    public ResponseEntity<Feriado> crearPorPais(@PathVariable String paisId, @RequestBody Feriado feriado) {
        feriadoServicio.crearFeriadoPorPais(feriado, paisId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/locacion/{locacionId}")
    public ResponseEntity<Feriado> crearPorLocacion(@PathVariable Integer locacionId, @RequestBody Feriado feriado) {
        feriadoServicio.crearFeriadoPorLocacion(feriado, locacionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.feriadoServicio.findById(id));
        } catch (FeriadoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        try {
            return ResponseEntity.ok(this.feriadoServicio.findAll());
        } catch (FeriadoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pais/{paisId}")
    public ResponseEntity<List<Map<String, Object>>> findByPaisId(@PathVariable String paisId) {
        try {
            return ResponseEntity.ok(this.feriadoServicio.findByPaisId(paisId));
        } catch (FeriadoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/locacion/{locacionId}")
    public ResponseEntity<List<Map<String, Object>>> findByLocacionId(@PathVariable Integer locacionId) {
        try {
            return ResponseEntity.ok(this.feriadoServicio.findByLocacionId(locacionId));
        } catch (FeriadoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/locacion/nombre/{nombre}")
    public ResponseEntity<List<Map<String, Object>>> findByLocacionNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(this.feriadoServicio.findByLocacionNombre(nombre));
        } catch (FeriadoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
