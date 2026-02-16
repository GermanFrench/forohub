package com.forohub.foroparaalura.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotNull
        Long autorId,

        @NotNull
        Long cursoId

) {}
