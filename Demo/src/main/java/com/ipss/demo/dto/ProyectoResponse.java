package com.ipss.demo.dto;

import java.time.LocalDate;

public class ProyectoResponse {
  private Long id;
  private String nombre;
  private String descripcion;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private String estado;

  public ProyectoResponse() { }

  public ProyectoResponse(Long id, String nombre, String descripcion,
                          LocalDate fechaInicio, LocalDate fechaFin, String estado) {
    this.id = id; this.nombre = nombre; this.descripcion = descripcion;
    this.fechaInicio = fechaInicio; this.fechaFin = fechaFin; this.estado = estado;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getNombre() { return nombre; }
  public void setNombre(String nombre) { this.nombre = nombre; }

  public String getDescripcion() { return descripcion; }
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

  public LocalDate getFechaInicio() { return fechaInicio; }
  public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

  public LocalDate getFechaFin() { return fechaFin; }
  public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }
}
