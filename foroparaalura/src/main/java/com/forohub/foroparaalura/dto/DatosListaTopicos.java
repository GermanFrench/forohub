package com.forohub.foroparaalura.dto;

import com.forohub.foroparaalura.domain.Status;

import java.time.LocalDateTime;

public record DatosListaTopicos(

        String titulo,

        String mensaje,

        LocalDateTime fecha_de_creacion,

        Status status,

        DatosAutor autor,

        DatosCurso curso


) {
}
