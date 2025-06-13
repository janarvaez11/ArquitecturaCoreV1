package com.banquito.core.aplicacion.general.servicio;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.aplicacion.general.modelo.Pais;
import com.banquito.core.aplicacion.general.repositorio.PaisRepositorio;
import com.banquito.core.aplicacion.general.excepcion.*;

@Service
public class PaisServicio {

    private final PaisRepositorio paisRepositorio;

    public PaisServicio(PaisRepositorio paisRepositorio) {
        this.paisRepositorio = paisRepositorio;
    }

    @Transactional
    public void crearPais(Pais pais) {
        if (paisRepositorio.existsById(pais.getId())) {
            throw new CrearPaisExcepcion("Pais", "El país con ID " + pais.getId() + " ya existe.");
        }
        this.paisRepositorio.save(pais);
    }

    @Transactional
    public void actualizarPais(String id, Pais pais) {
        Pais paisDB = obtenerPorIdNoMapeado(id);
        
        paisDB.setNombre(pais.getNombre());
        paisDB.setCodigoTelefono(pais.getCodigoTelefono());
        
        this.paisRepositorio.save(paisDB);
    }

    public Map<String, Object> obtenerPorId(String id) {
        Pais pais = this.paisRepositorio.findById(id)
                .orElseThrow(() -> new PaisNoEncontradoExcepcion("País no encontrado con ID: " + id));
        return mapPais(pais);
    }

    private Pais obtenerPorIdNoMapeado(String id) {
        return this.paisRepositorio.findById(id)
                .orElseThrow(() -> new PaisNoEncontradoExcepcion("País no encontrado con ID: " + id));
    }

    public List<Map<String, Object>> listarTodos() {
        List<Pais> paises = this.paisRepositorio.findAll();
        if (paises.isEmpty()) {
            throw new PaisNoEncontradoExcepcion("No hay países registrados en el sistema.");
        }
        return paises.stream()
                .map(this::mapPais)
                .collect(Collectors.toList());
    }

    public Map<String, Object> obtenerPaisPorDefecto() {
        List<Pais> paises = this.paisRepositorio.findAll();
        if (paises.isEmpty()) {
            throw new PaisNoEncontradoExcepcion("No hay países registrados en el sistema.");
        }
        return mapPais(paises.getFirst());
    }

    private Map<String, Object> mapPais(Pais pais) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", pais.getId());
        map.put("nombre", pais.getNombre());
        map.put("codigoTelefono", pais.getCodigoTelefono());
        
        // Mapeo de relaciones
        if (pais.getEstructurasGeograficas() != null && !pais.getEstructurasGeograficas().isEmpty()) {
            map.put("estructurasGeograficas", pais.getEstructurasGeograficas().stream()
                    .map(eg -> {
                        Map<String, Object> egMap = new LinkedHashMap<>();
                        egMap.put("id", eg.getId());
                        egMap.put("nombre", eg.getNombre());
                        return egMap;
                    })
                    .collect(Collectors.toList()));
        } else {
            map.put("estructurasGeograficas", null);
        }
        
        if (pais.getLocacionesGeograficas() != null && !pais.getLocacionesGeograficas().isEmpty()) {
            map.put("locacionesGeograficas", pais.getLocacionesGeograficas().stream()
                    .map(lg -> {
                        Map<String, Object> lgMap = new LinkedHashMap<>();
                        lgMap.put("id", lg.getId());
                        lgMap.put("nombre", lg.getNombre());
                        return lgMap;
                    })
                    .collect(Collectors.toList()));
        } else {
            map.put("locacionesGeograficas", null);
        }
        
        return map;
    }
}