package com.forohub.foroparaalura.controller;

import com.forohub.foroparaalura.domain.Curso;
import com.forohub.foroparaalura.dto.DatosRegistroCurso;
import com.forohub.foroparaalura.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@RequestBody @Valid DatosRegistroCurso dto) {
        Curso cursoCreado = cursoService.registrarCurso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoCreado);
    }
}

