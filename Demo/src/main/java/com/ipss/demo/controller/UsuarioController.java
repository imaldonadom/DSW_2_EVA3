package com.ipss.demo.controller;

import com.ipss.demo.model.Rol;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

  private final UsuarioService service;
  public UsuarioController(UsuarioService service) { this.service = service; }

  @GetMapping public List<Usuario> listar() { return service.listar(); }
  @GetMapping("/{id}") public Usuario obtener(@PathVariable Long id) { return service.buscar(id); }
  @PostMapping public Usuario crear(@Valid @RequestBody Usuario u) { return service.crear(u); }
  @PutMapping("/{id}") public Usuario actualizar(@PathVariable Long id, @Valid @RequestBody Usuario u) { return service.actualizar(id, u); }
  @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.eliminar(id); }

  @GetMapping("/rol/{rol}") public List<Usuario> porRol(@PathVariable Rol rol) { return service.listarPorRol(rol); }
}
