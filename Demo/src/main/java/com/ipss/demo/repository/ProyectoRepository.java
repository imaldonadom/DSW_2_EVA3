package com.ipss.demo.repository;

import com.ipss.demo.model.Proyecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

  @Query("""
      SELECT p FROM Proyecto p
      WHERE (:estado IS NULL OR p.estado = :estado)
        AND (:desde IS NULL OR p.fechaInicio >= :desde)
        AND (:hasta IS NULL OR p.fechaInicio <= :hasta)
      """)
  Page<Proyecto> search(String estado, LocalDate desde, LocalDate hasta, Pageable pageable);
}
