package com.dh.clinica.controllers;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController()
public class OdondologoController {
    @Autowired
    private OdontologoService service;
//------------------------------------------------------TRAE TODOS LOS ODONTOLOGOS--------------------------------------------//

    @GetMapping("/odontologos")
    public List<OdontologoDto> getAll() throws ResourceNotFoundException {

        return service.getAll();
    }
//------------------------------------------------------TRAE UN ODONTOLOGO POR ID-------------------------------------------//

    @GetMapping("/odontologos/{id}")
    public ResponseEntity<OdontologoDto> getOdontologo(@PathVariable long id) {
        Optional<OdontologoDto> odontologoSearch= Optional.ofNullable(service.getByID(id));
        if(odontologoSearch.isPresent()){
            return ResponseEntity.ok(service.getByID(id));
        }
        else{
            return ResponseEntity.notFound().build(); //enviamos un error 404
        }

    }

//------------------------------------------------------CREA UN ODONTOLOGO--------------------------------------------//

@PostMapping("/odontologos")
public ResponseEntity<OdontologoDto> postOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
    Optional<Odontologo> odontologoSearchPorMatricula= service.buscarOdontologoPorMatricula(odontologo.getMatricula());
    if(odontologoSearchPorMatricula.isPresent()){
        //si existe ese odontólogo con dicha matrícula no lo guardo
        throw new ResourceNotFoundException("Odontólogo matrícula : " + odontologo.getMatricula() + " ya se encuentra Guardado en la Base de Dastos");
    }
    else {
        return ResponseEntity.ok(service.guardar(odontologo));
    }
}
//------------------------------------------------------ACTUALIZA UN ODONTOLOGO POR ID--------------------------------------------//

    @PutMapping("/odontologos/{id}")
    public ResponseEntity<OdontologoDto> putOdontologo(@PathVariable long id, @RequestBody Odontologo odontologo) {
        Optional<OdontologoDto> odontologoSearch= Optional.ofNullable(service.getByID(id));
        if(odontologoSearch.isPresent()){
            return ResponseEntity.ok(service.actualizar(id, odontologo));
        }
        else{
            return ResponseEntity.notFound().build(); //enviamos un error 404
        }

    }
//------------------------------------------------------ELIMINA UN ODONTOLOGO POR ID-------------------------------------------//

    @DeleteMapping("/odontologos/{id}")
    public ResponseEntity<String> deleteOdontologo(@PathVariable long id) {
        Optional<OdontologoDto> odontologoSearch= Optional.ofNullable(service.getByID(id));
        if(odontologoSearch.isPresent()){
            service.eliminar(id);
            return ResponseEntity.ok("Se elimino el Odontólogo con id: " + id);
        }
        else{
            return ResponseEntity.badRequest().body("No se encontro el Odontólogo con id:"+id);
        }
    }
    }


