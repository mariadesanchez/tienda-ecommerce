package com.dh.clinica.controllers;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exceptions.PacienteBussinesException;
import com.dh.clinica.exceptions.TurnoBussinesException;
import com.dh.clinica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController()
public class TurnoController {

    @Autowired
    private TurnoService service;

    @GetMapping("/turnos")
    public List<TurnoDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/turnos/{id}")
    public TurnoDto getTurno(@PathVariable long id) {
        return service.getByID(id);
    }


    @PostMapping("/turnos")
    public ResponseEntity<TurnoDto> postTurno(@RequestBody Turno turno) throws TurnoBussinesException {

        ResponseEntity<TurnoDto> response = null;

        try {
            response = ResponseEntity.ok(service.guardar(turno));
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
    @PutMapping("/turno/{id}")
    public TurnoDto putTurno(@PathVariable long id, @RequestBody Turno turno) {
        return service.actualizar(id, turno);
    }

    @DeleteMapping("/turno/{id}")
    public void deleteTurno(@PathVariable long id) {
        service.eliminar(id);
    }
}
