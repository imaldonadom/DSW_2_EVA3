package com.ipss.demo.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class ProyectoRequest {
  @NotBlank public String nombre;
  public String descripcion;
  public LocalDate fechaInicio;
  public LocalDate fechaFin;
  public String estado;
}
