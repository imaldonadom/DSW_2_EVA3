package com.ipss.demo.dto;

import jakarta.validation.constraints.NotNull;

public class InscripcionCreateDTO {
  @NotNull public Long alumnoId;
  @NotNull public Long cursoId;
}
