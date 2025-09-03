package com.ipss.demo.controller;

import com.ipss.demo.dto.UsuarioDTO;
import com.ipss.demo.model.Rol;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService service;

    // Spring inyecta el servicio (constructor injection)
    public UsuarioController(UsuarioService service) { this.service = service; }

    // GET /api/usuarios → lista completa
    @GetMapping
    public List<Usuario> listar() { return service.listar(); }

    // GET /api/usuarios/rol/{rol} → filtra por rol (ADMIN/ALUMNO/PROFESOR)
    // El PathVariable se convierte automáticamente al enum Rol
    @GetMapping("/rol/{rol}")
    public List<Usuario> listarPorRol(@PathVariable Rol rol) { return service.listarPorRol(rol); }

    // GET /api/usuarios/{id} → uno por id
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) { return service.buscar(id); }

    // POST /api/usuarios → crea. Devuelve 201 Created.
    // @Valid hace correr las validaciones del DTO (NotBlank, Email, etc.)
    @PostMapping
    public ResponseEntity<Usuario> crear(@Valid @RequestBody UsuarioDTO dto) {
        Usuario u = new Usuario(dto.nombre(), dto.email(), dto.rol());
        Usuario saved = service.crear(u);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/usuarios/{id} → actualiza y devuelve 200 OK con el usuario actualizado
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        Usuario data = new Usuario(dto.nombre(), dto.email(), dto.rol());
        return service.actualizar(id, data);
    }

    // DELETE /api/usuarios/{id} → 204 No Content (sin cuerpo)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) { service.eliminar(id); }
}
