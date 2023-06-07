//package com.clinica.odontologica.services;
//
//import com.clinica.odontologica.dto.TurnoDto;
//import com.clinica.odontologica.entity.Domicilio;
//import com.clinica.odontologica.entity.Odontologo;
//import com.clinica.odontologica.entity.Paciente;
//import com.clinica.odontologica.exceptions.GlobalExceptionHandler;
//import com.clinica.odontologica.exceptions.ResourceNotFoundException;
//import com.clinica.odontologica.repository.OdontologoRepository;
//import com.clinica.odontologica.repository.PacienteRepository;
//import com.clinica.odontologica.repository.TurnoRepository;
//import lombok.ToString;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//@ToString
//@SpringBootTest
//class TurnoServiceTest {
//    @Autowired
//    private TurnoService turnoService;
//    @Autowired
//    private PacienteService pacienteService;
//    @Autowired
//    private OdontologoService odontologoService;
//
//    @Autowired
//    private OdontologoRepository odontologoRepository;
//
//    @Autowired
//    private PacienteRepository pacienteRpository;
//
//    @Autowired
//    private TurnoRepository turnoRepository;
//
//    @Test
//    void guardar_y_buscar_por_matricula () throws ResourceNotFoundException, GlobalExceptionHandler {
//
//
//        //Arrange
//        Odontologo odontologo = new Odontologo();
//        odontologo.setApellido("Sanchez Lopezzzzzz");
//        odontologo.setNombre("Maria de los Angeles");
//        odontologo.setMatricula(12345561L);
//        //Act
//        odontologoService.guardar(odontologo);
//
//        Domicilio domicilio = new Domicilio();
//        domicilio.setCalle("Esperanza");
//        domicilio.setNumero(123);
//        domicilio.setLocalidad("La Plata");
//        domicilio.setProvincia("Buenos Aires");
//
//        Paciente paciente = new Paciente();
//        paciente.setApellido("Sanchez Lopezzzzzz");
//        paciente.setNombre("Maria de los Angeles");
//        paciente.setDni(12345550);
//        paciente.setFechaAlta(LocalDate.parse("2020-12-31"));
//        paciente.setDomicilio(domicilio);
//        //Act
//        pacienteService.guardar(paciente);
//
//
//        Odontologo resultadoOdontologo = odontologoRepository.findByMatricula(12345561L);
//        Long idSearchOdontologo = resultadoOdontologo.getId();
//
//
//
//        Paciente resultadoPaciente = pacienteRpository.findByDni(12345550);
//        Long idSearchPaciente = resultadoPaciente.getId();
//
//        TurnoDto turnodto = new TurnoDto();
//        turnodto.setFecha(LocalDate.parse("2020-12-31"));
//        turnodto.setPacienteId(idSearchPaciente);
//        turnodto.setOdontologoId(idSearchOdontologo);
//
//        TurnoDto resultadoEsperado = turnoService.guardar( turnoService.mapDtoToEntity(turnodto));
//
//        //Assert
//
//
//        Assertions.assertNotNull(resultadoEsperado);
//
//    }
//}