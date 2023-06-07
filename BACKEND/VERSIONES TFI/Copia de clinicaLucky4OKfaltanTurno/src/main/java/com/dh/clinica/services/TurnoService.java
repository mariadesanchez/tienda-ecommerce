package com.dh.clinica.services;
import com.dh.clinica.dto.TurnoDto;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service

public class TurnoService {

    @Autowired
    private TurnoRepository repository;
    private final static Logger logger = Logger.getLogger(String.valueOf(TurnoService.class));
    ArrayList ListDto = new ArrayList();
    public List<TurnoDto> getAll() {
        if (repository.findAll().size() > 0) {

            for (Turno t :
                    repository.findAll()) {
                ListDto.add(mapEntityToDto(t));
                logger.info("GETTING ALL TURNOS: " + mapEntityToDto(t).getHora() +' ' + mapEntityToDto(t).getDia()+' ' + mapEntityToDto(t).getMes()+' '
                        + mapEntityToDto(t).getAnio());




            }
            return ListDto;
        }
        return null;
    }

    public TurnoDto getByID(Long id) {
        Turno turnoSearch =  repository.findById(id).orElse(null);
        return mapEntityToDto(turnoSearch);
    }

    public TurnoDto guardar(Turno turno) {
        Turno TurnoSave = repository.save(turno);
        return mapEntityToDto(turno);
    }

    public TurnoDto actualizar(long id, Turno turno) {
        Turno turnoUpDate = repository.findById(id).orElse(null);
        if (turnoUpDate != null) {
            turnoUpDate.setHora(turno.getHora());
            turnoUpDate.setDia(turno.getDia());
            turnoUpDate.setMes(turno.getMes());
            turnoUpDate.setAnio(turno.getAnio());
            repository.save(turnoUpDate);
            return mapEntityToDto(turnoUpDate);
        }
        return null;
    }
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    //ENTIDAD A DTO
    private TurnoDto mapEntityToDto(Turno turno) {
        TurnoDto dto = new TurnoDto();
        dto.setId(turno.getId());
        dto.setHora(turno.getHora());
        dto.setDia(turno.getDia());
        dto.setMes(turno.getMes());
        dto.setAnio(turno.getAnio());
        return dto;

    }

    //DTO A ENTIDAD
    private Turno mapDtoToEntity(Turno dto) {
        Turno turno = new Turno();
        turno.setId(dto.getId());
        turno.setHora(dto.getHora());
        turno.setDia(dto.getDia());
        turno.setMes(dto.getMes());
        turno.setAnio(dto.getAnio());
        return turno;
    }


}

