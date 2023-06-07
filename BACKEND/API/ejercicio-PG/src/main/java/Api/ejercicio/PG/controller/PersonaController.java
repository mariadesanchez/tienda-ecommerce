package Api.ejercicio.PG.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import Api.ejercicio.PG.service.PersonaService;


@RestController
public class PersonaController {

    @GetMapping("/{dia}/{mes}/{anio}")

    public String edadPersona(@PathVariable int dia,
                              @PathVariable int mes,
                              @PathVariable int anio){
        PersonaService personaService = new PersonaService();

        return personaService.getEdad(anio,mes,dia);
    }

}
