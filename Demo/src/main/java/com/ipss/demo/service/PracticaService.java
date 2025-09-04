package com.ipss.demo.service;

import com.ipss.demo.dto.PracticaRequest;
import com.ipss.demo.dto.PracticaResponse;
import com.ipss.demo.model.Practica;
import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.PracticaRepository;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PracticaService {
  private final PracticaRepository repo;
  private final UsuarioRepository usuarios;

  public PracticaService(PracticaRepository repo, UsuarioRepository usuarios) {
    this.repo = repo; this.usuarios = usuarios;
  }

  public List<PracticaResponse> listar() {
    return repo.findAll().stream().map(this::toResponse).toList();
  }

  public List<PracticaResponse> listarPorAlumno(Long alumnoId) {
    return repo.findByAlumno_Id(alumnoId).stream().map(this::toResponse).toList();
  }

  public PracticaResponse buscar(Long id) {
    return toResponse(repo.findById(id).orElseThrow());
  }

  public PracticaResponse crear(PracticaRequest r) {
    Practica p = new Practica();
    mapRequest(p, r);
    return toResponse(repo.save(p));
  }

  public PracticaResponse actualizar(Long id, PracticaRequest r) {
    Practica p = repo.findById(id).orElseThrow();
    mapRequest(p, r);
    return toResponse(repo.save(p));
  }

  public void eliminar(Long id) { repo.deleteById(id); }

  // ---- helpers ----
  private void mapRequest(Practica p, PracticaRequest r) {
    Usuario alumno = usuarios.findById(r.alumnoId).orElseThrow();
    p.setAlumno(alumno);

    Usuario profesor = (r.profesorId != null) ? usuarios.findById(r.profesorId).orElse(null) : null;
    p.setProfesor(profesor);

    p.setCarrera(r.carrera);
    p.setDescripcion(r.descripcion);
    p.setFechaInicio(r.fechaInicio!=null && !r.fechaInicio.isBlank() ? LocalDate.parse(r.fechaInicio) : null);
    p.setFechaFin(r.fechaFin!=null && !r.fechaFin.isBlank() ? LocalDate.parse(r.fechaFin) : null);

    p.setEmpresaNombre(r.empresaNombre);
    p.setEmpresaDireccion(r.empresaDireccion);
    p.setEmpresaTelefono(r.empresaTelefono);

    p.setJefeDirectoNombre(r.jefeDirectoNombre);
    p.setJefeDirectoContacto(r.jefeDirectoContacto);
  }

  private PracticaResponse toResponse(Practica p) {
    PracticaResponse r = new PracticaResponse();
    r.id = p.getId();
    r.alumnoId = p.getAlumno()!=null ? p.getAlumno().getId() : null;
    r.alumnoNombre = p.getAlumno()!=null ? p.getAlumno().getNombre() : null;
    r.profesorId = p.getProfesor()!=null ? p.getProfesor().getId() : null;
    r.profesorNombre = p.getProfesor()!=null ? p.getProfesor().getNombre() : null;
    r.carrera = p.getCarrera();
    r.descripcion = p.getDescripcion();
    r.fechaInicio = p.getFechaInicio()!=null ? p.getFechaInicio().toString() : null;
    r.fechaFin = p.getFechaFin()!=null ? p.getFechaFin().toString() : null;
    r.empresaNombre = p.getEmpresaNombre();
    r.empresaDireccion = p.getEmpresaDireccion();
    r.empresaTelefono = p.getEmpresaTelefono();
    r.jefeDirectoNombre = p.getJefeDirectoNombre();
    r.jefeDirectoContacto = p.getJefeDirectoContacto();
    return r;
  }
}
