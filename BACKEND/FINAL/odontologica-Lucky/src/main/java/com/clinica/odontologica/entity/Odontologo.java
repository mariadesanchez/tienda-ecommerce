package com.dh.clinica.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Odontologos")
public class Odontologo {
    @Id
    @SequenceGenerator(name="odontologo_id",sequenceName="odontologo_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="odontologo_id")
    private long id;
    private String nombre;
    private String apellido;
    private Long matricula;

    //-----------------------------------------------RELACION ONO TO MANY ODONTOLOGO - TUNOS --------------------------------------------//

    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Turno> turnos = new ArrayList<>();
}
