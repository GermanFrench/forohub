package com.forohub.foroparaalura.repository;

import com.forohub.foroparaalura.domain.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
        SELECT t FROM Topico t
        WHERE t.curso.nombre = :nombreCurso
        AND FUNCTION('YEAR', t.fechaDeCreacion) = :anio
    """)
    List<Topico> buscarPorCursoYAnio(
            @Param("nombreCurso") String nombreCurso,
            @Param("anio") int anio
    );

    List<Topico> findTop10ByOrderByFechaDeCreacionAsc();

    boolean existsByTituloAndMensaje(@NotBlank String titulo, @NotBlank @Size(min=5, max=200) String mensaje);

    Page<Topico> findByActivoTrue(Pageable pageable);

    Optional<Topico> findByIdAndActivoTrue(Long id);

    List<Topico> findByActivoTrue();



}
