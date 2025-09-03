package com.ipss.demo.dto;

import com.ipss.demo.model.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotNull Rol rol
) {}
