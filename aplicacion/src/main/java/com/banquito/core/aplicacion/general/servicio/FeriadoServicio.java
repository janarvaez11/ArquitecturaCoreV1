package com.banquito.core.aplicacion.general.servicio;

import com.banquito.core.aplicacion.general.excepcion.CrearEntidadException;
import com.banquito.core.aplicacion.general.excepcion.FeriadoNoEncontradoException;
import com.banquito.core.aplicacion.general.modelo.Feriado;
import com.banquito.core.aplicacion.general.repositorio.FeriadoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.banquito.core.aplicacion.general.modelo.LocacionGeografica;
import com.banquito.core.aplicacion.general.modelo.Pais;    
import com.banquito.core.aplicacion.general.repositorio.LocacionGeograficaRepositorio;
import com.banquito.core.aplicacion.general.repositorio.PaisRepositorio;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class FeriadoServicio {

    private final FeriadoRepositorio feriadorepositorio;
    private final PaisRepositorio paisrepositorio;
    private final LocacionGeograficaRepositorio locaciongeograficarepositorio;

    public FeriadoServicio(FeriadoRepositorio feriadorepositorio, PaisRepositorio paisrepositorio, LocacionGeograficaRepositorio locaciongeograficarepositorio) {
        this.feriadorepositorio = feriadorepositorio;
        this.paisrepositorio = paisrepositorio;
        this.locaciongeograficarepositorio = locaciongeograficarepositorio;
    }

    @Transactional
    public void crearFeriadoPorPais(Feriado feriado, String paisId) {
        try {
            if (feriado.getFechaFeriado() == null) {
                throw new CrearEntidadException("Feriado", "La fecha del feriado no puede ser nula");
            }
            Date fechaDate = feriado.getFechaFeriado();
            LocalDate fecha = fechaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int currentYear = LocalDate.now().getYear();
            if (fecha.getYear() < currentYear) {
                throw new CrearEntidadException("Feriado", "La fecha debe ser del año actual o posterior: " + currentYear);
            }
            Pais pais = paisrepositorio.findById(paisId)
                    .orElseThrow(() -> new IllegalArgumentException("País no encontrado"));
            feriado.setPais(pais);
            feriado.setTipoFeriado("NAC");
            feriado.setLocacion(null);
            feriadorepositorio.save(feriado);
        } catch (RuntimeException rte) {
            throw new CrearEntidadException("Feriado", "Error al crear el feriado por país. Texto del error: " + rte.getMessage());
        }
    }

    @Transactional
    public void crearFeriadoPorLocacion(Feriado feriado, Integer locacionId) {
        try {
            if (feriado.getFechaFeriado() == null) {
                throw new CrearEntidadException("Feriado", "La fecha del feriado no puede ser nula");
            }
            Date fechaDate = feriado.getFechaFeriado();
            LocalDate fecha = fechaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int currentYear = LocalDate.now().getYear();
            if (fecha.getYear() < currentYear) {
                throw new CrearEntidadException("Feriado", "La fecha debe ser del año actual o posterior: " + currentYear);
            }
            LocacionGeografica locacion = locaciongeograficarepositorio.findById(locacionId)
                    .orElseThrow(() -> new IllegalArgumentException("Locación no encontrada"));
            feriado.setLocacion(locacion);
            feriado.setTipoFeriado("REG");
            feriado.setPais(locacion.getPais());
            feriadorepositorio.save(feriado);
        } catch (RuntimeException rte) {
            throw new CrearEntidadException("Feriado", "Error al crear el feriado por locación. Texto del error: " + rte.getMessage());
        }
    }

    public Map<String, Object> findById(Integer id) {
        Optional<Feriado> feriadoOptional = this.feriadorepositorio.findById(id);
        if (feriadoOptional.isPresent()) {
            return mapFeriado(feriadoOptional.get());
        } else {
            throw new FeriadoNoEncontradoException("El id: " + id + " no corresponde a ningun Feriado");
        }
    }

    public List<Map<String, Object>> findAll() {
        List<Feriado> feriados = this.feriadorepositorio.findAll();
        if (!feriados.isEmpty()) {
            return feriadorepositorio.findAll().stream().map(this::mapFeriado).toList();
        }else {
            throw new FeriadoNoEncontradoException("No existen feriados registrados");
        }

    }
    public List<Map<String, Object>> findByLocacionId(Integer locacionId) {
        List<Feriado> feriados = feriadorepositorio.findByLocacionId(locacionId);
        if (!feriados.isEmpty()) {
            return feriados.stream().map(this::mapFeriado).toList();
        } else {
            throw new FeriadoNoEncontradoException("No existen feriados para la locación: " + locacionId);
        }
    }

    public List<Map<String, Object>> findByPaisId(String paisId) {
        List<Feriado> feriados = feriadorepositorio.findByPaisId(paisId);
        if (!feriados.isEmpty()) {
            return feriados.stream().map(this::mapFeriado).toList();
        } else {
            throw new FeriadoNoEncontradoException("No existen feriados para el país: " + paisId);
        }
    }

    public List<Map<String, Object>> findByLocacionNombre(String nombre) {
        List<Feriado> feriados = feriadorepositorio.findByLocacion_Nombre(nombre);
        if (!feriados.isEmpty()) {
            return feriados.stream().map(this::mapFeriado).toList();
        } else {
            throw new FeriadoNoEncontradoException("No existen feriados para la locación con nombre: " + nombre);
        }
    }

    private Map<String, Object> mapFeriado(Feriado feriado) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", feriado.getId());
        map.put("fechaFeriado", feriado.getFechaFeriado());
        map.put("nombre", feriado.getNombre());
        map.put("tipoFeriado", feriado.getTipoFeriado());
        map.put("version", feriado.getVersion());
        map.put("paisId", feriado.getPais() != null ? feriado.getPais().getId() : null);
        map.put("locacion", feriado.getLocacion() != null ? feriado.getLocacion().getNombre() : null);
        return map;
    }

}
