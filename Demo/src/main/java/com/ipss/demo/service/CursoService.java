package com.ipss.demo.service;

import com.ipss.demo.model.Curso;
import com.ipss.demo.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
  private final CursoRepository repo;
  public CursoService(CursoRepository repo) { this.repo = repo; }

  public List<Curso> listar() { return repo.findAll(); }
  public Curso crear(Curso c) { return repo.save(c); }
  public Curso actualizar(Long id, Curso data) {
    Curso c = repo.findById(id).orElseThrow();
    if (data.getCodigo()!=null) c.setCodigo(data.getCodigo());
    if (data.getNombre()!=null) c.setNombre(data.getNombre());
    return repo.save(c);
  }
  public void eliminar(Long id) { repo.deleteById(id); }
}
