package com.ipss.demo.controller;

import com.ipss.demo.dto.InscripcionCreateDTO;
import com.ipss.demo.model.Inscripcion;
import com.ipss.demo.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    private final InscripcionService service;
    public InscripcionController(InscripcionService service) { this.service = service; }

    @GetMapping
    public List<Inscripcion> listar() { return service.listar(); }

    @PostMapping
    public Inscripcion crear(@Valid @RequestBody InscripcionCreateDTO dto) {
        return service.inscribir(dto.alumnoId(), dto.cursoId());
    }

    @GetMapping("/alumno/{alumnoId}")
    public List<Inscripcion> porAlumno(@PathVariable Long alumnoId) {
        return service.porAlumno(alumnoId);
    }

    @GetMapping("/curso/{cursoId}")
    public List<Inscripcion> porCurso(@PathVariable Long cursoId) {
        return service.porCurso(cursoId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
