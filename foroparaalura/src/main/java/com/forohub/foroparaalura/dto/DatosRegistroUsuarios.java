package com.forohub.foroparaalura.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuarios(

        @NotBlank
        String nombre,

        @NotBlank
        @Email
        String login,

        @NotBlank
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
                message = "La contraseña debe tener mayúscula, minúscula, número y un carácter especial"
        )
        String password

) {}
