package com.ipss.demo.controller;

import com.ipss.demo.model.Curso;
import com.ipss.demo.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@CrossOrigin(origins="*")
public class CursoController {

  private final CursoService service;
  public CursoController(CursoService service) { this.service = service; }

  @GetMapping public List<Curso> listar() { return service.listar(); }
  @PostMapping public Curso crear(@Valid @RequestBody Curso c) { return service.crear(c); }
  @PutMapping("/{id}") public Curso actualizar(@PathVariable Long id, @Valid @RequestBody Curso c) { return service.actualizar(id, c); }
  @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
