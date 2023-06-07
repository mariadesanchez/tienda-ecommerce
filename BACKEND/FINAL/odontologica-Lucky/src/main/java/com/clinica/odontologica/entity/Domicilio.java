package com.dh.clinica.entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;


@Getter
@Setter
@Entity
public class Domicilio {
    @Id
    @SequenceGenerator(name="domicilio_id",sequenceName="domicilio_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="domicilio_id")

    private Long id;
    private String calle;
    private Integer numero;
    private String localidad;
    private String provincia;


}

