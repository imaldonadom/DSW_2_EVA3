package com.ipss.demo.config;

import com.ipss.demo.model.*;
import com.ipss.demo.repository.CursoRepository;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(UsuarioRepository usuarioRepo, CursoRepository cursoRepo) {
        return args -> {
            if (usuarioRepo.count() == 0) {
                usuarioRepo.save(new Usuario("Admin Eva", "admin@eva3.cl", Rol.ADMIN));
                usuarioRepo.save(new Usuario("María Profesor", "maria@eva3.cl", Rol.PROFESOR));
                usuarioRepo.save(new Usuario("Ignacio Alumno", "ignacio@eva3.cl", Rol.ALUMNO));
                usuarioRepo.save(new Usuario("Jorge Alumno", "jorge@eva3.cl", Rol.ALUMNO));
            }
            if (cursoRepo.count() == 0) {
                cursoRepo.save(new Curso("Desarrollo Web 2", "DW2-001"));
                cursoRepo.save(new Curso("Arquitectura Cloud", "AC-101"));
            }
        };
    }
}
