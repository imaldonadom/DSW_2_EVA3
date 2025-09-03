package com.ipss.demo.service;

import com.ipss.demo.exception.DuplicateEmailException;
import com.ipss.demo.exception.ResourceNotFoundException;
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

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public List<Usuario> listarPorRol(Rol rol) {
        return repo.findByRol(rol);
    }

    @Transactional(readOnly = true)
    public Usuario buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado id=" + id));
    }

    public Usuario crear(Usuario u) {
        // normaliza email
        if (u.getEmail() != null) {
            u.setEmail(u.getEmail().trim().toLowerCase());
        }
        if (repo.existsByEmail(u.getEmail())) {
            throw new DuplicateEmailException("Email duplicado: " + u.getEmail());
        }
        return repo.save(u);
    }

    public Usuario actualizar(Long id, Usuario data) {
        Usuario u = buscar(id); // lanza 404 si no existe

        // validar duplicados sólo si cambió el email
        String email = data.getEmail() != null ? data.getEmail().trim().toLowerCase() : null;
        if (email != null && repo.existsByEmailAndIdNot(email, id)) {
            throw new DuplicateEmailException("Email duplicado: " + email);
        }

        if (data.getNombre() != null) u.setNombre(data.getNombre());
        if (email != null)           u.setEmail(email);
        if (data.getRol() != null)   u.setRol(data.getRol());

        return repo.save(u);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado id=" + id);
        }
        repo.deleteById(id);
    }
}
