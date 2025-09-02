package com.ipss.demo.dto;

import com.ipss.demo.model.Proyecto;

import java.time.LocalDate;

public class ProyectoResponse {
  public Long id;
  public String nombre;
  public String descripcion;
  public LocalDate fechaInicio;
  public LocalDate fechaFin;
  public String estado;

  public static ProyectoResponse of(Proyecto p){
    ProyectoResponse r = new ProyectoResponse();
    r.id = p.getId();
    r.nombre = p.getNombre();
    r.descripcion = p.getDescripcion();
    r.fechaInicio = p.getFechaInicio();
    r.fechaFin = p.getFechaFin();
    r.estado = p.getEstado();
    return r;
  }
}
