package com.dh.clinica.controllers;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.services.OdontologoService;
import com.dh.clinica.services.PacienteService;
import com.dh.clinica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

//------------------------------------------------------TRAE TODOS LOS TURNOS-------------------------------------------//

    @GetMapping("/turnos")
    public ResponseEntity<List<TurnoDto>> buscarTurnos () throws ResourceNotFoundException {

        return ResponseEntity.ok(turnoService.getAll());
    }
//------------------------------------------------------CREA UN TUNRO-------------------------------------------//

    @PostMapping("/turnos")
    public ResponseEntity<TurnoDto> registrarTurno (@RequestBody Turno turno) {
        Optional<OdontologoDto> odontologoSearch = Optional.ofNullable(
                odontologoService.getByID(turno.getOdontologo().getId()));

        Optional<PacienteDto> pacienteSearch = Optional.ofNullable
                (pacienteService.getByID(turno.getPaciente().getId()));
        if ((odontologoSearch).isPresent()
                && pacienteSearch.isPresent()) {
            return ResponseEntity.ok(turnoService.guardar(turno));
        } else {
            //bad request 400
            return ResponseEntity.badRequest().build();
        }
    }
//------------------------------------------------------TRAE UN TURNO POR ID-------------------------------------------//

    @GetMapping("/turnos/{id}")
    public ResponseEntity<TurnoDto> buscarTurno (@PathVariable Long id) {
        Optional<TurnoDto> turnoSearch = Optional.ofNullable(turnoService.getByID(id));
        if (turnoSearch.isPresent()) {
            return ResponseEntity.ok(turnoSearch.get());
        } else {
            return ResponseEntity.notFound().build(); //enviamos un error 404
        }
    }
//------------------------------------------------------ELIMINA UN TURNO POR ID-----------------------------------------//

    @DeleteMapping("/turnos/{id}")
    public ResponseEntity<String> borrarTurno (@PathVariable Long id) {
        Optional<TurnoDto> turnoSearch = Optional.ofNullable(turnoService.getByID(id));
        if (turnoSearch.isPresent()) {
            turnoService.eliminar(id);
            return ResponseEntity.ok("Se elimino el turno con id: " + id);
        } else {
            return ResponseEntity.badRequest().body("No se encontro el turno con id:" + id);
        }
    }
//------------------------------------------------------ACTUALIZA UN TURNO POR ID---------------------------------------//
    @PutMapping("/turnos/{id}")
    public ResponseEntity<String> actualizarTurno (Long id, @RequestBody Turno turno) {

        Optional<TurnoDto> turnoSearch = Optional.ofNullable(turnoService.getByID(id));
        if (turnoSearch.isPresent()) {
            turnoService.actualizar(id,turno);
            return ResponseEntity.ok("Se actualizo el turno con id: " + id);
        } else {
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Se elimino el turno con id:"+id);
            return ResponseEntity.badRequest().body("No se encontro el turno con id:" + id);
        }

    }
}