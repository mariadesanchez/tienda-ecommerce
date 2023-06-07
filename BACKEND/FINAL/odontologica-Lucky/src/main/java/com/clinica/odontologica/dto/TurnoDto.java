package com.dh.clinica.dto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class TurnoDto {
    private Long id;
//    private Paciente paciente;
//    private Odontologo odontologo;
    private Long pacienteId;
    private Long odontologoId;
    public LocalDate fecha;


    public TurnoDto () {
    }



}
