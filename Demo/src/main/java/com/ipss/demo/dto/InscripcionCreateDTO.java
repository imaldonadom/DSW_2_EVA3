package com.ipss.demo.dto;

import jakarta.validation.constraints.NotNull;

public record InscripcionCreateDTO(
        @NotNull Long alumnoId,
        @NotNull Long cursoId
) {}
