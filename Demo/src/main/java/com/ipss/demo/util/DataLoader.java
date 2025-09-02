package com.ipss.demo.util;

import com.ipss.demo.model.AppUser;
import com.ipss.demo.model.Proyecto;
import com.ipss.demo.model.Role;
import com.ipss.demo.repository.AppUserRepository;
import com.ipss.demo.repository.ProyectoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

@Configuration
public class DataLoader {

  @Bean
  CommandLineRunner init(ProyectoRepository proyectos, AppUserRepository users, PasswordEncoder encoder) {
    return args -> {
      Path dataDir = Path.of(System.getProperty("user.dir"), "data");
      Files.createDirectories(dataDir);
      System.out.println("[INIT] Carpeta de datos: " + dataDir.toAbsolutePath());

      if (proyectos.count() == 0) {
        Proyecto a = new Proyecto();
        a.setNombre("EVA3 - Portal Ventasfix");
        a.setDescripcion("CRUD base y endpoints REST");
        a.setFechaInicio(LocalDate.now().minusDays(7));
        a.setEstado("CREADO");
        proyectos.save(a);

        Proyecto b = new Proyecto();
        b.setNombre("QA - Dashboard");
        b.setDescripcion("Gráficos y KPIs operacionales");
        b.setFechaInicio(LocalDate.now().minusDays(30));
        b.setEstado("EN_PROCESO");
        proyectos.save(b);
      }

      if (!users.existsByUsername("admin")) {
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin123"));
        admin.setRole(Role.ADMIN);
        users.save(admin);
        System.out.println("[INIT] Usuario admin/admin123 creado");
      }
    };
  }
}
