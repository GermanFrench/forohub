package com.forohub.foroparaalura.dto;

import java.time.LocalDateTime;

public record TopicoResponseDto(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaDeCreacion,
        String status,
        AutorDTO autor,
        CursoDTO curso
) {
}
