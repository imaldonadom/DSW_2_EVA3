package com.ipss.demo.service;

import com.ipss.demo.dto.ProyectoRequest;
import com.ipss.demo.exception.NotFoundException;
import com.ipss.demo.model.Proyecto;
import com.ipss.demo.repository.ProyectoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {

  private final ProyectoRepository repo;

  public ProyectoService(ProyectoRepository repo){ this.repo = repo; }

  public Page<Proyecto> listar(Pageable pageable){ return repo.findAll(pageable); }

  public Page<Proyecto> buscar(String estado, java.time.LocalDate desde, java.time.LocalDate hasta, Pageable pageable){
    if (estado != null && estado.isBlank()) estado = null;
    return repo.search(estado, desde, hasta, pageable);
  }

  public Proyecto crear(ProyectoRequest r){
    Proyecto p = new Proyecto();
    p.setNombre(r.nombre);
    p.setDescripcion(r.descripcion);
    p.setFechaInicio(r.fechaInicio);
    p.setFechaFin(r.fechaFin);
    p.setEstado(r.estado != null ? r.estado : "CREADO");
    return repo.save(p);
  }

  public Proyecto actualizar(Long id, ProyectoRequest r){
    Proyecto p = repo.findById(id).orElseThrow(() -> new NotFoundException("Proyecto "+id+" no existe"));
    if (r.nombre != null && !r.nombre.isBlank()) p.setNombre(r.nombre);
    p.setDescripcion(r.descripcion);
    p.setFechaInicio(r.fechaInicio);
    p.setFechaFin(r.fechaFin);
    if (r.estado != null) p.setEstado(r.estado);
    return repo.save(p);
  }

  public void eliminar(Long id){
    if (!repo.existsById(id)) throw new NotFoundException("Proyecto "+id+" no existe");
    repo.deleteById(id);
  }
}
