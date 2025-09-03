package com.ipss.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "inscripciones",
       uniqueConstraints = @UniqueConstraint(columnNames = {"alumno_id","curso_id"}))
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Alumno es un Usuario con rol ALUMNO
    @ManyToOne(optional = false)
    @JoinColumn(name = "alumno_id")
    @NotNull
    private Usuario alumno;

    @ManyToOne(optional = false)
    @JoinColumn(name = "curso_id")
    @NotNull
    private Curso curso;

    public Inscripcion() {}
    public Inscripcion(Usuario alumno, Curso curso) {
        this.alumno = alumno;
        this.curso = curso;
    }

    public Long getId() { return id; }
    public Usuario getAlumno() { return alumno; }
    public Curso getCurso() { return curso; }

    public void setId(Long id) { this.id = id; }
    public void setAlumno(Usuario alumno) { this.alumno = alumno; }
    public void setCurso(Curso curso) { this.curso = curso; }
}
