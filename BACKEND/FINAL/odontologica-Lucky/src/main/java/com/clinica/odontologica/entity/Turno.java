package com.dh.clinica.entity;


import com.dh.clinica.entity.Odontologo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Turnos")
public class Turno {

    @Id
    @SequenceGenerator(name="turno_id",sequenceName="turno_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="turno_id")
    @Column(name="id")
    private long id;


    //    @Column(name = "id_paciente")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @Column
    private LocalDate fecha;


    public Turno() {
    }

    public Long getOdontologoId () {
        return odontologo.getId();
    }
    public Long getPacienteId () {
        return odontologo.getId();
    }


   
}

