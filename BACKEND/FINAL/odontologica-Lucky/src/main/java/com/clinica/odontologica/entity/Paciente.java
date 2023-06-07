package com.dh.clinica.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Pacientes")
public class Paciente {

    @Id
    @SequenceGenerator(name="paciente_id",sequenceName="paciente_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="paciente_id")
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fechaAlta;
//----------------------------------------------RELACION ONE TO ONE PACIENTE- DOMICILIO-------------------------------------------//

    @OneToOne(cascade = {CascadeType.ALL})
    private Domicilio domicilio;
//---------------------------------------------RELACION ONE TO MANY PACIENTE - TURNOS --------------------------------------------//

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Turno> turnos = new ArrayList<>();

}
