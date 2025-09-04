package com.ipss.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="practicas")
public class Practica {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false) @JoinColumn(name="alumno_id")
  @NotNull
  private Usuario alumno;

  @ManyToOne(optional=true) @JoinColumn(name="profesor_id")
  private Usuario profesor; // opcional

  private String carrera;
  @Column(length = 2000)
  private String descripcion;

  private LocalDate fechaInicio;
  private LocalDate fechaFin;

  // Empresa
  @NotBlank(message = "empresa no debe estar vacío")
  private String empresaNombre;

  private String empresaDireccion;
  private String empresaTelefono;

  // Jefe directo
  private String jefeDirectoNombre;
  private String jefeDirectoContacto;

  public Long getId() { return id; }
  public Usuario getAlumno() { return alumno; }
  public Usuario getProfesor() { return profesor; }
  public String getCarrera() { return carrera; }
  public String getDescripcion() { return descripcion; }
  public LocalDate getFechaInicio() { return fechaInicio; }
  public LocalDate getFechaFin() { return fechaFin; }
  public String getEmpresaNombre() { return empresaNombre; }
  public String getEmpresaDireccion() { return empresaDireccion; }
  public String getEmpresaTelefono() { return empresaTelefono; }
  public String getJefeDirectoNombre() { return jefeDirectoNombre; }
  public String getJefeDirectoContacto() { return jefeDirectoContacto; }

  public void setId(Long id) { this.id = id; }
  public void setAlumno(Usuario alumno) { this.alumno = alumno; }
  public void setProfesor(Usuario profesor) { this.profesor = profesor; }
  public void setCarrera(String carrera) { this.carrera = carrera; }
  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
  public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }
  public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
  public void setEmpresaNombre(String empresaNombre) { this.empresaNombre = empresaNombre; }
  public void setEmpresaDireccion(String empresaDireccion) { this.empresaDireccion = empresaDireccion; }
  public void setEmpresaTelefono(String empresaTelefono) { this.empresaTelefono = empresaTelefono; }
  public void setJefeDirectoNombre(String jefeDirectoNombre) { this.jefeDirectoNombre = jefeDirectoNombre; }
  public void setJefeDirectoContacto(String jefeDirectoContacto) { this.jefeDirectoContacto = jefeDirectoContacto; }
}
