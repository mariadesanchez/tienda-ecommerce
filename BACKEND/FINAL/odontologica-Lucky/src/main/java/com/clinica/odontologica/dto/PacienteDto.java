package com.dh.clinica.dto;
import com.dh.clinica.entity.Domicilio;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaAlta;
    private Domicilio domicilio;

    public PacienteDto () {
    }

}
