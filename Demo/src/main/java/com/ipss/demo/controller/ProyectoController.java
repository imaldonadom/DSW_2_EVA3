package com.ipss.demo.controller;

import com.ipss.demo.model.Proyecto;
import com.ipss.demo.service.ProyectoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

  private final ProyectoService service;

  public ProyectoController(ProyectoService service) {
    this.service = service;
  }

  @GetMapping
  public List<Proyecto> listar() {
    return service.listar();
  }

  @GetMapping("/{id}")
  public Proyecto obtener(@PathVariable Long id) {
    return service.obtener(id);
  }

  @PostMapping
  public Proyecto crear(@RequestBody Proyecto p) {
    return service.crear(p);
  }

  @PutMapping("/{id}")
  public Proyecto actualizar(@PathVariable Long id, @RequestBody Proyecto p) {
    return service.actualizar(id, p);
  }

  @DeleteMapping("/{id}")
  public void eliminar(@PathVariable Long id) {
    service.eliminar(id);
  }
}
