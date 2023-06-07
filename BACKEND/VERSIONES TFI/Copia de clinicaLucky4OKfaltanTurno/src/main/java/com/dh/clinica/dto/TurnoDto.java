package com.dh.clinica.dto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TurnoDto {
    private Long id;
    public Odontologo odontologo;
    public Paciente paciente;
    public Date fecha;
    private String hora;
    private String dia;
    private String mes;
    private String anio;

    public TurnoDto () {
    }
}
