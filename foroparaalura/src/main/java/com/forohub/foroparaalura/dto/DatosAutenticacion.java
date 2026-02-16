package com.forohub.foroparaalura.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(

        @NotBlank
        String login,

        @NotBlank
        String password
) {
}
