package com.forohub.foroparaalura.service;

import com.forohub.foroparaalura.controller.Categoria;
import com.forohub.foroparaalura.domain.Curso;
import com.forohub.foroparaalura.dto.DatosRegistroCurso;
import com.forohub.foroparaalura.repository.CursoRepository;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso registrarCurso(DatosRegistroCurso dto) {
        // Evitar cursos duplicados por nombre
        if (cursoRepository.existsByNombre(dto.nombre())) {
            throw new RuntimeException("Ya existe un curso con ese nombre");
        }

        // Crear el curso usando el enum de categoría
        Categoria categoria = dto.categoria(); // dto.categoria() ya es un enum
        Curso curso = Curso.builder()
                .nombre(dto.nombre())
                .categoria(categoria)
                .build();

        return cursoRepository.save(curso);
    }
}

