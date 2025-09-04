package com.ipss.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="cursos", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Curso {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String codigo;

  @NotBlank
  private String nombre;

  public Curso() {}
  public Curso(String codigo, String nombre) {
    this.codigo = codigo; this.nombre = nombre;
  }

  public Long getId() { return id; }
  public String getCodigo() { return codigo; }
  public String getNombre() { return nombre; }

  public void setId(Long id) { this.id = id; }
  public void setCodigo(String codigo) { this.codigo = codigo; }
  public void setNombre(String nombre) { this.nombre = nombre; }
}
