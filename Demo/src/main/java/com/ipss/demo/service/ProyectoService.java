package com.ipss.demo.service;

import com.ipss.demo.model.Proyecto;
import com.ipss.demo.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProyectoService {

  private final ProyectoRepository repo;

  public ProyectoService(ProyectoRepository repo) { this.repo = repo; }

  public List<Proyecto> listar() { return repo.findAll(); }

  public Proyecto crear(Proyecto p) { return repo.save(p); }

  public Proyecto obtener(Long id) { return repo.findById(id).orElse(null); }

  public Proyecto actualizar(Long id, Proyecto p) {
    Proyecto db = obtener(id);
    if (db == null) return null;
    db.setNombre(p.getNombre());
    db.setDescripcion(p.getDescripcion());
    db.setFechaInicio(p.getFechaInicio());
    db.setFechaFin(p.getFechaFin());
    db.setEstado(p.getEstado());
    return repo.save(db);
  }

  public void eliminar(Long id) { repo.deleteById(id); }
}
