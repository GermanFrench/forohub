package com.forohub.foroparaalura.dto;

import com.forohub.foroparaalura.controller.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(

        @NotBlank
        String nombre,

        //Fijarme despues si lleva otra anotacion mas por ser un enum.
        @NotNull
        Categoria categoria
) {
}
