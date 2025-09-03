package com.ipss.demo.util;

import com.ipss.demo.model.Proyecto;
import com.ipss.demo.repository.ProyectoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final ProyectoRepository repo;

  public DataLoader(ProyectoRepository repo) {
    this.repo = repo;
  }

  @Override
  public void run(String... args) {
    if (repo.count() == 0) {
      Proyecto a = new Proyecto();
      a.setNombre("EVA3 - Portal Ventasfix");
      a.setDescripcion("CRUD base y endpoints REST");
      a.setFechaInicio(LocalDate.now().minusDays(7));
      a.setEstado("CREADO");

      Proyecto b = new Proyecto();
      b.setNombre("QA - Dashboard");
      b.setDescripcion("Gráficos y KPIs operacionales");
      b.setFechaInicio(LocalDate.now().minusDays(30));
      b.setEstado("EN_PROCESO");

      repo.save(a);
      repo.save(b);
    }
  }
}
