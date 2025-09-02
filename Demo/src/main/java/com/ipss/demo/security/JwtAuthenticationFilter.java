package com.ipss.demo.security;

import com.ipss.demo.model.AppUser;
import com.ipss.demo.repository.AppUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwt;
  private final AppUserRepository users;

  public JwtAuthenticationFilter(JwtService jwt, AppUserRepository users) {
    this.jwt = jwt; this.users = users;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws ServletException, IOException {

    String header = req.getHeader(HttpHeaders.AUTHORIZATION);
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      try {
        String username = jwt.getSubject(token);
        AppUser u = users.findByUsername(username).orElse(null);
        if (u != null) {
          var auth = new UsernamePasswordAuthenticationToken(
              u.getUsername(), null,
              List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name()))
          );
          SecurityContextHolder.getContext().setAuthentication(auth);
        }
      } catch (Exception ignored) {}
    }
    chain.doFilter(req, res);
  }
}
