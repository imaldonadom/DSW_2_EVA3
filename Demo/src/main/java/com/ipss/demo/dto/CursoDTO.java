package com.ipss.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoDTO(
        Long id,
        @NotBlank String nombre,
        @NotBlank String codigo
) {}
