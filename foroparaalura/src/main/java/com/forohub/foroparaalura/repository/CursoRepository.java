package com.forohub.foroparaalura.repository;

import com.forohub.foroparaalura.domain.Curso;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNombre(@NotBlank String nombre);
}
