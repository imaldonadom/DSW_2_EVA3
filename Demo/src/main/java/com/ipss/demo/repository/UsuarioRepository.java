package com.ipss.demo.repository;

import com.ipss.demo.model.Rol;
import com.ipss.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    List<Usuario> findByRol(Rol rol);
}
