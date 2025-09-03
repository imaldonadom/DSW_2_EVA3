package com.ipss.demo.repository;

import com.ipss.demo.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> { }
