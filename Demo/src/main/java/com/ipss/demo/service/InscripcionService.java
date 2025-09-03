package com.ipss.demo.service;

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
    private final UsuarioRepository usuarioRepo;
    private final CursoRepository cursoRepo;

    public InscripcionService(InscripcionRepository repo, UsuarioRepository usuarioRepo, CursoRepository cursoRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
        this.cursoRepo = cursoRepo;
    }

    public List<Inscripcion> listar() { return repo.findAll(); }

    public Inscripcion inscribir(Long alumnoId, Long cursoId) {
        Usuario alumno = usuarioRepo.findById(alumnoId).orElse(null);
        Curso curso = cursoRepo.findById(cursoId).orElse(null);
        if (alumno == null || curso == null) return null;
        return repo.findByAlumnoAndCurso(alumno, curso)
                .orElseGet(() -> repo.save(new Inscripcion(alumno, curso)));
    }

    public void eliminar(Long id) { repo.deleteById(id); }

    public List<Inscripcion> porAlumno(Long alumnoId) {
        Usuario alumno = usuarioRepo.findById(alumnoId).orElse(null);
        return alumno == null ? List.of() : repo.findByAlumno(alumno);
    }

    public List<Inscripcion> porCurso(Long cursoId) {
        Curso curso = cursoRepo.findById(cursoId).orElse(null);
        return curso == null ? List.of() : repo.findByCurso(curso);
    }
}
