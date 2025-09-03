package com.ipss.demo.service;

import com.ipss.demo.model.Rol;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repo;
    public UsuarioService(UsuarioRepository repo) { this.repo = repo; }

    public List<Usuario> listar() { return repo.findAll(); }
    public List<Usuario> listarPorRol(Rol rol) { return repo.findByRol(rol); }
    public Usuario crear(Usuario u) { return repo.save(u); }
    public Usuario buscar(Long id) { return repo.findById(id).orElse(null); }
    public void eliminar(Long id) { repo.deleteById(id); }
    public Usuario actualizar(Long id, Usuario data) {
        return repo.findById(id).map(u -> {
            u.setNombre(data.getNombre());
            u.setEmail(data.getEmail());
            u.setRol(data.getRol());
            return repo.save(u);
        }).orElse(null);
    }
}
