package com.banquito.core.aplicacion.general.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.banquito.core.aplicacion.general.modelo.Sucursal;
import com.banquito.core.aplicacion.general.servicio.SucursalServicio;

@CrossOrigin
@RestController
@RequestMapping("/api/sucursales")
public class SucursalControlador {

    private final SucursalServicio sucursalServicio;

    public SucursalControlador(SucursalServicio sucursalServicio) {
        this.sucursalServicio = sucursalServicio;
    }

    @PostMapping
    public ResponseEntity<Void> crear(@RequestBody Sucursal sucursal) {
        sucursalServicio.crearSucursal(sucursal);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Void> actualizar(@PathVariable String codigo, @RequestBody Sucursal sucursal) {
        sucursalServicio.actualizarSucursal(codigo, sucursal);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        sucursalServicio.eliminarLogico(codigo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(sucursalServicio.obtenerPorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listarTodas() {
        return ResponseEntity.ok(sucursalServicio.listarTodas());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<Map<String, Object>>> listarActivas() {
        return ResponseEntity.ok(sucursalServicio.listarActivas());
    }

    @GetMapping("/locacion/{idLocacion}")
    public ResponseEntity<List<Map<String, Object>>> buscarPorLocacion(@PathVariable Integer idLocacion) {
        return ResponseEntity.ok(sucursalServicio.buscarPorLocacion(idLocacion));
    }

    @GetMapping("/entidad-bancaria/{idEntidadBancaria}")
    public ResponseEntity<List<Map<String, Object>>> buscarPorEntidadBancaria(@PathVariable Integer idEntidadBancaria) {
        return ResponseEntity.ok(sucursalServicio.buscarPorEntidadBancaria(idEntidadBancaria));
    }

}