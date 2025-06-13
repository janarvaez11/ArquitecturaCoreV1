package com.banquito.core.aplicacion.general.controlador;

import com.banquito.core.aplicacion.general.excepcion.MonedaNoEncontradaException;
import com.banquito.core.aplicacion.general.modelo.Moneda;
import com.banquito.core.aplicacion.general.servicio.MonedaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.banquito.core.aplicacion.general.modelo.Pais;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/monedas")
public class MonedaControlador {

    private final MonedaServicio monedaServicio;

    public MonedaControlador(MonedaServicio monedaServicio) {
        this.monedaServicio = monedaServicio;
    }


    @PostMapping("/pais/{idPais}")
    public ResponseEntity<Moneda> crearMonedaPorPais(@RequestBody Moneda moneda, @PathVariable String idPais) {
        Pais pais = new Pais(idPais);
        monedaServicio.crearMonedaPorPais(moneda, pais);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{idMoneda}/{idEntidadBancaria}")
    public ResponseEntity<Moneda> asignarMonedaAEntidadBancaria(
            @PathVariable String idMoneda,
            @PathVariable Integer idEntidadBancaria) {
        monedaServicio.asignarMonedaAEntidadBancaria(idMoneda, idEntidadBancaria);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String id){
        try{
            return ResponseEntity.ok(this.monedaServicio.findById(id));
        }catch (MonedaNoEncontradaException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        try {
            return ResponseEntity.ok(this.monedaServicio.findAll());
        } catch (MonedaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/pais/{idPais}")
    public ResponseEntity<List<Map<String,Object>>> findByPaisId(@PathVariable String idPais) {
        try {
            return ResponseEntity.ok(this.monedaServicio.findByPaisId(idPais));
        } catch (MonedaNoEncontradaException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/entidadbancaria/{idEntidadBancaria}")
    public ResponseEntity<List<Map<String,Object>>> findByEntidadBancariaId(@PathVariable Integer idEntidadBancaria) {
        try {
            return ResponseEntity.ok(this.monedaServicio.findByEntidadBancariaId(idEntidadBancaria));
        } catch (MonedaNoEncontradaException e) {
            return ResponseEntity.noContent().build();
        }
    }

}
