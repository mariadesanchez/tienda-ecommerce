package com.dh.clinica.repository;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {



    Optional<Paciente> findByDni (Integer dni);
}
