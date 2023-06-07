package com.dh.clinica.services;
import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service

public class OdontologoService {

    @Autowired
    private OdontologoRepository repository;
    
    private final static Logger logger = Logger.getLogger(String.valueOf(OdontologoService.class));
    
    ArrayList ListDto = new ArrayList();
    public List<OdontologoDto> getAll() throws ResourceNotFoundException {
        if (repository.findAll().size() > 0) {

            for (Odontologo o :
                    repository.findAll()) {
                ListDto.add(mapEntityToDto(o));
                logger.info("GETTING ALL ODONTOLOGOS: " + mapEntityToDto(o).getNombre() + ' ' + mapEntityToDto(o).getApellido());

            }
            return ListDto;
        }
        throw new ResourceNotFoundException("No hay Datos que retornar");
    }

    public OdontologoDto getByID(Long id) {
        Odontologo odontologoSearch= repository.findById(id).orElse(null);

            return mapEntityToDto(odontologoSearch);

    }
    public Optional<Odontologo> buscarOdontologoPorMatricula(Long matricula) throws ResourceNotFoundException {
        if (matricula > 0) {
            return repository.findByMatricula(matricula);
        } else {
            throw new ResourceNotFoundException("El Dato de matrícula Debe ser un Número Mayor a Cero");

        }
    }
    public OdontologoDto guardar(Odontologo odontologo) throws ResourceNotFoundException {
        if (odontologo.getNombre()==null) {
            throw new ResourceNotFoundException("El Nombre del Odontólogo no puede ser nulo");}

         else if (odontologo.getNombre().length() < 10) {
            throw new ResourceNotFoundException("Nombre del Odontólogo debe ser un mayor a 10 dígitos");}

        else if (odontologo.getApellido()==null) {
            throw new ResourceNotFoundException("El Apellido del Odontólogo no puede ser nulo");}

        else if (odontologo.getApellido().length() < 10) {
            throw new ResourceNotFoundException("Apellido del Odontólogo debe ser un mayor a 10 dígitos");}

//        else if ((odontologo.getMatricula())==null) {
//            throw new ResourceNotFoundException("La matrícula del Odontólogo no puede ser nula");}

         else if (String.valueOf(odontologo.getMatricula()).length()< 6) {
            throw new ResourceNotFoundException("La matrícula del Odontólogo debe ser mayor a 5 dígitos");}

            else if (odontologo.getMatricula()< 0) {
                throw new ResourceNotFoundException("La matrícula del Odontólogo debe ser un número positivo");}

          else {
            Odontologo odontologoSave = repository.save(odontologo);
            logger.info("SAVING ODONTOLOGO: " + odontologo.getNombre() + " " + odontologo.getApellido());
            return mapEntityToDto(odontologo);
        }
    }

    public OdontologoDto actualizar(long id, Odontologo odontologo) {
        Odontologo odontologoUpDate = repository.findById(id).orElse(null);

            odontologoUpDate.setApellido(odontologo.getApellido());
            odontologoUpDate.setNombre(odontologo.getNombre());
            repository.save(odontologoUpDate);
            return mapEntityToDto(odontologoUpDate);

    }
    public void eliminar (long id) {

        Optional<Odontologo> odontologo = repository.findById(id);
        if (odontologo.isPresent()) {
            logger.info("DELETE ODONTOLOGO ID : " + id);
            repository.deleteById(id);

        } else {
            logger.info("ID: " + id + " NO EXISTE");
        }
    }

    //ENTIDAD A DTO
    private OdontologoDto mapEntityToDto(Odontologo odontologo) {
            OdontologoDto dto = new OdontologoDto();
            dto.setId(odontologo.getId());
            dto.setNombre(odontologo.getNombre());
            dto.setApellido(odontologo.getApellido());
            return dto;

    }

    //DTO A ENTIDAD
    private Odontologo mapDtoToEntity(Odontologo dto) {
            Odontologo odontologo = new Odontologo();
            odontologo.setId(dto.getId());
            odontologo.setNombre(dto.getNombre());
            odontologo.setApellido(dto.getApellido());
            return odontologo;
    }


}

