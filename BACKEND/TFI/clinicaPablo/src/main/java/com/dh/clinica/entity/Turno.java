package com.dh.clinica.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne()
    @JoinColumn(name = "paciente_id")
    private Paciente pacienteT;

    @ManyToOne()
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologoT;

    private String hora;
    private String dia;
    private String mes;
    private String anio;
}

