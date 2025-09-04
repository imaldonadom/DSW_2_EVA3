package com.ipss.demo.controller;

import com.ipss.demo.dto.InscripcionCreateDTO;
import com.ipss.demo.dto.InscripcionResponse;
import com.ipss.demo.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@CrossOrigin(origins="*")
public class InscripcionController {

  private final InscripcionService service;
  public InscripcionController(InscripcionService service) { this.service = service; }

  @GetMapping public List<InscripcionResponse> listar() { return service.listar(); }
  @PostMapping public InscripcionResponse crear(@Valid @RequestBody InscripcionCreateDTO dto) { return service.crear(dto); }
  @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
