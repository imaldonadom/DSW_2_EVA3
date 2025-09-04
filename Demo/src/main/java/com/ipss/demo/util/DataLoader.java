package com.ipss.demo.util;

import com.ipss.demo.model.Curso;
import com.ipss.demo.model.Inscripcion;
import com.ipss.demo.model.Rol;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.CursoRepository;
import com.ipss.demo.repository.InscripcionRepository;
import com.ipss.demo.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    private final UsuarioRepository usuarios;
    private final CursoRepository cursos;
    private final InscripcionRepository inscripciones;

    public DataLoader(UsuarioRepository usuarios,
                      CursoRepository cursos,
                      InscripcionRepository inscripciones) {
        this.usuarios = usuarios;
        this.cursos = cursos;
        this.inscripciones = inscripciones;
    }

    @PostConstruct
    public void init() {
        // evita duplicar semillas si ya hay usuarios
        if (usuarios.count() > 0) return;

        // ===== Usuarios
        usuarios.save(new Usuario("Admin Eva",     "admin@eva3.cl",  Rol.ADMIN));
        usuarios.save(new Usuario("María Profesor","maria@eva3.cl",  Rol.PROFESOR));
        Usuario igna  = usuarios.save(new Usuario("Ignacio Alumno", "ignacio@eva3.cl", Rol.ALUMNO));
        Usuario jorge = usuarios.save(new Usuario("Jorge Alumno",   "jorge@eva3.cl",   Rol.ALUMNO));

        // ===== Cursos
        Curso dw2 = cursos.save(new Curso("DW2-001", "Desarrollo Web 2"));
        Curso ac  = cursos.save(new Curso("AC-101",  "Arquitectura Cloud"));

        // ===== Inscripciones de ejemplo (para que haya datos cruzados)
        inscripciones.save(new Inscripcion(igna,  dw2));
        inscripciones.save(new Inscripcion(jorge, ac));
    }
}
