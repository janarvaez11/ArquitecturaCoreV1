package com.banquito.core.aplicacion.cuentas.servicio;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.banquito.core.aplicacion.cuentas.excepcion.ActualizarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.CrearEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EliminarEntidadExcepcion;
import com.banquito.core.aplicacion.cuentas.excepcion.EntidadNoEncontradaExcepcion;
import com.banquito.core.aplicacion.cuentas.modelo.CuentaCliente;
import com.banquito.core.aplicacion.cuentas.repositorio.CuentaClienteRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CuentaClienteServicio {

    private final CuentaClienteRepositorio cuentaClienteRepositorio;

    public CuentaClienteServicio(CuentaClienteRepositorio cuentaClienteRepositorio) {
        this.cuentaClienteRepositorio = cuentaClienteRepositorio;
    }

    @Transactional
    public CuentaCliente crear(CuentaCliente cuentaCliente) {
        try {
            validarCuentaCliente(cuentaCliente);
            validarNumeroCuentaUnicoPorCliente(cuentaCliente);
            return this.cuentaClienteRepositorio.save(cuentaCliente);
        } catch (RuntimeException e) {
            throw new CrearEntidadExcepcion("CuentaCliente", 
                "Error al crear la cuenta del cliente: " + e.getMessage());
        }
    }

    public CuentaCliente buscarPorId(Integer id) {
        return this.cuentaClienteRepositorio.findById(id)
            .orElseThrow(() -> new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No se encontró la cuenta del cliente con id: " + id));
    }

    @Transactional
    public CuentaCliente actualizar(Integer id, CuentaCliente cuentaCliente) {
        try {
            CuentaCliente existente = buscarPorId(id);
            validarCuentaCliente(cuentaCliente);
            
            // Solo validar número de cuenta único si ha cambiado
            if (!existente.getNumeroCuenta().equals(cuentaCliente.getNumeroCuenta())) {
                validarNumeroCuentaUnicoPorCliente(cuentaCliente);
            }
            
            cuentaCliente.setIdCuentaCliente(existente.getIdCuentaCliente());
            return this.cuentaClienteRepositorio.save(cuentaCliente);
        } catch (RuntimeException e) {
            throw new ActualizarEntidadExcepcion("CuentaCliente", 
                "Error al actualizar la cuenta del cliente: " + e.getMessage());
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        try {
            CuentaCliente cuentaCliente = buscarPorId(id);
            this.cuentaClienteRepositorio.delete(cuentaCliente);
        } catch (RuntimeException e) {
            throw new EliminarEntidadExcepcion("CuentaCliente", 
                "Error al eliminar la cuenta del cliente: " + e.getMessage());
        }
    }

    public List<CuentaCliente> listarTodos() {
        List<CuentaCliente> cuentas = this.cuentaClienteRepositorio.findAll();
        if (cuentas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No existen cuentas de clientes registradas");
        }
        return cuentas;
    }

    public Page<CuentaCliente> listarTodosPaginado(Pageable pageable) {
        Page<CuentaCliente> page = this.cuentaClienteRepositorio.findAll(pageable);
        if (page.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No existen cuentas de clientes registradas");
        }
        return page;
    }

    public List<CuentaCliente> buscarPorCliente(Integer clienteId) {
        List<CuentaCliente> cuentas = this.cuentaClienteRepositorio.findByCliente_idCliente(clienteId);
        if (cuentas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No se encontraron cuentas para el cliente: " + clienteId);
        }
        return cuentas;
    }

    public List<CuentaCliente> buscarPorEstado(String estado) {
        List<CuentaCliente> cuentas = this.cuentaClienteRepositorio.findByEstado(estado);
        if (cuentas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No se encontraron cuentas en estado: " + estado);
        }
        return cuentas;
    }

    public List<CuentaCliente> buscarPorSaldoDisponibleMayorQue(BigDecimal saldoMinimo) {
        List<CuentaCliente> cuentas = this.cuentaClienteRepositorio.findBySaldoDisponibleGreaterThan(saldoMinimo);
        if (cuentas.isEmpty()) {
            throw new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No se encontraron cuentas con saldo mayor a: " + saldoMinimo);
        }
        return cuentas;
    }

    public CuentaCliente buscarPorNumeroCuenta(String numeroCuenta) {
        CuentaCliente cuenta = this.cuentaClienteRepositorio.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null) {
            throw new EntidadNoEncontradaExcepcion("CuentaCliente", 
                "No se encontró la cuenta con número: " + numeroCuenta);
        }
        return cuenta;
    }

    private void validarNumeroCuentaUnicoPorCliente(CuentaCliente cuentaCliente) {
        if (cuentaCliente.getNumeroCuenta() == null || cuentaCliente.getCliente() == null 
            || cuentaCliente.getCliente().getIdCliente() == null) {
            throw new CrearEntidadExcepcion("CuentaCliente", 
                "El número de cuenta y el cliente son obligatorios");
        }

        // Verificar si existe otra cuenta con el mismo número para el mismo cliente
        if (this.cuentaClienteRepositorio.existsByNumeroCuentaAndClienteIdCliente(
                cuentaCliente.getNumeroCuenta(), 
                cuentaCliente.getCliente().getIdCliente())) {
            throw new CrearEntidadExcepcion("CuentaCliente", 
                "Ya existe una cuenta con el número " + cuentaCliente.getNumeroCuenta() + 
                " para el cliente con ID " + cuentaCliente.getCliente().getIdCliente());
        }
    }

    private void validarCuentaCliente(CuentaCliente cuentaCliente) {
        if (cuentaCliente.getNumeroCuenta() == null || cuentaCliente.getNumeroCuenta().trim().isEmpty()) {
            throw new CrearEntidadExcepcion("CuentaCliente", 
                "El número de cuenta es obligatorio");
        }
        if (cuentaCliente.getSaldoDisponible() != null && cuentaCliente.getSaldoDisponible().compareTo(BigDecimal.ZERO) < 0) {
            throw new CrearEntidadExcepcion("CuentaCliente", 
                "El saldo disponible no puede ser negativo");
        }
        if (cuentaCliente.getCliente() == null || cuentaCliente.getCliente().getIdCliente() == null) {
            throw new CrearEntidadExcepcion("CuentaCliente", 
                "El cliente es obligatorio");
        }
    }
}
