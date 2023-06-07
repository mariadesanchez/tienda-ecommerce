package com.dh.clinica.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne()
    @JoinColumn(name = "odontologo_id",nullable = false)
    private Odontologo odontologo;

    @ManyToOne()
    @JoinColumn(name = "paciente_id",nullable = false)
    private Odontologo paciente;

    private String hora;
    private String dia;
    private String mes;
    private String anio;


}

