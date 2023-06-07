package com.dh.clinica.controllers;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exceptions.GlobalExceptionHandler;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController()
public class PacienteController {

    @Autowired
    private PacienteService service;
//------------------------------------------------------TRAE TODOS LOS PACIENTES--------------------------------------------//

    @GetMapping("/pacientes")
    public List<PacienteDto> getAll() throws ResourceNotFoundException {
        return service.getAll();
    }
    //------------------------------------------------------TRAE UN PACIENTE POR ID--------------------------------------------//
    @GetMapping("/pacientes/{id}")
    public ResponseEntity<PacienteDto> getPaciente(@PathVariable long id) {
        Optional<PacienteDto> pacienteSearch= Optional.ofNullable(service.getByID(id));
        if(pacienteSearch.isPresent()){
           ;
            return ResponseEntity.ok( service.getByID(id));
        }
        else{
            return ResponseEntity.notFound().build(); //enviamos un error 404
        }
    }
//------------------------------------------------------CREA UN PACIENTE--------------------------------------------//
    @PostMapping("/pacientes")
    public ResponseEntity<PacienteDto> postPaciente(@RequestBody Paciente paciente) throws GlobalExceptionHandler, ResourceNotFoundException {

        Optional<Paciente> pacienteSearchPorDni= service.buscarPacientePorDni(paciente.getDni());
        if(pacienteSearchPorDni.isPresent()){
            //si existe ese Paciente con dicha matrícula no lo guardo
            throw new ResourceNotFoundException("Paciente DNI : " + paciente.getDni() + " ya se encuentra Guardado en la Base de Dastos");

        }
        else {
            return ResponseEntity.ok(service.guardar(paciente));
        }
    }
    //------------------------------------------------------ACTUALIZA UN PACIENTE POR ID-------------------------------//
    @PutMapping("/pacientes/{id}")
    public ResponseEntity<PacienteDto> putOdontologo(@PathVariable long id, @RequestBody Paciente paciente) {
        Optional<PacienteDto> pacienteoSearch = Optional.ofNullable(service.getByID(id));
        if (pacienteoSearch.isPresent()) {
            return ResponseEntity.ok(service.actualizar(id, paciente));

        } else {
            return ResponseEntity.notFound().build(); //enviamos un error 404
        }
    }
//------------------------------------------------------ELIMINA UN PACIENTE POR ID-------------------------------------------//

    @DeleteMapping("/pacientes/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable long id) {
        Optional<PacienteDto> pacienteSearch= Optional.ofNullable(service.getByID(id));
        if(pacienteSearch.isPresent()){
            service.eliminar(id);
            return ResponseEntity.ok("Se elimino el Paciente con id: " + id);
        }
        else{
            return ResponseEntity.badRequest().body("No se Paciente el paciente con id:"+id);
        }
    }
}
