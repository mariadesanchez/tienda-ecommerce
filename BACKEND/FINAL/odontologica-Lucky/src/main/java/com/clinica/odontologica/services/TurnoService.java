package com.dh.clinica.services;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service

public class TurnoService {
//
    @Autowired
    private TurnoRepository repository;
    private final static Logger logger = Logger.getLogger(String.valueOf(TurnoService.class));


    ArrayList ListDto = new ArrayList();
    public List<TurnoDto> getAll() throws ResourceNotFoundException {
        if (repository.findAll().size() > 0) {

            for (Turno t :
                    repository.findAll()) {
                ListDto.add(mapEntityToDto(t));
                logger.info("GETTING ALL TURNOS: " +t.getFecha());
            }
            return ListDto;
        }
        throw new ResourceNotFoundException("No hay Datos que retornar");
    }

    public TurnoDto getByID(Long id) {
        Turno turnoSearch =  repository.findById(id).orElse(null);
        logger.info("GETTING  TURNO ID : " + id);
        return mapEntityToDto(turnoSearch);
    }

    public TurnoDto guardar(Turno turno) {

            Turno turnoSave = repository.save(turno);
            return mapEntityToDto(turnoSave);

        }

    public TurnoDto actualizar(long id, Turno turno) {

        Turno turnoUpDate = repository.findById(id).orElse(null);
        turnoUpDate.setOdontologo(turno.getOdontologo());
        turnoUpDate.setPaciente(turno.getPaciente());
        repository.save(turnoUpDate);
        logger.info("UPDATING TURNO ID : " + turnoUpDate.getId());
        return mapEntityToDto(turnoUpDate);
    }

    public void eliminar (long id) {

        Optional<Turno> turno = repository.findById(id);
        if (turno.isPresent()) {
            logger.info("DELETE TURNO ID : " + id);
            repository.deleteById(id);

        } else {
            logger.info("ID: " + id + " NO EXISTE");
        }
    }

    //ENTIDAD A DTO
    private TurnoDto mapEntityToDto(Turno turno) {
        TurnoDto dto = new TurnoDto();
        dto.setId(turno.getId());
        dto.setFecha(turno.getFecha());
        dto.setOdontologoId(turno.getOdontologo().getId());
        dto.setPacienteId(turno.getPaciente().getId());

        return dto;

    }

    //DTO A ENTIDAD
    private Turno mapDtoToEntity(TurnoDto dto) {
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        odontologo.setId(dto.getOdontologoId());
        paciente.setId(dto.getPacienteId());

        turno.setId(dto.getId());
        turno.setFecha(dto.getFecha());
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }


}

