package com.dh.clinica.services;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service

public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public List<Turno> getAll() {
        return turnoRepository.findAll();
    }



    public Optional<Turno> getByID(Long id) {
        return turnoRepository.findById(id);
    }

    public Turno guardar(Turno turno) {

        return turnoRepository.save(turno);

    }

    public Turno actualizar(long id, Turno turno) {
        Turno turnoActualizar =  turnoRepository.findById(id).orElse(null);
        turnoActualizar.setHora(turno.getHora());
        turnoActualizar.setDia(turno.getDia());
        turnoActualizar.setMes(turno.getMes());
        turnoActualizar.setAnio(turno.getAnio());

        return turnoRepository.save(turnoActualizar);

    }


    public void eliminar(Long id) {

        turnoRepository.deleteById(id);
    }


}

