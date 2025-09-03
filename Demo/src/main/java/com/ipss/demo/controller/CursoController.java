package com.ipss.demo.controller;

import com.ipss.demo.dto.CursoDTO;
import com.ipss.demo.model.Curso;
import com.ipss.demo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    private final CursoService service;
    public CursoController(CursoService service) { this.service = service; }

    @GetMapping
    public List<Curso> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Curso obtener(@PathVariable Long id) { return service.buscar(id); }

    @PostMapping
    public Curso crear(@Valid @RequestBody CursoDTO dto) {
        return service.crear(new Curso(dto.nombre(), dto.codigo()));
    }

    @PutMapping("/{id}")
    public Curso actualizar(@PathVariable Long id, @Valid @RequestBody CursoDTO dto) {
        return service.actualizar(id, new Curso(dto.nombre(), dto.codigo()));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
