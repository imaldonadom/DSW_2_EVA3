package com.ipss.demo.repository;

import com.ipss.demo.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
}
