package com.banquito.core.aplicacion.general.servicio;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.general.modelo.EstructuraGeografica;
import com.banquito.core.aplicacion.general.repositorio.EstructuraGeograficaRepositorio;
import com.banquito.core.aplicacion.general.excepcion.EstructuraGeograficaNoEncontradaExcepcion;

@Service
public class EstructuraGeograficaServicio {

    private final EstructuraGeograficaRepositorio repositorio;

    public EstructuraGeograficaServicio(EstructuraGeograficaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Map<String, Object>> listarTodas() {
        List<EstructuraGeografica> estructuras = this.repositorio.findAll();
        if (estructuras.isEmpty()) {
            throw new EstructuraGeograficaNoEncontradaExcepcion(
                    "No hay estructuras geográficas registradas");
        }
        
        return estructuras.stream()
                .map(this::mapearEstructura)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> listarPorPais(String idPais) {
        List<EstructuraGeografica> estructuras = this.repositorio.findByIdPaisId(idPais);
        if (estructuras.isEmpty()) {
            throw new EstructuraGeograficaNoEncontradaExcepcion(
                    "No hay estructuras para el país: " + idPais);
        }
        
        return estructuras.stream()
                .map(this::mapearEstructura)
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapearEstructura(EstructuraGeografica estructura) {
        Map<String, Object> mapa = new LinkedHashMap<>();

        mapa.put("paisId", estructura.getId().getPaisId());
        mapa.put("codigoNivel", estructura.getId().getCodigoNivel());

        mapa.put("nombre", estructura.getNombre());

        if (estructura.getPais() != null) {
            Map<String, Object> paisMap = new LinkedHashMap<>();
            paisMap.put("id", estructura.getPais().getId());
            paisMap.put("nombre", estructura.getPais().getNombre());
            mapa.put("pais", paisMap);
        }
        
        if (estructura.getLocacionesGeograficas() != null && !estructura.getLocacionesGeograficas().isEmpty()) {
            mapa.put("locaciones", estructura.getLocacionesGeograficas().stream()
                    .map(loc -> {
                        Map<String, Object> locMap = new LinkedHashMap<>();
                        locMap.put("id", loc.getId());
                        locMap.put("nombre", loc.getNombre());
                        return locMap;
                    })
                    .collect(Collectors.toList()));
        }
        
        return mapa;
    }
}