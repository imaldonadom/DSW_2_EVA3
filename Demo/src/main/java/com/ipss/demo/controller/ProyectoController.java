package com.ipss.demo.controller;

import com.ipss.demo.dto.ProyectoRequest;
import com.ipss.demo.dto.ProyectoResponse;
import com.ipss.demo.service.ProyectoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

  private final ProyectoService service;

  public ProyectoController(ProyectoService service){ this.service = service; }

  @GetMapping
  public Page<ProyectoResponse> listar(@ParameterObject Pageable pageable){
    return service.listar(pageable).map(ProyectoResponse::of);
  }

  @GetMapping("/page")
  public Page<ProyectoResponse> listarPage(@ParameterObject Pageable pageable){
    return service.listar(pageable).map(ProyectoResponse::of);
  }

  @GetMapping("/search")
  public Page<ProyectoResponse> buscar(
      @RequestParam(required = false) String estado,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta,
      @ParameterObject Pageable pageable){
    return service.buscar(estado, desde, hasta, pageable).map(ProyectoResponse::of);
  }

  @PostMapping
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<ProyectoResponse> crear(@Valid @RequestBody ProyectoRequest r){
    return ResponseEntity.ok(ProyectoResponse.of(service.crear(r)));
  }

  @PutMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<ProyectoResponse> actualizar(@PathVariable Long id, @RequestBody ProyectoRequest r){
    return ResponseEntity.ok(ProyectoResponse.of(service.actualizar(id, r)));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<Void> eliminar(@PathVariable Long id){
    service.eliminar(id);
    return ResponseEntity.noContent().build();
  }
}
