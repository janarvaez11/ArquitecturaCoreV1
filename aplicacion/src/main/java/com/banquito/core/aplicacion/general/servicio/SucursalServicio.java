package com.banquito.core.aplicacion.general.servicio;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.core.aplicacion.general.modelo.Sucursal;
import com.banquito.core.aplicacion.general.repositorio.SucursalRepositorio;
import com.banquito.core.aplicacion.general.excepcion.*;

@Service
public class SucursalServicio {

    private final SucursalRepositorio sucursalRepositorio;

    public SucursalServicio(SucursalRepositorio sucursalRepositorio) {
        this.sucursalRepositorio = sucursalRepositorio;
    }

    @Transactional
    public void crearSucursal(Sucursal sucursal) {
        if (sucursalRepositorio.existsByCodigo(sucursal.getCodigo())) {
            throw new CrearSucursalExcepcion("Ya existe una sucursal con el código: " + sucursal.getCodigo(), null);
        }
        sucursal.setEstado("ACT");
        this.sucursalRepositorio.save(sucursal);
    }

    @Transactional
    public void actualizarSucursal(String codigo, Sucursal sucursal) {
        Sucursal sucursalDB = obtenerPorCodigoNoMapeado(codigo);
        
        sucursalDB.setNombre(sucursal.getNombre());
        sucursalDB.setTelefono(sucursal.getTelefono());
        sucursalDB.setCorreoElectronico(sucursal.getCorreoElectronico());
        sucursalDB.setLatitud(sucursal.getLatitud());
        sucursalDB.setLongitud(sucursal.getLongitud());
        sucursalDB.setLinea1(sucursal.getLinea1());
        sucursalDB.setLinea2(sucursal.getLinea2());
        sucursalDB.setEstado(sucursal.getEstado());
        
        this.sucursalRepositorio.save(sucursalDB);
    }

    @Transactional
    public void eliminarLogico(String codigo) {
        Sucursal sucursal = obtenerPorCodigoNoMapeado(codigo);
        sucursal.setEstado("INA");
        this.sucursalRepositorio.save(sucursal);
    }

    public Map<String, Object> obtenerPorCodigo(String codigo) {
        Sucursal sucursal = this.sucursalRepositorio.findByCodigo(codigo)
                .orElseThrow(() -> new SucursalNoEncontradaExcepcion("Sucursal no encontrada con código: " + codigo));
        return mapSucursal(sucursal);
    }

    private Sucursal obtenerPorCodigoNoMapeado(String codigo) {
        return this.sucursalRepositorio.findByCodigo(codigo)
                .orElseThrow(() -> new SucursalNoEncontradaExcepcion("Sucursal no encontrada con código: " + codigo));
    }

    public List<Map<String, Object>> listarTodas() {
        return this.sucursalRepositorio.findAll().stream()
                .map(this::mapSucursal)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> listarActivas() {
        return this.sucursalRepositorio.findByEstado("ACT").stream()
                .map(this::mapSucursal)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> buscarPorLocacion(Integer idLocacion) {
        return this.sucursalRepositorio.findByLocacionId(idLocacion).stream()
                .map(this::mapSucursal)
                .collect(Collectors.toList());
    }

    public List<Map<String, Object>> buscarPorEntidadBancaria(Integer idEntidadBancaria) {
        return this.sucursalRepositorio.findByEntidadBancariaId(idEntidadBancaria).stream()
                .map(this::mapSucursal)
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapSucursal(Sucursal sucursal) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", sucursal.getIdSucursal());
        map.put("codigo", sucursal.getCodigo());
        map.put("claveUnica", sucursal.getClaveUnica());
        map.put("nombre", sucursal.getNombre());
        map.put("estado", sucursal.getEstado());
        map.put("fechaCreacion", sucursal.getFechaCreacion());
        map.put("correoElectronico", sucursal.getCorreoElectronico());
        map.put("telefono", sucursal.getTelefono());
        map.put("direccion", String.format("%s %s", 
                sucursal.getLinea1(), 
                sucursal.getLinea2() != null ? sucursal.getLinea2() : ""));
        map.put("coordenadas", String.format("%f, %f", 
                sucursal.getLatitud(), 
                sucursal.getLongitud()));
        
        if (sucursal.getEntidadBancaria() != null) {
            Map<String, Object> entidadBancariaMap = new LinkedHashMap<>();
            entidadBancariaMap.put("id", sucursal.getEntidadBancaria().getId());
            entidadBancariaMap.put("codigoLocal", sucursal.getEntidadBancaria().getCodigoLocal());
            entidadBancariaMap.put("nombre", sucursal.getEntidadBancaria().getNombre());
            entidadBancariaMap.put("codigoInternacional", sucursal.getEntidadBancaria().getCodigoInternacional());
            map.put("entidadBancaria", entidadBancariaMap);
        } else {
            map.put("entidadBancaria", null);
        }
        
        if (sucursal.getLocacion() != null) {
            Map<String, Object> locacionMap = new LinkedHashMap<>();
            locacionMap.put("id", sucursal.getLocacion().getId());
            locacionMap.put("nombre", sucursal.getLocacion().getNombre());
            
            if (sucursal.getLocacion().getPais() != null) {
                locacionMap.put("pais", sucursal.getLocacion().getPais().getId());
            }
            
            map.put("locacion", locacionMap);
        } else {
            map.put("locacion", null);
        }
        
        return map;
    }
}