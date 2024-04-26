package dev.humberto.crud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoGrado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;


    @ManyToOne
    @JoinColumn(name = "alumno_id")
    public Alumno alumnoId;

    @ManyToOne
    @JoinColumn(name = "grado_id")
    public Grado gradoId;

    public String seccion;
}
