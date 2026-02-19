package com.forohub.foroparaalura.controller;

import com.forohub.foroparaalura.dto.DatosActualizarTopico;
import com.forohub.foroparaalura.dto.DatosRegistroTopico;
import com.forohub.foroparaalura.dto.TopicoResponseDto;
import com.forohub.foroparaalura.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    private final TopicoService topicoService;

    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    // =============================
    // CREAR TÓPICO
    // =============================
    @PostMapping
    public ResponseEntity<TopicoResponseDto> crear(
            @RequestBody @Valid DatosRegistroTopico datos) {

        TopicoResponseDto response = topicoService.crearTopico(datos);

        return ResponseEntity
                .created(URI.create("/topicos/" + response.id()))
                .body(response);
    }

    // =============================
    // LISTAR PAGINADO
    // =============================
    @GetMapping
    public ResponseEntity<Page<TopicoResponseDto>> listar(
            @PageableDefault(size = 10, sort = "fechaDeCreacion", direction = Sort.Direction.ASC)
            Pageable pageable) {

        return ResponseEntity.ok(topicoService.listar(pageable));
    }

    // =============================
    // LISTAR TODOS (SIN PAGINACIÓN)
    // =============================
    @GetMapping("/todos")
    public ResponseEntity<List<TopicoResponseDto>> listarTodos() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    // =============================
    // LISTAR PRIMEROS 10
    // =============================
    @GetMapping("/primeros")
    public ResponseEntity<List<TopicoResponseDto>> listarPrimeros() {
        return ResponseEntity.ok(topicoService.listarPrimeros10());
    }

    // =============================
    // BUSCAR POR CURSO Y AÑO
    // =============================
    @GetMapping("/buscar")
    public ResponseEntity<List<TopicoResponseDto>> buscarPorCursoYAnio(
            @RequestParam String curso,
            @RequestParam int anio) {

        return ResponseEntity.ok(
                topicoService.buscarPorCursoYAnio(curso, anio)
        );
    }

    // =============================
    // OBTENER POR ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> obtenerPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                topicoService.obtenerPorId(id)
        );
    }

    // =============================
    // ACTUALIZAR
    // =============================
    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDto> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {

        return ResponseEntity.ok(
                topicoService.actualizarTopico(id, datos)
        );
    }

    // =============================
    // ELIMINAR (SOFT DELETE)
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }



}
