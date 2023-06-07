package com.dh.clinica.repository;

import com.dh.clinica.dto.OdontologoDto;
import com.dh.clinica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

}

