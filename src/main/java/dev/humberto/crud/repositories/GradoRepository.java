package dev.humberto.crud.repositories;

import dev.humberto.crud.model.Grado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradoRepository extends JpaRepository<Grado, Long> {
}
