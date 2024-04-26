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
public class Grado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String nombre;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    public Profesor profesorId;

}
