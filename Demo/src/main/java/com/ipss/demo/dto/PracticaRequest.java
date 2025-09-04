package com.ipss.demo.dto;

import jakarta.validation.constraints.NotNull;

public class PracticaRequest {
  @NotNull public Long alumnoId;
  public Long profesorId;          // opcional
  public String carrera;
  public String descripcion;
  public String fechaInicio;       // ISO yyyy-MM-dd
  public String fechaFin;

  @NotNull public String empresaNombre;
  public String empresaDireccion;
  public String empresaTelefono;

  public String jefeDirectoNombre;
  public String jefeDirectoContacto;
}
