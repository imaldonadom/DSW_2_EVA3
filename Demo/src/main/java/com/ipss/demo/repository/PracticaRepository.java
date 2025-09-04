package com.ipss.demo.repository;

import com.ipss.demo.model.Practica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticaRepository extends JpaRepository<Practica, Long> {
  List<Practica> findByAlumno_Id(Long alumnoId);
}
