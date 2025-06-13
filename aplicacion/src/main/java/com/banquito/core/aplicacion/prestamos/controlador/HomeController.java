package com.banquito.core.aplicacion.prestamos.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /// CHECK THE STATUS OF SERVER IS OK
    @GetMapping
    public ResponseEntity<String> HomeController() {
        return new ResponseEntity<>("Welcome to loans project, SERVER ✅", HttpStatus.OK);
    }

    /// CHECK THE STATUS OF DB IS OK
    @GetMapping("/check")
    public List<Map<String, Object>> checkDB() {
        return jdbcTemplate.queryForList("SELECT * FROM clientes");
    }

}
