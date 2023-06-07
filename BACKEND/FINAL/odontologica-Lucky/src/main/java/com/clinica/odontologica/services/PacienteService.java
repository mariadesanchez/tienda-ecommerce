package com.dh.clinica.services;
import com.dh.clinica.dto.PacienteDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exceptions.GlobalExceptionHandler;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service

public class PacienteService {

    @Autowired
    private PacienteRepository repository;
    private final static Logger logger = Logger.getLogger(String.valueOf(PacienteService.class));
    ArrayList ListDto = new ArrayList();

    public List<PacienteDto> getAll () throws ResourceNotFoundException {
        if (repository.findAll().size() > 0) {
            for (Paciente p :
                    repository.findAll()) {
                ListDto.add(mapEntityToDto(p));
                logger.info("GETTING ALL PACIENTES: " + mapEntityToDto(p).getNombre() + ' ' + mapEntityToDto(p).getApellido());
            }

            return ListDto;
        }
        throw new ResourceNotFoundException("No hay Datos que retornar");
    }

    public PacienteDto getByID (Long id) {
       Paciente pacienteSearch = repository.findById(id).orElse(null);

            logger.info("GETTING  PACIENTE ID : " + id);
            return mapEntityToDto(pacienteSearch);

    }

    public PacienteDto guardar (Paciente paciente) throws GlobalExceptionHandler, ResourceNotFoundException {
        if (paciente.getNombre().length() < 10) {
            throw new ResourceNotFoundException("Nombre del Paciente debe ser un mayor a 10 dígitos");

        } else if (paciente.getApellido().length() < 10) {
            throw new ResourceNotFoundException("Apellido del Paciente debe ser un mayor a 10 dígitos");

        } else if (String.valueOf(paciente.getDni()).length() < 6) {
            throw new ResourceNotFoundException("DNI del Paciente debe ser un mayor a 5 dígitos");}

        else if (paciente.getDni()< 0) {
            throw new ResourceNotFoundException("El Dni del Paciente  debe ser un número positivo");}


        else if (paciente.getDomicilio()==null) {
            throw new ResourceNotFoundException("El Paciente debe tener un Domicilio Asociado");

        } else {
            Paciente pacienteSave = repository.save(paciente);
            logger.info("SAVING PACIENTE: " + paciente.getNombre() + " " + paciente.getApellido());
            return mapEntityToDto(paciente);
        }
    }


    public PacienteDto actualizar (long id, Paciente paciente) {
        Paciente pacienteUpDate = repository.findById(id).orElse(null);

            pacienteUpDate.setApellido(paciente.getApellido());
            pacienteUpDate.setNombre(paciente.getNombre());
            repository.save(pacienteUpDate);
            logger.info("UPDATING PACIENTE: " + paciente.getNombre() + " " + paciente.getApellido());
            return mapEntityToDto(pacienteUpDate);
        }


    public void eliminar (long id) {

        Optional<Paciente> paciente = repository.findById(id);
        if (paciente.isPresent()) {
            logger.info("DELETE PACIENTE ID : " + id);
            repository.deleteById(id);

        } else {
            logger.info("ID: " + id + " NO EXISTE");
        }
    }
    public Optional<Paciente> buscarPacientePorDni (Integer dni) throws ResourceNotFoundException {

        if (dni > 5) {
            return repository.findByDni(dni);
        } else if (dni < 0) {

            throw new ResourceNotFoundException("El Número de DNI no puede ser un número Negativo");
        } else {
            throw new ResourceNotFoundException("El Número de DNI debe ser mayor a 5 dígitos");
        }
    }

    //ENTIDAD A DTO
    private PacienteDto mapEntityToDto (Paciente paciente) {
        PacienteDto dto = new PacienteDto();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setFechaAlta(paciente.getFechaAlta());
        dto.setDomicilio(paciente.getDomicilio());
        return dto;

    }

    //DTO A ENTIDAD
    private Paciente mapDtoToEntity (Paciente dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setFechaAlta(dto.getFechaAlta());
        paciente.setDomicilio(dto.getDomicilio());
        return paciente;
    }



}





