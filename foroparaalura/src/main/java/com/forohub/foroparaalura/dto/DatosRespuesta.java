package com.forohub.foroparaalura.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuesta(

        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,

        @NotNull(message = "Debe informar el id del tópico")
        Long topicoId,

        @NotNull(message = "Debe informar el id del usuario")
        Long usuarioId

) {}
