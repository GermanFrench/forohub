package com.forohub.foroparaalura.dto;


import com.forohub.foroparaalura.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record DatosRegistroTopico(

        @NotBlank
        String titulo,

        @NotBlank
        @Size(min=5, max=200)
        String mensaje,

        @NotNull(message = "La fecha de creación es obligatoria")
        @PastOrPresent(message = "La fecha de creación no puede ser futura")
        LocalDateTime fecha_de_creacion,

        @NotNull(message = "El estado es obligatorio")
        Status status,

        @NotNull(message = "El autor es obligatorio")
        Long autorId,

        @NotNull(message = "El curso es obligatorio")
        Long cursoId



) {
}
