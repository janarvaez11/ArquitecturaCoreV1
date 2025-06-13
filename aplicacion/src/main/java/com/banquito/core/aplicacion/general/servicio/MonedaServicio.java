package com.banquito.core.aplicacion.general.servicio;

import com.banquito.core.aplicacion.general.excepcion.ActualizarEntidadException;
import com.banquito.core.aplicacion.general.excepcion.CrearEntidadException;
import com.banquito.core.aplicacion.general.excepcion.MonedaNoEncontradaException;
import com.banquito.core.aplicacion.general.modelo.EntidadBancaria;
import com.banquito.core.aplicacion.general.modelo.Moneda;
import com.banquito.core.aplicacion.general.repositorio.MonedaRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.aplicacion.general.modelo.Pais;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MonedaServicio {

    private final MonedaRepositorio monedarepositorio;
    private final EntidadBancariaServicio entidadBancariaServicio;

    public MonedaServicio(MonedaRepositorio repositorio, EntidadBancariaServicio entidadBancariaServicio) {
        this.monedarepositorio = repositorio;
        this.entidadBancariaServicio = entidadBancariaServicio;
    }


    @Transactional
    public void crearMonedaPorPais(Moneda moneda, Pais pais) {
        try {
            if (moneda.getId() == null ) {
                throw new CrearEntidadException("Moneda", "El id no puede ser nulo");
            }
            if (moneda.getNombre() == null || moneda.getNombre().length() > 50) {
                throw new CrearEntidadException("Moneda", "El nombre es obligatorio y debe tener máximo 50 caracteres");
            }
            if (moneda.getSimbolo() == null || moneda.getSimbolo().length() > 5) {
                throw new CrearEntidadException("Moneda", "El símbolo es obligatorio y debe tener máximo 5 caracteres");
            }
            if (moneda.getCodigo() == null ) {
                throw new CrearEntidadException("Moneda", "El código no puede ser nulo");
            }

            moneda.setPais(pais);
            this.monedarepositorio.save(moneda);
        } catch (RuntimeException rte) {
            throw new CrearEntidadException("Moneda", "Error al crear la moneda por pais. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void asignarMonedaAEntidadBancaria(String idMoneda, Integer idEntidadBancaria) {
        try {
            Optional<Moneda> monedaOptional = this.monedarepositorio.findById(idMoneda);
            if (monedaOptional.isPresent()) {
                Moneda moneda = monedaOptional.get();
                EntidadBancaria entidadBancaria = entidadBancariaServicio.EncotrarporId(idEntidadBancaria);
                moneda.setEntidadBancaria(entidadBancaria);
            } else {
                throw new MonedaNoEncontradaException("La moneda con ID: " + idMoneda + " no existe");
            }
        } catch (RuntimeException rte) {
            throw new ActualizarEntidadException("Moneda", "Error al asignar la moneda a la entidad bancaria. Texto del error: " + rte.getMessage());
        }
    }

    public Map<String, Object> findById(String id) {
        Optional<Moneda> monedaOptional = monedarepositorio.findById(id);
        if (monedaOptional.isPresent()) {
            return mapMoneda(monedaOptional.get());
        } else {
            throw new MonedaNoEncontradaException("El id: " + id + " no corresponde a ninguna moneda");
        }
    }

    public List<Map<String, Object>> findAll() {
        List<Moneda> monedas = this.monedarepositorio.findAll();
        if (!monedas.isEmpty()) {
            return monedas.stream().map(this::mapMoneda).toList();
        } else {
            throw new MonedaNoEncontradaException("No existen monedas registrados");
        }
    }

    public List<Map<String, Object>> findByPaisId(String idPais) {
        List<Moneda> monedas = this.monedarepositorio.findByPaisId(idPais);
        if (!monedas.isEmpty()) {
            return monedas.stream().map(this::mapMoneda).toList();
        } else {
            throw new MonedaNoEncontradaException("No existen monedas para el país: " + idPais);
        }
    }

    public List<Map<String, Object>> findByEntidadBancariaId(Integer idEntidadBancaria) {
        List<Moneda> monedas = this.monedarepositorio.findByEntidadBancariaId(idEntidadBancaria);
        if (!monedas.isEmpty()) {
            return monedas.stream().map(this::mapMoneda).toList();
        } else {
            throw new MonedaNoEncontradaException("No existen monedas para la entidad bancaria: " + idEntidadBancaria);
        }
    }

    private Map<String, Object> mapMoneda(Moneda moneda) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", moneda.getId());
        map.put("nombre", moneda.getNombre());
        map.put("simbolo", moneda.getSimbolo());
        map.put("codigo", moneda.getCodigo());
        map.put("version", moneda.getVersion());
        map.put("paisId", moneda.getPais() != null ? moneda.getPais().getId() : null);
        map.put("entidadBancariaId", moneda.getEntidadBancaria() != null ? moneda.getEntidadBancaria().getId() : null);
        return map;
    }
}
