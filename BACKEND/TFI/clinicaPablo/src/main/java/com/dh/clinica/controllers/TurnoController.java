package com.dh.clinica.controllers;
import com.dh.clinica.entity.Odontologo;

import com.dh.clinica.entity.Turno;
import com.dh.clinica.repository.TurnoRepository;
import com.dh.clinica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController()
public class TurnoController {
    @Autowired
    TurnoRepository turnoRepository;
    @Autowired
    private TurnoService service;

    @GetMapping("/turnos")
    public List<Turno> getAll() {
        return service.getAll();
    }

    @GetMapping("/turnos/{id}")
    public Optional<Turno> getTurno(@PathVariable long id) {
        return service.getByID(id);
    }

    @PostMapping("/turnos")
    public ResponseEntity<Turno> postTurno(@RequestBody Turno turno) {

        ResponseEntity<Turno> response = null;

        try {
            response = ResponseEntity.ok(service.guardar(turno));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    @PutMapping("/turnos/{id}")
    public Turno putTurno(@PathVariable long id, @RequestBody Turno turno) {
        return service.actualizar(id, turno);
    }

    @DeleteMapping("/turnos/{id}")
    public void deleteTurno(@PathVariable long id) {
        service.eliminar(id);
    }
}

