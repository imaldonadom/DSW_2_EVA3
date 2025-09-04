package com.ipss.demo.service;

import com.ipss.demo.dto.InscripcionCreateDTO;
import com.ipss.demo.dto.InscripcionResponse;
import com.ipss.demo.model.Curso;
import com.ipss.demo.model.Inscripcion;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.CursoRepository;
import com.ipss.demo.repository.InscripcionRepository;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {
  private final InscripcionRepository repo;
  private final UsuarioRepository usuarios;
  private final CursoRepository cursos;

  public InscripcionService(InscripcionRepository repo, UsuarioRepository usuarios, CursoRepository cursos) {
    this.repo = repo; this.usuarios = usuarios; this.cursos = cursos;
  }

  public List<InscripcionResponse> listar() {
    return repo.findAll().stream().map(i -> {
      InscripcionResponse r = new InscripcionResponse();
      r.id = i.getId();
      r.alumnoId = i.getAlumno().getId();
      r.alumnoNombre = i.getAlumno().getNombre();
      r.cursoId = i.getCurso().getId();
      r.cursoCodigo = i.getCurso().getCodigo();
      r.cursoNombre = i.getCurso().getNombre();
      return r;
    }).toList();
  }

  public InscripcionResponse crear(InscripcionCreateDTO dto) {
    Usuario alumno = usuarios.findById(dto.alumnoId).orElseThrow();
    Curso curso = cursos.findById(dto.cursoId).orElseThrow();
    Inscripcion i = repo.save(new Inscripcion(alumno, curso));
    InscripcionResponse r = new InscripcionResponse();
    r.id = i.getId();
    r.alumnoId = alumno.getId(); r.alumnoNombre = alumno.getNombre();
    r.cursoId = curso.getId(); r.cursoCodigo = curso.getCodigo(); r.cursoNombre = curso.getNombre();
    return r;
  }

  public void eliminar(Long id) { repo.deleteById(id); }
}
