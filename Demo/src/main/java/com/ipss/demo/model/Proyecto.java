package com.ipss.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "proyectos")
public class Proyecto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @Column(length = 1000)
  private String descripcion;

  private LocalDate fechaInicio;
  private LocalDate fechaFin;

  private String estado; // CREADO, EN_PROCESO, FINALIZADO

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
