package com.ipss.demo.controller;

import com.ipss.demo.dto.AuthDtos.AuthResponse;
import com.ipss.demo.dto.AuthDtos.LoginRequest;
import com.ipss.demo.dto.AuthDtos.RegisterRequest;
import com.ipss.demo.model.AppUser;
import com.ipss.demo.model.Role;
import com.ipss.demo.repository.AppUserRepository;
import com.ipss.demo.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

  private final AppUserRepository users;
  private final PasswordEncoder encoder;
  private final JwtService jwt;

  public AuthController(AppUserRepository users, PasswordEncoder encoder, JwtService jwt) {
    this.users = users; this.encoder = encoder; this.jwt = jwt;
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody RegisterRequest r){
    if (r.username == null || r.username.isBlank() || r.password == null || r.password.length() < 4)
      return ResponseEntity.badRequest().body(Map.of("message","username y password (>=4) requeridos"));
    if (users.existsByUsername(r.username))
      return ResponseEntity.badRequest().body(Map.of("message","username ya existe"));

    AppUser u = new AppUser();
    u.setUsername(r.username.trim());
    u.setPassword(encoder.encode(r.password));
    u.setRole(Role.USER);
    users.save(u);

    String token = jwt.generate(u.getUsername(), Map.of("role", u.getRole().name()));
    return ResponseEntity.ok(new AuthResponse(token, u.getUsername(), u.getRole().name()));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest r){
    var u = users.findByUsername(r.username).orElse(null);
    if (u == null || !encoder.matches(r.password, u.getPassword()))
      return ResponseEntity.status(401).body(Map.of("message","credenciales inválidas"));

    String token = jwt.generate(u.getUsername(), Map.of("role", u.getRole().name()));
    return ResponseEntity.ok(new AuthResponse(token, u.getUsername(), u.getRole().name()));
  }
}
