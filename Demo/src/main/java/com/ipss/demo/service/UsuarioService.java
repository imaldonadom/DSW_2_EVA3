package com.ipss.demo.service;

import com.ipss.demo.model.Rol;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    // ===== CRUD =====

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public List<Usuario> listarPorRol(Rol rol) {
        return repo.findByRol(rol);
    }

    public Usuario buscar(Long id) {
        return repo.findById(id).orElseThrow(() ->
            new IllegalArgumentException("Usuario no encontrado id=" + id));
    }

    public Usuario crear(Usuario u) {
        String email = u.getEmail() == null ? null : u.getEmail().trim();
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email es obligatorio");
        }
        if (repo.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email duplicado: " + email);
        }
        // normalizamos email a minúsculas si quieres
        u.setEmail(email);
        return repo.save(u);
    }

    public Usuario actualizar(Long id, Usuario data) {
        Usuario u = buscar(id); // lanza si no existe

        // Validar duplicado sólo si cambió el email
        String nuevoEmail = data.getEmail() == null ? null : data.getEmail().trim();
        if (nuevoEmail != null && !nuevoEmail.equalsIgnoreCase(u.getEmail())) {
            if (repo.existsByEmailIgnoreCaseAndIdNot(nuevoEmail, id)) {
                throw new IllegalArgumentException("Email duplicado: " + nuevoEmail);
            }
            u.setEmail(nuevoEmail);
        }

        if (data.getNombre() != null) u.setNombre(data.getNombre());
        if (data.getRol() != null)     u.setRol(data.getRol());

        return repo.save(u);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Usuario no encontrado id=" + id);
        }
        repo.deleteById(id);
    }
}
