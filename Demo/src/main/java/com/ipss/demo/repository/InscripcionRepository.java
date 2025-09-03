package com.ipss.demo.repository;

import com.ipss.demo.model.Curso;
import com.ipss.demo.model.Inscripcion;
import com.ipss.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByAlumno(Usuario alumno);
    List<Inscripcion> findByCurso(Curso curso);
    Optional<Inscripcion> findByAlumnoAndCurso(Usuario alumno, Curso curso);
}
