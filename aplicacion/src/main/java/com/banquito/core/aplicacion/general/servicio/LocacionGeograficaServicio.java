package com.banquito.core.aplicacion.general.servicio;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.general.modelo.LocacionGeografica;
import com.banquito.core.aplicacion.general.modelo.Pais;
import com.banquito.core.aplicacion.general.repositorio.LocacionGeograficaRepositorio;
import com.banquito.core.aplicacion.general.excepcion.LocacionGeograficaNoEncontradaExcepcion;

@Service
public class LocacionGeograficaServicio {

    private final LocacionGeograficaRepositorio repositorio;

    public LocacionGeograficaServicio(LocacionGeograficaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public Map<String, Object> listarTodas() {
        List<LocacionGeografica> locaciones = repositorio.findAll();
        
        if (locaciones.isEmpty()) {
            throw new LocacionGeograficaNoEncontradaExcepcion("No hay locaciones registradas");
        }
        
        // Asumimos que todas las locaciones pertenecen al mismo país
        Pais pais = locaciones.get(0).getPais();
        
        // Obtener provincias (nivel 1)
        List<LocacionGeografica> provincias = locaciones.stream()
                .filter(l -> l.getCodigoNivel() == 1)
                .collect(Collectors.toList());
        
        // Construir la estructura de respuesta
        Map<String, Object> respuesta = new LinkedHashMap<>();
        Map<String, Object> paisMap = new LinkedHashMap<>();
        
        paisMap.put("nombre", pais.getNombre());
        
        List<Map<String, Object>> provinciasMap = provincias.stream()
                .map(provincia -> {
                    Map<String, Object> provinciaMap = new LinkedHashMap<>();
                    provinciaMap.put("id", provincia.getId());
                    provinciaMap.put("nombre", provincia.getNombre());
                    provinciaMap.put("codigoTelefonoArea", provincia.getCodigoTelefonoArea());
                    provinciaMap.put("codigoPostal", provincia.getCodigoPostal());
                    
                    // Obtener cantones de esta provincia (nivel 2 con padre)
                    List<Map<String, Object>> cantones = locaciones.stream()
                            .filter(l -> l.getCodigoNivel() == 2 && 
                                       l.getLocacionPadre() != null && 
                                       l.getLocacionPadre().getId().equals(provincia.getId()))
                            .map(canton -> {
                                Map<String, Object> cantonMap = new LinkedHashMap<>();
                                cantonMap.put("id", canton.getId());
                                cantonMap.put("nombre", canton.getNombre());
                                cantonMap.put("codigoTelefonoArea", canton.getCodigoTelefonoArea());
                                cantonMap.put("codigoPostal", canton.getCodigoPostal());
                                return cantonMap;
                            })
                            .collect(Collectors.toList());
                    
                    provinciaMap.put("cantones", cantones);
                    return provinciaMap;
                })
                .collect(Collectors.toList());
        
        paisMap.put("provincias", provinciasMap);
        respuesta.put(pais.getId(), paisMap);
        
        return respuesta;
    }

    public List<Map<String, Object>> listarPorNivel(Integer codigoNivel) {
        List<LocacionGeografica> locaciones = repositorio.findByCodigoNivel(codigoNivel);
        
        if (locaciones.isEmpty()) {
            throw new LocacionGeograficaNoEncontradaExcepcion(
                "No hay locaciones para el nivel: " + codigoNivel);
        }
        
        return locaciones.stream()
                .map(this::mapearLocacion)
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapearLocacion(LocacionGeografica locacion) {
        Map<String, Object> mapa = new LinkedHashMap<>();
        mapa.put("id", locacion.getId());
        mapa.put("nombre", locacion.getNombre());
        mapa.put("codigoNivel", locacion.getCodigoNivel());
        mapa.put("tipo", obtenerTipoLocacion(locacion.getCodigoNivel()));
        mapa.put("codigoTelefonoArea", locacion.getCodigoTelefonoArea());
        mapa.put("codigoPostal", locacion.getCodigoPostal());
        
        if (locacion.getPais() != null) {
            mapa.put("pais", locacion.getPais().getId());
        }
        
        return mapa;
    }

    private String obtenerTipoLocacion(Integer codigoNivel) {
        switch (codigoNivel) {
            case 1: return "Provincia";
            case 2: return "Cantón";
            default: return "Desconocido";
        }
    }
}