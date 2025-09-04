package com.ipss.demo.controller;

import com.ipss.demo.dto.PracticaRequest;
import com.ipss.demo.dto.PracticaResponse;
import com.ipss.demo.service.PracticaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/practicas")
@CrossOrigin(origins = "*")
public class PracticaController {

  private final PracticaService service;

  public PracticaController(PracticaService service) { this.service = service; }

  @GetMapping
  public List<PracticaResponse> listar() { return service.listar(); }

  @GetMapping("/alumno/{alumnoId}")
  public List<PracticaResponse> listarPorAlumno(@PathVariable Long alumnoId) {
    return service.listarPorAlumno(alumnoId);
  }

  @GetMapping("/{id}")
  public PracticaResponse obtener(@PathVariable Long id) { return service.buscar(id); }

  @PostMapping
  public ResponseEntity<PracticaResponse> crear(@Valid @RequestBody PracticaRequest req) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(req));
  }

  @PutMapping("/{id}")
  public PracticaResponse actualizar(@PathVariable Long id, @Valid @RequestBody PracticaRequest req) {
    return service.actualizar(id, req);
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
